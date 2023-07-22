package com.caso_de_estudo_contingencia.estudo_contingencia.util;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SelfExpiringHashMap<K, V> implements SelfExpiringMap<K, V> {

    private static final Logger logger = LoggerFactory.getLogger(SelfExpiringHashMap.class);

    private final Map<K, V> internalMap;

    private final Map<K, ExpiringKey<K>> expiringKeys;

    /**
     * Holds the map keys using the given life time for expiration.
     */
    private final DelayQueue<ExpiringKey<K>> delayQueue = new DelayQueue<>();

    /**
     * The default max life time in milliseconds.
     */
    private final long maxLifeTimeMillis;

    public SelfExpiringHashMap() {
        internalMap = new ConcurrentHashMap<>();
        expiringKeys = new WeakHashMap<>();
        this.maxLifeTimeMillis = Long.MAX_VALUE;
    }

    public SelfExpiringHashMap(long defaultMaxLifeTimeMillis) {
        internalMap = new ConcurrentHashMap<>();
        expiringKeys = new WeakHashMap<>();
        this.maxLifeTimeMillis = defaultMaxLifeTimeMillis;
    }

    public SelfExpiringHashMap(long defaultMaxLifeTimeMillis, int initialCapacity) {
        internalMap = new ConcurrentHashMap<>(initialCapacity);
        expiringKeys = new WeakHashMap<>(initialCapacity);
        this.maxLifeTimeMillis = defaultMaxLifeTimeMillis;
    }

    public SelfExpiringHashMap(long defaultMaxLifeTimeMillis, int initialCapacity, float loadFactor) {
        internalMap = new ConcurrentHashMap<>(initialCapacity, loadFactor);
        expiringKeys = new WeakHashMap<>(initialCapacity, loadFactor);
        this.maxLifeTimeMillis = defaultMaxLifeTimeMillis;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        cleanup();
        return internalMap.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        cleanup();
        return internalMap.isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsKey(Object key) {
        cleanup();
        return internalMap.containsKey(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsValue(Object value) {
        cleanup();
        return internalMap.containsValue(value);
    }

    @Override
    public V get(Object key) {
        return this.get(key, true);
    }

    @SuppressWarnings("unchecked")
    public V get(Object key, Boolean renewKey) {
        cleanup();
        if (Boolean.TRUE.equals(renewKey)) {
            renewKey((K) key);
        }
        return internalMap.get(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public V put(K key, V value) {
        return this.put(key, value, maxLifeTimeMillis);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public V put(K key, V value, long lifeTimeMillis) {
        cleanup();
        ExpiringKey<K> delayedKey = new ExpiringKey<>(key, lifeTimeMillis);
        ExpiringKey<K> oldKey = expiringKeys.put(key, delayedKey);
        if (oldKey != null) {
            expireKey(oldKey);
            expiringKeys.put(key, delayedKey);
        }
        if (!delayQueue.offer(delayedKey)) {
            logger.debug("delay queue returned false");
        }
        return internalMap.put(key, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public V remove(Object key) {
        V removedValue = internalMap.remove(key);
        expireKey(expiringKeys.remove(key));
        return removedValue;
    }

    /**
     * Not supported.
     */
    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean renewKey(K key) {
        ExpiringKey<K> delayedKey = expiringKeys.get(key);
        if (delayedKey != null) {
            delayedKey.renew();
            return true;
        }
        return false;
    }

    private void expireKey(ExpiringKey<K> delayedKey) {
        if (delayedKey != null) {
            delayedKey.expire();
            cleanup();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        delayQueue.clear();
        expiringKeys.clear();
        internalMap.clear();
    }

    /**
     * Not supported.
     */
    @Override
    public Set<K> keySet() {
        return internalMap.keySet();
    }

    /**
     * Not supported.
     */
    @Override
    public Collection<V> values() {
        throw new UnsupportedOperationException();
    }

    /**
     * Not supported.
     */
    @Override
    public Set<Entry<K, V>> entrySet() {
        throw new UnsupportedOperationException();
    }

    private void cleanup() {
        ExpiringKey<K> delayedKey = delayQueue.poll();
        while (delayedKey != null) {
            internalMap.remove(delayedKey.getKey());
            expiringKeys.remove(delayedKey.getKey());
            delayedKey = delayQueue.poll();
        }
    }

    @SuppressWarnings("hiding")
    private class ExpiringKey<K> implements Delayed {

        private long startTime = System.currentTimeMillis();
        private final long maxLifeTimeMillis;
        private final K key;

        public ExpiringKey(K key, long maxLifeTimeMillis) {
            this.maxLifeTimeMillis = maxLifeTimeMillis;
            this.key = key;
        }

        public K getKey() {
            return key;
        }

        /**
         * {@inheritDoc}
         */
        @SuppressWarnings("unchecked")
        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final ExpiringKey<K> other = (ExpiringKey<K>) obj;
            return !(this.key != other.key && (this.key == null || !this.key.equals(other.key)));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int hashCode() {
            int hash = 7;
            hash = 31 * hash + (this.key != null ? this.key.hashCode() : 0);
            return hash;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(getDelayMillis(), TimeUnit.MILLISECONDS);
        }

        private long getDelayMillis() {
            return (startTime + maxLifeTimeMillis) - System.currentTimeMillis();
        }

        public void renew() {
            startTime = System.currentTimeMillis();
        }

        public void expire() {
            startTime = Long.MIN_VALUE;
        }

        /**
         * {@inheritDoc}
         */
        @SuppressWarnings("unchecked")
        @Override
        public int compareTo(Delayed that) {
            return Long.compare(this.getDelayMillis(), ((ExpiringKey<K>) that).getDelayMillis());
        }
    }

}

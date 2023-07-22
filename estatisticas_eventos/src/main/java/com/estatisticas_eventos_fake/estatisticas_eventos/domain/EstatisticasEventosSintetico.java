package com.estatisticas_eventos_fake.estatisticas_eventos.domain;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class EstatisticasEventosSintetico {

    private Integer idPosto;
    private String nomePosto;
    private Map<String, Integer> mapaDeDatas = new TreeMap<String, Integer>();

    public EstatisticasEventosSintetico() {
    }

    /**
     * @return Integer return the idPosto
     */
    public Integer getIdPosto() {
        return idPosto;
    }

    /**
     * @param idPosto the idPosto to set
     */
    public void setIdPosto(Integer idPosto) {
        this.idPosto = idPosto;
    }

    /**
     * @return String return the nomePosto
     */
    public String getNomePosto() {
        return nomePosto;
    }

    /**
     * @param nomePosto the nomePosto to set
     */
    public void setNomePosto(String nomePosto) {
        this.nomePosto = nomePosto;
    }

    /**
     * @return Map<String, Integer> return the mapaDeDatas
     */
    public Map<String, Integer> getMapaDeDatas() {
        return mapaDeDatas;
    }

    /**
     * @param mapaDeDatas the mapaDeDatas to set
     */
    public void setMapaDeDatas(Map<String, Integer> mapaDeDatas) {
        this.mapaDeDatas = mapaDeDatas;
    }

    /* hash equals */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        return this.idPosto.equals(((EstatisticasEventosSintetico) obj).getIdPosto());
    }

    /* Update data */
    public void updateData(EstatisticasEventosSintetico ee) {

        if (ee.getIdPosto().equals(this.getIdPosto())) {

            Set<String> keys = ee.getMapaDeDatas().keySet();

            for (String d : keys) {
                if (this.mapaDeDatas.containsKey(d)) {
                    this.mapaDeDatas.put(d, this.mapaDeDatas.get(d) + ee.getMapaDeDatas().get(d));
                } else {
                    mapaDeDatas.put(d, ee.getMapaDeDatas().get(d));
                }
            }
        }
    }
}

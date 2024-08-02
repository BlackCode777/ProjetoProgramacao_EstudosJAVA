package com.caso_de_estudo_contingencia.estudo_contingencia.comparator;

import java.util.Comparator;

import com.caso_de_estudo_contingencia.estudo_contingencia.model.Posto;

public class PostoComparator implements Comparator<Posto> {

    @Override
    public int compare(Posto o1, Posto o2) {
        return o1.getDescricao().compareTo(o2.getDescricao());
    }

}

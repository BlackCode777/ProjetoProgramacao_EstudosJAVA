package com.estatisticas_eventos_fake.estatisticas_eventos.comparator;

import java.util.Comparator;

import com.estatisticas_eventos_fake.estatisticas_eventos.domain.Posto;

public class PostosComparator implements Comparator<Posto> {

    @Override
    public int compare(Posto o1, Posto o2) {

        return o1.getDesc().compareTo(o2.getDesc());

    }

}

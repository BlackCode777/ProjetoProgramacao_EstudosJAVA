package com.estatisticas_eventos_fake.estatisticas_eventos.comparator;

import java.util.Comparator;

import com.estatisticas_eventos_fake.estatisticas_eventos.domain.EstatisticasBarramento;

public class EstatisticasBarramentoComparator implements Comparator<EstatisticasBarramento> {

    @Override
    public int compare(EstatisticasBarramento o1, EstatisticasBarramento o2) {
        return o1.getPosto().getDesc().compareTo(o2.getPosto().getDesc());
    }

}

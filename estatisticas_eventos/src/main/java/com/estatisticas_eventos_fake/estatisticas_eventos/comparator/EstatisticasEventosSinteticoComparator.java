package com.estatisticas_eventos_fake.estatisticas_eventos.comparator;

import java.util.Comparator;

import com.estatisticas_eventos_fake.estatisticas_eventos.domain.EstatisticasEventosSintetico;

public class EstatisticasEventosSinteticoComparator implements Comparator<EstatisticasEventosSintetico> {

    @Override
    public int compare(EstatisticasEventosSintetico o1, EstatisticasEventosSintetico o2) {

        return o1.getNomePosto().compareTo(o2.getNomePosto());

    }

}

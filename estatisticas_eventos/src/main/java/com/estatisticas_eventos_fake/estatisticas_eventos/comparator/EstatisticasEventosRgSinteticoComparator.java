package com.estatisticas_eventos_fake.estatisticas_eventos.comparator;

import java.util.Comparator;

import com.estatisticas_eventos_fake.estatisticas_eventos.domain.EstatisticasEventosRgSintetico;

public class EstatisticasEventosRgSinteticoComparator implements Comparator<EstatisticasEventosRgSintetico> {

    /* Function for compareto name of the Point */
    public int compare(EstatisticasEventosRgSintetico o1, EstatisticasEventosRgSintetico o2) {
        return o1.getNomePosto().compareTo(o2.getNomePosto());
    }

}

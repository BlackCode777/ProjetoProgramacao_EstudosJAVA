package com.caso_de_estudo_contingencia.estudo_contingencia.comparator;

import java.util.Comparator;

import com.caso_de_estudo_contingencia.estudo_contingencia.model.Servico;

public class ServicoComparator implements Comparator<Servico> {

    @Override
    public int compare(Servico o1, Servico o2) {
        return o1.getDescricao().compareTo(o2.getDescricao());
    }

}

package com.caso_de_estudo_contingencia.estudo_contingencia.comparator;

import java.util.Comparator;

import com.caso_de_estudo_contingencia.estudo_contingencia.model.Orgao;

public class OrgaoComparator implements Comparator<Orgao> {

    @Override
    public int compare(Orgao o1, Orgao o2) {
        return o1.getDescricao().compareTo(o2.getDescricao());
    }

}

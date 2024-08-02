package com.estatisticas_eventos_fake.estatisticas_eventos.domain;

import java.util.ArrayList;
import java.util.List;

public class EstatisticasBarramento {

    private Posto posto;
    private List<Sistema> sistemas = new ArrayList<Sistema>();

    public EstatisticasBarramento() {
    }

    public Posto getPosto() {
        return posto;
    }

    public void setPosto(Posto posto) {
        this.posto = posto;
    }

    public List<Sistema> getSistemas() {
        return sistemas;
    }

    public void setSistemas(List<Sistema> sistemas) {
        this.sistemas = sistemas;
    }

    // aux
    public void addData(Sistema sistema) {
        if (sistemas == null) {
            sistemas = new ArrayList<Sistema>();
        }

        sistemas.add(sistema);
    }

}

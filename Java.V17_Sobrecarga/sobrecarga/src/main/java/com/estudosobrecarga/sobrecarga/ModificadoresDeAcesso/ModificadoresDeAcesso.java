package com.estudosobrecarga.sobrecarga.ModificadoresDeAcesso;

public class ModificadoresDeAcesso {

    private String nomeModifivador;
    public String nomeModificadorPublic;

    void constructor(String nomeModifivador, String nomeModificadorPublic) {
        this.nomeModificadorPublic = nomeModificadorPublic;
        this.nomeModifivador = nomeModifivador;
    }

    public String getNomeModificadorPublic() {
        return nomeModificadorPublic;
    }

    public void setNomeModificadorPublic(String nomeModificadorPublic) {
        this.nomeModificadorPublic = nomeModificadorPublic;
    }

    public String getNomeModifivador(String string) {
        return nomeModifivador;
    }

    public void setNomeModifivador(String nomeModifivador) {
        this.nomeModifivador = nomeModifivador;
    }

}

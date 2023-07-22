package com.estatisticas_eventos_fake.estatisticas_eventos.domain;

public class RgMaiorMenor {

    private Integer quantidade = 0;
    private Integer quantidadeMaior = 0;
    private Integer quantidadeMenor = 0;
    private Integer quantidadeSegundaVia = 0;

    public RgMaiorMenor() {
    }

    /**
     * @return Integer return the quantidade
     */
    public Integer getQuantidade() {
        return quantidade;
    }

    /**
     * @param quantidade the quantidade to set
     */
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * @return Integer return the quantidadeMaior
     */
    public Integer getQuantidadeMaior() {
        return quantidadeMaior;
    }

    /**
     * @param quantidadeMaior the quantidadeMaior to set
     */
    public void setQuantidadeMaior(Integer quantidadeMaior) {
        this.quantidadeMaior = quantidadeMaior;
    }

    /**
     * @return Integer return the quantidadeMenor
     */
    public Integer getQuantidadeMenor() {
        return quantidadeMenor;
    }

    /**
     * @param quantidadeMenor the quantidadeMenor to set
     */
    public void setQuantidadeMenor(Integer quantidadeMenor) {
        this.quantidadeMenor = quantidadeMenor;
    }

    /**
     * @return Integer return the quantidadeSegundaVia
     */
    public Integer getQuantidadeSegundaVia() {
        return quantidadeSegundaVia;
    }

    /**
     * @param quantidadeSegundaVia the quantidadeSegundaVia to set
     */
    public void setQuantidadeSegundaVia(Integer quantidadeSegundaVia) {
        this.quantidadeSegundaVia = quantidadeSegundaVia;
    }

}

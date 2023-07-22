package com.estatisticas_eventos_fake.estatisticas_eventos.comparator;

import java.util.Comparator;

import com.estatisticas_eventos_fake.estatisticas_eventos.model.Barramento;

/*
O código que você compartilhou é uma implementação de um comparador personalizado chamado BarramentoComparator. Esse comparador é usado para definir uma ordem específica para objetos do tipo 
Barramento, que podem ser usados para ordenação em coleções ou estruturas de dados que requerem uma lógica de comparação personalizada.
A classe BarramentoComparator implementa a interface Comparator<Barramento>, que exige a implementação do método compare(T o1, T o2). Nesse caso, T é substituído pelo tipo Barramento.
A lógica da comparação neste comparador é baseada em múltiplos critérios, e o objetivo é determinar a ordem de dois objetos Barramento com base em alguns campos específicos do objeto. A ordem 
determinada será usada para classificar uma lista ou coleção de objetos Barramento.
Aqui está a lógica de comparação implementada no método compare:
Primeiro, o comparador verifica se ambos os objetos Barramento possuem o campo Posto definido. Se sim, compara o campo desc do Posto. Se os campos desc são iguais, passa para o próximo critério de comparação.
O próximo critério de comparação é a data dos objetos Barramento. Se a data for a mesma, passa para o próximo critério de comparação.
O próximo critério é comparar o campo desc dos campos Orgao dos objetos Barramento. Se os campos desc são iguais, passa para o próximo critério de comparação.
O último critério é comparar o campo desc dos campos Servico dos objetos Barramento.
Se o primeiro objeto Barramento for considerado "menor" que o segundo objeto em algum critério, o método compare retorna um valor negativo. Se os objetos forem considerados "iguais" em todos os 
critérios, o método retorna 0. Se o primeiro objeto for considerado "maior" que o segundo objeto em algum critério, o método retorna um valor positivo.
A exceção Exception e é capturada e tratada, retornando 0 se ocorrer um erro durante a comparação. No entanto, é importante notar que tratar uma exceção dessa maneira não é uma prática recomendada e pode 
resultar em comportamento inesperado.
Este comparador pode ser usado, por exemplo, para ordenar uma lista de objetos Barramento usando a classe Collections.sort():
java
Copy code
List<Barramento> listaBarramentos = new ArrayList<>();
// Adicione objetos Barramento à lista
Collections.sort(listaBarramentos, new BarramentoComparator());
Save to grepper
Dessa forma, a lista de Barramento será classificada de acordo com a ordem definida no BarramentoComparator.
*/

public class BarramentoComparator implements Comparator<Barramento> {

    @Override
    public int compare(Barramento o1, Barramento o2) {

        /* Compara os postos e a descrição dos postos */
        if (o1.getPosto() != null && o2.getPosto() != null
                && o1.getPosto().getDesc().compareTo(o2.getPosto().getDesc()) == 0) {

            /* Compara as datas */
            if (o1.getData().compareTo(o2.getData()) == 0) {

                /* Compara a descrição dos orgãos */
                if (o1.getOrgao().getDesc().compareTo(o2.getOrgao().getDesc()) == 0) {

                    /* retorna o serviço descr */
                    return o1.getServico().getDesc().compareTo(o2.getServico().getDesc());
                } else {
                    return o1.getOrgao().getDesc().compareTo(o2.getOrgao().getDesc());
                }
            } else {
                try {
                    return o1.getDataTranf().compareTo(o2.getDataTranf());
                } catch (Exception e) {
                    return 0;
                }
            }
        } else {
            if (o1.getPosto() != null && o2.getPosto() != null) {
                return o1.getPosto().getDesc().compareTo(o2.getPosto().getDesc());
            } else {
                try {
                    return o1.getDataTranf().compareTo(o2.getDataTranf());
                } catch (Exception e) {
                    return 0;
                }
            }
        }
    }
}
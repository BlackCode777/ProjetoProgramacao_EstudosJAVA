package com.estudosobrecarga.sobrecarga.Secao11TopicosEspeciaisJavaDataHora;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class ExecucaoDatasHoras {

    // https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html

    public static void main(String[] args) {
        System.out.println(" ********** Trabalhan do com datas customizadas ********** ");
        System.out.println(" ******************* DateTimeFormatter ******************* ");

        // Vou criar uma variável LocalDate que é um formato ISO local
        LocalDate dataLocal = LocalDate.parse("2021-08-15");
        LocalDateTime dataHoraLocal = LocalDateTime.parse("2024-06-02T18:28:00.761546700");
        Instant instant = Instant.parse("2021-08-15T18:28:00.761546700Z");

        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataFormatada = LocalDate.parse("21/05/2024", formatter1);

        System.out.println(" ********** Trabalhan do com datas customizadas ********** ");
        System.out.println(" ******************* DateTimeFormatter transformando para texto ******************* ");
        // Vou passar a data para ser formata para o formatter e armazer este
        // Especificando o formato da data desejada
        // a variável formatter contém o formato da data que eu preciso
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Agora vou passar a data para ser formata para o formatter e armazer este
        // formato em uma variável LocalDate que é um formato ISO local
        LocalDate dataFormatter = LocalDate.parse("21/05/2024", formatter);
        System.out.println("Data formatada '21/05/2024' >>> " + dataFormatter); // 2024-05-21

        System.out.println();
        System.out.println(" ********** Trabalhan do com datas customizadas ********** ");
        LocalDate dataCustomizada = LocalDate.of(2021, 8, 15);
        System.out.println("Data customizada '2021-08-15' >>> " + dataCustomizada); // 2021-08-15

        System.out.println();
        System.out.println(" ************************************************** ");
        System.out.println(" ******** Convertendo para formato ISO ******** ");
        LocalDate data = LocalDate.parse("2021-08-15");
        System.out.println("Data LocalDate.parse() >>> " + data);
        LocalDateTime dataTime = LocalDateTime.parse("2024-06-02T18:28:00.761546700");
        System.out.println("Data e hora LocalDateTime.parse() >>> " + dataTime);
        System.out.println(" ************************************************** ");
        // Instant instant = Instant.now();
        System.out.println("Instant >>> " + instant);
        System.out.println(" ************************************************** ");
        LocalDateTime agora = LocalDateTime.now();
        System.out.println("Data e hora atual com LocalDateTime >>> " + agora);
        System.out.println(" ************************************************** ");
        LocalDate hoje = LocalDate.now();
        System.out.println("Data de hoje formato string >>> " + hoje.toString());
        System.out.println("Formato dia/mes/ano >>> " + hoje.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        System.out.println("Pegando cada dado 'dia/mês/ano' separadamente >>> " + hoje.getDayOfMonth() + "/"
                + hoje.getMonthValue() + "/" + hoje.getYear());
        System.out.println("Pegando cada dado 'dia/mês/ano' separadamente >>> " + hoje.getDayOfMonth() + "/"
                + hoje.getMonth() + "/" + hoje.getYear());
        System.out.println(hoje);

        System.out.println(" ************************************************** ");

        System.out.println(" ********** Criando listagem de datas entre dataInicial e dataFinal ********** ");
        LocalDate dataInicio = LocalDate.of(2021, 8, 15);
        LocalDate dataFim = LocalDate.of(2021, 8, 20);
        List<LocalDate> datas = listarDatasEntre(dataInicio, dataFim);
        for (LocalDate dataLista : datas) {
            System.out.println(dataLista);
        }

    }

    // Função para calcular a diferença entre duas datas
    public static long calcularDiferencaDias(LocalDate dataInicial, LocalDate dataFinal) {
        return ChronoUnit.DAYS.between(dataInicial, dataFinal);
    }

    // Função para adicionar dias a uma data
    public static LocalDate adicionarDias(LocalDate data, long dias) {
        return data.plusDays(dias);
    }

    // Função para formatar uma data
    public static String formatarData(LocalDate data, String padrao) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(padrao);
        return data.format(formatter);
    }

    // Função para obter a data atual formatada
    public static String obterDataAtualFormatada(String padrao) {
        LocalDate hoje = LocalDate.now();
        return formatarData(hoje, padrao);
    }

    // Função para criar uma lista de datas entre duas datas
    public static List<LocalDate> listarDatasEntre(LocalDate dataInicio, LocalDate dataFim) {
        List<LocalDate> datas = new ArrayList<>();
        LocalDate dataAtual = dataInicio;
        while (!dataAtual.isAfter(dataFim)) {
            datas.add(dataAtual);
            dataAtual = dataAtual.plusDays(1);
        }
        return datas;
    }

    // Função para converter Instant para LocalDateTime
    public static LocalDateTime converterInstantParaLocalDateTime(Instant instant) {
        return LocalDateTime.ofInstant(instant, java.time.ZoneId.systemDefault());
    }

    // Função para exibir data e hora atual no formato ISO
    public static String obterDataHoraAtualISO() {
        LocalDateTime agora = LocalDateTime.now();
        return agora.toString();
    }
}

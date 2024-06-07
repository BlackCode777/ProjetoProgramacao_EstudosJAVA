package com.estudosobrecarga.sobrecarga.Secao11TopicosEspeciaisJavaDataHora.Secao11IntroducaoDataHora;

public class IntroducaoDataHoraConceitosI {

    /*
     * 
     * # Instanciação de Datas e Horas
     * - (Agora) - Data e hora atual do sistema.
     * - Texto ISO 8601 - Data e hora em formato de texto.
     * - Texto formato customizado - Data e hora em formato customizado.
     * - dia, mês, ano [horário] - Data e hora local.
     * 
     * # Formatação
     * - Data-hora -> Texto ISO 8601.
     * - Data-hora -> Texto formato customizado.
     * 
     */

    /*
     * PADRÃO ISO 8601
     * 
     * Data [hora] - local:
     * 2020-08-15
     * 2020-08-15T03:10:15
     * 2020-08-15T03:10:15.123456789 // nanosegundos
     * 
     * Data-hora global:
     * 2020-08-15T03:10:15Z // Z indica UTC
     * 2020-08-15T03:10:15-03:00 // offset
     * 2020-08-15T03:10:15.123456789-03:00 // nanosegundos
     * 
     */

    /*
     * TIMEZONA ( FUSO HORÁRIO )
     * 
     * -#- GMT - Greenwich Mean Time
     * 
     * -#- Outros fuso horários são relativos ao GMT/UTC.
     * - Exemplo: America/Sao_Paulo é -03:00 em relação ao GMT/UTC.
     * - Exemplo: America/New_York é -05:00 em relação ao GMT/UTC.
     * - Exemplo: Europe/London é +00:00 em relação ao GMT/UTC.
     * - Exemplo: Asia/Tokyo é +09:00 em relação ao GMT/UTC.
     * - Exemplo: Pacific/Honolulu é -10:00 em relação ao GMT/UTC.
     * - Exemplo: Etc/GMT+12 é +12:00 em relação ao GMT/UTC.
     * - Exemplo: Etc/GMT-12 é -12:00 em relação ao GMT/UTC.
     * 
     * -#- UTC - Coordinated Universal Time
     * 
     * -#- DST - Daylight Saving Time
     * 
     * -#- Fuso Horário: Região da Terra onde um determinado horário é utilizado.
     * 
     * -#- UTC: Tempo Universal Coordenado. É o fuso horário de referência a partir
     * do qual se calculam todas as outras zonas do mundo.
     * 
     * -#- GMT: Meridiano de Greenwich. É o fuso horário de referência a partir do
     * qual se calculam todas as outras zonas do mundo.
     * 
     * -#- DST: Horário de Verão. É a prática de adiantar os relógios em uma hora
     * durante os meses mais quentes do ano, de forma a aproveitar mais a luz do
     * dia.
     * 
     * -#- Zona de Tempo: Região da Terra onde um determinado fuso horário é
     * utilizado. Exemplo: America/Sao_Paulo.
     * 
     * -#- Offset: Diferença de tempo em relação ao UTC sem as regras de horário de
     * verão. Exemplo: -03:00.
     */

    /*
     * Conceitos Importantes
     * 
     * - Instant: Representa um instante específico na linha do tempo. É uma
     * quantidade de tempo em nanosegundos a partir de 01/01/1970 (Epoch).
     * 
     * - LocalDate: Representa uma data (ano, mês e dia) sem horário e sem fuso
     * horário.
     * 
     * - LocalTime: Representa um horário (hora, minuto, segundo e nanosegundos) sem
     * data e sem fuso horário.
     * 
     * - LocalDateTime: Representa uma data e hora, sem fuso horário.
     * 
     * - ZonedDateTime: Representa uma data e hora com fuso horário.
     * 
     * - ZoneId: Representa um fuso horário.
     * 
     * - ZoneOffset: Representa a diferença de tempo em relação ao UTC sem as regras
     * de horário de verão.
     * 
     * - Period: Representa um período de tempo com base em datas (exemplo: 2 anos,
     * 3 meses e 4 dias). É possível fazer operações de adição e subtração. Não
     * trabalha com horas. Exemplo: 2 anos + 3 meses + 4 dias. Não é possível
     * trabalhar com horas. Exemplo: 2 anos + 3 horas. Não faz sentido.
     * 
     * - Duration: Representa um período de tempo com base em tempo (horas, minutos,
     * segundos e nanosegundos). É possível fazer operações de adição e subtração.
     * Exemplo:
     * 3 horas + 5 minutos + 10 segundos. Não trabalha com datas.
     * Exemplo:
     * 3 horas + 5 dias. Não faz sentido.
     * 
     * - ChronoUnit: Enumeração que define as unidades suportadas para cálculos de
     * datas e horas.
     * exemplo: dias, horas, minutos, segundos, etc.
     * 
     * - DateTimeFormatter: Formata datas e horas.
     * exemplo: dd/MM/yyyy, yyyy-MM-dd HH:mm:ss, etc.
     * 
     * - DateTimeParseException: Exceção disparada em caso de erro de parse.
     * exemplo: data inválida.
     * 
     * - DateTimeException: Exceção disparada em caso de erro de manipulação de
     * datas e horas.
     * exemplo: 30 de fevereiro.
     * 
     * - Instant.now(): Obtém um instante específico na linha do tempo.
     * exemplo: 2020-08-15T03:10:15Z.
     * 
     * - LocalDate.now(): Obtém a data atual do sistema.
     * exemplo: 2020-08-15.
     * 
     * - LocalTime.now(): Obtém a hora atual do sistema.
     * exemplo: 03:10:15.
     * 
     * - LocalDateTime.now(): Obtém a data e hora atual do sistema.
     * exemplo: 2020-08-15T03:10:15.
     * 
     * - ZonedDateTime.now(): Obtém a data e hora atual do sistema com fuso horário.
     * exemplo: 2020-08-15T03:10:15-03:00[America/Sao_Paulo].
     * 
     * - ZoneId.of("America/Sao_Paulo"): Obtém um fuso horário específico.
     * exemplo: America/Sao_Paulo.
     * 
     * - ZoneOffset.of("-03:00"): Obtém um deslocamento de tempo específico.
     * exemplo: -03:00.
     * 
     * - Period.between(LocalDate, LocalDate): Calcula a diferença entre duas datas.
     * exemplo: 2 anos, 3 meses e 4 dias.
     * exemplo de calculo: Period.between(LocalDate.of(2020, 8, 15),
     * LocalDate.of(2022, 11, 20)).
     * 
     * - Duration.between(Temporal, Temporal): Calcula a diferença entre duas horas.
     * exemplo: 3 horas, 5 minutos e 10 segundos - não trabalha com datas.
     * exemplo de calculo: Duration.between(LocalTime.of(10, 0), LocalTime.of(13,
     * 5)).
     * 
     * - date.format(DateTimeFormatter): Formata uma data.
     * exemplo: date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).
     * 
     * - time.format(DateTimeFormatter): Formata uma hora.
     * exemplo: time.format(DateTimeFormatter.ofPattern("HH:mm:ss")).
     * 
     * - dateTime.format(DateTimeFormatter): Formata uma data e hora.
     * exemplo: dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")).
     * 
     * - LocalDate.parse("dd/MM/yyyy", DateTimeFormatter): Converte uma String para
     * LocalDate.
     * exemplo: LocalDate.parse("15/08/2020",
     * DateTimeFormatter.ofPattern("dd/MM/yyyy")).
     * 
     * - LocalTime.parse("HH:mm:ss", DateTimeFormatter): Converte uma String para
     * LocalTime.
     * exemplo: LocalTime.parse("03:10:15",
     * DateTimeFormatter.ofPattern("HH:mm:ss")).
     * 
     * - LocalDateTime.parse("dd/MM/yyyy HH:mm:ss", DateTimeFormatter): Converte uma
     * String para LocalDateTime.
     * exemplo: LocalDateTime.parse("15/08/2020 03:10:15",
     * DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")).
     * 
     * - ZonedDateTime.parse("dd/MM/yyyy HH:mm:ss", DateTimeFormatter): Converte uma
     * String para ZonedDateTime.
     * exemplo: ZonedDateTime.parse("15/08/2020 03:10:15",
     * DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")).
     * 
     * - date.atStartOfDay(): Converte uma data para LocalDateTime.
     * exemplo: date.atStartOfDay().
     * 
     * - time.atDate(LocalDate): Converte uma hora para LocalDateTime.
     * exemplo: time.atDate(LocalDate.of(2020, 8, 15)).
     * 
     * - dateTime.toLocalDate(): Extrai a data de um LocalDateTime.
     * exemplo: dateTime.toLocalDate().
     * 
     * - dateTime.toLocalTime(): Extrai a hora de um LocalDateTime.
     * exemplo: dateTime.toLocalTime().
     * exemplo de LocalDateTime: 2020-08-15T03:10:15.
     * 
     * - dateTime.toLocalDateTime(): Converte um ZonedDateTime para LocalDateTime.
     * exemplo: dateTime.toLocalDateTime().
     * exemplo de ZonedDateTime: 2020-08-15T03:10:15-03:00[America/Sao_Paulo].
     * 
     * - dateTime.withZoneSameInstant(ZoneId): Converte um ZonedDateTime para outro
     * fuso horário.
     * exemplo: dateTime.withZoneSameInstant(ZoneId.of("America/New_York")).
     * exemplo de withZoneSameInstant: 2020-08-15T03:10:15-03:00[America/Sao_Paulo].
     * 
     * - dateTime.withZoneSameLocal(ZoneId): Converte um ZonedDateTime para outro
     * fuso horário, mantendo o mesmo instante.
     * exemplo: dateTime.withZoneSameLocal(ZoneId.of("America/New_York")).
     * exemplo de withZoneSameLocal: 2020-08-15T03:10:15-03:00[America/New_York].
     * 
     * - dateTime.plusHours(2): Adiciona horas a um LocalDateTime.
     * exemplo: dateTime.plusHours(2).
     * 
     * - dateTime.minusDays(3): Subtrai dias de um LocalDateTime.
     * exemplo: dateTime.minusDays(3).
     * 
     * - dateTime.isAfter(other): Verifica se um LocalDateTime é posterior a outro.
     * exemplo: dateTime.isAfter(other).
     * 
     * - dateTime.isBefore(other): Verifica se um LocalDateTime é anterior a outro.
     * exemplo: dateTime.isBefore(other).
     * exemplo do que seria o other: LocalDateTime.of(2020, 8, 15, 3, 10, 15).
     * 
     * - dateTime.isEqual(other): Verifica se um LocalDateTime é igual a outro.
     * exemplo: dateTime.isEqual(other).
     * exemplo do que seria o other: LocalDateTime.of(2020, 8, 15, 3, 10, 15).
     * 
     * - dateTime.compareTo(other): Compara dois LocalDateTime.
     * exemplo: dateTime.compareTo(other).
     * exemplo do que seria o other: LocalDateTime.of(2020, 8, 15, 3, 10, 15).
     * 
     * - dateTime.equals(other): Verifica se dois LocalDateTime são iguais.
     * exemplo: dateTime.equals(other).
     * exemplo do que seria o other: LocalDateTime.of(2020, 8, 15, 3, 10, 15).
     * 
     * - dateTime.hashCode(): Obtém o código hash de um LocalDateTime.
     * exemplo: dateTime.hashCode().
     * 
     * - dateTime.toString(): Obtém uma representação textual de um LocalDateTime.
     * exemplo: dateTime.toString().
     * 
     * - dateTime.toInstant(): Converte um LocalDateTime para Instant.
     * exemplo: dateTime.toInstant().
     * 
     * - dateTime.toEpochSecond(): Obtém o número de segundos desde 01/01/1970.
     * exemplo: dateTime.toEpochSecond().
     * 
     * - dateTime.toEpochMilli(): Obtém o número de milissegundos desde 01/01/1970.
     * exemplo: dateTime.toEpochMilli().
     * 
     * - Instant.ofEpochSecond(seconds): Cria um Instant a partir de segundos.
     * exemplo: Instant.ofEpochSecond(seconds).
     * 
     * - Instant.ofEpochMilli(millis): Cria um Instant a partir de milissegundos.
     * exemplo: Instant.ofEpochMilli(millis).
     * 
     * - instant.getEpochSecond(): Obtém o número de segundos desde 01/01/1970.
     * exemplo: instant.getEpochSecond().
     * 
     * - instant.getNano(): Obtém o número de nanosegundos.
     * exemplo: instant.getNano().
     * 
     */

}

// definindo pacote
package DatasEmJava;

public class DatasEmJava_DateClass {

    public static void main(String[] args) {
        // criando um objeto do tipo Date
        java.util.Date date = new java.util.Date(); // instanciando um objeto do tipo Date

        // convertendo para sql.Date
        java.sql.Date sqlDate = new java.sql.Date(date.getTime()); // converte na data pura, sem hora, minuto e segundo
        System.out.println("convertendo para sql.Date >>> " + sqlDate);

        // imprimindo a data atual
        System.out.println(date);
    }

}

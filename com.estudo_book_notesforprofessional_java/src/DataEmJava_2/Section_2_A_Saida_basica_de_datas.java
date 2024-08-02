
package DataEmJava_2;

public class Section_2_A_Saida_basica_de_datas {

    //Using the following code with the format string yyyy/MM/dd hh:mm.ss, we will receive the following output
    public static void main(String[] args) {

        String formatString = "yyyy/MM/dd hh:mm.ss";
        
        //Date date = new Date(formatString);

        //java.util.Date date = new java.util.Date();
        System.out.printf("%1$s %2$tB %2$td, %2$tY", "Due date:", date);

    }
    
}

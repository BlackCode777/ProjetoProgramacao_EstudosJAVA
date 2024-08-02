import java.util.ArrayList;

public class App {

    static String[] list = { "Java", "C", "C++" };
    static String name = "Anderson";
    static int age = 10;

    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        System.out.println(createObjectArrayString(list, name, age));
    }

    // crio uma função que recebe um array de string e dois parametros/ do tipo
    // string e int
    public static ArrayList<String> createObjectArrayString(String[] list2, String name, int age) {
        ArrayList<String> newList = new ArrayList<String>(); // crio uma variavel do tipo ArrayList vazia para receber
                                                             // os valores do array dos parametros da função
        try {
            for (int i = 0; i < list2.length; i++) { // percorro o array de string e adiciono os valores na variavel do
                                                     // tipo ArrayList
                newList.add(list2[i]); // adiciono os valores do array de string na variavel do tipo ArrayList
            }
            newList.add(name); // adiciono os valores do parametro name na variavel do tipo ArrayList
            newList.add(String.valueOf(age)); // adiciono os valores do parametro age na variavel do tipo ArrayList
        } catch (Exception e) {
            System.out.println(e); // caso ocorra algum erro, imprimo o erro
        }
        return newList; // retorno a variavel do tipo ArrayList
    }

}

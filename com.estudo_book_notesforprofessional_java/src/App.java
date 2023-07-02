import java.util.ArrayList;

public class App {

    static String[] list = { "Java", "C", "C++" };
    static String name = "Anderson";
    static int age = 10;

    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        System.out.println(createObjectArrayString(list, name, age));
    }

    public static ArrayList<String> createObjectArrayString(String[] list2, String name, int age) {
        ArrayList<String> newList = new ArrayList<String>();
        try {
            for (int i = 0; i < list2.length; i++) {
                newList.add(list2[i]);
            }
            newList.add(name);
            newList.add(String.valueOf(age));
        } catch (Exception e) {
            System.out.println(e);
        }
        return newList;
    }

}

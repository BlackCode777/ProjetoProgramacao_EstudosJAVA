
/* criar o pacote */
package main.java.com.desafio_logica_helloworld;

public class DesafioExecutavelHelloWord {    
    
    public static void main(String[] args){

        try {
            
            /* Acesar os valores da classe mouse */
            DesafioLogicaClassMouse mouse = new DesafioLogicaClassMouse();
            mouse.cor = "branco";
            mouse.modelo = "M170";
            mouse.marca = "Logitech";
            mouse.tipo = "sem fio";
            mouse.conexao = "USB";

            DesafioLogicaClassMouse mouse2 = new DesafioLogicaClassMouse();
            mouse2.cor = "preto";
            mouse2.modelo = "M177";
            mouse2.marca = "AMD";
            mouse2.tipo = "Com fio";
            mouse2.conexao = "bluetooth";

            /* Variável boolean =  true / false */
            boolean ativoOuDesativo;

            /* Acessar os valores da classe teclado */
            if( mouse.modelo == "M170" ){

                /*Usando o for para percorrer a variável mouse2*/
                /* explicação do for: é usado para percorrer os tipos listas  */
                /*
                * O que são listas?
                Listas são variáveis que armazenam mais de um valor, exemplo
                lista de compras, lista de nomes, lista de números, etc.

                String[] listaDeCompras = ["Arroz", "Feijão", "Macarrão", "Carne", "Frango", "Peixe"];
                listaDeCompras[0] = "Arroz";
                listaDeCompras[1] = "Feijão";
                listaDeCompras[2] = "Macarrão";
                listaDeCompras[3] = "Carne";
                listaDeCompras[4] = "Frango";
                listaDeCompras[5] = "Peixe";
                
                */
                String[] listaDeCompras = {"Arroz", "Feijão", "Macarrão", "Carne", "Frango", "Peixe"};

                /* percorrendo a lista de compras */
                for( int a = 0; a < 6; a++ ){
                    System.out.println("Percorrendo a lista de compras");
                    System.out.println();
                    System.out.println("################# Atributos da lista de compras #################");
                    System.out.println("Item da lista de compras: " + listaDeCompras[a]);
                    System.out.println("##################################################################");
                }
                
                /* Percorrendo a lista de mouse2 */
                for( int i = 0; i < 1; i++ ){
                    System.out.println("Percorrendo a lista de mouse2");
                    System.out.println();
                    System.out.println("################# Atributos do mouse2 #################");
                    System.out.println("Cor do mouse2: " + mouse2.cor);
                    System.out.println("Modelo do mouse2: " + mouse2.modelo);
                    System.out.println("Marca do mouse2: " + mouse2.marca);
                    System.out.println("Tipo do mouse2: " + mouse2.tipo);
                    System.out.println("Conexão do mouse2: " + mouse2.conexao);
                    System.out.println("#######################################################");
                    System.out.println();
                }

                /* Condicioanl encadeada */
                ativoOuDesativo = true;
                if( ativoOuDesativo == true ){

                    if( mouse.cor == "branco" ){
                        System.out.println("Condicionais encadeadas - para ativoOuDesativo do Mouse");
                        System.out.println();
                        System.out.println("################# Atributos do mouse #################");
                        System.out.println("Cor do mouse: " + mouse.cor);
                        System.out.println("Modelo do mouse: " + mouse.modelo);
                        System.out.println("Marca do mouse: " + mouse.marca);
                        System.out.println("Tipo do mouse: " + mouse.tipo);
                        System.out.println("Conexão do mouse: " + mouse.conexao);
                        System.out.println("#######################################################");
                    } else {
                        System.out.println("Condicionais encadeadas - para ativoOuDesativo do Mouse - FIM");
                        System.out.println("O mouse está desativado e não é branco");
                    }
                    
                } else {
                    System.out.println("Condicionais encadeadas - para modelo do Mouse - FIM");
                    System.out.println("O modelo do mouse não é o M170");
                }
        } else {
            System.out.println("Else da camada externa");
        }

        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }

        //System.out.println( "Hello World !" );

    }


}

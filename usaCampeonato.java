import java.util.Scanner;

public class Menu{
  public static void main(String[] args){
    Scanner teclado = new Scanner(System.in);

    char opcao = '';

    do{
      swithc(opcao){
        case 'a': 
          //novo jogador
          println('a');
        case 'b':
          //remover jogador
          println('b');
        case 'c':
          // executa jogada
          println('c');
        case 'd':
          //mostra resultados
          println('d');
        case 'e':
          //gravar em arquivo
          println('e');
        case 'f':
          //ler arquivo
          println('f');
      }while(opcao != 'g');
    }
  }
}

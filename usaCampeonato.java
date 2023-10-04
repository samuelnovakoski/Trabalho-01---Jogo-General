import java.util.Scanner;

public class usaCampeonato{
  public static void main(String[] args){
    Campeonato campeonato = new Campeonato();
    menu(campeonato);
  }

  public static void menu(Campeonato campeonato) {
    char opcao;

    do {
      Scanner scanner = new Scanner(System.in);
   
      System.out.println(" ____________________________________________");
      System.out.println("|  a - Adicionar Jogador                     |");
      System.out.println("|  b - Remover Jogador                       |");
      System.out.println("|  c - Executar jogada                       |");
      System.out.println("|  d - Mostrar resultados da ultima rodada   |");
      System.out.println("|  e - Gravar dados do campeonato em arquivo |");
      System.out.println("|  f - Ler dados do campeonato em arquivo    |");
      System.out.println("|  g - Sair da aplicacao                     |");
      System.out.println("|____________________________________________|");
      System.out.print(" Escolha uma opcao: ");
      opcao = scanner.nextLine().charAt(0);

      switch(opcao){
          case 'a': 
            //novo jogador
            campeonato.incluirJogador();
            break;
          case 'b':
            //remover jogador
            campeonato.removerJogador();
            break;
          case 'c':
            // executa jogada
            campeonato.iniciarCampeonato();
            break;
          case 'd':
            //mostra cartela de resultados
            campeonato.mostrarCartela();
            break;
          case 'e':
            //gravar em arquivo
            campeonato.gravarEmArquivo(campeonato);
            break;
          case 'f':
            //ler arquivo
            campeonato.lerDoArquivo();
            break;
      }
    }while(opcao != 'g');
  }
}

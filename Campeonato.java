import java.util.Scanner;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class Campeonato implements Serializable {
    private Jogador[] jogador = new Jogador[10];
    private Scanner scanner = new Scanner(System.in);

    private int qntJogadores = 0;

    public void incluirJogador(){
        int cont = 0;
        for(int i = 0; i < jogador.length; i++)
            if(jogador[i] == null && cont == 0){
                jogador[i] = new Jogador();
                cont++;
            }
        
            for(int i = 1; i < jogador.length; i++)
                for(int j = 0; j < i; j++)
                    if(jogador[j] != null && jogador[i] != null)
                        if(jogador[j].getNome() == jogador[i].getNome())
                            jogador[i] = new Jogador();

            qntJogadores++;
            System.out.println("\nJogador adicionado com sucesso!\n");
    }

    public void removerJogador(){
        listaJogadores();
        System.out.println("Informe o nome do jogador que deseja remover: ");
        String nome = scanner.nextLine();
        int removido = 0;

        for(int i = 0; i < jogador.length; i++)
            if(jogador[i].getNome().equals(nome)){
                for(int j = i; j < jogador.length; j++)
                    jogador[j] = jogador[j + 1];
                
                jogador[jogador.length - 1] = null;
                qntJogadores--;
                removido++;

                System.out.println("\nJogador " + nome + " removido com sucesso!\n");
                break;
            }
        if(removido == 0)
            System.out.println("\nNao foi possivel encontrar o jogador " + nome);
    }

    public void listaJogadores(){
        for(int i = 0; i < jogador.length; i++)
            if(i < jogador.length - 1)
                System.out.print(jogador[i].getNome() + " - ");
            else
                System.out.println(jogador[i].getNome());
    }

    public void iniciarCampeonato(){
        for(int i = 0; i < 13; i++)
            for(int j = 0; j < jogador.length; j++){
                System.out.print("rolando dados para " + jogador[j].getNome() + "(" + jogador[j].getTipo() + ")...\n");
                jogador[j].jogarDados();
                jogador[j].escolherJogada();
            }
    }

    public void mostrarCartela(){
        System.out.println("\t----Cartela de resultados----");

        for(int i = 0; i < qntJogadores; i++)
            System.out.print("\t" + jogador[i].getNome());
        
        for(int i = 0; i < 13; i++){
            System.out.print("\n" + (i + 1));
            for(int j = 0; j < jogador.length; j++){
                if(jogador[j].getJogo().getJogadas()[i] != -1)
                    System.out.print("\t" + jogador[j].getJogo().getJogadas()[i]);
                else
                    System.out.print("\tx");
            }
        }

        System.out.println("\n-----------------------------");
        System.out.print("Total: ");
        for(int i = 0; i < jogador.length; i++)
            System.out.print("\t" + jogador[i].getJogo().getTotal() + "\n");
    }
}

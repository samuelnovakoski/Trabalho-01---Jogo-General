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
    private int qntJogadores = 0;
    private File file = new File("general.dat");

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
        Scanner scanner = new Scanner(System.in);
        listaJogadores();
        System.out.println("Informe o nome do jogador que deseja remover: ");
        String nome = scanner.nextLine();
        int removido = 0;

        for(int i = 0; i < qntJogadores; i++)
            if(jogador[i].getNome().equals(nome)){
                for(int j = i; j < qntJogadores; j++)
                    jogador[j] = jogador[j + 1];
                
                jogador[qntJogadores - 1] = null;
                qntJogadores--;
                removido++;

                System.out.println("\nJogador " + nome + " removido com sucesso!\n");
                break;
            }
        if(removido == 0)
            System.out.println("\nNao foi possivel encontrar o jogador " + nome);
    }

    public void listaJogadores(){
        for(int i = 0; i < qntJogadores; i++)
            if(i < qntJogadores - 1)
                System.out.print(jogador[i].getNome() + " - ");
            else
                System.out.println(jogador[i].getNome());
    }

    public void iniciarCampeonato(){
        if(qntJogadores > 0)
            for(int i = 0; i < 13; i++)
                for(int j = 0; j < qntJogadores; j++){
                    if(jogador[j].getTotalJogadas() < 13){
                        System.out.print("rolando dados para " + jogador[j].getNome() + "(" + jogador[j].getTipo() + ")...\n");
                        jogador[j].jogarDados();
                        jogador[j].escolherJogada();
                    }
                    else{
                        System.out.println("Jogador " + jogador[j].getNome() + " ja fez todas as suas jogadas!\n");
                    }
                }
        else
                System.out.println("\nInsira um jogador para poder jogar!");
    }

    public void mostrarCartela(){
        System.out.println("\t----Cartela de resultados----");

        for(int i = 0; i < qntJogadores; i++)
            System.out.print("\t" + jogador[i].getNome());
        
        for(int i = 0; i < 13; i++){
            System.out.print("\n" + (i + 1));
            for(int j = 0; j < qntJogadores; j++){
                if(jogador[j].getJogo().getJogadas()[i] != -1)
                    System.out.print("\t" + jogador[j].getJogo().getJogadas()[i]);
                else
                    System.out.print("\tx");
            }
        }

        System.out.println("\n-----------------------------");
        System.out.print("Total: ");
        for(int i = 0; i < qntJogadores; i++)
            System.out.print("\t" + jogador[i].getJogo().getTotal());
        System.out.println();

        if(qntJogadores > 1 && jogador[qntJogadores - 1].getTotalJogadas() >= 13){
            int vencedor = 0;
            boolean empate = false;
            for(int i = 0; i < qntJogadores; i++){
                if(jogador[vencedor].getJogo().getTotal() < jogador[i].getJogo().getTotal())
                    vencedor = i;
                else if(jogador[vencedor].getJogo().getTotal() == jogador[i].getJogo().getTotal() && vencedor != i)
                    empate = true;
            }   
            if(empate == false)
                System.out.println("\nParabens " + jogador[vencedor].getNome() + " voce ganhou com " + jogador[vencedor].getJogo().getTotal() + " pontos!!!");
            else
                System.out.println("Empate!");
        }
    }

    public void gravarEmArquivo(Campeonato camp){
        try{
            FileOutputStream fout = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fout);

            oos.writeObject(camp);

            oos.flush();
            oos.close();
            fout.close();

            System.out.println("Dados Gravados com sucesso!");
        }
        catch(Exception e){
            System.err.println("erro: " + e.toString());
        }
    }

    public void lerDoArquivo(){
        try{
            FileInputStream fin = new FileInputStream(file);
            ObjectInputStream oin = new ObjectInputStream(fin);

            Campeonato camp = (Campeonato) oin.readObject();

            oin.close();
            fin.close();

            for(int i = 0; i < 10; i++){
                this.jogador[i] = camp.jogador[i];
                this.qntJogadores = camp.qntJogadores;
            }

            System.out.println("Dados carregados com sucesso!");
        }catch(Exception e){
            System.err.println("erro: " + e.toString());
        }
    }
}

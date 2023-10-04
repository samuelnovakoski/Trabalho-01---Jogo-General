import java.util.Scanner;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class Campeonato implements Serializable{
    private Jogador[] jogadores = new Jogador[10];
    private JogoGeneral[] jogo = new JogoGeneral[10];
    private int qntJogadores = 0;
    private File file = new File("general.dat");

    public void incluirJogador(){   
        int cont = 0;
        for(int i = 0; i < jogadores.length; i++)
            if(jogadores[i] == null && cont == 0){
                jogo[i] = new JogoGeneral();
                jogadores[i] = new Jogador(jogo[i]);
                cont++;
            }

        for(int i = 1; i < jogadores.length; i++)
            for(int j = 0; j < i; j++)
                if(jogadores[j] != null && jogadores[i] != null)
                    if(jogadores[j].getNome() == jogadores[i].getNome()){
                        jogadores[i] = new Jogador(jogo[i]);
                        jogo[i] = new JogoGeneral();
                    }
                
        qntJogadores++;
        System.out.println("jogador adicionado com sucesso");
    }

    public void removerJogador(){
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Lista de jogadores: ");
            listaJogadores();
            System.out.print("Informe o nome do jogador que deseja remover: ");
            String nome = scanner.nextLine();
            int removido = 0;

            for(int i = 0; i < qntJogadores; i++)
                if(jogadores[i].getNome().equals(nome)){
                    for(int j = i; j < qntJogadores; j++)
                        jogadores[j] = jogadores[j + 1];
                    
                        jogadores[qntJogadores - 1] = null;
                        qntJogadores--;
                        removido++;

                    System.out.println("\nJogador removido com suceso!");
                    break;
                }
            
            if(removido == 0)
                System.out.println("Nao foi possivel encontrar o jogador " + nome);
        }
    }

    public void iniciarCampeonato(){
        for(int i = 0; i < 13; i++)
            for(int j = 0; j < qntJogadores; j++){
                jogadores[j].jogarDados();
                System.out.println(jogo[j].toString());
                jogadores[j].escolherJogada();
            }
    }

    public void listaJogadores(){
        for(int i = 0; i < qntJogadores; i++)
            if(i < qntJogadores - 1)
                System.out.print(jogadores[i].getNome() + " - ");
            else
                System.out.println(jogadores[i].getNome());
    }

    public void mostrarCartela(){
        System.out.println("\tCartela de resultados");

        for(int i = 0; i < qntJogadores; i++)
            System.out.print("\t" + jogadores[i].getNome());

        for(int i = 0; i < 13; i++){
            System.out.print("\n" + (i + 1));
            for(int j = 0; j < qntJogadores; j++){
                if(jogo[j].getJogadas()[i] != -1)
                    System.out.print("\t" + jogo[j].getJogadas()[i]);
                else
                    System.out.print("\tx");
            }
        }
        
        System.out.println("\n--------------------------------");
        System.out.print("Total: ");
        for(int i = 0; i < qntJogadores; i++)
            System.out.print("\t" + jogo[i].getTotal());
        System.out.println();
    }

    public void gravarEmArquivo(Campeonato camp){
        try{
            FileOutputStream fout = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fout);


            oos.writeObject(camp);
            // oos.writeObject(jogadores);
            // oos.writeObject(jogo);
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
            // Jogador[] jogadors = (Jogador[]) oin.readObject();
            // JogoGeneral[] jogoG = (JogoGeneral[]) oin.readObject();
            oin.close();
            fin.close();
            
            // for(int i = 0; i < qntJogadores; i++){
            //     jogadores[i] = jogadors[i];
            //     jogo[i] = jogoG[i];
            // }

            for(int i = 0; i < 10; i++){
                this.jogadores[i] = camp.jogadores[i];
                this.jogo[i] = camp.jogo[i];
                this.qntJogadores = camp.qntJogadores;
                //this.scanner = new Scanner(System.in);
                
            }

            //camp.mostrarCartela();

            System.out.println("Dados lidos com sucesso!");

        }catch(Exception e){
            System.err.println("erro: " + e.toString());
        }
    }

    public Jogador getJogadores(int x){
        return jogadores[x];
    }

    public JogoGeneral getJogoGeneral(int x){
        return jogo[x];
    }
}

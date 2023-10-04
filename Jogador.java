import java.util.Scanner;
import java.io.Serializable;

public class Jogador implements Serializable{
    private String nome;
    private String tipo;
    private JogoGeneral jogoG = new JogoGeneral();
    private transient Scanner scanner;
    
    public Jogador(JogoGeneral jogoG){
        this.jogoG = jogoG;
        infoJogador();
    }

    public void infoJogador(){
        System.out.print("Nome do jogador: ");
        setNome(scanner.nextLine());
        System.out.print("Tipo do jogador [H - Humano ou M - Maquina]: ");
        do{
            setTipo(scanner.nextLine());
            if(getTipo() != 'H' && getTipo() != 'M' && getTipo() != 'h' && getTipo() != 'm')
                System.out.print("Tipo de jogador invalido; \nPor favor insira um tipo valido (H - Humano ou M - Maquina)");
        }while(getTipo() != 'H' && getTipo() != 'M' && getTipo() != 'h' && getTipo() != 'm');
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getNome(){
        return nome;
    }

    public void setTipo(String tipo){
        this.tipo = tipo;
    }

    public char getTipo(){
        return tipo.charAt(0);
    }

    public void jogarDados(){
        System.out.println("rolando dados para " + getNome() + "(" + getTipo() + ")...");
        jogoG.rolarDados();
    }

    public String toString(){
        return "\nPara qual jogada deseja marcar: [1 - 13] " + getNome() + "?\n1 2 3 4 5 6 7(T) 8(Q) 9(F) 10(S+) 11(S-) 12(G) 13(X)";
    }

    public void escolherJogada(){
        if(getTipo() == 'H' || getTipo() == 'h'){
            System.out.println(toString());
            mostrarJogada();
            int x = scanner.nextInt();
            jogoG.validarJogada(x);
        }
        else
            maquina();
    }

    public void mostrarJogada(){
        for(int i = 0; i < 13 - 1 + 1; i++){
            if(jogoG.getJogadas()[i] == -1)
                System.out.print("- ");
            else
                System.out.print(jogoG.getJogadas()[i] + " ");
        }
        System.out.println();
    }

    public void maquina(){
        System.out.println(toString());
        mostrarJogada();
    }
}

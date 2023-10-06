import java.util.Scanner;
import java.io.Serializable;

public class Jogador implements Serializable{
    private String nome;
    private String tipo;
    private JogoGeneral jogoG = new JogoGeneral();
    private int jogadasExecutadas = 0;
    
    public Jogador(){
        setNome();
        setTipo();  
        this.jogoG = new JogoGeneral();  
    }

    public Jogador(String nome, String tipo){
        this.nome = nome;
        this.tipo = tipo;
    }

    public void setNome(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Insira o nome do jogador: ");
        this.nome = scanner.nextLine();
    }

    public String getNome(){
        return this.nome;
    }

    public void setTipo(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Insira o tipo do jogador: [H - Humano | M - Maquina] ");
        validarTipo(scanner.nextLine());
    }

    public void validarTipo(String tipo){
        if(tipo.charAt(0) != 'H'  && tipo.charAt(0) != 'h' && tipo.charAt(0) != 'M' && tipo.charAt(0) != 'm')
            System.out.print("Tipo invalido por favor insira um tipo valido");
        else
            this.tipo = tipo;
    }

    public char getTipo(){
        return tipo.charAt(0);
    }

    public JogoGeneral getJogo(){
        return jogoG;
    }

    public int getTotalJogadas(){
        return jogadasExecutadas;
    }

    public void jogarDados(){
        jogoG.rolarDados();
        System.out.println(jogoG.toString());
    }

    public String toString(){
        String s = "\nPara qual jogada deseja marcar: [1 - 13] " + getNome() + "?\n1 2 3 4 5 6 7(T) 8(Q) 9(F) 10(S+) 11(S-) 12(G) 13(X)\n";

        for(int i = 0; i < 13; i++){
            if(jogoG.getJogadas()[i] == -1)
                s += "- ";
            else
                s += jogoG.getJogadas()[i] + " ";
        }
        return s;
    }

    public void escolherJogada(){

        Scanner scanner = new Scanner(System.in);
        if(getTipo() == 'H' || getTipo() == 'h'){
            System.out.println(toString());
            int x = scanner.nextInt();
            jogoG.validarJogada(x);
            jogadasExecutadas++;
        }
        else{
            for(int i = 1; i <= 13; i++){
                System.out.println(toString());
                jogoG.validarJogada(i);
                jogadasExecutadas++;
            }
        }

    }
}

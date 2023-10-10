import java.util.Scanner;
import java.io.Serializable;

public class Jogador implements Serializable{
    //atributos
    private String nome;
    private String tipo;
    private JogoGeneral jogoG = new JogoGeneral();
    private int jogadasExecutadas = 0;
    
    //métodos
    //construtor padrão
    public Jogador(){
        setNome();
        setTipo();  
        this.jogoG = new JogoGeneral();  
    }

    //construtor sobrecarregado com nome e tipo
    public Jogador(String nome, String tipo){
        this.nome = nome;
        this.tipo = tipo;
    }

    //atribui nome ao jogador
    public void setNome(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Insira o nome do jogador: ");
        this.nome = scanner.nextLine();
    }

    //retorna o nome
    public String getNome(){
        return this.nome;
    }

    //atribui tipo ao jogador
    public void setTipo(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Insira o tipo do jogador: [H - Humano | M - Maquina] ");
        validarTipo(scanner.nextLine());
    }

    //valida o tipo escolhido entre humano e máquina
    public void validarTipo(String tipo){
        Scanner scanner = new Scanner(System.in);
        do{
            if(tipo.charAt(0) != 'H'  && tipo.charAt(0) != 'h' && tipo.charAt(0) != 'M' && tipo.charAt(0) != 'm'){
                System.out.print("Tipo invalido por favor insira um tipo valido");
                tipo = scanner.nextLine();
            }
            else
                this.tipo = tipo;
        }while(tipo.charAt(0) != 'H'  && tipo.charAt(0) != 'h' && tipo.charAt(0) != 'M' && tipo.charAt(0) != 'm');
    }

    //retorna o tipo
    public char getTipo(){
        return tipo.charAt(0);
    }

    //retorna o JogoGeneral
    public JogoGeneral getJogo(){
        return jogoG;
    }

    //retorna o total de jogadas já feitas
    public int getTotalJogadas(){
        return jogadasExecutadas;
    }

    //joga os dados e mostra na tela
    public void jogarDados(){
        jogoG.rolarDados();
        System.out.println(jogoG.toString());
    }

    public String toString(){
        String s = "";

        if(getTipo() == 'H' || getTipo() == 'h')
            s += "\nPara qual jogada deseja marcar: [1 - 13] " + getNome() + "?\n1\t2\t3\t4\t5\t6\t7(T)\t8(Q)\t9(F)\t10(S+)\t11(S-)\t12(G)\t13(X)\n";
        else
            s += "\nJogada escolhida por " + getNome() + "(" + getTipo() + ") [1 - 13]: " + jogadasExecutadas + "\n";

        for(int i = 0; i < 13; i++){
            if(jogoG.getJogadas()[i] == -1)
                s += "-\t";
            else
                s += jogoG.getJogadas()[i] + "\t";
        }
        return s;
    }
    
    //verifica o tipo do jogador, se for do tipo humano ele escolhe a jogada, se não chama a máquina
    public void escolherJogada(){
        Scanner scanner = new Scanner(System.in);
        if(getTipo() == 'H' || getTipo() == 'h'){
            System.out.println(toString());
            int x = scanner.nextInt();
            jogoG.validarJogada(x);
            jogadasExecutadas++;
        }
        else
            maquina();
    }

    //escolhe a jogada para o jogador tipo máquina
    public void maquina(){
        jogadasExecutadas++;
        System.out.println(toString());
        jogoG.validarJogada(jogadasExecutadas);
    }
}

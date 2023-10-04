import java.util.Scanner;

public class Jogador {
    private String nome;
    private String tipo;
    private JogoGeneral jogoG = new JogoGeneral();
    private static Scanner scanner = new Scanner(System.in);
    
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
        System.out.print("Insira o nome do jogador: ");
        this.nome = scanner.nextLine();
    }

    public String getNome(){
        return this.nome;
    }

    public void setTipo(){
        System.out.print("Insira o tipo do jogador: [H - Humano | M - Maquina] ");
        validarTipo(scanner.nextLine());
    }

    public void validarTipo(String tipo){
        if(tipo.charAt(0) != 'H' && tipo.charAt(0) != 'M')
            System.out.print("Tipo invalido por favor insira um tipo valido");
        else
            this.tipo = tipo;
    }

    public char getTipo(){
        return tipo.toUpperCase().charAt(0);
    }

    public JogoGeneral getJogo(){
        return jogoG;
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
        if(getTipo() == 'H'){
            System.out.println(toString());
            int x;
            do{
                x = scanner.nextInt();
                jogoG.validarJogada(x);
            }while(x < 1 || x > 13 || jogoG.getJogadas()[x - 1] != -1);
        }
    }
}

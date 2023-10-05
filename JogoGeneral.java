import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.Arrays;

public class JogoGeneral implements Serializable{
    private Dado dados[] = new Dado[5];
    private int jogadas[] = new int[13];
    private int total = 0;
    private int resultado = 0;
    
    public JogoGeneral(){
        for(int i = 0; i < 13; i++)
            jogadas[i] = -1;
    }

    public void rolarDados(){
        for(int i = 0; i < dados.length; i++){
            dados[i] = new Dado();
            dados[i].roll();
        }
    }

    public String toString(){
        String s = "";

        for(int i = 0; i < dados.length; i++){
            if(i < dados.length - 1)
                s += dados[i].getSideUp() + "-";
            else
                s += dados[i].getSideUp();
        }
        
        return "valores obtidos: " + s;
    }

    public int[] getJogadas(){
        return jogadas;
    }

    public Dado[] getDados(){
        return dados;
    }

    public int getTotal(){
        total = 0;
        for(int i = 0; i < 13; i++)
            if(getJogadas()[i] != -1)
                total += getJogadas()[i];
        return total;
    }

    public void validarJogada(int x){
        Scanner scanner = new Scanner(System.in);
        
        while(x < 1 || x > 13 || jogadas[x - 1] != -1){
            if(x < 1 || x > 13)
                System.out.println("jogada invalida por favor insira uma jogada valida");
            else if(jogadas[x - 1] != -1)
                System.out.println("A essa jogada já foi feita, por favor insira uma jogada que ainda não foi realizada");
            
            x = scanner.nextInt();
        };
        pontuarJogada(x);
    }

    private void pontuarJogada(int x) {
        resultado = 0;
        switch(x){
            case 1:
                for(int i = 0; i < dados.length; i++)
                    if(dados[i].getSideUp() == 1)
                        resultado += dados[i].getSideUp();
                break;
            case 2:
                for(int i = 0; i < dados.length; i++)
                    if(dados[i].getSideUp() == 2)
                        resultado += dados[i].getSideUp();
                break;
            case 3:
                for(int i = 0; i < dados.length; i++)
                    if(dados[i].getSideUp() == 3)
                        resultado += dados[i].getSideUp();
                break;
            case 4:
                for(int i = 0; i < dados.length; i++)
                    if(dados[i].getSideUp() == 4)
                        resultado += dados[i].getSideUp();
                break;
            case 5:
                for(int i = 0; i < dados.length; i++)
                    if(dados[i].getSideUp() == 5)
                        resultado += dados[i].getSideUp();
                break;
            case 6:
                for(int i = 0; i < dados.length; i++)
                    if(dados[i].getSideUp() == 6)
                        resultado += dados[i].getSideUp();
                break;
            case 7:
                boolean trinca = false;
                for(int i = 1; i <= 6; i++){
                    int contagem = 0;

                    for(int j = 0; j < dados.length; j++)
                        if(dados[j].getSideUp() == i)
                            contagem++;
                    
                    if(contagem >= 3)
                        trinca = true;
                }
                if(trinca == true)
                    for(int i = 0; i < dados.length; i++)
                        resultado += dados[i].getSideUp();

                break;
            case 8:
                boolean quadra = false;
                
                for(int i = 1; i <= 6; i++){
                    int contagem = 0;
                    for(int j = 0; j < dados.length; j++)
                        if(dados[j].getSideUp() == i)
                            contagem++;
                    if(contagem >= 4)
                        quadra = true;
                }

                if(quadra == true)
                        for(int i = 0; i < dados.length; i++)
                            resultado += dados[i].getSideUp();
                break;
            case 9:
                trinca = false;
                boolean par = false;

                for(int i = 1; i <= 6; i++){
                    int contagem = 0;

                    for(int j = 0; j < dados.length; j++)
                        if(dados[j].getSideUp() == i)
                            contagem++;
                    
                    if(contagem == 3)
                        trinca = true;
                    else if(contagem == 2)
                        par = true;
                }

                if(trinca == true && par == true)
                    resultado = 25;
                break;
            case 10:
                ordenarDados();
                boolean sequencia = true;

                for(int i = 0; i < dados.length - 1; i++){
                    if(dados[i].getSideUp() + 1 != dados[i + 1].getSideUp()) 
                        sequencia = false;
                }
                
                if(sequencia == true)
                    if(dados[0].getSideUp() == 1)
                        resultado = 40;
                
                break;
            case 11:
                ordenarDados();
                sequencia = true;
                for(int i = 0; i < dados.length - 1; i++){
                    if(dados[i].getSideUp() + 1 != dados[i + 1].getSideUp()) 
                        sequencia = false;
                }
                
                if(sequencia == true)
                    if(dados[0].getSideUp() == 1)
                        resultado = 40;
                
                break;
            case 12:
                boolean general = false;
                for(int i = 0; i < 6; i++){
                    int contagem = 0; 

                    for(int j = 0; j < dados.length; j++)
                        if(dados[j].getSideUp() == i)
                            contagem++;
                    if(contagem == 5)
                        general = true;
                }

                if(general)
                    resultado = 50;
                break;
            case 13:
                for(int i = 0; i < dados.length; i++){
                    resultado += dados[i].getSideUp();
                }
                break;
            default:
                System.out.println("Opcao invalida!");
        }
        if(resultado == 0)
            System.out.println("seus valores nao cumprem o requisito para esta jogada!\n");
        jogadas[x - 1] = resultado;
    }

    public void ordenarDados(){
        for(int i = 0; i < dados.length; i++)
            for(int j = 0; j < dados.length - 1; j++)
                if(dados[j].getSideUp() > dados[j + 1].getSideUp()){
                    Dado aux = dados[j];
                    dados[j] = dados[j + 1];
                    dados[j + 1] = aux;
                }
    }
}

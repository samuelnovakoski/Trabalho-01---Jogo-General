import java.io.Serializable;
import java.util.Scanner;

public class JogoGeneral implements Serializable{
    //atributos
    private Dado dados[] = new Dado[5];
    private int jogadas[] = new int[13];
    private int total = 0;
    private int resultado = 0;
    
    //métodos
    //construtor padrão
    public JogoGeneral(){
        for(int i = 0; i < 13; i++)
            jogadas[i] = -1;
    }

    //rolagem de dados para o array 
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

    //retorna as jogadas
    public int[] getJogadas(){
        return jogadas;
    }

    //retorna os dados
    public Dado[] getDados(){
        return dados;
    }

    //retorna o total de pontos feitos
    public int getTotal(){
        total = 0;
        for(int i = 0; i < 13; i++)
            if(getJogadas()[i] != -1)
                total += getJogadas()[i];
        return total;
    }

    //valida a jogada escolhida (se já foi feita ou não)
    public void validarJogada(int x){
        Scanner scanner = new Scanner(System.in);
        
        while(x < 1 || x > 13 || jogadas[x - 1] != -1){
            if(x < 1 || x > 13)
                System.out.println("jogada invalida por favor insira uma jogada valida [1 - 13]");
            else if(jogadas[x - 1] != -1)
                System.out.println("Essa jogada ja foi feita, por favor insira uma jogada que ainda nao foi realizada");
            
            x = scanner.nextInt();
        }
        pontuarJogada(x);
    }

    //pontua a jogada escolhida de acordo com as regras de cada tipo
    public void pontuarJogada(int x) {
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
                int highSeq = 0;
                
                if(dados[0].getSideUp() == 2)
                    highSeq++;

                for(int i = 0; i < dados.length - 1; i++){
                    if(dados[i].getSideUp() == (dados[i+1].getSideUp() - 1)) 
                        highSeq++;
                }
                
                if(highSeq == 5)
                    resultado = 30;
                
                break;
            case 11:
                ordenarDados();
                int lowSeq = 0;

                if(dados[0].getSideUp() == 1)
                    lowSeq++;
                
                for(int i = 0; i < dados.length - 1; i++){
                    if(dados[i].getSideUp() == (dados[i+1].getSideUp() - 1))
                        lowSeq++;
                }

                if(lowSeq == 5)
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

    //ordena os dados
    public void ordenarDados(){
        int aux;
        for(int i = 0; i < dados.length; i++)
            for(int j = 0; j < dados.length - 1; j++)
                if(this.dados[j].getSideUp() > this.dados[j + 1].getSideUp()){
                    aux = this.dados[j].getSideUp();
                    this.dados[j].setSideUp(this.dados[j+1].getSideUp());
                    this.dados[j + 1].setSideUp(aux);
                }
    }
}

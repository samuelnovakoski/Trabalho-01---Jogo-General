public class JogoGeneral {
    private Dado dados[] = new Dado[5];
    private int jogadas[] = new int[13];
    private int total = 0;
    private int resultado = 0;
    
    public JogoGeneral(){
        for(int i = 0; i < jogadas.length; i++)
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
        for(int i = 0; i < 13; i++)
            if(getJogadas()[i] != -1)
                total += getJogadas()[i];
        return total;
    }

    public void validarJogada(int x){
        if(x < 1 || x > 13)
            System.out.println("jogada invalida por favor insira uma jogada valida");
        else if(jogadas[x - 1] != -1)
            System.out.println("A essa jogada já foi feita, por favor insira uma jogada que ainda não foi realizada");
        else
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
                for(int i = 0; i < dados.length; i++){
                    int trinca = 0;

                    for(int j = 0; j < dados.length; j++)
                        if(dados[i].getSideUp() == dados[j].getSideUp())
                            trinca++;
                    if(trinca >= 3)
                        for(i = 0; i < dados.length; i++)
                            resultado += dados[i].getSideUp();
                }
                break;
            case 8:
                for(int i = 0; i < dados.length; i++){
                    int quadra = 0;

                    for(int j = 0; j < dados.length; j++)
                        if(dados[i].getSideUp() == dados[j].getSideUp())
                            quadra++;
                    if(quadra >= 4)
                        for(i = 0; i < dados.length; i++)
                            resultado += dados[i].getSideUp();
                }
                break;
            case 9:
                for(int i = 0; i < dados.length; i++){
                    int trinca = 0;
                    int par = 0;
                    int d1;

                    for(int j = 0; j < dados.length; j++)
                        if(dados[i].getSideUp() == dados[j].getSideUp()){
                            trinca++;
                            d1 = dados[i].getSideUp();
                            if(dados[i].getSideUp() != d1)
                                par++;
                        }
                    if(trinca == 3 && par == 2)
                        resultado = 25;
                }
                break;
            case 10:
                int sequencia = 0;
                for(int i = 0; i < dados.length - 1; i++)
                    if(dados[i].getSideUp() + 1 != dados[i + 1].getSideUp()) 
                        sequencia = 1;
                
                if(sequencia == 0)
                    if(dados[0].getSideUp() == 2)
                        resultado = 30;

                break;
            case 11:
                sequencia = 0;
                for(int i = 0; i < dados.length - 1; i++)
                    if(dados[i].getSideUp() + 1 != dados[i + 1].getSideUp()) 
                        sequencia = 1;
                
                if(sequencia == 0)
                    if(dados[0].getSideUp() == 1)
                        resultado = 40;
                
                break;
            case 12:
                for(int i = 0; i < dados.length; i++){
                    int general = 0;

                    for(int j = 0; j < dados.length; j++)
                        if(dados[i].getSideUp() == dados[j].getSideUp())
                            general++;
                    if(general == 5)
                        resultado = 50;
                }
                break;
            case 13:
                for(int i = 0; i < dados.length; i++){
                    resultado += dados[i].getSideUp();
                }
                break;

        }
        if(resultado == 0)
            System.out.println("seus valores nao cumprem o requisito para esta jogada!\n");
        jogadas[x - 1] = resultado;
    }
}


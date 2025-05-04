package boardgame;

public abstract class Peca {

    protected Posicao posicao;
    private Tabuleiro tabuleiro;

    public Peca(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
        posicao = null;
    }

    public abstract boolean[][] movimentosPossiveis();

    public boolean movimentoPossivel (Posicao posicao){
        return movimentosPossiveis()[posicao.getLinha()][posicao.getColuna()];  //metodo concreto que utiliza um metodo abstrato
    }

    public boolean existeMovimentoPossivel(){                                   //chama o metodo abstrato para procurar na matriz se há
        boolean[][] matriz = movimentosPossiveis();                             //pelo menos uma posição verdadeira
        for(int i = 0; i < matriz.length; i++){
            for(int j = 0; j < matriz.length; j++){
                if(matriz[i][j]){
                    return true;
                }
            }
        }
        return false;
    }

    protected Tabuleiro getTabuleiro() {
        return tabuleiro;
    }
}

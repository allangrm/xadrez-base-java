package xadrez;

import boardgame.Peca;
import boardgame.Posicao;
import boardgame.Tabuleiro;

public abstract class PecaXadrez extends Peca{

    private Cor cor;
    private int movimentosCount;

    public PecaXadrez(Tabuleiro tabuleiro, Cor cor) {
        super(tabuleiro);
        this.cor = cor;
    }

    public PosicaoXadrez getPosicaoXadrez(){
        return  PosicaoXadrez.fromPosicao(posicao);
    }

    public void incrementarMoveCount(){
        movimentosCount++;
    }

    public void decrementarMoveCount(){
        movimentosCount--;
    }

    protected boolean temPecaAdversaria(Posicao posicao){
         PecaXadrez p = (PecaXadrez) getTabuleiro().peca(posicao);  //downcast
        return p != null && p.getCor() != cor;                      //checa se nao é nulo e se a cor é diferente
    }

    public Cor getCor() {
        return cor;
    }

    public int getMovimentosCount() {
        return movimentosCount;
    }
}

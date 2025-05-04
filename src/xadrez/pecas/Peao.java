package xadrez.pecas;

import boardgame.Posicao;
import boardgame.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Peao extends PecaXadrez {
    public Peao(Tabuleiro tabuleiro, Cor cor) {
        super(tabuleiro, cor);
    }

    @Override
    public String toString(){
        return "P";
    }

    private boolean podeMover(Posicao posicao){
        PecaXadrez p = (PecaXadrez)getTabuleiro().peca(posicao);
        return p == null || p.getCor() != getCor();
    }

    @Override
    public boolean[][] movimentosPossiveis() {
        boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao p = new Posicao(0,0);

        return matriz;
    }
}

package xadrez.pecas;

import boardgame.Posicao;
import boardgame.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Cavalo extends PecaXadrez {
    public Cavalo(Tabuleiro tabuleiro, Cor cor) {
        super(tabuleiro, cor);
    }

    @Override
    public String toString() {
        return "C";
    }

    private boolean podeMover(Posicao posicao) {
        PecaXadrez p = (PecaXadrez) getTabuleiro().peca(posicao);
        return p == null || p.getCor() != getCor();
    }

    @Override
    public boolean[][] movimentosPossiveis() {
        boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao p = new Posicao(0, 0);

        p.setValor(posicao.getLinha() - 1, posicao.getColuna() - 2);
        if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }

        p.setValor(posicao.getLinha() - 2, posicao.getColuna() - 1);
        if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }

        p.setValor(posicao.getLinha() - 2, posicao.getColuna() + 1);
        if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }

        p.setValor(posicao.getLinha() - 1, posicao.getColuna() + 2);
        if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }

        p.setValor(posicao.getLinha() + 1, posicao.getColuna() + 2);
        if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }


        p.setValor(posicao.getLinha() + 2, posicao.getColuna() + 1);
        if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }

        p.setValor(posicao.getLinha() + 2, posicao.getColuna() - 1);
        if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }

        p.setValor(posicao.getLinha() + 1, posicao.getColuna() - 2);
        if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }


        return matriz;
    }
}


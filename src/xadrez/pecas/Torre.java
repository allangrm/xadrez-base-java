package xadrez.pecas;

import boardgame.Posicao;
import boardgame.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Torre extends PecaXadrez {
    public Torre(Tabuleiro tabuleiro, Cor cor) {
        super(tabuleiro, cor);
    }

    @Override
    public String toString(){
        return "T";
    }

    @Override
    public boolean[][] movimentosPossiveis() {
        boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()]; //todas as posições estão como falsas

        Posicao p = new Posicao(0,0);

        //acima
        p.setValor(posicao.getLinha() - 1, posicao.getColuna());
        while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().posicaoOcupada(p)) { //checa se a posição existe e não está ocupada
            matriz[p.getLinha()][p.getColuna()] = true;
            p.setLinha(p.getLinha() - 1);
        }
        if(getTabuleiro().posicaoExiste(p) && temPecaAdversaria(p)){
            matriz[p.getLinha()][p.getColuna()] = true;
        }

        //abaixo
        p.setValor(posicao.getLinha() + 1, posicao.getColuna());
        while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().posicaoOcupada(p)) { //checa se a posição existe e não está ocupada
            matriz[p.getLinha()][p.getColuna()] = true;
            p.setLinha(p.getLinha() + 1);
        }
        if(getTabuleiro().posicaoExiste(p) && temPecaAdversaria(p)){
            matriz[p.getLinha()][p.getColuna()] = true;
        }

        //esquerda
        p.setValor(posicao.getLinha(), posicao.getColuna() - 1);
        while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().posicaoOcupada(p)) { //checa se a posição existe e não está ocupada
            matriz[p.getLinha()][p.getColuna()] = true;
            p.setColuna(p.getColuna() - 1);
        }
        if(getTabuleiro().posicaoExiste(p) && temPecaAdversaria(p)){
            matriz[p.getLinha()][p.getColuna()] = true;
        }

        //direita
        p.setValor(posicao.getLinha(), posicao.getColuna() + 1);
        while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().posicaoOcupada(p)) { //checa se a posição existe e não está ocupada
            matriz[p.getLinha()][p.getColuna()] = true;
            p.setColuna(p.getColuna() + 1);
        }
        if(getTabuleiro().posicaoExiste(p) && temPecaAdversaria(p)){
            matriz[p.getLinha()][p.getColuna()] = true;
        }

        return matriz;
    }
}

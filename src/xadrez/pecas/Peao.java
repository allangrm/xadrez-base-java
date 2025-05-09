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

        if(getCor() == Cor.BRANCO){
            p.setValor(posicao.getLinha() - 1 , posicao.getColuna());
            if(getTabuleiro().posicaoExiste(p) && !getTabuleiro().posicaoOcupada(p)){
                matriz[p.getLinha()][p.getColuna()] = true;
            }
            p.setValor(posicao.getLinha() - 2 , posicao.getColuna());
            Posicao p2 = new Posicao(posicao.getLinha() - 1 , posicao.getColuna());
            if(getTabuleiro().posicaoExiste(p) && !getTabuleiro().posicaoOcupada(p) && getTabuleiro().posicaoExiste(p2) && !getTabuleiro().posicaoOcupada(p2) && getMovimentosCount() == 0){
                matriz[p.getLinha()][p.getColuna()] = true;
            }
            p.setValor(posicao.getLinha() - 1 , posicao.getColuna() - 1);
            if(getTabuleiro().posicaoExiste(p) && temPecaAdversaria(p)){
                matriz[p.getLinha()][p.getColuna()] = true;
            }
            p.setValor(posicao.getLinha() - 1 , posicao.getColuna() + 1);
            if(getTabuleiro().posicaoExiste(p) && temPecaAdversaria(p)){
                matriz[p.getLinha()][p.getColuna()] = true;
            }
        }else {p.setValor(posicao.getLinha() + 1 , posicao.getColuna());
            if(getTabuleiro().posicaoExiste(p) && !getTabuleiro().posicaoOcupada(p)){
                matriz[p.getLinha()][p.getColuna()] = true;
            }
            p.setValor(posicao.getLinha() + 2 , posicao.getColuna());
            Posicao p2 = new Posicao(posicao.getLinha() + 1 , posicao.getColuna());
            if(getTabuleiro().posicaoExiste(p) && !getTabuleiro().posicaoOcupada(p) && getTabuleiro().posicaoExiste(p2) && !getTabuleiro().posicaoOcupada(p2) && getMovimentosCount() == 0){
                matriz[p.getLinha()][p.getColuna()] = true;
            }
            p.setValor(posicao.getLinha() + 1 , posicao.getColuna() - 1);
            if(getTabuleiro().posicaoExiste(p) && temPecaAdversaria(p)){
                matriz[p.getLinha()][p.getColuna()] = true;
            }
            p.setValor(posicao.getLinha() + 1 , posicao.getColuna() + 1);
            if(getTabuleiro().posicaoExiste(p) && temPecaAdversaria(p)){
                matriz[p.getLinha()][p.getColuna()] = true;
            }

        }

        return matriz;
    }
}

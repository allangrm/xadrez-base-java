package xadrez;

import boardgame.Posicao;
import boardgame.Tabuleiro;
import xadrez.pecas.*;

public class PartidaXadrez {

    private Tabuleiro tabuleiro;

    public PartidaXadrez(){
        tabuleiro = new Tabuleiro(8,8);
        setupInicial();
    }

    public PecaXadrez[][] getPecas(){   //seta as pecas que serão usadas pelo tabuleiro
        PecaXadrez[][] matriz = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
        for (int i = 0; i < tabuleiro.getLinhas(); i++){
            for (int j = 0; j < tabuleiro.getColunas(); j++){
                matriz[i][j] = (PecaXadrez) tabuleiro.peca(i, j);   //downcasting - pesquisar
                                                                    //interpreta como uma peça de xadrez e não uma peça comum
            }
        }
        return matriz;
    }

    private void colocarNovaPeca(char coluna, int linha, PecaXadrez peca){ //colocar peças a partir da coordenada do xadrez
        tabuleiro.colocarPeca(peca, new PosicaoXadrez(coluna,linha).toPosicao());
    }

    private void setupInicial(){        //colocar pecas no tabuleiro
        for(int i = 0; i < tabuleiro.getColunas(); i++){
            tabuleiro.colocarPeca(new Peao(tabuleiro, Cor.PRETO), new Posicao(1,i)); //falta mudar os peoes
        }
        for(int i = 0; i < tabuleiro.getColunas(); i++){
            tabuleiro.colocarPeca(new Peao(tabuleiro, Cor.BRANCO), new Posicao(6,i));
        }
        colocarNovaPeca('c', 8, new Bispo(tabuleiro, Cor.PRETO));
        colocarNovaPeca('f', 8, new Bispo(tabuleiro, Cor.PRETO));
        colocarNovaPeca('c', 1, new Bispo(tabuleiro, Cor.BRANCO));
        colocarNovaPeca('f', 1, new Bispo(tabuleiro, Cor.BRANCO));
        colocarNovaPeca('b', 8, new Cavalo(tabuleiro, Cor.PRETO));
        colocarNovaPeca('g', 8, new Cavalo(tabuleiro, Cor.PRETO));
        colocarNovaPeca('b', 1, new Cavalo(tabuleiro, Cor.BRANCO));
        colocarNovaPeca('g', 1, new Cavalo(tabuleiro, Cor.BRANCO));
        colocarNovaPeca('a', 8, new Torre(tabuleiro, Cor.PRETO));
        colocarNovaPeca('h', 8, new Torre(tabuleiro, Cor.PRETO));
        colocarNovaPeca('a', 1,new Torre(tabuleiro, Cor.BRANCO));
        colocarNovaPeca('h', 1, new Torre(tabuleiro, Cor.BRANCO));
        colocarNovaPeca('e', 8, new Rei(tabuleiro, Cor.PRETO));
        colocarNovaPeca('e', 1, new Rei(tabuleiro, Cor.BRANCO));
        colocarNovaPeca('d', 8, new Dama(tabuleiro, Cor.PRETO));
        colocarNovaPeca('d', 1, new Dama(tabuleiro, Cor.BRANCO));

    }
}

package xadrez;

import boardgame.Peca;
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

    private void colocarNovaPeca(char coluna, int linha, PecaXadrez peca){                      //colocar peças a partir da coordenada do xadrez
        tabuleiro.colocarPeca(peca, new PosicaoXadrez(coluna,linha).toPosicao());
    }

    public PecaXadrez performarMovimentoPeca(PosicaoXadrez posicaoOrigem, PosicaoXadrez posicaoDestino){
        Posicao origem = posicaoOrigem.toPosicao();                                             //converte as duas posições para uma posição da matriz
        Posicao destino = posicaoDestino.toPosicao();
        validarPosicaoOrigem(origem);
        validarPosicaoDestino(origem, destino);
        Peca pecaCapturada = fazerMovimento(origem, destino);
        return (PecaXadrez)pecaCapturada;
    }

    private Peca fazerMovimento(Posicao origem, Posicao destino){
        Peca p = tabuleiro.removerPeca(origem);                     //remove a peça da origem para movimentá-la
        Peca pecaCapturada = tabuleiro.removerPeca(destino);        //remove a peça da posição de destino(caso de captura) caso haja peça no destino
        tabuleiro.colocarPeca(p, destino);                          //coloca a peca instanciada(selecionada para movimento) na posição designada
        return pecaCapturada;
    }

    private void validarPosicaoOrigem(Posicao posicao){             //verifica se tem peça na posição informada
        if (!tabuleiro.posicaoOcupada(posicao)){
            throw new XadrezException("Não há nenhuma peça nessa posição");
        }
        if(!tabuleiro.peca(posicao).existeMovimentoPossivel()){
            throw new XadrezException("Não existe movimentos possíveis para a peça escolhida");
        }
    }

    private void validarPosicaoDestino(Posicao origem, Posicao destino) {
        if (!tabuleiro.peca(origem).movimentoPossivel(destino)) {                                       //se para a peça de origem a posição de destino não é um movimento
             throw new XadrezException("A peça escolhida não pode se mover para a posição de destino"); //possível, então não pode ir para lá
        }
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

package xadrez;

import boardgame.Peca;
import boardgame.Posicao;
import boardgame.Tabuleiro;
import xadrez.pecas.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PartidaXadrez {

    private int turno;
    private Cor jogadorAtual;
    private Tabuleiro tabuleiro;
    private boolean xeque;

    private List<Peca> pecasNoTabuleiro = new ArrayList<>();
    private List<Peca> pecasCapturadas = new ArrayList<>();


    public PartidaXadrez(){
        tabuleiro = new Tabuleiro(8,8);
        turno = 1;
        jogadorAtual = Cor.BRANCO;
        xeque = false;
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
        pecasNoTabuleiro.add(peca);                                                             //adiciona na lista de pecas que estao atualmente no tabuleiro
    }

    public boolean[][] movimentosPossiveis(PosicaoXadrez posicaoOrigem){
        Posicao posicao = posicaoOrigem.toPosicao();
        validarPosicaoOrigem(posicao);
        return tabuleiro.peca(posicao).movimentosPossiveis();
    }

    public PecaXadrez performarMovimentoPeca(PosicaoXadrez posicaoOrigem, PosicaoXadrez posicaoDestino){
        Posicao origem = posicaoOrigem.toPosicao();                                             //converte as duas posições para uma posição da matriz
        Posicao destino = posicaoDestino.toPosicao();
        validarPosicaoOrigem(origem);
        validarPosicaoDestino(origem, destino);
        Peca pecaCapturada = fazerMovimento(origem, destino);

        if(testarXeque(jogadorAtual)){
            desfazerMovimento(origem, destino, pecaCapturada);
            throw new XadrezException("Você não pode se colocar em xeque!");
        }
        xeque = (testarXeque(oponente(jogadorAtual))) ? true : false; //se o testar xeque do oponente do jogador atual for true então a partida está em xeque
        proximoTurno();
        return (PecaXadrez)pecaCapturada;
    }

    private Peca fazerMovimento(Posicao origem, Posicao destino){
        Peca p = tabuleiro.removerPeca(origem);                     //remove a peça da origem para movimentá-la
        Peca pecaCapturada = tabuleiro.removerPeca(destino);        //remove a peça da posição de destino(caso de captura) caso haja peça no destino
        tabuleiro.colocarPeca(p, destino);                          //coloca a peca instanciada(selecionada para movimento) na posição designada

        if (pecaCapturada != null){                                 //caso o movimento feito capture uma peça as listas serão atualizadas
            pecasNoTabuleiro.remove(pecaCapturada);
            pecasCapturadas.add(pecaCapturada);
        }
        return pecaCapturada;
    }

    private void desfazerMovimento(Posicao origem, Posicao destino, Peca pecaCapturada){
        Peca p = tabuleiro.removerPeca((destino));
        tabuleiro.colocarPeca(p, origem);

        if(pecaCapturada != null){
            tabuleiro.colocarPeca(pecaCapturada, destino);
            pecasCapturadas.remove(pecaCapturada);
            pecasNoTabuleiro.add(pecaCapturada);
        }
    }

    private void validarPosicaoOrigem(Posicao posicao){             //verifica se tem peça na posição informada
        if (!tabuleiro.posicaoOcupada(posicao)){
            throw new XadrezException("Não há nenhuma peça nessa posição");
        }
        if(jogadorAtual != ((PecaXadrez)tabuleiro.peca(posicao)).getCor()){     //verifica se a peça é do jogador do turno atual (downcasting)
            throw new XadrezException("Essa peça é do adversário!");
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

    private void proximoTurno(){
        turno++;
        jogadorAtual = (jogadorAtual == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
    }

    private Cor oponente(Cor cor){                          //se a cor do argumento for branca(jogador) retorna preto(oponente)
        return(cor == Cor.PRETO) ? Cor.PRETO : Cor.BRANCO;
    }

    private PecaXadrez rei(Cor cor){
        List<Peca> lista = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getCor() == cor).collect(Collectors.toList()); //downcasting
        for(Peca p : lista){
            if (p instanceof Rei){
                return (PecaXadrez)p;       //downcasting
            }
        }
        throw new IllegalStateException("Não existe o rei "+ cor + "no tabuleiro");
    }

    private boolean testarXeque(Cor cor) {
        Posicao posicaoRei = rei(cor).getPosicaoXadrez().toPosicao(); //pega a coordenada do rei em formato do xadrez
        List<Peca> pecasOponente = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getCor() == oponente(cor)).collect(Collectors.toList()); //downcasting
        for (Peca p : pecasOponente){
            boolean[][] matriz = p.movimentosPossiveis();               //matriz de movimentos possiveis da peça selecionada do oponente
            if(matriz[posicaoRei.getLinha()][posicaoRei.getColuna()]){  //se nessa matriz a posição do rei for true então o rei está em cheque
                return true;
            }
        }
        return false;
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
        colocarNovaPeca('a', 1, new Torre(tabuleiro, Cor.BRANCO));
        colocarNovaPeca('h', 1, new Torre(tabuleiro, Cor.BRANCO));
        colocarNovaPeca('e', 8, new Rei(tabuleiro, Cor.PRETO));
        colocarNovaPeca('e', 1, new Rei(tabuleiro, Cor.BRANCO));
        colocarNovaPeca('d', 8, new Dama(tabuleiro, Cor.PRETO));
        colocarNovaPeca('d', 1, new Dama(tabuleiro, Cor.BRANCO));

    }

    public int getTurno() {
        return turno;
    }

    public Cor getJogadorAtual() {
        return jogadorAtual;
    }

    public boolean isXeque() {
        return xeque;
    }
}

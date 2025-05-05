package xadrez;

import boardgame.Posicao;

public class PosicaoXadrez {
    private char coluna;
    private int linha;

    public PosicaoXadrez(char coluna, int linha) {
        if(coluna < 'a' || coluna > 'h' || linha < 1 || linha > 8){
            throw new XadrezException("Erro instanciando posição no xadrez. Valores validos vão de a1 até h8");
        }
        this.coluna = coluna;
        this.linha = linha;
    }

    protected Posicao toPosicao(){
        return new Posicao(8 - linha, coluna - 'a');
    }

    protected static PosicaoXadrez fromPosicao(Posicao posicao){            //pega uma posição da matriz e transforma no formato de xadrez (a1-h8)
        return new PosicaoXadrez((char)('a' + posicao.getColuna()), 8 - posicao.getLinha());
    }

    @Override
    public String toString(){
        return "" + coluna + linha;
    }

    public char getColuna() {
        return coluna;
    }

    public int getLinha() {
        return linha;
    }
}

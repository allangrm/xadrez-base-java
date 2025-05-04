package boardgame;

public class Tabuleiro {

    private int linhas;
    private int colunas;
    private Peca[][] pecas;

    public Tabuleiro(int linhas, int colunas) {
        if (linhas < 1 || colunas < 1){
            throw new TabuleiroExecption("Erro ao criar tabuleiro: deve haver pelo menos 1 linha e 1 coluna");
        }
        this.linhas = linhas;
        this.colunas = colunas;
        pecas = new Peca[linhas][colunas];
    }

    public Peca peca(int linha, int coluna){ //retorna peca por linha e colunas
        if(!posicaoExiste(linha,coluna)){
            throw new TabuleiroExecption("Posição não está no tabuleiro");
        }
        return pecas[linha][coluna];
    }

    public Peca peca(Posicao posicao){  //retorna peca pela sua posicao
        if(!posicaoExiste(posicao)){
            throw new TabuleiroExecption("Posição não está no tabuleiro");
        }
        return pecas[posicao.getLinha()][posicao.getColuna()];
    }

    public void colocarPeca(Peca peca, Posicao posicao){
        if (posicaoOcupada((posicao))){
            throw new TabuleiroExecption(("Já existe uma peça nessa posição "+ posicao));
        }
        pecas[posicao.getLinha()][posicao.getColuna()] = peca;  //pecas instanciadas somente no tabuleiro
        peca.posicao = posicao;                                 //posicao da classe Peca(super) é atribuida com uma nova
                                                                //posicao (ou posicao inicial)
    }

    private boolean posicaoExiste(int linha, int coluna){       //existe quando esta dentro do tabuleiro
        return linha >= 0 && linha < linhas && coluna >= 0 && coluna < colunas;
    }

    public boolean posicaoExiste(Posicao posicao){

        return posicaoExiste(posicao.getLinha(), posicao.getColuna());
    }

    public boolean posicaoOcupada(Posicao posicao){
        if(!posicaoExiste(posicao)){
            throw new TabuleiroExecption("Posição não está no tabuleiro");
        }
        return peca(posicao) != null;
    }

    public int getLinhas() {
        return linhas;
    }

    public int getColunas() {
        return colunas;
    }

}

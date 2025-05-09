package application;

import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.Cor;
import xadrez.PosicaoXadrez;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class UI {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    // https://stackoverflow.com/questions/2979383/java-clear-the-console
    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static PosicaoXadrez lerPosicaoXadrez(Scanner sc){
        try {
            String s = sc.nextLine();
            char coluna = s.charAt(0);                                  //coluna é o primeiro caractere ex.: a1, a é coluna
            int linha = Integer.parseInt(s.substring(1));     //recorta e pega so a linha, ou segundo caractere, que é um inteiro
            return new PosicaoXadrez(coluna, linha);
        }
        catch (RuntimeException e){
            throw new InputMismatchException("Erro ao ler a posição solicitada. Valores válidos vão de a1 até h8");
        }
    }

    public static void printPartida(PartidaXadrez partida, List<PecaXadrez> capturadas) {
        printTabuleiro(partida.getPecas());
        System.out.println();
        printPecasCapturadas(capturadas);
        System.out.println("Turno: " + partida.getTurno());
        if (!partida.isXequeMate()) {
            System.out.println("Esperando jogador: " + partida.getJogadorAtual());
            if (partida.isXeque()) {
                System.out.println("XEQUE!");
            }

        } else {
            System.out.println("XEQUE MATE!");
            System.out.println("Ganhador: " + partida.getJogadorAtual());
        }
    }

    public static void printTabuleiro(PecaXadrez[][] pecas){
        for (int i = 0; i < pecas.length; i++){
            System.out.print((8 - i) + " ");
            for (int j = 0; j < pecas.length; j++){
                printPeca(pecas[i][j], false);
            }
            System.out.println(); //quebra de linha para ir para a proxima
        }
        System.out.println("  a b c d e f g h");
    }

    public static void printTabuleiro(PecaXadrez[][] pecas, boolean[][] movimentosPossiveis){ //printa tabuleiro com possíveis movimentos da peça selecionada
        for (int i = 0; i < pecas.length; i++){
            System.out.print((8 - i) + " ");
            for (int j = 0; j < pecas.length; j++){
                printPeca(pecas[i][j], movimentosPossiveis[i][j]);
            }
            System.out.println(); //quebra de linha para ir para a proxima
        }
        System.out.println("  a b c d e f g h");
    }

    private static void printPeca(PecaXadrez peca, boolean fundo){
        if (fundo){
            System.out.print(ANSI_GREEN_BACKGROUND);
        }
        if (peca == null) {
            System.out.print("-" + ANSI_RESET);
        }
        else {
            if (peca.getCor() == Cor.BRANCO) {
                System.out.print(ANSI_WHITE + peca + ANSI_RESET);
            }
            else {
                System.out.print(ANSI_BLUE + peca + ANSI_RESET);
            }
        }
        System.out.print(" ");
    }

    private static void printPecasCapturadas(List<PecaXadrez> capturadas){
        List<PecaXadrez> brancas = capturadas.stream().filter(x -> x.getCor() == Cor.BRANCO).collect(Collectors.toList());  //filtrando a lista para achar as peças brancas capturadas
        List<PecaXadrez> pretas = capturadas.stream().filter(x -> x.getCor() == Cor.PRETO).collect(Collectors.toList());    //filtrando a lista para achar as peças pretas capturadas
        System.out.println("Peças capturadas: ");
        System.out.print("Brancas: ");
        System.out.println(ANSI_WHITE);
        System.out.println(Arrays.toString(brancas.toArray()));
        System.out.println(ANSI_RESET);

        System.out.print("Pretas: ");
        System.out.println(ANSI_BLUE);
        System.out.println(Arrays.toString(pretas.toArray()));
        System.out.println(ANSI_RESET);

    }
}

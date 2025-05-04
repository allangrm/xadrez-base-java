package xadrez;

import boardgame.TabuleiroExecption;

public class XadrezException extends TabuleiroExecption {       //além de pegar as exeções de xadrez também pega as do tabuleiro

    public XadrezException(String msg){
        super(msg);
    }
}

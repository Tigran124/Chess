public class King extends ChessFigures{

    public King(boolean isWhite) {
        super();
        imigeYourTurn = 'K';
        imigeOponentTurn = 'k';
        isFigure = true;
        this.isWhite = isWhite;
        dosentMoove = true;
        posibleMooves = new PosibleMoove();
    }
    @Override
    public void calculatePosibleMoove(int i, int j, ChessFigures[][] board) {
        this.posibleMooves = new PosibleMoove();
        int index = 0;
        if(isOnBoard(i + 1,j + 1)) {
            if (isFrendlyFigure(isWhite,i + 1,j + 1,board)) {
                board[i + 1][j + 1].underProtection = true;
            }else if(!board[i + 1][j + 1].isFigure){
                if(!tileIsAnderAttak(isWhite,i + 1,j + 1,board)) {
                    posibleMooves.newPosibleMoove(index, i + 1, j + 1);
                    index++;
                }
            }else {
                if(!board[i + 1][j + 1].underProtection){
                    posibleMooves.newPosibleMoove(index, i + 1, j + 1);
                    index++;
                }
            }
        }
        if(isOnBoard(i,j + 1)) {
            if (isFrendlyFigure(isWhite,i,j + 1,board)) {
                board[i][j + 1].underProtection = true;
            }else if(!board[i][j + 1].isFigure){
                if(!tileIsAnderAttak(isWhite,i,j + 1,board)) {
                    posibleMooves.newPosibleMoove(index, i, j + 1);
                    index++;
                }
            }else {
                if(!board[i][j + 1].underProtection){
                    posibleMooves.newPosibleMoove(index, i, j + 1);
                    index++;
                }
            }
        }
        if(isOnBoard(i + 1,j)) {
            if (isFrendlyFigure(isWhite,i + 1,j,board)) {
                board[i + 1][j].underProtection = true;
            }else if(!board[i + 1][j].isFigure){
                if(!tileIsAnderAttak(isWhite,i + 1,j,board)) {
                    posibleMooves.newPosibleMoove(index, i + 1, j);
                    index++;
                }
            }else {
                if(!board[i + 1][j].underProtection){
                    posibleMooves.newPosibleMoove(index, i + 1, j);
                    index++;
                }
            }
        }
        if(isOnBoard(i - 1,j + 1)) {
            if (isFrendlyFigure(isWhite,i - 1,j + 1,board)) {
                board[i - 1][j + 1].underProtection = true;
            }else if(!board[i - 1][j + 1].isFigure){
                if(!tileIsAnderAttak(isWhite,i - 1,j + 1,board)) {
                    posibleMooves.newPosibleMoove(index, i - 1, j + 1);
                    index++;
                }
            }else {
                if(!board[i - 1][j + 1].underProtection){
                    posibleMooves.newPosibleMoove(index, i - 1, j + 1);
                    index++;
                }
            }
        }
        if(isOnBoard(i,j - 1)) {
            if (isFrendlyFigure(isWhite,i,j - 1,board)) {
                board[i][j - 1].underProtection = true;
            }else if(!board[i][j - 1].isFigure){
                if(!tileIsAnderAttak(isWhite,i,j - 1,board)) {
                    posibleMooves.newPosibleMoove(index, i, j - 1);
                    index++;
                }
            }else {
                if(!board[i][j - 1].underProtection){
                    posibleMooves.newPosibleMoove(index, i, j - 1);
                    index++;
                }
            }
        }
        if(isOnBoard(i - 1,j)) {
            if (isFrendlyFigure(isWhite,i - 1,j,board)) {
                board[i - 1][j].underProtection = true;
            }else if(!board[i - 1][j].isFigure){
                if(!tileIsAnderAttak(isWhite,i - 1,j,board)) {
                    posibleMooves.newPosibleMoove(index, i - 1, j);
                    index++;
                }
            }else {
                if(!board[i - 1][j].underProtection){
                    posibleMooves.newPosibleMoove(index, i - 1, j);
                    index++;
                }
            }
        }
        if(isOnBoard(i + 1,j - 1)) {
            if (isFrendlyFigure(isWhite,i + 1,j - 1,board)) {
                board[i + 1][j - 1].underProtection = true;
            }else if(!board[i + 1][j - 1].isFigure){
                if(!tileIsAnderAttak(isWhite,i + 1,j - 1,board)) {
                    posibleMooves.newPosibleMoove(index, i + 1, j - 1);
                    index++;
                }
            }else {
                if(!board[i + 1][j - 1].underProtection){
                    posibleMooves.newPosibleMoove(index, i + 1, j - 1);
                    index++;
                }
            }
        }
        if(isOnBoard(i - 1,j - 1)) {
            if (isFrendlyFigure(isWhite,i - 1,j - 1,board)) {
                board[i - 1][j - 1].underProtection = true;
            }else if(!board[i - 1][j - 1].isFigure){
                if(!tileIsAnderAttak(isWhite,i - 1,j - 1,board)) {
                    posibleMooves.newPosibleMoove(index, i - 1, j - 1);
                    index++;
                }
            }else {
                if(!board[i - 1][j - 1].underProtection){
                    posibleMooves.newPosibleMoove(index, i - 1, j - 1);
                    index++;
                }
            }
        }
        if (dosentMoove) {
            if (castleRight(isWhite, i, j, board)) {
                posibleMooves.newPosibleMoove(index, i, j + 2);
                index++;
            }
        }
        if (dosentMoove) {
            if (castleLeft(isWhite, i, j, board)) {
                posibleMooves.newPosibleMoove(index, i, j - 2);
                index++;
            }
        }
        this.posibleMooveCount = index;
    }


    private boolean isFrendlyFigure(boolean isWhite,int i,int j,ChessFigures[][] board) {
        if (board[i][j].isFigure){
            return isWhite == board[i][j].isWhite;
        }else {
            return false;
        }
    }

    @Override
    public boolean isOponentFigure(boolean isWhite, int i, int j, ChessFigures[][] board) {
        if (board[i][j].isFigure){
            return isWhite != board[i][j].isWhite;
        }else {
            return false;
        }
    }

    @Override
    public void printe(boolean whooseTurnIs) {
        if (whooseTurnIs == isWhite){
            System.out.print(" " + imigeYourTurn + " ");
        }else {
            System.out.print(" " + imigeOponentTurn + " ");
        }
    }

    @Override
    public void printeChoosen(boolean whooseTurnIs) {
        if (whooseTurnIs == isWhite){
            System.out.print("*" + imigeYourTurn + "*");
        }else {
            System.out.print("*" + imigeOponentTurn + "*");
        }
    }

    private boolean isOnBoard(int i,int j){
        return (i <= 7 && i >= 0) && (j <= 7 && j >= 0);
    }

    private boolean tileIsAnderAttak(boolean isWhite,int x,int y,ChessFigures[][] board) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j].isWhite != isWhite && board[i][j].isFigure) {
                    for (int k = 0; k < board[i][j].posibleMooveCount; k++) {
                        if (board[i][j].posibleMooves.canAttakTile(x,y,board[i][j].posibleMooveCount)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean castleRight(boolean isWhite,int i,int j,ChessFigures[][] board) {
        if (board[i][j+3].dosentMoove) {
            if (!tileIsAnderAttak(isWhite,i,j,board) && !board[i][j + 1].isFigure && !board[i][j + 2].isFigure) {
                if (!tileIsAnderAttak(isWhite,i,j + 1,board) && !tileIsAnderAttak(isWhite,i,j + 2,board)) {
                    return true;
                }
            }
        }
        return false;
    }


    private boolean castleLeft(boolean isWhite,int i,int j,ChessFigures[][] board) {
        if (board[i][j-4].dosentMoove) {
            if (!tileIsAnderAttak(isWhite,i,j,board) && !board[i][j - 1].isFigure && !board[i][j - 2].isFigure && !board[i][j - 3].isFigure) {
                if (!tileIsAnderAttak(isWhite,i,j - 1,board) && !tileIsAnderAttak(isWhite,i,j - 2,board)) {
                    return true;
                }
            }
        }
        return false;
    }
}

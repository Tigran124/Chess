public class Solger extends ChessFigures{

    public Solger(boolean isWhite) {
        super();
        imigeYourTurn = 'S';
        imigeOponentTurn = 's';
        isFigure = true;
        this.isWhite = isWhite;
        underProtection = false;
        atakingKing = false;
        solgerTwoBoxMoove = false;
        posibleMooves = new PosibleMoove();
    }

    @Override
    public void calculatePosibleMoove(int i,int j,ChessFigures[][] board) {
        int index = 0;
        if (isWhite) {
            if (isOnBoard(i + 1,j + 1)) {
                if (isFrendlyFigure(isWhite,i + 1,j + 1,board)) {
                    board[i + 1][j + 1].underProtection = true;
                }
                posibleMooves.newPosibleMoove(index, i + 1,j + 1);
                index++;
            }
            if (isOnBoard(i + 1,j - 1)) {
                if (isFrendlyFigure(isWhite,i + 1,j - 1,board)) {
                    board[i + 1][j - 1].underProtection = true;
                }
                posibleMooves.newPosibleMoove(index, i + 1,j - 1);
                index++;
            }
            this.posibleMooveCount = index;
        }else {
            if (isOnBoard(i - 1,j + 1)) {
                if (isFrendlyFigure(isWhite,i - 1,j + 1,board)) {
                    board[i - 1][j + 1].underProtection = true;
                }
                posibleMooves.newPosibleMoove(index, i - 1,j + 1);
                index++;
            }
            if (isOnBoard(i - 1,j - 1)) {
                if (isFrendlyFigure(isWhite,i - 1,j - 1,board)) {
                    board[i - 1][j - 1].underProtection = true;
                }
                posibleMooves.newPosibleMoove(index, i - 1,j - 1);
                index++;
            }
            this.posibleMooveCount = index;
        }
    }

    @Override
    public void calculatePosibleMooveForSolger(int i,int j,ChessFigures[][] board) {
        int index = 0;
        if (isWhite) {
            if (isOnBoard(i + 1,j + 1) && isOponentFigure(isWhite,i + 1,j + 1,board)) {
                posibleMooves.newPosibleMoove(index, i + 1,j + 1);
                index++;
            }
            if (isOnBoard(i + 1,j - 1) && isOponentFigure(isWhite,i + 1,j - 1,board)) {
                posibleMooves.newPosibleMoove(index, i + 1,j - 1);
                index++;
            }
            if (isOnBoard(i,j + 1) && isOponentFigure(isWhite,i,j + 1,board)) {
                if (board[i][j + 1].solgerTwoBoxMoove) {
                    posibleMooves.newPosibleMoove(index, i + 1,j + 1);
                    index++;
                }
            }
            if (isOnBoard(i,j - 1) && isOponentFigure(isWhite,i,j - 1,board)) {
                if (board[i][j - 1].solgerTwoBoxMoove) {
                    posibleMooves.newPosibleMoove(index, i + 1,j - 1);
                    index++;
                }
            }
            if (!board[i + 1][j].isFigure) {
                posibleMooves.newPosibleMoove(index, i + 1, j);
                index++;
                if (i == 1) {
                    if (!board[i + 2][j].isFigure) {
                        posibleMooves.newPosibleMoove(index, i + 2, j);
                        index++;
                    }
                }
            }
            this.posibleMooveCount = index;
        }else {
            if (isOnBoard(i - 1,j + 1) && isOponentFigure(isWhite,i - 1,j + 1,board)) {
                posibleMooves.newPosibleMoove(index, i - 1,j + 1);
                index++;
            }
            if (isOnBoard(i - 1,j - 1) && isOponentFigure(isWhite,i - 1,j - 1,board)) {
                posibleMooves.newPosibleMoove(index, i - 1,j - 1);
                index++;
            }
            if (isOnBoard(i,j + 1) && isOponentFigure(isWhite,i,j + 1,board)) {
                if (board[i][j + 1].solgerTwoBoxMoove) {
                    posibleMooves.newPosibleMoove(index, i - 1,j + 1);
                    index++;
                }
            }
            if (isOnBoard(i,j - 1) && isOponentFigure(isWhite,i,j - 1,board)) {
                if (board[i][j - 1].solgerTwoBoxMoove) {
                    posibleMooves.newPosibleMoove(index, i - 1,j - 1);
                    index++;
                }
            }
            if (!board[i - 1][j].isFigure) {
                posibleMooves.newPosibleMoove(index, i - 1, j);
                index++;
                if (i == 6) {
                    if (!board[i - 2][j].isFigure) {
                        posibleMooves.newPosibleMoove(index, i - 2, j);
                        index++;
                    }
                }
            }
            this.posibleMooveCount = index;
        }
    }

    private boolean isFrendlyFigure(boolean isWhite,int i,int j,ChessFigures[][] board) {
        if (board[i][j].isFigure){
            return isWhite == board[i][j].isWhite;
        }else {
            return false;
        }
    }

    @Override
    public boolean isOponentFigure(boolean isWhite,int i,int j,ChessFigures[][] board) {
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
}

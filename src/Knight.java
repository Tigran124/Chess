public class Knight extends ChessFigures{

    public Knight(boolean isWhite) {
        super();
        imigeYourTurn = 'N';
        imigeOponentTurn = 'n';
        isFigure = true;
        this.isWhite = isWhite;
        underProtection = false;
        posibleMooves = new PosibleMoove();
    }
    @Override
    public void calculatePosibleMoove(int i, int j, ChessFigures[][] board) {
        int index = 0;
        if (isOnBoard(i + 1,j + 2)) {
            if (isFrendlyFigure(isWhite,i + 1,j + 2,board)) {
                board[i + 1][j + 2].underProtection = true;
            }else {
                posibleMooves.newPosibleMoove(index, i + 1, j + 2);
                index++;
            }
        }
        if (isOnBoard(i + 2,j + 1)) {
            if (isFrendlyFigure(isWhite,i + 2,j + 1,board)) {
                board[i + 2][j + 1].underProtection = true;
            }else {
                posibleMooves.newPosibleMoove(index, i + 2, j + 1);
                index++;
            }
        }
        if (isOnBoard(i - 1,j + 2)) {
            if (isFrendlyFigure(isWhite,i - 1,j + 2,board)) {
                board[i - 1][j + 2].underProtection = true;
            }else {
                posibleMooves.newPosibleMoove(index, i - 1, j + 2);
                index++;
            }
        }
        if (isOnBoard(i - 2,j + 1)) {
            if (isFrendlyFigure(isWhite,i - 2,j + 1,board)) {
                board[i - 2][j + 1].underProtection = true;
            }else {
                posibleMooves.newPosibleMoove(index, i - 2, j + 1);
                index++;
            }
        }
        if (isOnBoard(i + 1,j - 2)) {
            if (isFrendlyFigure(isWhite,i + 1,j - 2,board)) {
                board[i + 1][j - 2].underProtection = true;
            }else {
                posibleMooves.newPosibleMoove(index, i + 1, j - 2);
                index++;
            }
        }
        if (isOnBoard(i + 2,j - 1)) {
            if (isFrendlyFigure(isWhite,i + 2,j - 1,board)) {
                board[i + 2][j - 1].underProtection = true;
            }else {
                posibleMooves.newPosibleMoove(index, i + 2, j - 1);
                index++;
            }
        }
        if (isOnBoard(i - 1,j - 2)) {
            if (isFrendlyFigure(isWhite,i - 1,j - 2,board)) {
                board[i - 1][j - 2].underProtection = true;
            }else {
                posibleMooves.newPosibleMoove(index, i - 1, j - 2);
                index++;
            }
        }
        if (isOnBoard(i - 2,j - 1)) {
            if (isFrendlyFigure(isWhite,i - 2,j - 1,board)) {
                board[i - 2][j - 1].underProtection = true;
            }else {
                posibleMooves.newPosibleMoove(index, i - 2, j - 1);
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
    public boolean isOponentFigure(boolean isWhite,int i,int j,ChessFigures[][] board) {
        if (board[i][j].isFigure){
            return isWhite != board[i][j].isWhite;
        }else {
            return false;
        }
    }

    private boolean isOnBoard(int i,int j){
        return (i <= 7 && i >= 0) && (j <= 7 && j >= 0);
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
}

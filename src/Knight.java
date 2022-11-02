public class Knight extends ChessFigures{

    public Knight(boolean isWhite) {
        super();
        if (isWhite){
            imige = 'n';
        }else {
            imige = 'N';
        }
        isFigure = true;
        this.isWhite = isWhite;
        posibleMooves = new PosibleMoove();
    }
    @Override
    public void calculatePosibleMoove(int i, int j, ChessFigures[][] board) {
        int index = 0;
        if (isOnBoard(i + 1,j + 2)&&(!board[i + 1][j + 2].isFigure || isOponentFigure(isWhite,i + 1,j + 2,board))) {
            posibleMooves.newPosibleMoove(index, i + 1, j + 2);
            index++;
        }
        if (isOnBoard(i + 2,j + 1)&&(!board[i + 2][j + 1].isFigure || isOponentFigure(isWhite,i + 2,j + 1,board))) {
            posibleMooves.newPosibleMoove(index, i + 2, j + 1);
            index++;
        }
        if (isOnBoard(i - 1,j + 2)&&(!board[i - 1][j + 2].isFigure || isOponentFigure(isWhite,i - 1,j + 2,board))) {
            posibleMooves.newPosibleMoove(index, i - 1, j + 2);
            index++;
        }
        if (isOnBoard(i - 2,j + 1)&&(!board[i - 2][j + 1].isFigure || isOponentFigure(isWhite,i - 2,j + 1,board))) {
            posibleMooves.newPosibleMoove(index, i - 2, j + 1);
            index++;
        }
        if (isOnBoard(i + 1,j - 2)&&(!board[i + 1][j - 2].isFigure || isOponentFigure(isWhite,i + 1,j - 2,board))) {
            posibleMooves.newPosibleMoove(index, i + 1, j - 2);
            index++;
        }
        if (isOnBoard(i + 2,j - 1)&&(!board[i + 2][j - 1].isFigure || isOponentFigure(isWhite,i + 2,j - 1,board))) {
            posibleMooves.newPosibleMoove(index, i + 2, j - 1);
            index++;
        }
        if (isOnBoard(i - 1,j - 2)&&(!board[i - 1][j - 2].isFigure || isOponentFigure(isWhite,i - 1,j - 2,board))) {
            posibleMooves.newPosibleMoove(index, i - 1, j - 2);
            index++;
        }
        if (isOnBoard(i - 2,j - 1)&&(!board[i - 2][j - 1].isFigure || isOponentFigure(isWhite,i - 2,j - 1,board))) {
            posibleMooves.newPosibleMoove(index, i - 2, j - 1);
            index++;
        }
        this.posibleMooveCount = index;
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
    public void printe() {
        System.out.print(" "+imige+" ");
    }

    @Override
    public void printeChoosen() {
        System.out.print("*"+imige+"*");
    }
}

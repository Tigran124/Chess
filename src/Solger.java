public class Solger extends ChessFigures{

    public Solger(boolean isWhite) {
        super();
        if (isWhite){
            imige = 's';
        }else {
            imige = 'S';
        }
        isFigure = true;
        this.isWhite = isWhite;
        posibleMooves = new PosibleMoove();
    }

    @Override
    public void calculatePosibleMoove(int i,int j,ChessFigures[][] board) {
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
        }else {
            if (isOnBoard(i - 1,j + 1) && isOponentFigure(isWhite,i - 1,j + 1,board)) {
                posibleMooves.newPosibleMoove(index, i - 1,j + 1);
                index++;
            }
            if (isOnBoard(i - 1,j - 1) && isOponentFigure(isWhite,i - 1,j - 1,board)) {
                posibleMooves.newPosibleMoove(index, i - 1,j - 1);
                index++;
            }
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
            if (!board[i + 1][j].isFigure) {
                posibleMooves.newPosibleMoove(index, i + 1, j);
                index++;
                if (!board[i + 2][j].isFigure && i == 1) {
                    posibleMooves.newPosibleMoove(index, i + 2, j);
                    index++;
                }
            }
        }else {
            if (isOnBoard(i - 1,j + 1) && isOponentFigure(isWhite,i - 1,j + 1,board)) {
                posibleMooves.newPosibleMoove(index, i - 1,j + 1);
                index++;
            }
            if (isOnBoard(i - 1,j - 1) && isOponentFigure(isWhite,i - 1,j - 1,board)) {
                posibleMooves.newPosibleMoove(index, i - 1,j - 1);
                index++;
            }
            if (!board[i - 1][j].isFigure) {
                posibleMooves.newPosibleMoove(index, i - 1, j);
                index++;
                if (!board[i - 2][j].isFigure && i == 6) {
                    posibleMooves.newPosibleMoove(index, i - 2, j);
                    index++;
                }
            }
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
    public void printe() {
        System.out.print(" "+imige+" ");
    }

    @Override
    public void printeChoosen() {
        System.out.print("*"+imige+"*");
    }

    private boolean isOnBoard(int i,int j){
        return (i <= 7 && i >= 0) && (j <= 7 && j >= 0);
    }
}

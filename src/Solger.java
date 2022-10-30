public class Solger extends ChessFigures{

    public Solger(boolean isWhite) {
        super();
        if (isWhite){
            imige = 's';
        }else {
            imige = 's';
        }
        isFigure = true;
        this.isWhite = isWhite;
        posibleMooves = new PosibleMoove();
    }

    @Override
    public void calculatePosibleMoove(int i,int j,ChessFigures[][] board) {
        int index = 0;
        if (isWhite) {
            if (!board[i + 1][j].isFigure) {
                posibleMooves.newPosibleMoove(index, i + 1, j);
                index++;
                if (!board[i + 2][j].isFigure && i == 1) {
                    posibleMooves.newPosibleMoove(index, i + 2, j);
                    index++;
                }
            }
        }else {
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
}

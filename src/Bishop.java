public class Bishop extends ChessFigures{

    public Bishop(boolean isWhite) {
        super();
        if (isWhite){
            imige = 'b';
        }else {
            imige = 'B';
        }
        isFigure = true;
        this.isWhite = isWhite;
        posibleMooves = new PosibleMoove();
    }
    @Override
    public void calculatePosibleMoove(int i, int j, ChessFigures[][] board) {
        int index = 0;
        for (int k = 1; i + k < board.length && j + k < board.length; k++) {
            if (!board[i + k][j + k].isFigure) {
                posibleMooves.newPosibleMoove(index, i + k, j + k);
                index++;
            }else if (isOponentFigure(isWhite,i + k,j + k,board)){
                posibleMooves.newPosibleMoove(index, i + k, j + k);
                index++;
                break;
            }else {
                break;
            }
        }
        for (int k = 1; i + k < board.length && j - k >= 0; k++) {
            if (!board[i + k][j - k].isFigure) {
                posibleMooves.newPosibleMoove(index, i + k, j - k);
                index++;
            }else if (isOponentFigure(isWhite,i + k,j - k,board)){
                posibleMooves.newPosibleMoove(index, i + k, j - k);
                index++;
                break;
            }else {
                break;
            }
        }
        for (int k = 1; i - k >= 0 && j + k < board.length; k++) {
            if (!board[i - k][j + k].isFigure) {
                posibleMooves.newPosibleMoove(index, i - k, j + k);
                index++;
            }else if (isOponentFigure(isWhite,i - k,j + k,board)){
                posibleMooves.newPosibleMoove(index, i - k, j + k);
                index++;
                break;
            }else {
                break;
            }
        }
        for (int k = 1; i - k >= 0 && j - k >= 0; k++) {
            if (!board[i - k][j - k].isFigure) {
                posibleMooves.newPosibleMoove(index, i - k, j - k);
                index++;
            }else if (isOponentFigure(isWhite,i - k,j - k,board)){
                posibleMooves.newPosibleMoove(index, i - k, j - k);
                index++;
                break;
            }else {
                break;
            }
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

    @Override
    public void printe() {
        System.out.print(" "+imige+" ");
    }

    @Override
    public void printeChoosen() {
        System.out.print("*"+imige+"*");
    }
}

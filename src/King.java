public class King extends ChessFigures{

    public King(boolean isWhite) {
        super();
        if (isWhite){
            imige = 'k';
        }else {
            imige = 'K';
        }
        isFigure = true;
        this.isWhite = isWhite;
        posibleMooves = new PosibleMoove();
    }
    @Override
    public void calculatePosibleMoove(int i, int j, ChessFigures[][] board) {
        int index = 0;
        if(isOnBoard(i + 1,j + 1) && (isOponentFigure(isWhite,i + 1,j + 1,board) || !board[i + 1][j + 1].isFigure)) {
            posibleMooves.newPosibleMoove(index,i + 1,j + 1);
            index++;
        }
        if(isOnBoard(i,j + 1) && isOponentFigure(isWhite,i,j + 1,board) || !board[i][j + 1].isFigure) {
            posibleMooves.newPosibleMoove(index,i,j + 1);
            index++;
        }
        if(isOnBoard(i + 1,j) && isOponentFigure(isWhite,i + 1,j,board) || !board[i + 1][j].isFigure) {
            posibleMooves.newPosibleMoove(index, i + 1,j);
            index++;
        }
        if(isOnBoard(i - 1,j + 1) && isOponentFigure(isWhite,i - 1,j + 1,board) || !board[i - 1][j + 1].isFigure) {
            posibleMooves.newPosibleMoove(index,i - 1,j + 1);
            index++;
        }
        if(isOnBoard(i,j - 1) && isOponentFigure(isWhite,i,j - 1,board) || !board[i][j - 1].isFigure) {
            posibleMooves.newPosibleMoove(index,i,j - 1);
            index++;
        }
        if(isOnBoard(i - 1,j) && isOponentFigure(isWhite,i - 1,j,board) || !board[i - 1][j].isFigure) {
            posibleMooves.newPosibleMoove(index, i - 1,j);
            index++;
        }
        if(isOnBoard(i + 1,j - 1) && isOponentFigure(isWhite,i + 1,j - 1,board) || !board[i + 1][j - 1].isFigure) {
            posibleMooves.newPosibleMoove(index,i + 1,j - 1);
            index++;
        }
        if(isOnBoard(i - 1,j - 1) && isOponentFigure(isWhite,i - 1,j - 1,board) || !board[i - 1][j - 1].isFigure) {
            posibleMooves.newPosibleMoove(index,i - 1,j - 1);
            index++;
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

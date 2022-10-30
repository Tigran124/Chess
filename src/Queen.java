public class Queen extends ChessFigures{

    public Queen(boolean isWhite) {
        super();
        if (isWhite){
            imige = 'q';
        }else {
            imige = 'Q';
        }
        isFigure = true;
        this.isWhite = isWhite;
        posibleMooves = new PosibleMoove();
    }
    @Override
    public void calculatePosibleMoove(int i, int j, ChessFigures[][] board) {
        int index = 0;
        for (int k = 1; i + k < board.length; k++) {
            if (!board[i + k][j].isFigure) {
                posibleMooves.newPosibleMoove(index, i + k, j);
                index++;
            }else if (isOponentFigure(isWhite,i + k,j,board)){
                posibleMooves.newPosibleMoove(index, i + k, j);
                index++;
                break;
            }else {
                break;
            }
        }
        for (int k = 1; k < board.length && i - k >= 0; k++) {
            if (!board[i - k][j].isFigure) {
                posibleMooves.newPosibleMoove(index, i - k, j);
                index++;
            }else if (isOponentFigure(isWhite,i - k,j,board)){
                posibleMooves.newPosibleMoove(index, i - k, j);
                index++;
                break;
            }else {
                break;
            }
        }
        for (int k = 1; j + k < board.length; k++) {
            if (!board[i][j +k].isFigure) {
                posibleMooves.newPosibleMoove(index, i , j + k);
                index++;
            }else if (isOponentFigure(isWhite,i,j +k,board)){
                posibleMooves.newPosibleMoove(index,i,j + k);
                index++;
                break;
            }else {
                break;
            }
        }
        for (int k = 1; k < board.length && j - k >= 0; k++) {
            if (!board[i][j - k].isFigure) {
                posibleMooves.newPosibleMoove(index, i, j - k);
                index++;
            }else if (isOponentFigure(isWhite,i,j - k,board)){
                posibleMooves.newPosibleMoove(index, i,j - k);
                index++;
                break;
            }else {
                break;
            }
        }
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
}

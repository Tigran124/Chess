public class Bishop extends ChessFigures{

    public Bishop(boolean isWhite) {
        super();
        imigeYourTurn = 'B';
        imigeOponentTurn = 'b';
        isFigure = true;
        this.isWhite = isWhite;
        underProtection = false;
        atakingKing = false;
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
            }else if (isFrendlyFigure(isWhite,i + k,j + k,board)){
                board[i + k][j + k].underProtection = true;
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
            }else if (isFrendlyFigure(isWhite,i + k,j - k,board)){
                board[i + k][j - k].underProtection = true;
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
            }else if (isFrendlyFigure(isWhite,i - k,j + k,board)){
                board[i - k][j + k].underProtection = true;
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
            }else if (isFrendlyFigure(isWhite,i - k,j - k,board)){
                board[i - k][j - k].underProtection = true;
                break;
            }else {
                break;
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

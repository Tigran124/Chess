public class Queen extends ChessFigures{

    public Queen(boolean isWhite) {
        super();
        imigeYourTurn = 'Q';
        imigeOponentTurn = 'q';
        isFigure = true;
        this.isWhite = isWhite;
        underProtection = false;
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
            }else if (isFrendlyFigure(isWhite,i + k,j,board)){
                board[i + k][j].underProtection = true;
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
            }else if (isFrendlyFigure(isWhite,i - k,j,board)){
                board[i - k][j].underProtection = true;
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
            }else if (isFrendlyFigure(isWhite,i,j + k,board)){
                board[i][j + k].underProtection = true;
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
            }else if (isFrendlyFigure(isWhite,i,j - k,board)){
                board[i][j - k].underProtection = true;
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
}

public class Tile extends ChessFigures {
    public Tile() {
        super();
        imigeYourTurn = '_';
        imigeOponentTurn = '_';
        isFigure = false;
    }

    @Override
    public void calculatePosibleMoove(int i,int j,ChessFigures[][] board) {
    }

    @Override
    public boolean isOponentFigure(boolean isWhite, int i, int j, ChessFigures[][] board) {
        return false;
    }

    @Override
    public void printe(boolean whooseTurnIs) {
        System.out.print(" " + imigeOponentTurn + " ");
    }

    @Override
    public void printeChoosen(boolean whooseTurnIs) {
        System.out.print("*" + imigeOponentTurn + "*");
    }
}

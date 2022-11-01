public class Tile extends ChessFigures {
    public Tile() {
        super();
        imige = '_';
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
    public void printe() {
        System.out.print(" "+imige+" ");
    }

    @Override
    public void printeChoosen() {
        System.out.print("*"+imige+"*");
    }
}

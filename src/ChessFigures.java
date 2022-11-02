public abstract class ChessFigures {
     char imige;
     boolean isFigure;
     boolean isWhite;
     int posibleMooveCount;
     PosibleMoove posibleMooves;

     public ChessFigures(){}

     public abstract void calculatePosibleMoove(int i,int j,ChessFigures[][] board);

     public void calculatePosibleMooveForSolger(int i,int j,ChessFigures[][] board){
     }

     public abstract boolean isOponentFigure(boolean isWhite,int i,int j,ChessFigures[][] board);

    public abstract void printe();

    public abstract void printeChoosen();
}

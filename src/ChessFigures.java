public abstract class ChessFigures {
     char imigeYourTurn;
     char imigeOponentTurn;
     boolean underProtection;
     boolean isFigure;
     boolean isWhite;
     boolean atakingKing;
     boolean solgerTwoBoxMoove;
     boolean dosentMoove;
     int posibleMooveCount;
     PosibleMoove posibleMooves;

     public ChessFigures(){}

     public abstract void calculatePosibleMoove(int i,int j,ChessFigures[][] board);

     public void calculatePosibleMooveForSolider(int i, int j, ChessFigures[][] board){
     }

     public abstract boolean isOponentFigure(boolean isWhite,int i,int j,ChessFigures[][] board);

    public abstract void printe(boolean whooseTurnIs);

    public abstract void printeChoosen(boolean whooseTurnIs);
}

import java.util.Scanner;

public class PlayChess {
    boolean gameContinue = true;
    boolean whooseTurnIs = true;
    int whiteKingI = -2;
    int whiteKingJ = -2;
    int blackKingI = 0;
    int blackKingJ = 0;
    Scanner scanner = new Scanner(System.in);
    ChessFigures [][] board = new  ChessFigures [8][8];
    public void play() {
        fullBoard();
        int turn = 2;
        while (gameContinue)  {
            whooseTurnIs = (turn % 2) == 0;
            printBoard(whooseTurnIs);
            calculatePosibleMooves();
            check(whooseTurnIs);
            System.out.println("chose figure");
            int i = isValid(scanner.nextInt());
            int j = isValid(scanner.nextInt());
            while (!board[i][j].isWhite == whooseTurnIs || !board[i][j].isFigure) {
                if (!board[i][j].isFigure) {
                    System.out.println("there are no figure");
                }else {
                    System.out.println("it is oponents figure");
                }
                i = isValid(scanner.nextInt());
                j = isValid(scanner.nextInt());
            }
            toChooseFigure(i,j,board);
            System.out.println("where to moove");
            int x = isValid(scanner.nextInt());
            int y = isValid(scanner.nextInt());
            while (!board[i][j].posibleMooves.isPosible(x,y)) {
                System.out.println("you can not moove there");
                x = isValid(scanner.nextInt());
                y = isValid(scanner.nextInt());
            }
            toMoove(i,j,x,y,board);
            calculatePosibleMooves();
            if (check(whooseTurnIs)) {
                if (whooseTurnIs) {
                    System.out.println("White loose");
                }else {
                    System.out.println("Black loose");
                }
                this.gameContinue = false;
            }
            clearPosibleMoove();
            turn ++;
        }
    }
    public void fullBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = new Tile();
            }
        }
        board[0][0] = new King(false);
        board[7][7] = new Rook(true);
    }

    public void printBoard(boolean whooseTurnIs) {
        for (int i = 7; i >=0; i--) {
            for (int j = 0; j < 8; j++) {
                board[i][j].printe(whooseTurnIs);
            }
            System.out.println();
        }
    }

    public void printChoosen(int x,int y,boolean whooseTurnIs) {
        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                if(board[x][y].posibleMooves.isPosible(i,j) || (x == i && y == j)){
                    board[i][j].printeChoosen(whooseTurnIs);
                }else {
                    board[i][j].printe(whooseTurnIs);
                }
            }
            System.out.println();
        }
    }

    public void toChooseFigure(int i,int j,ChessFigures[][] board) {
        if(board[i][j]instanceof Solger){
            board[i][j].posibleMooves = new PosibleMoove();
            board[i][j].calculatePosibleMooveForSolger(i, j, board);
            printChoosen(i, j,whooseTurnIs);
        }else {
            board[i][j].calculatePosibleMoove(i, j, board);
            printChoosen(i, j,whooseTurnIs);
        }
    }

    public void toMoove(int i,int j,int x,int y,ChessFigures[][] board) {
        if(i == whiteKingI && j == whiteKingJ){
            this.whiteKingI = x;
            this.whiteKingJ = y;
        }
        if (i == blackKingI && j == blackKingJ){
            this.blackKingI = x;
            this.blackKingJ = y;
        }
        board[x][y] = board[i][j];
        board[i][j] = new Tile();
    }

    public void calculatePosibleMooves() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j].isFigure) {
                    board[i][j].calculatePosibleMoove(i,j,board);
                }
            }
        }
    }


    public boolean check(boolean isWhite) {
        if (isWhite){
            if(tileIsAnderAttak(isWhite,whiteKingI,whiteKingJ)) {
                System.out.println("White King is ander attak");
                return true;
            }
        }else {
            if(tileIsAnderAttak(isWhite,blackKingI,blackKingJ)) {
                System.out.println("Black King is ander attak");
                return true;
            }
        }
        return false;
    }

    public boolean tileIsAnderAttak(boolean isWhite,int x,int y) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j].isWhite != isWhite && board[i][j].isFigure) {
                    for (int k = 0; k < board[i][j].posibleMooveCount; k++) {
                        if (board[i][j].posibleMooves.canAttakTile(x,y,board[i][j].posibleMooveCount)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public void clearPosibleMoove() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j].isFigure) {
                    board[i][j].posibleMooves = new PosibleMoove();
                    board[i][j].underProtection = false;
                }
            }
        }
    }

    public int isValid (int cordinate) {
        while (!(cordinate < 8 && cordinate >= 0)) {
            System.out.println("wrong cordinate");
            cordinate = scanner.nextInt();
        }
        return cordinate;
    }
}

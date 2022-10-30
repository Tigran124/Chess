import java.util.Scanner;

public class PlayChess {
    boolean gameEnd = true;
    Scanner scanner = new Scanner(System.in);
    ChessFigures [][] board = new  ChessFigures [8][8];
    public void play() {
        fullBoard();
        while (gameEnd)  {
            printBoard();
            calculateAllPosibleMooves();
            System.out.println("chose figure");
            int i = isValid(scanner.nextInt());
            int j = isValid(scanner.nextInt());
            while (!board[i][j].isFigure) {
                System.out.println("there are no figure");
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
            clearPosibleMoove();
        }
    }
    public void fullBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = new Tile();
            }
        }
        board[0][0] = new Queen(true);
        board[0][2] = new Knight(true);
        board[0][3] = new Rook(true);
        board[0][4] = new Solger(true);
        board[0][5] = new Bishop(true);
        board[7][7] = new Rook(false);
    }

    public void printBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j].printe();
            }
            System.out.println();
        }
    }

    public void printChoosen(int x,int y) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(board[x][y].posibleMooves.isPosible(i,j) || (x == i && y == j)){
                    board[i][j].printeChoosen();
                }else {
                    board[i][j].printe();
                }
            }
            System.out.println();
        }
    }

    public void toChooseFigure(int i,int j,ChessFigures[][] board) {
        board[i][j].calculatePosibleMoove(i,j,board);
        printChoosen(i,j);
    }

    public void toMoove(int i,int j,int x,int y,ChessFigures[][] board) {
        board[x][y] = board[i][j];
        board[i][j] = new Tile();
    }

    public void calculateAllPosibleMooves() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j].isFigure) {
                    board[i][j].calculatePosibleMoove(i,j,board);
                }
            }
        }
    }

    public void clearPosibleMoove() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j].isFigure) {
                    board[i][j].posibleMooves = new PosibleMoove();
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

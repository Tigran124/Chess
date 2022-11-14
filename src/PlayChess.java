import java.util.Scanner;

public class PlayChess {
    boolean gameContinue = true;
    boolean whooseTurnIs = true;
    int whiteKingI = 0;
    int whiteKingJ = 4;
    int blackKingI = 7;
    int blackKingJ = 4;
    Scanner scanner = new Scanner(System.in);
    ChessFigures [][] board = new  ChessFigures [8][8];
    public void play() {
        fullBoard();
        int turn = 2;
        while (gameContinue)  {
            this.whooseTurnIs = (turn % 2) == 0;
            printBoard(whooseTurnIs);
            calculateOpinentPosibleMooves(!whooseTurnIs);
            calculateMyPosibleMooves(whooseTurnIs);
            if (check(whooseTurnIs)) {
                rescueKing(whooseTurnIs);
                if (mate()) {
                    if (whooseTurnIs) {
                        System.out.println("White loose");
                    }else {
                        System.out.println("Black loose");
                    }
                    break;
                }
            }
            System.out.println("chose figure");
            int i = isValid(scanner.nextInt());
            int j = isValid(scanner.nextInt());
            while (!board[i][j].isWhite == whooseTurnIs || !board[i][j].isFigure) {
                if (!board[i][j].isFigure) {
                    System.out.println("there are no figure");
                }else {
                    System.out.println("it is opponents figure");
                }
                i = isValid(scanner.nextInt());
                j = isValid(scanner.nextInt());
            }
            toChooseFigure(i,j);
            System.out.println("where to move");
            int x = isValid(scanner.nextInt());
            int y = isValid(scanner.nextInt());
            while (!board[i][j].posibleMooves.isPosible(x,y,board[i][j].posibleMooveCount)) {
                System.out.println("you can not moove there");
                x = isValid(scanner.nextInt());
                y = isValid(scanner.nextInt());
            }
            toMoove(i,j,x,y,board);
            clearPosibleMoove();
            calculateOpinentPosibleMooves(!whooseTurnIs);
            if (check(whooseTurnIs)) {
                if (whooseTurnIs) {
                    System.out.println("White loose");
                }else {
                    System.out.println("Black loose");
                }
                this.gameContinue = false;
            }
            clearPosibleMoove();
            clearOtherParametrs(!whooseTurnIs);
            turn ++;
        }
    }
    public void fullBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = new Tile();
            }
        }
        board[0][4] = new King(true);
        board[0][0] = new Queen(true);
        board[0][1] = new Queen(true);
        board[7][4] = new King(false);
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
                if(board[x][y].posibleMooves.isPosible(i,j,board[x][y].posibleMooveCount) || (x == i && y == j)){
                    board[i][j].printeChoosen(whooseTurnIs);
                }else {
                    board[i][j].printe(whooseTurnIs);
                }
            }
            System.out.println();
        }
    }

    public void toChooseFigure(int i,int j) {
        printChoosen(i, j,whooseTurnIs);
    }

    public void toMoove(int i,int j,int x,int y,ChessFigures[][] board) {
        if (board[i][j]instanceof King){
            kingIsMooving(i,j,x,y);
        }
        if (board[i][j]instanceof Rook && board[i][j].dosentMoove) {
            board[i][j].dosentMoove = false;
        }
        if (board[i][j]instanceof Solger && ((i - x) == 2 || (i - x) == -2)) {
            board[i][j].solgerTwoBoxMoove = true;
        }
        board[x][y] = board[i][j];
        if (board[i][j]instanceof King && (y - j == 2)) {
            board[i][j + 1] = board[i][j + 3];
            board[i][j + 3] = new Tile();
        }
        if (board[i][j]instanceof King && (y - j == -2)) {
            board[i][j - 1] = board[i][j - 4];
            board[i][j - 4] = new Tile();
        }
        if (board[x][y]instanceof Solger && isOnBoard(x + 1,y)) {
            if (board[x + 1][y].solgerTwoBoxMoove && j != y) {
                board[x + 1][y] = new Tile();
            }
        }
        if (board[x][y]instanceof Solger && isOnBoard(x - 1,y)) {
            if (board[x - 1][y].solgerTwoBoxMoove && j != y) {
                board[x - 1][y] = new Tile();
            }
        }
        board[i][j] = new Tile();
        if (board[x][y]instanceof Solger && (x == 0 || x == 7)) {
            promoteSolger(board[x][y].isWhite,x,y);
        }
    }

    public void calculateOpinentPosibleMooves(boolean whooseTurnIs) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j].isFigure && board[i][j].isWhite == whooseTurnIs) {
                    board[i][j].calculatePosibleMoove(i,j,board);
                }
            }
        }
    }

    public void calculateMyPosibleMooves(boolean whooseTurnIs) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j].isFigure && board[i][j].isWhite == whooseTurnIs) {
                    if(board[i][j]instanceof Solger){
                        board[i][j].calculatePosibleMooveForSolger(i, j, board);
                    }else {
                        board[i][j].calculatePosibleMoove(i, j, board);
                    }
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


    public boolean mate() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j].isWhite == whooseTurnIs && board[i][j].isFigure) {
                    if (board[i][j].posibleMooveCount != 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }


    private void rescueKing(boolean whooseTurnIs) {
        int countOfAtakers = 0;
        int kingsI;
        int kingsJ;
        PosibleMoove atakers = new PosibleMoove();
        if(whooseTurnIs){
            kingsI = whiteKingI;
            kingsJ = whiteKingJ;
        }else {
            kingsI = blackKingI;
            kingsJ = blackKingJ;
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j].atakingKing) {
                    atakers.newPosibleMoove(countOfAtakers,i,j);
                    countOfAtakers ++;
                }
            }
        }
        for (int i = 0; i < countOfAtakers; i++) {
            int atakerI = atakers.cordinatesI[i];
            int atakerJ = atakers.cordinatesJ[i];
            if (board[atakerI][atakerJ]instanceof Rook || board[atakerI][atakerJ]instanceof Queen) {
                board[kingsI][kingsJ].posibleMooves.isOnTheLine(kingsI,kingsJ,atakerI,atakerJ,board[kingsI][kingsJ].posibleMooveCount,board);
            }
            if (board[atakerI][atakerJ]instanceof Bishop || board[atakerI][atakerJ]instanceof Queen) {
                board[kingsI][kingsJ].posibleMooves.isOnParalel(kingsI,kingsJ,atakerI,atakerJ,board[kingsI][kingsJ].posibleMooveCount,board);
            }
        }
        if(countOfAtakers == 1) {
            int atakerI = atakers.cordinatesI[0];
            int atakerJ = atakers.cordinatesJ[0];
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board[i][j].isWhite == whooseTurnIs && board[i][j].isFigure) {
                        boolean attakLine = board[atakerI][atakerJ]instanceof Rook || board[atakerI][atakerJ]instanceof Queen;
                        boolean attakParalel = board[atakerI][atakerJ]instanceof Bishop || board[atakerI][atakerJ]instanceof Queen;
                        board[i][j].posibleMooveCount = board[i][j].posibleMooves.calculateRescueMove(attakLine,attakParalel,atakerI,atakerJ,kingsI,kingsJ,board[i][j].posibleMooveCount);
                    }
                }
            }
        }else {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board[i][j].isWhite == whooseTurnIs && board[i][j].isFigure) {
                        board[i][j].posibleMooveCount = 0;
                    }
                }
            }
        }
    }

    private void kingIsMooving (int i,int j,int x,int y) {
        if(i == whiteKingI && j == whiteKingJ){
            board[i][j].dosentMoove = false;
            this.whiteKingI = x;
            this.whiteKingJ = y;
        }
        if (i == blackKingI && j == blackKingJ){
            board[i][j].dosentMoove = false;
            this.blackKingI = x;
            this.blackKingJ = y;
        }
    }

    public boolean tileIsAnderAttak(boolean isWhite,int x,int y) {
        boolean tileIsUnderAttak = false;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j].isWhite != isWhite && board[i][j].isFigure) {
                    for (int k = 0; k < board[i][j].posibleMooveCount; k++) {
                        if (board[i][j].posibleMooves.canAttakTile(x,y,board[i][j].posibleMooveCount)) {
                            board[i][j].atakingKing = true;
                            tileIsUnderAttak = true;
                        }
                    }
                }
            }
        }
        return tileIsUnderAttak;
    }

    public void clearPosibleMoove() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j].isFigure) {
                    board[i][j].posibleMooves = new PosibleMoove();
                    board[i][j].underProtection = false;
                    board[i][j].atakingKing = false;
                }
            }
        }
    }

    public void clearOtherParametrs(boolean whooseTurnIs) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j].isFigure && board[i][j].isWhite == whooseTurnIs) {
                    board[i][j].solgerTwoBoxMoove = false;
                }
            }
        }
    }


    private void promoteSolger (boolean isWhite,int i,int j) {
        System.out.println("Upgrade to what 1) Queen 2)Bishop 3)Knight 4)Rook");
        int index = scanner.nextInt();
        switch (index) {
            case 1 -> board[i][j] = new Queen(isWhite);
            case 2 -> board[i][j] = new Bishop(isWhite);
            case 3 -> board[i][j] = new Knight(isWhite);
            case 4 -> board[i][j] = new Rook(isWhite);
            default -> {
                System.out.println("You input wrong number");
                promoteSolger(isWhite, i, j);
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


    private boolean isOnBoard(int i,int j){
        return (i <= 7 && i >= 0) && (j <= 7 && j >= 0);
    }
}

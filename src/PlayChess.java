import java.util.Scanner;

public class PlayChess {
    boolean gameContinue = true;
    boolean whooseTurnIs = true;
    int whiteKingI = 0;
    int whiteKingJ = 4;
    int blackKingI = 7;
    int blackKingJ = 4;
    int i;
    int j;
    int x;
    int y;
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
                    whoWins();
                    break;
                }
                printRescureMoves();
            }else if (draw()) {
                System.out.println("Drown");
                break;
            }
            toChooseFigure(i,j);
            whereToMove(x,y);
            toMoove(i,j,x,y,board);
            clearPosibleMoove();
            clearOtherParametrs(!whooseTurnIs);
            turn ++;
        }
    }
    public void fullBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = new Tile();
            }
        }
        for (int k = 0; k < 8; k++) {
            board[1][k] = new Solger(true);
        }
        board[0][0] = new Rook(true);
        board[0][1] = new Knight(true);
        board[0][2] = new Bishop(true);
        board[0][3] = new Queen(true);
        board[0][4] = new King(true);
        board[0][5] = new Bishop(true);
        board[0][6] = new Knight(true);
        board[0][7] = new Rook(true);
        for (int k = 0; k < 8; k++) {
            board[6][k] = new Solger(false);
        }
        board[7][0] = new Rook(false);
        board[7][1] = new Knight(false);
        board[7][2] = new Bishop(false);
        board[7][3] = new Queen(false);
        board[7][4] = new King(false);
        board[7][5] = new Bishop(false);
        board[7][6] = new Knight(false);
        board[7][7] = new Rook(false);
    }

    public void printBoard(boolean whooseTurnIs) {
        if (whooseTurnIs) {
            System.out.println("White turn");
        }else {
            System.out.println("Black turn");
        }
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

    public void printRescureMoves() {
        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                if(board[i][j].posibleMooveCount != 0 && board[i][j].isWhite == whooseTurnIs){
                    board[i][j].printeChoosen(whooseTurnIs);
                }else {
                    board[i][j].printe(whooseTurnIs);
                }
            }
            System.out.println();
        }
    }

    public void toChooseFigure(int i,int j) {
        System.out.println("chose figure");
        i = isValid(scanner.nextInt());
        j = isValid(scanner.nextInt());
        while (!board[i][j].isWhite == whooseTurnIs || !board[i][j].isFigure || board[i][j].posibleMooveCount == 0) {
            if (!board[i][j].isFigure) {
                System.out.println("there are no figure");
                i = isValid(scanner.nextInt());
                j = isValid(scanner.nextInt());
            } else if (board[i][j].posibleMooveCount == 0) {
                System.out.println("These figure can not move");
                i = isValid(scanner.nextInt());
                j = isValid(scanner.nextInt());
            } else if (board[i][j].isWhite != whooseTurnIs) {
                System.out.println("it is opponents figure");
                i = isValid(scanner.nextInt());
                j = isValid(scanner.nextInt());
            }
        }
        this.i = i;
        this.j = j;
        printChoosen(i, j,whooseTurnIs);
    }

    public void whereToMove (int x,int y) {
        System.out.println("Where to move");
        x = isValid(scanner.nextInt());
        y = isValid(scanner.nextInt());
        while (!board[i][j].posibleMooves.isPosible(x,y,board[i][j].posibleMooveCount)) {
            System.out.println("You can not move there");
            System.out.println("If you want to move other figure input 10");
            x = scanner.nextInt();
            if (x == 10) {
                toChooseFigure(i,j);
            }
            if (!(x >= 0 && x<=7)) {
                x = isValid(scanner.nextInt());
            }
            y = isValid(scanner.nextInt());
        }
        this.x = x;
        this.y = y;
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
        clearImposibleMove();
    }

    public void clearImposibleMove() {
        int kingsI;
        int kingsJ;
        int protectorI = 0;
        int protectorJ = 0;
        if(whooseTurnIs){
            kingsI = whiteKingI;
            kingsJ = whiteKingJ;
        }else {
            kingsI = blackKingI;
            kingsJ = blackKingJ;
        }
        for (int k = 1,protectorCount = 0; kingsI + k < 8 && protectorCount < 2; k++) {
            if (isFrendlyFigure(kingsI + k,kingsJ)) {
                protectorI = kingsI + k;
                protectorJ = kingsJ;
                protectorCount ++;
            } else if (isOponentFigure(kingsI + k,kingsJ)) {
                if (protectorCount == 1 && (board[kingsI + k][kingsJ]instanceof Queen || board[kingsI + k][kingsJ]instanceof Rook)) {
                    board[protectorI][protectorJ].posibleMooveCount = board[protectorI][protectorJ].posibleMooves.removeImposibleMoveOnLine(kingsI + k,kingsJ,kingsI,kingsJ,board[protectorI][protectorJ].posibleMooveCount);
                }else {
                    break;
                }
            }
        }
        for (int k = 1,protectorCount = 0;k < 8 && kingsI - k >= 0 && protectorCount < 2; k++) {
            if (isFrendlyFigure(kingsI - k,kingsJ)) {
                protectorI = kingsI - k;
                protectorJ = kingsJ;
                protectorCount ++;
            } else if (isOponentFigure(kingsI - k,kingsJ)) {
                if (protectorCount == 1 && (board[kingsI - k][kingsJ]instanceof Queen || board[kingsI - k][kingsJ]instanceof Rook)) {
                    board[protectorI][protectorJ].posibleMooveCount = board[protectorI][protectorJ].posibleMooves.removeImposibleMoveOnLine(kingsI - k,kingsJ,kingsI,kingsJ,board[protectorI][protectorJ].posibleMooveCount);
                }else {
                    break;
                }
            }
        }
        for (int k = 1,protectorCount = 0; kingsJ + k < 8 && protectorCount < 2; k++) {
            if (isFrendlyFigure(kingsI,kingsJ + k)) {
                protectorI = kingsI;
                protectorJ = kingsJ + k;
                protectorCount ++;
            } else if (isOponentFigure(kingsI,kingsJ + k)) {
                if (protectorCount == 1 && (board[kingsI][kingsJ + k]instanceof Queen || board[kingsI][kingsJ + k]instanceof Rook)) {
                    board[protectorI][protectorJ].posibleMooveCount = board[protectorI][protectorJ].posibleMooves.removeImposibleMoveOnLine(kingsI,kingsJ + k,kingsI,kingsJ,board[protectorI][protectorJ].posibleMooveCount);
                }else {
                    break;
                }
            }
        }
        for (int k = 1,protectorCount = 0;k < 8 && kingsJ - k >= 0 && protectorCount < 2; k++) {
            if (isFrendlyFigure(kingsI,kingsJ - k)) {
                protectorI = kingsI;
                protectorJ = kingsJ - k;
                protectorCount ++;
            } else if (isOponentFigure(kingsI,kingsJ - k)) {
                if (protectorCount == 1 && (board[kingsI][kingsJ - k]instanceof Queen || board[kingsI][kingsJ - k]instanceof Rook)) {
                    board[protectorI][protectorJ].posibleMooveCount = board[protectorI][protectorJ].posibleMooves.removeImposibleMoveOnLine(kingsI,kingsJ - k,kingsI,kingsJ,board[protectorI][protectorJ].posibleMooveCount);
                }else {
                    break;
                }
            }
        }
        for (int k = 1,protectorCount = 0;kingsI + k < 8 && kingsJ + k < 8 && protectorCount < 2; k++) {
            if (isFrendlyFigure(kingsI + k,kingsJ + k)) {
                protectorI = kingsI + k;
                protectorJ = kingsJ + k;
                protectorCount ++;
            } else if (isOponentFigure(kingsI + k,kingsJ + k)) {
                if (protectorCount == 1 && (board[kingsI + k][kingsJ + k]instanceof Queen || board[kingsI + k][kingsJ + k]instanceof Bishop)) {
                    board[protectorI][protectorJ].posibleMooveCount = board[protectorI][protectorJ].posibleMooves.removeImposibleMoveOnDiaganal(kingsI + k,kingsJ + k,kingsI,kingsJ,board[protectorI][protectorJ].posibleMooveCount);
                }else {
                    break;
                }
            }
        }
        for (int k = 1,protectorCount = 0;kingsI + k < 8 && kingsJ - k >= 0 && protectorCount < 2; k++) {
            if (isFrendlyFigure(kingsI + k,kingsJ - k)) {
                protectorI = kingsI + k;
                protectorJ = kingsJ - k;
                protectorCount ++;
            } else if (isOponentFigure(kingsI + k,kingsJ - k)) {
                if (protectorCount == 1 && (board[kingsI + k][kingsJ - k]instanceof Queen || board[kingsI + k][kingsJ - k]instanceof Bishop)) {
                    board[protectorI][protectorJ].posibleMooveCount = board[protectorI][protectorJ].posibleMooves.removeImposibleMoveOnDiaganal(kingsI + k,kingsJ - k,kingsI,kingsJ,board[protectorI][protectorJ].posibleMooveCount);
                }else {
                    break;
                }
            }
        }
        for (int k = 1,protectorCount = 0;kingsI - k >= 0 && kingsJ + k < 8 && protectorCount < 2; k++) {
            if (isFrendlyFigure(kingsI - k,kingsJ + k)) {
                protectorI = kingsI - k;
                protectorJ = kingsJ + k;
                protectorCount ++;
            } else if (isOponentFigure(kingsI - k,kingsJ + k)) {
                if (protectorCount == 1 && (board[kingsI - k][kingsJ + k]instanceof Queen || board[kingsI - k][kingsJ + k]instanceof Bishop)) {
                    board[protectorI][protectorJ].posibleMooveCount = board[protectorI][protectorJ].posibleMooves.removeImposibleMoveOnDiaganal(kingsI - k,kingsJ + k,kingsI,kingsJ,board[protectorI][protectorJ].posibleMooveCount);
                }else {
                    break;
                }
            }
        }
        for (int k = 1,protectorCount = 0;kingsI - k >= 0 && kingsJ - k >= 0 && protectorCount < 2; k++) {
            if (isFrendlyFigure(kingsI - k,kingsJ - k)) {
                protectorI = kingsI - k;
                protectorJ = kingsJ - k;
                protectorCount ++;
            } else if (isOponentFigure(kingsI - k,kingsJ - k)) {
                if (protectorCount == 1 && (board[kingsI - k][kingsJ - k]instanceof Queen || board[kingsI - k][kingsJ - k]instanceof Bishop)) {
                    board[protectorI][protectorJ].posibleMooveCount = board[protectorI][protectorJ].posibleMooves.removeImposibleMoveOnDiaganal(kingsI - k,kingsJ - k,kingsI,kingsJ,board[protectorI][protectorJ].posibleMooveCount);
                }else {
                    break;
                }
            }
        }
    }

    public boolean draw() {
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

    public boolean check(boolean isWhite)   {
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

    public void whoWins() {
        if (whooseTurnIs) {
            System.out.println("Black wins");
        }else {
            System.out.println("White wins");
        }
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
                board[kingsI][kingsJ].posibleMooves.isOnDiaganal(kingsI,kingsJ,atakerI,atakerJ,board[kingsI][kingsJ].posibleMooveCount,board);
            }
        }
        if(countOfAtakers == 1) {
            int atakerI = atakers.cordinatesI[0];
            int atakerJ = atakers.cordinatesJ[0];
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board[i][j].isWhite == whooseTurnIs && board[i][j].isFigure) {
                        if (board[i][j]instanceof King){
                            continue;
                        }
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
                        if (board[i][j]instanceof King){
                            continue;
                        }
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

    private boolean isFrendlyFigure(int i,int j) {
        if (board[i][j].isFigure){
            return whooseTurnIs == board[i][j].isWhite;
        }else {
            return false;
        }
    }

    public boolean isOponentFigure(int i, int j) {
        if (board[i][j].isFigure){
            return whooseTurnIs != board[i][j].isWhite;
        }else {
            return false;
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

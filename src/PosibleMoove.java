public class PosibleMoove {
    Integer[] cordinatesI;
    Integer[] cordinatesJ;

    public PosibleMoove() {
        cordinatesI = new Integer[8];
        cordinatesJ = new Integer[8];
    }

    public void newPosibleMoove(int index,int i,int j) {
        if (cordinatesI[cordinatesI.length - 1] != null) {
            enlargeArrey(cordinatesI,cordinatesJ);
        }
        this.cordinatesI[index] = i;
        this.cordinatesJ[index] = j;
    }

    public boolean isPosible(int i,int j,int moveCount){
        boolean isPosible = false;
        for (int k = 0; k < moveCount; k++) {
            if(cordinatesI[k] == null) {
                break;
            }
            if (cordinatesI[k] == i && cordinatesJ[k] == j) {
                isPosible = true;
                break;
            }
        }
        return isPosible;
    }

    public void isOnTheLine(int myI,int myJ,int atakerI,int atakerJ,int moveCount,ChessFigures[][] board) {
        for (int i = 0; i < moveCount; i++) {
            if (cordinatesI[i] == atakerI ^ cordinatesJ[i] ==atakerJ) {
                removeMove(i,moveCount);
                board[myI][myJ].posibleMooveCount -= 1;
                moveCount --;
                i --;
            }
        }
    }

    public void isOnDiaganal(int myI, int myJ, int atakerI, int atakerJ, int moveCount, ChessFigures[][] board) {
        for (int i = 0; i < moveCount; i++) {
            if (onDiaganal(cordinatesI[i],cordinatesJ[i],atakerI,atakerJ)) {
                removeMove(i,moveCount);
                board[myI][myJ].posibleMooveCount -= 1;
                moveCount --;
                i --;
            }
        }
    }

    public boolean onDiaganal(int cordI, int cordJ, int atakerI, int atakerJ) {
        int subtractionI = cordI - atakerI;
        int subtractionJ = cordJ - atakerJ;
        if (subtractionI == 0 && subtractionJ == 0){
            return false;
        }
        subtractionI = Math.abs(subtractionI);
        subtractionJ = Math.abs(subtractionJ);
        return subtractionI == subtractionJ;
    }

    public int removeImposibleMoveOnLine(int atakerI,int atakerJ,int kingsI,int kingsJ,int moveCount) {
        PosibleMoove tempPosibleMove = new PosibleMoove();
        int posibleMoveCount = 0;
        for (int i = 0; i < moveCount; i++) {
            if (atakerI == kingsI && atakerI == cordinatesI[i]) {
                tempPosibleMove.cordinatesI[posibleMoveCount] = cordinatesI[i];
                tempPosibleMove.cordinatesJ[posibleMoveCount] = cordinatesJ[i];
                posibleMoveCount++;
                continue;
            }
            if (atakerJ == kingsJ && atakerJ == cordinatesJ[i]) {
                tempPosibleMove.cordinatesI[posibleMoveCount] = cordinatesI[i];
                tempPosibleMove.cordinatesJ[posibleMoveCount] = cordinatesJ[i];
                posibleMoveCount++;
            }
        }
        this.cordinatesI = tempPosibleMove.cordinatesI;
        this.cordinatesJ = tempPosibleMove.cordinatesJ;
        return posibleMoveCount;
    }

    public int removeImposibleMoveOnDiaganal(int atakerI,int atakerJ,int kingsI,int kingsJ,int moveCount) {
        PosibleMoove tempPosibleMove = new PosibleMoove();
        int posibleMoveCount = 0;
        for (int i = 0; i < moveCount; i++) {
            if (onDiaganal(cordinatesI[i], cordinatesJ[i], atakerI, atakerJ) && onDiaganal(cordinatesI[i], cordinatesJ[i], kingsI, kingsJ)) {
                tempPosibleMove.cordinatesI[posibleMoveCount] = cordinatesI[i];
                tempPosibleMove.cordinatesJ[posibleMoveCount] = cordinatesJ[i];
                posibleMoveCount++;
            }
            if (cordinatesI[i] == atakerI && cordinatesJ[i] == atakerJ) {
                tempPosibleMove.cordinatesI[posibleMoveCount] = cordinatesI[i];
                tempPosibleMove.cordinatesJ[posibleMoveCount] = cordinatesJ[i];
                posibleMoveCount++;
            }
        }
        this.cordinatesI = tempPosibleMove.cordinatesI;
        this.cordinatesJ = tempPosibleMove.cordinatesJ;
        return posibleMoveCount;
    }

    public int calculateRescueMove (boolean attakLine,boolean attakParalel,int atakerI,int atakerJ,int kingsI,int kingsJ,int moveCount) {
        PosibleMoove rescueMove = new PosibleMoove();
        int rescueMoveCount = 0;
        for (int i = 0; i < moveCount; i++) {
            if (cordinatesI[i] == atakerI && cordinatesJ[i] == atakerJ) {
                rescueMove.cordinatesI[rescueMoveCount] = cordinatesI[i];
                rescueMove.cordinatesJ[rescueMoveCount] = cordinatesJ[i];
                rescueMoveCount ++;
                continue;
            }
            if (attakLine) {
                if (atakerI == kingsI && atakerI == cordinatesI[i]) {
                    rescueMove.cordinatesI[rescueMoveCount] = cordinatesI[i];
                    rescueMove.cordinatesJ[rescueMoveCount] = cordinatesJ[i];
                    rescueMoveCount ++;
                    continue;
                }
                if (atakerJ == kingsJ && atakerJ == cordinatesJ[i]) {
                    rescueMove.cordinatesI[rescueMoveCount] = cordinatesI[i];
                    rescueMove.cordinatesJ[rescueMoveCount] = cordinatesJ[i];
                    rescueMoveCount ++;
                    continue;
                }
            }
            if (attakParalel) {
                if (onDiaganal(cordinatesI[i],cordinatesJ[i],atakerI,atakerJ) && onDiaganal(cordinatesI[i],cordinatesJ[i],kingsI,kingsJ)){
                    if (cordinatesI[i] < Math.max(atakerI,kingsI) && cordinatesI[i] > Math.min(atakerI,kingsI)) {
                        if (cordinatesJ[i] < Math.max(atakerJ,kingsJ) && cordinatesJ[i] > Math.min(atakerJ,kingsJ)) {
                            rescueMove.cordinatesI[rescueMoveCount] = cordinatesI[i];
                            rescueMove.cordinatesJ[rescueMoveCount] = cordinatesJ[i];
                            rescueMoveCount++;
                        }
                    }
                }
            }
        }
        this.cordinatesI = rescueMove.cordinatesI;
        this.cordinatesJ = rescueMove.cordinatesJ;
        return rescueMoveCount;
    }

    public void removeMove(int removeIndex,int moveCount) {
        for (int i = removeIndex; i < moveCount-1; i++) {
            cordinatesI[i] = cordinatesI[i + 1];
            cordinatesJ[i] = cordinatesJ[i + 1];
        }
    }

    public void enlargeArrey (Integer[] cordinatesI,Integer[] cordinatesJ) {
        Integer[] largerCodinateI = new Integer[cordinatesI.length + 8];
        Integer[] largerCodinateJ = new Integer[cordinatesJ.length + 8];
        for (int i = 0; i < cordinatesI.length; i++) {
            largerCodinateI[i] = cordinatesI[i];
            largerCodinateJ[i] = cordinatesJ[i];
        }
        this.cordinatesI = largerCodinateI;
        this.cordinatesJ = largerCodinateJ;
    }


    public boolean canAttakTile (int x,int y,int mooveCount) {
        for (int i = 0; i < mooveCount && cordinatesI[i] != null; i++) {
            if (cordinatesI[i] == x && cordinatesJ[i] == y){
                return true;
            }
        }
        return false;
    }
}

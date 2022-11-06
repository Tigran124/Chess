import java.util.Arrays;

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

    public boolean isPosible(int i,int j){
        boolean isPosible = false;
        for (int k = 0; k < cordinatesI.length; k++) {
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

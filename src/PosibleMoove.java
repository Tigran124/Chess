import java.util.Arrays;

public class PosibleMoove {
    int[] cordinatesI;
    int[] cordinatesJ;

    public PosibleMoove() {
        cordinatesI = new int[8];
        Arrays.fill(cordinatesI,-1);
        cordinatesJ = new int[8];
        Arrays.fill(cordinatesJ,-1);
    }

    public void newPosibleMoove(int index,int i,int j) {
        if (cordinatesI[cordinatesI.length - 1] != -1) {
            enlargeArrey(cordinatesI,cordinatesJ);
        }
        this.cordinatesI[index] = i;
        this.cordinatesJ[index] = j;
    }

    public boolean isPosible(int i,int j){
        boolean isPosible = false;
        for (int k = 0; k < cordinatesI.length; k++) {
            if (cordinatesI[k] == i && cordinatesJ[k] ==j) {
                isPosible = true;
                break;
            }
            if(cordinatesI[k] == -1) {
                break;
            }
        }
        return isPosible;
    }

    public void enlargeArrey (int[] cordinatesI,int[] cordinatesJ) {
        int[] largerCodinateI = new int[cordinatesI.length + 8];
        int[] largerCodinateJ = new int[cordinatesJ.length + 8];
        Arrays.fill(largerCodinateI,-1);
        Arrays.fill(largerCodinateJ,-1);
        for (int i = 0; i < cordinatesI.length; i++) {
            largerCodinateI[i] = cordinatesI[i];
            largerCodinateJ[i] = cordinatesJ[i];
        }
        this.cordinatesI = largerCodinateI;
        this.cordinatesJ = largerCodinateJ;
    }
}

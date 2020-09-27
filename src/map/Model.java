package map;

/**
 * @author huzipeng
 * @version 1.0
 */

public class Model {
    transient int i;
    int j;

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    @Override
    public String toString() {
        return "Model{" +
                "i=" + i +
                ", j=" + j +
                '}';
    }
}

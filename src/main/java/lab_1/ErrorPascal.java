package lab_1;

/**
 * Created by AlstonQiLee on 2016-11-04.
 */
public abstract class ErrorPascal implements Pascal {
    public boolean inv;
    public long save; //inver save value for n
    private int n;
    private int k;
    public static long[][] memory;
    private int counter;

    public ErrorPascal(boolean inv) {
        System.out.println("Pascal triangle:");
        this.inv = inv;
        counter =0;
    }

    public void printPascal(int n) {
        this.n = n;
    }

    public boolean getinv() {
        return inv;
        // return n;
    }

    public void init() {
        if (counter ==0) {
            this.save = n;
            memory = new long[this.n + 1][this.n + 1];
        }
        counter++;
        return;
    }

    public void exportn(int n) {
        this.n = n;
        // return n;
    }

    public void exportk(int k) {
        this.k = k;
        // return n;
    }

    public boolean sainitycheck() {
        if (this.n < 0) {
            System.out.println("Row can not be smaller than " + 0 + "row :");
            System.out.println("Terminated");
            return false;
        }

        if (this.n > 32) {
            System.out.println("Row can not be smaller than " + 32 + "row :");
            System.out.println("Terminated");
            return false;
        }

        if (this.k > this.n) {
            System.out.println("K can not bigger than N.");
            System.out.println("Terminated");
            return false;
        }
        return true;
    }

    public int binom(int n, int k) {
        //exception

        return 0;
    }

}

package lab_1;


//import java.lang.reflect.Array;

/**
 * Created by AlstonQiLee on 2016-11-04.
 */
public class IterativePascal extends ErrorPascal implements Pascal {
    public static int[][] save;
    public static int[][] memory;

    public IterativePascal(boolean inv) {
        super(inv);

    }
   // public static void main(String[] args) {
        //IterativePascal a = new IterativePascal();
        //int n=51;

      //System.out.print( a.binom(0,6));

       //a.printPascal(10);
   // }

    public void printPascal(int n) {
        exportn(n);
        if (!sainitycheck()){
            return;
        }
        save =new int[n+1][n+1];
        memory =new int [n][n];
        for(int i=0;i<3;i++){
            for(int k =0;k<=i;k++) {
                save[i][k]=binom(i, k);
            }
        }

        for(int i=3;i<n;i++){
            for(int k =0;k<=i;k++) {
                if (k==0){
                    save[i][k]= 0+save[i-1][k];
                }
                else {
                    save[i][k] = save[i - 1][k - 1] + save[i - 1][k];
                }
            }
        }
        if (!inv) {

            for (int i = 0; i < n; i++) {
                for (int k = 0; k <= i; k++) {
                    System.out.print(save[i][k]);
                    System.out.print(" ");
                }
                System.out.println(" ");
            }
        }
        else if(inv){
            for (int i = n; i > 0; i--) {
                for (int k = 0; k <= i; k++) {
                    if (save[i][k]!=0) {
                        System.out.print(save[i][k]);
                        System.out.print(" ");
                    }
                }
                System.out.println(" ");
            }
        }
        return;
    }

    public int binom(int n, int k) {
        exportn(n);
        exportk(k);
        if (!sainitycheck()){
            return 0;
        }
//exception
        /*
        if (k > n) {
            System.out.println("K can not bigger than N.");
            System.out.println("Terminated");
            return 0;
        }
        */

        if(n==k||k==0){
            return 1;
        }
/*        if(memory[n][k]!=0){
            return memory[n][k];
        }
        */
        //factorial nominator
        long nom=1;
        //factorial
        for (int i =1; i <=n ;i++) {
            nom = nom *i;
        }
        //denominator
        long denom=1;
        //factorial
        for (int i =1; i <=k ;i++) {
            denom = denom *i;
        }
        //n-k
        long nmk=1;
        for (int i =1; i <=n-k ;i++) {
            nmk = nmk *i;
        }
        denom = denom * nmk;
        /*
        int ans=0;
        ans = (int)(nom/ denom);
        */
        //memory[n][k]=ans;
return (int)(nom/ denom);
    }



}

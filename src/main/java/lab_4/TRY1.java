package lab_4;

import java.util.Scanner;

/**
 * Created by AlstonQiLee on 2016-11-23.
 */
public class TRY1 {
    public static void main(String[] args) {
        for(int i =0;i<9999;i++) {
            Scanner input = new Scanner(System.in);
            System.out.println("input letters");
            String o = input.nextLine();
            System.out.println((o.hashCode() * 11) % 8);
        }
    }
}

package lab_1_Maven;

/**
 * Created by AlstonQiLee on 2016-11-05.
 */

/******************************************************************************
 *  Compilation:  javac BinarySearch.java
 *  Execution:    java BinarySearch wordlist.txt < input.txt
 *  Data files:   http://www.cs.princeton.edu/introcs/43sort/emails.txt
 *                http://www.cs.princeton.edu/introcs/43sort/whitelist.txt
 *
 *  Read in an alphabetical list of words from the given file.
 *  Then prompt the user to enter words. The program reports which
 *  words are *not* in the wordlist.
 *
 *  % java BinarySearch whitelist.txt < emails.html
 *  marvin@spam
 *  mallory@spam
 *  eve@airport
 *
 ******************************************************************************/



import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;
import proj1.TinySearchEngine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class BinarySearch {
    public BinarySearch(){

    }
public static int counter =0;
        // return the index of the key in the sorted array a[]; -1 if not found
        public static int search(String key, ArrayList<TinySearchEngine.Node> a) {return search(key, a, 0, a.size());}
        public static int search(String key, ArrayList<TinySearchEngine.Node> a, int lo, int hi) {
            counter++;
            // possible key indices in [lo, hi)
            if (hi <= lo) return -1;
            int mid = lo + (hi - lo) / 2;
            int cmp = a.get(mid).word.toString().compareTo(key);
            if      (cmp > 0) return search(key, a, lo, mid);
            else if (cmp < 0) return search(key, a, mid+1, hi);
            else              return mid;
        }


/*        // whitelist, exception filter
        public static void main(String[] args) {


           // Scanner input = new Scanner(System.in);
          // System.out.print("Please enter the path that you wanted to search:");
           // String argument=input.nextLine();

            In in = new In(argument);

            String s = in.readAll();
            String[] words = s.split("\\s+");
            System.err.println("Done reading words");


            // sort the words (if needed)
            Arrays.sort(words);
            System.err.println("Done sorting words");
            //System.out.print(s);//debug
            for(int i=0;i<52;i++){}

           // System.out.print("Please input the number that you want to search: ");
            // prompt user to enter a word and check if it's there
            while (!StdIn.isEmpty()) {
                counter=0;
                String key = StdIn.readString();
                if (search(key, words) < 0){
                    StdOut.print(key);
                    System.out.println(" No Match!!");}
                else{
                    System.out.println(key+" Has been found!!");;
                }
                System.out.print("Time consuming is:"+ counter);
            }
        }*/
    }



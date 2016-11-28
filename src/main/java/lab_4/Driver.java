package lab_4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Created by AlstonQiLee on 2016-11-24.
 * QUESTION 3 BASED ON TRIE
 */
public class Driver {
    public static void main(String[] args) {
        Trie<Integer> a = new Trie<Integer>();
        File input = new File("C:\\Users\\AlstonQiLee\\IdeaProjects\\ID1020\\src\\lab_4\\pg98.txt");//url
        ArrayList<String> word= new ArrayList();//word list
        Driver driver = new Driver();//non static call
        int counter = 0;
        try {
            Scanner fileScan = new Scanner(input, "utf-8");//input stream

            while (fileScan.hasNextLine()) {
                StringBuilder in = new StringBuilder();
                String str =fileScan.nextLine().toLowerCase();
                in.append(str);
                String[] strarr = driver.filter(in.toString());
                //System.out.println (1);
                for(int i =0; i<strarr.length;i++) {
                    a.put(strarr[i]);
                   if( !word.contains(strarr[i])) {
                       word.add(strarr[i]);//creating word
                       counter++;
                   }

                }
            }
            fileScan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //creating count
        ArrayList<Integer> prefix2 = new ArrayList();
        ArrayList<String> prefix2word = new ArrayList();
        ArrayList<Integer> count = new ArrayList();
        for (int i = 0; i < counter; i++) {
            count.add(Integer.parseInt(a.get(word.get(i).toString()).toString()));
            driver.prefix10(prefix2,prefix2word,word,a,i,2);//prefix2 prefix1 prefix 1 print 10 most. just took the first one is fine
        }

        /*  test branch!! use in seminar for question 1,2,3,4 on problem 3,3*/
        driver.max10(prefix2,prefix2word);//prefix10

      // driver.max10(count,word);//max10
        //driver.min10(count,word);//min10
    }

    /*METHOD*/
    //PREFIX10 FOR3,4
    public void prefix10(ArrayList<Integer> prefix2, ArrayList prefix2word, ArrayList word, Trie a, int i, int prefixnr){
        StringBuilder prefix = null;
        char[] first2= word.get(i).toString().toCharArray();
        if (first2.length>=prefixnr) {
            prefix =new StringBuilder();
            if(prefixnr==2) {
                prefix.append(first2[0]);
                prefix.append(first2[1]);
            }
            if(prefixnr==1){
            prefix.append(first2[0]);}
        }
        if (prefix !=null) {
            String key = prefix.toString();
            if (!prefix2word.contains(key)) {
                prefix2word.add(key);
                prefix2.add(a.count(key));
            }
        }

    }
    //MIN FOR 2
    public void min10(ArrayList<Integer> count,ArrayList word){
        for (int i = 0; i < 10; i++) {
            Integer min = Collections.min(count);
            String strout = word.get(count.indexOf(min)).toString();
            count.remove(min);
            word.remove(strout);

            System.out.println("Min 10 is:"+strout+" it appeared "+ min+" times");
        }
    }
    //MAX FOR 1
    public void max10(ArrayList<Integer> count,ArrayList word){
        for (int i = 0; i < 10; i++) {
            Integer max = Collections.max(count);
            String strout = word.get(count.indexOf(max)).toString();

            count.remove(word.indexOf(strout));
            word.remove(strout);

            System.out.println("Top 10 is:"+strout+" it appeared "+ max+" times");
        }
    }
//FILTER OUT  ALL NON LETTER SYMBOL ( USED A BIT IDEA FROM STACK OVERFLOW BUT I WROTE MOST OF THEM MYSELF BY SCREATCH)
    public String[] filter(String str) {

        String[] strarr = str.split(" ");
        for (int i =0; i<strarr.length;i++) {
            StringBuilder build = new StringBuilder();
           // System.out.print(strarr[i]);
            char[] chars = strarr[i].toLowerCase().toCharArray();
            for (Character c : chars) {
                if (Character.isLetter(c)) {
                    build.append(c);
                }
            }
            strarr[i]= build.toString();
        }
        return strarr;

    }
}


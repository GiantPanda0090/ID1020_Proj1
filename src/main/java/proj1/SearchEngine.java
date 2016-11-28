package proj1;



import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by AlstonQiLee on 2016-11-28.
 */
public class SearchEngine {
    //ArrayList charList;
    private static final int R = 256;//EXTEND ASCII
    public static int counter =0;
   ArrayList<String> word;
    ArrayList<ArrayList> filename;
    public SearchEngine (){
        //Trie<Integer> trie= new Trie<Integer>();
        //charList = new ArrayList();
        word= new ArrayList<String>();
        filename= new ArrayList<ArrayList>();
    }
    public void insert(String url,String filename){
       File a = new File(url);
        try {
            Scanner filescan = new Scanner(a, "utf-8");
            String str = filescan.nextLine();//sentence
            String[] strarr = str.split(" ");//word
            for(int i =0; i<strarr.length;i++) {
                word.add(strarr.hashCode(),strarr[i]);
                if(this.filename.get(strarr.hashCode())==null) {
                    this.filename.add(strarr.hashCode(), new ArrayList());
                }
                else{
                   this.filename.get(strarr.hashCode()).add(filename);
                }
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static int search(String key, ArrayList<String> a) {
        return search(key, a, 0, a.size());
    }
    public static int search(String key, ArrayList<String> a, int lo, int hi) {
        counter++;
        // possible key indices in [lo, hi)
        if (hi <= lo) return -1;
        int mid = lo + (hi - lo) / 2;
        int cmp = a.get(mid).compareTo(key);
        if      (cmp > 0) return search(key, a, lo, mid);
        else if (cmp < 0) return search(key, a, mid+1, hi);
        else              return mid;
    }

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

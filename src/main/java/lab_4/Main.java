package lab_4;

import java.util.Iterator;

/**
 * Created by AlstonQiLee on 2016-11-24.
 * TEST CLASS FOR QUESTION 2
 */

public class Main {
    public static void main(String[] args) {
        Trie<Integer> a = new Trie<Integer>();
 a.put("doggy");
        a.put("doggyae");
        a.put("doggyab");
        a.put("doggyab");
        a.put("doggyad");
        a.put("doggya");
        a.put("doggyca");
        a.put("doggycb");
        a.put("doggycd");
        a.put("doggya");
        a.put("doggyo");
       Iterator TrieItr= a.iterator("doggy");
        int i=0;
        //System.out.println(a.get("doggyab"));
        while(TrieItr.hasNext()){
            //System.out.print( i);
            //i++;
            Object element =TrieItr.next();
            if(element != null) {
                System.out.print(element.toString() + " ");
            }
        }
        //a.max10();
    }

}

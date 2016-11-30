package proj1;


import edu.princeton.cs.introcs.In;
import lab_1_Maven.BinarySearch;
import se.kth.id1020.TinySearchEngineBase;
import se.kth.id1020.util.Attributes;
import se.kth.id1020.util.Document;
import se.kth.id1020.util.Word;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by AlstonQiLee on 2016-11-28.
 */
public class TinySearchEngine implements TinySearchEngineBase{
    ArrayList<Node> nodList;
    ArrayList<Node> backup;
    int sortcounter;
      //  ArrayList<Integer>index;
    public TinySearchEngine(){
       nodList = new ArrayList<Node>();
sortcounter=0;
       // index =new ArrayList<Integer>();
    }

    public void insert(Word word, Attributes attributes) {
         Node input = new Node(word,attributes);
        nodList.add(input);
    }

    public List<Document> search(String s) {
        List<Document> result= new ArrayList<Document>();

           Collections.sort(nodList, new Comparator<Node>() {
               public int compare(Node node1, Node node2) {
                   return node1.word.word.compareTo(node2.word.word);
               }
           });
           sortcounter++;

    backup=nodList;

        BinarySearch find = new BinarySearch();
        for(int i =0;i<nodList.size();i++) {
           int f = find.search(s, nodList);
            //int indi=0;
            if (f == -1){
                //System.out.println("NO MATCH!!");
                resetAll();
                return result;
            }else {
                    result.add(nodList.get(f).attributes.document);
                    backup.add(nodList.get(f));
                     nodList.remove(f);
                   // index.add(f);
                   // indi++;
                //nodList.get(find.search(s, nodList)).check = true;
            }
            }//for end
        resetAll();
        return result;
    }

    public void resetAll() {
       nodList=backup;
            return;
    }

    public class Node{
        public Word word ;

        public Attributes attributes;
        public Integer hashCode;
        //public boolean check;
        public Node(Word word, Attributes attributes){
            this.word = word;
            this.attributes=attributes;
            this.hashCode= word.hashCode();
            //check= false;
        }


    }
}

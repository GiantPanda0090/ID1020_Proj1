package proj1;


import lab_1_Maven.BinarySearch;
import se.kth.id1020.TinySearchEngineBase;
import se.kth.id1020.util.Attributes;
import se.kth.id1020.util.Document;
import se.kth.id1020.util.Word;

import java.util.*;

/**
 * Created by AlstonQiLee on 2016-11-28.
 */
public class TinySearchEngine implements TinySearchEngineBase{
    ArrayList<Node> nodList;
    ArrayList<Node> backup;
    //int sortcounter;
      //  ArrayList<Integer>index;
    public TinySearchEngine(){
       nodList = new ArrayList<Node>();

//sortcounter=0;
       // index =new ArrayList<Integer>();
    }

    public void insert(Word word, Attributes attributes) {
         Node input = new Node(word,attributes);

        nodList.add(input);
   // nodList.add(input);


    }

    String property="";
    String direction="";
    public List<Document> search(String s) {
        BinarySearch find = new BinarySearch();
        List<Document> result = new ArrayList<Document>();
        List<Node> nodeResult = new ArrayList<Node>();
        String[] str = s.split(" ");
        if(str.length!=1) {
            String[] filter = new String[2];
            filter[0] = str[str.length - 2];
            filter[1] = str[str.length - 1];
            int a = find.searcharr("count", filter);
            if (a == -1) {
                a = find.searcharr("popularity", filter);
            } else if (a == -1) {
                a = find.searcharr("occurrence", filter);
            }
                property = filter[a];

            int b = find.searcharr("asc", filter);
            if (b == -1) {
                find.searcharr("desc", filter);
            } else {
                direction = filter[b];
            }
        }
        Collections.sort(nodList, new Comparator<Node>() {
            public int compare(Node node1, Node node2) {
                return node1.word.word.compareTo(node2.word.word);
            }
        });
        backup = nodList;
        return search(str, result,nodeResult,0);
    }
        public List<Document> search(String str[], final List<Document> result,  List<Node> nodeResult,int j) {
        BinarySearch find = new BinarySearch();

        for(int i =0;i<nodList.size();i++) {
             int  f= find.search(str[j], nodList);
                if (f == -1) {

                    Set<Document> hs = new HashSet<Document>(result);
                    List<Document> b = new ArrayList<Document>(hs);
                    if(property.equals("count") ){
                    Collections.sort(b, new Comparator<Document>() {
                        public int compare(Document node1, Document node2) {
                            return Collections.frequency(result, node2)- Collections.frequency(result, node1);
                        }
                    });
                        property ="";
                    }
                    result.clear();
                    result.addAll(b);




                    resetAll();
                    return result;//out

                } else {
                  // if(result.contains(nodList.get(f).attributes.document))
                   result.add(nodList.get(f).attributes.document);
                    backup.add(nodList.get(f));
                    nodList.remove(f);
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
        public Node(Word word, Attributes attributes){
            this.word = word;
            this.attributes=attributes;
        }
    }

}

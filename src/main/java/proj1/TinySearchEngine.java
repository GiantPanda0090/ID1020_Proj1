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
        BinarySearch find = new BinarySearch();
         Node input = new Node(word,attributes);
        nodList.add(find.sort(input.word.word,nodList),input);//personaly invention "binary sort"
   // nodList.add(input);

    }

    String property="";
    String direction="";
    public List<Document> search(String s) {
        backup=new ArrayList<Node>(nodList);
       BinarySearch find = new BinarySearch();
        List<Document> result = new ArrayList<Document>();
        String[] str = s.split(" ");
        List<String> rebuild = new ArrayList<String>(Arrays.asList(str));
        resetAll();
        if(str.length>=3) {
            if(str[str.length-3].equals("orderby")){
                property =str[str.length-2];
                direction= str[str.length-1];
                rebuild.remove(rebuild.size()-3);
                rebuild.remove(rebuild.size()-2);
                rebuild.remove(rebuild.size()-1);
                str= rebuild.toArray(new String[str.length-3]);//new String[str.length-3];
            }else if(str[str.length-2].equals("orderby")){
                property =str[str.length-1];
                rebuild.remove(rebuild.size()-2);
                rebuild.remove(rebuild.size()-1);
                str= rebuild.toArray(new String[str.length-2]);
            }else if(str[str.length-1].equals("orderby")){
                System.err.println("orderby what?");
            }
        }
       /* Collections.sort(nodList, new Comparator<Node>() {
            public int compare(Node node1, Node node2) {
                return node1.word.word.compareTo(node2.word.word);
            }
        });*/

        return search(str, result,0,find);
    }
        public List<Document> search(String str[],  final List<Document> result,int j,BinarySearch find) {
        //BinarySearch find = new BinarySearch();
            resetAll();
        for(int i =0;i<nodList.size();i++) {
           //System.out.println(j);
             int  f= find.search(str[j], nodList);
                if (f == -1) {
                    if(j<str.length-1) {
                        //resetAll();
                        search(str, result, j + 1,find);
                    }
                    Set<Document> hs = new HashSet<Document>(result);
                    List<Document> b = new ArrayList<Document>(hs);

                    //property sort
                       if (property.equals("count")) {
                            Collections.sort(b, new Comparator<Document>() {
                                public int compare(Document node1, Document node2) {
                                    return Collections.frequency(result, node2) - Collections.frequency(result, node1);
                                }
                            });
                            property = "";
                            direction = "";
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
            nodList =new ArrayList<Node>( backup);
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

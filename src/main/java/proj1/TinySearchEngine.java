package proj1;


import lab_1_Maven.BinarySearch;
import lab_3.BubbleSort;
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
        List<Attributes> resultAtrr = new ArrayList<Attributes>();
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
                System.out.println("No order has been specified for this property. Ascending order will be used");

            }else if(str[str.length-1].equals("orderby")){
                System.err.println("orderby what?");
            }
        }
       /* Collections.sort(nodList, new Comparator<Node>() {
            public int compare(Node node1, Node node2) {
                return node1.word.word.compareTo(node2.word.word);
            }
        });*/

        return search(str, result,resultAtrr,0,find);
    }
        public List<Document> search(String str[],  final List<Document> result, final List<Attributes> resultAtrr,int j,BinarySearch find) {
        //BinarySearch find = new BinarySearch();
            resetAll();
        for(int i =0;i<nodList.size();i++) {
            //System.out.println(j);
            int f = find.search(str[j], nodList);
            if (f == -1) {
                if (str.length > 1) {
                    if (j < str.length - 1) {
                        //resetAll();
                        search(str, result, resultAtrr, j + 1, find);
                    } else if (j == str.length - 1) {
                        return result;
                    }
                }
                if (property.equals("occurrence")) {
                    Set<Attributes> hs = new HashSet<Attributes>(resultAtrr);
                    List<Attributes> b = new ArrayList<Attributes>(hs);

                    BubbleSort sort = new BubbleSort(b,result);
                    List a =sort.sortOccu();
                    b.clear();
                    b.addAll(a);
                   /* Collections.sort(b, new Comparator<Attributes>() {
                        public int compare(Attributes node1, Attributes node2) {
                            return node1.occurrence - node2.occurrence;
                        }
                    });*/
                    result.clear();
                    for (int resulti = 0; resulti < b.size(); resulti++) {
                        if(!result.contains(b.get(resulti).document)) {
                            result.add(b.get(resulti).document);
                        }
                    }
                } else{
                    Set<Document> hs = new HashSet<Document>(result);
                List<Document> b = new ArrayList<Document>(hs);


                    if (property.equals("popularity")) {
                    BubbleSort sort = new BubbleSort(b,result);
                        List a =sort.sortPopula();
                        b.clear();
                    b.addAll(a);
                    }
               /* if (property.equals("popularity")) {
                    Collections.sort(b, new Comparator<Document>() {
                        public int compare(Document node1, Document node2) {
                            return node1.popularity - node2.popularity;
                        }
                    });
                }*/
                //property sort
                if (property.equals("count")) {
                    BubbleSort sort = new BubbleSort(b,result);
                    List a =sort.sortFreq();
                    b.clear();
                    b.addAll(a);
                   /* Collections.sort(b, new Comparator<Document>() {
                        public int compare(Document node1, Document node2) {
                            return Collections.frequency(result, node1) - Collections.frequency(result, node2);
                        }
                    });*/
                }
                result.clear();
                result.addAll(b);
            }
             //property sort end

                //direction
                if(direction.equals("desc")){
                    System.out.println("Descending order will be applied");
                    Collections.reverse(result);//reverse result
                }else  if(direction.equals("asec")){
                    System.out.println("Ascending order will be applied");
                }

                //resetALL
                property = "";
                direction = "";
            resetAll();
                break;
                } else {
                if  (property.equals("occurrence")) {
                    resultAtrr.add(nodList.get(f).attributes);
                }else {
                    result.add(nodList.get(f).attributes.document);
                }
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

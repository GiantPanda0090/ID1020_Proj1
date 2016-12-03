package lab_3;

//import lab2.linkedlist;

import se.kth.id1020.util.Attributes;
import se.kth.id1020.util.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AlstonQiLee on 2016-11-16.
 * bubblesort lunch psvm
 */
public class BubbleSort {

    linkedlist sortlist;
    List b;
    List<Document> result;
    public BubbleSort(List b,List<Document> result) {
        this.b = b;
        this.result= result;
    }

    public List sortPopula() {
            sortlist = new linkedlist(1,result);
            List<Document> arr = new ArrayList<Document>(b);
            for (int i = 0; i < arr.size(); i++) {
                sortlist.add(arr.get(i));
            }
       sortlist.bubblesort();//bubble sort activated
        List out = new ArrayList(sortlist.printlist());
        return out;
    }
    public List sortFreq() {
        sortlist = new linkedlist(2,result);
        List<Document> arr = new ArrayList<Document>(b);
        for (int i = 0; i < arr.size(); i++) {
            sortlist.add(arr.get(i));
        }
        sortlist.bubblesort();//bubble sort activated
        List out = new ArrayList(sortlist.printlist());
        return out;
    }
    public List sortOccu() {
        sortlist = new linkedlist(3,result);
        List<Attributes> arr = new ArrayList<Attributes>(b);
        for (int i = 0; i < arr.size(); i++) {
            sortlist.addAttr(arr.get(i));
        }
        sortlist.bubblesort();//bubble sort activated
        List out = new ArrayList(sortlist.printlistAttr());
        return out;
    }
}
/**
 * answer for 2.2 3)
 * answer found from stack overflow
 * In an unstable algorithm, straw or spork may be interchanged, but in stable sort, they stay in the same
 * relative positions (that is, since 'straw' appears before 'spork' in the input, it also appears before 'spork' in the output).
 *
 * summerize in my own words
 * unstable sort will destroy the first come first serve rule. it swap in both > and >=. might cause trouble in for example
 * ticket selling system. apartment queing system. etc
 * stable sort only compare >. it wont swap in =
 */
/*
*answer for 2.3
* swap is double than the orignial list inversion
* no idea why? remember to ask on friday labatory
 */

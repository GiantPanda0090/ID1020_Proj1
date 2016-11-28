package lab_4;

import java.util.Iterator;

/**
 * Created by AlstonQiLee on 2016-11-24.
 */
public class Trie<Value>  {
    //MADE BY SYMBLE TABLE
    private static final int R = 256;//EXTEND ASCII
    int n;
    boolean mismatch;//NOT USED?
    private Node root;
//CONSTRUCTOR
    public Trie() {
        n = 0;

    }

//PUT
    public void put(String key) {
        root = put(root, key, 0);
    }

    private Node put(Node x, String key, int index) {
        if (x == null) {
            x = new Node();
        }
        if (index == key.length()) {
            if (x.value == null) {
                n++;//increase the length counter
                x.value =0;
            }//no hit!!
                x.value = Integer.parseInt(x.value.toString()) + 1;
                return x;

        }
        char c = key.charAt(index);//read one by one (recursion)
        x.next[c] = put(x.next[c], key, index + 1);
        return x;
    }
//GET
    public Value get(String key){
        Node x = get (root,key,0);
        if (x!=null){
            return (Value) x.value;//object paremetirize
        }
        else {
            System.err.println("No key Node found!! check get method or key value");
            return null;

        }
    }

    private Node get(Node x, String key,int i){
        if ( i == key.length()){
            return x;
        }//if it sucess go though all the key, its a hit
        char c = key.charAt(i);//loop though the string
        Node hit = get(x.next[c],key,i+1);//searching thought the node array and find the right tree
        //System.err.println("No key Node found!! check get method or key value");
        return hit;
    }

    //COUNT
    public int count(String key){
        Node x = root;
        int answer=0;
        if(get(key)== null){
            //System.err.println("Key does not exist!");
            return 0;
        }else{
            Node hit =search(x,key,0);
            Object save =hit.value;
            Object[] s = new Object[R];
            s[0]= save;
            for (int i =1; i< hit.next.length; i++){
                Node t =hit.next[i-1];
                if (t!=null) {
                    s[i] = t.value;
                }

            }

            for( int i =0; i<s.length;i++){

                if(s[i]!=null) {
                    answer = Integer.parseInt(s[i].toString()) + answer;
                }
            }
        }
        return answer;
    }

    //SEARCH
    private Node search(Node x, String key,int i){
        if ( i == key.length()){
            return x;
        }//if it sucess go though all the key, its a hit
        char c = key.charAt(i);//loop though the string
        Node hit = get(x.next[c],key,i+1);//searching thought the node array and find the right tree
        //System.err.println("No key Node found!! check get method or key value");
        return hit;
    }

//DEISTINCT
    public int distinct(String key){
        Node x = root;
        int answer=0;
        if(get(key)== null){
            System.err.println("Key does not exist!");
            return 0;
        }else{
            Node hit =search(x,key,0);
            for (int i =0; i< hit.next.length; i++){
                Node t =hit.next[i];
                if (t!=null) {
                    answer++;
                }

            }
        }
        return answer;
    }

    //ITERATOR
    int[] count = new int[10];
    String []word= new String[10];
    public Iterator<String> iterator(String prefix){

       final String key= prefix;
        Iterator itr = new Iterator() {
            int check=0;
            Node x = root;
            Node hit =search(x,key,0);
            Node t=hit.next[check];;



            public boolean hasNext() {
                //int answer=0;
                Node hit =search(x,key,0);
                boolean answer= check<hit.next.length-1 &&get(key)!= null;
                return answer;
            }


            public Object next() {
                //int answer=0;
                StringBuilder a=null;
                int i =0;
                //t=t.next[i];
                if (t!=null) {
                    Node z=null;
                        a = new StringBuilder();
                        a.append(key);
                        a.append(Character.toString((char) check));
                        // System.out.print(a.toString() + " ");
                    while(i<R) {
                        z = t.next[i];
                        if (z != null) {
                            a.append("-->");
                            a.append(key);
                            a.append(Character.toString((char) check));
                            a.append(Character.toString((char) i));
                           // System.out.print(a.toString() + " ");
                        }
                        i++;
                    }
                    //here

                        //System.out.print(get(i));

                    }
                check++;
                //System.out.print(check);
                t =hit.next[check];
                //System.out.print(a.toString() + " ");
                return a;
            }
            public void remove(){

            }
        };
        return itr;
    }

// INNER CLASS NODE ( ST STYLE)
    private static class Node {
        private Object value;
        private Node[] next = new Node[R]; //EXTENDED ASCii MADE A ALPHABATIC ORDER NODE ARRAY FOR LOADS OF TREE ROOT

    }

/*
* MAIN IDEA BASED ON ASCII TABLE CAST TO CHAR AND CHAR CAST TO ASCII CODE INT. SO THERE IS A WAY TO COMPARE STUFF AND
* MUCH EASY TO OPERATION. USE EXTENDED ASCII TWO INCASE ARRAY OVERFLOW.
* */





}

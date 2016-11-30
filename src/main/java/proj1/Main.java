package proj1;

import lab_4.Driver;
import se.kth.id1020.TinySearchEngineBase;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by AlstonQiLee on 2016-11-28.
 */
public class Main {
    public static void main(String[] args) throws Exception{
         TinySearchEngineBase searchEngine = new TinySearchEngine();
        se.kth.id1020.Driver Driver =new se.kth.id1020.Driver();
         Driver.run(searchEngine);
         }

}

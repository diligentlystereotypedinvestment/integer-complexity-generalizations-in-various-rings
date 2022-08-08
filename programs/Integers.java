import java.util.ArrayList; // import the ArrayList class
import java.util.Arrays;
import java.util.Collections;
import java.io.*;

public class Integers{
    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        int n = 16;

        ArrayList<ArrayList<Integer>> lists = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> cumulative = new ArrayList<Integer>();

        //FileWriter f = new FileWriter(new File("C:\\Users\\ajfro\\Downloads\\naturalupto1000"), true);
        // keeps track of all numbers that have already been used to avoid repeats

        for (int a = 0; a <= n; a++)
            lists.add(new ArrayList<Integer>());

        lists.get(0).add(0); // just to avoid off by 1 errors
        lists.get(1).add(1);
        lists.get(2).add(2);
        lists.get(3).add(3);
        lists.get(4).add(4);

        cumulative.add(0); //just in case
        cumulative.add(1);
        cumulative.add(2);
        cumulative.add(3);
        cumulative.add(4);

        int cumul = 0;

        int toBeAdded = 0;
        boolean add = true;

        for (int i = 5; i <= n; i++)
        {
            for (int j = 1; j <= i/2; j++)
                for (int k = 0; k < lists.get(j).size(); k++)
                {
                    for (int l = 0; l < lists.get(i-j).size(); l++)
                    {
                        toBeAdded = lists.get(j).get(k) + lists.get(i-j).get(l);
                        //check if toBeAdded is in cumulative
                        add = true;
                        for (int m = 0; m < cumulative.size(); m++)
                        {
                            if (toBeAdded == cumulative.get(m))
                                add = false;
                        }
                        if (add)
                        {
                            cumulative.add(toBeAdded);
                            lists.get(i).add(toBeAdded);
                        }

                        toBeAdded = lists.get(j).get(k) * lists.get(i-j).get(l);
                        //check if toBeAdded is in cumulative
                        add = true;
                        for (int m = 0; m < cumulative.size(); m++)
                        {
                            if (toBeAdded == cumulative.get(m))
                                add = false;
                        }
                        if (add)
                        {
                            cumulative.add(toBeAdded);
                            lists.get(i).add(toBeAdded);
                        }

                    }
                }
        }

        //System.out.println(lists);
        cumul = 0;
        for (int b = 1; b <= n; b++)
        {

            for(int i: lists.get(b)){
                System.out.println(i + ", " + b);
                //}

                //cumul += lists.get(b).size();
                //System.out.print(b + ":     ");
                //Collections.sort(lists.get(b));
                //System.out.println(lists.get(b));
                //System.out.println("Size = " + lists.get(b).size());

                //System.out.println("Cumulative Size = " + cumul);
                //System.out.println();

            }
            //f.flush();
            //f.close();

        }
    }
}
//This class does the operations of "building up" each set of integers with a certain complexity

import java.util.ArrayList; // import the ArrayList class
import java.util.Arrays;
import java.util.Collections;

public class IntegralExpressions{
    public static void main(String[] args)
    {
        int n = 10;

        ArrayList<ArrayList<IntegerExpression>> lists = new ArrayList<ArrayList<IntegerExpression>>();
        ArrayList<Integer> cumulative = new ArrayList<Integer>();
        // keeps track of all numbers that have already been used to avoid repeats

        for (int a = 0; a <= n; a++)
            lists.add(new ArrayList<IntegerExpression>());

        lists.get(0).add(new IntegerExpression(0, new ArrayList<Integer>())); // just to avoid off by 1 errors
        lists.get(1).add(new IntegerExpression(1, new ArrayList<Integer>()));

        cumulative.add(0); //just in case
        cumulative.add(1);

        int cumul = 0;

        IntegerExpression toBeAdded;
        boolean add = true;

        for (int i = 2; i <= n; i++)
        {
            for (int j = 1; j <= n/2; j++)
                for (int k = 0; k < lists.get(j).size(); k++)
                {
                    for (int l = 0; l < lists.get(i-j).size(); l++)
                    {
                        toBeAdded = new IntegerExpression(lists.get(j).get(k), lists.get(i-j).get(l), 1);
                        //check if toBeAdded is in cumulative
                        add = true;
                        for (int m = 0; m < cumulative.size(); m++)
                        {
                            if (toBeAdded.getI() == cumulative.get(m))
                                add = false;
                        }
                        if (add)
                        {
                            cumulative.add(toBeAdded.getI());
                            lists.get(i).add(toBeAdded);
                        }

                        toBeAdded = new IntegerExpression(lists.get(j).get(k), lists.get(i-j).get(l), -1);
                        //check if toBeAdded is in cumulative
                        add = true;
                        for (int m = 0; m < cumulative.size(); m++)
                        {
                            if (toBeAdded.getI() == cumulative.get(m))
                                add = false;
                        }
                        if (add)
                        {
                            cumulative.add(toBeAdded.getI());
                            lists.get(i).add(toBeAdded);
                        }
                    }
                }
        }

        //System.out.println(lists);
        cumul = 0;
        for (int b = 1; b <= n; b++)
        {
            cumul += lists.get(b).size();

            System.out.println(b + ":     ");
            System.out.println();
            Collections.sort(lists.get(b), (a,c) -> Integer.compare(a.getI(), c.getI()));

            if (b == 1)
                System.out.println("i = i");
            else
                for (int d = 0; d < lists.get(b).size(); d++)
                {
                    System.out.println(lists.get(b).get(d));
                }

            System.out.println();
            System.out.println("Size = " + lists.get(b).size());

            System.out.println("Cumulative Size = " + cumul);
            System.out.println();
            System.out.println();
        }

    }
}
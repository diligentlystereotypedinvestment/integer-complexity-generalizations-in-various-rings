//This class does the operations of "building up" each set of polynomials with a certain complexity

import java.util.ArrayList; // import the ArrayList class
import java.util.Arrays;
import java.util.Collections;
import java.io.*;

public class polynomialComplexity
{
    public static void main(String[] args)
    {

        int n = 17;

        ArrayList<ArrayList<polynomial>> lists = new ArrayList<ArrayList<polynomial>>();
        ArrayList<polynomial> cumulative = new ArrayList<polynomial>();

        for (int a = 0; a <= n; a++)
            lists.add(new ArrayList<polynomial>());

        ArrayList<Integer> start = new ArrayList<Integer>();
        start.add(0);
        start.add(1);
        lists.get(1).add(new polynomial(start)); // adds the expression x as having complexity 1

        cumulative.add(new polynomial(start));

        polynomial toBeAdded = null;    
        boolean add = true;

        ArrayList<Integer> negOne = new ArrayList<Integer>();
        negOne.add(-1);

        for (int i = 2; i <= n; i++)
        {
            for (int j = 1; j <= i/2; j++)
                for (int k = 0; k < lists.get(j).size(); k++)
                {
                    for (int l = 0; l < lists.get(i-j).size(); l++)
                    {
                        toBeAdded = polynomial.multiplication(lists.get(j).get(k),  lists.get(i-j).get(l)).scrapZeros();

                        //check if toBeAdded is in cumulative
                        add = true;
                        for (int m = 0; m < cumulative.size(); m++)
                        {

                            if (toBeAdded.toString().equals(cumulative.get(m).scrapZeros().toString()))
                            {
                                add = false;
                                break;
                            }

                        }

                        if (add)
                        {
                            cumulative.add(toBeAdded);
                            lists.get(i).add(toBeAdded);
                        }

                        toBeAdded = polynomial.addition(lists.get(j).get(k),  lists.get(i-j).get(l)).scrapZeros();
                        //check if toBeAdded is in cumulative
                        add = true;
                        for (int m = 0; m < cumulative.size(); m++)
                        {

                            if (toBeAdded.toString().equals(cumulative.get(m).scrapZeros().toString()))
                            {
                                add = false;
                                break;
                            }
                        }

                        if (add)
                        {
                            cumulative.add(toBeAdded);
                            lists.get(i).add(toBeAdded);
                        }

                    }
                }
            System.out.println("Complexity = " + i + ":");
            System.out.println("Size = " + lists.get(i).size());
            System.out.println();
        }

        /*
        for (int i = 1; i <= n; i++)
        {
            System.out.println("Complexity = " + i + ":");
            System.out.println("Size = " + lists.get(i).size());
            System.out.println();

            for (int j = 0; j < lists.get(i).size(); j++)
            {
                System.out.print(lists.get(i).get(j).scrapZeros());
                System.out.print("\t\t" + "SumCoeff = " + lists.get(i).get(j).getSum());
                System.out.print("\tDegree = " + (lists.get(i).get(j).getCoefficients().size()-1));
                System.out.println("\tTotal = " + ((lists.get(i).get(j).getCoefficients().size() + lists.get(i).get(j).getSum())-1));
            }

            System.out.println();
            System.out.println();
        }
        */

    }

}
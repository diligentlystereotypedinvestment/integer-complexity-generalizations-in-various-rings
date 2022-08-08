import java.util.ArrayList; // import the ArrayList class
import java.util.Arrays;
import java.util.Collections;

public class GaussianExpressions{
    public static void main(String[] args)
    {
        int n = 11;//*************************************************************************************

        ArrayList<ArrayList<GaussExpression>> lists = new ArrayList<ArrayList<GaussExpression>>();

        ArrayList<ArrayList<GaussExpression>> listsInteger = new ArrayList<ArrayList<GaussExpression>>();

        ArrayList<ArrayList<GaussExpression>> listsIntegerComplex = new ArrayList<ArrayList<GaussExpression>>(); //integers with complex components

        ArrayList<Gauss> cumulative = new ArrayList<Gauss>();
        // keeps track of all numbers that have already been used to avoid repeats

        for (int a = 0; a <= n; a++)
            lists.add(new ArrayList<GaussExpression>());

        for (int a = 0; a <= n; a++)
            listsInteger.add(new ArrayList<GaussExpression>());

        for (int a = 0; a <= n; a++)
            listsIntegerComplex.add(new ArrayList<GaussExpression>());

        lists.get(1).add(new GaussExpression (new Gauss(0, 1), new ArrayList<Integer>())); //starts off with i = []

        cumulative.add(new Gauss (0, 1)); //adds i to the cumulative ArrayList

        int cumul = 0; // number of total Gaussians reached

        GaussExpression toBeAdded;
        boolean add = true;

        for (int i = 2; i <= n; i++)
        {
            for (int j = 1; j <= n/2; j++)
                for (int k = 0; k < lists.get(j).size(); k++)
                {
                    for (int l = 0; l < lists.get(i-j).size(); l++)
                    {
                        toBeAdded = new GaussExpression(lists.get(j).get(k), lists.get(i-j).get(l), 1);
                        //check if toBeAdded is in cumulative
                        add = true;
                        for (int m = 0; m < cumulative.size(); m++)
                        {
                            if (toBeAdded.getG().equals(cumulative.get(m))) //checks if the Gaussian Integer is a repeat
                                add = false;
                        }
                        if (add)
                        {
                            cumulative.add(toBeAdded.getG()); //adds the integer
                            lists.get(i).add(toBeAdded); //adds the expression

                            if ((lists.get(j).get(k).getG().getA() != 0 && lists.get(j).get(k).getG().getB() != 0) && (lists.get(i-j).get(l).getG().getA() != 0 && lists.get(i-j).get(l).getG().getB() != 0))
                                listsIntegerComplex.get(i).add(toBeAdded);

                            if (toBeAdded.getG().getB() == 0 || toBeAdded.getG().getA()==0) // if the GaussInt is imaginary or an integer
                            {
                                listsInteger.get(i).add(toBeAdded);

                                if ((lists.get(j).get(k).getG().getA() != 0 && lists.get(j).get(k).getG().getB() != 0) || (lists.get(i-j).get(l).getG().getA() != 0 && lists.get(i-j).get(l).getG().getB() != 0))
                                    listsIntegerComplex.get(i).add(toBeAdded);
                            }
                        }

                        toBeAdded = new GaussExpression(lists.get(j).get(k), lists.get(i-j).get(l), -1);
                        //check if toBeAdded is in cumulative
                        add = true;
                        for (int m = 0; m < cumulative.size(); m++)
                        {
                            if (toBeAdded.getG().equals(cumulative.get(m)))
                                add = false;
                        }
                        if (add)
                        {
                            cumulative.add(toBeAdded.getG());
                            lists.get(i).add(toBeAdded);

                            if ((lists.get(j).get(k).getG().getA() != 0 && lists.get(j).get(k).getG().getB() != 0) && (lists.get(i-j).get(l).getG().getA() != 0 && lists.get(i-j).get(l).getG().getB() != 0))
                                listsIntegerComplex.get(i).add(toBeAdded);

                            if (toBeAdded.getG().getB() == 0 || toBeAdded.getG().getA()==0) // if the GaussInt is imaginary or an integer
                            {
                                listsInteger.get(i).add(toBeAdded);
                                if ((lists.get(j).get(k).getG().getA() != 0 && lists.get(j).get(k).getG().getB() != 0) || (lists.get(i-j).get(l).getG().getA() != 0 && lists.get(i-j).get(l).getG().getB() != 0))
                                    listsIntegerComplex.get(i).add(toBeAdded);
                            }

                        }
                    }
                }

        }
        //System.out.println(lists);
        //for all Gaussian Integers
        cumul = 0;

        for (int b = 1; b <= n; b++)
        {
            cumul += listsIntegerComplex.get(b).size();

            System.out.print(b + ":     ");
            Collections.sort(listsIntegerComplex.get(b), (a,c) -> Integer.compare(a.getG().norm(), c.getG().norm()));
            System.out.println(listsIntegerComplex.get(b));
            System.out.println("Size = " + listsIntegerComplex.get(b).size());

            System.out.println("Cumulative Size = " + cumul);
            System.out.println();
        }

        //for imaginary and integers
        /*
        for (int b = 1; b <= n; b++)
        {
        cumul += lists.get(b).size();

        System.out.println(b + ":     ");
        Collections.sort(lists.get(b), (a,c) -> Integer.compare(a.getG().norm(), c.getG().norm()));

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
         */
    }
}

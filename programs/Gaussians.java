import java.util.ArrayList; // import the ArrayList class
import java.util.Arrays;
import java.util.Collections;

public class Gaussians{
    public static void main(String[] args)
    {
        int n = 17;//*************************************************************************************

        ArrayList<ArrayList<Gauss>> lists = new ArrayList<ArrayList<Gauss>>();

        ArrayList<ArrayList<Gauss>> listsInteger = new ArrayList<ArrayList<Gauss>>();

        ArrayList<Gauss> complexFactor = new ArrayList<Gauss>();

        ArrayList<Gauss> lessComplex = new ArrayList<Gauss>(); //will give all Gaussian integers n + mi that are less complex than n

        ArrayList<Gauss> cumulative = new ArrayList<Gauss>();
        // keeps track of all numbers that have already been used to avoid repeats

        for (int a = 0; a <= n; a++)
            lists.add(new ArrayList<Gauss>());

        for (int a = 0; a <= n; a++)
            listsInteger.add(new ArrayList<Gauss>());

        lists.get(1).add(new Gauss (0, 1)); //starts off with i

        cumulative.add(new Gauss (0, 1));

        int cumul = 0; // number of total Gaussians reached

        Gauss toBeAdded = new Gauss(0);
        boolean add = true;
        boolean isLessComplex;

        for (int i = 2; i <= n; i++)
        {
            for (int j = 1; j <= n/2; j++)
                for (int k = 0; k < lists.get(j).size(); k++)
                {
                    for (int l = 0; l < lists.get(i-j).size(); l++)
                    {
                        toBeAdded = Gauss.add(lists.get(j).get(k), lists.get(i-j).get(l));
                        //check if toBeAdded is in cumulative
                        add = true;
                        for (int m = 0; m < cumulative.size(); m++)
                        {
                            if (toBeAdded.equals(cumulative.get(m)))
                                add = false;
                        }
                        if (add)
                        {
                            cumulative.add(toBeAdded);
                            lists.get(i).add(toBeAdded);

                            isLessComplex = true;
                            for (int m = 0; m < cumulative.size(); m++)
                            {
                                if ((new Gauss(toBeAdded.getA())).equals(cumulative.get(m))) // if the real part is already seen
                                    isLessComplex = false;
                            }

                            if (isLessComplex)
                            {
                                lessComplex.add(toBeAdded);
                            }

                            if (toBeAdded.getB() == 0 && toBeAdded.getA()>=0)
                            {
                                listsInteger.get(i).add(toBeAdded);
                            }
                        }

                        toBeAdded = Gauss.multiply(lists.get(j).get(k), lists.get(i-j).get(l));
                        //check if toBeAdded is in cumulative
                        add = true;
                        for (int m = 0; m < cumulative.size(); m++)
                        {
                            if (toBeAdded.equals(cumulative.get(m)))
                                add = false;
                        }

                        if (add)
                        {
                            cumulative.add(toBeAdded);
                            lists.get(i).add(toBeAdded);

                            isLessComplex = true;
                            for (int m = 0; m < cumulative.size(); m++)
                            {
                                if ((new Gauss(toBeAdded.getA(), 0)).equals(cumulative.get(m))) // if the real part is already seen
                                    isLessComplex = false;
                            }

                            if (isLessComplex)
                            {
                                lessComplex.add(toBeAdded);
                            }

                            if (lists.get(j).get(k).getA() != 0 && lists.get(j).get(k).getB() != 0 && lists.get(i-j).get(l).getA() != 0 && lists.get(i-j).get(l).getB() != 0)
                                complexFactor.add(toBeAdded);

                            if (toBeAdded.getB() == 0 && toBeAdded.getA()>=0)
                            {
                                listsInteger.get(i).add(toBeAdded);
                            }

                        }
                    }
                }

        }
        //System.out.println(lists);
        //for all Gaussian Integers
        cumul = 0;

        //System.out.println(lessComplex);
        //System.out.println(complexFactor);
        /*
        for (int b = 1; b <= n; b++)
        {
        cumul += lists.get(b).size();

        System.out.print(b + ":     ");

        Collections.sort(lists.get(b), (a,c) -> Integer.compare(a.norm(), c.norm())); // sort by norm
        Collections.sort(lists.get(b), (a,c) -> Integer.compare(a.getA(), c.getA())); // sort by real part
        System.out.println(lists.get(b));
        Collections.sort(listsInteger.get(b), (a,c) -> Integer.compare(a.getA(), c.getA())); // sort by real part
        System.out.println(listsInteger.get(b));
        System.out.println("Size = " + lists.get(b).size());

        System.out.println("Cumulative Size = " + cumul);
        System.out.println();
        }
         */

        //for imaginary and integers
        
        // prints stuff in lists
        /*
        for (int b = 1; b <= n; b++)
        {
            cumul += listsInteger.get(b).size();

            System.out.print(b + ":     ");
            Collections.sort(listsInteger.get(b), (a,c) -> Integer.compare(a.norm(), c.norm()));
            System.out.println(listsInteger.get(b));
            System.out.println("Size = " + listsInteger.get(b).size());

            System.out.println("Cumulative Size = " + cumul);
            System.out.println();
        }
        */
        
        for (int b = 1; b <= n; b++)
        {
            for (int i = 0; i < listsInteger.get(b).size(); i++)
            {
                System.out.println("" + listsInteger.get(b).get(i) + ", " + b);
            }
        }

       
        //System.out.println(complexFactor);
    }
}

import java.util.Scanner;
import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
/**
 * Write a description of class zelinsky here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class zelinsky
{
    public static void main(String[] args) throws FileNotFoundException, IOException {
        int[][] real = new int[1000][2];
        System.out.println(zMethod(31, 0));
        int max = 50;
        FileWriter f = new FileWriter(new File("C:\\Users\\ajfro\\Downloads\\zelinsky-v-real"));
        Scanner scan = new Scanner(new File("C:\\Users\\ajfro\\Downloads\\naturalupto1000"));
        for(int i = 1; i < 1000; i++){
            String[] line = scan.nextLine().split(", ");
            real[i] = new int[] {Integer.valueOf(line[0]), Integer.valueOf(line[1])};
        }
        for(int n = 1; n < max + 1; n++){
            int zelinsky = zMethod(n, 0);
            int index = indexOf(real, n);
            if(real[index][1] == zelinsky){
                f.write(n + ", " + zelinsky + "\n");
            }
        }
        f.close();
    }
    
    public static int indexOf(int[][] arr, int find){
        for(int i = 0; i < arr.length; i++){
            if(arr[i][0] == find){
                return i;
            }
        }
        return -1;
    }
    
    public static int zMethod(int n, int comp){
        if(n == 0)
            return comp;
        if(n == 1)
            return comp;
        int base2 = 0;
        int base3 = 0;
        if(n % 2 == 1)
            base2 = zMethod((n - 1) / 2, comp + 3);
        if(n % 2 == 0)
            base2 = zMethod(n / 2, comp + 2);
        if(n % 3 == 2)
            base3 = zMethod((n - 2) / 3, comp + 5);
        if(n % 3 == 1)
            base3 = zMethod((n - 1) / 3, comp + 4);
        if(n % 3 == 0)
            base3 = zMethod(n / 3, comp + 3);
        if(base2 < base3)
            return base2;
        return base3;
    }
}
/* Use the slash-star style comments or the system won't see your
   identification information */
/*
ID: josephk4
LANG: JAVA
TASK: backforth
*/
import java.io.*;
import java.util.*;

class backforth {
    static Set<Integer> set = new HashSet<>();
  public static void main (String [] args) throws IOException {

    BufferedReader f = new BufferedReader(new FileReader("backforth.in"));

    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("backforth.out")));
        
    int[] ar = new int[11];
    int[] ar2 = new int[11];

    StringTokenizer st = new StringTokenizer(f.readLine());
    for(int i = 0; i < 10; i++){
        ar[i] = (Integer.parseInt(st.nextToken()));
    }

    st = new StringTokenizer(f.readLine());
    for(int i = 0; i < 10; i++){
        ar2[i] = (Integer.parseInt(st.nextToken()));
    }

    bringMilk(ar, ar2);

    for(int i : set){
        System.out.println(i);
    }

    out.print(set.size());
    out.close();
    f.close();
    }
    static void bringMilk (int[] a, int[] b){
        int[] ar = new int[11];
        int[] ar2 = new int[11];
        for(int i = 0; i < 10; i++){
            ar[i] = a[i];
            ar2[i] = b[i];
        }

        for(int i = 0; i < 10; i++){
            ar2[10] = ar[i]; ar[i] = -1;
            bringMilk1(ar, ar2, 1000 - ar2[10], 1);
            ar[i] = ar2[10];
        }
        return;
    }
    static void bringMilk1 (int[] a, int[] b, int milk, int n){
        int[] ar = new int[11];
        int[] ar2 = new int[11];
        for(int i = 0; i < 11; i++){
            ar[i] = a[i];
            ar2[i] = b[i];
        }

        

        for(int i = 0; i < 11; i++){
            if(!(ar2[i] < 0)){
                ar[10] = ar2[i]; ar2[i] = -1;
                bringMilk2(ar, ar2, milk + ar[10], n+1);
                ar2[i] = ar[10];
            }
        }
        return;
    }

    static void bringMilk2 (int[] a, int[] b, int milk, int n){
        int[] ar = new int[11];
        int[] ar2 = new int[11];
        for(int i = 0; i < 11; i++){
            ar[i] = a[i];
            ar2[i] = b[i];
        }

        if(n == 4){
            set.add(milk);
            return;
        }


        for(int i = 0; i < 11; i++){
            if(!(ar[i] < 0)){
                ar2[10] = ar[i]; ar[i] = -1;
                bringMilk1(ar, ar2, milk - ar2[10], n+1);
                ar[i] = ar2[10];
            }
        }
        return;
    }
}
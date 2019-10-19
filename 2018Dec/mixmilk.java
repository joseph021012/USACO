/* Use the slash-star style comments or the system won't see your
   identification information */
/*
ID: josephk3
LANG: JAVA
TASK: mixmilk
*/
import java.io.*;
import java.util.*;

class mixmilk {
  public static void main (String [] args) throws IOException {

    BufferedReader f = new BufferedReader(new FileReader("mixmilk.in"));

    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("mixmilk.out")));

    int[] ar = new int[3];
    int[] ar2 = new int[3];

    for(int i = 0; i < 3; i++){
        StringTokenizer st = new StringTokenizer(f.readLine());
        ar2[i] = Integer.parseInt(st.nextToken());
        ar[i] = Integer.parseInt(st.nextToken());
    }

    for(int i = 0; i < 100; i++){
        int a = i%3;
        int b = (i+1)%3;

        ar[b] += ar[a];
        if(ar[b] > ar2[b]){
            ar[a] = ar[b] - ar2[b];
            ar[b] = ar2[b];
        } else{
            ar[a] = 0;
        }
    }

    for(int i = 0; i < 3; i++){
        out.println(ar[i]);
    }
		
    out.close();
    f.close();
  }
}
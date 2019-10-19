/* Use the slash-star style comments or the system won't see your
   identification information */
/*
ID: josephk4
LANG: JAVA
TASK: blist
*/
import java.io.*;
import java.util.*;

class blist {
  public static void main (String [] args) throws IOException {

    BufferedReader f = new BufferedReader(new FileReader("blist.in"));

    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("blist.out")));

    StringTokenizer st = new StringTokenizer(f.readLine());
    int n = Integer.parseInt(st.nextToken());
    int max = 0;
    
    int[] buckets = new int[1000]; 

    for(int i = 0; i < n; i++){
        st = new StringTokenizer(f.readLine());
        int s = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        for(int i2 = s-1; i2 < t; i2++){
            buckets[i2] += b;

            if(buckets[i2] > max){
                max = buckets[i2];
            }
        }
    }
		
    out.println(max);
    out.close();                                  // close the output file
    f.close();
  }
}
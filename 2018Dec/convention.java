/* Use the slash-star style comments or the system won't see your
   identification information */
/*
ID: josephk3
LANG: JAVA
TASK: convention
*/
import java.io.*;
import java.util.*;

class convention {
  public static void main (String [] args) throws IOException {

    BufferedReader f = new BufferedReader(new FileReader("convention.in"));

    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("convention.out")));

    StringTokenizer st = new StringTokenizer(f.readLine());

    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());
    int c = Integer.parseInt(st.nextToken());

    int[] ar = new int[n];
    st = new StringTokenizer(f.readLine());

    for(int i = 0; i < n; i++){
        ar[i] = Integer.parseInt(st.nextToken());
    }

    Arrays.sort(ar);
    int max = ar[ar.length-1] - ar[0];
    int minMax = max/2;
    int min = 0;
    while(min < max){
        minMax = (max+min)/2;
        int buses = 1;
        int passengers = 1;
        for(int i = 1; i < ar.length; i++){
            if(passengers < c && (ar[i] - ar[i-1]) <= minMax){
                passengers++;
            } else{
                buses++;
                passengers = 1;
            }
        }
        if(buses <= m){
            max = minMax;
            System.out.println(max);
            if(max-min == 1){
                minMax = max;
                break;
            }
        } else{
            min = minMax;
            System.out.println(min);
            if(max-min == 1){
                minMax = min;
                break;
            }
        }
    }
    
    out.print(minMax);
    out.close();
    f.close();
  }
}
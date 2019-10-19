/* Use the slash-star style comments or the system won't see your
   identification information */
/*
ID: josephk3
LANG: JAVA
TASK: convention
*/
import java.io.*;
import java.util.*;

class convention2 {
  public static void main (String [] args) throws IOException {

    BufferedReader f = new BufferedReader(new FileReader("convention2.in"));

    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("convention2.out")));

    StringTokenizer st = new StringTokenizer(f.readLine());

    int n = Integer.parseInt(st.nextToken());

    LinkedList<Event> events = new LinkedList<>();

    for(int i = 0; i < n; i++){
        st = new StringTokenizer(f.readLine());
        int a = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        events.add(new Event(a, t, i));
    }

    printAr(events);
    Collections.sort(events, new Sort());
    printAr(events);

    LinkedList<Event> waiting = new LinkedList<>();
    waiting.add(events.pop());
    int max = 0;
    int time = waiting.get(0).a;
    Event current = waiting.get(0);

    while(!events.isEmpty() || !waiting.isEmpty()){
        Collections.sort(waiting, new SSort());
        
        if(waiting.isEmpty() && events.isEmpty()){
            break;
        }

        printAr(waiting);
        current = waiting.pop();
        
        time = time + current.t;

        outer: while(true){
            if(!events.isEmpty()){
                if(events.get(0).a <= time){
                    waiting.add(events.pop());
                } else{
                    break outer;
                }
            } else{
                break outer;
            }
        }

        for(int i = 0; i < waiting.size(); i++){
            if((time - waiting.get(i).a) > max && waiting.get(i).a < time){
                max = time - waiting.get(i).a;
            }
        }

        if(waiting.isEmpty()){
            if(!events.isEmpty()){
                waiting.add(events.pop());
            }
        }
    }

    out.print(max);
		
    out.close();
    f.close();
  }
  static void printAr(LinkedList<Event> ar){
      for(int i = 0; i < ar.size(); i++){
          System.out.print(ar.get(i).a + " ");
      }
      System.out.println();
  }
}

class Event{
    int a;
    int t;
    int s;
    Event(int a, int t, int s){
        this.a = a; this.t = t; this. s = s;
    }
}

class Sort implements Comparator<Event>{
    public int compare(Event a, Event b) 
    { 
        return a.a - b.a; 
    } 
}

class SSort implements Comparator<Event>{
    public int compare(Event a, Event b) 
    { 
        return a.s - b.s; 
    } 
}
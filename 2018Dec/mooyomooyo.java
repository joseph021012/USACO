import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class mooyomooyo {
    static boolean debug = false;

    static int N, K;
    static int[][] ar;

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("mooyomooyo.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("mooyomooyo.out")));

        StringTokenizer st = new StringTokenizer(f.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        ar = new int[N][10];

        for (int r = 0; r < N; r++) {
            String line = f.readLine();
            for (int c = 0; c < 10; c++) {
                ar[r][c] = Integer.parseInt(line.substring(c, c + 1));
            }
        }

        if (debug)
            print2d(ar);

        while (true) {

            boolean changed = false;
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < 10; c++) {
                    if (floodfill(r, c))
                        changed = true;
                }
            }

            if (debug) {
                System.out.println("flood fill");
                print2d(ar);
            }

            if (!changed)
                break;

            for (int c = 0; c < 10; c++) {
                int rr = N - 1; // trailing r
                for (int r = N - 1; r >= 0; r--) {
                    if (ar[r][c] != 0) {
                        if (rr == r)
                            rr--;
                        else {
                            ar[rr--][c] = ar[r][c];
                            ar[r][c] = 0;
                        }
                    }
                }
            }

            if (debug) {
                System.out.println("drop");
                print2d(ar);
            }
        }

        // print output
        for (int[] row : ar) {
            for (int c : row) {
                out.print(c);
            }
            out.println();
        }

        out.close();
    }

    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    static boolean floodfill(int r, int c) {
        if (ar[r][c] == 0)
            return false;

        boolean[][] ff = new boolean[N][10]; // for flood fill
        int count = 1;
        ff[r][c] = true;

        Queue<Pt> q = new LinkedList<>();
        q.add(new Pt(c, r));

        while (!q.isEmpty()) {
            Pt u = q.remove();

            for (int i = 0; i < 4; i++) {
                int x = u.x + dx[i];
                int y = u.y + dy[i];

                if (x >= 0 && x < 10 && y >= 0 && y < N && !ff[y][x] && ar[y][x] == ar[r][c]) {
                    ff[y][x] = true;
                    count++;
                    q.add(new Pt(x, y));
                }
            }
        }

        if (count >= K) {
            for (int rr = 0; rr < N; rr++) {
                for (int cc = 0; cc < 10; cc++) {
                    if (ff[rr][cc]) {
                        ar[rr][cc] = 0;
                    }
                }
            }
            return true;
        } else {
            return false;
        }

    }

    static void print2d(int[][] ar) {
        for (int[] row : ar) {
            for (int c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
        System.out.println("----------");
    }

    static class Pt {
        int x, y;

        public Pt(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return x + "," + y;
        }

        @Override
        public boolean equals(Object obj) {
            Pt o = (Pt) obj;
            return o.x == x && o.y == y;
        }

        @Override
        public int hashCode() {
            return x * 10 + y;
        }
    }
}
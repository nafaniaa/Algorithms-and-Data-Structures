import java.io.*;
import java.util.*;

public class Main {

    static int findDad(int a, int[] dsu) {
        ArrayList<Integer> path = new ArrayList<>();
        while (dsu[a] >= 0) {
            path.add(a);
            a = dsu[a];
        }
        int root = a;
        for (int i = 0; i < path.size(); i++) {
            int node = path.get(i);
            dsu[node] = root;
        }
        return root;
    }

    static void buildRoad(int road1, int road2, int[] dsu, int[] componentsOfConnectivity) {
        road1 = findDad(road1, dsu);
        road2 = findDad(road2, dsu);
        if (road1 != road2) {
            if (dsu[road1] > dsu[road2]) {
                dsu[road1] += dsu[road2];
                dsu[road2] = road1;
            }
            else {
                dsu[road2] += dsu[road1];
                dsu[road1] = road2;
            }
            componentsOfConnectivity[0]--;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("D://algos//fucking_roads//src//input.txt"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("D://algos//fucking_roads//src//output.txt")));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        int[] dsu = new int[n + 1];
        Arrays.fill(dsu, -1);
        int[] componentsOfConnectivity = {n};

        int[][] roads = new int[m][2];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            roads[i][0] = Integer.parseInt(st.nextToken());
            roads[i][1] = Integer.parseInt(st.nextToken());
        }

        boolean[] isRoad = new boolean[m];
        int[] destroyedRoads = new int[q];
        for (int i = 0; i < q; i++) {
            int a = Integer.parseInt(br.readLine());
            destroyedRoads[i] = a - 1;
            isRoad[a - 1] = false;
        }

        for (int i = 0; i < m; i++) {
            if (isRoad[i]) {
                buildRoad(roads[i][0], roads[i][1], dsu, componentsOfConnectivity);
            }
        }

        char[] result = new char[q];
        for (int i = q - 1; i >= 0; i--) {
            int city1 = roads[destroyedRoads[i]][0];
            int city2 = roads[destroyedRoads[i]][1];
            if (componentsOfConnectivity[0] == 1) {
                result[i] = '1';
            }
            else {
                result[i] = '0';
            }
            buildRoad(city1, city2, dsu, componentsOfConnectivity);
        }
        for (int i = 0; i < q; i++) {
            pw.print(result[i]);
        }

        br.close();
        pw.close();
    }
}

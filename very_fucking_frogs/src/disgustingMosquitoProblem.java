import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class disgustingMosquitoProblem {

    public static int iHateThisTask(ArrayList<Integer> list, int n) {
        if (n == 1) {
            return list.get(0);
        }
        if (n == 2) {
            return -1;
        }
        ArrayList<Integer> sum = new ArrayList<>(n + 1);
        sum.add(0);
        sum.add(list.get(0));
        sum.add(-1);

        for (int i = 3; i <= n; ++i) {
            sum.add(Math.max(sum.get(i - 3), sum.get(i - 2)) + list.get(i - 1));
        }
        return sum.get(n);
    }

    public static ArrayList<Integer> findOptimalRoute(ArrayList<Integer> list, int n) {
        ArrayList<Integer> route = new ArrayList<>();
        ArrayList<Integer> sum = new ArrayList<>(n + 1);
        sum.add(-2);
        sum.add(list.get(0));
        sum.add(-1);

        for (int i = 3; i <= n; ++i) {
            sum.add(Math.max(sum.get(i - 3), sum.get(i - 2)) + list.get(i - 1));
        }

        route.add(n);
        int i = n - 2;
        while (i >= 1) {
            if (sum.get(i) > sum.get(i - 1)) {
                route.add(i);
                i -= 2;
            } else {
                route.add(i - 1);
                i -= 3;
            }
        }
        Collections.reverse(route);
        return route;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        ArrayList<Integer> mosquitoes = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            mosquitoes.add(scanner.nextInt());
        }
        int maxMosquitoes = iHateThisTask(mosquitoes, n);
        System.out.println(maxMosquitoes);
        if (n > 2) {
            ArrayList<Integer> route = findOptimalRoute(mosquitoes, n);
            for (int i = 0; i < route.size(); ++i) {
                System.out.print(route.get(i) + " ");
            }
        }
        scanner.close();
    }
}

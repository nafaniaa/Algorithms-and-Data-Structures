import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] vec = new int[n];
        for (int i = 0; i < n; i++) {
            vec[i] = scanner.nextInt();
        }

        int mosquito;
        Set<Integer> lily = new HashSet<>();

        if (n == 1) {
            lily.add(1);
        }
        int[] f = new int[n];

        f[0] = vec[0];
        f[1] = -1;
        for (int i = 2; i < n; i++) {
            if (i == 2) {
                f[i] = f[i - 2] + vec[i];
            } else {
                int prevPrev = i - 3 >= 0 ? f[i - 3] : 0;
                f[i] = Math.max(f[i - 2], prevPrev) + vec[i];
            }
        }

        int i = n - 1;
        lily.add(n);
        while (i >= 1) {
            if (i == 2 || i == 3) {
                lily.add(1);
                break;
            }
            if (f[i - 2] > f[i - 3]) {
                lily.add(i - 1);
                i -= 2;
            } else {
                lily.add(i - 2);
                i -= 3;
            }
        }

        mosquito = f[n - 1];
        System.out.println(mosquito);
        if (mosquito != -1) {
            for (int l : lily) {
                System.out.print(l + " ");
            }
        }
    }
}

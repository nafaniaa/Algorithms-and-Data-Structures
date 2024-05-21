import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class SegmentTree {
    private final int[] tree;
    private final int[] array;

    public SegmentTree(int[] array) {
        this.array = array;
        this.tree = new int[4 * array.length];
        buildTree(0, 0, array.length);
    }

    private void buildTree(int node, int start, int end) {
        if (start == end - 1) {
            tree[node] = array[start];
        } else {
            int mid = (start + end) / 2;
            buildTree(2 * node + 1, start, mid);
            buildTree(2 * node + 2, mid, end);
            tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
        }
    }

    public void update(int index, int value) {
        updateHelper(0, 0, array.length, index, value);
    }

    private void updateHelper(int node, int start, int end, int index, int value) {
        if (start == end - 1) {
            tree[node] += value;
        } else {
            int mid = (start + end) / 2;
            if (index < mid) {
                updateHelper(2 * node + 1, start, mid, index, value);
            } else {
                updateHelper(2 * node + 2, mid, end, index, value);
            }
            tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
        }
    }

    public int query(int left, int right) {
        return queryHelper(0, 0, array.length, left, right);
    }

    private int queryHelper(int node, int start, int end, int left, int right) {
        if (start >= right || end <= left) {
            return 0;
        }
        if (start >= left && end <= right) {
            return tree[node];
        }
        int mid = (start + end) / 2;
        int leftSum = queryHelper(2 * node + 1, start, mid, left, right);
        int rightSum = queryHelper(2 * node + 2, mid, end, left, right);
        return leftSum + rightSum;
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(reader.readLine());
        int[] array = new int[n];
        StringTokenizer st = new StringTokenizer(reader.readLine());
        for (int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }
        SegmentTree segmentTree = new SegmentTree(array);
        int q = Integer.parseInt(reader.readLine());
        while (q-- > 0) {
            StringTokenizer query = new StringTokenizer(reader.readLine());
            String type = query.nextToken();
            if (type.equals("Add")) {
                int index = Integer.parseInt(query.nextToken());
                int value = Integer.parseInt(query.nextToken());
                segmentTree.update(index, value);
            } else if (type.equals("FindSum")) {
                int left = Integer.parseInt(query.nextToken());
                int right = Integer.parseInt(query.nextToken());
                int result = segmentTree.query(left, right);
                writer.write(result + "\n");
            }
        }
        writer.flush();
    }
}

import java.io.*;

class BinaryTree {
    Node root;

    BinaryTree() {
        root = null;
    }

    void insert(int key) {
        root = insertRec(root, key);
    }

    Node insertRec(Node root, int key) {
        if (root == null) {
            root = new Node(key);
            return root;
        }
        if (key < root.key) {
            root.left = insertRec(root.left, key);
        } else if (key > root.key) {
            root.right = insertRec(root.right, key);
        }
        return root;
    }

    void preOrderTraversal(Node node, FileWriter fileWriter) throws IOException {
        if (node != null) {
            fileWriter.write(String.valueOf(node.key) + '\n');
            preOrderTraversal(node.left, fileWriter);
            preOrderTraversal(node.right, fileWriter);
        }
    }

    class Node {
        int key;
        Node left;
        Node right;

        Node(int key) {
            this.key = key;
            left = null;
            right = null;
        }
    }
}

public class Main implements Runnable {
    public static void main(String[] args) {
        new Thread(null, new Main(), "", 64 * 1024 * 1024).start();
    }

    @Override
    public void run() {
        String inputFileName = "D://algos//bst_task1_3//src//input.txt";
        String outputFileName = "D://algos//bst_task1_3//src//output.txt";
        BinaryTree bt = new BinaryTree();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
             FileWriter writer = new FileWriter(outputFileName, false)) {
            String line;
            while ((line = reader.readLine()) != null) {
                bt.insert(Integer.parseInt(line));
            }

            bt.preOrderTraversal(bt.root, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

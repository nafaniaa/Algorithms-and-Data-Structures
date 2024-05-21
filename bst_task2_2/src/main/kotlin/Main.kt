import java.io.*;

class BinaryTree{
    Node head;

    BinaryTree(){
        head = null;
    }

    void insert(int key){
        if (head == null){
            head = new Node(key);
        }
        else{
            Node currentItem = head;
            while (true){
                if (key == currentItem.key){
                    break;
                }
                if (key < currentItem.key){
                    if (currentItem.left == null){
                        currentItem.left = new Node(key);
                        break;
                    }
                    else{
                        currentItem = currentItem.left;
                    }
                } else {
                    if (currentItem.right == null){
                        currentItem.right = new Node(key);
                        break;
                    } else{
                        currentItem = currentItem.right;
                    }
                }
            }
        }
    }

    void deleteNode(int value){
        head = deleteRec(head, value);
    }

    Node deleteRec(Node root, int key){
        if (root == null){
            return root;
        }
        if (key < root.key){
            root.left = deleteRec(root.left, key);
        }
        else if (key > root.key){
            root.right = deleteRec(root.right, key);
        }
        else{
            if (root.left == null){
                return root.right;
            }
            else if (root.right == null){
                return root.left;
            }

            root.key = minValue(root.right);
            root.right = deleteRec(root.right, root.key);
        }
        return root;
    }

    int minValue (Node root){
        int minv = root.key;
        while (root.left != null){
            minv = root.left.key;
            root = root.left;
        }
        return minv;
    }

    void preOrderTraversal(Node node, FileWriter fileWriter) throws IOException {
        if (node == null){
            return;
        }
        fileWriter.write(String.valueOf(node.key) + '\n');
        preOrderTraversal(node.left, fileWriter);
        preOrderTraversal(node.right, fileWriter);
    }

    class Node{
        int key;
        Node left;
        Node right;
        Node(int _key){
            key = _key;
            left = null;
            right = null;
        }
    }
}

public class Main implements Runnable {
    public static void main(String[] args){
        new Thread(null, new Main(), "", 64 * 1024 * 1024).start();
    }

    @Override
    public void run(){
        String inputFileName = "input.txt";
        String outputFileName = "output.txt";
        BinaryTree bt = new BinaryTree();

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(inputFileName));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String line;
        try {
            line = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int nodeToDelete = Integer.parseInt(line);

        try {
            reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        while (true){
            try {
                if ((line = reader.readLine()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            bt.insert(Integer.parseInt(line));
        }

        bt.deleteNode(nodeToDelete);
        FileWriter writer = null;
        try {
            writer = new FileWriter(outputFileName, false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            bt.preOrderTraversal(bt.head, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
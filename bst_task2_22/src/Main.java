import java.io.*;

class BST {
    static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;

        TreeNode(int value) {
            this.value = value;
            left = null;
            right = null;
        }
    }

    TreeNode root;

    BST() {
        root = null;
    }

    void insert(int value) {
        if (root == null) {
            root = new TreeNode(value);
        } else {
            TreeNode currentNode = root;
            while (true) {
                if (value == currentNode.value) {
                    break;
                }
                if (value < currentNode.value) {
                    if (currentNode.left == null) {
                        currentNode.left = new TreeNode(value);
                        break;
                    } else {
                        currentNode = currentNode.left;
                    }
                } else {
                    if (currentNode.right == null) {
                        currentNode.right = new TreeNode(value);
                        break;
                    } else {
                        currentNode = currentNode.right;
                    }
                }
            }
        }
    }

    void removeNode(int value) {
        root = deleteNode(root, value);
    }

    TreeNode deleteNode(TreeNode node, int value) {
        if (node == null) {
            return node;
        }
        if (value < node.value) {
            node.left = deleteNode(node.left, value);
        } else if (value > node.value) {
            node.right = deleteNode(node.right, value);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }
            node.value = findMinValue(node.right);
            node.right = deleteNode(node.right, node.value);
        }
        return node;
    }

    int findMinValue(TreeNode node) {
        int minValue = node.value;
        while (node.left != null) {
            minValue = node.left.value;
            node = node.left;
        }
        return minValue;
    }

    void preorderTraversal(TreeNode node, FileWriter fileWriter) throws IOException {
        if (node == null) {
            return;
        }
        fileWriter.write(node.value + "\n");
        preorderTraversal(node.left, fileWriter);
        preorderTraversal(node.right, fileWriter);
    }

    void run() {
        String inputFileName = "D://algos//bst_task2_22//src//input.txt";
        String outputFileName = "D://algos//bst_task2_22//src//output.txt";
        BST bst = new BST();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName))) {
            int valueToDelete = Integer.parseInt(reader.readLine());
            reader.readLine();

            String line;
            while ((line = reader.readLine()) != null) {
                bst.insert(Integer.parseInt(line));
            }

            bst.removeNode(valueToDelete);

            try (FileWriter writer = new FileWriter(outputFileName, false)) {
                bst.preorderTraversal(bst.root, writer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        new BST().run();
    }
}



//D://algos//bst_task2_22//src//
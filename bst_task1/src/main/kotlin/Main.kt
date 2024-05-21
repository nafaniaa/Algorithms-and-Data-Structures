import java.io.File
import java.util.Stack

class TreeNode(var key: Long){
    var left: TreeNode? = null
    var right: TreeNode? = null
}

//вставка ключей
fun insert(root: TreeNode?, key: Long): TreeNode{
    if(root == null){
        return TreeNode(key)
    }

    if(key < root.key){
        root.left = insert(root.left ,key)

    } else if (key > root.key){
        root.right = insert(root.right, key)
    }

    return root
}

//прямой левый обход
fun preorderTraversal(root: TreeNode?, outputFile: File) {
    val stack = Stack<TreeNode>().run {
        val stackInitialized = this
        var current = root

        while (current != null || !stackInitialized.isEmpty()) {
            while (current != null) {
                outputFile.appendText("${current.key}\n")
                if (current.right != null) {
                    stackInitialized.push(current.right)
                }
                current = current.left
            }

            if (!stackInitialized.isEmpty()) {
                current = stackInitialized.pop()
            }
        }
    }
}

fun main(args: Array<String>) {
    val inputFile = File("D://algos//bst_task1//src//main//kotlin//input.txt")
    val keys = inputFile.readLines().map { it.toLong() }

    var root: TreeNode? = null
    for (key in keys) {
        root = insert(root, key)
    }

    val outputFile = File("D://algos//bst_task1//src//main//kotlin//output.txt")
    preorderTraversal(root, outputFile)
}


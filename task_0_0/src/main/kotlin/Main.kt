import java.io.*

fun main(args: Array<String>) {
    val inputFilename = "out/production/task_0_0/input.txt"
    val outputFilename = "out/production/task_0_0/output.txt"

    val keys = readKeysFromFile(inputFilename)

    val bst = BinarySearchTree()
    for (key in keys) {
        bst.insert(key)
    }

    val sum = bst.sumKeys()

    writeSumToFile(sum, outputFilename)
}

class TreeNode(var key: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class BinarySearchTree {
    private var root: TreeNode? = null

    fun insert(key: Int) {
        root = insertRecursive(root, key)
    }

    fun sumKeys(): Long {
        return sumKeysIterative(root)
    }

    private fun insertRecursive(node: TreeNode?, key: Int): TreeNode {
        if (node == null) {
            return TreeNode(key)
        }
        if (key < node.key) {
            node.left = insertRecursive(node.left, key)
        } else if (key > node.key) {
            node.right = insertRecursive(node.right, key)
        }
        return node
    }

    private fun sumKeysIterative(node: TreeNode?): Long {
        var sum: Long = 0
        val stack = mutableListOf<TreeNode>()
        var current: TreeNode? = node
        while (current != null || stack.isNotEmpty()) {
            while (current != null) {
                stack.add(current)
                current = current.left
            }
            current = stack.removeAt(stack.size - 1)
            sum += current.key
            current = current.right
        }
        return sum
    }
}

fun readKeysFromFile(filename: String): IntArray {
    val file = File(filename)
    val keys = mutableListOf<Int>()
    file.bufferedReader().useLines { lines ->
        lines.forEach {
            keys.add(it.toInt())
        }
    }
    return keys.toIntArray()
}

fun writeSumToFile(sum: Long, filename: String) {
    val file = File(filename)
    file.bufferedWriter().use { writer ->
        writer.write(sum.toString())
    }
}

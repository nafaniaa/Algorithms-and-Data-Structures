import java.io.File
import java.util.*

class TreeNode(var key: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

fun insert(root: TreeNode?, key: Int): TreeNode {
    var current = root
    var parent: TreeNode? = null
    while (current != null) {
        parent = current
        current = if (key < current.key) current.left else current.right
    }
    val newNode = TreeNode(key)
    if (parent == null) {
        return newNode
    } else if (key < parent.key) {
        parent.left = newNode
    } else {
        parent.right = newNode
    }
    return root ?: newNode
}

fun delete(root: TreeNode?, key: Int): TreeNode? {
    var current = root
    var parent: TreeNode? = null
    while (current != null && current.key != key) {
        parent = current
        current = if (key < current.key) current.left else current.right
    }
    if (current == null) {
        return root
    }
    if (current.left == null) {
        return if (parent == null) current.right else {
            if (current == parent.left) parent.left = current.right else parent.right = current.right
            root
        }
    } else if (current.right == null) {
        return if (parent == null) current.left else {
            if (current == parent.left) parent.left = current.left else parent.right = current.left
            root
        }
    } else {
        var successorParent = current
        var successor = current.right
        while (successor!!.left != null) {
            successorParent = successor
            successor = successor.left
        }
        current.key = successor.key
        if (successorParent!!.left == successor) {
            successorParent.left = successor.right
        } else {
            successorParent.right = successor.right
        }
        return root
    }
}

fun leftPreorderTraversal(root: TreeNode?): List<Int> {
    val result = mutableListOf<Int>()
    val stack = Stack<TreeNode>()
    var current = root
    while (current != null || !stack.isEmpty()) {
        while (current != null) {
            result.add(current.key)
            stack.push(current)
            current = current.left
        }
        current = stack.pop()
        current = current.right
    }
    return result
}

fun main() {
    val lines = File("D://algos//bst_task2//src//main//kotlin//input.txt").readLines()
    val keyToDelete = lines[0].toInt()
    val keys = lines.drop(2).map { it.toInt() }

    var root: TreeNode? = null
    for (key in keys) {
        root = insert(root, key)
    }

    root = delete(root, keyToDelete)

    val result = leftPreorderTraversal(root)

    File("D://algos//bst_task2//src//main//kotlin//output.txt").writeText(result.joinToString("\n"))
}

// "D://algos//bst_task2//src//main//kotlin//output.txt"
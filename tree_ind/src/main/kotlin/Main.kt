import java.io.File

data class Node(
    var key: Int,
    var leftChild: Node? = null,
    var rightChild: Node? = null,
    var heightDiff: Int = 0
)

fun insert(root: Node?, key: Int): Node {
    if (root == null) {
        return Node(key)
    }
    if (key < root.key) {
        root.leftChild = insert(root.leftChild, key)
    } else if (key > root.key) {
        root.rightChild = insert(root.rightChild, key)
    }
    root.heightDiff = getHeight(root.leftChild) - getHeight(root.rightChild)
    return root
}

fun deleteNode(root: Node?, key: Int): Node? {
    if (root == null) return root

    if (key < root.key) {
        root.leftChild = deleteNode(root.leftChild, key)
    } else if (key > root.key) {
        root.rightChild = deleteNode(root.rightChild, key)
    } else {
        if (root.leftChild == null) {
            return root.rightChild
        } else if (root.rightChild == null) {
            return root.leftChild
        }

        root.key = minValue(root.rightChild!!)
        root.rightChild = deleteNode(root.rightChild, root.key)
    }
    return root
}

fun minValue(node: Node): Int {
    var minv = node.key
    var current = node
    while (current.leftChild != null) {
        minv = current.leftChild!!.key
        current = current.leftChild!!
    }
    return minv
}

fun getHeight(node: Node?): Int {
    if (node == null) {
        return -1
    }
    return 1 + maxOf(getHeight(node.leftChild), getHeight(node.rightChild))
}

fun preorderTraversal(root: Node?, result: StringBuilder) {
    if (root != null) {
        result.append("${root.key} ")
        preorderTraversal(root.leftChild, result)
        preorderTraversal(root.rightChild, result)
    }
}

fun findNodeWithMaxHeightDiff(root: Node?): Node? {
    if (root == null) return null

    val leftHeight = getHeight(root.leftChild)
    val rightHeight = getHeight(root.rightChild)
    val maxHeightDiff = maxOf(leftHeight, rightHeight)

    return if (maxHeightDiff == root.heightDiff) root else null
}


fun readInput(fileName: String): List<Int> {
    val inputList = mutableListOf<Int>()
    File(fileName).forEachLine {
        inputList.add(it.toInt())
    }
    return inputList
}

fun writeOutput(fileName: String, result: String) {
    File(fileName).writeText(result.trim())
}

fun main() {
    val inputKeys = readInput("D://algos//tree_ind//src//main//kotlin//in.txt")
    var root: Node? = null

    for (key in inputKeys) {
        root = insert(root, key)
    }

    val nodeWithMaxHeightDiff = findNodeWithMaxHeightDiff(root)
    if (nodeWithMaxHeightDiff != null) {
        root = deleteNode(root, nodeWithMaxHeightDiff.key)
    }

    val result = StringBuilder()
    preorderTraversal(root, result)

    writeOutput("D://algos//tree_ind//src//main//kotlin//out.txt", result.toString())
}

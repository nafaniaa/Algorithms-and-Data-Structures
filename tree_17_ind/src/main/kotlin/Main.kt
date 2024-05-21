import java.io.File

data class TreeNode(var key: Int, var left: TreeNode? = null, var right: TreeNode? = null)

fun insert(root: TreeNode?, key: Int): TreeNode {
    if (root == null) {
        return TreeNode(key)
    }
    if (key < root.key) {
        root.left = insert(root.left, key)
    } else if (key > root.key) {
        root.right = insert(root.right, key)
    }
    return root
}

fun preOrderTraversal(root: TreeNode?, traversal: MutableList<Int>) {
    if (root != null) {
        traversal.add(root.key)
        preOrderTraversal(root.left, traversal)
        preOrderTraversal(root.right, traversal)
    }
}

fun findMaxDiffHeightNode(root: TreeNode?, maxDiff: Int, maxHeightDiffNode: TreeNode): TreeNode {
    if (root == null) {
        return maxHeightDiffNode
    }

    val heightDiff = kotlin.math.abs(getHeight(root.left) - getHeight(root.right))
    if (heightDiff > maxDiff || (heightDiff == maxDiff && root.key < maxHeightDiffNode.key)) {
        return findMaxDiffHeightNode(root.left, heightDiff, root)
    } else {
        return findMaxDiffHeightNode(root.right, maxDiff, maxHeightDiffNode)
    }
}

fun getHeight(root: TreeNode?): Int {
    if (root == null) {
        return -1
    }
    return 1 + kotlin.math.max(getHeight(root.left), getHeight(root.right))
}

fun deleteNode(root: TreeNode?, key: Int): TreeNode? {
    if (root == null) {
        return null
    }

    if (key < root.key) {
        root.left = deleteNode(root.left, key)
    } else if (key > root.key) {
        root.right = deleteNode(root.right, key)
    } else {
        if (root.left == null) {
            return root.right
        } else if (root.right == null) {
            return root.left
        }

        root.key = minValue(root.right)

        root.right = deleteNode(root.right, root.key)
    }

    return root
}

fun minValue(node: TreeNode?): Int {
    var current = node
    var minv = node!!.key
    while (current!!.left != null) {
        minv = current.left!!.key
        current = current.left
    }
    return minv
}

fun main() {
    val input = File("in.txt").readLines()
    val keys = input.map { it.toInt() }
    var root: TreeNode? = null


    for (key in keys) {
        root = insert(root, key)
    }

    val maxHeightDiffNode = findMaxDiffHeightNode(root, 0, TreeNode(Int.MAX_VALUE))


    root = deleteNode(root, maxHeightDiffNode.key)


    val traversal = mutableListOf<Int>()
    preOrderTraversal(root, traversal)


    File("out.txt").writeText(traversal.joinToString("\n"))
}
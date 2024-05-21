import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileReader
import java.io.FileWriter
import java.util.*

class Node(val frequency: Long) {
    var left: Node? = null
    var right: Node? = null
}

class HuffmanTree {
    companion object {
        var root: Node? = null
        var codeLength = 0L

        fun buildTree(frequencies: PriorityQueue<Long>) {
            var node: Node?
            var sum: Long
            var min1: Node?
            var min2: Node?
            val arr = frequencies.toTypedArray()
            val cmp = Comparator<Node> { node1, node2 ->
                when {
                    node1.frequency > node2.frequency -> 1
                    node1.frequency < node2.frequency -> -1
                    else -> 0
                }
            }
            val roots = PriorityQueue<Node>(cmp)
            arr.forEach { roots.add(Node(it)) }

            while (roots.size != 1) {
                min1 = roots.peek()
                roots.remove()
                min2 = roots.peek()
                roots.remove()
                sum = min1!!.frequency + min2!!.frequency
                node = Node(sum)
                node.left = min1
                node.right = min2
                root = node
                roots.add(node)
            }
            calculateCodeLength(root, 0)
        }

        private fun calculateCodeLength(node: Node?, height: Int) {
            if (node?.left != null) {
                calculateCodeLength(node.left, height + 1)
            }
            if (node?.right != null) {
                calculateCodeLength(node.right, height + 1)
            }
            if (node?.left == null && node?.right == null) {
                node?.let {
                    codeLength += it.frequency * height
                }
            }
        }
    }
}

fun main() {
    val inputFile = "D://algos//haffman1//src//main//kotlin//huffman.in"
    val outputFile = "D://algos//haffman1//src//main//kotlin//haffman.out"
    val reader = BufferedReader(FileReader(inputFile))
    val writer = BufferedWriter(FileWriter(outputFile))
    val N = reader.readLine().toLong()
    val st = StringTokenizer(reader.readLine())
    val frequencies = PriorityQueue<Long>(N.toInt())
    while (st.hasMoreTokens()) {
        frequencies.add(st.nextToken().toLong())
    }
    HuffmanTree.buildTree(frequencies)
    writer.write(HuffmanTree.codeLength.toString())
    writer.flush()
    reader.close()
    writer.close()
}

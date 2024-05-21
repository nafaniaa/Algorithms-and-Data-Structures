import java.io.File
import java.math.BigInteger
import java.util.*

data class HuffmanNode(
    var frequency: BigInteger = BigInteger.ZERO,
    var left: HuffmanNode? = null,
    var right: HuffmanNode? = null,
    var parent: HuffmanNode? = null
): Comparable<HuffmanNode> {

    override fun compareTo(other: HuffmanNode): Int {
        return this.frequency.compareTo(other.frequency)
    }
}

fun buildHuffmanTree(frequencies: List<BigInteger>): HuffmanNode? {
    val priorityQueue = PriorityQueue<HuffmanNode>()

    for (frequency in frequencies) {
        priorityQueue.add(HuffmanNode(frequency))
    }

    while (priorityQueue.size > 1) {
        val left = priorityQueue.poll()
        val right = priorityQueue.poll()

        val sumFrequency = left.frequency + right.frequency
        val newNode = HuffmanNode(sumFrequency)
        newNode.left = left
        newNode.right = right

        left.parent = newNode
        right.parent = newNode

        priorityQueue.add(newNode)
    }

    return priorityQueue.poll()
}

fun distanceToRoot(leaf: HuffmanNode): BigInteger {
    var distance = BigInteger.ZERO
    var currentNode: HuffmanNode? = leaf

    while (currentNode != null && currentNode.parent != null) {
        distance = distance.add(BigInteger.ONE)
        currentNode = currentNode.parent
    }

    return distance
}

fun calculateEncodedLength(root: HuffmanNode, depth: Int = 0): BigInteger {
    if (root.left == null && root.right == null) {
        return root.frequency.multiply(depth.toBigInteger())
    }
    return calculateEncodedLength(root.left!!, depth + 1).add(
        calculateEncodedLength(root.right!!, depth + 1)
    )
}

fun main(args: Array<String>) {
    val lines = File("D://algos//my_haffman//src//main//kotlin//input.txt").readLines()
    val line = lines[1].split(" ").map { it.toBigInteger() }
    val huffmanTree = buildHuffmanTree(line)

    val encodedLength = calculateEncodedLength(huffmanTree!!)

    File("D://algos//my_haffman//src//main//kotlin//output.txt").writeText("$encodedLength")
}

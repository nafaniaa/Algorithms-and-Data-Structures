import java.io.*

class Node(val key: Int, val leftLim: Long, val rightLim: Long)

fun main() {
    var numberOfNodes: Int
    var result: String = "YES"

    BufferedReader(FileReader("D://algos//bst_task3//src//main//kotlin//bst.in")).use { reader ->
        numberOfNodes = reader.readLine()!!.toInt()

        val nodes = arrayOfNulls<Node>(numberOfNodes + 1)
        nodes[0] = null
        nodes[1] = Node(reader.readLine()!!.toInt(), Long.MIN_VALUE, Long.MAX_VALUE)
        var line: String?
        for (i in 2..numberOfNodes) {
            line = reader.readLine()
            val parts: List<String> = line.split(" ")
            val key = parts[0].toInt()
            val fatherIndex = parts[1].toLong()
            val type = parts[2]
            val (leftLimit, rightLimit) = if (type == "L") {
                nodes[fatherIndex.toInt()]!!.leftLim to nodes[fatherIndex.toInt()]!!.key.toLong()
            } else {
                nodes[fatherIndex.toInt()]!!.key.toLong() to nodes[fatherIndex.toInt()]!!.rightLim
            }
            if (key < leftLimit || key >= rightLimit) {
                result = "NO"
                break
            }
            nodes[i] = Node(key, leftLimit, rightLimit)
        }
    }

    PrintWriter(FileWriter("D://algos//bst_task3//src//main//kotlin//bst.out")).use { output ->
        output.write(result)
    }
}

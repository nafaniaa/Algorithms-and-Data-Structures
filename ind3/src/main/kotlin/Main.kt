import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.util.*

fun main() {
    try {
        val scanner = Scanner(File("D://algos//ind3//src//main//kotlin//in.txt"))
        val n = scanner.nextInt()
        val graph = Array(n) { mutableListOf<Pair<Long, Long>>() }
        val size = LongArray(n)
        val order = LongArray(n)
        val parentChilds = LongArray(n)
        val queue: Queue<Long> = ArrayDeque()

        for (i in 0 until n) {
            var I = scanner.nextInt()
            var J = scanner.nextInt()
            size[I - 1] = J.toLong()
            if (J == 0) {
                queue.add(I - 1L)
            }
            while (J > 0) {
                val ind = scanner.nextLong()
                parentChilds[ind.toInt() - 1] = I - 1L
                graph[I - 1].add(Pair(ind, scanner.nextLong()))
                J--
            }
        }

        var idx = 0
        while (queue.isNotEmpty()) {
            val curNode = queue.poll()
            order[idx++] = curNode
            size[parentChilds[curNode.toInt()].toInt()]--
            if (size[parentChilds[curNode.toInt()].toInt()] == 0L) {
                queue.add(parentChilds[curNode.toInt()])
            }
        }

        val minCost = LongArray(n) { 0 }
        var ordI = 0
        var i: Int
        var minIndex: Long
        var minBribe: Long

        while (ordI < n) {
            i = order[ordI].toInt()
            if (graph[i].isNotEmpty()) {
                minIndex = graph[i][0].first
                minCost[minIndex.toInt() - 1] += graph[i][0].second
                minBribe = minCost[minIndex.toInt() - 1]
                for (j in 1 until graph[i].size) {
                    val (index, bribe) = graph[i][j]
                    minCost[index.toInt() - 1] += bribe
                    if (minCost[index.toInt() - 1] <= minBribe) {
                        minBribe = bribe
                        minIndex = index
                    }
                }
                minCost[i] += minCost[minIndex.toInt() - 1]
            }
            ordI++
        }

        val resultOrder = mutableListOf(1)
        var curIndex = 0
        var key = true
        while (key) {
            val iterator = graph[curIndex].iterator()
            key = false
            while (iterator.hasNext()) {
                val (index, bribe) = iterator.next()
                if (minCost[index.toInt() - 1] == minCost[curIndex]) {
                    resultOrder.add(index.toInt())
                    minCost[index.toInt() - 1] -= bribe
                    curIndex = index.toInt() - 1
                    key = true
                }
            }
        }

        scanner.close()
        val fileWriter = FileWriter("D://algos//ind3//src//main//kotlin//out.txt")
        fileWriter.write("${minCost[0]}\n")
        fileWriter.write(resultOrder.joinToString(" "))
        fileWriter.close()
    } catch (e: IOException) {
        println("An error occurred while reading the file.")
    }
}

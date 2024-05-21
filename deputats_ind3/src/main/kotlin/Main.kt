import java.io.File
import java.util.*

data class Official(val id: Int, val bribes: MutableList<Pair<Int, Int>> = mutableListOf(), var cost: Int = 0)

fun main() {
    val inputFile = File("in.txt")
    val outputFile = File("out.txt")

    val officials = mutableListOf<Official>()
    val n: Int
    inputFile.bufferedReader().useLines { lines ->
        n = lines.next().toInt()
        repeat(n) {
            val (id, m) = lines.next().split(" ").map { it.toInt() }
            val official = Official(id)
            repeat(m) {
                val (subordinate, bribe) = lines.next().split(" ").map { it.toInt() }
                official.bribes.add(subordinate to bribe)
            }
            officials.add(official)
        }
    }

    val (minCost, order) = findMinCostAndOrder(officials)

    outputFile.bufferedWriter().use { writer ->
        writer.write("$minCost\n")
        writer.write(order.joinToString(" "))
    }
}

fun findMinCostAndOrder(officials: List<Official>): Pair<Int, List<Int>> {
    val minCosts = IntArray(officials.size + 1) { Int.MAX_VALUE }
    val order = mutableListOf<Int>()

    fun dfs(current: Int) {
        order.add(current)
        var minBribe = Int.MAX_VALUE

        for ((subordinate, bribe) in officials[current - 1].bribes) {
            if (minCosts[subordinate] == Int.MAX_VALUE) {
                dfs(subordinate)
            }
            minBribe = minOf(minBribe, minCosts[subordinate] + bribe)
        }

        minCosts[current] = minBribe
    }

    dfs(1)

    return minCosts[1] to order.reversed()
}

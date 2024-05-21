import java.io.File

fun main() {
    val lines = File("D://algos//graphs3//src//main//kotlin//input.txt").readLines()
    val (n, m) = lines[0].split(" ").map { it.toInt() }


    val adjacencyList = Array(n) { mutableListOf<Int>() }


    for (i in m downTo 1) {
        val (u, v) = lines[i].split(" ").map { it.toInt() }
        adjacencyList[u - 1].add(v)
        adjacencyList[v - 1].add(u)
    }


    val outputFile = File("D://algos//graphs3//src//main//kotlin//output.txt")
    outputFile.printWriter().use { out ->
        for (i in 1..n) {
            out.print("${adjacencyList[i - 1].size} ")
            for (vertex in adjacencyList[i - 1]) {
                out.print("$vertex ")
            }
            out.println()
        }
    }
}

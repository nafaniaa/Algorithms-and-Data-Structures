import java.io.File

fun main() {
    val lines = File("D://algos//graphs1//src//main//kotlin//input.txt").readLines()
    val (n, m) = lines[0].split(" ").map { it.toInt() }

    // Создаем матрицу смежности, заполненную нулями
    val adjacencyMatrix = Array(n) { IntArray(n) }

    // Считываем ребра и заполняем матрицу смежности
    for (i in 1..m) {
        val (u, v) = lines[i].split(" ").map { it.toInt() }
        adjacencyMatrix[u - 1][v - 1] = 1
        adjacencyMatrix[v - 1][u - 1] = 1 // так как граф неориентированный, добавляем ребра в обе стороны
    }

    // Записываем результат в output.txt
    val outputFile = File("D://algos//graphs1//src//main//kotlin//output.txt")
    outputFile.printWriter().use { out ->
        for (row in adjacencyMatrix) {
            out.println(row.joinToString(" "))
        }
    }
}

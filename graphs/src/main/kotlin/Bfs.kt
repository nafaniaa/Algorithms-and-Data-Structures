import java.io.File

fun bfs(matrix: List<List<Int>>, visited: BooleanArray, marks: IntArray, startVertex: Int, counter: Int): Int {
    val bfsQueue = mutableListOf<Int>()
    bfsQueue.add(startVertex)
    visited[startVertex] = true
    marks[startVertex] = counter
    var newCounter = counter + 1
    while (bfsQueue.isNotEmpty()) {
        val p = bfsQueue.removeAt(0)
        for (j in matrix[p].indices) {
            if (matrix[p][j] == 1 && !visited[j]) {
                visited[j] = true
                bfsQueue.add(j)
                marks[j] = newCounter++
            }
        }
    }
    return newCounter
}

fun main() {
    val inputFile = File("D://algos//graphs//src//main//kotlin//input.txt")
    val outputFile = File("D://algos//graphs//src//main//kotlin//output.txt")
    val lines = inputFile.readLines()

    val n = lines[0].toInt()
    var counter = 1
    val matrix = mutableListOf<List<Int>>()
    val visited = BooleanArray(n)
    val marks = IntArray(n)

    for (i in 1 until lines.size) {
        val str = lines[i].split(" ").map { it.toInt() }
        matrix.add(str)
    }

    for (i in 0 until n) {
        if (!visited[i]) {
            counter = bfs(matrix, visited, marks, i, counter)
        }
    }

    val outputFileWriter = outputFile.bufferedWriter()
    marks.forEach { outputFileWriter.write("$it ") }
    outputFileWriter.close()
}

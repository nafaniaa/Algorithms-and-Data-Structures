import java.io.File

fun dfs(root: Int, numRoot: Int, matrix: List<List<Int>>, visited: MutableList<Boolean>, marks: MutableList<Int>, currentMark: Int): Int {
    var newMark = currentMark
    visited[root] = true
    marks[root] = newMark++
    for (j in 0 until numRoot) {
        if (matrix[root][j] == 1 && !visited[j]) {
            newMark = dfs(j, numRoot, matrix, visited, marks, newMark)
        }
    }
    return newMark
}

fun performDFS(numRoot: Int, matrix: List<List<Int>>): List<Int> {
    val visited = MutableList(numRoot) { false }
    val marks = MutableList(numRoot) { 0 }
    var currentLabel = 1
    for (i in 0 until numRoot) {
        if (!visited[i]) {
            currentLabel = dfs(i, numRoot, matrix, visited, marks, currentLabel)
        }
    }
    return marks
}

fun main() {
    val inputFile = File("D://algos//graphs//src//main//kotlin//input.txt")
    val outputFile = File("D://algos//graphs//src//main//kotlin//output.txt")
    val lines = inputFile.readLines()
    val numRoot = lines[0].toInt()
    val matrix = mutableListOf<List<Int>>()
    for (i in 1..numRoot) {
        val row = lines[i].split(" ").map { it.toInt() }
        matrix.add(row)
    }


    val marks = performDFS(numRoot, matrix)


    outputFile.writeText(marks.joinToString(" "))
}

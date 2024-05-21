import java.io.File

fun main() {
    val lines = File("D://algos//graphs2//src//main//kotlin//input.txt").readLines()
    val n = lines[0].toInt()

    val tree = Array(n + 1) { 0 } // массив P, индексация с 1

    // Заполняем массив P
    for (i in 1 until n) {
        val (u, v) = lines[i].split(" ").map { it.toInt() }
        tree[v] = u
    }

    // Записываем результат в output.txt
    val outputFile = File("D://algos//graphs2//src//main//kotlin//output.txt")
    outputFile.printWriter().use { out ->
        for (i in 1..n) {
            out.print("${tree[i]} ")
        }
    }
}

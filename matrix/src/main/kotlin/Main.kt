import java.io.File

fun minOperationsForMatrixChain(s: Int, matrixSizes: List<Int>): Int {

    val dp = Array(s + 1) { IntArray(s + 1) }


    for (l in 2..s) {
        for (i in 1..s - l + 1) {
            val j = i + l - 1
            dp[i][j] = Int.MAX_VALUE
            for (k in i until j) {
                val operations = dp[i][k] + dp[k + 1][j] + matrixSizes[i - 1] * matrixSizes[k] * matrixSizes[j]
                dp[i][j] = minOf(dp[i][j], operations)
            }
        }
    }

    return dp[1][s]
}

fun main() {
    val inputFile = File("D://algos//matrix//src//main//kotlin//input.txt")
    val outputFile = File("D://algos//matrix//src//main//kotlin//output.txt")

    // Чтение входных данных из файла
    val inputLines = inputFile.readLines()
    val s = inputLines[0].toInt()
    val matrixSizes = mutableListOf<Int>()
    for (i in 1 until inputLines.size) {
        val (n, m) = inputLines[i].split(" ").map { it.toInt() }
        matrixSizes.add(n)
        if (i == inputLines.size - 1) matrixSizes.add(m)
    }

    val result = minOperationsForMatrixChain(s, matrixSizes)

    outputFile.writeText(result.toString())
}

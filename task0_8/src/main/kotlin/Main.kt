import java.io.File

fun levenshteinDistance(x: Int, y: Int, z: Int, A: String, B: String): Int {
    val m = A.length
    val n = B.length
    val dp = Array(m + 1) { IntArray(n + 1) }

    for (i in 0..m) {
        for (j in 0..n) {
            when {
                i == 0 -> dp[i][j] = j * y
                j == 0 -> dp[i][j] = i * x
                A[i - 1] == B[j - 1] -> dp[i][j] = dp[i - 1][j - 1]
                else -> dp[i][j] = minOf(dp[i - 1][j] + x, dp[i][j - 1] + y, dp[i - 1][j - 1] + z)
            }
        }
    }

    return dp[m][n]
}

fun main() {
    val lines = File("D://algos//task0_8//src//main//kotlin//in.txt").readLines()
    val x = lines[0].toInt()
    val y = lines[1].toInt()
    val z = lines[2].toInt()
    val A = lines[3]
    val B = lines[4]

    val result = levenshteinDistance(x, y, z, A, B)

    File("D://algos//task0_8//src//main//kotlin//out.txt").writeText(result.toString())
}

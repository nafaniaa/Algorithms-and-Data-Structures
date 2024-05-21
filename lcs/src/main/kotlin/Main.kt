fun main() {
    val n = readLine()!!.toInt()

    val elementsA = readLine()!!.split(" ").map { it.toInt() }
    val elementsB = readLine()!!.split(" ").map { it.toInt() }

    val dp = Array(n + 1) { IntArray(n + 1) }
    for (i in 0..n) {
        dp[i][0] = 0
        dp[0][i] = 0
    }

    for (i in 1..n) {
        for (j in 1..n) {
            dp[i][j] = if (elementsA[i - 1] == elementsB[j - 1]) dp[i - 1][j - 1] + 1
            else maxOf(dp[i - 1][j], dp[i][j - 1])
        }
    }

    val k = dp[n][n]
    println(k)

    var i = n
    var j = n
    val indicesA = mutableListOf<Int>()
    val indicesB = mutableListOf<Int>()

    while (i > 0 && j > 0) {
        when {
            elementsA[i - 1] == elementsB[j - 1] -> {
                indicesA.add(i - 1)
                indicesB.add(j - 1)
                i--
                j--
            }
            dp[i - 1][j] > dp[i][j - 1] -> i--
            else -> j--
        }
    }

    println(indicesA.reversed().joinToString(" "))
    println(indicesB.reversed().joinToString(" "))
}

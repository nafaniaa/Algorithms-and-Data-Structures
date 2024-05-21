fun binomialCoefficient(n: Int, k: Int): Long {
    val MOD = 1_000_000_007L
    val dp = Array(n + 1) { LongArray(k + 1) }

    for (i in 0..n) {
        for (j in 0..minOf(i, k)) {
            if (j == 0 || j == i) {
                dp[i][j] = 1
            } else {
                dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j]) % MOD
            }
        }
    }

    return dp[n][k]
}

fun main() {
    val (N, K) = readLine()!!.split(" ").map { it.toInt() }
    println(binomialCoefficient(N, K))
}

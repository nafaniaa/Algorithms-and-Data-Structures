import java.io.File

fun longestPalindromeSubseq(inputString: String): Pair<Int, String> {
    val n = inputString.length
    val dp = Array(n) { IntArray(n) { 0 } }

    for (i in 0 until n) {
        dp[i][i] = 1
    }

    for (len in 2..n) {
        for (i in 0 until n - len + 1) {
            val j = i + len - 1
            if (inputString[i] == inputString[j]) {
                dp[i][j] = dp[i + 1][j - 1] + 2
            } else {
                dp[i][j] = maxOf(dp[i + 1][j], dp[i][j - 1])
            }
        }
    }

    val length = dp[0][n - 1]
    val palindrome = CharArray(length)

    var leftPointer = 0
    var rightPointer = n - 1
    var index = length - 1
    while (leftPointer < rightPointer) {
        if (inputString[leftPointer] == inputString[rightPointer]) {
            palindrome[index] = inputString[leftPointer]
            palindrome[length - index - 1] = inputString[leftPointer]
            leftPointer++
            rightPointer--
            index--
        } else if (dp[leftPointer][rightPointer] == dp[leftPointer + 1][rightPointer]) {
            leftPointer++
        } else {
            rightPointer--
        }
    }
    if (leftPointer == rightPointer) {
        palindrome[index] = inputString[leftPointer]
    }

    return length to String(palindrome)
}

fun main() {
    val inputFile = File("D://algos//palindrome//src//main//kotlin//input.txt")
    val outputFile = File("D://algos//palindrome//src//main//kotlin//output.txt")

    val inputString = inputFile.readText().trim()

    val result = longestPalindromeSubseq(inputString)
    outputFile.bufferedWriter().use { out ->
        out.write("${result.first}\n")
        out.write(result.second)
    }
}

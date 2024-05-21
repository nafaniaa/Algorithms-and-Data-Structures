import java.io.File

fun prefixFunction(pattern: String): IntArray {
    val m = pattern.length
    val pi = IntArray(m)
    var k = 0
    for (i in 1 until m) {
        while (k > 0 && pattern[k] != pattern[i]) {
            k = pi[k - 1]
        }
        if (pattern[k] == pattern[i]) {
            k++
        }
        pi[i] = k
    }
    return pi
}

fun kmp(text: String, pattern: String): IntArray {
    val n = text.length
    val m = pattern.length
    val pi = prefixFunction(pattern)
    val matches = mutableListOf<Int>()
    var q = 0
    for (i in 0 until n) {
        while (q > 0 && pattern[q] != text[i]) {
            q = pi[q - 1]
        }
        if (pattern[q] == text[i]) {
            q++
        }
        if (q == m) {
            matches.add(i - m + 1)
            q = pi[q - 1]
        }
    }
    return matches.toIntArray()
}

fun main() {
    val inputFile = File("D://algos//str1//src//main//kotlin//input.txt")
    val outputFile = File("D://algos//str1//src//main//kotlin//output.txt")

    val lines = inputFile.readLines()
    val n = lines[0].toInt()
    val s1 = lines[1]
    val s2 = lines[2]

    val concatenatedS1 = s1 + s1

    val shifts = kmp(concatenatedS1, s2)

    val output = if (shifts.isNotEmpty()) {
        shifts[0]
    } else {
        -1
    }

    outputFile.writeText(output.toString())
}

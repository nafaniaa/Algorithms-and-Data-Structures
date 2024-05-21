import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val S = br.readLine()

    val suffixArray = buildSuffixArray(S)

    println(S.length)
    println(suffixArray.joinToString(" "))
}

fun buildSuffixArray(S: String): IntArray {
    val n = S.length
    val suffixes = Array(n) { i -> Pair(S.substring(i), i) }
    suffixes.sortBy { it.first }

    val suffixArray = IntArray(n)
    for (i in suffixes.indices) {
        suffixArray[i] = suffixes[i].second
    }

    return suffixArray
}

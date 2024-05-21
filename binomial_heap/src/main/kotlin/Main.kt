import java.io.File
import kotlin.math.log2

fun main() {
    val inputFilePath = "D://algos//binomial_heap//src//main//kotlin//input.txt"
    val outputFilePath = "D://algos//binomial_heap//src//main//kotlin//output.txt"

    val n = File(inputFilePath).readText().trim().toLong()
    val result = if (n != 0L) buildBinomialHeap(n) else emptyList()

    if (result.isEmpty()) {
        File(outputFilePath).writeText("-1")
    } else {
        File(outputFilePath).writeText(result.reversed().joinToString("\n"))
    }
}

fun buildBinomialHeap(n: Long): List<Long> {
    return if (n == 0L) {
        emptyList()
    } else {
        val height = log2(n.toDouble()).toLong()
        val currentPower = 1L shl height.toInt()
        listOf(height) + buildBinomialHeap(n - currentPower)
    }
}

import java.io.File
import java.math.BigInteger

fun main(args: Array<String>) {
    val inputFilePath = "D://algos//bin_heap_2//src//main//kotlin//input.txt"
    val outputFilePath = "D://algos//bin_heap_2//src//main//kotlin//output.txt"

    val n = File(inputFilePath).readText().trim().toBigInteger()
    val heights = if (n != BigInteger.ZERO) binomialHeap(n) else mutableListOf()

    if (heights.isEmpty()) {
        File(outputFilePath).writeText("-1")
    } else {
        File(outputFilePath).writeText(heights.joinToString("\n"))
    }
}

fun binomialHeap(v: BigInteger): MutableList<BigInteger> {
    val powers = mutableListOf<BigInteger>()
    var remainder = v

    var i = BigInteger.ZERO
    val two = BigInteger.valueOf(2)
    while (remainder != BigInteger.ZERO) {
        if (remainder % two == BigInteger.ONE) {
            powers.add(i)
        }
        i = i.add(BigInteger.ONE)
        remainder /= two
    }

    return powers.sorted().toMutableList()
}

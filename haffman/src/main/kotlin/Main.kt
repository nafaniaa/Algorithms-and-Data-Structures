import java.io.File

fun decodeHuffman(a: LongArray): Long {
    val b = LongArray(a.size - 1) { Long.MAX_VALUE }
    var length = 0L
    var i = 0
    var j = 0
    for (k in 0 until a.size - 1) {
        val a1: Long
        val a2: Long
        val b1: Long
        val b2: Long
        if (i + 1 < a.size) {
            a1 = a[i]
            a2 = a[i + 1]
        } else {
            if (i < a.size) {
                a1 = a[i]
                a2 = Long.MAX_VALUE
            } else {
                a1 = Long.MAX_VALUE
                a2 = Long.MAX_VALUE
            }
        }
        if (j + 1 < k) {
            b1 = b[j]
            b2 = b[j + 1]
        } else {
            if (j < k) {
                b1 = b[j]
                b2 = Long.MAX_VALUE
            } else {
                b1 = Long.MAX_VALUE
                b2 = Long.MAX_VALUE
            }
        }
        if (a1 < b1) {
            b[k] = a1
            i++
            if (a2 < b1) {
                b[k] += a2
                length += b[k]
                i++
            } else {
                b[k] += b1
                length += b[k]
                j++
            }
        } else {
            b[k] = b1
            j++
            if (a1 < b2) {
                b[k] += a1
                length += b[k]
                i++
            } else {
                b[k] += b2
                length += b[k]
                j++
            }
        }
    }
    return length
}

fun main() {
    val lines = File("D://algos//haffman//src//main//kotlin//huffman.in").readLines()
    val n = lines[0].toInt()
    val frequencies = lines[1].split(" ").map { it.toLong() }.toLongArray()
    val result = decodeHuffman(frequencies)
    File("D://algos//haffman//src//main//kotlin//huffman.out").writeText(result.toString())
}

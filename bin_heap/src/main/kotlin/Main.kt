import java.io.File

fun main() {
    val lines = File("D://algos//bin_heap//src//main//kotlin//input.txt").readLines()
    val n = lines[0].toInt()
    val array = lines[1].split(" ").map { it.toInt() }.toTypedArray()

    val result = if (isBinHeap(array, n)) "Yes" else "No"

    File("D://algos//bin_heap//src//main//kotlin//output.txt").writeText(result)
}

fun isBinHeap(arr: Array<Int>, size: Int): Boolean {

    for (i in 0 until size) {
        val leftChildIndex = 2 * i + 1
        val rightChildIndex = 2 * i + 2


        if (leftChildIndex < size && arr[i] > arr[leftChildIndex]) {
            return false
        }

        if (rightChildIndex < size && arr[i] > arr[rightChildIndex]) {
            return false
        }
    }
    return true
}

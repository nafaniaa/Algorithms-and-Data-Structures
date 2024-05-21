import java.util.*

fun binarySearchAlgorithm(numbers: IntArray, size: Int, target: Int): Int {
    var left = 0
    var right = size
    var mid: Int

    while (left < right) {
        mid = (left + right) / 2
        if (target == numbers[mid]) {
            return 1
        } else if (target < numbers[mid]) {
            right = mid
        } else {
            left = mid + 1
        }
    }
    return 0
}

fun findFirstEqualOrGreaterIndex(numbers: IntArray, size: Int, target: Int): Int {
    var left = 0
    var right = size
    var mid: Int

    while (left < right) {
        mid = (left + right) / 2
        if (target <= numbers[mid]) {
            right = mid
        } else {
            left = mid + 1
        }
    }
    return left
}

fun findFirstGreaterIndex(numbers: IntArray, size: Int, target: Int): Int {
    var left = 0
    var right = size
    var mid: Int

    while (left < right) {
        mid = (left + right) / 2
        if (target < numbers[mid]) {
            right = mid
        } else {
            left = mid + 1
        }
    }
    return left
}

fun main() {
    var arraySize: Int
    var triesSize: Int
    val input = Scanner(System.`in`)

    arraySize = input.nextInt()
    val numbers = IntArray(arraySize)
    for (i in 0 until arraySize) {
        numbers[i] = input.nextInt()
    }

    triesSize = input.nextInt()
    val tries = IntArray(triesSize)
    for (i in 0 until triesSize) {
        tries[i] = input.nextInt()
    }

    input.close()

    for (i in 0 until triesSize) {
        println("${binarySearchAlgorithm(numbers, arraySize, tries[i])} ${findFirstEqualOrGreaterIndex(numbers, arraySize, tries[i])} ${findFirstGreaterIndex(numbers, arraySize, tries[i])}")
    }
}

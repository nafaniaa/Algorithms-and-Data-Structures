import java.io.*


fun main() {
    val inputFile = File("D://algos//lis//src//main//kotlin//input.txt")
    val outputFile = File("D://algos//lis//src//main//kotlin//output.txt")
    val br = BufferedReader(InputStreamReader(FileInputStream(inputFile)))
    val n = br.readLine().toInt()
    val A = br.readLine().split(" ").map { it.toInt() }
    br.close()

    val tails = IntArray(n)
    var length = 0

    for (num in A) {
        var left = 0
        var right = length

        while (left < right) {
            val mid = left + (right - left) / 2
            if (tails[mid] < num) {
                left = mid + 1
            } else {
                right = mid
            }
        }

        tails[left] = num
        if (left == length) {
            length++
        }
    }

    val bw = BufferedWriter(FileWriter(outputFile))
    bw.write("$length")
    bw.close()
}
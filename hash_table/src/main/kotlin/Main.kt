import java.io.File

fun main() {
    val inputFile = File("D://algos//hash_table//src//main//kotlin//input.txt")
    val outputFile = File("D://algos//hash_table//src//main//kotlin//output.txt")

    val lines = inputFile.bufferedReader().use { it.readLines() }
    val (m, c, n) = lines.first().split(" ").map { it.toInt() }

    val table = MutableList(m) { -1 }
    lines.drop(1).map { it.toInt() }.forEach { key ->
        if (!table.contains(key)) {
            for (j in 0 until m) {
                val newIndex = ((key % m) + c * j) % m
                if (table[newIndex] == -1) {
                    table[newIndex] = key
                    break
                }
            }
        }
    }

    outputFile.bufferedWriter().use { it.write(table.joinToString(" ")) }
}

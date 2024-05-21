import java.io.File
import java.io.PrintStream
import java.util.*
import kotlin.collections.HashSet

fun main() {
    val s = HashSet<Long>()
    var sum = 0L

    val scanner = Scanner(File("src/main/input.txt"))
    while (scanner.hasNextLong()) {
        s.add(scanner.nextLong())
    }
    scanner.close()

    for (item in s) {
        sum += item
    }

    val outputStream = File("src/main/output.txt").outputStream()
    PrintStream(outputStream).use { ps ->
        ps.println(sum)
    }
}

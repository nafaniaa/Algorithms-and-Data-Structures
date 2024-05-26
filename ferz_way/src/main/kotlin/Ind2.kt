/*
import java.io.File
import kotlin.math.abs

fun main() {
    val lines = File("D://algos//ferz_way//src//main//kotlin//input.txt").readLines()

    val n = lines[0].toLong()
    val xyCoordinates = lines.drop(1).map { it.split(" ").map { it.toLong() } }

    val ans = mutableListOf(0L)
    val used = BooleanArray(n.toInt())
    used[0] = true
    var cur = 0L
    var totalDistance = 0L

    while (ans.size != n.toInt()) {
        val nextCity = (1 until n.toInt()).filter { !used[it] }.minByOrNull { abs(xyCoordinates[it][0] - xyCoordinates[cur.toInt()][0]) + abs(xyCoordinates[it][1] - xyCoordinates[cur.toInt()][1]) }!!

        used[nextCity] = true
        ans.add(nextCity.toLong())
        totalDistance += abs(xyCoordinates[nextCity][0] - xyCoordinates[cur.toInt()][0]) + abs(xyCoordinates[nextCity][1] - xyCoordinates[cur.toInt()][1])
        cur = nextCity.toLong()
    }

    totalDistance += abs(xyCoordinates[ans[0].toInt()][0] - xyCoordinates[cur.toInt()][0]) + abs(xyCoordinates[ans[0].toInt()][1] - xyCoordinates[cur.toInt()][1])
    ans.add(0)

    val output = File("D://algos//ferz_way//src//main//kotlin//output.txt").bufferedWriter()
    ans.forEach { output.write("${it + 1} ") }
    output.newLine()
    output.write(totalDistance.toString())
    output.newLine()
    output.close()
}
*/

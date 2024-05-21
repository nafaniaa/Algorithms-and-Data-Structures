import java.io.File

fun findRoot(a: Int, dsu: MutableList<Int>): Int {
    val path = mutableListOf<Int>()
    var current = a
    while (dsu[current] >= 0) {
        path.add(current)
        current = dsu[current]
    }
    val root = current
    for (node in path) {
        dsu[node] = root
    }
    return root
}

fun buildRoad(road1: Int, road2: Int, dsu: MutableList<Int>, componentsOfConnectivity: IntArray) {
    var r1 = findRoot(road1, dsu)
    var r2 = findRoot(road2, dsu)
    if (r1 != r2) {
        if (dsu[r1] > dsu[r2]) {
            dsu[r1] += dsu[r2]
            dsu[r2] = r1
        } else {
            dsu[r2] += dsu[r1]
            dsu[r1] = r2
        }
        componentsOfConnectivity[0]--
    }
}

fun main() {
    val lines = File("D://algos//roads_hard//src//main//kotlin//input.txt").readLines()
    val nmq = lines[0].split(" ").map { it.toInt() }
    val n = nmq[0]
    val m = nmq[1]
    val q = nmq[2]
    val dsu = MutableList(n + 1) { -1 }
    var componentsOfConnectivity = intArrayOf(n)
    val roads = lines[1].split(" ").map { it.toInt() }
    val isRoad = MutableList(m) { true }
    val destroyedRoads = mutableListOf<Int>()
    for (i in 2 until 2 + q) {
        val a = lines[i].toInt() - 1
        destroyedRoads.add(a)
        isRoad[a] = false
    }
    for (i in 0 until m) {
        if (isRoad[i]) {
            buildRoad(roads[2 * i], roads[2 * i + 1], dsu, componentsOfConnectivity)
        }
    }

    val result = StringBuilder()
    for (i in q - 1 downTo 0) {
        val city1 = roads[2 * destroyedRoads[i]]
        val city2 = roads[2 * destroyedRoads[i] + 1]
        result.append(if (componentsOfConnectivity[0] == 1) '1' else '0')
        buildRoad(city1, city2, dsu, componentsOfConnectivity)
    }
    File("D://algos//roads_hard//src//main//kotlin//output.txt").bufferedWriter().use { it.write(result.toString()) }
}

import java.io.File
import kotlin.math.abs

data class City(val x: Int, val y: Int, val index: Int)

fun computeDistance(city1: City, city2: City): Int {
    return abs(city1.x - city2.x) + abs(city1.y - city2.y)
}

fun findRoot(parent: IntArray, x: Int): Int {
    if (parent[x] == x) return x
    parent[x] = findRoot(parent, parent[x])
    return parent[x]
}

fun mergeSets(parent: IntArray, a: Int, b: Int) {
    val rootA = findRoot(parent, a)
    val rootB = findRoot(parent, b)
    if (rootA != rootB) parent[rootA] = rootB
}

fun depthFirstSearch(graph: Array<MutableList<Int>>, visited: BooleanArray, result: IntArray, counter: IntArray, v: Int, parentNode: Int) {
    if (visited[v]) {
        visited[v] = false
        result[counter[0]++] = v
    }
    for (neighbor in graph[v]) {
        if (neighbor == parentNode) continue
        depthFirstSearch(graph, visited, result, counter, neighbor, v)
    }
}

fun main() {
    val input = File("D://algos//ferz_way//src//main//kotlin//input.txt").readLines()
    val numCities = input[0].toInt()
    val coordX = IntArray(numCities + 1)
    val coordY = IntArray(numCities + 1)
    val parent = IntArray(numCities + 1) { it }
    val visited = BooleanArray(numCities + 1) { true }
    val graph = Array(numCities + 1) { mutableListOf<Int>() }
    val result = IntArray(numCities + 1)
    val counter = intArrayOf(0)

    for (i in 1..numCities) {
        val (x, y) = input[i].split(" ").map { it.toInt() }
        coordX[i] = x
        coordY[i] = y
    }

    val allEdges = mutableListOf<Pair<Int, Pair<Int, Int>>>()
    for (i in 1..numCities) {
        for (j in i + 1..numCities) {
            allEdges.add(Pair(computeDistance(City(coordX[i], coordY[i], i), City(coordX[j], coordY[j], j)), Pair(i, j)))
        }
    }

    allEdges.sortBy { it.first }

    for (edge in allEdges) {
        val distance = edge.first
        val city1 = edge.second.first
        val city2 = edge.second.second
        if (findRoot(parent, city1) != findRoot(parent, city2)) {
            mergeSets(parent, city1, city2)
            graph[city1].add(city2)
            graph[city2].add(city1)
        }
    }

    depthFirstSearch(graph, visited, result, counter, 1, -1)

    // Замыкаем цикл, добавляя стартовую вершину в конец пути
    result[counter[0]] = result[0]

    var totalDistance: Long = 0
    for (i in 0 until counter[0]) {
        totalDistance += computeDistance(City(coordX[result[i]], coordY[result[i]], result[i]), City(coordX[result[i + 1]], coordY[result[i + 1]], result[i + 1])).toLong()
    }

    File("D://algos//ferz_way//src//main//kotlin//output.txt").printWriter().use { out ->
        for (i in 0..counter[0]) {
            out.print("${result[i]} ")
        }
        out.println()
        out.println(totalDistance)
    }
}

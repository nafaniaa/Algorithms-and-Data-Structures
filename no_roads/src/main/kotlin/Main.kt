import java.io.File

class DisjointSetUnion(n: Int) {
    private val parent = IntArray(n)
    private val rank = IntArray(n)

    init {
        for (i in 0 until n) {
            parent[i] = i
            rank[i] = 0
        }
    }

    fun find(x: Int): Int {
        if (x != parent[x]) {
            parent[x] = find(parent[x])
        }
        return parent[x]
    }

    fun union(x: Int, y: Int) {
        val xRoot = find(x)
        val yRoot = find(y)

        if (xRoot == yRoot) return

        if (rank[xRoot] < rank[yRoot]) {
            parent[xRoot] = yRoot
        } else if (rank[xRoot] > rank[yRoot]) {
            parent[yRoot] = xRoot
        } else {
            parent[yRoot] = xRoot
            rank[xRoot]++
        }
    }
}

fun main() {
    val lines = File("D://algos//no_roads//src//main//kotlin//input.txt").readLines()
    val (n, m, q) = lines[0].split(" ").map { it.toInt() }
    val roads = mutableListOf<Pair<Int, Int>>()
    val destroyedRoads = mutableSetOf<Int>()

    for (i in 1..m) {
        val (u, v) = lines[i].split(" ").map { it.toInt() - 1 } // Subtract 1 to adjust for 0-based indexing
        roads.add(u to v)
    }

    for (i in m + 1 until m + 1 + q) {
        val x = lines[i].toInt() - 1 // Subtract 1 to adjust for 0-based indexing
        destroyedRoads.add(x)
    }

    val dsu = DisjointSetUnion(n)

    // Function to check connectivity
    fun isConnected(): Boolean {
        val representative = dsu.find(0)
        for (i in 1 until n) {
            if (dsu.find(i) != representative) {
                return false
            }
        }
        return true
    }

    val results = mutableListOf<Int>()

    // Simulate earthquakes
    for (i in 0 until q) {
        val (u, v) = roads[i]
        dsu.union(u, v)

        // If the destroyed road is not required for connectivity, it's not a problem
        if (i !in destroyedRoads) {
            results.add(if (isConnected()) 1 else 0)
        } else {
            results.add(0)
        }
    }

    File("D://algos//no_roads//src//main//kotlin//output.txt").writeText(results.joinToString(""))
}

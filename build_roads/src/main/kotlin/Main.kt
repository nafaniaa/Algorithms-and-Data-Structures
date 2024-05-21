import java.io.File

class DSU(n: Int) {
    private val parent = IntArray(n) { it }
    private val rank = IntArray(n) { 0 }

    fun find(x: Int): Int {
        return if (parent[x] != x) {
            parent[x] = find(parent[x]) // Сжатие пути
            parent[x]
        } else {
            x
        }
    }
    fun union(x: Int, y: Int) {
        val rootX = find(x)
        val rootY = find(y)
        if (rootX != rootY) {
            if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY
            } else if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX
            } else {
                parent[rootY] = rootX
                rank[rootX]++
            }
        }
    }

    fun countComponents(): Int {
        var count = 0
        for (i in parent.indices) {
            if (parent[i] == i) {
                count++
            }
        }
        return count
    }
}


fun main() {
    val input = File("D://algos//build_roads//src//main//kotlin//input.txt").bufferedReader()
    val output = File("D://algos//build_roads//src//main//kotlin//output.txt").bufferedWriter()

    val (n, q) = input.readLine().split(" ").map { it.toInt() }
    val dsu = DSU(n)

    repeat(q) {
        val (u, v) = input.readLine().split(" ").map { it.toInt() }
        dsu.union(u - 1, v - 1)
        output.write("${dsu.countComponents()}\n")
    }

    input.close()
    output.close()
}

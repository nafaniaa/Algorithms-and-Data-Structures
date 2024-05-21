import java.io.File
import java.util.*

class Edge(val to: Int, val weight: Long)

fun dijkstra(graph: Array<ArrayList<Edge>>, start: Int, end: Int): Long {
    val n = graph.size
    val dist = LongArray(n) { Long.MAX_VALUE }
    val processed = BooleanArray(n) { false }
    dist[start] = 0L
    val minHeap = PriorityQueue<Pair<Long, Int>>(compareBy { it.first })
    minHeap.add(Pair(0L, start))

    while (minHeap.isNotEmpty()) {
        val (d, v) = minHeap.poll()
        if (processed[v]) continue
        processed[v] = true
        for (edge in graph[v]) {
            val u = edge.to
            val w = edge.weight
            if (!processed[u] && dist[v] + w < dist[u]) {
                dist[u] = dist[v] + w
                minHeap.add(Pair(dist[u], u))
            }
        }
    }
    return dist[end]
}

fun main() {
    val input = File("D://algos//graphs//src//main//kotlin//input.txt").bufferedReader()
    val output = File("D://algos//graphs//src//main//kotlin//output.txt").bufferedWriter()

    val (n, m) = input.readLine().split(" ").map { it.toInt() }
    val graph = Array(n) { ArrayList<Edge>() }

    repeat(m) {
        val (u, v, w) = input.readLine().split(" ").map { it.toLong() }
        graph[u.toInt() - 1].add(Edge(v.toInt() - 1, w))
        graph[v.toInt() - 1].add(Edge(u.toInt() - 1, w))
    }

    val shortestPathLength = dijkstra(graph, 0, n - 1)
    output.write("$shortestPathLength")

    output.close()
}

import java.util.*

class Edge(val to: Int, var capacity: Int, var reversedEdge: Int)

fun maxFlow(graph: Array<ArrayList<Edge>>, source: Int, sink: Int): Int {
    val n = graph.size
    var flow = 0
    while (true) {
        val parent = IntArray(n) { -1 }
        val queue: Queue<Int> = LinkedList()
        queue.add(source)
        parent[source] = source
        while (queue.isNotEmpty() && parent[sink] == -1) {
            val u = queue.poll()
            for (e in graph[u]) {
                if (parent[e.to] == -1 && e.capacity > 0) {
                    parent[e.to] = u
                    queue.add(e.to)
                }
            }
        }
        if (parent[sink] == -1) break
        var cur = sink
        var pathFlow = Int.MAX_VALUE
        while (cur != source) {
            val prev = parent[cur]
            val edge = graph[prev].first { it.to == cur }
            pathFlow = minOf(pathFlow, edge.capacity)
            cur = prev
        }
        cur = sink
        while (cur != source) {
            val prev = parent[cur]
            val edge = graph[prev].first { it.to == cur }
            edge.capacity -= pathFlow
            val reversedEdgeIndex = edge.reversedEdge
            if (reversedEdgeIndex != -1) {
                val reversedEdge = graph[cur][reversedEdgeIndex]
                reversedEdge.capacity += pathFlow
            }
            cur = prev
        }
        flow += pathFlow
    }
    return flow
}

fun main() {
    val input = Scanner(System.`in`)
    val n = input.nextInt()
    val m = input.nextInt()
    val graph = Array(n) { ArrayList<Edge>() }
    repeat(m) {
        val u = input.nextInt() - 1
        val v = input.nextInt() - 1
        val capacity = input.nextInt()
        val existingEdge = graph[u].firstOrNull { it.to == v }
        if (existingEdge != null) {
            existingEdge.capacity += capacity
        } else {
            graph[u].add(Edge(v, capacity, -1))
            graph[v].add(Edge(u, 0, -1)) // Adding reverse edge with 0 capacity
            graph[v].last().reversedEdge = graph[u].size - 1
            graph[u].last().reversedEdge = graph[v].size - 1
        }
    }
    println(maxFlow(graph, 0, n - 1))
}

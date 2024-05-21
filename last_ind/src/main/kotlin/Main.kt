import java.io.File

fun findSpanningTree(graph: Array<IntArray>): List<Pair<Int, Int>> {
    val n = graph.size
    val edges = mutableListOf<Pair<Int, Int>>()
    val visited = BooleanArray(n) // Для отслеживания посещенных вершин

    fun dfs(node: Int) {
        visited[node] = true // Помечаем вершину как посещенную
        for (i in 0 until n) {
            if (graph[node][i] == 1 && !visited[i]) {
                edges.add(Pair(node + 1, i + 1)) // Добавляем ребро в список
                dfs(i) // Рекурсивно идем в глубину
            }
        }
    }

    dfs(0) // Начинаем с первой вершины

    if (n == 1){
        edges.add(Pair(0 , 0))
        return edges
    }

    // Проверяем, были ли посещены все вершины
    if (visited.all { it }) {
        return edges
    } else {
        return listOf() // Возвращаем пустой список, если граф несвязный
    }


}

fun main() {
    val inputFile = File("D://algos//last_ind//src//main//kotlin//input.txt")
    val outputFile = File("D://algos//last_ind//src//main//kotlin//output.txt")

    val lines = inputFile.readLines()
    val n = lines[0].toInt()
    val graph = Array(n) { IntArray(n) }

    for (i in 1..n) {
        val row = lines[i].split(" ").map { it.toInt() }
        for (j in 0 until n) {
            graph[i - 1][j] = row[j]
        }
    }

    val result = findSpanningTree(graph)

    outputFile.bufferedWriter().use { out ->
        if (result.isEmpty()) {
            out.write("-1")
        }else if(result.first() == Pair(0,0)){
            out.write("0")
        }else {
            out.write("${result.size}\n")
            for (edge in result) {
                out.write("${edge.first} ${edge.second}\n")
            }
        }
    }

 
}

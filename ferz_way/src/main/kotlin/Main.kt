/*
import java.io.File
import java.util.LinkedList
import java.util.Queue

data class Position(val x: Int, val y: Int)

val directions = listOf(
    Position(1, 0), Position(1, 1), Position(0, 1), Position(-1, 1),
    Position(-1, 0), Position(-1, -1), Position(0, -1), Position(1, -1)
)

fun isValid(x: Int, y: Int, columns: Int, rows: Int, board: Array<Array<Boolean>>): Boolean {
    return x in 0 until columns && y in 0 until rows && board[x][y]
}

fun bfs(start: Position, end: Position, columns: Int, rows: Int, board: Array<Array<Boolean>>): List<List<Position>> {
    val queue: Queue<Position> = LinkedList()
    val visited = Array(columns) { Array(rows) { false } }
    val parent = mutableMapOf<Position, MutableList<Position>>()
    val resultPaths = mutableListOf<List<Position>>()
    val minSteps = mutableMapOf<Position, Int>()

    queue.add(start)
    visited[start.x][start.y] = true
    minSteps[start] = 0

    while (queue.isNotEmpty()) {
        val currentPos = queue.poll()
        val currentSteps = minSteps[currentPos]!!

        for (dir in directions) {
            var nextX = currentPos.x + dir.x
            var nextY = currentPos.y + dir.y
            while (isValid(nextX, nextY, columns, rows, board)) {
                val nextPos = Position(nextX, nextY)

                if (!visited[nextX][nextY]) {
                    visited[nextX][nextY] = true
                    queue.add(nextPos)
                    parent.getOrPut(nextPos) { mutableListOf() }.add(currentPos)
                    minSteps[nextPos] = currentSteps + 1
                } else if (minSteps[nextPos] == currentSteps + 1) {
                    parent[nextPos]!!.add(currentPos)
                }

                nextX += dir.x
                nextY += dir.y
            }
        }
    }

    fun buildPaths(end: Position): List<List<Position>> {
        val paths = mutableListOf<List<Position>>()
        val path = mutableListOf<Position>()

        fun dfs(pos: Position) {
            path.add(pos)
            if (pos == start) {
                paths.add(path.reversed().toList())
            } else {
                parent[pos]?.forEach { dfs(it) }
            }
            path.removeAt(path.size - 1)
        }

        dfs(end)
        return paths
    }

    resultPaths.addAll(buildPaths(end))
    return resultPaths
}

fun main() {
    val input = File("D://algos//ferz_way//src//main//kotlin//input.txt").readLines()
    val columns = input[0].toInt()
    val rows = input[1].toInt()
    val blackPiecesCount = input[2].toInt()
    val board = Array(columns) { Array(rows) { true } }

    for (i in 0 until blackPiecesCount) {
        val (pieceX, pieceY) = input[3 + i].split(" ").map { it.toInt() }
        board[pieceX][pieceY] = false
    }

    val (startX, startY) = input[3 + blackPiecesCount].split(" ").map { it.toInt() }
    val (endX, endY) = input[4 + blackPiecesCount].split(" ").map { it.toInt() }
    val start = Position(startX, startY)
    val end = Position(endX, endY)

    val paths = bfs(start, end, columns, rows, board)

    if (paths.isEmpty()) {
        File("D://algos//ferz_way//src//main//kotlin//output.txt").writeText("no_solutions")
    } else {
        val output = buildString {
            paths.forEach { path ->
                appendLine(path.size - 1)
                path.forEach { pos ->
                    appendLine("${pos.x} ${pos.y}")
                }
            }
        }

        File("D://algos//ferz_way//src//main//kotlin//output.txt").writeText(output.trim())
    }
}
*/

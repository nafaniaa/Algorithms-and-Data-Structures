/*
import java.io.File

data class Position(val x: Int, val y: Int) // позиция шахмотной доски

//список всех возможных направлений, в которых может двигаться ферзь
val directions = listOf(
    Position(1, 0), Position(1, 1), Position(0, 1), Position(-1, 1),
    Position(-1, 0), Position(-1, -1), Position(0, -1), Position(1, -1)
)

//проверка допустимости кода, возвращает тру , если клетка находится в пределах поля и не занята чёрной фигурой
fun isValid(x: Int, y: Int, columns: Int, rows: Int, board: Array<Array<Boolean>>): Boolean {
    return x in 0 until columns && y in 0 until rows && board[x][y]
}

fun getAllPaths(start: Position, end: Position, columns: Int, rows: Int, board: Array<Array<Boolean>>): List<List<Position>> {
    // Создание двумерного массива для хранения минимального числа шагов до каждой клетки
    val minSteps = Array(columns) { Array(rows) { Int.MAX_VALUE } }
    // Создание двумерного массива для хранения списка родителей каждой клетки
    val parent = Array(columns) { Array(rows) { mutableListOf<Position>() } }

    // Инициализация начальной позиции с нулевым числом шагов
    minSteps[start.x][start.y] = 0

    val queue = mutableListOf<Position>()
    queue.add(start)

    while (queue.isNotEmpty()) {
        val current = queue.removeAt(0)
        val currentSteps = minSteps[current.x][current.y]

        // Перебор всех возможных направлений движения
        for (dir in directions) {
            var nx = current.x + dir.x
            var ny = current.y + dir.y
            while (isValid(nx, ny, columns, rows, board)) {
                // Если найден новый кратчайший путь до клетки
                if (minSteps[nx][ny] > currentSteps + 1) {
                    minSteps[nx][ny] = currentSteps + 1
                    parent[nx][ny].clear()
                    parent[nx][ny].add(current)
                    queue.add(Position(nx, ny))
                } else if (minSteps[nx][ny] == currentSteps + 1) {
                    // Если найдено ещё одно кратчайшее решение
                    parent[nx][ny].add(current)
                }
                nx += dir.x
                ny += dir.y
            }
        }
    }
//восстановление путей
    fun buildPaths(pos: Position): List<List<Position>> {
        val resultPaths = mutableListOf<List<Position>>()
        val path = mutableListOf<Position>()

        fun dfs(current: Position) {
            path.add(current)
            if (current == start) {
                // Если достигнута начальная позиция, добавляем путь в результаты
                resultPaths.add(path.reversed().toList())
            } else {
                parent[current.x][current.y].forEach { dfs(it) }
            }
            path.removeAt(path.size - 1)
        }

    // Начинаем поиск путей с конечной позиции
        dfs(end)
        return resultPaths
    }

    return if (minSteps[end.x][end.y] == Int.MAX_VALUE) {
        emptyList()
    } else {
        buildPaths(end)
    }
}

fun main() {
    val input = File("D://algos//ferz_way//src//main//kotlin//input.txt").readLines()
    val columns = input[0].toInt()
    val rows = input[1].toInt()
    val blackPiecesCount = input[2].toInt()
    val board = Array(columns) { Array(rows) { true } }

    // Заполнение шахматной доски информацией о чёрных фигурах
    for (i in 0 until blackPiecesCount) {
        val (pieceX, pieceY) = input[3 + i].split(" ").map { it.toInt() }
        board[pieceX][pieceY] = false
    }

    val (startX, startY) = input[3 + blackPiecesCount].split(" ").map { it.toInt() }
    val (endX, endY) = input[4 + blackPiecesCount].split(" ").map { it.toInt() }
    val start = Position(startX, startY)
    val end = Position(endX, endY)

    val paths = getAllPaths(start, end, columns, rows, board)

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

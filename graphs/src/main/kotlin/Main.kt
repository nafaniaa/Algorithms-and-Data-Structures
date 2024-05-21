import java.io.File
import java.util.*

fun findTreeRoot(matrix: Array<IntArray>): Int {
    for (i in matrix.indices) {
        var isRoot = true
        for (j in matrix.indices) {
            if (matrix[j][i] == 1) {
                isRoot = false
                break
            }
        }
        if (isRoot) {
            return i + 1 //  номерация начинается с 1
        }
    }
    return -1 // если корень не найден
}

fun bfs(matrix: Array<IntArray>, root: Int): IntArray {
    val n = matrix.size
    val parent = IntArray(n) // Массив для хранения родителей вершин
    val visited = BooleanArray(n) // Массив для отслеживания посещенных вершин
    val queue: Queue<Int> = LinkedList()
    queue.offer(root)// добавление корня дерева для начала обхода
    visited[root - 1] = true // Отмечаем корень как посещенный



    //пока в очереди есть элементы
    while (queue.isNotEmpty()) {
        val current = queue.poll()//Извлекаем из очереди текущую вершину для обработки
        for (child in matrix[current - 1].indices) {
            if (matrix[current - 1][child] == 1 && !visited[child]) {
                parent[child] = current
                queue.offer(child + 1) // добавляем 1, так как номерация начинается с 1
                visited[child] = true
            }
        }
    }

    return parent
}

fun main() {
    val inputFile = File("D://algos//graphs//src//main//kotlin//input.txt")
    val outputFile = File("D://algos//graphs//src//main//kotlin//output.txt")
    val scanner = Scanner(inputFile)
    val n = scanner.nextInt()
    val matrix = Array(n) { IntArray(n) }

    for (i in 0 until n) {
        for (j in 0 until n) {
            matrix[i][j] = scanner.nextInt()
        }
    }

    val root = findTreeRoot(matrix)
    if (root != -1) {
        val canonMatrix = bfs(matrix, root)
        outputFile.bufferedWriter().use { writer ->
            writer.write(canonMatrix.joinToString(" "))
        }
    } else {
        println("smth bad")
    }
}

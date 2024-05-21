import java.io.File

fun main() {
    // Чтение входных данных из файла
    val input = File("D://algos//see_fight//src//main//kotlin//input.txt").readLines()

    // Инициализация игрового поля
    val field = Array(10) { CharArray(10) }
    for (i in 0 until 10) {
        field[i] = input[i].toCharArray()
    }

    // Количество ударов
    val numAttacks = input[10].toInt()

    val attacks = mutableListOf<Pair<Int, Int>>()
    for (i in 0 until numAttacks) {
        val (y, xChar) = input[i + 11].split(" ")
        val x = xChar[0] - 'a' // преобразуем в числовое значение
        attacks.add(Pair(y.toInt() - 1, x))
    }

    // Обработка ударов
    val output = mutableListOf<String>()
    var gameOver = false

    val hitCells = mutableSetOf<Pair<Int, Int>>() // Для хранения уже попавших ударов
    var shipsLeft = countShips(field)

    for ((y, x) in attacks) {
        if (gameOver) break
        if (y in 0 until 10 && x in 0 until 10) { // Проверка на выход за пределы массива
            if (Pair(y, x) in hitCells) {
                output.add("MISS") // Уже совершенный удар
            } else {
                hitCells.add(Pair(y, x))
                when (field[y][x]) {
                    '.', 'X' -> {
                        output.add("MISS")
                        field[y][x] = 'X'
                    }
                    '#' -> {
                        field[y][x] = 'X'
                        val hit = hitShip(y, x, field)
                        if (hit > 0) {
                            output.add("HIT")
                        } else {
                            output.add("DEAD")
                            shipsLeft--
                            if (shipsLeft == 0) {
                                output.add("GAME OVER")
                                gameOver = true
                            }
                        }
                    }
                }
            }
        }
    }

    // Запись результатов в файл
    File("D://algos//see_fight//src//main//kotlin//output.txt").writeText(output.joinToString("\n"))
}

// Подсчет попаданий по кораблю
fun hitShip(y: Int, x: Int, field: Array<CharArray>): Int {
    var hitCount = 0
    for (i in -1..1) {
        for (j in -1..1) {
            val newY = y + i
            val newX = x + j
            if (newY in 0 until 10 && newX in 0 until 10 && field.getOrNull(newY)?.getOrNull(newX) == '#') {
                hitCount++
            }
        }
    }
    return hitCount
}

// Подсчет количества решёток
fun countShips(field: Array<CharArray>): Int {
    var count = 0
    for (i in field.indices) {
        for (j in field[i].indices) {
            if (field[i][j] == '#') {
                count++
            }
        }
    }
    return count
}

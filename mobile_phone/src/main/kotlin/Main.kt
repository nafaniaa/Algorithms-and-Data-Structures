import java.io.File

fun main() {
    val inputFile = File("D://algos//mobile_phone//src//main//kotlin//in.txt")
    val outputFile = File("D://algos//mobile_phone//src//main//kotlin//out.txt")

    val inputLines = inputFile.readLines()

    val numberOfButtons = inputLines[0].toInt()
    val numberOfLetters = inputLines[1].toInt()
    val frequencies = inputLines.drop(2).map { it.split(" ").map(String::toLong) }

    val dp = Array(numberOfButtons) { LongArray(numberOfLetters) }

    var k = 0

    fun calculateMinimumKeyPresses(n: Int, m: Int): Unit {
        val frequency = frequencies[m - 1][0]

        // Если это первая клавиша
        if (n == 1) {
            dp[0][m - 1] = if (m == 1) { // если это первая буква на первой клавише
                frequency
            } else {
                //  вычисляем количество нажатий, добавляя к предыдущему результату вес текущей буквы умноженный на ее порядковый номер
                dp[0][m - 2] + frequency * m
            }
            return
        }

        if (n > m) {
            // Если  больше клавиш, чем букв в алфавите,то копируем результат с предыдущей клавиши
            dp[n - 1][m - 1] = dp[m - 1][m - 1]
            return
        }

        var minIndex = k // индекс на который домножаю
        var minResult = dp[n - 2][m - 2] + frequency
        var sum = frequency
        var accumulatedFrequency = frequency

        for (i in k downTo 1) {
            //frequencies[m - 1 - (k - i) - 1][0] -- это вес текущей буквы.
            sum += frequencies[m - 1 - (k - i) - 1][0] + accumulatedFrequency
            accumulatedFrequency += frequencies[m - 1 - (k - i) - 1][0]

            val tempResult = dp[n - 2][m - 1 - (k - i + 1) - 1] + sum
            if (tempResult < minResult) {
                minResult = tempResult
                minIndex = k - i + 1
            }
        }

        dp[n - 1][m - 1] = minResult
        k = minIndex + 1
    }

    for (i in 1..numberOfButtons) {
        k = 0
        for (j in 1..numberOfLetters) {
            calculateMinimumKeyPresses(i, j)
        }
    }

    outputFile.bufferedWriter().use { writer ->
        writer.write(dp[numberOfButtons - 1][numberOfLetters - 1].toString())
    }
}

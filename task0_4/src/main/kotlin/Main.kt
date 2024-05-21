fun main() {
    val very_very_big_number = 1000000007L
    var n = 0
    var k = 0
    readLine()?.split(" ")?.let { list ->
        n = list[0].toInt()
        k = list[1].toInt()
    }

    //количество единиц больше нулей
    if (k > n) {
        println(0)
        return
    }

    var numerator = 1L //числитель бин. еоэф.
    for (i in n downTo n - k + 1) {
        numerator = (numerator * i) % very_very_big_number
    }
    var denominator = 1L // знаменатель
    for (i in 1..k) {
        denominator = (denominator * i) % very_very_big_number
    }


    println((numerator * exponentiation(denominator, very_very_big_number - 2, very_very_big_number)) % very_very_big_number)
}

fun exponentiation(base: Long, exponent: Long, mod: Long): Long {
    var rem = 1L
    var exp = exponent
    var b = base
    while (exp > 0) {
        if (exp % 2 == 1L) {
            rem = (rem * b) % mod
        }
        b = (b * b) % mod
        exp /= 2
    }
    return rem
}


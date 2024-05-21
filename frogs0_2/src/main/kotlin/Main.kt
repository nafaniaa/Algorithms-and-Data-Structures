fun numberOfDeadMosquitoes(array: ArrayList<Int>): ArrayList<Int> {
    val F: ArrayList<Int> = ArrayList()

    if (array.size >= 1) {
        F.add(array[0])
    }

    if (array.size >= 2) {
        F.add(-1)
    }

    if (array.size >= 3) {
        F.add(array[0] + array[2])
    }

    for (i in 3 until array.size) {
        F.add(maxOf(F[i - 2], F[i - 3]) + array[i])
    }
    return F

}

fun destroyedHummcocks(F: ArrayList<Int>,array: ArrayList<Int> ): List<Int> {
    var hummcocks: ArrayList<Int> = ArrayList()
    var num: Int = array.size
    hummcocks.add(num)
    while (num >= 4) {
        if (maxOf(F[num - 3], F[num - 4]) == F[num - 3]) {
            num -= 2

        } else if (maxOf(F[num - 3], F[num - 4]) == F[num - 4]) {
            num -= 3

        }
        hummcocks.add(num)
    }
    return hummcocks.reversed()
}



fun main() {
    val arr : ArrayList<Int> = ArrayList()
    val length = readLine()!!.toInt()
    val elements = readLine()!!.split(" ")
    for (i in 0 until length) {
        arr.add(elements[i].toInt()) // Use add method to add elements
    }

    val deadMos = numberOfDeadMosquitoes(arr)
    println(deadMos.last())
    val hummcocks = destroyedHummcocks(deadMos, arr)
    if (arr.size != 2) {
        for (hum in hummcocks) {
            print("$hum ")
        }
    }
}
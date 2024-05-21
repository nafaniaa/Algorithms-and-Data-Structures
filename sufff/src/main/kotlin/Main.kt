data class Suffix(var index: Int, var rank: IntArray)

fun computeSuffixArray(str: String): IntArray {
    val n = str.length
    val suffixes = Array(n) { Suffix(index = 0, rank = IntArray(2)) }

    for (i in 0 until n) {
        suffixes[i].index = i
        suffixes[i].rank[0] = str[i] - 'a'
        suffixes[i].rank[1] = if (i + 1 < n) str[i + 1] - 'a' else -1
    }

    suffixes.sortWith(compareBy({ it.rank[0] }, { it.rank[1] }))

    val index = IntArray(n)

    var k = 4
    while (k < 2 * n) {
        var rankValue = 0
        var prevRank = suffixes[0].rank[0]
        suffixes[0].rank[0] = rankValue
        index[suffixes[0].index] = 0

        for (i in 1 until n) {
            if (suffixes[i].rank[0] == prevRank && suffixes[i].rank[1] == suffixes[i - 1].rank[1]) {
                prevRank = suffixes[i].rank[0]
                suffixes[i].rank[0] = rankValue
            } else {
                prevRank = suffixes[i].rank[0]
                suffixes[i].rank[0] = ++rankValue
            }
            index[suffixes[i].index] = i
        }

        for (i in 0 until n) {
            val nextIndex = suffixes[i].index + k / 2
            suffixes[i].rank[1] = if (nextIndex < n) suffixes[index[nextIndex]].rank[0] else -1
        }

        suffixes.sortWith(compareBy({ it.rank[0] }, { it.rank[1] }))
        k *= 2
    }

    val suffixArray = IntArray(n)
    for (i in 0 until n) {
        suffixArray[i] = suffixes[i].index
    }

    return suffixArray
}

fun main() {
    val str = readLine()!!
    val suffixArray = computeSuffixArray(str)

    println(str.length)
    for (i in suffixArray.indices) {
        print("${suffixArray[i] + 1} ")
    }
}

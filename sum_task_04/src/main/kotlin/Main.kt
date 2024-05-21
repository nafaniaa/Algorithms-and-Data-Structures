import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))

    var st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()

    st = StringTokenizer(br.readLine())
    val array = MutableList(n) { st.nextToken().toLong() }

    val prefixSum = MutableList(n + 1) { 0L }
    for (i in 1..n) {
        prefixSum[i] = prefixSum[i - 1] + array[i - 1]
    }

    val q = br.readLine().toInt()
    repeat(q) {
        st = StringTokenizer(br.readLine())
        when (st.nextToken()) {
            "Add" -> {
                val i = st.nextToken().toInt()
                val x = st.nextToken().toLong()
                for (j in i + 1..n) {
                    prefixSum[j] += x
                }
            }
            "FindSum" -> {
                val l = st.nextToken().toInt()
                val r = st.nextToken().toInt()
                println(prefixSum[r] - prefixSum[l])
            }
        }
    }
}

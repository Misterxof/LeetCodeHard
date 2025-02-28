package o_z_n_t_Shortest_Common_Supersequence

class ShortestCommonSupersequence {
    fun shortestCommonSupersequence(str1: String, str2: String): String {
        val dp = Array(str1.length + 1) { IntArray(str2.length + 1) }
        val res = StringBuilder()
        var i = 0
        var j = 0

        for (i in str1.indices.reversed()) {
            for (j in str2.indices.reversed()) {
                 if (str1[i] == str2[j]) {
                     dp[i][j] = 1 + dp[i + 1][j + 1]
                } else {
                     dp[i][j] = maxOf(dp[i + 1][j], dp[i][j + 1])
                }
            }
        }

        while (i < str1.length && j < str2.length) {
            when {
                str1[i] == str2[j] -> {
                    res.append(str1[i++])
                    j++
                }
                dp[i + 1][j] < dp[i][j + 1] -> res.append(str2[j++])
                else -> res.append(str1[i++])
            }
        }

        while (i < str1.length) res.append(str1[i++])
        while (j < str2.length) res.append(str2[j++])

        return res.toString()
    }
}

fun main() {
    println(ShortestCommonSupersequence().shortestCommonSupersequence(
        "abac",
        "cab"
    ))
    println(ShortestCommonSupersequence().shortestCommonSupersequence(
        "aaaaaaaa",
        "aaaaaaaa"
    ))
}
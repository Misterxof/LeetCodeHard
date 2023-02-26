package se_t_Edit_Distance

class EditDistance {
    fun minDistance(word1: String, word2: String): Int {
        val array = Array(word1.length + 1) { IntArray(word2.length + 1) }

        for (i in 0..word1.length) {
            for (j in 0..word2.length) {
                array[i][j] = when {
                    i == 0 -> j
                    j == 0 -> i
                    word1[i - 1] == word2[j - 1] -> array[i - 1][j - 1]
                    else -> 1 + listOf(array[i][j - 1], array[i - 1][j], array[i - 1][j - 1]).min()
                }
            }
        }
        return array.last().last()
    }

    fun minDistanceRecursion(w1: String, w2: String, i1: Int, i2: Int): Int {
        if (w1.isBlank() || w2.isBlank()) return 0
        if (i1 == 0) return i2
        if (i2 == 0) return i1
        if (w1[i1 - 1] == w2[i2 - 1]) return minDistanceRecursion(w1, w2, i1 - 1, i2 -1)
        else {
            val insert = minDistanceRecursion(w1, w2, i1, i2 - 1)
            val delete = minDistanceRecursion(w1, w2, i1 - 1, i2)
            val replace = minDistanceRecursion(w1, w2, i1 - 1, i2 - 1)
            return minOf(insert, minOf(delete, replace)) + 1
        }
    }
}

fun main() {
    println(EditDistance().minDistance("hores", "ros"))
}
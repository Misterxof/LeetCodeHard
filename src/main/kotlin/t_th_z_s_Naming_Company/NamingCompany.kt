package t_th_z_s_Naming_Company

class NamingCompany {
    fun distinctNames(ideas: Array<String>): Long {
        var result = 0L
        val hashMap = hashMapOf<String, Int>()

        ideas.forEach {
            hashMap[it] = 0
        }

        ideas.forEachIndexed { i, it ->
            ideas.forEachIndexed { j, jt ->
                if (i != j) {
                    val first = it.replaceFirst(it[0], jt[0])
                    val second = jt.replaceFirst(jt[0], it[0])
                    if (!hashMap.containsKey(first) && !hashMap.containsKey(second)) result++
                }
            }
        }
        return result
    }
}

fun main() {
    println(NamingCompany().distinctNames(arrayOf("coffee","donuts","time","toffee")))
    println(NamingCompany().distinctNames(arrayOf("lack","back")))
}
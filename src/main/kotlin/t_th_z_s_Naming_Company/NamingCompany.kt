package t_th_z_s_Naming_Company

class NamingCompany {
    fun distinctNames(ideas: Array<String>): Long {
        var result = 0L
        val hashMap = hashMapOf<Char, MutableSet<String>>()
        val ignore = hashMapOf<Char, Int>() // skip that we already checked

        ideas.forEach {
            if (hashMap.containsKey(it[0]))
                hashMap[it[0]]!!.add(it.replaceFirst(it[0].toString(), ""))
            else
                hashMap[it[0]] = mutableSetOf(it.replaceFirst(it[0].toString(), ""))
        }

        hashMap.forEach { it ->
            hashMap.forEach { jt ->
                if (it.key != jt.key && !ignore.containsKey(jt.key)) {
                    // find only unique members in two sets
                    val r = it.value.minus(jt.value)
                    val r2 = jt.value.minus(it.value)

                    if (r.isNotEmpty() && r2.isNotEmpty())
                        result += r.size * r2.size * 2
                }
            }
            ignore[it.key] = 0
        }

        return result
    }
}

fun main() {
    println(NamingCompany().distinctNames(arrayOf("coffee","donuts","time","toffee")))
    println(NamingCompany().distinctNames(arrayOf("lack","back")))
    println(NamingCompany().distinctNames(arrayOf("ps","yn","eciuehjexw","pdesv","e","dz","xxdnk","vdqjhncfj")))
}
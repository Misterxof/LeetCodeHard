package f_s_z_LFU_Cache

class LFUCache2(capacity: Int) {
    private val cacheHashMap: MutableList<Triple<Int, Int, Int>>
    private var capacity = 0
    private var currentCapacity = 0
    private var lastUsedKey: MutableList<Int>

    init {
        this.capacity = capacity
        lastUsedKey = mutableListOf()
        cacheHashMap = mutableListOf()
        print("null, ")
    }

    fun get(key: Int): Int {
        val result = cacheHashMap.findLast { key == it.first }
        val resultIndex = cacheHashMap.indexOf(result)
        print("${result?.second ?: -1}, ")
//        println()
//        cacheHashMap.forEach{
//            println("(${it.first}, ${it.second})")
//        }
        result?.let {
            result!!.second + 1
            cacheHashMap[resultIndex] = Triple(result!!.first, result.second, result!!.third + 1)
            lastUsedKey.add(key)
        }

        return result?.second ?: -1
    }

    fun put(key: Int, value: Int) {
//        println("\nstart")
//        cacheHashMap.forEach {
//            println("(${it.first}, ${it.third})")
//        }

        if (isKeyExist(key)) {
            changeValueByKey(key, value)
            lastUsedKey.add(key)
        } else {
            if (currentCapacity < capacity) {
                cacheHashMap.add(Triple(key, value, 1))
                currentCapacity++
                lastUsedKey.add(key)
            } else if (capacity != 0) {
                val keyRemove = getLFU()
//                print(" rem=$keyRemove ")
                val element = getElement(keyRemove)
                val elementIndex = getElementIndex(element!!)
                cacheHashMap.remove(getElement(keyRemove))
                cacheHashMap.add(Triple(key, value, 1))
//                print(" new=($key, $value) ")
                lastUsedKey.removeAll { it == keyRemove }
                lastUsedKey.add(key)
            }
        }

//        println("end")
//        cacheHashMap.forEach {
//            println("(${it.first}, ${it.third})")
//        }
        print("null, ")
    }

    private fun changeValueByKey(key: Int, value: Int) {
        val element = getElement(key)
        val elementIndex = getElementIndex(element!!)
        cacheHashMap[elementIndex] = Triple(element.first, value, element.third + 1)
    }

    private fun isKeyExist(key: Int) = getElement(key) != null

    private fun getElement(key: Int) = cacheHashMap.find { key == it.first }

    private fun getElementIndex(triple: Triple<Int, Int, Int>) = cacheHashMap.indexOf(triple)

    private fun getLFU(): Int {
        var list: MutableList<Pair<Int, Int>> = mutableListOf() // <key, counter>
        var index = 0
        cacheHashMap.forEach { triple ->
            if (list.isEmpty()) {
                list.add(Pair(0, 10000))
            }
            if (triple.third < list[index].second) {
                if (index >= 1) {
                    list = mutableListOf(Pair(triple.first, triple.third))
                    index = 0
                } else
                    list[index] = Pair(triple.first, triple.third)
            } else {
                index++
                list.add(Pair(triple.first, triple.third))
            }
        }

//        println("LFu")
//        list.forEach{
//            println(it)
//        }
        if (list.size > 1) {
            var index = -1
            while (list.size > 1){
                var indexInList = 0
                index = -1
                list.forEachIndexed() {indexaPair, pair ->
                    val tempIndex = lastUsedKey.lastIndexOf(pair.first)

                    if (index == -1){
                        index = tempIndex!!
                        indexInList = indexaPair
                    }


                    if (tempIndex!! >= index) {
                        index = tempIndex
                        indexInList = indexaPair
                    }
//                    println("last = $tempIndex find = ${pair.first} index=${index}")
                }
                list.removeAt(indexInList)
            }

//            list.forEach { pair ->
//
//                val tempIndex = lastUsedKey.indexOf(pair.first)
//
////                println("last = $tempIndex find = ${pair.first} size=${lastUsedKey.size}")
////                lastUsedKey.forEachIndexed { index, value ->
////                    println("($index, $value)")
////                }
//
//                if (index == -1)
//                    index = tempIndex!!
//
//                if (tempIndex!! <= index) {
//                    index = tempIndex
//                }
//            }

            return list[0].first
        } else {
            return list[0].first
        }
    }

}

package f_s_z_LFU_Cache

class LFUCache(capacity: Int) {
    private val cacheHashMap: HashMap<Int, Pair<Int, Int>>
    private var capacity = 0
    private var currentCapacity = 0
    private var lastUsedKey: MutableList<Int>

    init {
        this.capacity = capacity
        lastUsedKey = mutableListOf()
        cacheHashMap = HashMap()
        print("null, ")
    }

    fun get(key: Int): Int {
        val result = cacheHashMap[key]
        print("${result?.first ?: -1}, ")
        result?.let {
            result!!.second + 1
            lastUsedKey += key
        }

        return result?.first ?: -1
    }

    fun put(key: Int, value: Int) {
        if (currentCapacity < capacity)  {
            cacheHashMap[key] = Pair(value, 1)
            currentCapacity++
            lastUsedKey += key
        } else {
            val keyRemove = getLFU()
            cacheHashMap.remove(keyRemove)
            cacheHashMap[key] = Pair(value, 1)
            lastUsedKey.removeAll{ it == keyRemove}
            lastUsedKey += key
        }
        print("null, ")
    }

    private fun getLFU(): Int{
        var list: MutableList<Pair<Int, Int>> = mutableListOf() // <key, counter>
        var index = 0
        cacheHashMap.forEach { k, v ->
            if (list.isEmpty()){
                list += Pair(0, 0)
            }
            if (v.second > list[index].second){
                if (index >= 1){
                    list = mutableListOf(Pair(k, v.second))
                    index = 0
                } else
                    list[index] = Pair(k, v.second)
            } else {
                index++
                list += Pair(k, v.second)
            }
        }

        if (list.size > 1){
            var index = -1
            list.forEach {  pair ->

                val tempIndex = lastUsedKey.find { it == pair.first }

                if (index == -1)
                    index = tempIndex!!

                if (tempIndex!! <= index){
                    index = tempIndex
                }
            }

            return lastUsedKey[index]
        } else {
            return list[0].first
        }
    }

}
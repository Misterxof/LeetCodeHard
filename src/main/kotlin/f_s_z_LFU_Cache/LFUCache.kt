package f_s_z_LFU_Cache

class LFUCache(capacity: Int) {
    private val cacheHashMap: HashMap<Int, Int>
    private val countersHashMap: HashMap<Int, Int>
    private var minUsed = 0
    private var capacity = 0
    private var lastUsedKey: HashMap<Int, MutableList<Int>>

    init {
        this.capacity = capacity
        cacheHashMap = hashMapOf()
        countersHashMap = hashMapOf()
        lastUsedKey = hashMapOf()
        print("null, ")
    }

    fun get(key: Int): Int {
        val result = cacheHashMap[key]
        print("${result ?: -1}, ")
        result?.let {
            updateMinimalUsed(key)
        }
        return result ?: -1
    }

    fun put(key: Int, value: Int) {
        if (capacity == 0) {
            print("null, ")
            return
        }

        if (cacheHashMap.containsKey(key))
            updateValue(key, value)
        else {
            if (cacheHashMap.size < capacity) {
                cacheHashMap[key] = value
                countersHashMap[key] = 1
                addOrUpdateLastUsed(key, 1)
                minUsed = 1
            } else {
                replaceEntry(key, value)
            }
        }
        print("null, ")
    }

    private fun updateValue(key: Int, value: Int) {
        cacheHashMap[key] = value
        updateMinimalUsed(key)
    }

    private fun removeKeyInLastUsed(key: Int) {
        lastUsedKey.forEach {
            it.value.remove(key)
        }
    }
    private fun addOrUpdateLastUsed(key: Int, counter: Int){
        removeKeyInLastUsed(key)

        if (lastUsedKey.containsKey(counter)) {
            val list = lastUsedKey[counter]
            list!!.add(key)
            lastUsedKey.replace(counter, list)
        } else {
            lastUsedKey[counter] = mutableListOf(key)
        }
    }

    private fun updateMinimalUsed(key: Int) {
        val counter = countersHashMap[key]!!
        val nextCounter = counter + 1

        if (minUsed == counter && countersHashMap.filter { (k, v) -> v == counter }.size == 1)
            minUsed = nextCounter

        countersHashMap[key] = nextCounter
        addOrUpdateLastUsed(key, nextCounter)
    }

    private fun replaceEntry(key: Int, value: Int) {
        val entries = lastUsedKey[minUsed]
        var removeKey = entries!![0]

        cacheHashMap.remove(removeKey)
        countersHashMap.remove(removeKey)
        cacheHashMap[key] = value
        countersHashMap[key] = 1
        removeKeyInLastUsed(removeKey)
        addOrUpdateLastUsed(key, 1)
        minUsed = 1
    }
}
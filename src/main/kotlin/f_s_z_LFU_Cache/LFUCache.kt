package f_s_z_LFU_Cache

import java.util.Collections

class LFUCache(capacity: Int) {
    private val cacheHashMap: HashMap<Int, Int>
    private val countersHashMap: HashMap<Int, Int>
    private val minUsed = 0 //need to use somehow
    private var capacity = 0
    private var lastUsedKey: MutableList<Int>

    init {
        this.capacity = capacity
        cacheHashMap = hashMapOf()
        countersHashMap = hashMapOf()
        lastUsedKey = mutableListOf()
        print("null, ")
    }

    fun get(key: Int): Int {
        val result = cacheHashMap[key]
        print("${result ?: -1}, ")
        result?.let {
            countersHashMap.computeIfPresent(key) { k, v ->
              //  print("up=($k, $v->${v+1})")
                v + 1
            }
            updateLastUsed(key)
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
                lastUsedKey.add(key)
            } else {
                replaceEntry(key, value)
            }
        }
        print("null, ")
    }

    private fun updateValue(key: Int, value: Int) {
        cacheHashMap.computeIfPresent(key) { k, v -> value }
        countersHashMap.computeIfPresent(key) { k, v ->
           // print("up=($k, $v->${v+1})")
            v + 1
        }
        updateLastUsed(key)
    }

    private fun updateLastUsed(key: Int){
        lastUsedKey.remove(key)
        lastUsedKey.add(key)
    }

    private fun replaceEntry(key: Int, value: Int) {
        val entries = countersHashMap.filter { (k, v) -> v == countersHashMap.minBy { it.value }.value  }
        var removeKeyIndex = Int.MAX_VALUE
        var removeKey = -1

        entries.forEach { entry ->
            val ent = lastUsedKey.indexOf(entry.key)
            if (ent!! < removeKeyIndex) {
                removeKeyIndex = ent
                removeKey = entry.key
            }
        }
//        print("last=$lastUsedKey,  ")
//        print("counter=$countersHashMap , ent=$entries, r=$removeKey ")

        cacheHashMap.remove(removeKey)
        countersHashMap.remove(removeKey)
        cacheHashMap[key] = value
        countersHashMap[key] = 1
        lastUsedKey.remove(removeKey)
        lastUsedKey.add(key)
    }
}
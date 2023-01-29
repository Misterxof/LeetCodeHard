package f_s_z_LFU_Cache

fun main(args: Array<String>) {
//    println("null, null, null, 1, null, -1, 3, null, -1, 3, 4")
//    val lfu = LFUCache(2)
//    lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
//    lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
//    lfu.get(1);      // return 1
//    // cache=[1,2], cnt(2)=1, cnt(1)=2
//    lfu.put(3, 3);   // 2 is the LFU key because cnt(2)=1 is the smallest, invalidate 2.
//    // cache=[3,1], cnt(3)=1, cnt(1)=2
//    lfu.get(2);      // return -1 (not found)
//    lfu.get(3);      // return 3
//    // cache=[3,1], cnt(3)=2, cnt(1)=2
//    lfu.put(4, 4);   // Both 1 and 3 have the same cnt, but 1 is LRU, invalidate 1.
//    // cache=[4,3], cnt(4)=1, cnt(3)=2
//    lfu.get(1);      // return -1 (not found)
//    lfu.get(3);      // return 3
//    // cache=[3,4], cnt(4)=1, cnt(3)=3
//    lfu.get(4);

//    println("1:\nnull, null, null, 2, 1, 2, null, null, -1, 2, 1, 4")
//    var lfu = LFUCache(3)
//    lfu.put(2, 2)
//    lfu.put(1, 1)
//    lfu.get(2)
//    lfu.get(1)
//    lfu.get(2)
//    lfu.put(3, 3)
//    lfu.put(4, 4)
//    lfu.get(3)
//    lfu.get(2)
//    lfu.get(1)
//    lfu.get(4)
//    println()
//
////    2 3
////    4 4
//
//    println("2:\nnull, null, null, null, null, 2")
//    lfu = LFUCache(2)
//    lfu.put(3, 1)
//    lfu.put(2, 1)
//    lfu.put(2, 2)
//    lfu.put(4, 4)
//    lfu.get(2)
//    println()
//
//    println("3:\nnull, -1, null, -1")
//    lfu = LFUCache(0)
//    lfu.get(0)
//    lfu.put(0, 0)
//    lfu.get(0)
//    println()
//
//    println("11:\nnull, null, null, 2, null, null, 2")
//    lfu = LFUCache(2)
//    lfu.put(2, 1)
//    lfu.put(2, 2)
//    lfu.get(2)
//    lfu.put(1, 1)
//    lfu.put(4, 1)
//    lfu.get(2)
//    println()
//
//    println("14:\nnull, -1, null, -1, null, null, 2, 6")
//    lfu = LFUCache(2)
//    lfu.get(2)
//    lfu.put(2, 6)
//    lfu.get(1)
//    lfu.put(1, 5)
//    lfu.put(1, 2)
//    lfu.get(1)
//    lfu.get(2)
//    println()
//
//    println("16:\nnull, null, null, 2, 1, null, 1, -1, 3")
//    lfu = LFUCache(2)
//    lfu.apply {
//        put(2, 1)
//        put(3, 2)
//        get(3)
//        get(2)
//        put(4, 3)
//        get(2)
//        get(3)
//        get(4)
//    }
//    println()

    println("18:\nnull, null, null, null, null, 4, 3, 2, -1, null, -1, 2, 3, -1, 5")
    var lfu = LFUCache(3)
    lfu.apply {
        put(1, 1)
        put(2, 2)
        put(3, 3)
        put(4, 4)
        get(4)
        get(3)
        get(2)
        get(1)
        put(5, 5)
        get(1)
        get(2)
        get(3)
        get(4)
        get(5)
    }
    println()

//    5 1
//    2 2
//    3 2
}
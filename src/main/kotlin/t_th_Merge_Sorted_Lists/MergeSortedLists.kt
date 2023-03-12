package t_th_Merge_Sorted_Lists

import java.util.Collections
import java.util.TreeMap

class MergeSortedLists {
    fun mergeKLists(lists: Array<ListNode?>): ListNode? {
        val allElements = TreeMap<Int, Int>(Collections.reverseOrder())
        var root: ListNode? = null

        for (i in lists.indices) {
            var current = lists[i]

            while (current != null) {
                if (allElements.computeIfPresent(current.`val`) { _, v -> v + 1 } == null)
                    allElements[current.`val`] = 1

                current = current.next
            }
        }

        allElements.forEach { item ->
            var i = item.value

            repeat(i) {
                val node = ListNode(item.key)
                node?.next = root
                root = node
            }
        }

        return root
    }
}

class ListNode(var `val`: Int) {
    var next: ListNode? = null

    override fun toString(): String {
        return "ListNode(`val`=$`val`, next=$next)"
    }
}

fun printlnLinkedListNodes(root: ListNode?) {
    var current: ListNode? = root

    while (current != null) {
        print("${current.`val`} -> ")
        current = current.next
    }
}

fun initializeListNode(intArray: IntArray): ListNode? {
    var root: ListNode? = null
    var i = intArray.lastIndex

    while (i != -1) {
        val node = ListNode(intArray[i])
        node?.next = root
        root = node
        i--
    }

    return root
}

fun main() {
    var list1 = initializeListNode(intArrayOf(1, 4, 5))
    var list2 = initializeListNode(intArrayOf(1, 3, 4))
    var list3 = initializeListNode(intArrayOf(2, 6))
    var conv = MergeSortedLists().mergeKLists(arrayOf(list1, list2, list3))
    println(conv)
    println()
    conv = MergeSortedLists().mergeKLists(arrayOf())
    println(conv)
    println()
    conv = MergeSortedLists().mergeKLists(arrayOf(initializeListNode(intArrayOf())))
    println(conv)
    println()
}
package fi_z_t_IPO

import java.util.*
import kotlin.collections.ArrayDeque

class IPO {
    fun findMaximizedCapital2(k: Int, w: Int, profits: IntArray, capital: IntArray): Int {
        var result = w
        var curK = 0
        var curW = w
        var curMax = 0
        var curIndex = 0

        while (curK < k && curK <= profits.size) {
            var changed = false
           // println("cK=$curK cw=$curW")

            for (i in capital.indices) {
                if (capital[i] != -1 && capital[i] <= curW) {
                //    println("p=${profits[i]} c=${capital[i]}")
                    if (profits[i] > curMax){
                        curMax = profits[i]
                        curIndex = i
                        changed = true
                    }
//                curMax = maxOf(curMax, profits[i])
                }
            }

            if (changed) {
                result += curMax
                //curW -= capital[curIndex]
                curW += curMax
                profits[curIndex] = -1
                capital[curIndex] = -1
                curMax = 0
            } else break

            curK++
        }
        return result
    }

    fun findMaximizedCapital(k: Int, w: Int, profits: IntArray, capital: IntArray): Int {
        val indices = Array(profits.size) { it }.apply { sortWith(compareBy { capital[it] }) }
        var curW = w
        var i = 0

        with(PriorityQueue<Int>(profits.size, compareBy({ -profits[it] }))) {
            repeat(k) {
                while (i <= indices.lastIndex && curW >= capital[indices[i]])
                    add(indices[i++])
                if (isNotEmpty()) curW += profits[poll()]
            }
        }

        return curW
    }
}

fun main() {
    println(IPO().findMaximizedCapital(2,0, intArrayOf(1,2,3), intArrayOf(0,1,1)))
//    println(IPO().findMaximizedCapital(3,0, intArrayOf(1,2,3), intArrayOf(0,1,2)))
//    println(IPO().findMaximizedCapital(2,0, intArrayOf(1,2,3), intArrayOf(0,9,10)))
//    println(IPO().findMaximizedCapital(1,0, intArrayOf(1,2,3), intArrayOf(0,1,2)))
}


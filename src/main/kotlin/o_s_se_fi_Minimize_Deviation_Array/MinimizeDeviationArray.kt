package o_s_se_fi_Minimize_Deviation_Array

import java.util.*

class MinimizeDeviationArray {
    fun minimumDeviation(nums: IntArray): Int {
        var minResult = Integer.MAX_VALUE
        val treeSet = TreeSet<Int>()

        for (i in nums.indices) {
            if (nums[i] %2 == 0) treeSet.add(nums[i])
            else treeSet.add(nums[i] * 2)
        }

        while (true){
            val min = treeSet.first()
            val max = treeSet.last()

            minResult = minOf(minResult, max - min)

            if (max %2 == 0) {
                treeSet.remove(max)
                treeSet.add(max / 2)
            } else break
        }

        return minResult
    }
}
package f_t_Trapping_Rain_Water

class TrappingRainWater {
    fun trap(height: IntArray): Int {
        return trap(height, -1, 0)
    }

    fun trap(height: IntArray, first: Int, result: Int): Int {
        var max = getNextHigher(height)
        var current = -1
        var res = 0

        loop@ for (i in height.indices) {
            if (height[i] == max.first) {
                res += trap(height.sliceArray(max.second + 1..height.lastIndex), max.first, 0)
                break
            }

            if (first == -1) {
                if (current == -1) {
                    current = height[i]
                    continue@loop
                }

                if (height[i] < current) res += (current - height[i])
                else current = height[i]
            } else {
                res += (max.first - height[i])
            }
        }

        return result + res
    }

    fun getNextHigher(height: IntArray): Pair<Int, Int> {
        var max = -1
        var index = -1

        for (i in height.indices) {
            if (height[i] > max) {
                max = height[i]
                index = i
            }
        }

        return max to index
    }
}

fun main() {
    println(TrappingRainWater().trap(intArrayOf(0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1)))
    println("-------------------------")
    println(TrappingRainWater().trap(intArrayOf(4,2,0,3,2,5)))
    println("-------------------------")
    println(TrappingRainWater().trap(intArrayOf(4,2,3)))
    println("-------------------------")
    println(TrappingRainWater().trap(intArrayOf(5,4,1,2)))
}
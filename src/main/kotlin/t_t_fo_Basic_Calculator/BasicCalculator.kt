package t_t_fo_Basic_Calculator

import java.util.Queue
import java.util.Stack

class BasicCalculator {
    fun calculate2(s: String): Int {
        val operator  = Stack<Char>()
        val operand = Stack<Int>()
        val postfix = StringBuilder()
        var counter = 0
        var flag = false

        s.forEach {
            when {
                it.isDigit() -> {
                    if (operator.peek() == '-' && operator.size == 1 && postfix.isEmpty()) {
                        postfix.append('.')
                        operator.pop()
                    }
                    if (flag) counter++
                    postfix.append(it)
                }
                it == '+' || it == '-' -> {
                    if (operator.isEmpty() || operator.peek() == '(') operator.push(it)
                    else {
                        postfix.append(operator.pop())
                        operator.push(it)
                    }
                }
                it == '(' -> {
                    operator.push(it)
                    flag = true
                }
                it == ')' -> {
//                    println(counter)
                    if (counter > 1) {
                        while (operator.peek() != '(') {
                            postfix.append(operator.pop())
                        }
                    } else if (counter == 1) {
//                        println("----------------")
//                        println(postfix)
                        println(operator)
                        if (operator.peek() == '-') {
                            val r = postfix.last().digitToInt()
                            postfix.setLength(postfix.length - 1)
                            postfix.append('.')
                            postfix.append(r)
                            operator.pop()
                        }
                        counter = 0
                        flag = false
                    }
                    operator.pop()
                }
            }
        }

        if(operator.isNotEmpty()) postfix.append(operator.pop())

//        println(postfix)
        var i = 0

        while (i != postfix.length) {
            if (postfix[i].isDigit()) operand.push(postfix[i].digitToInt())
            else if (postfix[i] == '.') {
                val r = postfix[i + 1].digitToInt().unaryMinus()
                operand.push(r)
//                println(r)
                i += 1
            }
            else {
                val b = operand.pop()
                val a = operand.pop()
//                println("a $a b $b")
//                println(a - b)

                if (postfix[i] == '+') operand.push(a + b)
                else operand.push(a - b)
            }
            i++
        }

//        println(operand)
        if (operand.size > 1) {
            var res = 0
            operand.forEach {
                res = res * 10 + it
            }
            operand.clear()
            operand.push(res)
        }
//        println(operand)

        return operand.pop()
    }

    fun calculate(s: String): Int {
        var result = 0
        var sign = 1
        var number = 0
        val stack = Stack<Int>()

        for (ch in s) {
            when {
                ch.isDigit() -> number = number * 10 + (ch - '0')
                ch == '+' -> {
                    result += sign * number
                    number = 0
                    sign = 1
                }
                ch == '-' -> {
                    result += sign * number
                    number = 0
                    sign = -1
                }
                ch == '(' -> {
                    stack.push(result)
                    stack.push(sign)
                    result = 0
                    sign = 1
                }
                ch == ')' -> {
                    result += sign * number
                    number = 0
                    result *= stack.pop()
                    result += stack.pop()
                }
            }
        }
        return result + (sign * number)
    }
}

fun main() {
    println(BasicCalculator().calculate(" 2-1 + 2 "))
    println(BasicCalculator().calculate(" 1+2 - 3 + 4"))
    println(BasicCalculator().calculate(" 1+(2 - 3) + 4"))
    println(BasicCalculator().calculate("(1+(4+5+2)-3)+(6+8)"))
    println(BasicCalculator().calculate("2147483647"))
    println(BasicCalculator().calculate("1-(     -2)"))
    println(BasicCalculator().calculate("(1)"))
}
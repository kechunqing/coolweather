package com.kcq.coolweather.kotlin

import android.content.Context
import android.content.SharedPreferences


/**
 * Created by kcq on 2020/5/15
 */
fun main(){
    val list= listOf("aa","bb","cc")
    val b = 10
    var c = 0
    var d=true
    var a = b.minus(20)
    c=a.unaryMinus()
    val anyResult=list.any{ it.length > 3}
    val allResult=list.all { it.length < 3 }
    println("a=$a b=$b c=$c")
    println("anyResult is $anyResult,allResult is $allResult")
//    printParam(str = "konka yes", num = 1980)
    println("operator result:${a}")
    println("result1:${num1AndNum2(100, 100, ::function1)}")
    println("result2:${num1AndNum2(100,100,::function2)}")
    println("result2:${num1AndNum2(100,100){n1,n2->n1*n2}}")
    testFunc(1){
        println("this:$this")
        println("it:$it")
    }
}

fun printParam(num: Int, str: String = "Hello World",context: Context) {
    println("num is $num , str is $str")
    context.getSharedPreferences("data",Context.MODE_PRIVATE).open {
    }
}

fun SharedPreferences.open(block: SharedPreferences.Editor.() -> Unit) {    //
    val editor = edit()
    editor.block()
}

fun testFunc(num: Int, ld: Int.(Int) -> Unit) {
    num.ld(100)
}

inline fun num1AndNum2(num1: Int, num2: Int, func: (Int, Int) -> Int):Int {
    return func(num1,num2)
}

fun function1(num1: Int, num2: Int): Int {
    return num1 + num2
}

fun function2(num1: Int, num2: Int): Int {
    return num1 - num2
}

class Money(val value: Int){
    operator fun plus(other: Int): Int {
        return value+other
    }}



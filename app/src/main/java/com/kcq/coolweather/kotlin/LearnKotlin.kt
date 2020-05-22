package com.kcq.coolweather.kotlin


/**
 * Created by kcq on 2020/5/15
 */
fun main(){
    val list= listOf("aa","bb","cc")
    val b = 10
    var a = b.plus(20)
    val money=Money(100)
    a++
    val anyResult=list.any{ it.length > 3}
    val allResult=list.all { it.length < 3 }
    println("a=$a b=$b")
    println("anyResult is $anyResult,allResult is $allResult")
    printParam(str = "konka yes",num = 1980)
    print("operator result:${money+200}")
}

fun printParam(num: Int, str: String = "Hello World") {
    println("num is $num , str is $str")
}

class Money(val value: Int){
    operator fun plus(other: Int): Int {
        return value+other
    }}



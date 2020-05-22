package com.kcq.coolweather.kotlin

/**
 * Created by kcq on 2020/5/21
 */
class Util {
    private var name: String? = null


    operator fun plus(newValue: Int): Int {
        return newValue+1
    }

    companion object {
        fun doAction1() {}

        @JvmStatic
        fun doAction2() {
        }
    }
}
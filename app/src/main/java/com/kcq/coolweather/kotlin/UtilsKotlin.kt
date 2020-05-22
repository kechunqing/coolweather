package com.kcq.coolweather.kotlin

import java.io.FileOutputStream
import java.io.IOException

/**
 * Created by kcq on 2020/5/22
 */
object UtilsKotlin {
    fun writeToFile(content: String, filePath: String) {
        try {
            val fs = FileOutputStream(filePath)
            fs.write(content.toByteArray())
            fs.close()
        } catch (e:IOException) {
            e.printStackTrace()
        }

    }
}
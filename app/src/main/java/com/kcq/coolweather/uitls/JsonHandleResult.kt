package com.kcq.coolweather.uitls

import com.kcq.coolweather.jbtab.gson.SmartTabProductGroup
import com.kcq.coolweather.jbtab.gson.SmartTabTitleGroup

/**
 * Created by kcq on 2020/5/19
 */
interface JsonHandleResult {
    fun smartData(smartTabTitleGroup: SmartTabTitleGroup?,smartTabProductGroup: SmartTabProductGroup?)
}
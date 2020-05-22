package com.kcq.coolweather.jbtab.gson

import com.google.gson.annotations.SerializedName

/**
 * Created by kcq on 2020/5/18
 */
data class IotItems(
    val title: String,
    val imageUrl: String,
    val action: String,
    val data: String,
    @SerializedName("extras") val extras:Any?
)

data class ProductInfo(val price:String?,val productId:String?)

data class SmartTabTitleEntity(
    val title: String,
    val imageUrl: String,
    val action: String,
    val data: String,
     val extras:String
)

data class SmartTabProductEntity(
    val title: String,
    val imageUrl: String,
    val action: String,
    val data: String,
    val extras:ProductInfo
)

data class SmartTabTitleGroup(
    val items:List<SmartTabTitleEntity>,
    @SerializedName("group_title") val title: String,
    val type: String
)

data class SmartTabProductGroup(
    val items:List<SmartTabProductEntity>,
    @SerializedName("group_title") val title: String,
    val type: String
)
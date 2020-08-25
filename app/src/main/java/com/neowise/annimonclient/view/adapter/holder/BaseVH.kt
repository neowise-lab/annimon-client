package com.neowise.annimonclient.view.adapter.holder


const val ITEM = 1
const val NAVIGATION = 2

interface BaseVH<T> {
    fun bind(item: T)
}
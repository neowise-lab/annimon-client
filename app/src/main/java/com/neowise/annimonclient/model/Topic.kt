package com.neowise.annimonclient.model

data class Topic(val id: String, val name: String) {
    override fun toString(): String = name
}
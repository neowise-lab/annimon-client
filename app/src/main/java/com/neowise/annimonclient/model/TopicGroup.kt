package com.neowise.annimonclient.model

data class TopicGroup(val id: String, val name: String, val topics: List<Topic>) {
    override fun toString(): String = name
}
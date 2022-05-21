package com.tolulonge.footballfixtures.core.mapper

interface Mapper<in I, out O> {
    fun map(input: I): O
}
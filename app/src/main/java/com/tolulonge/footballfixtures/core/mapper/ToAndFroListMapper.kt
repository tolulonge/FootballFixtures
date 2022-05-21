package com.tolulonge.footballfixtures.core.mapper

interface ToAndFroListMapper<T, E> {

    fun from(e: List<E>): List<T>

    fun to(t: List<T>): List<E>
}

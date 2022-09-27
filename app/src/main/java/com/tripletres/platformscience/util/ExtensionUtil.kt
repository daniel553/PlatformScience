package com.tripletres.platformscience.util

fun Int.isEven(): Boolean {
    return this % 2 == 0
}

fun Int.commonFactors(): List<Int> {
    var max = this
    val list = mutableListOf<Int>()
    while (max-- > 1) {
        if (this % max == 0) {
            list.add(max)
        }
    }

    return list.toList()
}
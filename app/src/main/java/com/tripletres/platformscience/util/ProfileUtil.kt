package com.tripletres.platformscience.util

import com.tripletres.platformscience.R

/**
 * Profile utility
 */
object ProfileUtil {
    private val pics = listOf(
        R.drawable.ic_man,
        R.drawable.ic_man_1,
        R.drawable.ic_man_2,
        R.drawable.ic_woman_2,
        R.drawable.ic_woman_3,
        R.drawable.ic_man_3,
        R.drawable.ic_man_4,
        R.drawable.ic_man_5,
        R.drawable.ic_woman_1,
        R.drawable.ic_woman_4,
        R.drawable.ic_woman_5,
        R.drawable.ic_man,
        R.drawable.ic_man_1,
        R.drawable.ic_man_2,
        R.drawable.ic_man_3,
    )

    /**
     * Just get a random pic from drawable list
     */
    fun getRandomPic(): Int = pics.random()

    /**
     * Single pic by index
     */
    fun getByIndex(index: Int): Int {
        return if (index < pics.size) {
            pics[index]
        } else pics[0]
    }
}
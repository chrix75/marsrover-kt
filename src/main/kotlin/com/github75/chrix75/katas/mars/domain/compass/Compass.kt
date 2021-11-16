package com.github75.chrix75.katas.mars.domain.compass

/**
 * Represent the compass of a rover.
 */
interface Compass {
    val direction: Direction

    fun turnLeft()
    fun turnRight()
}
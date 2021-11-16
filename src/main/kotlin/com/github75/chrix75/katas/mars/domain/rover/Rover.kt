package com.github75.chrix75.katas.mars.domain.rover

import com.github75.chrix75.katas.mars.domain.compass.Compass
import com.github75.chrix75.katas.mars.domain.compass.Direction

/**
 * Represent a rover on a planet.
 */
class Rover(private val compass: Compass) {
    val direction: Direction
        get() = compass.direction

    fun turn(to: Turn) {
        when (to) {
            Turn.LEFT -> compass.turnLeft()
            Turn.RIGHT -> compass.turnRight()
        }
    }
}

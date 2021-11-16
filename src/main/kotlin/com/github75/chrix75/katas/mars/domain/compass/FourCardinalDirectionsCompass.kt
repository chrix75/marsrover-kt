package com.github75.chrix75.katas.mars.domain.compass

/**
 * Simple Compass implementation with only the main cardinal direction (N, S, E, W)
 */
class FourCardinalDirectionsCompass(origin: Direction) : Compass {
    private val directions = arrayOf(
        Direction.NORTH,
        Direction.WEST,
        Direction.SOUTH,
        Direction.EAST
    )

    private var currentIndex: Int

    override val direction: Direction
        get() = directions[currentIndex]

    init {
        currentIndex = directions.indexOf(origin)
    }

    override fun turnLeft() {
        currentIndex = when {
            currentIndex < directions.size - 1 -> currentIndex + 1
            else -> 0
        }
    }

    override fun turnRight() {
        currentIndex = when {
            currentIndex > 0 -> currentIndex - 1
            else -> directions.size - 1
        }
    }
}
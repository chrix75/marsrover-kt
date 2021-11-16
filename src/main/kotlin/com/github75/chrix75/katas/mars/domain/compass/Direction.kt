package com.github75.chrix75.katas.mars.domain.compass

/**
 * Define the link between rover direction and movement vector.
 */
enum class Direction(val dx: Int, val dy: Int) {
    NORTH(0, -1),
    WEST(-1, 0),
    SOUTH(0, 1),
    EAST(1, 0)
}

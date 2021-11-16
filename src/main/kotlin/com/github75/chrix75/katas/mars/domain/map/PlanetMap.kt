package com.github75.chrix75.katas.mars.domain.map

import com.github75.chrix75.katas.mars.domain.compass.Direction

/**
 * A map representation of a planet.
 */
class PlanetMap(private val xSize: Int, private val ySize: Int) {
    private val obstacles = mutableListOf<Coords>()

    fun forward(configState: (state: MapState) -> Unit): Coords {
        return move(configState) { state ->
            manageBorder(Coords(state.x + state.direction.dx, state.y + state.direction.dy))
        }
    }

    fun backward(configState: (state: MapState) -> Unit): Coords {
        return move(configState) { state ->
            manageBorder(Coords(state.x - state.direction.dx, state.y - state.direction.dy))
        }
    }

    private fun move(configState: (state: MapState) -> Unit, movement: (state: MapState) -> Coords): Coords {
        val state = MapState()
        configState(state)

        val updatedCoords = movement(state)

        if (collide(updatedCoords)) {
            return Coords(state.x, state.y)
        }

        return updatedCoords
    }

    private fun collide(coords: Coords): Boolean = obstacles.contains(coords)

    private fun manageBorder(coords: Coords): Coords {
        if (hasNotReachedBorder(coords)) {
            return coords
        }

        var acceptedX: Int = coords.x
        var acceptedY: Int = coords.y

        if (acceptedX < 0) {
            acceptedX = xSize - 1
        }

        if (acceptedX >= xSize) {
            acceptedX = 0
        }

        if (acceptedY < 0) {
            acceptedY = ySize - 1
        }

        if (acceptedY >= ySize) {
            acceptedY = 0
        }

        return Coords(acceptedX, acceptedY)
    }

    private fun hasNotReachedBorder(coords: Coords): Boolean {
        return (coords.x in 0 until xSize) && (coords.y in 0 until ySize)
    }

    fun addObstacle(x: Int, y: Int) {
        assert(x in 0 until xSize)
        assert(y in 0 until ySize)
        obstacles.add(Coords(x, y))
    }
}

class MapState {
    private var _x: Int = 0
    private var _y: Int = 0
    private var _direction: Direction = Direction.NORTH

    val x: Int
        get() = _x

    val y: Int
        get() = _y

    val direction: Direction
        get() = _direction

    fun from(x: Int, y: Int) {
        this._x = x
        this._y = y
    }

    fun directedTo(direction: Direction) {
        this._direction = direction
    }

}

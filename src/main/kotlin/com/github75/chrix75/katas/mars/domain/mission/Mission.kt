package com.github75.chrix75.katas.mars.domain.mission

import com.github75.chrix75.katas.mars.domain.map.Coords
import com.github75.chrix75.katas.mars.domain.map.PlanetMap
import com.github75.chrix75.katas.mars.domain.rover.Rover
import com.github75.chrix75.katas.mars.domain.rover.Turn

/**
 * Representation of a mission that is a rover on a planet.
 */
class Mission(
    private val rover: Rover,
    private val planet: PlanetMap,
    private var coords: Coords
) {
    val position: Coords
        get() = coords

    fun command(command: String): CommandFailure? {
        for (i in command.indices) {
            val successful = executeAction(command[i])
            if (!successful) {
                return CommandFailure(i + 1)
            }
        }
        return null
    }

    private fun executeAction(action: Char): Boolean {
        val updatedCoords = when (action) {
            'F' -> planet.forward {
                it.from(coords.x, coords.y)
                it.directedTo(rover.direction)
            }

            'B' -> planet.backward {
                it.from(coords.x, coords.y)
                it.directedTo(rover.direction)
            }

            'L' -> {
                rover.turn(Turn.LEFT)
                coords
            }

            'R' -> {
                rover.turn(Turn.RIGHT)
                coords
            }

            else -> throw IllegalArgumentException("Unknown action: $action")
        }

        if (updatedCoords == coords && isMovement(action)) {
            return false
        }

        coords = updatedCoords
        return true
    }

    private fun isMovement(action: Char): Boolean = (action == 'F' || action == 'B')
}
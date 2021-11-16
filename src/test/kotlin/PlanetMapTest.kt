import com.github75.chrix75.katas.mars.domain.compass.Direction
import com.github75.chrix75.katas.mars.domain.map.Coords
import com.github75.chrix75.katas.mars.domain.map.PlanetMap
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class PlanetMapTest {
    @ParameterizedTest
    @MethodSource("forwardCoords")
    internal fun forward(x: Int, y: Int, direction: Direction, expectedCoords: Coords) {
        val map = PlanetMap(5, 5)
        val coords = map.forward {
            it.from(x, y)
            it.directedTo(direction)
        }

        Assertions.assertEquals(expectedCoords, coords)
    }

    @ParameterizedTest
    @MethodSource("backwardCoords")
    internal fun backward(x: Int, y: Int, direction: Direction, expectedCoords: Coords) {
        val map = PlanetMap(5, 5)
        val coords = map.backward {
            it.from(x, y)
            it.directedTo(direction)
        }

        Assertions.assertEquals(expectedCoords, coords)
    }

    @ParameterizedTest
    @MethodSource("pacmanCoordsForForward")
    internal fun `manage map borders for forward`(x: Int, y: Int, direction: Direction, expectedCoords: Coords) {
        val map = PlanetMap(5, 5)
        val coords = map.forward {
            it.from(x, y)
            it.directedTo(direction)
        }

        Assertions.assertEquals(expectedCoords, coords)
    }

    @ParameterizedTest
    @MethodSource("pacmanCoordsForBackward")
    internal fun `manage map borders for backward`(x: Int, y: Int, direction: Direction, expectedCoords: Coords) {
        val map = PlanetMap(5, 5)
        val coords = map.backward {
            it.from(x, y)
            it.directedTo(direction)
        }

        Assertions.assertEquals(expectedCoords, coords)
    }

    @Test
    internal fun `stop rover when collision is possible`() {
        val map = PlanetMap(5, 5)
        map.addObstacle(3, 2)
        val coords = map.forward {
            it.from(2, 2)
            it.directedTo(Direction.EAST)
        }

        Assertions.assertEquals(Coords(2, 2), coords)
    }

    companion object {
        @JvmStatic
        fun forwardCoords(): List<Arguments> {
            return listOf(
                arguments(2, 2, Direction.NORTH, Coords(2, 1)),
                arguments(2, 2, Direction.WEST, Coords(1, 2)),
                arguments(2, 2, Direction.SOUTH, Coords(2, 3)),
                arguments(2, 2, Direction.EAST, Coords(3, 2))
            )
        }

        @JvmStatic
        fun backwardCoords(): List<Arguments> {
            return listOf(
                arguments(2, 2, Direction.NORTH, Coords(2, 3)),
                arguments(2, 2, Direction.WEST, Coords(3, 2)),
                arguments(2, 2, Direction.SOUTH, Coords(2, 1)),
                arguments(2, 2, Direction.EAST, Coords(1, 2))
            )
        }

        @JvmStatic
        fun pacmanCoordsForForward(): List<Arguments> {
            return listOf(
                arguments(2, 0, Direction.NORTH, Coords(2, 4)),
                arguments(0, 2, Direction.WEST, Coords(4, 2)),
                arguments(2, 4, Direction.SOUTH, Coords(2, 0)),
                arguments(4, 2, Direction.EAST, Coords(0, 2))
            )
        }

        @JvmStatic
        fun pacmanCoordsForBackward(): List<Arguments> {
            return listOf(
                arguments(2, 4, Direction.NORTH, Coords(2, 0)),
                arguments(4, 2, Direction.WEST, Coords(0, 2)),
                arguments(2, 0, Direction.SOUTH, Coords(2, 4)),
                arguments(0, 2, Direction.EAST, Coords(4, 2))
            )
        }
    }
}
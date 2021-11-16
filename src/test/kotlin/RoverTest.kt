import com.github75.chrix75.katas.mars.domain.compass.FourCardinalDirectionsCompass
import com.github75.chrix75.katas.mars.domain.compass.Direction
import com.github75.chrix75.katas.mars.domain.rover.Rover
import com.github75.chrix75.katas.mars.domain.rover.Turn
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

internal class RoverTest {
    @ParameterizedTest
    @MethodSource("turnLeftRover")
    internal fun `turn rover on left`(origin: Direction, expected: Direction) {
        val rover = Rover(FourCardinalDirectionsCompass(origin))
        rover.turn(Turn.LEFT)
        Assertions.assertEquals(expected, rover.direction)
    }

    @ParameterizedTest
    @MethodSource("turnRightRover")
    internal fun `turn rover on right`(origin: Direction, expected: Direction) {
        val rover = Rover(FourCardinalDirectionsCompass(origin))
        rover.turn(Turn.RIGHT)
        Assertions.assertEquals(expected, rover.direction)
    }

    companion object {
        @JvmStatic
        fun turnLeftRover(): List<Arguments> {
            return listOf(
                arguments(Direction.NORTH, Direction.WEST),
                arguments(Direction.WEST, Direction.SOUTH),
                arguments(Direction.SOUTH, Direction.EAST),
                arguments(Direction.EAST, Direction.NORTH),
            )
        }

        @JvmStatic
        fun turnRightRover(): List<Arguments> {
            return listOf(
                arguments(Direction.NORTH, Direction.EAST),
                arguments(Direction.EAST, Direction.SOUTH),
                arguments(Direction.SOUTH, Direction.WEST),
                arguments(Direction.WEST, Direction.NORTH),
            )
        }
    }
}

import com.github75.chrix75.katas.mars.domain.compass.Direction
import com.github75.chrix75.katas.mars.domain.compass.FourCardinalDirectionsCompass
import com.github75.chrix75.katas.mars.domain.map.Coords
import com.github75.chrix75.katas.mars.domain.map.PlanetMap
import com.github75.chrix75.katas.mars.domain.mission.Mission
import com.github75.chrix75.katas.mars.domain.rover.Rover
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class MissionTest {
    @Test
    internal fun `land rover and send commands`() {
        val mission = Mission(
            Rover(FourCardinalDirectionsCompass(Direction.NORTH)),
            PlanetMap(5, 5),
            Coords(2, 2)
        )

        val failure = mission.command("FRFLB")
        assertNull(failure)
        assertEquals(Coords(3, 2), mission.position)
    }

    @Test
    internal fun `the rover is blocked by an obstacle`() {
        val map = PlanetMap(5, 5)
        val mission = Mission(
            Rover(FourCardinalDirectionsCompass(Direction.NORTH)),
            map,
            Coords(2, 2)
        )

        map.addObstacle(3, 2)
        val failure = mission.command("FRFLB")
        assertNotNull(failure)
        assertEquals(5, failure?.failedCommandNumber)
        assertEquals(Coords( 3, 1), mission.position)

    }
}
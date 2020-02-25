package GameLogic

import java.awt.event.ActionEvent

import GUI.FActionListener

class Level(list: List[Array[Double2 => Monster]])
{

    val time_between_waves = 100

    private var ntick = 0

    var started = false

    val waves: List[Wave] = list.map(a => new Wave(a))

    var next_waves: List[Wave] = waves


    def spawn_wave(game_logic: GameLogic): Unit =
    {
        next_waves match
        {
            case wave :: next =>
                next_waves = next
                wave.spawn(game_logic)
            case Nil => ()
        }
    }


    def start(game_logic: GameLogic): Unit =
    {
        started = true
    }

    def end(): Unit =
    {
        started = false
    }

    def is_finished: Boolean = Nil == next_waves

    def tick_level(game_logic: GameLogic): Unit =
    {
        if (started)
        {
            if (ntick % time_between_waves == 0)
            {
                spawn_wave(game_logic)
            }
            ntick += 1
        }

    }
}

object Levels
{
    val spawn_point = new Double2(0.5, 0.01)

    private val triangle: Double2 => Monster = Triangle
    val a: List[Array[Double2 => Monster]] = List(Array(triangle), Array(triangle, triangle))
    val l = new Level(a)
    val levels: List[Level] =
        List(new Level(List(Array(triangle), Array(triangle))), new Level(List(Array(triangle, triangle, triangle), Array(triangle, triangle))))
}

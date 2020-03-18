package GameLogic

import GameLogic.Monsters.Monster

class Wave(list: List[Map[Double2 => Monster, Int]], val spawn_tile: Int2)
{

    val time_between_waves = 100

    private var ntick = 0

    var started = false

    val subwaves: List[SubWave] = list.map(a => new SubWave(a))

    var next_subwaves: List[SubWave] = subwaves


    def spawn_wave(game_logic: GameLogic): Unit =
    {
        next_subwaves match
        {
            case subwave :: next =>
                next_subwaves = next
                subwave.spawn(game_logic, spawn_tile)
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

    def is_finished: Boolean = Nil == next_subwaves

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


package GameLogic

import GameLogic.Monsters.Monster

class Wave(sw: List[Map[Double2 => Monster, Int]], val spawn_tile: Int2)
{

    val time_between_subwaves = 200

    private var ntick = 0

    var started = false

    val subwaves: List[SubWave] = sw.map(a => new SubWave(a))

    var next_subwaves: List[SubWave] = List()

    def spawn_next_subwave(game_logic: GameLogic): Unit =
    {
        next_subwaves match
        {
            case subwave :: next =>
                next_subwaves = next
                subwave.spawn(game_logic, spawn_tile)
            case Nil => ()
        }
    }

    def start: Unit =
    {
        next_subwaves = subwaves //cela sert si on joue plusieurs fois le meme niveau
        ntick = 0 //cela sert si on joue plusieurs fois le meme niveau
        started = true
    }

    def end(): Unit =
    {
        started = false
    }

    def is_finished: Boolean = Nil == next_subwaves

    def tick_wave(game_logic: GameLogic): Unit =
    {
        if (started)
        {
            if (ntick % time_between_subwaves == 0)
            {
                spawn_next_subwave(game_logic)
                ntick = 1
            }
            else
            {
                ntick += 1
            }
        }

    }
}


package GameLogic

class Wave(list: List[Array[Double2 => Monster]])
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
            case wave :: next =>
                next_subwaves = next
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

object Levels
{
    val spawn_tile: Int2 = Int2(0, 9)


    private val cyan_monster: Double2 => Monster = new CyanMonster(_)
    private val blue_monster: Double2 => Monster = new BlueMonster(_)
    val a: List[Array[Double2 => Monster]] = List(Array(cyan_monster), Array(cyan_monster, cyan_monster))
    val l = new Wave(a)
    val levels: List[Wave] =
        List(
            new Wave(List(
                Array(cyan_monster, cyan_monster, cyan_monster),
                Array(cyan_monster, cyan_monster, cyan_monster, cyan_monster))),
            new Wave(List(
                Array(cyan_monster, cyan_monster, cyan_monster, cyan_monster, cyan_monster),
                Array(cyan_monster, cyan_monster, cyan_monster, cyan_monster, cyan_monster, cyan_monster, cyan_monster),
                Array(cyan_monster, cyan_monster, cyan_monster, cyan_monster, cyan_monster, cyan_monster, cyan_monster, cyan_monster, cyan_monster))),
            new Wave(List(
                Array(cyan_monster, cyan_monster, cyan_monster, cyan_monster, cyan_monster, cyan_monster, cyan_monster, cyan_monster, cyan_monster, blue_monster, cyan_monster, cyan_monster, cyan_monster),
                Array(cyan_monster, blue_monster, blue_monster, blue_monster, cyan_monster, cyan_monster, cyan_monster),
                Array(blue_monster, blue_monster, blue_monster, blue_monster, blue_monster, blue_monster, blue_monster, blue_monster))),
            new Wave(List(
                Array(blue_monster, blue_monster, blue_monster, blue_monster, blue_monster, blue_monster, blue_monster, blue_monster),
                Array(blue_monster, blue_monster, blue_monster, blue_monster, blue_monster, blue_monster, blue_monster, blue_monster),
                Array(blue_monster, blue_monster, blue_monster, blue_monster, blue_monster, blue_monster, blue_monster, blue_monster),
                Array(blue_monster, blue_monster, blue_monster, blue_monster, blue_monster, blue_monster, blue_monster, blue_monster),
                Array(blue_monster, blue_monster, blue_monster, blue_monster, blue_monster, blue_monster, blue_monster, blue_monster, blue_monster, blue_monster, blue_monster, blue_monster))),
            new Wave(List(
                Array(blue_monster, blue_monster, blue_monster, blue_monster, blue_monster, blue_monster, blue_monster, blue_monster, blue_monster, blue_monster),
                Array(blue_monster, blue_monster, blue_monster, blue_monster, blue_monster, blue_monster, blue_monster, blue_monster, blue_monster, blue_monster, blue_monster),
                Array(blue_monster, blue_monster, blue_monster, blue_monster, blue_monster, blue_monster, blue_monster, blue_monster, blue_monster, blue_monster, blue_monster),
                Array(blue_monster, blue_monster, blue_monster, blue_monster, blue_monster, blue_monster, blue_monster, blue_monster, blue_monster, blue_monster, blue_monster))),
            new Wave(List(Array(blue_monster, blue_monster, blue_monster, blue_monster, blue_monster, blue_monster, blue_monster, blue_monster, blue_monster, blue_monster, blue_monster, blue_monster, blue_monster, blue_monster, blue_monster, blue_monster, blue_monster, blue_monster, blue_monster, blue_monster, blue_monster, blue_monster, blue_monster, blue_monster, blue_monster, blue_monster, blue_monster, blue_monster, blue_monster, blue_monster, blue_monster))))


}

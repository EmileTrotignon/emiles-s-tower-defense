import GameLogic.Monsters.{BlueMonster, CyanMonster, Monster}
import GameLogic._

import scala.io.{BufferedSource, Source}

/*
object Levels
{
    val spawn_tile: Int2 = Int2(0, 9)



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

*/

package object GameInstance
{

    private val cyan_monster: Double2 => Monster = new CyanMonster(_)
    private val blue_monster: Double2 => Monster = new BlueMonster(_)

    val map_file: BufferedSource = Source.fromResource("map1")
    assert(map_file.nonEmpty)
    val map: GameMap = GameMap.from_file(map_file)

    val levels: Array[Level] =
        Array(
            Level.from_list("Level 1", GameMap.from_file(Source.fromResource("map1")), 10, 10,
                List(
                    new Wave(List(
                        Map(cyan_monster -> 3),
                        Map(cyan_monster -> 4, blue_monster -> 1)
                    ), Int2(0, 9)),
                    new Wave(List(
                        Map(cyan_monster -> 3, blue_monster -> 2),
                        Map(cyan_monster -> 4, blue_monster -> 4),
                    ), Int2(0, 9))
                )
            ),
            Level.from_list("Level 2", GameMap.from_file(Source.fromResource("map2")), 10, 10,
                List(
                    new Wave(List(
                        Map(cyan_monster -> 2),
                        Map(cyan_monster -> 3, blue_monster -> 1)
                    ), Int2(0, 4)),
                    new Wave(List(
                        Map(cyan_monster -> 3, blue_monster -> 2),
                        Map(cyan_monster -> 4, blue_monster -> 4),
                    ), Int2(0, 4))
                )
            )
        )
}

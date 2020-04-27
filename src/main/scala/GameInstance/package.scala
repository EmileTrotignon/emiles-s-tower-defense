import GameLogic.Monsters._
import GameLogic._

import scala.io.{BufferedSource, Source}

package object GameInstance
{

    private val cyan_monster: Double2 => Monster = new CyanMonster(_)
    private val blue_monster: Double2 => Monster = new BlueMonster(_)
    private val shield_monster: Double2 => Monster = new ShieldMonster(_)
    private val healer_monster: Double2 => Monster = new HealerMonster(_)
    private val protector_monster: Double2 => Monster = new ProtectorMonster(_)
    private val little_monster: Double2 => Monster = new LittleMonster(_)

    val map_file: BufferedSource = Source.fromResource("map1")
    assert(map_file.nonEmpty)
    val map: GameMap = GameMap.from_file(map_file)

    val level_loaders: Array[Unit => Level] = //_ => new Level.from_list(...) permet de charger la GameMap a nouveau et donc ne pas subir l'effet des convert_tile effectues lors des precedentes parties sur ce level
        Array(
            (_ => Level.from_list("Level 1", GameMap.from_file(Source.fromResource("map1")), 15, 10,
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
            )),
            (_ => Level.from_list("Level 2", GameMap.from_file(Source.fromResource("map2")), 15, 10,
                List(
                    new Wave(List(
                        Map(little_monster -> 1, cyan_monster -> 3, blue_monster -> 2),
                        Map(cyan_monster -> 3, blue_monster -> 2),
                        Map(protector_monster -> 1, cyan_monster -> 4),
                        Map(cyan_monster -> 2, blue_monster -> 1)
                    ), Int2(0, 9)),
                    new Wave(List(
                        Map(cyan_monster -> 3, healer_monster -> 1),
                        Map(cyan_monster -> 4, blue_monster -> 1),
                        Map(cyan_monster -> 3, healer_monster -> 1),
                        Map(cyan_monster -> 2, blue_monster -> 2)
                    ), Int2(0, 9)),
                    new Wave(List(
                        Map(little_monster -> 1, cyan_monster -> 3, blue_monster -> 2),
                        Map(shield_monster -> 1, cyan_monster -> 4),
                        Map(cyan_monster -> 2, shield_monster -> 1, blue_monster -> 1)
                    ), Int2(0, 9))
                )
            )),
            (_ => Level.from_list("Level 3", GameMap.from_file(Source.fromResource("map3")), 16, 13,
                List(
                    new Wave(List(
                        Map(little_monster -> 1, blue_monster -> 1, cyan_monster -> 4),
                        Map(cyan_monster -> 1, blue_monster -> 2),
                        Map(cyan_monster -> 2,blue_monster -> 1),
                        Map(protector_monster -> 1, cyan_monster -> 4)
                    ), Int2(0, 2)),
                    new Wave(List(
                        Map(cyan_monster -> 3, healer_monster -> 1),
                        Map(cyan_monster -> 8, blue_monster -> 2),
                        Map(cyan_monster -> 3, healer_monster -> 1),
                        Map(cyan_monster -> 2, blue_monster -> 2)
                    ), Int2(0, 2)),
                    new Wave(List(
                        Map(little_monster -> 1, cyan_monster -> 3, blue_monster -> 2),
                        Map(shield_monster -> 1, cyan_monster -> 4),
                        Map(cyan_monster -> 2, shield_monster -> 1, blue_monster -> 1)
                    ), Int2(0, 2)),
                    new Wave(List(
                        Map(little_monster -> 2, cyan_monster -> 10, blue_monster -> 1),
                        Map(cyan_monster -> 1),
                        Map(),
                        Map(healer_monster -> 3, shield_monster -> 3, cyan_monster -> 3, blue_monster -> 1)
                    ), Int2(0, 11)),
                    new Wave(List(
                        Map(little_monster -> 1, shield_monster -> 1, protector_monster -> 1, healer_monster -> 1, cyan_monster -> 1),
                        Map(little_monster -> 1, shield_monster -> 1, protector_monster -> 1, healer_monster -> 1, blue_monster -> 2),
                        Map(little_monster -> 1, shield_monster -> 1, protector_monster -> 1, healer_monster -> 1, cyan_monster -> 1)
                    ), Int2(4, 0))
                )
            ))
        )
}

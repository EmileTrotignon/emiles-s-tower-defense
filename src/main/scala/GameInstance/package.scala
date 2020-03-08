import java.io.InputStream

import GameLogic.GameMap

import scala.io.{BufferedSource, Source}


package object GameInstance
{

    val map_file: BufferedSource = Source.fromResource("map1")
    assert(map_file.nonEmpty)
    val map: GameMap = GameMap.from_file(map_file)
}

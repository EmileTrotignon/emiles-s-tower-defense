package GameLogic

import com.sun.org.apache.xpath.internal.operations.Bool

import scala.collection.mutable.ArrayBuffer

class BoardLogic(val map: GameMap)
{
    var monsters: ArrayBuffer[Monster] = new ArrayBuffer[Monster]
    var towers: ArrayBuffer[Tower] = new ArrayBuffer[Tower]
    var bullets: ArrayBuffer[BulletLogic] = new ArrayBuffer[BulletLogic]
}

class Level(val waves: List[Wave])
{
    var next_waves: List[Wave] = waves

    def spawn_wave: Boolean =
    {
        next_waves match
        {
            case wave :: next =>
                next_waves = next
                wave.spawn()
                true
            case Nil => false
        }
    }

    def isFinished: Boolean = (Nil == next_waves)
}

class PlayerLogic(var money: Double, var lives: Double)
{
}

class GameLogic(map: GameMap, starting_money: Double, starting_lives: Double, var next_levels: List[Level])
{
    val board: BoardLogic = new BoardLogic(map)

    val player: PlayerLogic = new PlayerLogic(starting_money, starting_lives)

}

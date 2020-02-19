package GameLogic

import java.awt.{Graphics2D, Rectangle}

import GUI.FTimer
import com.sun.org.apache.xpath.internal.operations.Bool

import scala.collection.mutable


class BoardLogic(val map: GameMap)
{
    var monsters: mutable.Set[Monster] = mutable.Set[Monster]()
    var towers: mutable.Set[Tower] = mutable.Set[Tower]()
    var bullets: mutable.Set[BulletLogic] = mutable.Set[BulletLogic]()

    def tick_board(): Unit =
    {
        monsters.filterInPlace(p => p.position.is_in_bounds())
        bullets.filterInPlace(p => p.position.is_in_bounds())
        monsters.foreach(m => m.tick(this))
        towers.foreach(m => m.tick(this))
        bullets.foreach(m => m.tick(this))

        println(monsters.size)

    }

    def paint_board(g: Graphics2D, square_size: (Int, Int), bounds: Rectangle): Unit =
    {
        map.paint_map(g, square_size, bounds)
        monsters.foreach(m => m.paint(g))
        towers.foreach(m => m.paint(g))
        bullets.foreach(m => m.paint(g))
    }
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
            case Nil =>
                end()
                false
        }
    }

    def start(): Unit =
    {
        () //TODO
    }

    def end(): Unit = () //TODO

    def isFinished: Boolean = Nil == next_waves
}

class PlayerLogic(var money: Double, var lives: Double)
{
}

class GameLogic(map: GameMap, starting_money: Double, starting_lives: Double, var next_levels: List[Level])
{
    val board: BoardLogic = new BoardLogic(map)

    val player: PlayerLogic = new PlayerLogic(starting_money, starting_lives)

    val tick_interval = 10

    val timer: GUI.FTimer = new FTimer(tick_interval, x => board.tick_board())
    timer.start()

    def start_next_level(): Boolean =
    {
        next_levels match
        {
            case level :: next =>
                next_levels = next
                level.start()
                true
            case Nil => false
        }
    }

    def spawn_monster(monster: Monster): Unit =
    {
        board.monsters.addOne(monster)
    }
}

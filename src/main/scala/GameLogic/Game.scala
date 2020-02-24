package GameLogic

import java.awt.{Graphics2D, Rectangle}

import GUI.FTimer

import scala.collection.mutable


class BoardLogic(val map: GameMap)
{
    var monsters: mutable.Set[Monster] = mutable.Set[Monster]()
    var towers: mutable.Set[Tower] = mutable.Set[Tower]()
    var bullets: mutable.Set[Bullet] = mutable.Set[Bullet]()

    def tick_board(): Unit =
    {
        monsters.filterInPlace(p => p.position.is_in_bounds())
        bullets.filterInPlace(p => p.position.is_in_bounds())
        monsters.foreach(m => m.tick(this))
        towers.foreach(m => m.tick(this))
        bullets.foreach(m => m.tick(this))
    }

    def paint_board(g: Graphics2D, square_size: (Int, Int), bounds: Rectangle): Unit =
    {
        map.paint_map(g, square_size, bounds)
        monsters.foreach(m => m.paint(g))
        towers.foreach(m => m.paint(g))
        bullets.foreach(m => m.paint(g))
    }

    def spawn_bullet(bullet: Bullet): Unit =
    {
        bullets.addOne(bullet)
    }
}

case class Level(val waves: List[GameLogic => Wave])(val game_logic: GameLogic)
{
    var next_waves: List[GameLogic => Wave] = waves

    def spawn_wave(): Boolean =
    {
        next_waves match
        {
            case wave :: next =>
                next_waves = next
                wave(game_logic).spawn()
                true
            case Nil =>
                try_end()
                false
        }
    }

    def try_spawn_wave() =
    {
        if(!(spawn_wave()))
        {
            try_end()
        }
    }
    
    val alarm: GUI.FTimer = new GUI.FTimer(100000/60, a => try_spawn_wave)
    
    def start(): Unit =
    {
        alarm.start()
    }

    def can_end(): Boolean = 
    {//s'il n'y a plus aucun monstre
        var succes = true
        game_logic.board.monsters.foreach(m => succes = false)
        succes
    }
    
    def try_end(): Unit =
    {
        if(can_end())
        {
            alarm.stop()
        }
    }

    def isFinished: Boolean = Nil == next_waves
}

class PlayerLogic(var money: Double, var lives: Double)
{
}

class GameLogic(map: GameMap, starting_money: Double, starting_lives: Double, var next_levels: List[GameLogic => Level])
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
                level(this).start()
                true
            case Nil => false
        }
    }

    def spawn_monster(monster: Monster): Unit =
    {
        board.monsters.addOne(monster)
    }

    def spawn_tower(tower: Tower): Unit =
    {
        board.towers.addOne(tower)
    }
}

object GameStrategy {
    val spawn_point = new Point2DDouble(0.5, 0.01)

    val levels: List[GameLogic => Level]=
      
      (//level 1
          Level(List(
          Array((p => Triangle(p))),//wave 1.1
          Array((p => Triangle(p)),(p => Triangle(p)))))//wave 1.2
      ) :: (//level 2
          Level(List(
          Array((p => Triangle(p)),(p => Triangle(p)),(p => Triangle(p))),//wave 2.1
          Array((p => Triangle(p)),(p => Triangle(p)))))//wave 2.2
      ) :: Nil
}

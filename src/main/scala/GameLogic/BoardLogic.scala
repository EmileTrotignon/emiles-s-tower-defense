package GameLogic

import java.awt.{Graphics2D, Rectangle}

import scala.collection.mutable

class BoardLogic(val map: GameMap, var next_levels: List[Level])
{
    var monsters: mutable.Set[Monster] = mutable.Set[Monster]()
    var towers: mutable.Set[Tower] = mutable.Set[Tower]()
    var bullets: mutable.Set[Bullet] = mutable.Set[Bullet]()
    var current_level: Level = new Level(Nil)

    def start_next_level(game_logic: GameLogic): Boolean =
    {
        next_levels match
        {
            case level :: next =>
                next_levels = next
                current_level = level
                current_level.start(game_logic)
                true
            case Nil => false
        }
    }

    def is_deserted: Boolean = //tells whether there is still living monsters
    {
        monsters.isEmpty
    }

    def tick_board(): Unit =
    {
        monsters.filterInPlace(p => p.position.is_in_bounds())
        bullets.filterInPlace(p => p.position.is_in_bounds())
        monsters.foreach(m =>
        {
            val collisions = bullets.filter(b => b.is_colliding(m))
            collisions.foreach(b =>
            {
                m.take_damage(b.damage)
            })
            bullets = bullets.diff(collisions)
        })
        monsters.foreach(m => m.tick(this))
        towers.foreach(m => m.tick(this))
        bullets.foreach(m => m.tick(this))
        if (is_deserted && current_level.is_finished)
            current_level.end()
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
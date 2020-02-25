package GameLogic

import java.awt.{Graphics2D, Rectangle}

import scala.collection.mutable

class BoardLogic(val map: GameMap)
{
    var monsters: mutable.Set[Monster] = mutable.Set[Monster]()
    var towers: mutable.Set[Tower] = mutable.Set[Tower]()
    var bullets: mutable.Set[Bullet] = mutable.Set[Bullet]()

    def is_deserted: Boolean = //tells whether there is still living monsters
    {
        monsters.isEmpty
    }

    def tick_board(): Unit =
    {
        monsters.filterInPlace(p =>
        {
            !p.dead && p.position.is_in_bounds()
        })
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

    }

    def paint_board(size_info: SizeInfo, g: Graphics2D): Unit =
    {
        map.paint_map(size_info, g)
        monsters.foreach(m => m.paint(size_info, g))
        towers.foreach(m => m.paint(size_info, g))
        bullets.foreach(m => m.paint(size_info, g))
    }

    def spawn_bullet(bullet: Bullet): Unit =
    {
        bullets.addOne(bullet)
    }
}
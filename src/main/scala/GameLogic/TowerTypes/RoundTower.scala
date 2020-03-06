package GameLogic.TowerTypes

import java.awt.{Color, Graphics2D}

import GameLogic._

case class RoundTower(square_ : Int2, size_info: SizeInfo) extends Tower(square_, size_info)
{

    protected val damage: Double = 1
    protected val period: Int = 60
    val _reach: Double = 5 //portée exprimée en nombre de pixels

    private var tick: Int = 0

    override def paint(size_info: SizeInfoPixels, g: Graphics2D): Unit =
    {
        g.setColor(Color.GREEN)
        Graphics.fill_oval(size_info, size_info.pixels_to_logic(size_info.square_size) * .9, position, g)
    }

    override def tick(b: BoardLogic): Unit =
    {
        if (tick % period == 0 && b.monsters.nonEmpty)
        {
            shoot_bullet(b)
        }
        tick += 1
    }

    def shoot_bullet(b: BoardLogic): Unit =
    {
        val monster = b.monsters.minBy(m => Double2.squared_dist(position, m.position))
        if (Double2.dist(monster.position, position) <= _reach)
        {
            val direction = Double2.normalized(monster.position - position)
            val bullet = BigBullet(b.monsters, position, direction)
            b.spawn_bullet(bullet)
        }
    }
}

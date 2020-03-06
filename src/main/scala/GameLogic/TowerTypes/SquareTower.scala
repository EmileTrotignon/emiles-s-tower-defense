package GameLogic.TowerTypes

import java.awt.{Color, Graphics2D}

import GameLogic._

case class SquareTower(square_ : Int2, size_info: SizeInfo) extends Tower(square_, size_info: SizeInfo)
{

    val damage: Double = 0.5
    val period: Int = 20
    val reach: Double = 2 //portée exprimée en nombre de pixels

    private var tick: Int = 0

    override def paint(size_info: SizeInfoPixels, g: Graphics2D): Unit =
    {
        val pos_pixels = size_info.logic_to_pixels(position)
        val width: Int = (size_info.square_size.x * 0.9).floor.toInt
        val height: Int = (size_info.square_size.y * 0.9).floor.toInt

        g.setColor(Color.GREEN)

        g.fillRect(pos_pixels.x - width / 2, pos_pixels.y - height / 2, width, height)
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
        if (Double2.dist(monster.position, position) <= reach)
        {
            val direction = Double2.normalized(monster.position - position)
            val bullet = BaseBullet(position, direction)
            b.spawn_bullet(bullet)
        }
    }
}

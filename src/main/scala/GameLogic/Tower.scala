package GameLogic

import java.awt.{Color, Graphics2D}

abstract class Tower(val square: Int2 = null, map: GameMap) extends BoardObject
{
    override var position: Double2 = Int2.square_center(square, map.width(), map.height())
    val damage: Double
    val period: Int
    val reach: Double

    def build(): Unit =
    {}


}


case class SquareTower(square_ : Int2, map: GameMap) extends Tower(square_, map)
{

    val damage: Double = 0.5
    val period: Int = 40
    val reach: Double = 0.2 //portée exprimée en nombre de pixels

    private var tick: Int = 0

    override def paint(size_info: SizeInfo, g: Graphics2D): Unit =
    {
        val pos_pixels = size_info.logic_to_pixels(position)
        val width: Int = (size_info.square_size._1 * 0.9).floor.toInt
        val height: Int = (size_info.square_size._2 * 0.9).floor.toInt

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

case class RoundTower(square_ : Int2, map: GameMap) extends Tower(square_, map)
{

    val damage: Double = 1
    val period: Int = 30
    val reach: Double = 0.4 //portée exprimée en nombre de pixels

    private var tick: Int = 0

    override def paint(size_info: SizeInfo, g: Graphics2D): Unit =
    {
        val pos_pixels = size_info.logic_to_pixels(position)
        val width: Int = (size_info.square_size._1 * 0.9).floor.toInt
        val height: Int = (size_info.square_size._2 * 0.9).floor.toInt

        g.setColor(Color.GREEN)

        g.fillOval(pos_pixels.x - width / 2, pos_pixels.y - height / 2, width, height)
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

case class TowerType(name: String, constructor: (Int2, GameMap) => Tower, cost: Double)
{

}

object Towers
{
    var tower_constructors: Array[TowerType] = Array(TowerType("Square tower", SquareTower, 5), TowerType("Round tower", RoundTower, 10))
}

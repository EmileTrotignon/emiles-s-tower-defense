package GameLogic

import java.awt.{Color, Graphics2D}

abstract class Tower(val square: Point2DInt = null, map: GameMap) extends BoardObject
{
    override var position: Point2DDouble = Point2DInt.square_center(square, map.width(), map.height())
    val damage: Double
    val period: Int
    val reach: Int //portée exprimée en nombre de pixels
    val cost: Double

    def build(): Unit =
    {}
}


case class SquareTower(square_ : Point2DInt, map: GameMap) extends Tower(square_, map)
{

    val damage: Double = 1
    val period: Int = 20
    val reach: Int = 1 //portée exprimée en nombre de pixels
    val cost: Double = 1

    private var tick: Int = 0

    override def paint(g: Graphics2D): Unit =
    {
        val pos_pixels = position.to_pixels(g.getClipBounds().width, g.getClipBounds().height)
        g.setColor(Color.GREEN)
        g.fillRect(pos_pixels.x, pos_pixels.y, 50, 50)
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
        val monster = b.monsters.minBy(m => Point2DDouble.squared_dist(position, m.position))
        val direction = Point2DDouble.normalized(monster.position - position)
        val bullet = BaseBullet(new Point2DDouble(position), new Point2DDouble(direction))
        b.spawn_bullet(bullet)
    }
}

class TowerType(val name: String, val constructor: (Point2DInt, GameMap) => Tower)
{

}

object Towers
{
    var tower_constructors: Array[TowerType] = Array(new TowerType("Square tower", SquareTower)) //TODO Reste à géger cette liste. Pour la 1ère version, on peut faire que tous les types de tower sont disponibles dès le début
}

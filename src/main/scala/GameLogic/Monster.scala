package GameLogic

import java.awt.{Color, Graphics2D}

abstract class Monster(position_ : Point2DDouble) extends BoardObject
{
    override var position: Point2DDouble = new Point2DDouble(position_)
    val max_hp: Double
    var hp: Double
    val speed: Double
    val loot: Double
    val size: Double

    def take_damage(damage: Double): Unit =
    {
        hp -= damage
    }

    def die(b: BoardLogic): Unit =
    {

    }

    override def tick(b: BoardLogic): Unit =
    {
        if (hp <= 0)
        {
            this.die(b)
        }

        position = position + new Point2DDouble(0, speed)
    }
}

class Wave(monster_ : Array[Point2DDouble => Monster])
{
    val monsters: Array[Point2DDouble => Monster] = monster_

    def spawn(game_logic: GameLogic): Unit =
    {
        monsters.foreach(monster => game_logic.spawn_monster(monster(MonstersStrategy.spawn_point)))
    }

}

case class Triangle(position_ : Point2DDouble) extends Monster(position_)
{
    override val max_hp: Double = 10
    override var hp: Double = max_hp
    override val speed: Double = 0.001
    override val loot: Double = 1
    override val size: Double = 0.05

    override def paint(g: Graphics2D): Unit =
    {

        val pos_pixels = position.to_pixels(g.getClipBounds().width, g.getClipBounds().height)
        g.setColor(Color.BLUE)
        //g.fillRect(pos_pixels.x-25, pos_pixels.y-25, 50, 50)
        val size_pixels =
        {
            new Point2DDouble(size, size).to_pixels(g.getClipBounds().width, g.getClipBounds().height)
        }
        g.fillOval(pos_pixels.x - size_pixels.x / 2, pos_pixels.y - size_pixels.y / 2, size_pixels.x, size_pixels.y)
        //g.fillPolygon(Array(position.x, position.x + 10, position.x - 10), Array(position.y - 10, position.y + 10, position.y + 10), 1)
    }

    /*override def tick(b: BoardLogic): Unit =
    {
    }*/
}

object MonstersStrategy
{
    val spawn_point = new Point2DDouble(0.5, 0.01)

    val levels: List[Level] =
        (//level 1
          new Level(List(
              new Wave(Array(Triangle)), //wave 1.1
              new Wave(Array(Triangle, Triangle)))) //wave 1.2
            :: (//level 2
            new Level(List(
                new Wave(Array(Triangle, Triangle, Triangle)), //wave 2.1
                new Wave(Array(Triangle, Triangle)))) //wave 2.2
            ) :: Nil)
}

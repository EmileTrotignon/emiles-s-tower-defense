package GameLogic

import java.awt.{Color, Graphics2D}

abstract class Monster(override var position : Point2DDouble) extends BoardObject
{
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
        monsters.foreach((monster) => game_logic.spawn_monster(monster(MonstersStrategy.spawn_point)))
    }

}

class Triangle(position: Point2DDouble) extends Monster(position)
{
    override val max_hp: Double = 10
    override var hp: Double = max_hp
    override val speed: Double = 0.001
    override val loot: Double = 1
    override val size: Double = 3

    override def paint(g: Graphics2D): Unit =
    {

        val pos_pixels = position.to_pixels(g.getClipBounds().width, g.getClipBounds().height)
        g.setColor(Color.BLUE)
        g.fillRect(pos_pixels.x, pos_pixels.y, 50, 50)
        //g.fillPolygon(Array(position.x, position.x + 10, position.x - 10), Array(position.y - 10, position.y + 10, position.y + 10), 1)
    }

    /*override def tick(b: BoardLogic): Unit =
    {
    }*/
}

object MonstersStrategy {
    val spawn_point = new Point2DDouble(0.5, 0.01)

    val levels: List[Level]=
      (//level 1
          new Level(List(
          new Wave(Array(new Triangle(_))),//wave 1.1
          new Wave(Array(new Triangle(_),new Triangle(_)))))//wave 1.2
      ) :: (//level 2
          new Level(List(
          new Wave(Array(new Triangle(_),new Triangle(_),new Triangle(_))),//wave 2.1
          new Wave(Array(new Triangle(_),new Triangle(_)))))//wave 2.2
      ) :: Nil
}

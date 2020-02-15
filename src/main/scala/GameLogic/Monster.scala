package GameLogic

import java.awt.{Color, Graphics, Graphics2D}

abstract class Monster extends BoardObject
{
    val max_hp: Double
    var hp: Double
    val speed: Int
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

        position = new Point(position + new Coordinates(0, speed))
    }


    def spawn(): Unit = () //TODO
}

class Wave(monster_ : Array[Point => Monster])
{
    val monsters: Array[Point => Monster] = monster_

    def spawn(): Unit =
    {
        //monsters.foreach((monster: Monster) => monster.spawn())
    }

}


case class Triangle(override var position: Point) extends Monster
{
    override val max_hp: Double = 10
    override var hp: Double = max_hp
    override val speed: Int = 1
    override val loot: Double = 1
    override val size: Double = 3

    override def paint(g: Graphics2D): Unit =
    {

        g.setColor(Color.BLUE)
        g.fillRect(position.x, position.y, 50, 50)
        //g.fillPolygon(Array(position.x, position.x + 10, position.x - 10), Array(position.y - 10, position.y + 10, position.y + 10), 1)
    }

    /*override def tick(b: BoardLogic): Unit =
    {
    }*/
}


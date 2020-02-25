package GameLogic

import java.awt.{Color, Graphics2D}

abstract class Monster(override var position: Double2) extends BoardObject
{
    val max_hp: Double
    var hp: Double
    var dead: Boolean = false
    val speed: Double
    val loot: Double
    val size: Double

    def take_damage(damage: Double): Unit =
    {
        hp -= damage
    }

    def die(b: BoardLogic): Unit =
    {
        dead = true
    }

    override def tick(b: BoardLogic): Unit =
    {
        if (hp <= 0)
        {
            this.die(b)
        }

        position = position + new Double2(0, speed)
    }
}


case class Triangle(position_ : Double2) extends Monster(position_)
{
    override val max_hp: Double = 10
    override var hp: Double = max_hp
    override val speed: Double = 0.001
    override val loot: Double = 1
    override val size: Double = 0.05

    override def paint(size_info: SizeInfo, g: Graphics2D): Unit =
    {
        g.setColor(Color.BLUE)
        val pos_pixels = size_info.logic_to_pixels(position)
        val size_pixels = size_info.logic_to_pixels(new Double2(size, size))
        g.fillOval(pos_pixels.x - size_pixels.x / 2, pos_pixels.y - size_pixels.y / 2, size_pixels.x, size_pixels.y)
        Graphics.LifeBar.draw_life_bar(position, size, size_info, hp, max_hp, g)
    }

    /*override def tick(b: BoardLogic): Unit =
    {
    }*/
}


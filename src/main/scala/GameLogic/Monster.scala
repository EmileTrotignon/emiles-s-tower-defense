package GameLogic

import java.awt.{Color, Graphics2D}

abstract class Monster(override var position: Double2) extends BoardObject
{
    val max_hp: Double
    var hp: Double
    var dead: Boolean = false
    val damage: Double
    val speed: Double
    val loot: Double
    val size: Double

    def take_damage(damage: Double): Unit =
    {
        hp -= damage
    }

    def die(): Unit =
    {
        dead = true
    }

    override def tick(b: BoardLogic): Unit =
    {
        if (hp <= 0)
        {
            this.die()
        }

        position = position + new Double2(0, speed)
    }
}


class BlueMonster(position_ : Double2) extends Monster(position_)
{
    override val max_hp: Double = 10
    override var hp: Double = max_hp
    override val speed: Double = 0.001
    override val damage: Double = 2
    override val loot: Double = 1
    override val size: Double = 0.05

    override def paint(size_info: SizeInfo, g: Graphics2D): Unit =
    {
        g.setColor(Color.CYAN)
        Graphics.draw_oval(size_info, Double2(size, size), position, g)
        Graphics.draw_life_bar(position, size, size_info, hp, max_hp, g)
    }

    /*override def tick(b: BoardLogic): Unit =
    {
    }*/
}

class DarkBlueMonster(position_ : Double2) extends Monster(position_)
{
    override val max_hp: Double = 12
    override var hp: Double = max_hp
    override val speed: Double = 0.0012
    override val damage: Double = 4
    override val loot: Double = 2
    override val size: Double = 0.055

    override def paint(size_info: SizeInfo, g: Graphics2D): Unit =
    {
        g.setColor(Color.blue)
        Graphics.draw_oval(size_info, Double2(size, size), position, g)
        Graphics.draw_life_bar(position, size, size_info, hp, max_hp, g)
    }
}


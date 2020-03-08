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


        position += Double2.normalized(b.size_info.square_center(b.routes.next_target(position)) - position) * speed
    }
}


class BlueMonster(position_ : Double2) extends Monster(position_)
{
    override val max_hp: Double = 10
    override var hp: Double = max_hp
    override val speed: Double = 0.01
    override val damage: Double = 2
    override val loot: Double = 1
    override val size: Double = 0.5

    override def paint(size_info: SizeInfoPixels, g: Graphics2D): Unit =
    {
        Graphics.fill_oval_contour(size_info, Double2(size, size), position, g, Color.cyan, Color.black)
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
    override val speed: Double = 0.012
    override val damage: Double = 4
    override val loot: Double = 2
    override val size: Double = 0.5

    override def paint(size_info: SizeInfoPixels, g: Graphics2D): Unit =
    {
        Graphics.fill_oval_contour(size_info, Double2(size, size), position, g, Color.blue, Color.black)
        Graphics.draw_life_bar(position, size, size_info, hp, max_hp, g)
    }
}


package GameLogic

import java.awt.{Color, Graphics2D}

import Graphics.{Drawing, DrawingElement}

abstract class Monster(override var position: Double2) extends BoardObject
{
    val max_hp: Double
    var hp: Double
    var dead: Boolean = false
    val damage: Double
    val speed: Double
    val loot: Double
    val size: Double


    override def paint(size_info: SizeInfoPixels, layer: Int, g: Graphics2D): Unit =
    {
        layer match
        {
            case Layers.monster =>
                super.paint(size_info, layer, g)
            case Layers.life_bars =>
                Graphics.draw_life_bar(position, size, size_info, hp, max_hp, g)
            case _ => ()
        }
    }

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


        val vector_to_target = Double2.normalized(b.size_info.square_center(b.routes.next_target(position)) - position)
        val anti_frotteur_component =
            if (b.monsters.size >= 2)
            {
                val closest_monster = b.monsters.filter(m => m != this).minBy(m => Double2.squared_dist(m.position, position))
                val vector_from_closest_monster = Double2.normalized(position - closest_monster.position)
                val distance_of_closest_monster = Double2.squared_dist(position, closest_monster.position)
                vector_from_closest_monster / (distance_of_closest_monster + 0.1)
            } else
            {
                Double2(0, 0)
            }
        val random_component = Double2.random_normalised()
        position += Double2.normalized(vector_to_target + anti_frotteur_component * 0.2 + random_component * 0.1) * speed
    }
}


class CyanMonster(position_ : Double2) extends Monster(position_)
{
    override val max_hp: Double = 10
    override var hp: Double = max_hp
    override val speed: Double = 0.01
    override val damage: Double = 2
    override val loot: Double = 1
    override val size: Double = 0.5
    override val drawing: Drawing = new Graphics.Drawing(Array(
        DrawingElement(filled_up = true, Color.cyan, Graphics.Circle.unit),
        DrawingElement(filled_up = false, Color.black, Graphics.Circle.unit)))
}

class BlueMonster(position_ : Double2) extends Monster(position_)
{
    override val max_hp: Double = 12
    override var hp: Double = max_hp
    override val speed: Double = 0.012
    override val damage: Double = 4
    override val loot: Double = 2
    override val size: Double = 0.5
    override val drawing: Drawing = new Graphics.Drawing(Array(
        DrawingElement(filled_up = true, Color.blue, Graphics.Circle.unit),
        DrawingElement(filled_up = false, Color.black, Graphics.Circle.unit)))


}


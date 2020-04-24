package GameLogic.Monsters

import java.awt.Graphics2D

import GameLogic._

case class MonsterVals(position: Double2, max_hp: Double, hp: Double, damage: Double, speed: Double, direction: Double2, loot: Double, size: Double, take_damage: Double => Unit, monster: Monster)


abstract class Monster(override protected var _position: Double2) extends BoardObject
{
    protected val _max_hp: Double
    var _hp: Double
    var dead: Boolean = false
    protected val _damage: Double
    protected val _speed: Double
    protected val modifiers: MonsterModifierSet = new MonsterModifierSet(get_raw_vals)
    protected val _loot: Double

    def add_modifier(modifier: MonsterModifier): Unit =
    {
        modifiers.modifiers.addOne(modifier)
    }
    
    def speed: Double = modifiers.values.speed

    def damage: Double = modifiers.values.damage

    def loot: Double = modifiers.values.loot

    def hp: Double = scala.math.min(modifiers.values.hp, modifiers.values.max_hp) 

    def max_hp: Double = modifiers.values.max_hp
    
    def take_damage: Double => Unit = modifiers.values.take_damage

    override def direction: Double2 = modifiers.values.direction

    override def size: Double = modifiers.values.size
    
    override def position: Double2 = modifiers.values.position

    override def paint(size_info: SizeInfoPixels, layer: Int, g: Graphics2D): Unit =
    {
        layer match
        {
            case Layers.monster =>
                super.paint(size_info, layer, g)
            case Layers.life_bars =>
                Graphics.draw_life_bar(position, _size, size_info, hp, max_hp, g)
            case _ => ()
        }
    }

    def _take_damage(damage: Double): Unit =
    {
        _hp -= damage
    }

    def die(): Unit =
    {
        dead = true
    }

    def get_raw_vals: MonsterVals =
    {
        MonsterVals(_position, _max_hp, _hp, _damage, _speed, _direction, _loot, _size, _take_damage, this)
    }

    override def tick(b: BoardLogic): Unit =
    {
        modifiers.update(this)

        if (hp <= 0)
        {
            this.die()
            return
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
        _direction = Double2.normalized(vector_to_target + anti_frotteur_component * 0.2 + random_component * 0.1)
        modifiers.update(this)
        _position += direction * speed
        modifiers.tick(this)

    }
}

package GameLogic.Towers

import GameLogic._

import scala.collection.mutable

case class RoundTower(square_ : Int2, size_info: SizeInfo) extends Tower(square_, size_info)
{

    protected val damage: Double = 1.5
    protected val period: Int = 60
    val _reach: Double = 5 //portée exprimée en nombre de pixels

    private var tick: Int = 0
    private val bullet_speed = 0.025

    protected class TBullet(direction: Double2, override protected val monsters: mutable.Set[Monster], override var position: Double2) extends ZoneDamageBullet
    {
        override val zone_size: Double = 3
        override protected val damage: Double = 0
        override protected val zone_damage: Double = RoundTower.this.damage
        override val direction_and_speed: Double2 = Double2.normalized(direction) * bullet_speed
        override val size: Double = 0.3
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
        if (Double2.dist(monster.position, position) <= _reach)
        {
            val direction = Double2.normalized(monster.position - position)
            val bullet = new TBullet(direction, b.monsters, position)

            b.spawn_bullet(bullet)
        }
    }
}

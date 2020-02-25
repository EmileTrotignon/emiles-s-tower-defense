package GameLogic

class PlayerLogic(var money: Double, var lives: Double)
{
    def monster_in_base(m: Monster): Unit =
    {
        lives -= m.damage
        m.die()
    }
}

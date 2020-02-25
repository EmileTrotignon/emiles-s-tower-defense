package GameLogic

import GUI.FSignal

class PlayerLogic(private var _money: Double, private var _lives: Double)
{

    def money: Double = _money

    def money_=(new_money: Double)
    {
        _money = new_money
        updated_signal.emit(this)
    }


    def lives: Double = _lives

    def lives_=(new_lives: Double)
    {
        _lives = new_lives
        updated_signal.emit(this)
    }

    val updated_signal = new FSignal[this.type]

    def monster_in_base(m: Monster): Unit =
    {
        lives -= m.damage
        m.die()
    }
}

package GUI

import java.awt.event.ActionEvent

import GameLogic.{TileAction, PlayerAction}

class TileActionButton(player_action: PlayerAction, a: ActionEvent => Unit)
  extends FButton(s"${player_action.name} : ${player_action.cost}", a)
{
    private var _buildable_interface = true

    def buildable_interface: Boolean = _buildable_interface

    def buildable_interface_=(new_buildable: Boolean): Unit =
    {
        _buildable_interface = new_buildable
        update_enabled()
    }

    private var _buildable_money = true

    def buildable_money: Boolean = _buildable_money

    def buildable_money_=(new_buildable: Boolean): Unit =
    {
        _buildable_money = new_buildable
        update_enabled()
    }

    def update_enabled(): Unit =
    {
        val b = buildable_interface && buildable_money
        setEnabled(b)

    }

    def update_cost_enabling(money: Double): Unit =
    {
        val b = money >= player_action.cost
        buildable_money = b
    }
}

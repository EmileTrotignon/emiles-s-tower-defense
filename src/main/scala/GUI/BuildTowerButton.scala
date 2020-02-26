package GUI

import java.awt.event.ActionEvent

import GameLogic.TowerType

class BuildTowerButton(val tower_type: TowerType, panel: BuildTowersPanel)
  extends FButton(s"${tower_type.name} : ${tower_type.cost}",
      (a: ActionEvent) =>
      {
          panel.build_tower(tower_type.cost, tower_type.constructor)(a)
          panel.builders.foreach(b => b.buildable_interface = false)
      })
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
        val b = money >= tower_type.cost
        buildable_money = b
    }
}

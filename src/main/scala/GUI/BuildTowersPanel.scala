package GUI

import java.awt.event.ActionEvent

import GameLogic._

import javax.swing.{BoxLayout, JButton, JPanel}

class BuildTowersPanel(val canvas: GameBoardCanvas) extends JPanel
{
    def build_tower(cost: Double, tower_constructor: (GameLogic.Int2, GameLogic.GameMap) => GameLogic.Tower)(a: ActionEvent): Unit =
    {
        canvas.GameBoardCanvasMouseListener.status = BuildingTower(cost, tower_constructor)
        cancel_button.setVisible(true)
    }

    def enable_every_tower(): Unit =
    {
        builders.foreach(b => b.buildable_interface = true)
    }

    def cancel_build(action_event: ActionEvent): Unit =
    {
        enable_every_tower()
        cancel_button.setVisible(false)
    }

    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS))

    val builders: Array[BuildTowerButton] = GameLogic.Towers.tower_constructors.map(
        (t: GameLogic.TowerType) =>
        {
            val b = new BuildTowerButton(t, this)
            this.add(b)
            b
        })

    val cancel_button = new FButton("Cancel", cancel_build)
    this.add(cancel_button)
    cancel_button.setVisible(false)

    def update_affordable_towers(player: PlayerLogic)
    {
        builders.foreach(btb => btb.update_cost_enabling(player.money))
    }
}

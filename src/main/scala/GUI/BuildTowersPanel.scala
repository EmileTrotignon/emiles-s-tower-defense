package GUI

import java.awt.event.ActionEvent

import GameLogic.{Towers, _}
import _root_.GameLogic.Towers.{Tower, TowerType}
import javax.swing.{BoxLayout, JPanel}

class BuildTowersPanel(val canvas: GameBoardCanvas) extends JPanel
{
    def build_tower(cost: Double, tower_constructor: (GameLogic.Int2, GameLogic.SizeInfo) => Tower)(a: ActionEvent): Unit =
    {
        canvas.GameBoardCanvasMouseListener.status = GameBoardCanvas.BuildingTower(cost, tower_constructor)
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

    val builders: Array[BuildTowerButton] = Towers.Tower.tower_constructors.map(
        (t: TowerType) =>
        {
            val b = new BuildTowerButton(t, (a: ActionEvent) =>
            {
                build_tower(t.cost, t.constructor)(a)
                builders.foreach(b => b.buildable_interface = false)
            })
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

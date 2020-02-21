package GUI

import java.awt.event.ActionEvent

import javax.swing.{BoxLayout, JButton, JPanel}

class BuildTowersPanel(val canvas : GameBoardCanvas) extends JPanel
{
    def build_tower(tower_constructor: (GameLogic.Point2DInt, GameLogic.GameMap) => GameLogic.Tower)(a: ActionEvent): Unit =
    {
        canvas.GameBoardCanvasMouseListener.status = BuildingTower(tower_constructor)
        cancel_button.setVisible(true)
    }

    def cancel_build(action_event: ActionEvent): Unit =
    {
        cancel_button.setVisible(false)
    }

    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS))

    val buttons: Array[JButton] = GameLogic.Towers.tower_constructors.map(
        (t: GameLogic.TowerType) =>
        {
            val b = new FButton(t.name, build_tower(t.constructor))
            this.add(b)
            b
        })

    val cancel_button = new FButton("Cancel", cancel_build)
    this.add(cancel_button)
    cancel_button.setVisible(false)

}

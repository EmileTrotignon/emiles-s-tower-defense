package GUI

import java.awt.event.ActionEvent

import GameLogic.{Towers, TileAction, _}
import _root_.GameLogic.Towers.{Tower}
import javax.swing.{BoxLayout, JPanel}

class TileActionsPanel(val canvas: GameBoardCanvas) extends JPanel
{
    def perform_tile_action(cost: Double, tile_action: TileAction)(a: ActionEvent): Unit =
    {
        canvas.GameBoardCanvasMouseListener.status = GameBoardCanvas.PerformingTileAction(cost, tile_action)
        cancel_button.setVisible(true)
    }

    def enable_every_tile_action(): Unit =
    {
        tile_actions.foreach(b => b.buildable_interface = true)
    }

    def cancel_tile_action(action_event: ActionEvent): Unit =
    {
        enable_every_tile_action()
        cancel_button.setVisible(false)
    }

    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS))

    val tile_actions: Array[TileActionButton] = PlayerActions.actions.map(
        (player_action: PlayerAction) =>
        {
            val b = new TileActionButton(player_action, (a: ActionEvent) =>
            {
                perform_tile_action(player_action.cost, player_action.tile_action)(a)
                tile_actions.foreach(b => b.buildable_interface = false)
            })
            this.add(b)
            b
        })

    val cancel_button = new FButton("Cancel", cancel_tile_action)
    this.add(cancel_button)
    cancel_button.setVisible(false)

    def update_affordable_towers(player: PlayerLogic)
    {
        tile_actions.foreach(btb => btb.update_cost_enabling(player.money))
    }
}

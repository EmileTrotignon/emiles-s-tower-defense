
package GUI

import java.awt.event.ActionEvent

import javax.swing.UIManager

object Main //extends SimpleSwingApplication
{
    val lobby_window = new Lobby("Emiles's Tower Defense")
    var game_window: Option[GameWindow] = None: Option[GameWindow]

    UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName)


    def main(args: Array[String]): Unit =
    {
        lobby_window.setVisible(true)
    }

    def start_game(action_event: ActionEvent): Unit =
    {
        System.setProperty("sun.java2d.opengl", "true")
        lobby_window.setVisible(false)
        game_window = Some(new GameWindow())
        game_window match
        {
            case None => throw new RuntimeException()
            case Some(gw) => gw.setVisible(true)
        }

    }
}

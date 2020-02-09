
package GUI

import javax.swing.UIManager

object Main //extends SimpleSwingApplication
{
    val lobby_window = new Lobby("Emiles's GameLogic.Tower Defense")
    var game_window: Option[GameWindow] = None : Option[GameWindow]
    try
    {
        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName)
    }
    catch
    {
        case e: Exception =>
            println("Setting look failed")
    }

    def main(args: Array[String]): Unit =
    {
        lobby_window.setVisible(true)
    }

    def start_game(): Unit =
    {
        lobby_window.setVisible(false)
        game_window = Some(new GameWindow())
        game_window match
        {
            case None => throw new RuntimeException()
            case Some(gw) => gw.setVisible(true)
        }

    }
}

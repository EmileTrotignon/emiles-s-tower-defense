package GUI

import GameLogic.GameLogic
import javax.swing.UIManager

object Main //extends SimpleSwingApplication
{
    val lobby_window = new Lobby("Emiles's Tower Defense")
    lobby_window.start_game_signal.add_callback(game_logic => start_game(game_logic))
    var game_window: Option[GameWindow] = None: Option[GameWindow]

    UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName)


    def main(args: Array[String]): Unit =
    {
        open_lobby
    }
    
    def open_lobby: Unit =
    {
        lobby_window.setVisible(true)
    }

    def start_game(game_logic: GameLogic): Unit =
    {
        System.setProperty("sun.java2d.opengl", "true")
        lobby_window.setVisible(false)
        game_window = Some(new GameWindow(game_logic, _ => open_lobby))
        game_window match
        {
            case None => throw new RuntimeException()
            case Some(gw) => gw.setVisible(true)
        }

    }
}

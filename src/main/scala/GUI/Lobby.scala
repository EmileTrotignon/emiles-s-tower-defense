package GUI
import GUI.Actions.StartGameAction
import javax.swing._

class Lobby(name : String) extends JFrame(name)
{
    val start_game_button = new JButton("Start game")
    this.add(start_game_button)
    start_game_button.setAction(StartGameAction)
}

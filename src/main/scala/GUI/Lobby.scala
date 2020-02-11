package GUI
import javax.swing._

class Lobby(name : String) extends JFrame(name)
{
    val start_game_button = new JButton()
    this.add(start_game_button)
    start_game_button.setAction(new ActionFromFunction(Main.start_game))
    start_game_button.setText("Start game")
}

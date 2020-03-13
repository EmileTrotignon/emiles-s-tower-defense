package GUI
import javax.swing._

class Lobby(name : String) extends JFrame(name)
{
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)

    val start_game_signal: FSignal[Unit] = new FSignal[Unit]

    val start_game_button = new JButton()
    this.add(start_game_button)
    start_game_button.setAction(new FAction(Main.start_game))
    start_game_button.setText("Start game")

}

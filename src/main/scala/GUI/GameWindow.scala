package GUI
import javax.swing._
import java.awt.{BorderLayout, Canvas, Graphics}


class GameWindow extends JFrame("Tower Defense coucou")
{
    val build_tower_button = new JButton("Build tower")
    val next_level_button = new JButton("Start next level")
    val canvas = new Canvas()

    import javax.swing.JPanel
    import java.awt.BorderLayout

    val contentPane = new JPanel(new BorderLayout)
    //contentPane.setBorder(someBorder)
    val panel_page_end = new JPanel()
    contentPane.add(panel_page_end, BorderLayout.PAGE_END)
    panel_page_end.add(build_tower_button)
    panel_page_end.add(next_level_button);
    //contentPane.add(build_tower_button, BorderLayout.PAGE_END)
    //contentPane.add(next_level_button, BorderLayout.PAGE_END)

    this.setContentPane(contentPane);
    //this.getContentPane.add(build_tower_button, BorderLayout.C)
    //this.add(next_level_button)

}

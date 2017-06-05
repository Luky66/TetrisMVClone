
package tetrismvclone;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class ScoreView extends JPanel{
    
    public int score = 0;
    private JLabel scoreLabel;
    
    public ScoreView(int x, int y, int width, int height)
    {
        this.setBounds(x, y, width, height);
        //this.setBackground(Color.white);
        
        // Label
        JLabel label = new JLabel("Score: " + score);
        label.setBounds(0, 0, 100, 30);
        
        //label.setBackground(Color.white);
        //label.setOpaque(true);
        this.scoreLabel = label;
        this.add(scoreLabel);
    }
    
    public void UpdateScore()
    {
        scoreLabel.setText("Score: "+ score);
    }
    
    
}

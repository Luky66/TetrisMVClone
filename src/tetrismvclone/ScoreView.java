
// The score view is a Panel that contains three panels for the different socres
// These different scores are the points, the level and the lines cleared


package tetrismvclone;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class ScoreView extends JPanel{
    
    public int score = 0;
    
    // Panels
    private JPanel pointsPanel = new JPanel();
    private JPanel levelPanel;
    private JPanel linesPanel;
    
    // Labels
    private JLabel pointsTitleLabel;
    private JLabel pointsValueLabel;
    
    // Fonts
    private Font titleFont = new Font("SmallPlain", Font.PLAIN, 18);
    private Font valueFont = new Font("BigPlain", Font.PLAIN, 24);
    
    public ScoreView(int x, int y, int width, int height)
    {
        this.setLayout(null);
        this.setBounds(x, y, width, height);
        
        pointsPanel.setLayout(null);
        pointsPanel.setBounds(0, 0, width, height);
        // Labels //6 labels
        pointsTitleLabel = new JLabel("Score:");
        pointsTitleLabel.setFont(titleFont);
        pointsTitleLabel.setBounds(0, 0, 100, 30);
        pointsPanel.add(pointsTitleLabel);
        
        pointsValueLabel = new JLabel("0");
        pointsValueLabel.setFont(valueFont);
        pointsValueLabel.setBounds(60, 0, 100, 30);
        pointsPanel.add(pointsValueLabel);
        
        this.add(pointsPanel);
    }
    
    public void UpdateScore()
    {
        pointsValueLabel.setText(""+score);
    }
    
    
}

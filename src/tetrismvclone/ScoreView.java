
// The score view is a Panel that contains three panels for the different socres
// These different scores are the points, the level and the lines cleared


package tetrismvclone;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

// This is a view

public class ScoreView extends JPanel{
    
    public int score = 0;
    public int level = 1;
    public int lines = 0;
    
    // Panels
    private JPanel pointsPanel = new JPanel();
    private JPanel levelPanel = new JPanel();
    private JPanel linesPanel = new JPanel();
    
    // Labels
    private JLabel pointsTitleLabel;
    private JLabel pointsValueLabel;
    private JLabel levelTitleLabel;
    private JLabel levelValueLabel;
    private JLabel linesTitleLabel;
    private JLabel linesValueLabel;
    
    // Fonts
    private Font titleFont = new Font("SmallPlain", Font.PLAIN, 18);
    private Font valueFont = new Font("BigPlain", Font.PLAIN, 24);
    
    public ScoreView(int x, int y, int width, int height)
    {
        this.setLayout(null);
        this.setBounds(x, y, width, height);
        
        
        pointsPanel.setLayout(null);
        pointsPanel.setBounds(0, 0, width, 30);
        levelPanel.setLayout(null);
        levelPanel.setBounds(0, 30, width, 30);
        linesPanel.setLayout(null);
        linesPanel.setBounds(0, 60, width, 30);
        
        
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
        
        
        levelTitleLabel = new JLabel("Level:");
        levelTitleLabel.setFont(titleFont);
        levelTitleLabel.setBounds(0, 0, 100, 30);
        levelPanel.add(levelTitleLabel);
        
        levelValueLabel = new JLabel("0");
        levelValueLabel.setFont(valueFont);
        levelValueLabel.setBounds(60, 0, 100, 30);
        levelPanel.add(levelValueLabel);
        
        this.add(levelPanel);
        
        
        linesTitleLabel = new JLabel("Lines:");
        linesTitleLabel.setFont(titleFont);
        linesTitleLabel.setBounds(0, 0, 100, 30);
        linesPanel.add(linesTitleLabel);
        
        linesValueLabel = new JLabel("0");
        linesValueLabel.setFont(valueFont);
        linesValueLabel.setBounds(60, 0, 100, 30);
        linesPanel.add(linesValueLabel);
        
        this.add(linesPanel);
    }
    
    public void UpdateScore()
    {
        pointsValueLabel.setText(String.valueOf(score));
        levelValueLabel.setText(""+level);
        linesValueLabel.setText(""+lines);
    }
    
    
}

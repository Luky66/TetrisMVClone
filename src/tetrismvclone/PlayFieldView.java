
package tetrismvclone;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.JPanel;
import tetrismvclone.blocks.Block;
import tetrismvclone.blocks.BlockPart;

// This is a view

public class PlayFieldView extends JPanel{
    
    
    private int rows;
    private int cols;
    private int blockedArea;
    
    public int[][] field;
    public Block currentBlock; // the current block is drawn in this view along with the field
    
    // draw
    private Color[] colorPallete;
    private Color backgroundColor = Color.gray;
    private int squareSize;
    
    // GameOver stuff
    private JLabel gameOverLabel = new JLabel("Game Over!");
    
    public PlayFieldView(int rows, int blockedArea, int cols, int appBorder, int squareSize, Color[] colors)
    {
        this.setBounds(appBorder, appBorder, squareSize*cols+1, squareSize*(rows-blockedArea)+1);
        this.setBackground(backgroundColor);
        
        this.squareSize = squareSize;
        
        this.colorPallete = colors;
        
        this.rows = rows;
        this.cols = cols;
        this.blockedArea = blockedArea;

        this.setLayout(null);
        gameOverLabel.setFont(new Font("GameOver", Font.BOLD, 36));
        gameOverLabel.setForeground(Color.red);
        gameOverLabel.setBounds(50, 120, 300, 50);
        gameOverLabel.setOpaque(false);
        gameOverLabel.setVisible(false);
        this.add(gameOverLabel);
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        // Draw game 
        
        // Draw border
        g.setColor(Color.black);
        g.drawRect(0, 0, this.getWidth()-1, this.getHeight()-1);
        
        
        // Draw field
        for(int y=blockedArea; y < rows; y++)
        {
            for (int x = 0; x < cols; x++) {
                if(field[y][x] != 0) // if not transparent at position
                {
                    DrawSquare(g, x, y-blockedArea, colorPallete[field[y][x]]);
                }
            }
        }
        
        if(currentBlock != null)
        {
            // Draw falling block
            for (BlockPart part : currentBlock.parts) {
                if(part.y+currentBlock.y >= 2)
                {
                    DrawSquare(g, part.x+currentBlock.x, part.y+currentBlock.y-blockedArea, colorPallete[currentBlock.color]);
                }
            }
        }
    }
    
    private void DrawSquare(Graphics g, int x, int y, Color color)
    {
        g.setColor(color);
        g.fillRect(x*squareSize, y*squareSize, squareSize, squareSize);
        g.setColor(Color.black);
        g.drawRect(x*squareSize, y*squareSize, squareSize, squareSize);
    }
    
    public void ShowGameOver()
    {
        gameOverLabel.setVisible(true);
    }
}

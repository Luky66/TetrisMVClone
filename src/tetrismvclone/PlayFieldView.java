
package tetrismvclone;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.JPanel;
import tetrismvclone.blocks.Block;
import tetrismvclone.blocks.BlockPart;
import tetrismvclone.blocks.Blocks;


public class PlayFieldView extends JPanel{
    
    private final int squareSize = 30; // In pixels
    private int rows;
    private int cols;
    private int blockedArea;
    
    int[][] field;
    Block currentBlock;
    
    // draw
    Color backgroundColor = Color.gray;
    
    // GameOver stuff
    private JLabel gameOverLabel = new JLabel("Game Over!");
    
    public PlayFieldView(int rows, int blockedArea, int cols)
    {
        this.setBounds(20, 20, squareSize*cols+1, squareSize*(rows-blockedArea)+1);
        this.setBackground(backgroundColor);
        
        this.rows = rows;
        this.cols = cols;
        this.blockedArea = blockedArea;

        gameOverLabel.setFont(new Font("GameOver", Font.BOLD, 36));
        gameOverLabel.setForeground(Color.red);
        gameOverLabel.setBounds(0, 120, WIDTH, HEIGHT);
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
                    DrawSquare(g, x, y-blockedArea, Blocks.colors[field[y][x]]);
                }
            }
        }
        
        // Draw falling block
        for (BlockPart part : currentBlock.parts) {
            if(part.y+currentBlock.y >= 2)
            {
                DrawSquare(g, part.x+currentBlock.x, part.y+currentBlock.y-blockedArea, currentBlock.color);
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

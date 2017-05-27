
package tetrismvclone;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

import tetrismvclone.blocks.Block;
import tetrismvclone.blocks.BlockPart;
import tetrismvclone.blocks.Blocks;

public class View extends JPanel{
    
    private final int squareSize = 30; // In pixels
    private int rows;
    private int cols;
    private int blockedArea;
    private Dimension preferredDimension;
    
    // Colours
    Color backgroundColor = Color.gray;
    
    // Field
    public int[][] field;
    
    // Block
    public Block currentBlock;
            
    public void View(JFrame frame, int rows, int blockedArea, int cols)
    {
        this.preferredDimension = new Dimension(squareSize*cols, squareSize*(rows-blockedArea));
        this.setPreferredSize(preferredDimension);
        this.setBackground(backgroundColor);
        
        this.rows = rows;
        this.cols = cols;
        this.blockedArea = blockedArea;
        
        frame.add(this);
    }
    
    @Override
    public void paint(Graphics g)
    {
        // Draw background
        g.setColor(backgroundColor);
        g.fillRect(0, 0, preferredDimension.width, preferredDimension.height);
    
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
            DrawSquare(g, part.x+currentBlock.x, part.y+currentBlock.y-blockedArea, currentBlock.color);
        }
    }
    
    private void DrawSquare(Graphics g, int x, int y, Color color)
    {
        g.setColor(color);
        g.fillRect(x*squareSize, y*squareSize, squareSize, squareSize);
        g.setColor(Color.black);
        g.drawRect(x*squareSize, y*squareSize, squareSize, squareSize);
    }
    
}

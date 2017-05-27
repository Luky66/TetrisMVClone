
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
    
    private final int applicationBorder = 30;
    
    // play field
    private final int squareSize = 30; // In pixels
    private int rows;
    private int cols;
    private int blockedArea;
    Color backgroundColor = Color.gray;
    
    
    // UI
    private int uiWidth = 210; // in pixels
    private int displayBlockWidth = 180;
    private int displayBlockHeight = 120;
    
    Color uiBackgroundColor = Color.lightGray;
    
    // dimension
    private Dimension preferredDimension;
    
    
    // The Game
    public int[][] field;
    public Block currentBlock;
    public Block nextBlock;
            
    public void View(JFrame frame, int rows, int blockedArea, int cols)
    {
        this.preferredDimension = new Dimension(squareSize*cols+uiWidth+2*applicationBorder, squareSize*(rows-blockedArea)+2*applicationBorder);
        this.setPreferredSize(preferredDimension);
        this.setBackground(uiBackgroundColor);
        
        this.rows = rows;
        this.cols = cols;
        this.blockedArea = blockedArea;
        
        frame.add(this);
    }
    
    @Override
    public void paint(Graphics g)
    {
        
        // Draw UI and block preview
        
        g.setColor(backgroundColor);
        g.fillRect(preferredDimension.width-displayBlockWidth-applicationBorder, applicationBorder, displayBlockWidth, displayBlockHeight);
        
        int xOff = preferredDimension.width-displayBlockWidth-applicationBorder;
        int yOff = applicationBorder + squareSize;
                
        // Draw the next block in the display field
        for (BlockPart part : nextBlock.parts) {
            DrawSquare(g, part.x, part.y, nextBlock.color, xOff, yOff);
        }
        

        // Draw game 
        
        // Draw background
        g.setColor(backgroundColor);
        g.fillRect(applicationBorder, applicationBorder, preferredDimension.width-uiWidth-2*applicationBorder, preferredDimension.height-2*applicationBorder);
        g.drawRect(applicationBorder, applicationBorder, preferredDimension.width-uiWidth-2*applicationBorder, preferredDimension.height-2*applicationBorder);
        
        // Draw field
        for(int y=blockedArea; y < rows; y++)
        {
            for (int x = 0; x < cols; x++) {
                if(field[y][x] != 0) // if not transparent at position
                {
                    DrawSquare(g, x, y-blockedArea, Blocks.colors[field[y][x]], applicationBorder, applicationBorder);
                }
            }
        }
        
        // Draw falling block
        for (BlockPart part : currentBlock.parts) {
            if(part.y+currentBlock.y >= 2)
            {
                DrawSquare(g, part.x+currentBlock.x, part.y+currentBlock.y-blockedArea, currentBlock.color, applicationBorder, applicationBorder);
            }
        }
    }
    
    private void DrawSquare(Graphics g, int x, int y, Color color, int xOff, int yOff)
    {
        g.setColor(color);
        g.fillRect(x*squareSize+xOff, y*squareSize+yOff, squareSize, squareSize);
        g.setColor(Color.black);
        g.drawRect(x*squareSize+xOff, y*squareSize+yOff, squareSize, squareSize);
    }
    
}

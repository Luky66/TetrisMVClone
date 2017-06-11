
package tetrismvclone;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import tetrismvclone.blocks.Block;
import tetrismvclone.blocks.BlockPart;

// this is a view

public class NextBlockPreview extends JPanel{
    
    private int squareSize;
    
    public Block nextBlock;
    
    // draw
    Color[] colorPalette;
    Color backgroundColor = Color.gray;
    
    public NextBlockPreview(int x, int y, int squareSize, Color[] colors)
    {
        this.setBounds(x, y, squareSize*4 + 2*20, squareSize*2+2*20);
        this.setBackground(backgroundColor);
        
        this.squareSize = squareSize;
        
        this.colorPalette = colors;
    }
    
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        // Draw game
        
        g.setColor(Color.black);
        g.drawRect(0, 0, this.getWidth()-1, this.getHeight()-1);
        
        // Draw the next block in the display field
        int xOff = this.getWidth()/2-(nextBlock.width*squareSize)/2;
        int yOff = this.getHeight()/2-(nextBlock.height*squareSize)/2;
        
        for (BlockPart part : nextBlock.parts) {
            
            DrawSquare(g, part.x-nextBlock.offsetX, part.y-nextBlock.offsetY, colorPalette[nextBlock.color], xOff, yOff);
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

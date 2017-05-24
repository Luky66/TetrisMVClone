
package tetrismvclone;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class View extends JPanel{
    
    private final int squareSize = 30; // In pixels
    private int drawRows;
    private int calcRows;
    private int drawCols;
    private int calcCols;
    private Dimension preferredDimension;
    
    // Colours
    Color backgroundColor = Color.white;
    static final Color[] colours = 
    {
        Color.white,
        Color.black,
        Color.cyan,
        Color.blue,
        Color.orange,
        Color.yellow,
        Color.green,
        Color.magenta,
        Color.red
    };
    
    // Field
    public int[][] field;
    
    // Block
    
            
    public void View(JFrame frame, int calcRows, int drawRows, int calcCols)
    {
        this.preferredDimension = new Dimension(squareSize*calcCols, squareSize*drawRows);
        this.setPreferredSize(preferredDimension);
        this.setBackground(backgroundColor);
        
        this.drawRows = drawRows;
        this.calcRows = calcRows;
        this.calcCols = calcCols;
        this.drawCols = calcCols;
        
        frame.add(this);
    }
    
    @Override
    public void paint(Graphics g)
    {
        g.setColor(backgroundColor);
        g.fillRect(0, 0, preferredDimension.width, preferredDimension.height);
    
        // Draw field
        for(int y=0; y < drawRows; y++)
        {
            for (int x = 0; x < drawCols; x++) {
                if(field[y][x] != 0) // if not transparent at position
                {
                    DrawSquare(g, x, y, field[y][x]);
                }
            }
        }
        
        // Draw falling block
    }
    
    private void DrawSquare(Graphics g, int x, int y, int color)
    {
        g.setColor(colours[color]);
        g.fillRect(x*squareSize, y*squareSize, squareSize, squareSize);
    }
    
}

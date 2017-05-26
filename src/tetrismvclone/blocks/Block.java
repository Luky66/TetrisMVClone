
package tetrismvclone.blocks;

import java.awt.Color;
import java.awt.List;
import java.util.ArrayList;


public class Block {
    
    public String[] types = {
        "I" //, "J", "L", "O", "S", "T", "Z"
    };
    
    public String type; // The Blocks are I,J,L,O,S,T,Z
    public int shape;   // For the rotation
    public Color color;
    
    
    
    public BlockPart[] parts;
    
    public int xOffset;
    public int yOffset;
    
    // Constructor
    public Block() // sets a random block
    {
        SetBlock(types[0], 0);
    }
    
    public void SetBlock(String blockType, int shape) // Sets the blocktype
    {
        int index = -1;
        for (int i = 0; i < types.length; i++) {
            if(types[i].equals(blockType))
            {
                index = i;
                break;
            }
        }
        
        if(index < 0 && index >= types.length) // just to be on the safe side
        {
            index = 0;
        }
        
        // important
        SetBlockFromMatrix(Blocks.GetBlock(index, shape));
        
        xOffset = 4;
        yOffset = 0;
    }
    
    
    private void SetBlockFromMatrix(int[][] matrix)
    {
        ArrayList<BlockPart> partList = new ArrayList<>();
        
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[y].length; x++) {
                if(matrix[y][x] > 0)
                {
                    BlockPart part = new BlockPart(x, y);
                    partList.add(part);
                    color = Blocks.colors[matrix[y][x]];
                }
            }
        }
        parts = new BlockPart[partList.size()];
        for (int i = 0; i < parts.length; i++) {
            parts[i] = partList.get(i);
        }
    }
}
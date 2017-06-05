
package tetrismvclone.blocks;

import java.awt.Color;
import java.awt.List;
import java.util.ArrayList;
import java.util.Random;


public class Block {
    
    public String[] types = {
        "I", "J", "L", "O", "S", "T", "Z"
    };
    
    public String type; // The Blocks are I,J,L,O,S,T,Z
    public int shape;   // For the rotation
    public Color color;
    
    public BlockPart[] parts;
    
    public int x;
    public int y;
    
    public int width;
    public int height;
    
    public int offsetX = 1;
    public int offsetY = 0;
    
    // Constructor
    public Block() // sets a random block
    {
        int randomIndex = new Random().nextInt(types.length);
        SetBlock(types[randomIndex], 0);
    }
    
    public void SetBlock(String blockType, int shape) // Sets the blocktype
    {
        type = blockType;
        shape = shape;
        int index = GetTypeIndex(blockType);
        
        if(index < 0 || index >= types.length) // just to be on the safe side
        {
            System.out.println("The index ("+index+") is too high. it was set back to 0");
            index = 0;
        }
        
        // important
        SetBlockFromMatrix(Blocks.GetBlock(index, shape), offsetX, offsetY);
        
        x = 0;
        y = 0;
    }
    
    public void Rotate(int value)
    {
        if(shape < Blocks.blocks[GetTypeIndex(type)].length-1)
        {
            // the current shape is not the last one. Add 1
            shape++;
        }
        else
        {
            // the shape is the last one. Set it back to 0
            shape = 0;
        }
        // Set the new block
        SetBlockFromMatrix(Blocks.GetBlock(GetTypeIndex(type), shape), offsetX, offsetY);
    }
    
    private int GetTypeIndex(String typeString)
    {
        for (int i = 0; i < types.length; i++) {
            if(types[i].equals(typeString))
            {
                return i;
            }
        }
        System.out.println("No type : "+typeString +" found.");
        return -1;
    }
    
    private void SetBlockFromMatrix(int[][] matrix, int offset_x, int offset_y)
    {
        ArrayList<BlockPart> partList = new ArrayList<>();
        
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[y].length; x++) {
                if(matrix[y][x] > 0)
                {
                    BlockPart part = new BlockPart(x+offset_x, y+offset_y);
                    partList.add(part);
                    color = Blocks.colors[matrix[y][x]];
                }
            }
        }
        parts = new BlockPart[partList.size()];
        for (int i = 0; i < parts.length; i++) {
            parts[i] = partList.get(i);
        }
        
        SetWidthAndHeight(parts, matrix[0].length, matrix.length);
    }
    
    private void SetWidthAndHeight(BlockPart[] parts, int matrixWidth, int matrixHeight)
    {
        // Set start borders
        int leftBorder = matrixWidth;
        int rightBorder = 0;
        int topBorder = matrixHeight;
        int botBorder = 0;
        
        for(BlockPart part : parts)
        {
            if(part.x < leftBorder)
            {
                leftBorder = part.x;
            }
            if(part.x > rightBorder)
            {
                rightBorder = part.x;
            }
            if(part.y < topBorder)
            {
                topBorder = part.y;
            }
            if(part.y > botBorder)
            {
                botBorder = part.y;
            }
        }
        
        width = rightBorder-leftBorder+1;
        height = botBorder-topBorder+1;
        
        offsetX = leftBorder;
        offsetY = topBorder;
        //System.out.println("width = "+width+"; height = "+height);
    }
}
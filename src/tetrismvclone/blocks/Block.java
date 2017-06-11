
package tetrismvclone.blocks;

import java.util.ArrayList;
import java.util.Random;

// extends my model

public class Block {
    
    // all the different types of blocks
    public String[] types = {
        "I", "J", "L", "O", "S", "T", "Z"
    };
    
    public int type; // The Blocks are I,J,L,O,S,T,Z
    public int shape;   // For the rotation
    public int color;   // is a value (see the color array in the controller)
    
    public BlockPart[] parts; 
    // the block has for every square a block part with x and y value
    // it is easier to work with those than with matrixes all the time
    
    // the x and y value of the block
    public int x = 0; 
    public int y = 0;   
    
    // width and height
    public int width;
    public int height;
    
    // if the block is not filling the whole matrix it has an offset value to display and spawn correcectly
    public int offsetX = 0;
    public int offsetY = 0;
    
    
    // Constructor
    public Block() // sets a random block
    {
        int randomIndex = new Random().nextInt(types.length); //picks a random number representing the types array
        SetBlock(randomIndex, 0); // creates a random block from this value
    }
    
    public void SetBlock(int blockTypeIndex, int shape) // Sets the blocktype and shape
    {
        type = blockTypeIndex;
        this.shape = shape;
        
        
        if(blockTypeIndex < 0 || blockTypeIndex >= types.length) // just to be on the safe side
        {
            System.out.println("The index ("+blockTypeIndex+") is too high. it was set back to 0");
            blockTypeIndex = 0;
        }
        
        // get the matrix from the block and set the blockparts
        SetBlockFromMatrix(Blocks.GetBlock(blockTypeIndex, shape), offsetX, offsetY);
    }
    
    public void Rotate(int value)
    {
        // the rotation value tells us how many times 90 degrees we are rotating
        // it is unused atm
        
        // go through the shapes of a block
        if(shape < Blocks.blocks[type].length-1)
        {
            // the current shape is not the last one. Add 1
            shape++;
        }
        else
        {
            // the shape is the last one in the array. Go back to the firs one
            shape = 0;
        }
        // Set the new block
        SetBlockFromMatrix(Blocks.GetBlock(type, shape), 0, 0);
    }
    
    
    private void SetBlockFromMatrix(int[][] matrix, int offset_x, int offset_y) // set the blockparts
    {
        ArrayList<BlockPart> partList = new ArrayList<>();
        
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[y].length; x++) {
                if(matrix[y][x] > 0)
                {
                    BlockPart part = new BlockPart(x + offset_x, y + offset_y);
                    partList.add(part);
                    color = matrix[y][x]; // color it like the last used value in the matrix
                }
            }
        }
        parts = new BlockPart[partList.size()];
        for (int i = 0; i < parts.length; i++) {
            parts[i] = partList.get(i);
        }
        
        SetWidthAndHeight(parts, matrix[0].length, matrix.length); // calculate the new width and height
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
        
        // also safe the offset
        offsetX = leftBorder;
        offsetY = topBorder;
    }
}
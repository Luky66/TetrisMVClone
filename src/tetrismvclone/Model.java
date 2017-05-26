
// The Model handels all the things behind the scene and is platform independent

package tetrismvclone;

import java.util.Arrays;
import tetrismvclone.blocks.Block;
import tetrismvclone.blocks.BlockPart;
import tetrismvclone.blocks.Blocks;

public class Model {
    
    // Play field
    public int fieldHeight = 22;
    public int fieldWidth = 13;
    public int fieldSpawnArea = 2; 
    
        /* The spwan area is a area at the top of the field which isn't visible for the player 
        and is where the blocks are spawned */
    
    // The play field that is filled with colours (int for colour)
    public int[][] field = new int[fieldHeight][fieldWidth];
    
    // The blocks which are falling
    public Block currentBlock;
    public Block nextBlock;
    
    // Colours
    static final int EMPTY = 0;
    static final int BLACK = 1;
    static final int CYAN = 2;
    static final int BLUE = 3;
    static final int ORANGE = 4;
    static final int YELLOW = 5;
    static final int GREEN = 6;
    static final int MAGENTA = 7;
    static final int RED = 8;
    
    
    public Model()
    {
        currentBlock = new Block();
        nextBlock = new Block();
    }
    
    public void ChangeBlock()
    {
        System.out.println("Collision detected");
        // Set the field
        AddToField(currentBlock);
        
        // Set the blocks
        currentBlock = nextBlock;
        nextBlock = new Block();
    }
    
    public void Gravity()
    {
        currentBlock.yOffset++; // lowers the current block by 1
    }
    
    // Collisions
    public void CheckForCollision()
    {
        // Field
        // Border
        for (BlockPart part : currentBlock.parts) 
        {
            if(part.y+currentBlock.yOffset+1 >= fieldHeight)
            {
                // Collision
                ChangeBlock();
            }
        }
        
    }

    private void AddToField(Block block) 
    {
        int colorInt = Arrays.asList(Blocks.colors).indexOf(currentBlock.color);
        for(BlockPart part : block.parts)
        {
            field[currentBlock.yOffset+part.y][currentBlock.xOffset+part.x] = colorInt;
        }
    }
    
    
    
}

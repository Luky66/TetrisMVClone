
// The Model handels all the things behind the scene and is platform independent

package tetrismvclone;

import java.util.Arrays;
import tetrismvclone.blocks.Block;
import tetrismvclone.blocks.BlockPart;
import tetrismvclone.blocks.Blocks;

public class Model {
    
    // Play field
    public int fieldHeight = 22; // 22
    public int fieldWidth = 10; // 10
    public int fieldSpawnArea = 2; 
    
        /* The spwan area is a area at the top of the field which isn't visible for the player 
        and is where the blocks are spawned */
    
    // Game score
    public int level;
    public int score = 0;
    public int linesCleared = 0;
    public boolean scoresChanged = false;
    
    // The play field that is filled with colours (int for colour)
    public int[][] field;
    
    // The blocks which are falling
    public Block currentBlock;
    public Block nextBlock;
    int timeAmplifier = 1;
    public boolean blockFalling = false;
    
    // Directions
    static final int LEFT = 2;
    static final int RIGHT = 0;
    static final int DOWN = 3;
    static final int ROTATE = 1;
    
    public Model(int startLevel)
    {
        this.level = startLevel;
        
        field = new int[fieldHeight][fieldWidth];
        
        currentBlock = new Block();
        nextBlock = new Block();
    }
    
    public void MoveBlock(int direction)
    {
        switch(direction)
        {
            case LEFT:
                // Check for border
                for(BlockPart part : currentBlock.parts)
                {
                    if(part.x+currentBlock.x-1 < 0)
                    {
                        return;
                    }
                }
                if(CheckForOverlapCollision(-1)) // prohibit moving into the field sideways
                {
                    return;
                }
                currentBlock.x--;
                break;
            case RIGHT:
                for(BlockPart part : currentBlock.parts)
                {
                    if(part.x+currentBlock.x+1 >= fieldWidth) 
                    {
                        return;
                    }
                }
                if(CheckForOverlapCollision(1))
                {
                    return;
                }
                currentBlock.x++;
                break;
            case ROTATE:
                // Set the block
                int startRotation = currentBlock.shape;
                currentBlock.Rotate(1);
                
                // make sure the block is in the field
                for (BlockPart part : currentBlock.parts) 
                {
                    if(part.x+currentBlock.x < 0)
                    {
                        // the block is to far left.
                        currentBlock.x++;
                    }
                    if(part.x+currentBlock.x >= fieldWidth)
                    {
                        // the block is too far right
                        currentBlock.x--;
                    }
                }
                
                // and it's not overlapping or antyhing, otherwise rotate it once more...
                while(CheckForOverlapCollision(0))
                {
                    // the block is overlapping with the field
                    
                    if(currentBlock.shape == startRotation)
                    {
                        // something is wrong, the block was rotated fully already: abort
                        break;
                    }
                    
                    // rotate the block
                    currentBlock.Rotate(1);
                }
                
                
                
                break;
            case DOWN:
                timeAmplifier = 10;
                blockFalling = true;
                break;
            default:
                System.out.println("Unknown direction int "+direction+" given.");
                break;
        }
    }
    
    public void ChangeBlock()
    {
        // reset variables
        blockFalling = false;
        timeAmplifier = 1;
        
        // Set the field
        AddToField(currentBlock);
        
        // Set the blocks
        currentBlock = nextBlock;
        currentBlock.x = 2;
        
        nextBlock = new Block();
    }
    
    public void Gravity()
    {
        currentBlock.y++; // lowers the current block by 1
    }
    
    // Collisions
    public void CheckForDownwardsCollision()
    {
        for (BlockPart part : currentBlock.parts) 
        {
            
            // Border
            if(part.y+currentBlock.y+1 >= fieldHeight)
            {
                // Collision with bottom
                ChangeBlock();
                return;
            }
            
            // Field
            if(field[part.y+currentBlock.y+1][part.x+currentBlock.x] > 0)
            {
                // Collision with field
                ChangeBlock();
                return;
            }
        }
    }
    
    private boolean CheckForOverlapCollision(int xOffset)
    {
        for (BlockPart part : currentBlock.parts) 
        {
            // Debug
            if(part.y+currentBlock.y >= fieldHeight)
            {
                return true;
            }
            
            // Field
            if(field[part.y+currentBlock.y][part.x+currentBlock.x+xOffset] > 0)
            {
                // Collision with field
                return true;
            }
        }
        return false;
    }
    
    
    public void CheckForClear()
    {
        int linesCleared = 0;
        boolean foundGapInLine = false;
        
        // search for whole lines in the field
        for (int y = 0; y < fieldHeight; y++) {
            foundGapInLine = false;
            for (int x = 0; x < fieldWidth; x++) {
                if(field[y][x] <= 0)
                {
                    // if theres a gap, stop searching
                    foundGapInLine = true;
                    break;
                }
            }
            if(foundGapInLine == false)
            {
                // the line is gap free -> clear it
                linesCleared++;
                ClearLineInField(y);
            }
        }
        
        Score(linesCleared);
    }
    
    private void ClearLineInField(int y)
    {
        // clear the line
        for (int x = 0; x < fieldWidth; x++) {
            field[y][x] = 0;
        }
        
        // push everything down
        for (int i = y-1; i > 0; i--) {
            for (int x = 0; x < fieldWidth; x++) 
            {
                field[i+1][x] = field[i][x];
            }
        }
    }
    
    public boolean CheckForGameOver()
    {
        // iterates throuh the first row and checks if any block is used.
        for (int x = 0; x < fieldWidth; x++) {
            // the visible field starts at y = 2.
            if(field[2][x] > 0)
            {
                return true;
            }
        }
        return false;
    }

    private void AddToField(Block block) 
    {
        int colorInt = Arrays.asList(Blocks.colors).indexOf(currentBlock.color);
        for(BlockPart part : block.parts)
        {
            field[currentBlock.y+part.y][currentBlock.x+part.x] = colorInt;
        }
    }

    private void Score(int lines) 
    {
        int lineMultiplier = 0;
        
        switch(lines)
        {
            case 1:
                lineMultiplier = 40;
                break;
            case 2:
                lineMultiplier = 100;
                break;
            case 3:
                lineMultiplier = 300;
                break;
            case 4:
                lineMultiplier = 1200;
                break;
        }
        score += lineMultiplier*(level+1);
        scoresChanged = true;
    }
    
    
    
}

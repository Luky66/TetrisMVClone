
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
    public int level = 0;
    public int startLevel;
    public int score = 0;
    public int lines = 0;
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
    
    // Speed
    public final int startSleepTime = 500; // in ms
    public int sleepTime;
    public final double speedValue = 0.8; // The value the time is multiplied by, by level
    
    
    public Model(int startLevel)
    {
        this.startLevel = startLevel;
        
        // The play field
        field = new int[fieldHeight][fieldWidth];
        
        // The blocks
        currentBlock = new Block();
        nextBlock = new Block();
        currentBlock.x = (int) (fieldWidth/2-(currentBlock.width+1)/2);
        
        // The time between steps
        sleepTime = startSleepTime;
    }
    
    public void MoveBlock(int direction)
    {
        // Before moving the block we make sure it can move there
        
        switch(direction)
        {
            case LEFT:
                // Check for right border
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
                // if here it didn't return, the way is free and the block may move there
                currentBlock.x--;
                break;
            case RIGHT:
                // Check for left border
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
                int startRotation = currentBlock.shape; // save the initial shape for later
                
                currentBlock.Rotate(1); // this changes the shape of the block to a block rotated 90 degrees clockwise
                
                // make sure the block is in the field
                // if it rotates out of bounds it is moved inwards.
                // we check for possible overlap with the field in a later stage
                for (BlockPart part : currentBlock.parts) 
                {
                    if(part.x+currentBlock.x < 0)
                    {
                        // the block is too far left.
                        currentBlock.x++;
                    }
                    if(part.x+currentBlock.x >= fieldWidth)
                    {
                        // the block is too far right
                        currentBlock.x--;
                    }
                }
                
                // check for overlap
                while(CheckForOverlapCollision(0))
                {
                    // the block is overlapping with the field since the statement is true
                    
                    if(currentBlock.shape == startRotation)
                    {
                        // something is wrong, the block was rotated fully already: abort
                        // the block can't rotate and therefore we are done and the shape is equals the start rotation
                        break;
                    }
                    
                    // if in this occasion the block would have been overlapping in this rotation, rotate it once more.
                    // maybe it will work next time
                    currentBlock.Rotate(1);
                }
                break;
                
            case DOWN:
                BlockFalling(true); // make the block fall faster and do everything that is needed
                break;
                
            default:
                // If an unknown direction is passed into this function, prompt it.
                System.out.println("Unknown direction int "+direction+" given.");
                break;
        }
    }
    
    public void SetAndChangeBlock() 
    // when the block has collided and should get replaced, this function is called
    {
        // reset variables
        BlockFalling(false); // stop it from falling if it was
        
        // Set the field
        AddToField(currentBlock); // add the collided block to the field
        
        // Set the blocks
        currentBlock = nextBlock; // the previewed block is now the current block
        currentBlock.x = (int) (fieldWidth/2-(currentBlock.width+1)/2); 
        // change the x position to center the block on the playfield
        // if it is uneven go one square left
        
        nextBlock = new Block(); // also add a new block which is displayed in the block preview
    }
    
    public void Gravity() // pretty self explanatory 
    {
        currentBlock.y++; // lowers the current block by 1 // as a reminder: y points downwards
    }
    
    // Collisions
    public void CheckForDownwardsCollision() // checks if the block can move straight down
    {
        for (BlockPart part : currentBlock.parts) // loops through all the parts of the block
        {
            // Border
            if(part.y+currentBlock.y+1 >= fieldHeight) // Check if it is colliding with the border
            {
                // Collision with bottom
                SetAndChangeBlock();
                return;
            }
            
            // Field
            if(field[part.y+currentBlock.y+1][part.x+currentBlock.x] > 0) // check if it is colliding with the field
            {
                // Collision with field
                SetAndChangeBlock();
                return;
            }
            // if only a single one block part is going to collide with those it will change the block.
        }
    }
    
    private boolean CheckForOverlapCollision(int xOffset)
    // xOffset is an int that can indicate where the block will be moving. 
    {
        for (BlockPart part : currentBlock.parts) 
        {
            // For safety reasons
            if(part.y+currentBlock.y >= fieldHeight)
            {
                return true;
            }
            
            // Check overlap with the field
            if(field[part.y+currentBlock.y][part.x+currentBlock.x+xOffset] > 0)
            {
                // the block is overlapping (or would be)
                return true;
            }
        }
        // no overlap detected
        return false;
    }
    
    
    public void CheckForClear()
    // in this function we check if there is a full line in the field that should be cleared
    {
        int linesCleared = 0; // indicates how many lines were cleared since the start of this function. (max 4 -> long block)
        boolean foundGapInLine; // a boolean to shorten the search
        
        // search for complete lines in the field
        for (int y = 0; y < fieldHeight; y++) {
            foundGapInLine = false;
            for (int x = 0; x < fieldWidth; x++) {
                if(field[y][x] <= 0) // if this squre in the field is empty
                {
                    // there is a gap
                    foundGapInLine = true;
                    break; // search the next row
                }
            }
            if(foundGapInLine == false)
            {
                // the line is gap free -> clear it
                linesCleared++; // add one to the lines cleared this call
                ClearLineInField(y); // and clear the line
            }
        }
        lines += linesCleared; // add the cleared lines this time to the cleared lines alltime
        
        Score(linesCleared); // Add points
        ChangeLevel(lines); // Change the level if needed and adjust the speed
    }
    
    private void ClearLineInField(int y) // clears a line in the field and adjusts all blocks above
    {
        // clear the line
        for (int x = 0; x < fieldWidth; x++) {
            field[y][x] = 0; // empty all the squares in this row
        }
        
        // push everything above down one block
        for (int i = y-1; i > 0; i--) {
            for (int x = 0; x < fieldWidth; x++) 
            {
                field[i+1][x] = field[i][x];
            }
        }
    }
    
    public boolean CheckForGameOver()
    {
        // iterates throuh the third row and checks if any square isn't empty.
        // Hint: The third row is the first row visible
        for (int x = 0; x < fieldWidth; x++) {
            // the visible field starts at y = 2
            if(field[2][x] > 0)
            {
                return true;
            }
        }
        return false;
    }

    private void AddToField(Block block) // adds a block to the playfield
    {
        int colorInt = currentBlock.color; 
        // the block has only one color and the different parts aren't colored themselfes
        for(BlockPart part : block.parts)
        {
            field[currentBlock.y+part.y][currentBlock.x+part.x] = colorInt;
        }
    }

    private void Score(int lines) // Add points to the score
    {
        int lineMultiplier = 0;
        
        // original tetris point-system for line clears
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
    
    public void BlockFalling(boolean fall) // make the block fall faster
    {
        if(fall)
        {
            blockFalling = true;
            timeAmplifier = 10; // speed the main loop up a bit
        }
        else
        {
            blockFalling = false;
            timeAmplifier = 1; // return to normal speed
        }
    }

    private void ChangeLevel(int lines) 
    // This system is my own and is therefore different from other tetris versions
    {
        // startLevel is usually 1 but the model would support a game starting at higher levels
        level = (int) (Math.floor(lines/10)+1)+(startLevel-1);
        
        // Change the speed
        sleepTime = (int) Math.round(startSleepTime * Math.pow(speedValue, level-1));
    }
    
    public void ColorField(int[][] matrix, int color) // change the color of every used square in the field
    {
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[y].length; x++) {
                if(matrix[y][x] > 0)
                {
                    // This field is not empty
                    matrix[y][x] = color;
                }
            }
        }
    }
}

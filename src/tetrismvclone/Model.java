
// The Model handels all the things behind the scene and is platform independent

package tetrismvclone;

import tetrismvclone.blocks.Block;

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
        currentBlock.xOffset = 4;
        currentBlock.yOffset = 5;
    }
    
    
    // Collisions
    
    
    
    
}

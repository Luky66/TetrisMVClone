
// The Model handels all the things behind the scene and is platform independent

package tetrismvclone;

public class Model {
    
    // Play field
    int fieldHeight = 22;
    int fieldWidth = 13;
    int fieldSpawnArea = 2; 
    
        /* The spwan area is a area at the top of the field which isn't visible for the player 
        and is where the blocks are spawned */
    
    // The play field that is filled with colours (int for colour)
    int[][] field = new int[fieldHeight][fieldWidth];
    
    
    
    // Colours
    static final int EMPTY = 0;
    static final int BLACK = 1;
    static final int BLUE = 2;
    static final int NAVY = 3;
    static final int ORANGE = 4;
    static final int YELLOW = 5;
    static final int GREEN = 6;
    static final int PURPLE = 7;
    static final int RED = 8;
    
    
    // Blocks
        // The Blocks are I,J,L,O,S,T,Z
    
    
    
    public Model()
    {
        field[5][6] = 1;
        field[6][6] = 5;
    }
    
    
    // Collisions
    
    
    
    
}

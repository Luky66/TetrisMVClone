
package tetrismvclone.blocks;

import java.awt.Color;

public class Blocks {
    
    // all the colours
    public static final Color[] colors = 
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
    
    public static int[][][][] blocks = 
    {
        { // type "I"
            { // shape 0
                {0, 0, 0, 0},
                {2, 2, 2, 2},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
            },
            { // shape 1
                {0, 1, 0, 0},
                {0, 1, 0, 0},
                {0, 1, 0, 0},
                {0, 1, 0, 0}
            }
        }
    };
    
    
    public static int[][] GetBlock(int type, int shape)
    {
        return blocks[type][shape];
    }
}

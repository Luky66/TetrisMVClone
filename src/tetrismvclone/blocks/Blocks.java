
package tetrismvclone.blocks;

import java.awt.Color;

public class Blocks {
    
    // all the colours
    public static final Color[] colors = 
    {
        Color.black,
        Color.white,
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
                {0, 2, 0, 0},
                {0, 2, 0, 0},
                {0, 2, 0, 0},
                {0, 2, 0, 0}
            }
        },
        { // type J
            { // shape 0
                {3, 0, 0},
                {3, 3, 3},
                {0, 0, 0}
            },
            { // shape 1
                {0, 3, 3},
                {0, 3, 0},
                {0, 3, 0}
            },  
            { // shape 2
                {0, 0, 0},
                {3, 3, 3},
                {0, 0, 3}
            },
            { // shape 3
                {0, 3, 0},
                {0, 3, 0},
                {3, 3, 0}
            }
        },
        { // type L
            { // shape 0
                {0, 0, 4},
                {4, 4, 4},
                {0, 0, 0}
            },
            { // shape 1
                {0, 4, 0},
                {0, 4, 0},
                {0, 4, 4}
            },  
            { // shape 2
                {0, 0, 0},
                {4, 4, 4},
                {4, 0, 0}
            },
            { // shape 3
                {4, 4, 0},
                {0, 4, 0},
                {0, 4, 0}
            }
        },
        { // type O
            { // shape 0
                {5, 5},
                {5, 5}
            }
        },
        { // type S
            { // shape 0
                {0, 6, 6},
                {6, 6, 0},
                {0, 0, 0}
            },
            { // shape 1
                {0, 6, 0},
                {0, 6, 6},
                {0, 0, 6}
            }
        },
        { // type T
            { // shape 0
                {0, 7, 0},
                {7, 7, 7},
                {0, 0, 0}
            },
            { // shape 1
                {0, 7, 0},
                {0, 7, 7},
                {0, 7, 0}
            },  
            { // shape 2
                {0, 0, 0},
                {7, 7, 7},
                {0, 7, 0}
            },
            { // shape 3
                {0, 7, 0},
                {7, 7, 0},
                {0, 7, 0}
            }
        },
        { // type Z
            { // shape 0
                {8, 8, 0},
                {0, 8, 8},
                {0, 0, 0}
            },
            { // shape 1
                {0, 0, 8},
                {0, 8, 8},
                {0, 8, 0}
            }
        }
        
    };
    
    
    public static int[][] GetBlock(int type, int shape)
    {
        return blocks[type][shape];
    }
}

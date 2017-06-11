
// Tetris for dummies
// 
// 

package tetrismvclone;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

public class Controller implements KeyListener{
    
    private final int applicationBorder;
    private final int squareSize; // The size of the squares of the blocks and the field
    // The border between the different fields displayed by the three views
    
    // Model
    private Model model;
    
    // Views
    private PlayFieldView playFieldView;
    private NextBlockPreview nextBlockPreview;
    private ScoreView scoreView;
    
    // all the colors
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
    
    // Frame
    private JFrame frame;
    
    boolean gameRunning = true;
    
    
    // The consturctor
    public Controller()
    {
        // Initiate frame
        JFrame frame = new JFrame("Tetris MVClone");
        frame.addKeyListener(this);   
       
        
        // unsure about that
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
        @Override
        public void windowClosing(java.awt.event.WindowEvent windowEvent) {
            System.out.println("Window was closed. Terminating...");
            System.exit(0); 
            }
        });
        
        model = new Model(1); // We start at level 1
        
        this.applicationBorder = 15; // set the application border
        this.squareSize = 30; // In pixels
        
        //view = new View(frame, model.fieldHeight, model.fieldSpawnArea, model.fieldWidth); // Call the controller
        playFieldView = new PlayFieldView(model.fieldHeight, model.fieldSpawnArea, model.fieldWidth, applicationBorder, squareSize, colors);
        frame.add(playFieldView);
        nextBlockPreview = new NextBlockPreview(playFieldView.getWidth()+2*applicationBorder, applicationBorder, squareSize, colors);
        frame.add(nextBlockPreview);
        scoreView = new ScoreView(playFieldView.getWidth()+2*applicationBorder, nextBlockPreview.getHeight()+2*applicationBorder, nextBlockPreview.getWidth(), playFieldView.getHeight()-applicationBorder-nextBlockPreview.getHeight());
        frame.add(scoreView);

        
        
        
        // set the size
        frame.setSize(playFieldView.getWidth()+nextBlockPreview.getWidth()+3*applicationBorder+6, playFieldView.getHeight()+2*applicationBorder+23+6);
        frame.setResizable(false);
        
        frame.setLayout(null);
        frame.setVisible(true);
        
    }
    
    public void MainLoop()
    {
        // Main Game Loop
        while(gameRunning)
        {
            // Check for stuff
            model.CheckForDownwardsCollision();
            model.CheckForClear();

            // move the block down...
            model.Gravity();
            
            // check for gameover
            if(model.CheckForGameOver())
            {
                GameOver();
                Update();
                break;
            }
            
            // update everything and sync model and view
            Update();
            
            
        
            // pause 
            for (int i = 0; i < 10/model.timeAmplifier; i++) {
                try { 
                    Thread.sleep(model.sleepTime/10); // is 500 ms without hard drop
                } catch (InterruptedException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            
        }
    }
    
    private void Update()
    {
        // update the view
        playFieldView.field = model.field;
        playFieldView.currentBlock = model.currentBlock;
        nextBlockPreview.nextBlock = model.nextBlock;
        
        //view.nextBlock = model.nextBlock;
        
        
        if(model.scoresChanged)
        {
            scoreView.score = model.score;
            scoreView.level = model.level;
            scoreView.lines = model.lines;
            
            scoreView.UpdateScore();
            model.scoresChanged = false;
        }

        // repaint
        //view.repaint();
        playFieldView.repaint();
        nextBlockPreview.repaint();
    }
    
    private void GameOver()
    {
        System.out.println("Game Over!");
        gameRunning = false;
        
        model.currentBlock = null;
        model.nextBlock.color = 1; // make it black
        
        model.ColorField(model.field, 1);
        playFieldView.ShowGameOver();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //System.out.println("key typed: "+e.getKeyChar());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //System.out.println("key pressed: "+e.getKeyChar());
        
        if(!gameRunning || model.blockFalling)
        {
            switch(e.getKeyCode())
            {
                // Rotate
                case KeyEvent.VK_UP:
                    model.BlockFalling(false);
                    return;
                case KeyEvent.VK_W:
                    model.BlockFalling(false);
                    return;
                default:
                    return;
            }
            
        }
        
        switch(e.getKeyCode())
        {
            // Left
            case KeyEvent.VK_LEFT:
                model.MoveBlock(2);
                break;
            case KeyEvent.VK_A:
                model.MoveBlock(2);
                break;
                
            // Right
            case KeyEvent.VK_RIGHT:
                model.MoveBlock(0);
                break; 
            case KeyEvent.VK_D:
                model.MoveBlock(0);
                break;
                
            // Down
            case KeyEvent.VK_DOWN:
                model.MoveBlock(3);
                break;
            case KeyEvent.VK_S:
                model.MoveBlock(3);
                break;
                
            // Rotate
            case KeyEvent.VK_UP:
                model.MoveBlock(1);
                break;
            case KeyEvent.VK_W:
                model.MoveBlock(1);
                break;
            case KeyEvent.VK_SPACE:
                model.MoveBlock(1);
                break;
        }
        playFieldView.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //System.out.println("key released: "+e.getKeyChar());
    }
}

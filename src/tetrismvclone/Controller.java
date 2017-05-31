
// Tetris for dummies
// 
// 

package tetrismvclone;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.input.KeyCode;
import javax.swing.JFrame;

public class Controller implements KeyListener{
    
    // Frame
    private int applicationBorder = 20;
    
    // Model
    private Model model;
    
    // Views
    private PlayFieldView playFieldView;
    
    private JFrame frame;
    
    boolean gameRunning = true;
    
    public Controller()
    {
        model = new Model(0);
        
        
        // Initiate frame
        JFrame frame = new JFrame("Tetris 4 Dummies");
        
        
        frame.addKeyListener(this);    
        
        // unsure about that
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
        @Override
        public void windowClosing(java.awt.event.WindowEvent windowEvent) {
            System.out.println("Window was closed. Terminating...");
            System.exit(0); 
            }
        });
        
        //view = new View(frame, model.fieldHeight, model.fieldSpawnArea, model.fieldWidth); // Call the controller
        playFieldView = new PlayFieldView(model.fieldHeight, model.fieldSpawnArea, model.fieldWidth);
        frame.add(playFieldView);
        
        // Set variables from model to view
        playFieldView.field = model.field; // set the starting field
        playFieldView.currentBlock = model.currentBlock;
        
        
        //view.nextBlock = model.nextBlock;
        
        
        
        // pack everything
        frame.setSize(playFieldView.getWidth()+2*applicationBorder, playFieldView.getHeight()+2*applicationBorder);
        //frame.pack();
        frame.setLayout(null);
        frame.setVisible(true);
        
    }
    
    public void MainLoop()
    {
        // Main Game Loop
        while(gameRunning)
        {
            // check for gameover
            if(model.CheckForGameOver())
            {
                GameOver();
                break;
            }
            
            // Check for stuff
            model.CheckForDownwardsCollision();
            model.CheckForClear();
            
            // move the block down...
            model.Gravity();
            
            // update everything and sync model and view
            Update();
            
            
        
            // pause 
            for (int i = 0; i < 10/model.timeAmplifier; i++) {
                try { 
                    Thread.sleep(50);
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
        
        //view.nextBlock = model.nextBlock;
        
        if(model.scoresChanged)
        {
            // Update UI
            //view.UpdateScores(model.score);
            model.scoresChanged = false;
        }

        // repaint
        //view.repaint();
        playFieldView.repaint();
    }
    
    private void GameOver()
    {
        System.out.println("Game Over!");
        gameRunning = false;
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
            return;
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

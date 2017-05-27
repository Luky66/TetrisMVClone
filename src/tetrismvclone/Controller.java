
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
    
    private Model model;
    private View view;
    
    private JFrame frame;
    
    boolean gameRunning = true;
    
    public Controller()
    {
        model = new Model();
        view = new View();
        
        // Set variables from model to view
        view.field = model.field; // set the starting field
        view.currentBlock = model.currentBlock;
        view.nextBlock = model.nextBlock;
        
        // Initiate frame
        JFrame frame = new JFrame("Tetris 4 Dummies");
        view.View(frame, model.fieldHeight, model.fieldSpawnArea, model.fieldWidth); // Call the controller
        
        
        frame.addKeyListener(this);    
        
        // unsure about that
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
        @Override
        public void windowClosing(java.awt.event.WindowEvent windowEvent) {
            System.out.println("Window was closed. Terminating...");
            System.exit(0); 
            }
        });
        
        
        // pack everything
        frame.pack();
        frame.setVisible(true);
    }
    
    public void MainLoop()
    {
        // Main Game Loop
        while(gameRunning)
        {
            // Check for stuff
            model.CheckForCollision();
            model.CheckForClear();
            
            // move the block down...
            model.Gravity();
            
            // update everything and sync model and view
            Update();
            
            // check for gameover
            if(model.CheckForGameOver())
            {
                GameOver();
            }
        
            // pause 
            try { 
                Thread.sleep(500/model.timeAmplifier);
            } catch (InterruptedException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
    private void Update()
    {
        // update the view
        view.field = model.field;
        view.currentBlock = model.currentBlock;
        view.nextBlock = model.nextBlock;

        // repaint
        view.repaint();
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
        
        if(!gameRunning)
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
        view.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //System.out.println("key released: "+e.getKeyChar());
        
        if(!gameRunning)
        {
            return;
        }
        
        switch(e.getKeyCode())
        {
            // Down
            case KeyEvent.VK_DOWN:
                model.timeAmplifier = 1;
                break;
            case KeyEvent.VK_S:
                model.timeAmplifier = 1;
                break;
        }
    }
}

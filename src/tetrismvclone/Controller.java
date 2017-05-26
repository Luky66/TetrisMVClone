
// Tetris for dummies
// 
// 

package tetrismvclone;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

public class Controller {
    
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
        
        // Initiate frame
        JFrame frame = new JFrame("Tetris 4 Dummies");
        view.View(frame, model.fieldHeight, model.fieldHeight-model.fieldSpawnArea, model.fieldWidth); // Call the controller
        
        
        
        // pack everything
        frame.pack();
        frame.setVisible(true);
    }
    
    public void MainLoop()
    {
        // Main Game Loop
        while(gameRunning)
        {
            model.CheckForCollision();
            
            model.Gravity();
            view.repaint();
        
            // pause to 
            try { 
                Thread.sleep(50*10);
            } catch (InterruptedException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        
    }
}

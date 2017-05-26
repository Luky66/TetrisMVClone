
// Tetris for dummies
// 
// 

package tetrismvclone;

import javax.swing.JFrame;

public class Controller {
    
    private Model model;
    private View view;
    
    private JFrame frame;
    
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
}

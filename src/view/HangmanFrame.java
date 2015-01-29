package view;
 
//import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
 
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
 
import model.HangmanModel;
 
public class HangmanFrame {
 
    private GuessPanel guessPanel;
 
    private HangmanModel model;
 
    private HangmanPanel drawingPanel;
 
    private JFrame frame;
 
    public HangmanFrame(HangmanModel model) {
        this.model = model;
        createPartControl();
    }
 
    protected void createPartControl() {
        drawingPanel = new HangmanPanel(model);
        guessPanel = new GuessPanel(this, model);
        guessPanel.setDrawingPanel(drawingPanel);
 
        frame = new JFrame();
        frame.setTitle("Central Park Team - The Hangman"); 
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                exitProcedure();
            }
        });
 
        frame.setLayout(new FlowLayout());
 
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
   
        panel.add(guessPanel.getOutsidePanel());
        panel.add(drawingPanel);
 
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }
 
    public void exitProcedure() {
        frame.dispose();
        System.exit(0);
    }
 
    public JFrame getFrame() {
        return frame;
    }
}
package view;
 
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
 
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
 
import model.HangmanModel;
 
public class GuessPanel {
 
    protected static final Insets   bottomInsets    = new Insets(10, 10, 10, 10);
    protected static final Insets   normalInsets    = new Insets(10, 10, 0, 10);
    protected static final Insets   spacingInsets   = new Insets(20, 10, 0, 10);
 
    private int                     phraseGridy;
    private HangmanFrame            frame;
    private HangmanModel            model;
    private HangmanPanel            drawingPanel;
    private JButton                 guessButton;
    private JLabel                  guessesText;
    private JLabel                  possibleLettersText;
    private JPanel                  outsidePanel;
    private JPanel                  panel;
    private JTextField              guessTextField;

    private List<JLabel>          phraseText;
    private List<String>          phrase;
 
    public GuessPanel(HangmanFrame frame, HangmanModel model) {
        this.frame = frame;
        this.model = model;
        this.phrase = new ArrayList<String>();
        this.phraseText = new ArrayList<JLabel>();
        createPartControl();
    }
 
    public void setDrawingPanel(HangmanPanel drawingPanel) {
        this.drawingPanel = drawingPanel;
    }
 
    private void createPartControl() {
        outsidePanel = new JPanel();
        outsidePanel.setLayout(new FlowLayout());
        outsidePanel.setBackground(Color.black);
 
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.black);
        panel.setForeground(Color.lightGray);
 
        int gridy = 0;
        JLabel possibleLettersTitle = new JLabel("Possible letters");
        possibleLettersTitle.setForeground(Color.lightGray);
        possibleLettersTitle.setHorizontalAlignment(JLabel.CENTER);
        addComponent(panel, possibleLettersTitle, 0, gridy++, 2, 1,
                normalInsets, GridBagConstraints.CENTER,
                GridBagConstraints.HORIZONTAL);
 
        possibleLettersText = new JLabel(model.getUnguessedLetters());
        possibleLettersText.setForeground(Color.lightGray);
        possibleLettersText.setHorizontalAlignment(JLabel.CENTER);
        addComponent(panel, possibleLettersText, 0, gridy++, 2, 1,
                normalInsets, GridBagConstraints.CENTER,
                GridBagConstraints.HORIZONTAL);
 
        JLabel guessesTitle = new JLabel("Number of guesses");
        guessesTitle.setForeground(Color.lightGray);
        guessesTitle.setHorizontalAlignment(JLabel.CENTER);
        addComponent(panel, guessesTitle, 0, gridy++, 2, 1, spacingInsets,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL);
 
        guessesText = new JLabel(model.getNumberOfGuesses());
        guessesText.setForeground(Color.lightGray);
        guessesText.setHorizontalAlignment(JLabel.CENTER);
        addComponent(panel, guessesText, 0, gridy++, 2, 1, normalInsets,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL);
 
        JLabel guessLetterTitle = new JLabel("Guess letter");
        guessLetterTitle.setForeground(Color.lightGray);
        guessLetterTitle.setHorizontalAlignment(JLabel.CENTER);
        addComponent(panel, guessLetterTitle, 0, gridy++, 2, 1, spacingInsets,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL);
 
        JPanel guessPanel = new JPanel();
        guessPanel.setBackground(Color.black); 
        guessPanel.setLayout(new BoxLayout(guessPanel, BoxLayout.X_AXIS));
 
        guessTextField = new JTextField(10);
        guessTextField.setForeground(Color.lightGray);
        guessTextField.setBackground(Color.black);
        guessPanel.add(guessTextField);
 
        guessPanel.add(Box.createRigidArea(new Dimension(6, 0)));
 
        GuessActionListener listener = new GuessActionListener();
 
        guessButton = new JButton("Guess");
        guessButton.setForeground(Color.lightGray);
        guessButton.setBackground(Color.black);
        guessButton.addActionListener(listener);
        guessButton.registerKeyboardAction(guessButton
                .getActionForKeyStroke(KeyStroke.getKeyStroke(
                        KeyEvent.VK_SPACE, 0, false)), KeyStroke.getKeyStroke(
                KeyEvent.VK_ENTER, 0, false), JButton.WHEN_IN_FOCUSED_WINDOW);
        guessButton.registerKeyboardAction(guessButton
                .getActionForKeyStroke(KeyStroke.getKeyStroke(
                        KeyEvent.VK_SPACE, 0, true)), KeyStroke.getKeyStroke(
                KeyEvent.VK_ENTER, 0, true), JButton.WHEN_IN_FOCUSED_WINDOW);
        guessPanel.add(guessButton);
 
        addComponent(panel, guessPanel, 0, gridy++, 2, 1, spacingInsets,
                GridBagConstraints.CENTER, GridBagConstraints.NONE);
 
        phraseGridy = gridy;
 
        createPhraseControl(panel, gridy);
 
        outsidePanel.add(panel);
    }
 
    public void updatePartControl() {
        possibleLettersText.setText(model.getUnguessedLetters());
        guessesText.setText(model.getNumberOfGuesses());
 
        phrase = model.getHiddenPhrase();
        for (int i = 0; i < phrase.size(); i++) {
            JLabel label = phraseText.get(i);
            label.setText(phrase.get(i));
        }
    }
 
    public void resetPartControl() {
        possibleLettersText.setText(model.getUnguessedLetters());
        guessesText.setText(model.getNumberOfGuesses());
 
        removePhraseControl(panel);
        int gridy = phraseGridy;
        createPhraseControl(panel, gridy);
        panel.validate();
    }
 
    private void removePhraseControl(JPanel panel) {
        for (int i = phraseText.size() - 1; i >= 0; i--) {
            JLabel label = phraseText.get(i);
            panel.remove(label);
            phraseText.remove(i);
        }
    }
 
    private void createPhraseControl(JPanel panel, int gridy) {
        phrase = model.getHiddenPhrase();
        for (int i = 0; i < phrase.size(); i++) {
            JLabel label = new JLabel(phrase.get(i));
            Font newFont = new Font("Courier New", Font.BOLD, 24);
            label.setFont(newFont);
            label.setHorizontalAlignment(JLabel.CENTER);
            addComponent(panel, label, 0, gridy++, 2, 1, bottomInsets,
            GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL);
            phraseText.add(label);
        }
    }
 
    private void addComponent(Container container, Component component,
            int gridx, int gridy, int gridwidth, int gridheight, Insets insets,
            int anchor, int fill) {
        GridBagConstraints gbc = new GridBagConstraints(gridx, gridy,
                gridwidth, gridheight, 1.0D, 1.0D, anchor, fill, insets, 0, 0);
        container.add(component, gbc);
    }
 
    public JPanel getOutsidePanel() {
    	return outsidePanel;
    }
 
    public class GuessActionListener implements ActionListener {
 
        @Override
        public void actionPerformed(ActionEvent event) {
            String letter = guessTextField.getText();
            if ((letter.length() == 1) && (model.isPossibleLetter(letter))) {
                model.guessLetter(letter);
                updatePartControl();
                drawingPanel.repaint();
                guessTextField.setText("");
                guessTextField.requestFocusInWindow();
                
            	if (model.isDead()) {
                	JOptionPane.showMessageDialog(getOutsidePanel(),
                            "You died.  The phrase was\n"
                                    + model.getCurrentPhrase(),
                            "Death", JOptionPane.ERROR_MESSAGE);
                    init();
                } else if (model.isSolved()) {
                   JOptionPane.showMessageDialog(getOutsidePanel(),
                            "You guessed the phrase!\n"
                                    + model.getCurrentPhrase(),
                            "Congratulations", JOptionPane.INFORMATION_MESSAGE);
                    init();
                }
            } else {
                guessTextField.requestFocusInWindow();
            }
        }
 
        private void init() {
            model.init();
            resetPartControl();
            frame.getFrame().pack();
        }
    }
 }
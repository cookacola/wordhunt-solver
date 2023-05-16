import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class SingleCharacterTextField extends JTextField {

    public SingleCharacterTextField() {
        addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                handleKeyTyped(evt);
            }
        });
        setHorizontalAlignment(JTextField.CENTER);
    }

    private void handleKeyTyped(KeyEvent evt) {
        char inputChar = evt.getKeyChar();

        if (inputChar == KeyEvent.VK_ENTER) {
            Grid.printWords();
            evt.consume();
            return;
        }

        if (inputChar == KeyEvent.VK_BACK_SPACE) {
            evt.consume();
            transferFocusBackward();
            return;
        }

        char uppercaseChar = Character.toUpperCase(inputChar);
        setText(String.valueOf(uppercaseChar));
        transferFocus();
        evt.consume();

        // Check if the current text field is the last one
        if (evt.getComponent() == Grid.textFields[3][3]) {
            KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
            manager.focusNextComponent(Grid.textFields[0][0]); // Transfer focus to the first text field
        }
    }



}

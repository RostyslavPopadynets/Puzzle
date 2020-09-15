package pazzle;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Collections;
import java.util.List;

import javax.swing.*;

public class ClickAction extends AbstractAction {

    private List<PazzleButton> buttons;
    private JPanel panel;
    private ComparingImages comparingImages;

    ClickAction(List<PazzleButton> buttons, JPanel panel) {
        this.buttons = buttons;
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PazzleButton button = (PazzleButton) e.getSource();
        button.setVisible(false);
        buttons.set(buttons.indexOf(button), button);
        checkButton(button);
        comparingImages = new ComparingImages(buttons);
        checkSolution();
    }

    private void checkButton(PazzleButton buttonSource) {
        for (PazzleButton button : buttons) {
            if (!button.isVisible() && !buttonSource.equals(button)) {
                button.setVisible(true);
                buttonSource.setVisible(true);
                Collections.swap(buttons, buttons.indexOf(button), buttons.indexOf(buttonSource));
                updateButtons();
            }
        }
    }

    void draft() {
        comparingImages = new ComparingImages(buttons);
        buttons = comparingImages.compare();
        updateButtons();
    }

    private void updateButtons() {
        panel.removeAll();
        for (JComponent btn : buttons) {
            panel.add(btn);
        }
        panel.validate();
    }

    private void checkSolution() {
        if (checkFinish() && buttonsIsVisible()) {
            JOptionPane.showMessageDialog(panel, "Finished! You win!",
                    "Congratulation", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private boolean checkFinish() {
        return comparingImages.checkCorrect();
    }

    private boolean buttonsIsVisible() {
        return buttons.stream().allMatch(Component::isVisible);
    }
}
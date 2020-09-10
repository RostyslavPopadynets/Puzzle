package pazzle;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.*;

public class ClickAction extends AbstractAction {

    private List<PazzleButton> buttons;
    private JPanel panel;
    private List<Point> solution;

    ClickAction(List<PazzleButton> buttons, JPanel panel, List<Point> solution) {
        this.buttons = buttons;
        this.panel = panel;
        this.solution = solution;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PazzleButton button = (PazzleButton) e.getSource();
        button.setVisible(false);
        buttons.set(buttons.indexOf(button), button);
        checkButton(button);
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
        for (int i = 0; i < solution.size(); i++) {
            for (int j = 0; j < getCurrentList().size(); j++) {
                if (solution.get(i).equals(getCurrentList().get(j))) {
                    Collections.swap(buttons, j, i);
                    updateButtons();
                    if (checkSolution())
                        return;
                }
            }
        }
    }

    private void updateButtons() {
        panel.removeAll();
        for (JComponent btn : buttons) {
            panel.add(btn);
        }
        panel.validate();
    }

    private boolean checkSolution() {
        if (compareList(solution, getCurrentList())) {
            JOptionPane.showMessageDialog(panel, "Finished! You win!",
                    "Congratulation", JOptionPane.INFORMATION_MESSAGE);
            return true;
        }
        return false;
    }

    private List getCurrentList() {
        return buttons
                .stream()
                .map(button -> button.getClientProperty("position"))
                .collect(Collectors.toList());
    }

    private boolean buttonsIsVisible() {
        return buttons.stream().allMatch(Component::isVisible);
    }

    private boolean compareList(List ls1, List ls2) {
        return !buttonsIsVisible()
                ? buttonsIsVisible()
                : ls1.toString().contentEquals(ls2.toString());
    }
}
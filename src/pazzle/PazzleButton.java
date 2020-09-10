package pazzle;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

class PazzleButton extends JButton {

    PazzleButton(Image image) {
        super(new ImageIcon(image));
        initUI();
    }

    PazzleButton(String text) {
        super(text);
    }

    private void initUI() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBorder(BorderFactory.createLineBorder(Color.GREEN));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBorder(BorderFactory.createLineBorder(Color.BLACK));
            }
        });
    }
}
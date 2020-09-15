package pazzle;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.*;

class PazzleButton extends JButton {

    private BufferedImage bufferedImage;

    PazzleButton(Image image, BufferedImage bufferedImage) {
        super(new ImageIcon(image));
        this.bufferedImage = bufferedImage;
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

    BufferedImage getBufferedImage() {
        return bufferedImage;
    }
}
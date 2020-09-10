package pazzle;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;

class PuzzlePage extends JFrame {

    private BufferedImage resized;
    private final int DESIRED_WIDTH = 800;

    PuzzlePage() {
        initUI();
    }

    private void initUI() {
        List<Point> solution = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                solution.add(new Point(i, j));
            }
        }

        List<PazzleButton> buttons = new ArrayList<>();
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.setLayout(new GridLayout(4, 3, 1, 1));

        try {
            BufferedImage source = loadImage();
            int h = getNewHeight(source.getWidth(), source.getHeight());
            resized = resizeImage(source, h);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Could not load image", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

        int width = resized.getWidth(null);
        int height = resized.getHeight(null);
        PazzleButton auto = new PazzleButton("Automatic assembly of puzzles");
        auto.addActionListener(e -> autoDrafting(buttons, panel, solution));
        add(auto, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                Image image = createImage(new FilteredImageSource(resized.getSource(),
                        new CropImageFilter(j * width / 3, i * height / 4,
                                (width / 3), height / 4)));
                PazzleButton button = new PazzleButton(image);
                button.putClientProperty("position", new Point(i, j));
                buttons.add(button);
            }
        }

        Collections.shuffle(buttons);

        int NUMBER_OF_BUTTONS = 12;
        for (int i = 0; i < NUMBER_OF_BUTTONS; i++) {
            PazzleButton btn = buttons.get(i);
            panel.add(btn);
            btn.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            btn.addActionListener(new ClickAction(buttons, panel, solution));
        }

        setTitle("Puzzle");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void autoDrafting(List<PazzleButton> buttons, JPanel panel, List<Point> solution) {
        new ClickAction(buttons, panel, solution).draft();
    }

    private int getNewHeight(int width, int height) {
        double ratio = DESIRED_WIDTH / (double) width;
        return (int) (height * ratio);
    }

    private BufferedImage loadImage() throws IOException {
        return ImageIO.read(new File("photos/devcom.png"));
    }

    private BufferedImage resizeImage(BufferedImage originalImage,
                                      int height) {
        BufferedImage resizedImage = new BufferedImage(DESIRED_WIDTH, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = resizedImage.createGraphics();
        graphics.drawImage(originalImage, 0, 0, DESIRED_WIDTH, height, null);
        graphics.dispose();
        return resizedImage;
    }
}
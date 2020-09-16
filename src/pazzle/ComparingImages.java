package pazzle;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

class ComparingImages {

    private List<PazzleButton> buttons;
    private int width;
    private int height;
    private List<Map<String, List<Color>>> listRGBOfButtons;

    ComparingImages(List<PazzleButton> buttons) {
        this.buttons = buttons;
        this.width = buttons.get(0).getWidth();
        this.height = buttons.get(0).getHeight();
    }

    List<PazzleButton> compare() {
        updateRGB();
        autoDraw();
        return buttons;
    }

    private void autoDraw() {
        for (int i = 0; i < 4; i++) {
            autoDraft();
        }
        swapBottomElements();
    }

    private void updateRGB() {
        listRGBOfButtons = new ArrayList<>();
        for (PazzleButton button : buttons) {
            listRGBOfButtons.add(getRgb(button.getBufferedImage()));
        }
    }

    private void autoDraft() {
        List<Integer> middleElements = get2MiddleElements();
        if (!middleElements.isEmpty() && middleElements.size() == 2) {
            if (comparePixels(listRGBOfButtons.get(middleElements.get(0)).get("top"),
                    listRGBOfButtons.get(middleElements.get(1)).get("bottom"))) {
                if (middleElements.get(0) == 4 &&
                        middleElements.get(1) == 7) {
                    Collections.swap(buttons, 4, 7);
                }
                Collections.swap(buttons, 4, middleElements.get(1));
                Collections.swap(buttons, 7, middleElements.get(0));
            } else {
                Collections.swap(buttons, 7, middleElements.get(1));
                Collections.swap(buttons, 4, middleElements.get(0));
            }
            updateRGB();
            swapMiddleTopElement();
            swapTopLeftElement();
            swapTopRightElement();
            swapMiddleLeftElement1();
            swapMiddleLeftElement2();
            swapMiddleRightElement1();
            swapMiddleRightElement2();
        }
    }

    private void swapBottomElements() {
        int [] arr = {9,10,11};
        while (!correctBottomElements()) {
            int i1 = new Random().nextInt(3);
            int i2 = new Random().nextInt(3);
            Collections.swap(buttons, arr[i1], arr[i2]);
            updateRGB();
        }
    }

    private boolean correctBottomElements() {
        return comparePixels(listRGBOfButtons.get(9).get("right"),
                listRGBOfButtons.get(10).get("left")) &&
                comparePixels(listRGBOfButtons.get(10).get("right"),
                        listRGBOfButtons.get(11).get("left")) &&
                comparePixels(listRGBOfButtons.get(9).get("top"),
                        listRGBOfButtons.get(6).get("bottom")) &&
                comparePixels(listRGBOfButtons.get(10).get("top"),
                        listRGBOfButtons.get(7).get("bottom")) &&
                comparePixels(listRGBOfButtons.get(11).get("top"),
                        listRGBOfButtons.get(8).get("bottom"));
    }

    private void swapMiddleRightElement1() {
        for (int i = 0; i < listRGBOfButtons.size(); i++) {
            int t = 0;
            for (int j = 0; j < listRGBOfButtons.size(); j++) {
                if (i != j) {
                    if (i != 7 && i != 4 && i != 1 && i != 2 && i != 6 && i != 0 && i != 3 && comparePixels(listRGBOfButtons.get(i).get("left"),
                            listRGBOfButtons.get(4).get("right")) &&
                            comparePixels(listRGBOfButtons.get(i).get("top"),
                                    listRGBOfButtons.get(2).get("bottom"))) {
                        t++;
                    }
                }
            }
            if (t > 0) {
                Collections.swap(buttons, 5, i);
                updateRGB();
                return;
            }
        }
    }

    private void swapMiddleRightElement2() {
        for (int i = 0; i < listRGBOfButtons.size(); i++) {
            int t = 0;
            for (int j = 0; j < listRGBOfButtons.size(); j++) {
                if (i != j) {
                    if (i != 7 && i != 4 && i != 1 && i != 5 && i != 2 && i != 6 && i != 0 && i != 3 && comparePixels(listRGBOfButtons.get(i).get("left"),
                            listRGBOfButtons.get(7).get("right")) &&
                            comparePixels(listRGBOfButtons.get(i).get("top"),
                                    listRGBOfButtons.get(5).get("bottom"))) {
                        t++;
                    }
                }
            }
            if (t > 0) {
                Collections.swap(buttons, 8, i);
                updateRGB();
                return;
            }
        }
    }

    private void swapMiddleLeftElement1() {
        for (int i = 0; i < listRGBOfButtons.size(); i++) {
            int t = 0;
            for (int j = 0; j < listRGBOfButtons.size(); j++) {
                if (i != j) {
                    if (i != 7 && i != 4 && i != 1 && i != 0 && i != 3 && comparePixels(listRGBOfButtons.get(i).get("right"),
                            listRGBOfButtons.get(4).get("left")) &&
                            comparePixels(listRGBOfButtons.get(i).get("top"),
                                    listRGBOfButtons.get(0).get("bottom"))) {
                        t++;
                    }
                }
            }
            if (t > 0) {
                Collections.swap(buttons, 3, i);
                updateRGB();
                return;
            }
        }
    }

    private void swapMiddleLeftElement2() {
        for (int i = 0; i < listRGBOfButtons.size(); i++) {
            int p = 0;
            for (int j = 0; j < listRGBOfButtons.size(); j++) {
                if (i != j) {
                    if (i != 7 && i != 4 && i != 1 && i != 0 && i != 3 && comparePixels(listRGBOfButtons.get(i).get("right"),
                            listRGBOfButtons.get(7).get("left")) &&
                            comparePixels(listRGBOfButtons.get(i).get("top"),
                                    listRGBOfButtons.get(3).get("bottom"))) {
                        p++;
                    }
                }
            }
            if (p > 0) {
                Collections.swap(buttons, 6, i);
                updateRGB();
                return;
            }
        }
    }

    private void swapTopRightElement() {
        for (int i = 0; i < listRGBOfButtons.size(); i++) {
            int t = 0;
            for (int j = 0; j < listRGBOfButtons.size(); j++) {
                if (i != j) {
                    if (i != 7 && i != 4 && i != 1 && i != 0 && comparePixels(listRGBOfButtons.get(i).get("left"),
                            listRGBOfButtons.get(1).get("right"))) {
                        t++;
                    }
                }
            }
            if (t > 0) {
                Collections.swap(buttons, 2, i);
                updateRGB();
                return;
            }
        }
    }

    private void swapTopLeftElement() {
        for (int i = 0; i < listRGBOfButtons.size(); i++) {
            int t = 0;
            for (int j = 0; j < listRGBOfButtons.size(); j++) {
                if (i != j) {
                    if (i != 7 && i != 4 && i != 1 && comparePixels(listRGBOfButtons.get(i).get("right"),
                            listRGBOfButtons.get(1).get("left"))) {
                        t++;
                    }
                }
            }
            if (t > 0) {
                Collections.swap(buttons, 0, i);
                updateRGB();
                return;
            }
        }
    }

    private void swapMiddleTopElement() {
        for (int i = 0; i < listRGBOfButtons.size(); i++) {
            int t = 0;
            for (int j = 0; j < listRGBOfButtons.size(); j++) {
                if (i != j) {
                    if (i != 7 && comparePixels(listRGBOfButtons.get(i).get("bottom"),
                            listRGBOfButtons.get(4).get("top"))) {
                        t++;
                    }
                }
            }
            if (t > 0) {
                Collections.swap(buttons, 1, i);
                updateRGB();
                return;
            }
        }
    }

    private List<Integer> get2MiddleElements() {
        List<Integer> middleElements = new ArrayList<>();
        for (int i = 0; i < listRGBOfButtons.size(); i++) {
            int left = 0;
            int right = 0;
            int bottom = 0;
            int top = 0;
            for (int j = 0; j < listRGBOfButtons.size(); j++) {
                if (i != j) {
                    if (comparePixels(listRGBOfButtons.get(i).get("right"),
                            listRGBOfButtons.get(j).get("left"))) {
                        left++;
                    }
                    if (comparePixels(listRGBOfButtons.get(i).get("left"),
                            listRGBOfButtons.get(j).get("right"))) {
                        right++;
                    }
                    if (comparePixels(listRGBOfButtons.get(i).get("top"),
                            listRGBOfButtons.get(j).get("bottom"))) {
                        bottom++;
                    }
                    if (comparePixels(listRGBOfButtons.get(i).get("bottom"),
                            listRGBOfButtons.get(j).get("top"))) {
                        top++;
                    }
                }
            }
            if (left == 1 && right == 1 && bottom == 1 && top >= 1) {
                middleElements.add(i);
            }
            if (left == 1 && right == 1 && bottom == 7 && top == 1) {
                middleElements.add(i);
            }
        }
        return middleElements;
    }

    boolean checkCorrect() {
        List<Map<String, List<Color>>> listRGBOfButtons = new ArrayList<>();
        for (PazzleButton button : buttons) {
            listRGBOfButtons.add(getRgb(button.getBufferedImage()));
        }
        return comparePixels(listRGBOfButtons.get(0).get("right"),
                listRGBOfButtons.get(1).get("left")) &&
                comparePixels(listRGBOfButtons.get(1).get("right"),
                        listRGBOfButtons.get(2).get("left")) &&
                comparePixels(listRGBOfButtons.get(3).get("right"),
                        listRGBOfButtons.get(4).get("left")) &&
                comparePixels(listRGBOfButtons.get(4).get("right"),
                        listRGBOfButtons.get(5).get("left")) &&
                comparePixels(listRGBOfButtons.get(6).get("right"),
                        listRGBOfButtons.get(7).get("left")) &&
                comparePixels(listRGBOfButtons.get(7).get("right"),
                        listRGBOfButtons.get(8).get("left")) &&
                comparePixels(listRGBOfButtons.get(9).get("right"),
                        listRGBOfButtons.get(10).get("left")) &&
                comparePixels(listRGBOfButtons.get(10).get("right"),
                        listRGBOfButtons.get(11).get("left")) &&
                comparePixels(listRGBOfButtons.get(0).get("bottom"),
                        listRGBOfButtons.get(3).get("top")) &&
                comparePixels(listRGBOfButtons.get(1).get("bottom"),
                        listRGBOfButtons.get(4).get("top")) &&
                comparePixels(listRGBOfButtons.get(2).get("bottom"),
                        listRGBOfButtons.get(5).get("top")) &&
                comparePixels(listRGBOfButtons.get(3).get("bottom"),
                        listRGBOfButtons.get(6).get("top")) &&
                comparePixels(listRGBOfButtons.get(4).get("bottom"),
                        listRGBOfButtons.get(7).get("top")) &&
                comparePixels(listRGBOfButtons.get(5).get("bottom"),
                        listRGBOfButtons.get(8).get("top")) &&
                comparePixels(listRGBOfButtons.get(6).get("bottom"),
                        listRGBOfButtons.get(9).get("top")) &&
                comparePixels(listRGBOfButtons.get(7).get("bottom"),
                        listRGBOfButtons.get(10).get("top")) &&
                comparePixels(listRGBOfButtons.get(8).get("bottom"),
                        listRGBOfButtons.get(11).get("top"));
    }

    private boolean comparePixels(List<Color> side1, List<Color> side2) {
        int samePixels = 0;
        if (side1.size() == side2.size()) {
            for (int i = 0; i < side1.size(); i++) {
                if (side1.get(i).equals(side2.get(i))) {
                    samePixels += 1;
                }
            }
        }
        return (double) samePixels / side1.size() > 0.9;
    }

    private Map<String, List<Color>> getRgb(BufferedImage image) {
        Map<String, List<Color>> sidesRGB = new HashMap<>();
        List<Color> top = new ArrayList<>(); // зліва напрова
        List<Color> bottom = new ArrayList<>(); // зліва напрова
        List<Color> left = new ArrayList<>(); // зверху донизу
        List<Color> right = new ArrayList<>(); // зверху донизу
        for (int i = 0; i < height; i++) {
            int pixel = image.getRGB(0, i);
            left.add(new Color(pixel, false));
        }
        for (int i = 0; i < height; i++) {
            int pixel = image.getRGB(width, i);
            right.add(new Color(pixel, false));
        }
        for (int i = 0; i < width; i++) {
            int pixel = image.getRGB(i, 0);
            top.add(new Color(pixel, false));
        }
        for (int i = 0; i < width; i++) {
            int pixel = image.getRGB(i, height);
            bottom.add(new Color(pixel, false));
        }
        sidesRGB.put("top", top);
        sidesRGB.put("bottom", bottom);
        sidesRGB.put("left", left);
        sidesRGB.put("right", right);
        return sidesRGB;
    }

}
package myself.code.main_graphics;

import myself.code.inputs.KeyHandler;
import myself.code.main_graphics.base_objects.SimpleGraphicsObject;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    public static final int CHAR_SCALE = 3;

    public static final int CHAR_WIDTH_RAW = 5;
    public static final int CHAR_HEIGHT_RAW = 6;

    public static final int CHAR_WIDTH= CHAR_WIDTH_RAW * CHAR_SCALE;
    public static final int CHAR_HEIGHT= CHAR_HEIGHT_RAW * CHAR_SCALE;

    public static final int SCREEN_WIDTH = 768;
    public static final int SCREEN_HEIGHT = 576;
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    public GamePanel () {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread () {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run () {
        double drawInterval = (double) 1000000000 /60;
        double nextDrawTime = System.nanoTime() + drawInterval;
        while (gameThread != null) {

            update();
            repaint();

            double remainingTime = (nextDrawTime - System.nanoTime())/1000000;
            if (remainingTime < 0) {
                remainingTime = 0;
            }
            try {
                Thread.sleep((long) remainingTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            nextDrawTime = System.nanoTime() + drawInterval;
        }
    }
    public void update () {

    }

    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        char[][] arrayOfChars = {{'A', 'A', 'A'}, {138, 132, 137}, {'/', '|', '\\'}};
        char[][] lineOfChars = {{'A', 'A', 'A', 'A', 'A'}, {'A', 'A', 'A', 'A', 'A'}, {'A', 'A', 'A', 'A', 'A'}, {'A', 'A', 'A', 'A', 'A'}};
        LettersDecoder decoder = new LettersDecoder(13, 8, "src/myself/resources/letters/letters.png");
        Graphics2D g2 = (Graphics2D)g;

        GraphicsLayer layer = new GraphicsLayer(6, 6);
        GraphicsLayer.setGridSize(3 * CHAR_HEIGHT);

        //decoder.printAllChars(lineOfChars, 0, 0, g2);

        SimpleGraphicsObject ranObj = new SimpleGraphicsObject(0, 0, layer, arrayOfChars).setToLayer();
        SimpleGraphicsObject ranObj1 = new SimpleGraphicsObject(0, 1, layer, arrayOfChars).setToLayer();
        SimpleGraphicsObject ranObj2 = new SimpleGraphicsObject(0, 2, layer, arrayOfChars).setToLayer();
        SimpleGraphicsObject ranObj3 = new SimpleGraphicsObject(0, 3, layer, arrayOfChars).setToLayer();
        SimpleGraphicsObject ranObj5 = new SimpleGraphicsObject(1, 0, layer, arrayOfChars).setToLayer();
        SimpleGraphicsObject ranObj6 = new SimpleGraphicsObject(2, 0, layer, arrayOfChars).setToLayer();
        SimpleGraphicsObject ranObj7 = new SimpleGraphicsObject(3, 1, layer, arrayOfChars).setToLayer();
        SimpleGraphicsObject ranObj8 = new SimpleGraphicsObject(3, 2, layer, arrayOfChars).setToLayer();
        decoder.printLayer(layer, g2);

        g2.dispose();
    }
}

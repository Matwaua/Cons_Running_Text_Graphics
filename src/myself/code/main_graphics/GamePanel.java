package myself.code.main_graphics;

import myself.code.inputs.KeyHandler;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GamePanel extends JPanel implements Runnable {
    public static final int CHAR_SCALE = 10;

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
        char[][] arrayOfChars = {{'A', 'A', 'A'}, {98, 100, 98}, {'/', '|', '\\'}};
        LettersDecoder decoder = new LettersDecoder(13, 8, "src/myself/resources/letters/letters.png");
        Graphics2D g2 = (Graphics2D)g;
        decoder.printAllChars(arrayOfChars, 5, 5, g2);
        g2.dispose();
    }
}

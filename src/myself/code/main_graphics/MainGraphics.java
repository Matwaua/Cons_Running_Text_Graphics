package myself.code.main_graphics;

import javax.swing.*;

public class MainGraphics {
    public static void main(String[] args) {
        JFrame window = new JFrame("A new start");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        myself.code.main_graphics.GamePanel gamePanel = new myself.code.main_graphics.GamePanel();
        window.add(gamePanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();
    }
}

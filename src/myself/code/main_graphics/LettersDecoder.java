package myself.code.main_graphics;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static myself.code.main_graphics.GamePanel.*;

public class LettersDecoder {
    private BufferedImage[] allCharSprites;
    public LettersDecoder (int charsPerRow, int charsPerColum, String pathToCharSprites) {
        allCharSprites = new BufferedImage[charsPerColum * charsPerRow];
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(pathToCharSprites));
        } catch (IOException e) {
            System.err.println("could not find the file: " + e);
            return;
        }

        for (int i = 0; i < charsPerColum; i++) {
            for (int j = 0; j < charsPerRow; j++) {
                allCharSprites[i*charsPerRow + j] = image.getSubimage(j * CHAR_WIDTH_RAW, i * CHAR_HEIGHT_RAW, CHAR_WIDTH_RAW, CHAR_HEIGHT_RAW);
            }
        }
    }

    public BufferedImage getCharSprite (char wantedChar) {
        return allCharSprites[wantedChar - 33];
    }

    public void printAllChars (char[][] charsToPrint, int x, int y, Graphics2D g2) {
        for (int i = 0; i < charsToPrint.length; i++) {
            for (int j = 0; j < charsToPrint[i].length; j++) {
                try {
                    g2.drawImage(getCharSprite(charsToPrint[i][j]), x + (j * CHAR_WIDTH), y + (i * CHAR_HEIGHT), CHAR_WIDTH, CHAR_HEIGHT, null);
                } catch (ArrayIndexOutOfBoundsException ignored) {}
            }
        }
    }
}
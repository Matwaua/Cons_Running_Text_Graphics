package myself.code.main_graphics.base_objects;

import myself.code.main_graphics.GraphicsLayer;

import static myself.code.main_graphics.GamePanel.CHAR_WIDTH_RAW;

public class SimpleGraphicsObject {
    int x;
    int y;
    int width;
    int height;
    GraphicsLayer graphicsLayer;
    boolean active;

    char[][] texture;

    public SimpleGraphicsObject(int gridX, int gridY, GraphicsLayer graphicsLayer, char[][] texture) {
        this.x = gridX;
        this.y = gridY;

        this.graphicsLayer = graphicsLayer;
        this.active = true;
        this.texture = texture;

        height = texture.length * CHAR_WIDTH_RAW / GraphicsLayer.getGridSizeX();
        int last = 0;
        for (char[] rows : texture) {
            last = Math.max(rows.length * CHAR_WIDTH_RAW / GraphicsLayer.getGridSizeY(), last);
        }
        width = last;

    }

    public SimpleGraphicsObject setToLayer () {
        graphicsLayer.addToLayerByGridSize(x, y, this);
        return this;
    }

    public SimpleGraphicsObject() {
        this.x = 0;
        this.y = 0;
        this.width = 1;
        this.height = 1;
        this.graphicsLayer = null;
        this.active = false;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public GraphicsLayer getGraphicsLayer() {
        return graphicsLayer;
    }

    public void setGraphicsLayer(GraphicsLayer graphicsLayer2) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                graphicsLayer.addToLayerByGridSize(x + j, y + i, null);
                graphicsLayer2.addToLayerByPixelSize(x + j, y + i, this);
                graphicsLayer = graphicsLayer2;
            }
        }
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void invertActiveness() {
        this.active = !active;
    }

    public char[][] getTexture() {
        return texture;
    }

    public char[][] getSubTexture(int pixelX, int pixelY, int width, int height) {
        char [][] subTexture = new char[width][height];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                try {
                    subTexture[j][i] = texture[x + j][y + i];
                } catch (ArrayIndexOutOfBoundsException ignored) {}
            }
        }
        return subTexture;
    }

    public void setTexture(char[][] texture2) {
        this.texture = texture2;

        height = texture.length * CHAR_WIDTH_RAW / GraphicsLayer.getGridSizeX();
        int last = 0;
        for (char[] rows : texture) {
            last = Math.max(rows.length * CHAR_WIDTH_RAW / GraphicsLayer.getGridSizeY(), last);
        }
        width = last;
    }
}

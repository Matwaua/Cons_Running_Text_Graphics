package myself.code.main_graphics;

public class SimpleGraphicsObject {
    private int x;
    private int y;
    private GraphicsLayer graphicsLayer;
    private boolean active;

    private char[][] texture;

    public SimpleGraphicsObject(int gridX, int gridY, GraphicsLayer graphicsLayer, char[][] texture) {
        this.x = gridX * graphicsLayer.getGridSize();
        this.y = gridY * graphicsLayer.getGridSize();
        this.graphicsLayer = graphicsLayer;
        this.active = true;
        this.texture = texture;

        graphicsLayer.addToLayerByGridSize(gridX, gridY, this);
    }

    public SimpleGraphicsObject() {
        this.x = 0;
        this.y = 0;
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

    public void setGraphicsLayer(GraphicsLayer graphicsLayer) {
        graphicsLayer.addToLayerByPixelSize(x, y, null);
        this.graphicsLayer = graphicsLayer;
        graphicsLayer.addToLayerByPixelSize(x, y, this);
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

    public char[][] getSubTexture(int x, int y, int width, int height) {
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

    public void setTexture(char[][] texture) {
        this.texture = texture;
    }
}

package myself.code.main_graphics;

import myself.code.main_graphics.base_objects.SimpleGraphicsObject;

import static myself.code.main_graphics.GamePanel.*;

public class GraphicsLayer {
    private SimpleGraphicsObject[][] layerObjs;
    private static int gridSizeX;
    private static int gridSizeY;

    public GraphicsLayer(int layerSizeX, int layerSizeY) {
        this.layerObjs = new SimpleGraphicsObject[layerSizeX][layerSizeY];
    }

    public void addToLayerByGridSize (int layerX, int layerY, SimpleGraphicsObject obj) {
        layerObjs[layerX][layerY] = obj;
    }

    public void addToLayerByPixelSize (int x, int y, SimpleGraphicsObject obj) {
        layerObjs[x/ gridSizeY][y/ gridSizeY] = obj;
    }

    public SimpleGraphicsObject[][] getLayer () {
        return layerObjs;
    }

    public SimpleGraphicsObject getObj (int x, int y) {
        return layerObjs[x][y];
    }
    public static int getGridSizeX() {
        return gridSizeX;
    }

    public static int getGridSizeY() {
        return gridSizeY;
    }
    public static void setGridSize(int gridSizeY) {
        GraphicsLayer.gridSizeY = gridSizeY;
        GraphicsLayer.gridSizeX = (int) (gridSizeY * (float) CHAR_WIDTH_RAW / CHAR_HEIGHT_RAW);
    }
}

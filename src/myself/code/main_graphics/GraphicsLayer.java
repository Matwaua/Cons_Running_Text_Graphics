package myself.code.main_graphics;

public class GraphicsLayer {
    private SimpleGraphicsObject[][] layer;
    private final int gridSize;

    public GraphicsLayer(int layerSizeX, int layerSizeY, int gridSize) {
        this.layer = new SimpleGraphicsObject[layerSizeX][layerSizeY];
        this.gridSize = gridSize;
    }

    public void addToLayerByGridSize (int layerX, int layerY, SimpleGraphicsObject obj) {
        layer[layerX][layerY] = obj;
    }

    public void addToLayerByPixelSize (int x, int y, SimpleGraphicsObject obj) {
        layer[x/gridSize][y/gridSize] = obj;
    }

    public SimpleGraphicsObject[][] getLayer () {
        return layer;
    }

    public SimpleGraphicsObject getObj (int x, int y) {
        return layer[x][y];
    }

    public int getGridSize() {
        return gridSize;
    }
}

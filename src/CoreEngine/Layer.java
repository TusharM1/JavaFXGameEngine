package CoreEngine;

import CoreEngine.GameObjects.GameObject;

import java.util.ArrayList;
import java.util.Arrays;

public class Layer implements Comparable<Layer> {

    private static int layerCount = 0;

    private ArrayList<GameObject> layerObjects;

    private boolean isVisible;

    private int id;

    public Layer(GameObject... gameObjects) {
        layerObjects = new ArrayList<>(Arrays.asList(gameObjects));
        isVisible = true;
        id = layerCount++;
    }

    public void updateAll() {
        if (isVisible)
            for (int i = 0; i < layerObjects.size(); i++)
                layerObjects.get(i).update();
    }

    public void renderAll() {
        if (isVisible)
            for (int i = 0; i < layerObjects.size(); i++)
                layerObjects.get(i).draw();
    }

    public boolean isVisible() { return isVisible; }

    public void setVisibility(boolean isVisible) { this.isVisible = isVisible; }

    @Override
    public int compareTo(Layer layer) {
        return Integer.compare(this.id, layer.id);
    }

    public int getID() {
        return id;
    }
}

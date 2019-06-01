package CoreEngine;

import CoreEngine.GameObjects.GameObject;

import java.util.ArrayList;
import java.util.Arrays;

public class Layer extends ArrayList<GameObject> implements Comparable<Layer> {

//    TODO allow setting background image.

    private static int layerCount = 0;

//    private ArrayList<GameObject> layerObjects;

    private boolean isVisible;

    private int id;

    public Layer(GameObject... gameObjects) {
//        layerObjects = new ArrayList<>(Arrays.asList(gameObjects));
        this.addAll(Arrays.asList(gameObjects));
        isVisible = true;
        id = layerCount++;
    }

    public void updateAll() {
        if (isVisible)
            for (int i = 0; i < this.size(); i++)
                this.get(i).update();
    }

    public void renderAll() {
        if (isVisible)
            for (int i = 0; i < this.size(); i++)
                this.get(i).draw();
    }

    public boolean isVisible() { return isVisible; }

    public void setVisibility(boolean isVisible) { this.isVisible = isVisible; }

    @Override
    public int compareTo(Layer layer) { return Integer.compare(this.id, layer.id); }

    public int getID() { return id; }
}

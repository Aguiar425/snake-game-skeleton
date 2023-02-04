package academy.mindswap.gameobjects.fruit;

import academy.mindswap.field.Position;

public class Fruit {

    Position fruitPos;

    public Fruit(Position fruitPos) {
        this.fruitPos = fruitPos;
    }

    public Position getPosition() {
        return fruitPos;
    }

    public void setFruitPos(Position fruitPos) {
        this.fruitPos = fruitPos;
    }
}

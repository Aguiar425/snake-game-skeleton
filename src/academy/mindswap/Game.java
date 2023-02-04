package academy.mindswap;

import academy.mindswap.field.Field;
import academy.mindswap.field.Position;
import academy.mindswap.gameobjects.fruit.Fruit;
import academy.mindswap.gameobjects.snake.Direction;
import academy.mindswap.gameobjects.snake.Snake;
import com.googlecode.lanterna.input.Key;

public class Game {

    private Snake snake;
    private Fruit fruit;
    private int delay;

    public Game(int cols, int rows, int delay) {
        Field.init(cols, rows);
        snake = new Snake();
        this.delay = delay;
    }

    public void start() throws InterruptedException {
        generateFruit(); // uncomment when it's time to introduce fruits

        while (true) {
            Thread.sleep(delay);
            Field.clearTail(snake);
            moveSnake();
            checkCollisions();
            Field.drawSnake(snake);
        }
    }

    private void generateFruit() {
        int fruitNum = 0;
        int randomCol = RandomNumberGenerator.generateRandom(Field.getWidth()-5, 5);
        int randomRow = RandomNumberGenerator.generateRandom(Field.getHeight()-5, 5);
            fruit = new Fruit(new Position(randomCol,randomRow));
            Field.drawFruit(fruit);
    }

    private void moveSnake() {

        Key k = Field.readInput();

        if (k != null) {
            switch (k.getKind()) {
                case ArrowUp:
                    snake.move(Direction.UP);
                    return;

                case ArrowDown:
                    snake.move(Direction.DOWN);
                    return;

                case ArrowLeft:
                    snake.move(Direction.LEFT);
                    return;

                case ArrowRight:
                    snake.move(Direction.RIGHT);
                    return;
            }
        }
        snake.move();
    }

    private void checkCollisions() {
        if(snake.getFullSnake().getFirst().getRow() == fruit.getPosition().getRow() && snake.getFullSnake().getFirst().getCol() == fruit.getPosition().getCol()){
            fruit.setFruitPos(null);
            generateFruit();
            snake.increaseSize();
        }
        if(snake.getFullSnake().getFirst().getRow() == 0 || snake.getFullSnake().getFirst().getRow() == Field.getHeight()){
            snake.die();
        }
        if(snake.getFullSnake().getFirst().getCol() == 0 || snake.getFullSnake().getFirst().getCol() == Field.getWidth()){
            snake.die();
        }

        if(fruit == null){
            generateFruit();
        }
    }
}

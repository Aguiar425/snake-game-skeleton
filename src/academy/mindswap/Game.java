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
    private int score;

    public Game(int cols, int rows, int delay) {
        Field.init(cols, rows);
        snake = new Snake();
        this.delay = delay;
        this.score = 0;
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
        int randomCol = RandomNumberGenerator.generateRandom(Field.getWidth()+5 , 5);
        int randomRow = RandomNumberGenerator.generateRandom(Field.getHeight()+5 , 5);
        if(randomCol == Field.getWidth() || randomCol == 0){
            randomCol = RandomNumberGenerator.generateRandom(Field.getWidth()+5 , 5);
        }
        if(randomRow == Field.getHeight() || randomRow == 0){
            randomRow = RandomNumberGenerator.generateRandom(Field.getHeight()+5 , 5);
        }
        fruit = new Fruit(new Position(randomCol, randomRow));
        /*for (int i = 0; i < snake.getFullSnake().size(); i++) {
            while(fruit.getPosition() == snake.getFullSnake().get(i)){
            }
        }*/
        Field.drawFruit(fruit);
    }

    private void moveSnake() {

        Key k = Field.readInput();

        if (k != null) {
            switch (k.getKind()) {
                case ArrowUp:
                    if(snake.getDirection() == Direction.DOWN){
                        return;
                    }
                    snake.move(Direction.UP);
                    return;

                case ArrowDown:
                    if(snake.getDirection() == Direction.UP){
                        return;
                    }
                    snake.move(Direction.DOWN);
                    return;

                case ArrowLeft:
                    if(snake.getDirection() == Direction.RIGHT){
                        return;
                    }
                    snake.move(Direction.LEFT);
                    return;

                case ArrowRight:
                    if(snake.getDirection() == Direction.LEFT){
                        return;
                    }
                    snake.move(Direction.RIGHT);
                    return;
            }
        }
        snake.move();
    }

    private void checkCollisions() {
        if (snake.getFullSnake().getFirst().getRow() == fruit.getPosition().getRow() && snake.getFullSnake().getFirst().getCol() == fruit.getPosition().getCol()) {
            fruit.setFruitPos(null);
            generateFruit();
            score++;
            if(score >= 5){
                this.delay = delay -10;
            }
            if(score >= 10){
                this.delay = delay -10;
            }
            snake.increaseSize();
        }
        if (snake.getFullSnake().getFirst().getRow() == 0 || snake.getFullSnake().getFirst().getRow() == Field.getHeight()) {
            snake.die();
        }
        if (snake.getFullSnake().getFirst().getCol() == 0 || snake.getFullSnake().getFirst().getCol() == Field.getWidth()) {
            snake.die();
        }

        for (int i = 1; i < snake.getFullSnake().size(); i++) {
            if (snake.getFullSnake().getFirst().getRow() == snake.getFullSnake().get(i).getRow() && snake.getFullSnake().getFirst().getCol() == snake.getFullSnake().get(i).getCol()) {
                snake.die();
            }

            if (fruit == null) {
                generateFruit();
            }
        }
    }
}

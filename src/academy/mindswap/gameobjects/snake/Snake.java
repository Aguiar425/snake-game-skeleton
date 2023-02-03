package academy.mindswap.gameobjects.snake;

import academy.mindswap.RandomNumberGenerator;
import academy.mindswap.field.Field;
import academy.mindswap.field.Position;

import java.util.LinkedList;

public class Snake {

    private final static int SNAKE_INITIAL_SIZE = 3;
    private Direction direction;
    private boolean alive;

    private Position head;
    private Position tail;
    private int size = SNAKE_INITIAL_SIZE;
    int startingCol = RandomNumberGenerator.generateRandom(Field.getWidth()-5, 5);
    int startingRow = RandomNumberGenerator.generateRandom(Field.getHeight()-5, 5);

    private LinkedList<Position> snakeBody;

    public Snake(Direction startingDir) {

        this.direction = startingDir;
        this.snakeBody = new LinkedList<>();

        this.head = new Position(this.startingCol, this.startingRow);
        snakeBody.add(head);
        for(int i = 1; i < SNAKE_INITIAL_SIZE; i++)
        {
            snakeBody.add(new Position(head.getCol()-i, head.getRow()));
        }
        tail = snakeBody.getLast();
        this.alive = true;
    }

    public void increaseSize() {
        snakeBody.add(new Position(tail.getCol()-1, tail.getRow()));
        size++;
    }


    public void move(Direction direction){
        Position temp;
        switch (direction){
            case UP :
                temp = new Position(tail.getCol(), tail.getRow()-1);
                this.snakeBody.remove(tail);
                snakeBody.add(temp);
                this.head = temp;
                this.direction = Direction.UP;
            case DOWN :
                temp = new Position(tail.getCol(), tail.getRow()+1);
                this.snakeBody.remove(tail);
                snakeBody.add(temp);

                this.head = temp;
                this.direction = Direction.DOWN;

            case RIGHT :
                temp = new Position(tail.getCol()+1, tail.getRow());
                this.snakeBody.remove(tail);
                snakeBody.add(temp);

                this.head = temp;
                this.direction = Direction.RIGHT;

            case LEFT :
                temp = new Position(tail.getCol()-1, tail.getRow());
                this.snakeBody.remove(tail);
                snakeBody.add(temp);

                this.head = temp;
                this.direction = Direction.LEFT;

        }
    }

    public void move() {
        move(this.direction);
    }

    public void die() {
        alive = false;
    }

    public boolean isAlive() {
        return alive;
    }

    public Position getHead() {
        return this.head;
    }

    public Position getTail() {
        return this.tail;
    }

    public LinkedList<Position> getFullSnake(){
        return this.snakeBody;
    }

    public int getSnakeSize() {
        return 0;
    }

}


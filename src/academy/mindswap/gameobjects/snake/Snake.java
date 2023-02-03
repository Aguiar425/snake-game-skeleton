package academy.mindswap.gameobjects.snake;

import academy.mindswap.RandomNumberGenerator;
import academy.mindswap.field.Field;
import academy.mindswap.field.Position;

import java.util.LinkedList;

public class Snake {

    private final static int SNAKE_INITIAL_SIZE = 3;
    private Direction direction;
    private boolean alive;

    private LinkedList<Position> snakeBody;

    public Snake() {

        this.direction = Direction.RIGHT;
        this.snakeBody = new LinkedList<>();
        /*this.head = new Position(50, 12);
        snakeBody.add(new Position(50,12));*/
        for(int i = 0; i < SNAKE_INITIAL_SIZE; i++)
        {
            snakeBody.add(i, new Position(50-i, 12));
        }
        //this.tail = snakeBody.getLast();
        this.alive = true;
    }

    public void increaseSize() {
        /*snakeBody.add(new Position(snakeBody.getTail.getCol()-1, tail.getRow()));
        size++;*/
    }


    public void move(Direction direction){
        switch (direction){
            case UP :
                snakeBody.removeLast();
                snakeBody.addFirst(new Position(snakeBody.get(0).getCol(), snakeBody.get(0).getRow()-1));
                /*head.setRow(tail.getRow()-1);
                setTail(snakeBody.getLast());*/
                setDirection(Direction.UP);
                break;
            case DOWN :
                /*temp = new Position(head.getCol(), head.getRow()-1);
                Field.clearTail(this);
                snakeBody.add(temp);
                this.head = temp;*/
                /*head = tail;
                head.setRow(tail.getRow()+1);
                Field.clearTail(this);
                setTail(snakeBody.getLast());
                setDirection(Direction.DOWN);*/
                snakeBody.removeLast();
                snakeBody.addFirst(new Position(snakeBody.get(0).getCol(), snakeBody.get(0).getRow()+1));
                setDirection(Direction.DOWN);
                break;

            case RIGHT :
                snakeBody.removeLast();
                snakeBody.addFirst(new Position(snakeBody.get(0).getCol()+1, snakeBody.get(0).getRow()));
                setDirection(Direction.RIGHT);
                break;

            case LEFT :
                snakeBody.removeLast();
                snakeBody.addFirst(new Position(snakeBody.get(0).getCol()-1, snakeBody.get(0).getRow()));
                setDirection(Direction.LEFT);
                break;
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
        return this.snakeBody.getFirst();
    }

    public Position getTail() {
        return this.snakeBody.getLast();
    }

    public LinkedList<Position> getFullSnake(){
        return this.snakeBody;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    /*public int getSnakeSize() {
        return size;
    }*/

}


package academy.mindswap;

public class RandomNumberGenerator {

    public static int generateRandom(int max, int min) {
        return (int) (Math.random() * (max + 1 - min)); //should return a random number between 0 a max rand(1,13);
    }}

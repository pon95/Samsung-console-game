import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class SmallMonster extends Monster {
    Scanner input = new Scanner(System.in);
    String image = "\uD83D\uDC7B";

    SmallMonster(int size) {
        super(size);
    }

    @Override
    boolean askQuestion(int key) {
        System.out.println("ну, это будет просто:");
        int x = rand.nextInt(1489);
        int y = rand.nextInt(14233);
        int rightAnswer;
        if (x > y) rightAnswer = x;
        else rightAnswer = y;
        System.out.println("Что больше: " + x + " или " + y + " ?");
        return input.nextInt() == rightAnswer;
    }
}

import java.util.Scanner;

public class BigMonster extends Monster {
    Scanner input = new Scanner(System.in);
    String image = "\uD83D\uDC79";


    BigMonster(int size) {
        super(size);
    }

    @Override
    public String getImage(){
        return this.image;
    }


    @Override
    boolean askQuestion(int key) {
        if (key == 1) return super.askQuestion(1);
        int x = rand.nextInt(100);
        int y = rand.nextInt(100);
        int z = rand.nextInt(100);
        int rightAnswer = x * y - z;
        System.out.println("Реши: " + x + " * " + y + " - " + z + " = ???");
        int ans = input.nextInt();
        if (rightAnswer == ans) {
            System.out.println("Ты победил большого монстра!!!");
            return true;
        }
        System.out.println("ты проиграл битву с большим монстром и теряешь 2 жизни");
        return false;
    }
}
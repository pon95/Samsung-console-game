import java.util.Random;
import java.util.Scanner;

public class Monster {
    Random rand = new Random();
    public int x, y;
    public String image = "\uD83D\uDC7A";
    Monster(int size){
        this.x = rand.nextInt(size-1);
        this.y = rand.nextInt(size);
    }
    public String getImage() {
        return this.image;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    boolean askQuestion(int key) {
        Scanner input = new Scanner(System.in);
        Random rand = new Random();

        int[] numbers = {rand.nextInt(0, 21), rand.nextInt(1, 11)};
        char[] operands = {'+', '-', '*', '/', '%'};
        char operand = operands[rand.nextInt(0, operands.length)];
        String question = numbers[0] + " " + operand + " " + numbers[1];
        System.out.print(question + " = ???\nи ваш ответ: ");
        int rightAnswer;

        switch(operand){
            case '+':
                rightAnswer = numbers[0] + numbers[1];
                break;
            case '-':
                rightAnswer = numbers[0] - numbers[1];
                break;
            case '*':
                rightAnswer = numbers[0] * numbers[1];
                break;
            case '/':
                rightAnswer = numbers[0] / numbers[1];
                break;
            case '%':
                rightAnswer = numbers[0] % numbers[1];
                break;
            default:
                System.out.println("ошибка. ответ 0");
                rightAnswer=0;
        }
        int playerAnswer = input.nextInt();
        if(playerAnswer==rightAnswer){
            System.out.println("Ты победил!!! (монстра)");
            return true;
        } else{
            System.out.println("Ты проиграл битву(((");
            return false;
        }

    }
}
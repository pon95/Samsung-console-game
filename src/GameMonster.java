import java.util.Arrays;
import java.util.Scanner;

public class GameMonster extends Monster {
    Scanner input = new Scanner(System.in);

    GameMonster(int size) {
        super(size);
    }
    String image = "\uD83D\uDC7E";

    @Override
    public String getImage() {
        return image;
    }

    @Override
    boolean askQuestion(int key) {
        int hangs = 0;
        String[] states = {"/\\", " |\n |\n/\\", "+——+\n   |\n   |\n  /\\", "+——+\nO  |\n   |\n  /\\"}; // тут этапы рисования виселицы
        String[] words = {"MONSTER", "MINIGAME", "JAVATOP", }; // сюда можно добавлять любое кол-во своих слов
        String hiddenWord = words[rand.nextInt(words.length)];
        System.out.println("Играем в игру \"Палач\", у тебя " + states.length + " жизней");
        System.out.print("Вот слово: ");
        for (int i = 1; i <= hiddenWord.length(); i++) {
            System.out.print("_");
        }
        char[] rightCharWord = new char[hiddenWord.length()];
        char[] userCharWord = new char[hiddenWord.length()];
        Arrays.fill(userCharWord, '_');
        boolean wasLetterCorrect = false;
        boolean isGuessed = false;

        System.out.println("\nпиши заглавную ангийскую букву, которая есть в слове (угадывай)");

        hiddenWord.getChars(0, hiddenWord.length(), rightCharWord, 0);

        while (true) {
            isGuessed=true;
            for(int charNum=0; charNum<rightCharWord.length; charNum++) {
                if (rightCharWord[charNum] != userCharWord[charNum]) {
                    isGuessed = false;
                    break;
                }
            }
            if (isGuessed) break;
            char UserLetter = input.nextLine().charAt(0);
            for(char i : userCharWord) {
                if (i==UserLetter) {
                    System.out.println("Ай-ай-ай нельзя ту же букву писать!!!!! :(");
                    System.out.println(states[3]);
                    System.out.println("ты проиграл)");
                    return false;
                }
            }
            for (int i = 0; i < hiddenWord.length(); i++) {
                char nextChar = hiddenWord.charAt(i);
                if (UserLetter == nextChar) {
                    userCharWord[i] = UserLetter;
                    wasLetterCorrect=true;
                }
            }
            System.out.println(userCharWord);
            if (!wasLetterCorrect) {
                System.out.println(states[hangs]);
                hangs++;
            }
            if(hangs == states.length){
                System.out.println("ты проиграл)");
                return false;
            }
            wasLetterCorrect=false;
        }
        System.out.println("Ты угадал))");
        return true;
    }
}

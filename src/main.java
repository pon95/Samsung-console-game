import java.util.Random;
import java.util.Scanner;

public class main {
    public static void drawBoard(String[][] board) {
        String leftBlock = " | ", rightBlock = " |", wall = " + —— + —— + —— + —— + —— + ";
        for (String[] row : board) {
            System.out.println(wall);
            for (String col : row) {
                System.out.print(leftBlock);
                System.out.print(col);
            }
            System.out.println(rightBlock);
        }
        System.out.println(wall);
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Random rand = new Random();
        Player player = new Player();

        int step = 1, boardSize = 5, castleX = rand.nextInt(1, 6), castleY = 1;
        String[][] board = new String[boardSize][boardSize];
        for (int y = 1; y <= boardSize; y++) {
            for (int x = 1; x <= boardSize; x++) {
                board[y - 1][x - 1] = "  ";
            }
        }
        String castle = "\uD83C\uDFEF";
        int count_monster = boardSize * boardSize - boardSize - 5;

        Monster[] arrMonster = new Monster[count_monster + 1];
        int count = 0;
        Monster test;
        while (count <= count_monster) {
            int randNum = rand.nextInt(0, 15);
            if (randNum <= 2)
                test = new SmallMonster(boardSize);
            else if (randNum <= 7)
                test = new Monster(boardSize);
            else if (randNum <= 10)
                test = new BigMonster(boardSize);
            else
                test = new GameMonster(boardSize);
            if (board[test.getY()][test.getX()].equals("  ")) {
                board[test.getY()][test.getX()] = test.getImage();
                arrMonster[count] = test;
                count++;
            }
        }

        board[castleY - 1][castleX - 1] = castle;
        //board[pY][pX] = player;

        System.out.println("выбери сложность от 1 до 5");
        int difficulty = input.nextInt();
        System.out.println("персонаж сейчас в (" + (player.getX()) + ", " + (player.getY()) + ")");

        String next = null;
        while (true) {
            board[player.getY() - 1][player.getX() - 1] = player.getImage();

            drawBoard(board);
            if (player.lives <= 0) {
                System.out.println("Ты проирал!");
                break;
            }
            System.out.println("осталось " + player.lives + " жизней");

            System.out.println("Куда ходить? (пиши координаты вида x y)");
            int newX = input.nextInt(), newY = input.nextInt();
            next = board[newY - 1][newX - 1];
            if (player.isMoveCorrect(newX, newY)) {
                if (next.equals("  ")) {
                    board[player.getY() - 1][player.getX() - 1] = "  ";player.move(newX, newY);
                    step++;
                    System.out.println("\n\nОк, персонах в (" + player.getX() + ", " + player.getY() + ")\nход № " + step + "\n");
                } else if (next.equals(castle)) {
                    System.out.println("Ты победил!!!!!!111!1!");
                    break;
                } else {
                    for (Monster m : arrMonster) {
                        if ((m.getX() + 1) == newX && (m.getY() + 1) == newY) {
                            if (!m.askQuestion(difficulty)) {
                                if (m instanceof BigMonster) player.downLive();
                                player.downLive();
                            } else {
                                board[player.getY() - 1][player.getX() - 1] = "  ";
                                player.move(newX, newY);
                                step++;
                            }
                            break;
                        }
                    }
                }
            } else {
                System.out.println("Некоректный ход!! (ход в: \"" + next + "\")");
            }
        }
    }
        }

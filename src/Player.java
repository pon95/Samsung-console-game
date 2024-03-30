import java.util.Random;

public class Player {
    Player(int origin, int bound) {
        Random rand = new Random();
        this.x = rand.nextInt(origin, bound);
    }
    Player() {
        x = 3;
        y = 5;
    }
    public int x, y;
    public String image = "\uD83E\uDDD9\u200D";
    int lives = 3;
    public int getX(){
        return x;
    }
    public int getY() {
        return y;
    }
    public int getLive() {
        return lives;
    }
    public String getImage(){
        return image;
    }
    public void setImage(String image){
        this.image = image;
    }
    void move(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public boolean isMoveCorrect(int x, int y){
        if (this.x == x && Math.abs(this.y - y) == 1 || this.y == y && Math.abs(this.x - x) == 1){
            return true;
        }
        return false;
    }
    public void downLive() {
        lives--;
        if (lives < 0)
            lives = 0;
    }
}
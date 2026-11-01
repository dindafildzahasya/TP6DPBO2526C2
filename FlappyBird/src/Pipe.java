import java.awt.*;

public class Pipe {
    private int posX;
    private int posY;
    private int width;
    private int height;
    private Image image;
    private int velocityX;
    boolean passed;

    public Pipe(int posX, int posY, int width, int height, Image image){
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.image = image;
        this.velocityX = 0;
        this.passed = false;
    }

    // Getter
    public int getPosX() {
        return posX;
    }
    // Setter
    public void setPosX(int posX) {
        this.posX = posX;
    }

    // Getter
    public int getPosY() {
        return posY;
    }
    // Setter
    public void setPosY(int posY) {
        this.posY = posY;
    }

    // Getter
    public int getWidth() {
        return width;
    }
    // Setter
    public void setWidth(int width) {
        this.width = width;
    }

    // Getter
    public int getHeight() {
        return height;
    }
    // Setter
    public void setHeight(int height) {
        this.height = height;
    }

    // Getter
    public Image getImage() {
        return image;
    }
    // Setter
    public void setImage(Image image) {
        this.image = image;
    }

    // Getter
    public int getVelocityX() {
        return velocityX;
    }
    // Setter
    public void setVelocityX(int velocityX){
        this.velocityX = velocityX;
    }

    // Getter
    public boolean isPassed() {
        return passed;
    }
    // Setter
    public void setPassed(boolean passed) {
        this.passed = passed;
    }
}

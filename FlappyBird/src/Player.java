import java.awt.*;

public class Player {
    private int posX;
    private int posY;
    private int width;
    private int height;
    private Image image;
    private double velocityY = 0.0;

    public Player(int posX, int posY, int width, int height, Image image){
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.image = image;
        this.velocityY = 0.0;
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
    public double getVelocityY() {
        return velocityY;
    }
    // Setter
    public void setVelocityY(double velocityY){
        this.velocityY = velocityY;
    }
}

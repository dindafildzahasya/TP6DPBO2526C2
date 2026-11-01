import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Logic implements ActionListener, KeyListener{
    int frameWIdth = 360;
    int frameHeight = 640;

    int playerStartPosX = frameWIdth / 2;
    int playerStartPosY = frameHeight / 2;
    int playerWidth = 34;
    int playerHeight = 24;

    // atribut posisi dan ukuran pipa
    int pipeStartPosX = frameWIdth;
    int pipeStartPosY = 0;
    int pipeWidth = 64;
    int pipeHeight = 512;

    Timer gameLoop;
    Timer pipesCooldown;

    // Untuk mengetahui apakah permainan masih berjalan atau sudah berakhir
    boolean gameOver = false;
    boolean gameStarted = false;
    // Atribut untuk menyimpan skor
    int score = 0;

    double gravity = 0.35;
    int pipeVelocityX = -2;

    View view;
    Image birdImage;
    Player player;

    // Atribut untuk mengatur sound effect
    AudioPlayer hitSound;
    AudioPlayer flapSound;
    AudioPlayer scoreSound;

    // List pipa dan gambar pipa
    Image lowerPipeImage;
    Image upperPipeImage;
    ArrayList<Pipe> pipes;

    public Logic(){
        // Memuat sound effect
        hitSound = new AudioPlayer("/assets/audio/hit.wav");
        flapSound = new AudioPlayer("/assets/audio/flap.wav");
        scoreSound = new AudioPlayer("/assets/audio/score.wav");
        // Membuat file gambar
        birdImage = new ImageIcon(getClass().getResource("assets/bird.png")).getImage();
        player = new Player(playerStartPosX, playerStartPosY, playerWidth, playerHeight, birdImage);

        lowerPipeImage = new ImageIcon(getClass().getResource("assets/lowerPipe.png")).getImage();
        upperPipeImage = new ImageIcon(getClass().getResource("assets/upperPipe.png")).getImage();
        pipes = new ArrayList<Pipe>();

        pipesCooldown = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                placePipes();
            }
        });

        gameLoop = new Timer(1000/60, this);
    }

    // Method untuk memulai permainan
    public void startGame() {
        gameOver = false;
        pipesCooldown.start();

        // Menghentikan timer terlebih dahulu
        gameLoop.start();
        pipesCooldown.stop();

        // Mengembalikan posisi player
        player.setPosX(playerStartPosX);
        player.setPosY(playerStartPosY);
        player.setVelocityY(0.0);

        // Menghapus pipa sebelumnya
        pipes.clear();

        // Mengembalikan skor
        score = 0;

        if (view != null) {
            view.updateScore(score);
            view.repaint();
        }
    }

    public void setView(View view){
        this.view = view;
    }
    public Player getPlayer(){
        return player;
    }
    // Getter untuk GameOver
    public boolean isGameOver(){
        return gameOver;
    }
    // Getter getScore
    public int getScore(){
        return score;
    }

    // Method move
    public void move(){
        // Mengatur gravitasi player
        player.setVelocityY(player.getVelocityY() + gravity);
        // Menggerakkan Player secara vertikal
        int movementY = (int) Math.round(player.getVelocityY());
        player.setPosY(player.getPosY() + movementY);

        // Membatasi posisi Player agar tidak melewati batas atas
        if (player.getPosY() < 0) {
            player.setPosY(0);

            if (player.getVelocityY() < 0) {
                player.setVelocityY(0);
            }
        }

        // Menggerakkan semua pipa
        for(int i = 0; i < pipes.size(); i++){
            Pipe pipe = pipes.get(i);
            // Menggerakkan pipa
            pipe.setPosX(pipe.getPosX() + pipeVelocityX);

            // Mengecek apakah player sudah melewati pipa atas
            if (pipe.getImage() == upperPipeImage && !pipe.isPassed() &&
            pipe.getPosX() + pipe.getWidth() < player.getPosX()){
                // Menandai pipa sudah dilewati
                pipe.setPassed(true);

                // Menambah skor
                score++;

                // Memainkan suara ketika skor bertambah
                scoreSound.play(false);

                // Memperbarui tampilan skor
                if (view != null) {
                    view.updateScore(score);
                }
            }
        }
    }

    public ArrayList<Pipe> getPipes() {
        return pipes;
    }

    // Method untuk mengeccek tabrakan
    public void checkCollision(){
        // Membuat batas area Player
        Rectangle playerBounds = new Rectangle(player.getPosX(), player.getPosY(),
                player.getWidth(), player.getHeight());
        // Mengecek apakah player jatuh ke bawah
        if (player.getPosY() + player.getHeight() >= frameHeight) {
            endGame();
            return;
        }

        // Mengecek tabrakan plyaer dengan pipa
        for(int i = 0; i < pipes.size(); i++){
            Pipe pipe = pipes.get(i);
            // Membuat area tabrakan player dan pipe
            Rectangle pipeBounds = new Rectangle(pipe.getPosX(), pipe.getPosY(),
                    pipe.getWidth(), pipe.getHeight());

            // memeriksa apakah kedua area saling bertabrakan
            if (playerBounds.intersects(pipeBounds)){
                endGame();
                return;
            }
        }
    }

    public void endGame(){
        // Mencegah Game Over dipanggil berulang kali
        if (gameOver) {
            return;
        }
        gameOver = true;

        // Menghentikan Timer
        gameLoop.stop();
        pipesCooldown.stop();

        // Memainkan suara ketika burung menabrak pipa
        hitSound.play(false);
    }

    // Method ini untuk mengulang permainan
    public void restartGame(){
        // mengembbalikan posisi player ke posisi awal
        player.setPosX(playerStartPosX);
        player.setPosY(playerStartPosY);

        // Mengembalikan kecepatan player
        player.setVelocityY(0);

        // Mengembalikan semua pipa sebelumnya
        pipes.clear();

        // Mengembalikan skor ke 0
        score = 0;

        // Memperbarui tampilan skor
        if (view != null){
            view.updateScore(score);
        }

        // Mengubah status permainan
        gameOver = false;
        gameStarted = true;

        // Menjalankan kembali timer
        pipesCooldown.restart();
        gameLoop.start();

        // Memperbarui tampilan
        if (view != null) {
            view.repaint();
        }
    }

    public void placePipes(){
        int randomPosY = (int) (pipeStartPosY - (pipeHeight / 4) - Math.random() * (pipeHeight / 2));
        int openingSpace = frameHeight / 4;

        Pipe upperPipe = new Pipe(pipeStartPosX, randomPosY, pipeWidth, pipeHeight, upperPipeImage);
        pipes.add(upperPipe);

        Pipe lowerPipe = new Pipe(pipeStartPosX, randomPosY + openingSpace + pipeHeight,
                pipeWidth, pipeHeight, lowerPipeImage);
        pipes.add(lowerPipe);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(!gameOver){
            move();
            checkCollision();
        }

        if(view != null){
            view.repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e){}

    @Override
    public void keyPressed(KeyEvent e){
        // Ketika game over, tombol r menjalankan restart game
        if(gameOver && e.getKeyCode() == KeyEvent.VK_R){
            restartGame();
        }
        // Ketika permainan berlangsung, tombol space membuat burung melompat
        else if (!gameOver && e.getKeyCode() == KeyEvent.VK_SPACE) {
            // Memulai game ketika SPACE pertama kali ditekan
            if (!gameStarted) {
                gameStarted = true;

                pipesCooldown.start();
                gameLoop.start();
            }
            player.setVelocityY(-7.0);
            // Memainkan suara burung melompat
            flapSound.play(false);
        }
    }
    public void keyReleased(KeyEvent e){}
}

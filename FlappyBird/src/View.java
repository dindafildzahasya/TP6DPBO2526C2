import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class View extends JPanel{
    int width = 360;
    int height = 640;

    private Logic logic;
    // Atribut untuk menampilkan skor di layar
    private JLabel scoreLabel;
    // Atribut untuk background
    private Image backgroundImage;

    public View(Logic logic) {
        this.logic = logic;
        setPreferredSize(new Dimension(width, height));
        // Memuat gambar background
        backgroundImage = new ImageIcon(getClass().getResource("/assets/background.png")).getImage();

        // Mengatur layout agar posisi JLabel dapat diatur
        setLayout(null);

        // Membuat JLabel untuk menampilkan skor
        scoreLabel = new ScoreBadge();
        scoreLabel.setText("0");

        // Menambahkan JLabel ke JPanel
        add(scoreLabel);

        // Mengatur posisi score
        updateScorePosition();

        // fokus dan key listener untuk menerima input
        setFocusable(true);
        addKeyListener(logic);
    }

    // Mengatur posisi score di tengah atas
    private void updateScorePosition() {
        int scoreWidth = 116;
        int scoreHeight = 80;
        int panelWidth = getWidth() > 0 ? getWidth() : width;
        int x = (panelWidth - scoreWidth) / 2;

        scoreLabel.setBounds(x, 12, scoreWidth, scoreHeight);
    }

    // Memperbarui posisi ketika ukuran panel berubah
    @Override
    public void doLayout() {
        updateScorePosition();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();

        // Menggambar background
        g2.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);

        // Menggambar Player dan Pipe
        draw(g2);

        g2.dispose();
    }

    public void draw(Graphics g){
        // Menggambar Player
        Player player = logic.getPlayer();
        if(player != null){
            g.drawImage(player.getImage(), player.getPosX(), player.getPosY(),
                    player.getWidth(), player.getHeight(), null);
        }

        // Menggambar semua pipe
        ArrayList<Pipe> pipes = logic.getPipes();
        if(pipes != null){
            for(int i = 0; i < pipes.size(); i++){
                Pipe pipe = pipes.get(i);
                g.drawImage(pipe.getImage(), pipe.getPosX(), pipe.getPosY(),
                        pipe.getWidth(), pipe.getHeight(), null);
            }
        }
        // Menampilkan Game Over
        if (logic.isGameOver()) {
            drawGameOver(g);
        }
    }

    // Method untuk menggambar tampilan Game Over
    private void drawGameOver(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int panelWidth = 290;
        int panelHeight = 150;

        int x = (getWidth() - panelWidth) / 2;
        int y = (getHeight() - panelHeight) / 2;

        // Membuat background gelap transparan
        g2.setColor(new Color(0, 0, 0, 115));
        g2.fillRect(0, 0, getWidth(), getHeight());

        // Membuat bayangan panel
        g2.setColor(new Color(50, 40, 25, 100));

        g2.fillRoundRect(x + 5, y + 6, panelWidth, panelHeight, 28, 28);

        // Membuat panel utama
        GradientPaint panelGradient = new GradientPaint(0, y,
                new Color(255, 250, 230), 0, y
                + panelHeight, new Color(255, 225, 175));

        g2.setPaint(panelGradient);
        g2.fillRoundRect(x, y, panelWidth, panelHeight, 28, 28);

        // Outline panel
        g2.setColor(new Color(150, 100, 45));
        g2.setStroke(new BasicStroke(4));
        g2.drawRoundRect(x, y, panelWidth, panelHeight, 28, 28);

        // Membuat bagian judul GAME OVER
        g2.setColor(new Color(235, 115, 50));
        g2.fillRoundRect(x + 45, y + 10, panelWidth - 90, 36, 18, 18);

        // Tulisan GAME OVER
        g2.setFont(new Font("Trebuchet MS", Font.BOLD, 23));
        String message = "GAME OVER";

        FontMetrics fm = g2.getFontMetrics();

        int textX = (getWidth() - fm.stringWidth(message)) / 2;
        int textY = y + 35;

        g2.setColor(Color.WHITE);
        g2.drawString(message, textX, textY);

        // Menampilkan skor akhir
        g2.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        String finalScore = "SCORE: " + logic.getScore();
        fm = g2.getFontMetrics();

        int scoreX = (getWidth() - fm.stringWidth(finalScore)) / 2;

        g2.setColor(new Color(125, 80, 30));
        g2.drawString(finalScore, scoreX, y + 76);

        // Membuat tombol keyboard R
        int keyX = x + 48;
        int keyY = y + 94;

        g2.setColor(new Color(190, 125, 35));
        g2.fillRoundRect(keyX, keyY, 32, 32, 9, 10);
        // Outline tombol R
        g2.setColor(new Color(130, 80, 25));
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(keyX, keyY, 32, 32, 9, 10);

        // Tulisan R
        g2.setFont(new Font("Trebuchet MS", Font.BOLD, 21));
        fm = g2.getFontMetrics();

        int rX = keyX + (32 - fm.stringWidth("R")) / 2;
        int rY = keyY + 23;

        g2.setColor(Color.WHITE);
        g2.drawString("R", rX, rY);

        // Instruksi restart
        g2.setFont(new Font("Trebuchet MS", Font.BOLD, 12));

        g2.setColor(new Color(115, 75, 30));
        g2.drawString("PRESS TO RESTART", keyX + 42, keyY + 21);
        g2.dispose();
    }

    // Memperbarui angka score
    public void updateScore(int score) {
        scoreLabel.setText(String.valueOf(score));
        scoreLabel.repaint();
    }

    // Class untuk membuat tampilan score custom
    private class ScoreBadge extends JLabel {

        public ScoreBadge() {
            setHorizontalAlignment(SwingConstants.CENTER);
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics graphics) {

            Graphics2D g2 = (Graphics2D) graphics.create();

            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int w = getWidth();
            int h = getHeight();

            // Membuat bayangan badge
            g2.setColor(new Color(70, 55, 30, 100));
            g2.fillRoundRect(5, 7, w - 10, h - 12, 25, 25);

            // Membuat gradient badge
            GradientPaint gradient = new GradientPaint(0, 0,
                    new Color(255, 225, 100), 0, h,
                    new Color(235, 145, 35));
            g2.setPaint(gradient);

            g2.fillRoundRect(3, 2, w - 8, h - 12, 25, 25);

            // Membuat outline badge
            g2.setColor(new Color(145, 90, 25));
            g2.setStroke(new BasicStroke(3));

            g2.drawRoundRect(3, 2, w - 8, h - 12, 25, 25);

            // Membuat garis highlight
            g2.setColor(new Color(255, 255, 255, 120));
            g2.drawRoundRect(7, 6, w - 16, h - 20, 20, 20);

            // Membuat tulisan SCORE
            g2.setFont(new Font("Trebuchet MS", Font.BOLD, 13));
            String title = "SCORE";
            FontMetrics fm = g2.getFontMetrics();

            int titleX = (w - fm.stringWidth(title)) / 2;

            g2.setColor(new Color(110, 70, 25));
            g2.drawString(title, titleX, 23);

            // Menggambar angka score
            String number = getText();

            int fontSize = number.length() > 2 ? 29 : 36;

            g2.setFont(new Font("Trebuchet MS", Font.BOLD, fontSize));

            fm = g2.getFontMetrics();

            int numberX = (w - fm.stringWidth(number)) / 2;
            int numberY = 62;

            // Bayangan angka
            g2.setColor(new Color(120, 75, 25));
            g2.drawString(number, numberX + 2, numberY + 2);

            // Angka utama
            g2.setColor(Color.WHITE);
            g2.drawString(number, numberX, numberY);
            g2.dispose();
        }
    }
}

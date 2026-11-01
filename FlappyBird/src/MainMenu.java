import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainMenu extends JPanel {
    private Image backgroundImage;

    public MainMenu (ActionListener playAction, ActionListener exitAction) {
        // Mengatur ukutan dan warnan background
        setPreferredSize(new Dimension(360,640));
        // Memuat gambar background
        backgroundImage = new ImageIcon(getClass().getResource("/assets/background.png")).getImage();

        // Mengatur posisi komponenn di tengah
        setLayout(new GridBagLayout());

        // Panel untuk menampung judul dan tombol
        JPanel menuPanel = new JPanel();
        menuPanel.setOpaque(false);
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));

        // Membuat judul game
        JLabel title = new JLabel("FLAPPY BIRD");
        title.setFont(new Font("Trebuchet MS", Font.BOLD, 38));
        title.setForeground(new Color(255, 205, 65));
        title.setBackground(new Color(25, 95, 145, 210));
        title.setOpaque(true);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Memberikan outline pada judul
        title.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.WHITE, 3),
                BorderFactory.createEmptyBorder(10, 18, 10, 18)
        ));

        // Membuat tombol Play
        JButton playButton = createButton("PLAY",
                new Color(255, 205, 75),
                new Color(240, 135, 35),
                playAction);

        // Membuat tombol Exit
        JButton exitButton = createButton("EXIT",
                new Color(100, 195, 240),
                new Color(40, 120, 200),
                exitAction);

        // Menambahkan komponen ke panel
        menuPanel.add(title);
        menuPanel.add(Box.createVerticalStrut(45));
        menuPanel.add(playButton);
        menuPanel.add(Box.createVerticalStrut(18));
        menuPanel.add(exitButton);

        // Menambahkan panel menu ke JPanel utama
        add(menuPanel);
    }

    // Method untuk membuat tombol custom
    private JButton createButton(String text, Color topColor, Color bottomColor, ActionListener action) {
        JButton button = new JButton(text){
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();

                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Efek ketika tombol ditekan
                int offset = getModel().isPressed() ? 4 : 0;

                // Mengatur warna ketika mouse diarahkan ke tombol
                Color top = getModel().isRollover() ? topColor.brighter() : topColor;
                Color bottom = getModel().isRollover() ? bottomColor.brighter() : bottomColor;

                // Membuat bayangan tombol
                g2.setColor(new Color(50, 60, 70, 100));
                g2.fillRoundRect(4, 7, getWidth() - 8, getHeight() - 12, 25, 25);

                // Membuat gradient tombol
                GradientPaint gradient = new GradientPaint(0, 0, top, 0, getHeight(), bottom);
                g2.setPaint(gradient);
                g2.fillRoundRect(3, 2 + offset, getWidth() - 8, getHeight() - 12, 25, 25);

                // Membuat outline tombol
                g2.setColor(new Color(125, 85, 40));
                g2.setStroke(new BasicStroke(3));

                g2.drawRoundRect(3, 2 + offset, getWidth() - 8, getHeight() - 12, 25, 25);

                // Mengatur font tulisan tombol
                g2.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
                FontMetrics fm = g2.getFontMetrics();

                int textX = (getWidth() - fm.stringWidth(text)) / 2;
                int textY = (getHeight() + fm.getAscent() - fm.getDescent()) / 2 - 3 + offset;

                // Bayangan tulisan
                g2.setColor(new Color(100, 70, 35));
                g2.drawString(text, textX + 2, textY + 2);

                // Tulisan utama
                g2.setColor(Color.WHITE);
                g2.drawString(text, textX, textY);

                g2.dispose();
            }
        };

        // Mengatur tampilan tombol
        button.setPreferredSize(new Dimension(210, 60));
        button.setMaximumSize(new Dimension(210, 60));
        button.setMinimumSize(new Dimension(210, 60));

        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setRolloverEnabled(true);

        // Menambahkan aksi tombol
        button.addActionListener(action);

        return button;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();

        // Menggambar background
        if (backgroundImage != null) {
            g2.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
        g2.dispose();
    }
}

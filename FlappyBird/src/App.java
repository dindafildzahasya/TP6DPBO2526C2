import javax.swing.*;
import java.awt.*;

public class App {
    public static void main(String[] args) {
        // Membuat JFrame
        JFrame frame = new JFrame("FlappyBird");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        // Membuat logic dan view
        Logic logika = new Logic();
        View tampilan = new View(logika);
        logika.setView(tampilan);

        // Membuat CardLayout untuk berpindah tampilan
        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);

        // Membuat Main Menu
        MainMenu menu = new MainMenu(
                e -> { // Aksi ketika tombol Play ditekan
                    cardLayout.show(mainPanel, "game");
                    logika.startGame();

                    // Memberikan fokus keyboard ke View
                    SwingUtilities.invokeLater(() -> tampilan.requestFocusInWindow()
                    );
                },
                e -> { // Aksi ketika tombol Exit ditekan
                    System.exit(0);
                }
        );

        // Menambahkan menu dan game ke CardLayout
        mainPanel.add(menu, "menu");
        mainPanel.add(tampilan, "game");

        // Menampilkan panel utama
        frame.add(mainPanel);

        // Mengatur ukuran dan posisi JFrame
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Menampilkan Main Menu saat program pertama kali dibuka
        cardLayout.show(mainPanel, "menu");
    }
}
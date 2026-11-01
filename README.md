# Flappy Bird — Java Swing

Project game **Flappy Bird** yang dibuat menggunakan **Java Swing** sebagai tugas praktikum. Pemain mengendalikan burung agar dapat melewati rintangan berupa pipa. Pemain memperoleh skor setiap berhasil melewati sepasang pipa dan permainan berakhir ketika burung menabrak pipa atau jatuh ke batas bawah layar.

## Fitur Permainan

- **Main Menu** dengan tombol Play dan Exit.
- **Gameplay** dengan background, karakter burung, dan pipa sebagai obstacle.
- **Kontrol burung** menggunakan tombol `SPACE`.
- **Gravitasi dan lompatan** untuk mengatur gerakan burung.
- **Obstacle pipa** yang muncul secara berkala dan bergerak dari kanan ke kiri.
- **Deteksi tabrakan** antara burung dan pipa serta deteksi ketika burung jatuh ke batas bawah layar.
- **Game Over** ketika burung menabrak pipa atau jatuh.
- **Restart** menggunakan tombol `R` setelah Game Over.
- **Score** yang bertambah satu poin setiap kali burung berhasil melewati sepasang pipa.
- **Sound effect** untuk lompatan, penambahan skor, dan tabrakan/Game Over.
- **Tampilan visual khusus** untuk Main Menu, badge Score, dan panel Game Over.

## Teknologi yang Digunakan

- **Java** sebagai bahasa pemrograman.
- **Java Swing** untuk antarmuka, antara lain `JFrame`, `JPanel`, `JLabel`, dan `JButton`.
- **Java AWT/Java2D** untuk menggambar background, objek permainan, dan elemen visual.
- **Java Timer** untuk menjalankan game loop dan kemunculan pipa secara berkala.
- **Java Sound API** (`javax.sound.sampled`) untuk memainkan sound effect WAV.
- **IntelliJ IDEA** sebagai IDE pengembangan.

## Struktur Project

Struktur folder utama yang digunakan:

```text
repository/
├── FlappyBird/
│   ├── src/
│   │   ├── assets/
│   │   │   ├── background.png
│   │   │   ├── bird.png
│   │   │   ├── lowerPipe.png
│   │   │   ├── upperPipe.png
│   │   │   └── audio/
│   │   │       ├── hit.wav
│   │   │       ├── loncat.wav
│   │   │       └── score.wav
│   │   ├── App.java
│   │   ├── AudioPlayer.java
│   │   ├── Logic.java
│   │   ├── MainMenu.java
│   │   ├── Pipe.java
│   │   ├── Player.java
│   │   └── View.java
│   └── .gitignore
│
├── Dokumentasi/
│   ├── main_menu.png
│   ├── gameplay.png
│   ├── score.png
│   ├── game_over.png
│   ├── restart.png
│   └── flappybird_demo.mp4
│
└── README.md
```

Pastikan nama dan lokasi file aset sesuai dengan struktur tersebut. Folder `Dokumentasi/` diisi dengan screenshot dan video dari program yang benar-benar dijalankan. Jika nama file dokumentasi berbeda, sesuaikan nama file pada bagian Dokumentasi di bawah.

## Desain Program

Program dibagi menjadi beberapa class agar setiap bagian memiliki tanggung jawab masing-masing.

| Class | Tanggung jawab |
|---|---|
| `App` | Titik awal program. Membuat `JFrame`, menyiapkan `CardLayout`, menghubungkan Main Menu dan tampilan game, serta mengatur perpindahan halaman. |
| `MainMenu` | Menampilkan halaman awal, background, judul game, serta tombol Play dan Exit. |
| `View` | Menampilkan background, burung, pipa, badge skor, dan panel Game Over menggunakan `JPanel` dan Java2D. |
| `Logic` | Mengatur game loop, Timer, gravitasi, gerakan burung dan pipa, input keyboard, deteksi tabrakan, skor, Game Over, restart, serta pemanggilan sound effect. |
| `Player` | Menyimpan data burung, seperti posisi, ukuran, gambar, dan kecepatan vertikal (`velocityY`). |
| `Pipe` | Menyimpan data obstacle pipa, seperti posisi, ukuran, gambar, dan status apakah pipa sudah dilewati (`passed`). |
| `AudioPlayer` | Memuat, memainkan, dan menghentikan sound effect menggunakan Java Sound API. |

### Hubungan antarclass

```text
App
├── MainMenu
└── View
    └── Logic
        ├── Player
        ├── Pipe (disimpan dalam ArrayList)
        └── AudioPlayer
```

`App` menyiapkan jendela dan perpindahan tampilan. `MainMenu` menangani pilihan Play dan Exit. Ketika pemain memilih Play, `App` menampilkan `View`. `View` mengambil data dari `Logic`, kemudian menggambar `Player` dan daftar `Pipe`. `Logic` memperbarui posisi objek menggunakan `Timer` serta menangani aturan permainan dan input keyboard.

## Alur Permainan

1. Program dijalankan dan menampilkan Main Menu.
2. Pemain menekan **Play** untuk membuka gameplay.
3. Permainan menunggu input pertama. Pemain menekan `SPACE` untuk memulai gerakan burung sekaligus membuat burung melompat.
4. Gravitasi membuat burung bergerak turun, sedangkan pipa muncul secara berkala dan bergerak dari kanan ke kiri.
5. Setiap kali burung berhasil melewati sepasang pipa, skor bertambah satu poin dan sound effect skor dimainkan.
6. Jika burung menabrak pipa atau jatuh ke batas bawah layar, permainan berhenti dan panel Game Over ditampilkan. Sound effect tabrakan dimainkan.
7. Pemain menekan `R` untuk mengulang permainan. Posisi burung dan skor diatur kembali, pipa lama dihapus, lalu permainan dapat dimainkan kembali.
8. Pemain dapat menekan **Exit** pada Main Menu untuk keluar dari program.

## Kontrol

| Tombol/Aksi | Fungsi |
|---|---|
| **Play** | Membuka gameplay dari Main Menu. |
| `SPACE` | Memulai permainan pada input pertama dan membuat burung melompat selama gameplay. |
| `R` | Mengulang permainan setelah Game Over. |
| **Exit** | Menutup aplikasi dari Main Menu. |

## Sound Effect

Project menggunakan tiga sound effect berikut:

| File audio | Penggunaan |
|---|---|
| `loncat.wav` | Diputar ketika pemain menekan `SPACE` untuk membuat burung melompat. |
| `score.wav` | Diputar ketika skor bertambah setelah burung melewati pipa. |
| `hit.wav` | Diputar ketika burung menabrak pipa atau jatuh sehingga permainan berakhir. |

## Cara Menjalankan Program

1. Pastikan **JDK** sudah terpasang dan konfigurasi JDK pada IntelliJ IDEA sudah benar.
2. Buka folder project `FlappyBird` melalui IntelliJ IDEA.
3. Pastikan folder `src/assets/` beserta gambar dan folder `audio/` tersedia.
4. Pastikan file audio menggunakan format WAV yang didukung Java Sound API.
5. Buka `App.java`.
6. Jalankan method `main()` melalui IntelliJ IDEA.
7. Pada Main Menu, tekan **Play** untuk membuka permainan.

## Dokumentasi Program

Tambahkan screenshot dan video hasil menjalankan program ke folder `Dokumentasi/`. Dokumentasi berikut digunakan untuk menunjukkan fitur yang telah diimplementasikan.

### 1. Main Menu

Simpan screenshot Main Menu sebagai `Dokumentasi/main_menu.png`.

![Main Menu](Dokumentasi/main_menu.png)

### 2. Gameplay

Simpan screenshot ketika permainan sedang berlangsung sebagai `Dokumentasi/gameplay.png`.

![Gameplay](Dokumentasi/gameplay.png)

### 3. Score

Simpan screenshot yang memperlihatkan skor setelah burung melewati pipa sebagai `Dokumentasi/score.png`.

![Score](Dokumentasi/score.png)

### 4. Game Over

Simpan screenshot ketika permainan berakhir sebagai `Dokumentasi/game_over.png`.

![Game Over](Dokumentasi/game_over.png)

### 5. Restart

Simpan screenshot setelah permainan berhasil diulang menggunakan tombol `R` sebagai `Dokumentasi/restart.png`.

![Restart](Dokumentasi/restart.png)

### 6. Video Demo

Simpan rekaman demonstrasi permainan sebagai `Dokumentasi/flappybird_demo.mp4`. Video sebaiknya memperlihatkan Main Menu, gameplay, kontrol burung, penambahan skor, Game Over, restart, dan sound effect.

[ Lihat Video Demo Flappy Bird ](Dokumentasi/flappybird_demo.mp4)

> **Catatan dokumentasi:** Tambahkan file screenshot dan video dengan nama di atas agar gambar dan tautan dapat ditampilkan dengan benar pada GitHub. Gunakan hasil tangkapan dari program yang benar-benar dijalankan, bukan gambar ilustrasi.

## Kredit Aset

Aset gambar dan audio disimpan di dalam `FlappyBird/src/assets/`. Cantumkan nama pembuat dan tautan sumber sesuai halaman unduhan aset yang digunakan, serta periksa lisensi setiap aset sebelum repository dipublikasikan.

- Background: `background.png`
- Gambar burung: `bird.png`
- Gambar pipa: `lowerPipe.png` dan `upperPipe.png`
- Sound effect: `hit.wav`, `loncat.wav`, dan `score.wav`

## Pengumpulan

Sebelum mengumpulkan project:

- Pastikan repository GitHub berstatus **Public**.
- Gunakan nama repository sesuai format yang ditentukan pada modul praktikum: `TP6DPBO2526C...`.
- Pastikan folder `FlappyBird/`, folder `Dokumentasi/`, dan file `README.md` tersedia pada branch default repository.
- Pastikan screenshot dan video dokumentasi sudah ditambahkan.
- Pastikan seluruh aset gambar dan audio yang diperlukan tersedia agar program dapat dijalankan.

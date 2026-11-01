# Flappy Bird 


## Janji
Saya Adinda Fildzah Hasya dengan NIM 2501218 mengerjakan Tugas Praktikum 6 pada Mata Kuliah Desain dan Pemrograman Berorientasi Objek (DPBO) untuk keberkahan-Nya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin


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
│   │   │       ├── flap.wav
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
│   └── FlappyBird.mp4
│
└── README.md
```


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
| `flap.wav` | Diputar ketika pemain menekan `SPACE` untuk membuat burung melompat. |
| `score.wav` | Diputar ketika skor bertambah setelah burung melewati pipa. |
| `hit.wav` | Diputar ketika burung menabrak pipa atau jatuh sehingga permainan berakhir. |


## Dokumentasi Program


https://github.com/user-attachments/assets/5a50493f-5343-4f13-8bef-c85f10f736a8


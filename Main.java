
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

class CustomerQueue {
    private Queue<String> queue = new LinkedList<>();

    public void addCustomer(String name) {
        queue.add(name);
        System.out.println(name + " telah ditambahkan ke antrean.");
    }

    public void serveCustomer() {
        if (queue.isEmpty()) {
            System.out.println("Tidak ada pelanggan dalam antrean.");
        } else {
            String nama = queue.poll();
            System.out.println("Melayani pelanggan: " + nama);
            displayQueue(); 
        }
    }

    public void displayQueue() {
        if (queue.isEmpty()) {
            System.out.println("Antrean kosong");
        } else {
            System.out.println("Pelanggan dalam antrean:");
            int no = 1;
            for (String pelanggan : queue) {
                System.out.println(no++ + ". " + pelanggan);
            }
        }
    }
}

class TextEditor {
    private Stack<String> undoStack = new Stack<>();
    private Stack<String> redoStack = new Stack<>();
    private String currentText = "";

    public void addText(String newText) {
        undoStack.push(currentText);
        currentText += newText;
        redoStack.clear();
        System.out.println("Teks sekarang: " + currentText);
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            redoStack.push(currentText);
            currentText = undoStack.pop();
        }
        System.out.println("Undo: " + currentText);
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            undoStack.push(currentText);
            currentText = redoStack.pop();
        }
        System.out.println("Redo: " + currentText);
    }

    public void showText() {
        System.out.println("Teks sekarang: " + currentText);
    }
}

class Mahasiswa {
    String nama;
    String nim;
    double nilai;
    Mahasiswa next;

    public Mahasiswa(String nama, String nim, double nilai) {
        this.nama = nama;
        this.nim = nim;
        this.nilai = nilai;
        this.next = null;
    }
}

class ManajemenMahasiswa {
    private Mahasiswa head;

    public void tambahMahasiswa(String nama, String nim, double nilai) {
        Mahasiswa baru = new Mahasiswa(nama, nim, nilai);
        if (head == null) {
            head = baru;
        } else {
            Mahasiswa temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = baru;
        }
        System.out.println("Mahasiswa berhasil ditambahkan: " + nama);
    }

    public void hapusMahasiswa(String nim) {
        if (head == null) {
            System.out.println("Daftar mahasiswa kosong.");
            return;
        }
        if (head.nim.equalsIgnoreCase(nim)) {
            System.out.println("Mahasiswa " + head.nama + " berhasil dihapus.");
            head = head.next;
            return;
        }
        Mahasiswa temp = head;
        while (temp.next != null && !temp.next.nim.equalsIgnoreCase(nim)) {
            temp = temp.next;
        }
        if (temp.next != null) {
            System.out.println("Mahasiswa " + temp.next.nama + " berhasil dihapus.");
            temp.next = temp.next.next;
        } else {
            System.out.println("Mahasiswa dengan NIM " + nim + " tidak ditemukan.");
        }
    }

    public void updateMahasiswa(String nim, double nilaiBaru) {
        Mahasiswa temp = head;
        while (temp != null) {
            if (temp.nim.equalsIgnoreCase(nim)) {
                System.out.println("Mengupdate nilai mahasiswa (" + temp.nama + " -> " + nilaiBaru + ")");
                temp.nilai = nilaiBaru;
                return;
            }
            temp = temp.next;
        }
        System.out.println("Mahasiswa dengan NIM " + nim + " tidak ditemukan.");
    }

    public void tampilkanMahasiswa() {
        if (head == null) {
            System.out.println("Daftar mahasiswa kosong.");
            return;
        }
        Mahasiswa temp = head;
        System.out.println("--- Daftar Mahasiswa ---");
        while (temp != null) {
            System.out.println("NIM: " + temp.nim + " , Nama: " + temp.nama + " , Nilai: " + temp.nilai);
            temp = temp.next;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CustomerQueue customerQueue = new CustomerQueue();
        TextEditor textEditor = new TextEditor();
        ManajemenMahasiswa manajemenMahasiswa = new ManajemenMahasiswa();

        int pilihan = -1;
        while (pilihan != 0) {
            System.out.println("\n=== MENU UTAMA ===");
            System.out.println("1. Antrean Customer Service (Queue)");
            System.out.println("2. Editor Teks Undo/Redo (Stack)");
            System.out.println("3. Manajemen Data Mahasiswa (Linked List)");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");
            pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1:
                    menuQueue(scanner, customerQueue);
                    break;
                case 2:
                    menuStack(scanner, textEditor);
                    break;
                case 3:
                    menuLinkedList(scanner, manajemenMahasiswa);
                    break;
                case 0:
                    System.out.println("Terima kasih. Program selesai.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }

        scanner.close();
    }

    public static void menuQueue(Scanner scanner, CustomerQueue queue) {
        int pilih;
        do {
            System.out.println("\n--- Menu Antrean Customer Service ---");
            System.out.println("1. Tambah pelanggan");
            System.out.println("2. Layani pelanggan");
            System.out.println("3. Lihat antrean");
            System.out.println("0. Kembali");
            System.out.print("Pilihan: ");
            pilih = scanner.nextInt();
            scanner.nextLine();
            switch (pilih) {
                case 1:
                    System.out.print("Nama pelanggan: ");
                    String nama = scanner.nextLine();
                    queue.addCustomer(nama);
                    break;
                case 2:
                    queue.serveCustomer();
                    break;
                case 3:
                    queue.displayQueue();
                    break;
            }
        } while (pilih != 0);
    }

    public static void menuStack(Scanner scanner, TextEditor editor) {
        int pilih;
        do {
            System.out.println("\n--- Menu Editor Teks ---");
            System.out.println("1. Tambah teks");
            System.out.println("2. Undo");
            System.out.println("3. Redo");
            System.out.println("4. Lihat teks saat ini");
            System.out.println("0. Kembali");
            System.out.print("Pilihan: ");
            pilih = scanner.nextInt();
            scanner.nextLine();
            switch (pilih) {
                case 1:
                    System.out.print("Masukkan teks: ");
                    String teks = scanner.nextLine();
                    editor.addText(teks);
                    break;
                case 2:
                    editor.undo();
                    break;
                case 3:
                    editor.redo();
                    break;
                case 4:
                    editor.showText();
                    break;
            }
        } while (pilih != 0);
    }

    public static void menuLinkedList(Scanner scanner, ManajemenMahasiswa mm) {
        int pilih;
        do {
            System.out.println("\n--- Menu Manajemen Data Mahasiswa ---");
            System.out.println("1. Tambah mahasiswa");
            System.out.println("2. Hapus mahasiswa");
            System.out.println("3. Update data mahasiswa");
            System.out.println("4. Tampilkan daftar mahasiswa");
            System.out.println("0. Kembali");
            System.out.print("Pilihan: ");
            pilih = scanner.nextInt();
            scanner.nextLine();

            switch (pilih) {
                case 1:
                    System.out.print("Nama mahasiswa: ");
                    String nama = scanner.nextLine();
                    System.out.print("NIM: ");
                    String nim = scanner.nextLine();
                    System.out.print("Nilai: ");
                    double nilai = scanner.nextDouble();
                    scanner.nextLine();
                    mm.tambahMahasiswa(nama, nim, nilai);
                    break;
                case 2:
                    System.out.print("Masukkan NIM mahasiswa yang ingin dihapus: ");
                    String nimHapus = scanner.nextLine();
                    mm.hapusMahasiswa(nimHapus);
                    break;
                case 3:
                    System.out.print("Masukkan NIM mahasiswa yang ingin diupdate nilainya: ");
                    String nimUpdate = scanner.nextLine();
                    System.out.print("Nilai baru: ");
                    double nilaiBaru = scanner.nextDouble();
                    scanner.nextLine();
                    mm.updateMahasiswa(nimUpdate, nilaiBaru);
                    break;
                case 4:
                    mm.tampilkanMahasiswa();
                    break;
                case 0:
                    System.out.println("Kembali ke menu utama...");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (pilih != 0);
    }
}

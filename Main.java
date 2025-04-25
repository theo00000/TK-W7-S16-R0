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
            System.out.println("Melayani: " + queue.poll());
        }
    }

    public void displayQueue() {
        if (queue.isEmpty()) {
            System.out.println("Antrean kosong.");
        } else {
            System.out.println("Antrean saat ini: " + queue);
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

class Pasien {
    String nama;
    int umur;
    String keluhan;
    Pasien next;

    public Pasien(String nama, int umur, String keluhan) {
        this.nama = nama;
        this.umur = umur;
        this.keluhan = keluhan;
    }
}

class AntreanRumahSakit {
    private Pasien head;

    public void tambahPasien(String nama, int umur, String keluhan) {
        Pasien baru = new Pasien(nama, umur, keluhan);
        if (head == null) {
            head = baru;
        } else {
            Pasien temp = head;
            while (temp.next != null) temp = temp.next;
            temp.next = baru;
        }
        System.out.println("Pasien " + nama + " berhasil ditambahkan ke antrean.");
    }

    public void hapusPasien(String nama) {
        if (head == null) {
            System.out.println("Antrean kosong.");
            return;
        }
        if (head.nama.equalsIgnoreCase(nama)) {
            head = head.next;
            System.out.println("Pasien " + nama + " telah dihapus.");
            return;
        }
        Pasien temp = head;
        while (temp.next != null && !temp.next.nama.equalsIgnoreCase(nama)) {
            temp = temp.next;
        }
        if (temp.next != null) {
            temp.next = temp.next.next;
            System.out.println("Pasien " + nama + " telah dihapus.");
        } else {
            System.out.println("Pasien tidak ditemukan.");
        }
    }

    public void updatePasien(String nama, int umurBaru, String keluhanBaru) {
        Pasien temp = head;
        while (temp != null) {
            if (temp.nama.equalsIgnoreCase(nama)) {
                temp.umur = umurBaru;
                temp.keluhan = keluhanBaru;
                System.out.println("Data pasien " + nama + " telah diperbarui.");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Pasien tidak ditemukan.");
    }

    public void tampilkanPasien() {
        if (head == null) {
            System.out.println("Antrean pasien kosong.");
            return;
        }
        Pasien temp = head;
        while (temp != null) {
            System.out.println("Nama: " + temp.nama + ", Umur: " + temp.umur + ", Keluhan: " + temp.keluhan);
            temp = temp.next;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CustomerQueue customerQueue = new CustomerQueue();
        TextEditor textEditor = new TextEditor();
        AntreanRumahSakit antreanRS = new AntreanRumahSakit();

        int pilihan = -1;
        while (pilihan != 0) {
            System.out.println("\n=== MENU UTAMA ===");
            System.out.println("1. Antrean Customer Service (Queue)");
            System.out.println("2. Editor Teks Undo/Redo (Stack)");
            System.out.println("3. Antrean Rumah Sakit (Linked List)");
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
                    menuLinkedList(scanner, antreanRS);
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

    public static void menuLinkedList(Scanner scanner, AntreanRumahSakit rs) {
        int pilih;
        do {
            System.out.println("\n--- Menu Antrean Rumah Sakit ---");
            System.out.println("1. Tambah pasien");
            System.out.println("2. Hapus pasien");
            System.out.println("3. Update data pasien");
            System.out.println("4. Tampilkan antrean");
            System.out.println("0. Kembali");
            System.out.print("Pilihan: ");
            pilih = scanner.nextInt();
            scanner.nextLine();
            switch (pilih) {
                case 1:
                    System.out.print("Nama pasien: ");
                    String nama = scanner.nextLine();
                    System.out.print("Umur: ");
                    int umur = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Keluhan: ");
                    String keluhan = scanner.nextLine();
                    rs.tambahPasien(nama, umur, keluhan);
                    break;
                case 2:
                    System.out.print("Nama pasien yang akan dihapus: ");
                    String hapus = scanner.nextLine();
                    rs.hapusPasien(hapus);
                    break;
                case 3:
                    System.out.print("Nama pasien: ");
                    String update = scanner.nextLine();
                    System.out.print("Umur baru: ");
                    int umurBaru = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Keluhan baru: ");
                    String keluhanBaru = scanner.nextLine();
                    rs.updatePasien(update, umurBaru, keluhanBaru);
                    break;
                case 4:
                    rs.tampilkanPasien();
                    break;
            }
        } while (pilih != 0);
    }
}

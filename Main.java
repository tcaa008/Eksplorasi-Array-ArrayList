import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bank bank = new Bank();

        // Data awal nasabah
        bank.addCustomer("Budi", "Santoso");
        bank.addCustomer("Siti", "Aminah");

        // Rekening awal
        Customer nasabah1 = bank.getCustomer(0);
        nasabah1.setAccount(new Account(500000));

        Customer nasabah2 = bank.getCustomer(1);
        nasabah2.setAccount(new Account(1000000));

        boolean berjalan = true;

        while (berjalan) {
            System.out.println("\n=== SISTEM ATM BANK ===");
            System.out.println("1. Tambah Nasabah");
            System.out.println("2. Buka Rekening Baru");
            System.out.println("3. Cek Saldo");
            System.out.println("4. Setor Tunai (Deposit)");
            System.out.println("5. Tarik Tunai (Withdraw)");
            System.out.println("6. Keluar");
            System.out.print("Pilih menu (1-6): ");

            int pilihan = scanner.nextInt();
            scanner.nextLine(); 

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan Nama Depan: ");
                    String namaDepan = scanner.nextLine();
                    System.out.print("Masukkan Nama Belakang: ");
                    String namaBelakang = scanner.nextLine();
                    bank.addCustomer(namaDepan, namaBelakang);
                    System.out.println("Nasabah berhasil ditambahkan!");
                    break;

                case 2:
                    tampilkanNasabah(bank);
                    if (bank.getNumOfCustomers() == 0) break;
                    
                    System.out.print("Pilih nomor nasabah: ");
                    int idxNasabahRek = scanner.nextInt() - 1;
                    Customer nasabahRek = bank.getCustomer(idxNasabahRek);

                    if (nasabahRek != null) {
                        System.out.print("Masukkan saldo awal: ");
                        double saldoAwal = scanner.nextDouble();
                        nasabahRek.setAccount(new Account(saldoAwal));
                        System.out.println("Rekening baru berhasil dibuat!");
                    } else {
                        System.out.println("Nasabah tidak ditemukan!");
                    }
                    break;

                case 3:
                    Account rekCek = pilihRekening(bank, scanner);
                    if (rekCek != null) {
                        System.out.printf("Saldo saat ini: Rp %.2f\n", rekCek.getBalance());
                    }
                    break;

                case 4:
                    Account rekSetor = pilihRekening(bank, scanner);
                    if (rekSetor != null) {
                        System.out.print("Masukkan jumlah setoran: ");
                        double setoran = scanner.nextDouble();
                        if (rekSetor.deposit(setoran)) {
                            System.out.println("Setoran berhasil!");
                        } else {
                            System.out.println("Jumlah setoran tidak valid!");
                        }
                    }
                    break;

                case 5:
                    Account rekTarik = pilihRekening(bank, scanner);
                    if (rekTarik != null) {
                        System.out.print("Masukkan jumlah penarikan: ");
                        double penarikan = scanner.nextDouble();
                        if (rekTarik.withdraw(penarikan)) {
                            System.out.println("Penarikan berhasil!");
                        } else {
                            System.out.println("Penarikan gagal! Saldo tidak cukup.");
                        }
                    }
                    break;

                case 6:
                    berjalan = false;
                    System.out.println("Terima kasih telah menggunakan layanan ATM.");
                    break;

                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
        scanner.close();
    }

    private static void tampilkanNasabah(Bank bank) {
        if (bank.getNumOfCustomers() == 0) {
            System.out.println("Belum ada nasabah.");
            return;
        }
        System.out.println("\n--- Daftar Nasabah ---");
        for (int i = 0; i < bank.getNumOfCustomers(); i++) {
            Customer c = bank.getCustomer(i);
            System.out.println((i + 1) + ". " + c.getFirstName() + " " + c.getLastName() + " (Jumlah Rekening: " + c.getNumOfAccounts() + ")");
        }
    }

    private static Account pilihRekening(Bank bank, Scanner scanner) {
        tampilkanNasabah(bank);
        if (bank.getNumOfCustomers() == 0) return null;

        System.out.print("Pilih nomor nasabah: ");
        int idxNasabah = scanner.nextInt() - 1;
        Customer c = bank.getCustomer(idxNasabah);

        if (c == null) {
            System.out.println("Nasabah tidak ditemukan!");
            return null;
        }

        if (c.getNumOfAccounts() == 0) {
            System.out.println("Nasabah ini belum memiliki rekening!");
            return null;
        }

        System.out.println("Daftar Rekening milik " + c.getFirstName() + ":");
        for (int i = 0; i < c.getNumOfAccounts(); i++) {
            System.out.println((i + 1) + ". Rekening ke-" + (i + 1));
        }

        System.out.print("Pilih nomor rekening: ");
        int idxRekening = scanner.nextInt() - 1;
        Account rek = c.getAccount(idxRekening);

        if (rek == null) {
            System.out.println("Rekening tidak ditemukan!");
        }
        return rek;
    }
}
import java.util.ArrayList;

public class Customer {
    private String namaDepan;
    private String namaBelakang;
    private ArrayList<Account> daftarRekening;

    public Customer(String f, String l) {
        this.namaDepan = f;
        this.namaBelakang = l;
        this.daftarRekening = new ArrayList<>();
    }

    public String getFirstName() {
        return namaDepan;
    }

    public String getLastName() {
        return namaBelakang;
    }

    public void setAccount(Account rekening) {
        daftarRekening.add(rekening);
    }

    public Account getAccount(int indeks) {
        if (indeks >= 0 && indeks < daftarRekening.size()) {
            return daftarRekening.get(indeks);
        }
        return null;
    }

    public int getNumOfAccounts() {
        return daftarRekening.size();
    }
}
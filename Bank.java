import java.util.ArrayList;

public class Bank {
    private ArrayList<Customer> daftarNasabah;

    public Bank() {
        this.daftarNasabah = new ArrayList<>();
    }

    public void addCustomer(String f, String l) {
        daftarNasabah.add(new Customer(f, l));
    }

    public int getNumOfCustomers() {
        return daftarNasabah.size();
    }

    public Customer getCustomer(int indeks) {
        if (indeks >= 0 && indeks < daftarNasabah.size()) {
            return daftarNasabah.get(indeks);
        }
        return null;
    }
}
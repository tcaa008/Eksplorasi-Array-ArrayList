public class Account {
    private double saldo;

    public Account(double saldoAwal) {
        if (saldoAwal >= 0) {
            this.saldo = saldoAwal;
        } else {
            this.saldo = 0;
        }
    }

    public double getBalance() {
        return saldo;
    }

    public boolean deposit(double jumlah) {
        if (jumlah > 0) {
            saldo += jumlah;
            return true;
        }
        return false;
    }

    public boolean withdraw(double jumlah) {
        if (jumlah > 0 && saldo >= jumlah) {
            saldo -= jumlah;
            return true;
        }
        return false;
    }
}
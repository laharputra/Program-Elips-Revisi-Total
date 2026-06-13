public class JuringElips extends Elips implements Runnable {

    public double sudutDerajat, luasJuring, kelilingJuring;

    public JuringElips(double sumbuA, double sumbuB, double sudutDerajat, boolean isManual) {
        super(sumbuA, sumbuB, isManual);
        if (sudutDerajat <= 0 || sudutDerajat > 360) {
            throw new IllegalArgumentException("Sudut juring elips harus lebih dari 0 dan maksimal 360 derajat!");
        }
        this.sudutDerajat = sudutDerajat;
    }

    public double hitungLuasJuring() {
        if (super.luas <= 0) {
            throw new IllegalStateException("Gagal menghitung luas juring: luas elips dari superclass belum dihitung.");
        }
        this.luasJuring = (this.sudutDerajat / 360.0) * super.luas;
        return this.luasJuring;
    }

    public double hitungLuasJuring(double sumbuA, double sumbuB, double sudutDerajat) {
        double luasElips = super.hitungLuas(sumbuA, sumbuB);
        this.luasJuring = (sudutDerajat / 360.0) * luasElips;
        return this.luasJuring;
    }

    public double hitungKelilingJuring() {
        if (super.keliling <= 0) {
            throw new IllegalStateException("Gagal menghitung keliling juring: keliling elips dari superclass belum dihitung.");
        }
        double panjangBusur = (this.sudutDerajat / 360.0) * super.keliling;
        double rataRataJariJari = (super.sumbuA + super.sumbuB) / 2.0;
        this.kelilingJuring = panjangBusur + (2 * rataRataJariJari);
        return this.kelilingJuring;
    }

    public double hitungKelilingJuring(double sumbuA, double sumbuB, double sudutDerajat) {
        double kelilingElips = super.hitungKeliling(sumbuA, sumbuB);
        double panjangBusur = (sudutDerajat / 360.0) * kelilingElips;
        double rataRataJariJari = (sumbuA + sumbuB) / 2.0;
        this.kelilingJuring = panjangBusur + (2 * rataRataJariJari);
        return this.kelilingJuring;
    }

    @Override
    public void run() {
        super.run();
        if (this.isManual) {
            this.hitungLuasJuring(sumbuA, sumbuB, sudutDerajat);
            this.hitungKelilingJuring(sumbuA, sumbuB, sudutDerajat);
        } else {
            this.hitungLuasJuring();
            this.hitungKelilingJuring();
        }
    }
}

public class LimasElips extends Elips implements Runnable {

    public double tinggiLimas, volumeLimasElips, luasPermukaanLimasElips, luasSelimutLimasElips;

    public LimasElips(double sumbuA, double sumbuB, double tinggiLimas, boolean isManual) {
        super(sumbuA, sumbuB, isManual);
        if (tinggiLimas <= 0) {
            throw new IllegalArgumentException("Tinggi limas/kerucut elips harus lebih besar dari 0!");
        }
        this.tinggiLimas = tinggiLimas;
    }

    public double hitungVolumeLimasElips() {
        if (super.luas <= 0) {
            throw new IllegalStateException("Gagal menghitung volume limas elips: luas elips dari superclass belum dihitung.");
        }
        this.volumeLimasElips = (1.0 / 3.0) * super.luas * this.tinggiLimas;
        return this.volumeLimasElips;
    }

    public double hitungVolumeLimasElips(double sumbuA, double sumbuB, double tinggiLimas) {
        double luasAlas = super.hitungLuas(sumbuA, sumbuB);
        this.volumeLimasElips = (1.0 / 3.0) * luasAlas * tinggiLimas;
        return this.volumeLimasElips;
    }

    public double hitungLuasPermukaanLimasElips() {
        if (super.luas <= 0 || super.keliling <= 0) {
            throw new IllegalStateException("Gagal menghitung luas permukaan limas elips: luas/keliling elips dari superclass belum dihitung.");
        }
        double garisPelukisA = Math.sqrt(Math.pow(super.sumbuA, 2) + Math.pow(this.tinggiLimas, 2));
        double garisPelukisB = Math.sqrt(Math.pow(super.sumbuB, 2) + Math.pow(this.tinggiLimas, 2));
        double rataGarisPelukis = (garisPelukisA + garisPelukisB) / 2.0;
        this.luasSelimutLimasElips = 0.5 * super.keliling * rataGarisPelukis;
        this.luasPermukaanLimasElips = super.luas + this.luasSelimutLimasElips;
        return this.luasPermukaanLimasElips;
    }

    public double hitungLuasPermukaanLimasElips(double sumbuA, double sumbuB, double tinggiLimas) {
        double luasAlas = super.hitungLuas(sumbuA, sumbuB);
        double kelilingAlas = super.hitungKeliling(sumbuA, sumbuB);
        double garisPelukisA = Math.sqrt(Math.pow(sumbuA, 2) + Math.pow(tinggiLimas, 2));
        double garisPelukisB = Math.sqrt(Math.pow(sumbuB, 2) + Math.pow(tinggiLimas, 2));
        double rataGarisPelukis = (garisPelukisA + garisPelukisB) / 2.0;
        this.luasSelimutLimasElips = 0.5 * kelilingAlas * rataGarisPelukis;
        this.luasPermukaanLimasElips = luasAlas + this.luasSelimutLimasElips;
        return this.luasPermukaanLimasElips;
    }

    @Override
    public void run() {
        super.run();
        if (this.isManual) {
            this.hitungLuasPermukaanLimasElips(sumbuA, sumbuB, tinggiLimas);
            this.hitungVolumeLimasElips(sumbuA, sumbuB, tinggiLimas);
        } else {
            this.hitungLuasPermukaanLimasElips();
            this.hitungVolumeLimasElips();
        }
    }
}

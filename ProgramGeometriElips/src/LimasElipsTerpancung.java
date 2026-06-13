public class LimasElipsTerpancung extends LimasElips implements Runnable, Elips3D {

    public double faktorAtas, luasAtas, kelilingAtas, volumeTerpancung, luasPermukaanTerpancung, luasSelimutTerpancung;

    public LimasElipsTerpancung(double sumbuA, double sumbuB, double tinggiLimas, double faktorAtas, boolean isManual) {
        super(sumbuA, sumbuB, tinggiLimas, isManual);
        if (faktorAtas <= 0 || faktorAtas >= 1) {
            throw new IllegalArgumentException("Faktor atas limas elips terpancung harus lebih dari 0 dan kurang dari 1!");
        }
        this.faktorAtas = faktorAtas;
    }

    @Override
    public double hitungVolume() {
        super.hitungLuas();
        return this.hitungVolumeTerpancung();
    }

    @Override
    public double hitungLuasPermukaan() {
        super.hitungLuas();
        super.hitungKeliling();
        return this.hitungLuasPermukaanTerpancung();
    }

    public double hitungVolumeTerpancung() {
        if (super.luas <= 0) {
            throw new IllegalStateException("Gagal menghitung volume terpancung: luas alas elips dari superclass belum dihitung.");
        }
        this.luasAtas = super.luas * Math.pow(this.faktorAtas, 2);
        this.volumeTerpancung = (this.tinggiLimas / 3.0) * (super.luas + this.luasAtas + Math.sqrt(super.luas * this.luasAtas));
        return this.volumeTerpancung;
    }

    public double hitungVolumeTerpancung(double sumbuA, double sumbuB, double tinggiLimas, double faktorAtas) {
        double luasAlas = super.hitungLuas(sumbuA, sumbuB);
        this.luasAtas = luasAlas * Math.pow(faktorAtas, 2);
        this.volumeTerpancung = (tinggiLimas / 3.0) * (luasAlas + this.luasAtas + Math.sqrt(luasAlas * this.luasAtas));
        return this.volumeTerpancung;
    }

    public double hitungLuasPermukaanTerpancung() {
        if (super.luas <= 0 || super.keliling <= 0) {
            throw new IllegalStateException("Gagal menghitung luas permukaan terpancung: luas/keliling alas belum dihitung.");
        }
        this.luasAtas = super.luas * Math.pow(this.faktorAtas, 2);
        this.kelilingAtas = super.keliling * this.faktorAtas;

        double selisihA = super.sumbuA - (super.sumbuA * this.faktorAtas);
        double selisihB = super.sumbuB - (super.sumbuB * this.faktorAtas);
        double garisPelukisA = Math.sqrt(Math.pow(selisihA, 2) + Math.pow(this.tinggiLimas, 2));
        double garisPelukisB = Math.sqrt(Math.pow(selisihB, 2) + Math.pow(this.tinggiLimas, 2));
        double rataGarisPelukis = (garisPelukisA + garisPelukisB) / 2.0;

        this.luasSelimutTerpancung = 0.5 * (super.keliling + this.kelilingAtas) * rataGarisPelukis;
        this.luasPermukaanTerpancung = super.luas + this.luasAtas + this.luasSelimutTerpancung;
        return this.luasPermukaanTerpancung;
    }

    public double hitungLuasPermukaanTerpancung(double sumbuA, double sumbuB, double tinggiLimas, double faktorAtas) {
        double luasAlas = super.hitungLuas(sumbuA, sumbuB);
        double kelilingAlas = super.hitungKeliling(sumbuA, sumbuB);
        this.luasAtas = luasAlas * Math.pow(faktorAtas, 2);
        this.kelilingAtas = kelilingAlas * faktorAtas;

        double selisihA = sumbuA - (sumbuA * faktorAtas);
        double selisihB = sumbuB - (sumbuB * faktorAtas);
        double garisPelukisA = Math.sqrt(Math.pow(selisihA, 2) + Math.pow(tinggiLimas, 2));
        double garisPelukisB = Math.sqrt(Math.pow(selisihB, 2) + Math.pow(tinggiLimas, 2));
        double rataGarisPelukis = (garisPelukisA + garisPelukisB) / 2.0;

        this.luasSelimutTerpancung = 0.5 * (kelilingAlas + this.kelilingAtas) * rataGarisPelukis;
        this.luasPermukaanTerpancung = luasAlas + this.luasAtas + this.luasSelimutTerpancung;
        return this.luasPermukaanTerpancung;
    }

    @Override
    public void run() {
        this.hitungLuasPermukaan();
        this.hitungVolume();
    }
}

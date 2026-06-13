public class JuringElips3D extends BolaElips implements Runnable {

    public double sudutDerajat, tinggiJuring, luasAlasJuring, kelilingAlasJuring, volumeJuringElips3D, luasPermukaanJuringElips3D;

    public JuringElips3D(double sumbuA, double sumbuB, double tinggiJuring, double sudutDerajat, boolean isManual) {
        super(sumbuA, sumbuB, tinggiJuring, isManual);
        if (tinggiJuring <= 0) {
            throw new IllegalArgumentException("Tinggi juring elips 3D harus lebih besar dari 0!");
        }
        if (sudutDerajat <= 0 || sudutDerajat > 360) {
            throw new IllegalArgumentException("Sudut juring elips 3D harus lebih dari 0 dan maksimal 360 derajat!");
        }
        this.tinggiJuring = tinggiJuring;
        this.sudutDerajat = sudutDerajat;
    }

    public double hitungVolumeJuringElips3D() {
        this.luasAlasJuring = (this.sudutDerajat / 360.0) * super.luas;
        this.volumeJuringElips3D = this.luasAlasJuring * this.tinggiJuring;
        return this.volumeJuringElips3D;
    }

    public double hitungLuasPermukaanJuringElips3D() {
        double panjangBusur = (this.sudutDerajat / 360.0) * super.keliling;
        double rataRataJariJari = (super.sumbuA + super.sumbuB) / 2.0;
        this.kelilingAlasJuring = panjangBusur + (2 * rataRataJariJari);
        this.luasAlasJuring = (this.sudutDerajat / 360.0) * super.luas;
        this.luasPermukaanJuringElips3D = (2 * this.luasAlasJuring) + (this.kelilingAlasJuring * this.tinggiJuring);
        return this.luasPermukaanJuringElips3D;
    }

    @Override
    public void run() {
        super.run();
        this.hitungLuasPermukaanJuringElips3D();
        this.hitungVolumeJuringElips3D();
    }
}

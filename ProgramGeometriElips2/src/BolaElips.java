public class BolaElips extends Elips implements Runnable {

    public double sumbuC, volumeBolaElips, luasPermukaanBolaElips;

    public BolaElips(double sumbuA, double sumbuB, double sumbuC, boolean isManual) {
        super(sumbuA, sumbuB, isManual);
        if (sumbuC <= 0) {
            throw new IllegalArgumentException("Nilai sumbu C bola elips harus lebih besar dari 0!");
        }
        this.sumbuC = sumbuC;
    }

    public double hitungVolumeBolaElips() {
        if (super.luas <= 0) {
            throw new IllegalStateException("Gagal menghitung volume bola elips: luas elips dari superclass belum dihitung.");
        }
        // Volume ellipsoid = 4/3 * luas elips alas * sumbu C.
        this.volumeBolaElips = (4.0 / 3.0) * super.luas * this.sumbuC;
        return this.volumeBolaElips;
    }

    public double hitungVolumeBolaElips(double sumbuA, double sumbuB, double sumbuC) {
        double luasAlas = super.hitungLuas(sumbuA, sumbuB);
        this.volumeBolaElips = (4.0 / 3.0) * luasAlas * sumbuC;
        return this.volumeBolaElips;
    }

    public double hitungLuasPermukaanBolaElips() {
        double p = 1.6075;
        this.luasPermukaanBolaElips = 4 * Math.PI * Math.pow(
                (Math.pow(super.sumbuA * super.sumbuB, p)
                + Math.pow(super.sumbuA * this.sumbuC, p)
                + Math.pow(super.sumbuB * this.sumbuC, p)) / 3.0,
                1.0 / p
        );
        return this.luasPermukaanBolaElips;
    }

    public double hitungLuasPermukaanBolaElips(double sumbuA, double sumbuB, double sumbuC) {
        double p = 1.6075;
        this.luasPermukaanBolaElips = 4 * Math.PI * Math.pow(
                (Math.pow(sumbuA * sumbuB, p)
                + Math.pow(sumbuA * sumbuC, p)
                + Math.pow(sumbuB * sumbuC, p)) / 3.0,
                1.0 / p
        );
        return this.luasPermukaanBolaElips;
    }

    @Override
    public void run() {
        super.run();
        if (this.isManual) {
            this.hitungLuasPermukaanBolaElips(sumbuA, sumbuB, sumbuC);
            this.hitungVolumeBolaElips(sumbuA, sumbuB, sumbuC);
        } else {
            this.hitungLuasPermukaanBolaElips();
            this.hitungVolumeBolaElips();
        }
    }
}

public class TemberengElips3D extends BolaElips implements Runnable, Elips3D {

    public double sudutDerajat, tinggiTembereng, luasAlasTembereng, kelilingAlasTembereng, volumeTemberengElips3D, luasPermukaanTemberengElips3D;

    public TemberengElips3D(double sumbuA, double sumbuB, double tinggiTembereng, double sudutDerajat, boolean isManual) {
        super(sumbuA, sumbuB, tinggiTembereng, isManual);
        if (tinggiTembereng <= 0) {
            throw new IllegalArgumentException("Tinggi tembereng elips 3D harus lebih besar dari 0!");
        }
        if (sudutDerajat <= 0 || sudutDerajat >= 360) {
            throw new IllegalArgumentException("Sudut tembereng elips 3D harus lebih dari 0 dan kurang dari 360 derajat!");
        }
        this.tinggiTembereng = tinggiTembereng;
        this.sudutDerajat = sudutDerajat;
    }

    @Override
    public double hitungVolume() {
        super.hitungLuas();
        return this.hitungVolumeTemberengElips3D();
    }

    @Override
    public double hitungLuasPermukaan() {
        super.hitungLuas();
        super.hitungKeliling();
        return this.hitungLuasPermukaanTemberengElips3D();
    }

    public double hitungVolumeTemberengElips3D() {
        double theta = Math.toRadians(this.sudutDerajat);
        this.luasAlasTembereng = 0.5 * super.sumbuA * super.sumbuB * (theta - Math.sin(theta));
        this.volumeTemberengElips3D = this.luasAlasTembereng * this.tinggiTembereng;
        return this.volumeTemberengElips3D;
    }

    public double hitungLuasPermukaanTemberengElips3D() {
        double theta = Math.toRadians(this.sudutDerajat);
        double panjangBusur = (this.sudutDerajat / 360.0) * super.keliling;
        double taliBusur = 2 * Math.sqrt(
                Math.pow(super.sumbuA * Math.sin(theta / 2.0), 2)
                + Math.pow(super.sumbuB * (1 - Math.cos(theta / 2.0)), 2)
        );
        this.kelilingAlasTembereng = panjangBusur + taliBusur;
        this.luasAlasTembereng = 0.5 * super.sumbuA * super.sumbuB * (theta - Math.sin(theta));
        this.luasPermukaanTemberengElips3D = (2 * this.luasAlasTembereng) + (this.kelilingAlasTembereng * this.tinggiTembereng);
        return this.luasPermukaanTemberengElips3D;
    }

    @Override
    public void run() {
        this.hitungLuasPermukaan();
        this.hitungVolume();
    }
}

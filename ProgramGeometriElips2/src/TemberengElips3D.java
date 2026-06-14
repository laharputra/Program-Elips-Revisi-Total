public class TemberengElips3D extends BolaElips implements Runnable {

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

    public double hitungVolumeTemberengElips3D() {
        double theta = Math.toRadians(this.sudutDerajat);
        this.luasAlasTembereng = 0.5 * super.sumbuA * super.sumbuB * (theta - Math.sin(theta));
        this.volumeTemberengElips3D = this.luasAlasTembereng * this.tinggiTembereng;
        return this.volumeTemberengElips3D;
    }

    public double hitungVolumeTemberengElips3D(double sumbuA, double sumbuB, double tinggiTembereng, double sudutDerajat) {
        double theta = Math.toRadians(sudutDerajat);
        this.luasAlasTembereng = 0.5 * sumbuA * sumbuB * (theta - Math.sin(theta));
        this.volumeTemberengElips3D = this.luasAlasTembereng * tinggiTembereng;
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

    public double hitungLuasPermukaanTemberengElips3D(double sumbuA, double sumbuB, double tinggiTembereng, double sudutDerajat) {
        double kelilingElips = super.hitungKeliling(sumbuA, sumbuB);
        double theta = Math.toRadians(sudutDerajat);
        double panjangBusur = (sudutDerajat / 360.0) * kelilingElips;
        double taliBusur = 2 * Math.sqrt(
                Math.pow(sumbuA * Math.sin(theta / 2.0), 2)
                + Math.pow(sumbuB * (1 - Math.cos(theta / 2.0)), 2)
        );
        this.kelilingAlasTembereng = panjangBusur + taliBusur;
        this.luasAlasTembereng = 0.5 * sumbuA * sumbuB * (theta - Math.sin(theta));
        this.luasPermukaanTemberengElips3D = (2 * this.luasAlasTembereng) + (this.kelilingAlasTembereng * tinggiTembereng);
        return this.luasPermukaanTemberengElips3D;
    }

    @Override
    public void run() {
        super.run();
        if (this.isManual) {
            this.hitungLuasPermukaanTemberengElips3D(sumbuA, sumbuB, tinggiTembereng, sudutDerajat);
            this.hitungVolumeTemberengElips3D(sumbuA, sumbuB, tinggiTembereng, sudutDerajat);
        } else {
            this.hitungLuasPermukaanTemberengElips3D();
            this.hitungVolumeTemberengElips3D();
        }
    }
}

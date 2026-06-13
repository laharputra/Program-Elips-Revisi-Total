public class TemberengElips extends Elips implements Runnable {

    public double sudutDerajat, luasTembereng, kelilingTembereng;

    public TemberengElips(double sumbuA, double sumbuB, double sudutDerajat, boolean isManual) {
        super(sumbuA, sumbuB, isManual);
        if (sudutDerajat <= 0 || sudutDerajat >= 360) {
            throw new IllegalArgumentException("Sudut tembereng elips harus lebih dari 0 dan kurang dari 360 derajat!");
        }
        this.sudutDerajat = sudutDerajat;
    }

    @Override
    public double hitungLuas() {
        super.hitungLuas();
        return this.hitungLuasTembereng();
    }

    @Override
    public double hitungKeliling() {
        super.hitungKeliling();
        return this.hitungKelilingTembereng();
    }

    public double hitungLuasTembereng() {
        double theta = Math.toRadians(this.sudutDerajat);
        this.luasTembereng = 0.5 * super.sumbuA * super.sumbuB * (theta - Math.sin(theta));
        return this.luasTembereng;
    }

    public double hitungLuasTembereng(double sumbuA, double sumbuB, double sudutDerajat) {
        double theta = Math.toRadians(sudutDerajat);
        this.luasTembereng = 0.5 * sumbuA * sumbuB * (theta - Math.sin(theta));
        return this.luasTembereng;
    }

    public double hitungKelilingTembereng() {
        if (super.keliling <= 0) {
            throw new IllegalStateException("Gagal menghitung keliling tembereng: keliling elips dari superclass belum dihitung.");
        }
        double panjangBusur = (this.sudutDerajat / 360.0) * super.keliling;
        double theta = Math.toRadians(this.sudutDerajat);
        double taliBusur = 2 * Math.sqrt(
                Math.pow(super.sumbuA * Math.sin(theta / 2.0), 2)
                + Math.pow(super.sumbuB * (1 - Math.cos(theta / 2.0)), 2)
        );
        this.kelilingTembereng = panjangBusur + taliBusur;
        return this.kelilingTembereng;
    }

    public double hitungKelilingTembereng(double sumbuA, double sumbuB, double sudutDerajat) {
        double kelilingElips = super.hitungKeliling(sumbuA, sumbuB);
        double panjangBusur = (sudutDerajat / 360.0) * kelilingElips;
        double theta = Math.toRadians(sudutDerajat);
        double taliBusur = 2 * Math.sqrt(
                Math.pow(sumbuA * Math.sin(theta / 2.0), 2)
                + Math.pow(sumbuB * (1 - Math.cos(theta / 2.0)), 2)
        );
        this.kelilingTembereng = panjangBusur + taliBusur;
        return this.kelilingTembereng;
    }

    @Override
    public void run() {
        this.hitungLuas();
        this.hitungKeliling();
    }
}

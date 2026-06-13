public class CincinElips3D extends BolaElips implements Runnable, Elips3D {

    public double faktorDalam, tinggiCincin, luasAlasCincin, kelilingAlasCincin, volumeCincinElips3D, luasPermukaanCincinElips3D;

    public CincinElips3D(double sumbuA, double sumbuB, double tinggiCincin, double faktorDalam, boolean isManual) {
        super(sumbuA, sumbuB, tinggiCincin, isManual);
        if (tinggiCincin <= 0) {
            throw new IllegalArgumentException("Tinggi cincin elips 3D harus lebih besar dari 0!");
        }
        if (faktorDalam <= 0 || faktorDalam >= 1) {
            throw new IllegalArgumentException("Faktor dalam cincin elips 3D harus lebih dari 0 dan kurang dari 1!");
        }
        this.tinggiCincin = tinggiCincin;
        this.faktorDalam = faktorDalam;
    }

    @Override
    public double hitungVolume() {
        super.hitungLuas();
        return this.hitungVolumeCincinElips3D();
    }

    @Override
    public double hitungLuasPermukaan() {
        super.hitungLuas();
        super.hitungKeliling();
        return this.hitungLuasPermukaanCincinElips3D();
    }

    public double hitungVolumeCincinElips3D() {
        this.luasAlasCincin = super.luas * (1 - Math.pow(this.faktorDalam, 2));
        this.volumeCincinElips3D = this.luasAlasCincin * this.tinggiCincin;
        return this.volumeCincinElips3D;
    }

    public double hitungLuasPermukaanCincinElips3D() {
        this.luasAlasCincin = super.luas * (1 - Math.pow(this.faktorDalam, 2));
        this.kelilingAlasCincin = super.keliling + (super.keliling * this.faktorDalam);
        this.luasPermukaanCincinElips3D = (2 * this.luasAlasCincin) + (this.kelilingAlasCincin * this.tinggiCincin);
        return this.luasPermukaanCincinElips3D;
    }

    @Override
    public void run() {
        this.hitungLuasPermukaan();
        this.hitungVolume();
    }
}

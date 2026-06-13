public class CincinElips extends Elips implements Runnable {

    public double faktorDalam, luasCincin, kelilingCincin;

    public CincinElips(double sumbuA, double sumbuB, double faktorDalam, boolean isManual) {
        super(sumbuA, sumbuB, isManual);
        if (faktorDalam <= 0 || faktorDalam >= 1) {
            throw new IllegalArgumentException("Faktor dalam cincin elips harus lebih dari 0 dan kurang dari 1!");
        }
        this.faktorDalam = faktorDalam;
    }

    public double hitungLuasCincin() {
        if (super.luas <= 0) {
            throw new IllegalStateException("Gagal menghitung luas cincin: luas elips dari superclass belum dihitung.");
        }
        this.luasCincin = super.luas * (1 - Math.pow(this.faktorDalam, 2));
        return this.luasCincin;
    }

    public double hitungLuasCincin(double sumbuA, double sumbuB, double faktorDalam) {
        double luasElips = super.hitungLuas(sumbuA, sumbuB);
        this.luasCincin = luasElips * (1 - Math.pow(faktorDalam, 2));
        return this.luasCincin;
    }

    public double hitungKelilingCincin() {
        if (super.keliling <= 0) {
            throw new IllegalStateException("Gagal menghitung keliling cincin: keliling elips dari superclass belum dihitung.");
        }
        double kelilingDalam = super.keliling * this.faktorDalam;
        this.kelilingCincin = super.keliling + kelilingDalam;
        return this.kelilingCincin;
    }

    public double hitungKelilingCincin(double sumbuA, double sumbuB, double faktorDalam) {
        double kelilingElips = super.hitungKeliling(sumbuA, sumbuB);
        this.kelilingCincin = kelilingElips + (kelilingElips * faktorDalam);
        return this.kelilingCincin;
    }

    @Override
    public void run() {
        super.run();
        if (this.isManual) {
            this.hitungLuasCincin(sumbuA, sumbuB, faktorDalam);
            this.hitungKelilingCincin(sumbuA, sumbuB, faktorDalam);
        } else {
            this.hitungLuasCincin();
            this.hitungKelilingCincin();
        }
    }
}

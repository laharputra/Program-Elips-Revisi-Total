public class PrismaElips extends BolaElips implements Runnable {

    public double tinggiPrisma, volumePrismaElips, luasPermukaanPrismaElips;

    public PrismaElips(double sumbuA, double sumbuB, double tinggiPrisma, boolean isManual) {
        // Sesuai permintaan, class ini dibuat extends BolaElips.
        // Nilai sumbuC dipakai sama dengan tinggiPrisma agar konstruktor BolaElips tetap valid.
        super(sumbuA, sumbuB, tinggiPrisma, isManual);
        if (tinggiPrisma <= 0) {
            throw new IllegalArgumentException("Tinggi prisma/tabung elips harus lebih besar dari 0!");
        }
        this.tinggiPrisma = tinggiPrisma;
    }

    public double hitungVolumePrismaElips() {
        if (super.luas <= 0) {
            throw new IllegalStateException("Gagal menghitung volume prisma elips: luas elips dari superclass belum dihitung.");
        }
        this.volumePrismaElips = super.luas * this.tinggiPrisma;
        return this.volumePrismaElips;
    }

    public double hitungVolumePrismaElips(double sumbuA, double sumbuB, double tinggiPrisma) {
        double luasAlas = super.hitungLuas(sumbuA, sumbuB);
        this.volumePrismaElips = luasAlas * tinggiPrisma;
        return this.volumePrismaElips;
    }

    public double hitungLuasPermukaanPrismaElips() {
        if (super.luas <= 0 || super.keliling <= 0) {
            throw new IllegalStateException("Gagal menghitung luas permukaan prisma elips: luas/keliling elips dari superclass belum dihitung.");
        }
        this.luasPermukaanPrismaElips = (2 * super.luas) + (super.keliling * this.tinggiPrisma);
        return this.luasPermukaanPrismaElips;
    }

    public double hitungLuasPermukaanPrismaElips(double sumbuA, double sumbuB, double tinggiPrisma) {
        double luasAlas = super.hitungLuas(sumbuA, sumbuB);
        double kelilingAlas = super.hitungKeliling(sumbuA, sumbuB);
        this.luasPermukaanPrismaElips = (2 * luasAlas) + (kelilingAlas * tinggiPrisma);
        return this.luasPermukaanPrismaElips;
    }

    @Override
    public void run() {
        super.run();
        if (this.isManual) {
            this.hitungLuasPermukaanPrismaElips(sumbuA, sumbuB, tinggiPrisma);
            this.hitungVolumePrismaElips(sumbuA, sumbuB, tinggiPrisma);
        } else {
            this.hitungLuasPermukaanPrismaElips();
            this.hitungVolumePrismaElips();
        }
    }
}

public class Elips implements BendaGeometri, Runnable {

    // Atribut dibuat public mengikuti pola program acuan.
    public double sumbuA, sumbuB, luas, keliling;
    public boolean isManual;

    public Elips(double sumbuA, double sumbuB, boolean isManual) {
        if (sumbuA <= 0 || sumbuB <= 0) {
            throw new IllegalArgumentException("Nilai sumbu A dan sumbu B elips harus lebih besar dari 0!");
        }
        this.sumbuA = sumbuA;
        this.sumbuB = sumbuB;
        this.isManual = isManual;
    }

    @Override
    public double hitungLuas() throws IllegalStateException {
        if (this.sumbuA <= 0 || this.sumbuB <= 0) {
            throw new IllegalStateException("Gagal menghitung luas: sumbu A atau sumbu B tidak valid.");
        }
        this.luas = Math.PI * this.sumbuA * this.sumbuB;
        return this.luas;
    }

    // Overloading dengan parameter.
    @Override
    public double hitungLuas(double sumbuA, double sumbuB) throws IllegalArgumentException {
        if (sumbuA <= 0 || sumbuB <= 0) {
            throw new IllegalArgumentException("Input sumbu A dan sumbu B tidak boleh 0 atau negatif!");
        }
        this.luas = Math.PI * sumbuA * sumbuB;
        return this.luas;
    }

    @Override
    public double hitungKeliling() throws IllegalStateException {
        if (this.sumbuA <= 0 || this.sumbuB <= 0) {
            throw new IllegalStateException("Gagal menghitung keliling: sumbu A atau sumbu B tidak valid.");
        }
        // Pendekatan Ramanujan untuk keliling elips.
        this.keliling = Math.PI * (3 * (this.sumbuA + this.sumbuB)
                - Math.sqrt((3 * this.sumbuA + this.sumbuB) * (this.sumbuA + 3 * this.sumbuB)));
        return this.keliling;
    }

    // Overloading dengan parameter.
    @Override
    public double hitungKeliling(double sumbuA, double sumbuB) throws IllegalArgumentException {
        if (sumbuA <= 0 || sumbuB <= 0) {
            throw new IllegalArgumentException("Input sumbu A dan sumbu B tidak boleh 0 atau negatif!");
        }
        this.keliling = Math.PI * (3 * (sumbuA + sumbuB)
                - Math.sqrt((3 * sumbuA + sumbuB) * (sumbuA + 3 * sumbuB)));
        return this.keliling;
    }

    @Override
    public void run() {
        if (this.isManual) {
            this.hitungLuas(this.sumbuA, this.sumbuB);
            this.hitungKeliling(this.sumbuA, this.sumbuB);
        } else {
            this.hitungLuas();
            this.hitungKeliling();
        }
    }
}

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GeometriGUI extends JFrame {

    public static class InvalidInputException extends Exception {
        public InvalidInputException(String message) {
            super(message);
        }
    }

    public static class ThreadComputationException extends RuntimeException {
        public ThreadComputationException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public JButton btnGenerate;
    public JComboBox<String> cbMode;
    public JTextField txtJumlahData, txtSumbuA, txtSumbuB, txtTinggi, txtSudut, txtFaktorDalam, txtFaktorAtas;

    public JTable tabelElips, tabelJuring2D, tabelCincin2D, tabelTembereng2D;
    public JTable tabelBolaElips, tabelLimasElips, tabelLimasTerpancung, tabelPrismaElips;
    public JTable tabelJuring3D, tabelCincin3D, tabelTembereng3D;

    public DefaultTableModel modelElips, modelJuring2D, modelCincin2D, modelTembereng2D;
    public DefaultTableModel modelBolaElips, modelLimasElips, modelLimasTerpancung, modelPrismaElips;
    public DefaultTableModel modelJuring3D, modelCincin3D, modelTembereng3D;

    public JProgressBar pbElips, pbJuring2D, pbCincin2D, pbTembereng2D;
    public JProgressBar pbBolaElips, pbLimasElips, pbLimasTerpancung, pbPrismaElips;
    public JProgressBar pbJuring3D, pbCincin3D, pbTembereng3D;

    public GeometriGUI() {
        super("Sistem Komputasi Geometri Elips Multithreading");

        btnGenerate = new JButton("Generate Data");
        cbMode = new JComboBox<>(new String[]{"Opsi 1: Generate Angka Random", "Opsi 2: Gunakan Input Manual"});

        txtJumlahData = new JTextField("1000");
        txtSumbuA = new JTextField("10");
        txtSumbuB = new JTextField("6");
        txtTinggi = new JTextField("12");
        txtSudut = new JTextField("90");
        txtFaktorDalam = new JTextField("0.5");
        txtFaktorAtas = new JTextField("0.6");

        modelElips = new DefaultTableModel(new String[]{"No", "Sumbu A", "Sumbu B", "Luas Elips", "Keliling Elips"}, 0);
        modelJuring2D = new DefaultTableModel(new String[]{"No", "Sumbu A", "Sumbu B", "Sudut", "Luas Juring", "Keliling Juring"}, 0);
        modelCincin2D = new DefaultTableModel(new String[]{"No", "Sumbu A", "Sumbu B", "Faktor Dalam", "Luas Cincin", "Keliling Cincin"}, 0);
        modelTembereng2D = new DefaultTableModel(new String[]{"No", "Sumbu A", "Sumbu B", "Sudut", "Luas Tembereng", "Keliling Tembereng"}, 0);

        modelBolaElips = new DefaultTableModel(new String[]{"No", "Sumbu A", "Sumbu B", "Sumbu C", "Volume", "Luas Permukaan"}, 0);
        modelLimasElips = new DefaultTableModel(new String[]{"No", "Sumbu A", "Sumbu B", "Tinggi", "Volume", "Luas Permukaan"}, 0);
        modelLimasTerpancung = new DefaultTableModel(new String[]{"No", "Sumbu A", "Sumbu B", "Tinggi", "Faktor Atas", "Volume", "Luas Permukaan"}, 0);
        modelPrismaElips = new DefaultTableModel(new String[]{"No", "Sumbu A", "Sumbu B", "Tinggi", "Volume", "Luas Permukaan"}, 0);

        modelJuring3D = new DefaultTableModel(new String[]{"No", "Sumbu A", "Sumbu B", "Tinggi", "Sudut", "Volume", "Luas Permukaan"}, 0);
        modelCincin3D = new DefaultTableModel(new String[]{"No", "Sumbu A", "Sumbu B", "Tinggi", "Faktor Dalam", "Volume", "Luas Permukaan"}, 0);
        modelTembereng3D = new DefaultTableModel(new String[]{"No", "Sumbu A", "Sumbu B", "Tinggi", "Sudut", "Volume", "Luas Permukaan"}, 0);

        tabelElips = new JTable(modelElips);
        tabelJuring2D = new JTable(modelJuring2D);
        tabelCincin2D = new JTable(modelCincin2D);
        tabelTembereng2D = new JTable(modelTembereng2D);
        tabelBolaElips = new JTable(modelBolaElips);
        tabelLimasElips = new JTable(modelLimasElips);
        tabelLimasTerpancung = new JTable(modelLimasTerpancung);
        tabelPrismaElips = new JTable(modelPrismaElips);
        tabelJuring3D = new JTable(modelJuring3D);
        tabelCincin3D = new JTable(modelCincin3D);
        tabelTembereng3D = new JTable(modelTembereng3D);

        pbElips = progressBar();
        pbJuring2D = progressBar();
        pbCincin2D = progressBar();
        pbTembereng2D = progressBar();
        pbBolaElips = progressBar();
        pbLimasElips = progressBar();
        pbLimasTerpancung = progressBar();
        pbPrismaElips = progressBar();
        pbJuring3D = progressBar();
        pbCincin3D = progressBar();
        pbTembereng3D = progressBar();

        Container container = getContentPane();
        container.setLayout(new BorderLayout(8, 8));

        JPanel panelInput = new JPanel(new GridLayout(5, 4, 6, 6));
        panelInput.setBorder(BorderFactory.createTitledBorder("Input Data"));
        panelInput.add(new JLabel("Pilih Opsi:"));
        panelInput.add(cbMode);
        panelInput.add(new JLabel("Jumlah Data:"));
        panelInput.add(txtJumlahData);

        panelInput.add(new JLabel("Sumbu A:"));
        panelInput.add(txtSumbuA);
        panelInput.add(new JLabel("Sumbu B:"));
        panelInput.add(txtSumbuB);

        panelInput.add(new JLabel("Tinggi / Sumbu C:"));
        panelInput.add(txtTinggi);
        panelInput.add(new JLabel("Sudut Juring/Tembereng:"));
        panelInput.add(txtSudut);

        panelInput.add(new JLabel("Faktor Dalam Cincin:"));
        panelInput.add(txtFaktorDalam);
        panelInput.add(new JLabel("Faktor Atas Terpancung:"));
        panelInput.add(txtFaktorAtas);

        panelInput.add(new JLabel(""));
        panelInput.add(btnGenerate);
        panelInput.add(new JLabel(""));
        panelInput.add(new JLabel(""));

        container.add(panelInput, BorderLayout.NORTH);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.add("Elips", scroll(tabelElips, "Tabel Hasil Elips"));
        tabbedPane.add("Juring 2D", scroll(tabelJuring2D, "Tabel Hasil Juring Elips 2D"));
        tabbedPane.add("Cincin 2D", scroll(tabelCincin2D, "Tabel Hasil Cincin Elips 2D"));
        tabbedPane.add("Tembereng 2D", scroll(tabelTembereng2D, "Tabel Hasil Tembereng Elips 2D"));
        tabbedPane.add("Bola Elips", scroll(tabelBolaElips, "Tabel Hasil Bola Elips 3D"));
        tabbedPane.add("Limas Elips", scroll(tabelLimasElips, "Tabel Hasil Kerucut/Limas Alas Elips"));
        tabbedPane.add("Terpancung", scroll(tabelLimasTerpancung, "Tabel Hasil Kerucut/Limas Elips Terpancung"));
        tabbedPane.add("Prisma Elips", scroll(tabelPrismaElips, "Tabel Hasil Tabung/Prisma Elips"));
        tabbedPane.add("Juring 3D", scroll(tabelJuring3D, "Tabel Hasil Juring Elips 3D"));
        tabbedPane.add("Cincin 3D", scroll(tabelCincin3D, "Tabel Hasil Cincin Elips 3D"));
        tabbedPane.add("Tembereng 3D", scroll(tabelTembereng3D, "Tabel Hasil Tembereng Elips 3D"));
        container.add(tabbedPane, BorderLayout.CENTER);

        JPanel panelProgress = new JPanel(new GridLayout(11, 2, 5, 3));
        panelProgress.setBorder(BorderFactory.createTitledBorder("Progress Thread"));
        addProgress(panelProgress, "Thread 1 - Elips", pbElips);
        addProgress(panelProgress, "Thread 2 - Juring 2D", pbJuring2D);
        addProgress(panelProgress, "Thread 3 - Cincin 2D", pbCincin2D);
        addProgress(panelProgress, "Thread 4 - Tembereng 2D", pbTembereng2D);
        addProgress(panelProgress, "Thread 5 - Bola Elips", pbBolaElips);
        addProgress(panelProgress, "Thread 6 - Limas Elips", pbLimasElips);
        addProgress(panelProgress, "Thread 7 - Terpancung", pbLimasTerpancung);
        addProgress(panelProgress, "Thread 8 - Prisma Elips", pbPrismaElips);
        addProgress(panelProgress, "Thread 9 - Juring 3D", pbJuring3D);
        addProgress(panelProgress, "Thread 10 - Cincin 3D", pbCincin3D);
        addProgress(panelProgress, "Thread 11 - Tembereng 3D", pbTembereng3D);
        container.add(panelProgress, BorderLayout.SOUTH);

        btnGenerate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jalankanMasterThread();
            }
        });

        setSize(1250, 820);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private JProgressBar progressBar() {
        JProgressBar pb = new JProgressBar(0, 1000);
        pb.setStringPainted(true);
        return pb;
    }

    private JScrollPane scroll(JTable tabel, String judul) {
        JScrollPane sp = new JScrollPane(tabel);
        sp.setBorder(BorderFactory.createTitledBorder(judul));
        return sp;
    }

    private void addProgress(JPanel panel, String label, JProgressBar pb) {
        panel.add(new JLabel(" " + label + ":"));
        panel.add(pb);
    }

    public int validasiJumlahData() throws InvalidInputException {
        String raw = txtJumlahData.getText().trim();
        if (raw.isEmpty()) {
            throw new InvalidInputException("Field Jumlah Data tidak boleh kosong.");
        }
        int jumlah;
        try {
            jumlah = Integer.parseInt(raw);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Jumlah Data harus berupa angka bulat. Contoh: 1000");
        }
        if (jumlah <= 0) {
            throw new InvalidInputException("Jumlah Data harus lebih dari 0.");
        }
        if (jumlah > 100000) {
            throw new InvalidInputException("Jumlah Data terlalu besar. Maksimal 100000.");
        }
        return jumlah;
    }

    public double[] validasiInputManual() throws InvalidInputException {
        double sumbuA = parsePositive(txtSumbuA.getText().trim(), "Sumbu A");
        double sumbuB = parsePositive(txtSumbuB.getText().trim(), "Sumbu B");
        double tinggi = parsePositive(txtTinggi.getText().trim(), "Tinggi / Sumbu C");
        double sudut = parseRange(txtSudut.getText().trim(), "Sudut", 0, 360, false, true);
        double faktorDalam = parseRange(txtFaktorDalam.getText().trim(), "Faktor Dalam Cincin", 0, 1, false, false);
        double faktorAtas = parseRange(txtFaktorAtas.getText().trim(), "Faktor Atas Terpancung", 0, 1, false, false);

        if (sumbuA > 1000000 || sumbuB > 1000000 || tinggi > 1000000) {
            throw new InvalidInputException("Nilai sumbu/tinggi terlalu besar. Maksimal 1000000.");
        }
        return new double[]{sumbuA, sumbuB, tinggi, sudut, faktorDalam, faktorAtas};
    }

    private double parsePositive(String raw, String namaField) throws InvalidInputException {
        if (raw.isEmpty()) {
            throw new InvalidInputException("Field " + namaField + " tidak boleh kosong.");
        }
        double value;
        try {
            value = Double.parseDouble(raw);
        } catch (NumberFormatException e) {
            throw new InvalidInputException(namaField + " harus berupa angka desimal. Contoh: 10.5");
        }
        if (value <= 0) {
            throw new InvalidInputException(namaField + " harus lebih dari 0.");
        }
        return value;
    }

    private double parseRange(String raw, String namaField, double min, double max, boolean includeMin, boolean includeMax) throws InvalidInputException {
        if (raw.isEmpty()) {
            throw new InvalidInputException("Field " + namaField + " tidak boleh kosong.");
        }
        double value;
        try {
            value = Double.parseDouble(raw);
        } catch (NumberFormatException e) {
            throw new InvalidInputException(namaField + " harus berupa angka desimal.");
        }

        boolean validMin = includeMin ? value >= min : value > min;
        boolean validMax = includeMax ? value <= max : value < max;
        if (!validMin || !validMax) {
            String minText = includeMin ? ">= " + min : "> " + min;
            String maxText = includeMax ? "<= " + max : "< " + max;
            throw new InvalidInputException(namaField + " harus " + minText + " dan " + maxText + ".");
        }
        return value;
    }

    public void jalankanMasterThread() {
        btnGenerate.setEnabled(false);
        resetTabel();

        Thread masterThread = new Thread(() -> {
            final int jumlahData;
            final boolean isManual = cbMode.getSelectedIndex() == 1;
            final double manualSumbuA, manualSumbuB, manualTinggi, manualSudut, manualFaktorDalam, manualFaktorAtas;

            try {
                jumlahData = validasiJumlahData();
                if (isManual) {
                    double[] input = validasiInputManual();
                    manualSumbuA = input[0];
                    manualSumbuB = input[1];
                    manualTinggi = input[2];
                    manualSudut = input[3];
                    manualFaktorDalam = input[4];
                    manualFaktorAtas = input[5];
                } else {
                    manualSumbuA = 10;
                    manualSumbuB = 6;
                    manualTinggi = 12;
                    manualSudut = 90;
                    manualFaktorDalam = 0.5;
                    manualFaktorAtas = 0.6;
                }
            } catch (InvalidInputException e) {
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(GeometriGUI.this,
                            "Input Tidak Valid:\n" + e.getMessage(),
                            "Error Validasi",
                            JOptionPane.ERROR_MESSAGE);
                    btnGenerate.setEnabled(true);
                });
                return;
            }

            SwingUtilities.invokeLater(() -> setMaksimumProgress(jumlahData));

            double[] dataA = new double[jumlahData];
            double[] dataB = new double[jumlahData];
            double[] dataTinggi = new double[jumlahData];
            double[] dataSudut = new double[jumlahData];
            double[] dataFaktorDalam = new double[jumlahData];
            double[] dataFaktorAtas = new double[jumlahData];

            Random rand = new Random();
            for (int i = 0; i < jumlahData; i++) {
                if (isManual) {
                    dataA[i] = manualSumbuA;
                    dataB[i] = manualSumbuB;
                    dataTinggi[i] = manualTinggi;
                    dataSudut[i] = manualSudut;
                    dataFaktorDalam[i] = manualFaktorDalam;
                    dataFaktorAtas[i] = manualFaktorAtas;
                } else {
                    dataA[i] = 1 + (rand.nextDouble() * 49);
                    dataB[i] = 1 + (rand.nextDouble() * 49);
                    dataTinggi[i] = 1 + (rand.nextDouble() * 49);
                    dataSudut[i] = 10 + (rand.nextDouble() * 340);
                    dataFaktorDalam[i] = 0.1 + (rand.nextDouble() * 0.8);
                    dataFaktorAtas[i] = 0.1 + (rand.nextDouble() * 0.8);
                }
            }

            Object[][] hasilElips = new Object[jumlahData][5];
            Object[][] hasilJuring2D = new Object[jumlahData][6];
            Object[][] hasilCincin2D = new Object[jumlahData][6];
            Object[][] hasilTembereng2D = new Object[jumlahData][6];
            Object[][] hasilBola = new Object[jumlahData][6];
            Object[][] hasilLimas = new Object[jumlahData][6];
            Object[][] hasilTerpancung = new Object[jumlahData][7];
            Object[][] hasilPrisma = new Object[jumlahData][6];
            Object[][] hasilJuring3D = new Object[jumlahData][7];
            Object[][] hasilCincin3D = new Object[jumlahData][7];
            Object[][] hasilTembereng3D = new Object[jumlahData][7];

            final Throwable[] errors = new Throwable[11];

            Thread t1 = buatThread("Elips", 0, pbElips, hasilElips, jumlahData, dataA, dataB, dataTinggi, dataSudut, dataFaktorDalam, dataFaktorAtas, isManual,
                    (i, a, b, tinggi, sudut, faktorDalam, faktorAtas, manual) -> {
                        Elips elips1 = new Elips(a, b, manual);
                        elips1 = jalankanPolymorphism(elips1);
                        return new Object[]{i + 1, f(a), f(b), f(elips1.luas), f(elips1.keliling)};
                    }, errors);

            Thread t2 = buatThread("Juring Elips 2D", 1, pbJuring2D, hasilJuring2D, jumlahData, dataA, dataB, dataTinggi, dataSudut, dataFaktorDalam, dataFaktorAtas, isManual,
                    (i, a, b, tinggi, sudut, faktorDalam, faktorAtas, manual) -> {
                        Elips elips2 = new JuringElips(a, b, sudut, manual);
                        elips2 = jalankanPolymorphism(elips2);
                        JuringElips j = (JuringElips) elips2;
                        return new Object[]{i + 1, f(a), f(b), f(sudut), f(j.luasJuring), f(j.kelilingJuring)};
                    }, errors);

            Thread t3 = buatThread("Cincin Elips 2D", 2, pbCincin2D, hasilCincin2D, jumlahData, dataA, dataB, dataTinggi, dataSudut, dataFaktorDalam, dataFaktorAtas, isManual,
                    (i, a, b, tinggi, sudut, faktorDalam, faktorAtas, manual) -> {
                        Elips elips3 = new CincinElips(a, b, faktorDalam, manual);
                        elips3 = jalankanPolymorphism(elips3);
                        CincinElips c = (CincinElips) elips3;
                        return new Object[]{i + 1, f(a), f(b), f(faktorDalam), f(c.luasCincin), f(c.kelilingCincin)};
                    }, errors);

            Thread t4 = buatThread("Tembereng Elips 2D", 3, pbTembereng2D, hasilTembereng2D, jumlahData, dataA, dataB, dataTinggi, dataSudut, dataFaktorDalam, dataFaktorAtas, isManual,
                    (i, a, b, tinggi, sudut, faktorDalam, faktorAtas, manual) -> {
                        Elips elips4 = new TemberengElips(a, b, sudut, manual);
                        elips4 = jalankanPolymorphism(elips4);
                        TemberengElips t = (TemberengElips) elips4;
                        return new Object[]{i + 1, f(a), f(b), f(sudut), f(t.luasTembereng), f(t.kelilingTembereng)};
                    }, errors);

            Thread t5 = buatThread("Bola Elips", 4, pbBolaElips, hasilBola, jumlahData, dataA, dataB, dataTinggi, dataSudut, dataFaktorDalam, dataFaktorAtas, isManual,
                    (i, a, b, tinggi, sudut, faktorDalam, faktorAtas, manual) -> {
                        Elips3D elips3d1 = new BolaElips(a, b, tinggi, manual);
                        double volume = elips3d1.hitungVolume();
                        double luasPermukaan = elips3d1.hitungLuasPermukaan();
                        return new Object[]{i + 1, f(a), f(b), f(tinggi), f(volume), f(luasPermukaan)};
                    }, errors);

            Thread t6 = buatThread("Limas Elips", 5, pbLimasElips, hasilLimas, jumlahData, dataA, dataB, dataTinggi, dataSudut, dataFaktorDalam, dataFaktorAtas, isManual,
                    (i, a, b, tinggi, sudut, faktorDalam, faktorAtas, manual) -> {
                        Elips3D elips3d2 = new LimasElips(a, b, tinggi, manual);
                        double volume = elips3d2.hitungVolume();
                        double luasPermukaan = elips3d2.hitungLuasPermukaan();
                        return new Object[]{i + 1, f(a), f(b), f(tinggi), f(volume), f(luasPermukaan)};
                    }, errors);

            Thread t7 = buatThread("Limas Elips Terpancung", 6, pbLimasTerpancung, hasilTerpancung, jumlahData, dataA, dataB, dataTinggi, dataSudut, dataFaktorDalam, dataFaktorAtas, isManual,
                    (i, a, b, tinggi, sudut, faktorDalam, faktorAtas, manual) -> {
                        Elips3D elips3d3 = new LimasElipsTerpancung(a, b, tinggi, faktorAtas, manual);
                        double volume = elips3d3.hitungVolume();
                        double luasPermukaan = elips3d3.hitungLuasPermukaan();
                        return new Object[]{i + 1, f(a), f(b), f(tinggi), f(faktorAtas), f(volume), f(luasPermukaan)};
                    }, errors);

            Thread t8 = buatThread("Prisma Elips", 7, pbPrismaElips, hasilPrisma, jumlahData, dataA, dataB, dataTinggi, dataSudut, dataFaktorDalam, dataFaktorAtas, isManual,
                    (i, a, b, tinggi, sudut, faktorDalam, faktorAtas, manual) -> {
                        Elips3D elips3d4 = new PrismaElips(a, b, tinggi, manual);
                        double volume = elips3d4.hitungVolume();
                        double luasPermukaan = elips3d4.hitungLuasPermukaan();
                        return new Object[]{i + 1, f(a), f(b), f(tinggi), f(volume), f(luasPermukaan)};
                    }, errors);

            Thread t9 = buatThread("Juring Elips 3D", 8, pbJuring3D, hasilJuring3D, jumlahData, dataA, dataB, dataTinggi, dataSudut, dataFaktorDalam, dataFaktorAtas, isManual,
                    (i, a, b, tinggi, sudut, faktorDalam, faktorAtas, manual) -> {
                        Elips3D elips3d5 = new JuringElips3D(a, b, tinggi, sudut, manual);
                        double volume = elips3d5.hitungVolume();
                        double luasPermukaan = elips3d5.hitungLuasPermukaan();
                        return new Object[]{i + 1, f(a), f(b), f(tinggi), f(sudut), f(volume), f(luasPermukaan)};
                    }, errors);

            Thread t10 = buatThread("Cincin Elips 3D", 9, pbCincin3D, hasilCincin3D, jumlahData, dataA, dataB, dataTinggi, dataSudut, dataFaktorDalam, dataFaktorAtas, isManual,
                    (i, a, b, tinggi, sudut, faktorDalam, faktorAtas, manual) -> {
                        Elips3D elips3d6 = new CincinElips3D(a, b, tinggi, faktorDalam, manual);
                        double volume = elips3d6.hitungVolume();
                        double luasPermukaan = elips3d6.hitungLuasPermukaan();
                        return new Object[]{i + 1, f(a), f(b), f(tinggi), f(faktorDalam), f(volume), f(luasPermukaan)};
                    }, errors);

            Thread t11 = buatThread("Tembereng Elips 3D", 10, pbTembereng3D, hasilTembereng3D, jumlahData, dataA, dataB, dataTinggi, dataSudut, dataFaktorDalam, dataFaktorAtas, isManual,
                    (i, a, b, tinggi, sudut, faktorDalam, faktorAtas, manual) -> {
                        Elips3D elips3d7 = new TemberengElips3D(a, b, tinggi, sudut, manual);
                        double volume = elips3d7.hitungVolume();
                        double luasPermukaan = elips3d7.hitungLuasPermukaan();
                        return new Object[]{i + 1, f(a), f(b), f(tinggi), f(sudut), f(volume), f(luasPermukaan)};
                    }, errors);

            Thread[] semuaThread = {t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11};
            for (Thread thread : semuaThread) {
                thread.start();
            }

            try {
                for (Thread thread : semuaThread) {
                    thread.join();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(GeometriGUI.this,
                            "Proses komputasi dihentikan paksa.",
                            "Interupsi",
                            JOptionPane.WARNING_MESSAGE);
                    btnGenerate.setEnabled(true);
                });
                return;
            }

            SwingUtilities.invokeLater(() -> {
                tambahBaris(modelElips, hasilElips);
                tambahBaris(modelJuring2D, hasilJuring2D);
                tambahBaris(modelCincin2D, hasilCincin2D);
                tambahBaris(modelTembereng2D, hasilTembereng2D);
                tambahBaris(modelBolaElips, hasilBola);
                tambahBaris(modelLimasElips, hasilLimas);
                tambahBaris(modelLimasTerpancung, hasilTerpancung);
                tambahBaris(modelPrismaElips, hasilPrisma);
                tambahBaris(modelJuring3D, hasilJuring3D);
                tambahBaris(modelCincin3D, hasilCincin3D);
                tambahBaris(modelTembereng3D, hasilTembereng3D);

                StringBuilder pesanError = new StringBuilder();
                for (int i = 0; i < errors.length; i++) {
                    if (errors[i] != null) {
                        pesanError.append("• Thread ").append(i + 1).append(": ").append(errors[i].getMessage()).append("\n");
                    }
                }

                btnGenerate.setEnabled(true);
                if (pesanError.length() > 0) {
                    JOptionPane.showMessageDialog(GeometriGUI.this,
                            "Proses selesai dengan error:\n\n" + pesanError,
                            "Error Komputasi Thread",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(GeometriGUI.this,
                            "Proses selesai! Semua data berhasil ditampilkan.",
                            "Sukses",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            });
        });

        masterThread.start();
    }

    private void resetTabel() {
        modelElips.setRowCount(0);
        modelJuring2D.setRowCount(0);
        modelCincin2D.setRowCount(0);
        modelTembereng2D.setRowCount(0);
        modelBolaElips.setRowCount(0);
        modelLimasElips.setRowCount(0);
        modelLimasTerpancung.setRowCount(0);
        modelPrismaElips.setRowCount(0);
        modelJuring3D.setRowCount(0);
        modelCincin3D.setRowCount(0);
        modelTembereng3D.setRowCount(0);
    }

    private void setMaksimumProgress(int max) {
        JProgressBar[] pbs = {pbElips, pbJuring2D, pbCincin2D, pbTembereng2D, pbBolaElips, pbLimasElips, pbLimasTerpancung, pbPrismaElips, pbJuring3D, pbCincin3D, pbTembereng3D};
        for (JProgressBar pb : pbs) {
            pb.setMaximum(max);
            pb.setValue(0);
        }
    }

    private void tambahBaris(DefaultTableModel model, Object[][] data) {
        for (Object[] row : data) {
            model.addRow(row);
        }
    }

    private Elips jalankanPolymorphism(Elips bentuk) {
        // Runtime polymorphism:
        // parameter bertipe Elips, tetapi method run() yang berjalan mengikuti object aslinya.
        // Contoh: jika object aslinya BolaElips, maka yang dipanggil adalah BolaElips.run().
        bentuk.run();
        return bentuk;
    }

    private interface HitungBaris {
        Object[] hitung(int index, double a, double b, double tinggi, double sudut, double faktorDalam, double faktorAtas, boolean isManual);
    }

    private Thread buatThread(String namaThread, int indexError, JProgressBar progressBar, Object[][] hasil, int jumlahData,
            double[] dataA, double[] dataB, double[] dataTinggi, double[] dataSudut, double[] dataFaktorDalam, double[] dataFaktorAtas,
            boolean isManual, HitungBaris hitungBaris, Throwable[] errors) {

        return new Thread(() -> {
            Random randDelay = new Random();
            try {
                for (int i = 0; i < jumlahData; i++) {
                    double a = dataA[i];
                    double b = dataB[i];
                    double tinggi = dataTinggi[i];
                    double sudut = dataSudut[i];
                    double faktorDalam = dataFaktorDalam[i];
                    double faktorAtas = dataFaktorAtas[i];

                    cekValidAngka(a, b, tinggi, sudut, faktorDalam, faktorAtas, i + 1, namaThread);
                    hasil[i] = hitungBaris.hitung(i, a, b, tinggi, sudut, faktorDalam, faktorAtas, isManual);

                    final int progress = i + 1;
                    if (i % 50 == 0 || i == jumlahData - 1) {
                        SwingUtilities.invokeLater(() -> progressBar.setValue(progress));
                    }

                    if (randDelay.nextInt(100) < 3) {
                        try {
                            Thread.sleep(randDelay.nextInt(4) + 1);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            throw e;
                        }
                    }
                }
            } catch (Exception e) {
                errors[indexError] = new ThreadComputationException("Error di " + namaThread + ": " + e.getMessage(), e);
            }
        });
    }

    private void cekValidAngka(double a, double b, double tinggi, double sudut, double faktorDalam, double faktorAtas, int noData, String namaThread) {
        double[] values = {a, b, tinggi, sudut, faktorDalam, faktorAtas};
        for (double value : values) {
            if (Double.isNaN(value) || Double.isInfinite(value)) {
                throw new ArithmeticException("Nilai NaN/Infinity terdeteksi pada data ke-" + noData + " di " + namaThread);
            }
        }
    }

    public String f(double value) {
        return String.format("%.2f", value);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GeometriGUI().setVisible(true));
    }
}

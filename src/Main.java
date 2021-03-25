import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        double[] x = {2.0, 2.4, 2.8, 3.4, 3.6, 3.2, 2.6, 2.9, 2.5, 2.7};
        double[] y = {32, 30, 28, 23, 19, 25, 27, 24, 29, 33};

        Scanner scan = new Scanner(System.in);
        System.out.print("Nilai duga = ");
        double duga = scan.nextDouble();

        //mencari x^2
        ArrayList<Double> xKuadrat = mencariKuadrat(x, x);
        ArrayList<Double> yKuadrat = mencariKuadrat(y, x);

        //mencari jumlah x dan y
        double xJumlah = mencariJumlah(x);
        double yJumlah = mencariJumlah(y);

        //mencari jumlah xkuadrat dan ykuadrat
        double xKuadratJumlah = mencariJumlah2(xKuadrat);
        double yKuadratJumlah = mencariJumlah2(yKuadrat);

        //mencari xy
        ArrayList<Double> xy = new ArrayList<>();
        for (int i = 0; i < x.length; i++) {
            double hasil = x[i] * y[i];
            xy.add(hasil);
        }
        //mencari jumlah xy
        double xyJumlah = mencariJumlah2(xy);

        //mencari nilai atap
        double xAtap = xJumlah / x.length;
        double yAtap = yJumlah / y.length;

        //mencari nilai a dan b
        double b = (((x.length) * (xyJumlah)) -((xJumlah)*(yJumlah))) / (((x.length)*(xKuadratJumlah))-((xJumlah)*(xJumlah)));
        double a = yAtap - (b * xAtap);

        //mencari nilai duga
        double nilaiDuga = a + (b * duga);

        //mencari y'
        ArrayList<Double> yAksen = new ArrayList<>();
        for (double num : x) {
            double hasil = a + (b * num);
            yAksen.add(hasil);
        }

        //mencari jumlah y'
        double yAksenJumlah = mencariJumlah2(yAksen);

        //mencari y-y'
        ArrayList<Double> yKurangyAksen = new ArrayList<>();
        Double[] yAksen2 = new Double[yAksen.size()];
        yAksen2 = yAksen.toArray(yAksen2);
        for (int i = 0; i < x.length; i++) {
            double hasil = y[i] - yAksen2[i];
            yKurangyAksen.add(hasil);
        }

        //mencari (y-y')^2
        ArrayList<Double> yKurangyAksenKuadrat = new ArrayList<>();
        Double[] yKurangyAksen2 = new Double[yKurangyAksen.size()];
        yKurangyAksen2 = yKurangyAksen.toArray(yKurangyAksen2);
        for (int i = 0; i < x.length; i++) {
            double hasil = Math.pow(yKurangyAksen2[i], 2);
            yKurangyAksenKuadrat.add(hasil);
        }

        //mencari jumlah y-y' dan (y-y)^2
        double yKurangyAksenJumlah = mencariJumlah2(yKurangyAksen);
        double yKurangyAksenKuadratJumlah = mencariJumlah2(yKurangyAksenKuadrat);

        //mencari selisi taksir
        double syx = Math.sqrt((yKurangyAksenKuadratJumlah/(x.length-2)));

        menampilkan(x, x, "x", xJumlah);
        menampilkan(y, y, "y", yJumlah);
        menampilkan2(xKuadrat, x, "xKuadrat", xKuadratJumlah);
        menampilkan2(yKuadrat, y, "yKuadrat", yKuadratJumlah);
        menampilkan2(xy, x, "xy", xyJumlah);
        menampilkan2(yAksen, y, "y'", yAksenJumlah);
        menampilkan2(yKurangyAksen, y, "y-y'", yKurangyAksenJumlah);
        menampilkan2(yKurangyAksenKuadrat, y, "(y-y')^2", yKurangyAksenKuadratJumlah);
        System.out.println("Jadi nilai a = " + a + " dan nilai b = " + b);
        System.out.println("Persamaan Regresinya adalah y = " + a + " + " + b + " x");
        System.out.println("Nilai duga y, jika x = " + duga + " adalah " + nilaiDuga);
        System.out.println("Selisih Taksir = " + syx);
    }

    public static ArrayList<Double> mencariKuadrat(double[] nilai, double[] panjang) {
        ArrayList<Double> hasilKuadrat = new ArrayList<>();
        for (int i = 0; i < panjang.length; i++) {
            double hasil = Math.pow(nilai[i], 2);
            hasilKuadrat.add(hasil);
        }
        return hasilKuadrat;
    }


    public static double mencariJumlah(double[] nilai) {
        double hasil = 0;
        for (double num : nilai) {
            hasil = hasil + num;
        }
        return hasil;
    }

    public static double mencariJumlah2(ArrayList<Double> nilai) {
        double hasil = 0;
        for (double num : nilai) {
            hasil = hasil + num;
        }
        return hasil;
    }

    public static void menampilkan(double[] nilai, double[] panjang, String nama, double jumlah) {
        System.out.println("------" + nama + "-----");
        for (int i = 0; i < panjang.length; i++) {
            System.out.println(nama + " Ke-" + (i+1) + " = " + nilai[i]);
        }
        System.out.println("Jumlah " + nama + " = " + jumlah);
        System.out.println();
    }

    public static void menampilkan2(ArrayList<Double> nilai, double[] panjang, String nama, double jumlah) {
        System.out.println("------" + nama + "-----");
        for (int i = 0; i < panjang.length; i++) {
            Double[] hasilNilai = new Double[nilai.size()];
            hasilNilai = nilai.toArray(hasilNilai);
            System.out.println(nama + " Ke-" + (i+1) + " = " + hasilNilai[i]);
        }
        System.out.println("Jumlah " + nama + " = " + jumlah);
        System.out.println();
    }
}
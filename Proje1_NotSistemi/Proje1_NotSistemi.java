/*
 * Ad Soyad: Melike Gücin
 * Öğrenci No: 240541081
 * Proje: Akıllı Restoran Sipariş Sistemi
 * Tarih: 25.11.2025
 */

import java.util.Scanner;

public class Proje1_NotSistemi {
    public static void main(String[] args) {
        Scanner puan = new Scanner(System.in);

        // Kullanıcıdan notları all
        System.out.println("=== OGRENCİ NOT RAPORU ===");
        System.out.print("Vize notu: ");
        double vize = puan.nextDouble();

        System.out.print("Final notu: ");
        double finalNotu = puan.nextDouble();

        System.out.print("Odev notu: ");
        double odev = puan.nextDouble();
        System.out.println("------------------------------");

        double ortalama = (vize * 0.30) + (finalNotu * 0.40) + (odev * 0.30);
        System.out.println("Ortalama: " + calculateAverage(vize, finalNotu, odev));

        System.out.println("Harf Notu: " + getLetterGrade(ortalama));
        System.out.println("Durum: " + (isPassingGrade(ortalama) ? "GEÇTİ" : "KALDI"));
        System.out.println("Onur Listesi: " + (isHonorList(ortalama, vize, finalNotu, odev) ? "EVET" : "HAYIR"));
        System.out.println("Bütünleme Hakkı: " + (hasRetakeRight(ortalama) ? "VAR" : "YOK"));
    }
        public static double calculateAverage(double vize, double finalNotu, double odev) {
            double Ortalama = vize * 0.3 + finalNotu * 0.4 + odev* 0.3;
            return Ortalama;

}
    public static boolean isPassingGrade(double ortalama) {
        if (ortalama >= 50) return true;
        else
            return false;
    }

    public static char getLetterGrade(double ortalama) {
        if (ortalama > 100) ortalama = 100;

        int not = (int) ortalama / 10;
        {
            switch (not) {
                case 10: //100
                case 9:
                    return 'A';
                case 8:
                    return 'B';
                case 7:
                    return 'C';
                case 6:
                    return 'D';
                default:
                    return 'F';
            }
        }
        // Onur Listesii
    }

    public static boolean isHonorList(double ortalama, double vize, double finalNotu,  double odev) {
        if (ortalama >= 85 && vize >=70 && finalNotu >= 70 && odev >= 70) return true;
        else
           return false;
    }

    public static boolean hasRetakeRight(double ortalama) {
        if (ortalama >= 40 && ortalama < 50) return true;
        else
           return false;
    }
}


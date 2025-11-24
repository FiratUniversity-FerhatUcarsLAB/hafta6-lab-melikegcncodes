/**
* Ad Soyad: Melike Gücin
* Numara: 240541081
* Proje: Not Sistemi
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
        System.out.println("Ortalama: " + ortalama);

        System.out.println("Harf Notu: " + getLetterGrade(ortalama));
        System.out.println("Durum: " + isPassingGrade(ortalama));
        System.out.println("Onur Listesi: " + isHonorList(ortalama, vize, finalNotu, odev));
        System.out.println("Bütünleme Hakkı: " + hasRetakeRight(ortalama));
    }

    public static String isPassingGrade(double ortalama) {
        if (ortalama >= 50) return "Geçti";
        else
            return "Kaldı";
    }

    public static String getLetterGrade(double ortalama) {
        if (ortalama < 0) return "Kaldı";
        if (ortalama > 100) ortalama = 100;

        int not = (int) ortalama / 10;
        {
            switch (not) {
                case 10: //100
                case 9:
                    return "A";
                case 8:
                    return "B";
                case 7:
                    return "C";
                case 6:
                    return "D";
                default:
                    return "F";
            }
        }
        // Onur Listesii
    }

    public static String isHonorList(double ortalama, double vize, double finalNotu,  double odev) {
        if (ortalama >= 85 && vize >=70 && finalNotu >= 70 && odev >= 70) return "EVET";
        else
           return "HAYIR";
    }

    public static String hasRetakeRight(double ortalama) {
        if (ortalama >= 40 && ortalama < 50) return "VAR";
        else
           return "YOK";
    }
}




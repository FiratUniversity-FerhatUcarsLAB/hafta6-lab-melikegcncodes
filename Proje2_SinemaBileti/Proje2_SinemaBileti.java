/*
 * Ad Soyad: Melike Gücin
 * Öğrenci No: 240541081
 * Proje: Akıllı Restoran Sipariş Sistemi
 * Tarih: 25.11.2025
 */


import java.util.Scanner;

public class Proje2_SinemaBileti {

   // Sabitler
   static final double WEEKDAY_MATINE = 45.0;  // hafta içi makine (12 öncesi)
   static final double WEEKDAY_NORMAL = 65.0;
   static final double WEEKEND_MATINE = 55.0;
   static final double WEEKEND_NORMAL = 85.0;

    public static void main(String[] args) {
        Scanner biletFiyatı = new Scanner(System.in);

        // Kullanıcıdan girdileri al
        System.out.print("Gun Secimi (1=Pzt, 2=Sal, 3=Car, 4=Prs, 5=Cum, 6=Cmt, 7=Paz): ");
        int gun = biletFiyatı.nextInt();

        System.out.print("Saat (8-23): ");
        int saat = biletFiyatı.nextInt();

        System.out.print("Yas: ");
        int yas = biletFiyatı.nextInt();

        System.out.print("Meslek (1=Ogrenci, 2=Ogretmen, 3=Diger): ");
        int meslek = biletFiyatı.nextInt();

        System.out.print("Film turu (1=2D, 2=3D, 3=IMAX, 4=4DX): ");
        int filmTuru = biletFiyatı.nextInt();

        //Heaplamalarr
        boolean haftaSonu = isWeekend(gun);
        boolean matine = isMatinee(saat);
        // Değişkenleri tanımlaa
        double base = calculateBasePrice(gun, saat);
        double discount = calculateDiscount(yas, meslek, gun);
        double finalPrice = calculateFinalPrice(gun, saat, yas, meslek, filmTuru);
        double price = calculateBasePrice(gun, saat);
        generateTicketInfo(gun, saat, yas, meslek, filmTuru,base, finalPrice, discount);


        System.out.println( generateTicketInfo(gun, saat, yas, meslek, filmTuru,base, finalPrice, discount));


    }
    // Hafta içi mi hafta sonu mu kontrol et
    public static boolean isWeekend(int gun) {
       return (gun == 6 || gun == 7 );
    }
    // Saat kaç olduğunu ogren
    public static boolean isMatinee(int saat) {
        return (saat < 12);
    }
    //
    public static double calculateBasePrice( int gun, int saat) {
        boolean weekend = isWeekend(gun);
        boolean matine = isMatinee(saat);

        if (!weekend && matine) return WEEKDAY_MATINE;
        if (!weekend && !matine) return WEEKDAY_NORMAL;
        if (weekend && matine) return WEEKEND_MATINE;
        return WEEKEND_NORMAL;
    }
    // Yas indirimini hesapla
    public static double calculateDiscount(int yas, int meslek, int gun) {
        double discount = 0.0;

        if (yas >= 65 ) {
            discount = 0.30;
        }
        else if (yas < 12 ) {
            discount = 0.25;
        }

       else if (meslek == 1 ) {
            if (gun >= 1 && gun <=4)
            discount = 0.20;
        } else {
            discount = 0.15;
        }
    return discount;
    }
    // Film türünü belirle
    public  static double getFormatExtra(int filmTuru) {
        switch (filmTuru) {
            case 1: return 0.0;
            case 2: return 25.0;
            case 3: return 35.0;
            case 4: return 50.0;
            default: return 0.0;
        }
    }

    public static double calculateFinalPrice(int gun, int saat, int yas, int meslek, int filmTuru) {
        double base = calculateBasePrice(gun, saat);
        double discount = calculateDiscount(yas, meslek, gun);
        double indirimTutarı = base * discount;
        double araToplam = base - indirimTutarı;
        double extra = getFormatExtra(filmTuru);
        double toplam =  araToplam + extra;
        return toplam;
    }
    public static String generateTicketInfo(int gun, int saat, int yas, int meslek, int filmTuru,
                                            double base, double finalPrice, double discount) {
        return "=== BİLET BİLGİSİ ===\n" +
                "Gün: " + gun + "\n" +
                "Saat: " + saat + "\n" +
                "Yaş: " + yas + "\n" +
                "Meslek: " + meslek + "\n" +
                "Film Türü: " + filmTuru + "\n" +
                "Temel Fiyat: " + base + "\n" +
                "İndirim Oranı: %" + (discount) + "\n" +
                "Toplam Fiyat: " + finalPrice + " TL\n";

    }

}









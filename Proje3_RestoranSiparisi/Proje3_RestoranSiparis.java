/*
 * Ad Soyad: Melike Gücin
 * Ogrenci No: 240651081
 * Tarih: 27.11.2025
 * Aciklama: Akıllı Restoran Sipariş Sistemi
 */

import java.util.Scanner;

public class Proje3_RestoranSiparis {
    static double lastDrinkPrice = 0.0;

    public static double getMainDishPrice(int secim) {
        switch (secim) {
            case 1: // Izgara Tavuk
                return 85.0;
            case 2: // Adana Kebap
                return 120.0;
            case 3: // Levrek
                return 110.0;
            case 4: // Mantı
                return 65.0;
            case 0: // Yok
            default:
                return 0.0;
        }

    }

    public static double getAppetizerPrice(int secim) {
        switch (secim) {
            case 1: // Çorba
                return 25.0;
            case 2: // Humus
                return 45.0;
            case 3: //Sigara Böreği
                return 55.0;
            case 0: // Yok
            default:
                return 0.0;
        }
    }

    public static double getDrinkPrice(int secim) {

        double price;
        switch (secim)
        {
            case 1: // Kola
                price = 15.0;
                break;
            case 2: // Ayran
                price = 12.0;
                break;
            case 3: // Taze Meyve Suyu
                price = 35.0;
                break;
            case 4: // Limonata
                price = 25.0;
                break;
            case 0: // Yok
            default:
                price = 0.0;
        }
        lastDrinkPrice = price;
        return price;
    }

    public static double getDessertPrice(int secim) {
        switch (secim) {
            case 1: // Künefe
                return 65.0;
            case 2: // Baklava
                return 55.0;
            case 3: // Sütlaç
                return 35.0;
            case 0: // Yok
            default:
                return 0.0;
        }
    }

    public static boolean isComboOrder(boolean anaVar, boolean icecekVar, boolean tatliVar) {
        int code = (anaVar ? 1 : 0) + (icecekVar ? 1 : 0) + (tatliVar ? 1 : 0);

        switch (code) {
            case 3:
                return true;
            default:
                return false;


        }
    }
    public static boolean isHappyHour(int saat) {
       if  (saat >= 14.00 && saat <= 17.00)
           return true;
           else
           return false;

    }
    // Tüm İndirimlerr
    public static double calculateDiscount(double tutar, boolean combo, boolean ogrenci, int saat) {
        double toplamİndirim = 0.0;

        // Özel combo indirimi
        if (combo) {
            double comboİndirim = tutar * 0.15;
            toplamİndirim = toplamİndirim + comboİndirim;

            // Happy Hour İçecek İndirimi
            if (isHappyHour(saat) && lastDrinkPrice > 0) {
                double hhİndirim = lastDrinkPrice * 0.20;
                toplamİndirim = toplamİndirim + hhİndirim;
                double araToplam = tutar - toplamİndirim;

                // Öğrenci hafta içi indirimi (%10, araToplam üzerinden)
                if (ogrenci) {
                    double ogrenciİnd = araToplam * 0.10;
                    toplamİndirim = toplamİndirim + ogrenciİnd;
                    araToplam = araToplam - ogrenciİnd;
                }

                // 200 TL üzeri extra indirim
                if (araToplam > 200.0) {
                    double extraİndirim = araToplam * 0.10;
                    toplamİndirim = toplamİndirim + extraİndirim;
                }

            }
        }
        return toplamİndirim;
    }
    // Garson Önerisi
    public static double calculateServiceTip(double tutar) {
    return tutar * 0.10;
    }

    public static String getGunAdi(int gun) {
        switch (gun) {
            case 1: return "Pazartesi";
            case 2: return "Salı";
            case 3: return "Çarşamba";
            case 4: return "Perşembe";
            case 5: return "Cuma";
            case 6: return "Cumartesi";
            case 7: return "Pazar";
            default: return "Geçersiz";
        }
    }

    public static void main(String[] args) {
        Scanner siparis = new Scanner(System.in);

        System.out.println("=== AKILLI RESTORAN SIPARIS SISTEMI ===");

        System.out.print("Ana Yemek (0-4)  : ");
        int anaSecim = siparis.nextInt();

        System.out.print("Baslangic (0-3)  : ");
        int baslangicSecim = siparis.nextInt();

        System.out.print("Icecek (0-4)     : ");
        int icecekSecim = siparis.nextInt();

        System.out.print("Tatli (0-3)      : ");
        int tatliSecim = siparis.nextInt();

        System.out.print("Saat (8-23)      : ");
        int saat = siparis.nextInt();

        System.out.print("Ogrenci misiniz? (E/H): ");
        String ogrStr = siparis.next();
        boolean ogrenciMi = false;
        if (ogrStr == "E" || ogrStr == "e") ogrenciMi = true;

        System.out.print("Hangi gün? (1-7) : ");
        int gun = siparis.nextInt();

        // Fiyatları hesapla
        double anaFiyat = getMainDishPrice(anaSecim);
        double baslangicFiyat = getAppetizerPrice(baslangicSecim);
        double icecekFiyat = getDrinkPrice(icecekSecim);
        double tatliFiyat = getDessertPrice(tatliSecim);

        double araToplam = anaFiyat + baslangicFiyat + icecekFiyat + tatliFiyat;

        boolean anaVar = (anaFiyat > 0.0);
        boolean icecekVar = (icecekFiyat > 0.0);
        boolean tatliVar = (tatliFiyat > 0.0);

        boolean comboMu = isComboOrder(anaVar, icecekVar, tatliVar);

        boolean haftaIciMi = (gun >= 1 && gun <= 5);
        boolean ogrenciHaftaIci = (ogrenciMi && haftaIciMi);

        double toplamIndirim = calculateDiscount(araToplam, comboMu, ogrenciHaftaIci, saat);
        double odenecekTutar = araToplam - toplamIndirim;
        double bahsis = calculateServiceTip(odenecekTutar);

        System.out.println("\n=== SIPARIS OZETI ===");
        System.out.printf("Gun              : %s%n", getGunAdi(gun));
        System.out.printf("Saat             : %d:00%n", saat);
        System.out.println("-------------------------------");
        System.out.printf("Ana Yemek        : %.2f TL%n", anaFiyat);
        System.out.printf("Baslangic        : %.2f TL%n", baslangicFiyat);
        System.out.printf("Icecek           : %.2f TL%n", icecekFiyat);
        System.out.printf("Tatli            : %.2f TL%n", tatliFiyat);
        System.out.println("-------------------------------");
        System.out.printf("Ara Toplam       : %.2f TL%n", araToplam);
        System.out.printf("Toplam Indirim   : %.2f TL%n", toplamIndirim);
        System.out.printf("Odenecek Tutar   : %.2f TL%n", odenecekTutar);
        System.out.printf("Bahsis : %.2f TL%n", bahsis);
        System.out.println("===============================\n");

        siparis.close();
    }
}



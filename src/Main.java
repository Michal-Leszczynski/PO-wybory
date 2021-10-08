public class Main {

    public static void main(String[] args) {
        Reading reading = new Reading();
        Country bajtocja = Country.readCountry(reading);
        bajtocja.campaign();
        bajtocja.voting();
        bajtocja.results(new DHondtaMethode());
        bajtocja.results(new SainteLaguëMethode());
        bajtocja.results(new HareaNiemeyeraMethode());
        reading.closeScanner();
    }
}

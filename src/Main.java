import services.ApplicationServices;

public class Main {
    public static void main(String[] args) throws Exception {
        ApplicationServices.importData();
        ApplicationServices.getMyClubData();
        ApplicationServices.runApplication();
    }
}
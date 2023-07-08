package services;

public class Menu {
    public static void displayTaskManager() {
        System.out.println("->APPLICATION<-");
        System.out.println("1. Display data");
        System.out.println("2. Search");
        System.out.println("3. Edit data");
        System.out.println("4. Transfer");
        System.out.println("5. Save data");
        System.out.println("0. Exit");
        System.out.println("Choose task");
    }
    public static void displayTaskChooseData(){
        System.out.println("---------->DATA<----------");
        System.out.println("1. My Club data");
        System.out.println("2. Player in my club data");
        System.out.println("0. Exit");
        System.out.println("Choose task");
    }
    public static void displayTaskChoosePosition() {
        System.out.println("-->POSITION<--");
        System.out.println("1. Goalkeeper");
        System.out.println("2. Defender");
        System.out.println("3. Midfielder");
        System.out.println("4. Striker");
        System.out.println("0. Exit");
        System.out.println("Choose task");
    }
    public static void displayTaskChooseEditPlayerData() {
        System.out.println("----->EDIT-DATA<-----");
        System.out.println("1. Create new player");
        System.out.println("2. Edit data player");
        System.out.println("0. Exit");
        System.out.println("Choose task");
    }
    public static void displayTaskEditClubData() {
        System.out.println("------>CLUB-DATA<------");
        System.out.println("1. Edit nameClub");
        System.out.println("2. Edit yearFounded");
        System.out.println("3. Edit ground");
        System.out.println("4. Edit manager");
        System.out.println("5. Edit investmentMoney");
        System.out.println("0. Exit");
        System.out.println("Choose task");
    }
    public static void displayTaskEditPlayerData() {
        System.out.println("--->PLAYER-DATA<---");
        System.out.println("1. Edit namePlayer");
        System.out.println("2. Edit yearOfBirth");
        System.out.println("3. Edit national");
        System.out.println("4. Edit position");
        System.out.println("5. Edit number");
        System.out.println("6. Edit marketValue");
        System.out.println("7. Edit salary");
        System.out.println("0. Exit");
        System.out.println("Choose task");
    }
    public static void displayTaskChooseSearch() {
        System.out.println("---------->SEARCH<----------");
        System.out.println("1. All player in the league");
        System.out.println("2. Search by name");
        System.out.println("3. Search by club");
        System.out.println("4. Search by position");
        System.out.println("5. Freelance player");
        System.out.println("0. Exit");
        System.out.println("Choose task");
    }
    public static void displayTaskTransferPlayer() {
        System.out.println("--->TRANSFER<---");
        System.out.println("1. Buy player");
        System.out.println("2. Sell player");
        System.out.println("3. Layoff Player");
        System.out.println("0. Exit");
        System.out.println("Choose task");
    }
}

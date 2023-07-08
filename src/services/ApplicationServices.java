package services;

import club.Club;
import player.Player;
import player.PlayerFactory;
import player.Position;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

public class ApplicationServices {
    private static final Scanner scanner = new Scanner(System.in);
    private static Set<Player> allPlayersSet = new HashSet<>();
    private static Map<Integer, Player> myPlayersMap = new HashMap<>();
    private static Set<Club> allClubsSet = new HashSet<>();
    private static Club myClub = null;
    private static final String FILE_PATH_CLUB_DATA = "ClubsData.csv";
    private static final String FILE_PATH_PLAYER_DATA = "PlayersData.csv";
    private static String LINE_TITTLE_FILE_CLUB_DATA = "";
    private static String LINE_TITTLE_FILE_PLAYER_DATA = "";

    public static void importData() throws Exception {
        System.out.println("WELCOME TO BARCLAY PREMIER LEAGUE");

        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(FILE_PATH_CLUB_DATA);
            bufferedReader = new BufferedReader(fileReader);

            String line = bufferedReader.readLine();
            LINE_TITTLE_FILE_CLUB_DATA = line;
            line = bufferedReader.readLine();

            do {
                String[] data = line.split(";");
                String nameClub = data[0];
                int yearFounded = Integer.parseInt(data[1]);
                String ground = data[2];
                String manager = data[3];
                int totalPlayers = Integer.parseInt(data[4]);
                float investmentMoney = Float.parseFloat(data[5]);

                System.out.println(nameClub);

                allClubsSet.add(new Club.Builder()
                        .nameClub(nameClub)
                        .yearFounded(yearFounded)
                        .ground(ground)
                        .manager(manager)
                        .totalPlayers(totalPlayers)
                        .investmentMoney(investmentMoney)
                        .builder());
                line = bufferedReader.readLine();
            } while (line != null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            bufferedReader.close();
            fileReader.close();
        }
        try {
            fileReader = new FileReader(FILE_PATH_PLAYER_DATA);
            bufferedReader = new BufferedReader(fileReader);

            String line = bufferedReader.readLine();
            LINE_TITTLE_FILE_PLAYER_DATA = line;

            line = bufferedReader.readLine();

            do {
                String[] data = line.split(";");
                String namePlayer = data[0];
                String nameClub = data[1];
                int yearOfBirth = Integer.parseInt(data[2]);
                String national = data[3];
                int number = Integer.parseInt(data[4]);
                Position position = Position.valueOf(data[5]);
                float marketValue = Float.parseFloat(data[6]);
                float salary = Float.parseFloat(data[7]);

                allPlayersSet.add(PlayerFactory.createPlayer(nameClub, namePlayer, yearOfBirth, national, number, position, marketValue, salary));
                line = bufferedReader.readLine();
            } while (line != null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            bufferedReader.close();
            fileReader.close();
        }
    }
    public static void exportData() throws Exception {
        scanner.nextLine();
        System.out.println("Do you want to save the edited data?");
        System.out.println("Yes or No --> ");
        String answer = scanner.nextLine();
        if (answer.equalsIgnoreCase("YES")){
            FileWriter fileWriter = null;
            BufferedWriter bufferedWriter = null;

            try {
                fileWriter = new FileWriter(FILE_PATH_CLUB_DATA);
                bufferedWriter = new BufferedWriter(fileWriter);

                String line = LINE_TITTLE_FILE_CLUB_DATA;
                bufferedWriter.write(line);

                for (Club club : allClubsSet) {
                    bufferedWriter.newLine();
                    line = club.toWriteToFile();
                    bufferedWriter.write(line);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                bufferedWriter.close();
                fileWriter.close();
            }

            try {
                fileWriter = new FileWriter(FILE_PATH_PLAYER_DATA);
                bufferedWriter = new BufferedWriter(fileWriter);

                String line = LINE_TITTLE_FILE_PLAYER_DATA;
                bufferedWriter.write(line);

                for (Player player : allPlayersSet) {
                    bufferedWriter.newLine();
                    line = player.toWriteToFile();
                    bufferedWriter.write(line);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                bufferedWriter.close();
                fileWriter.close();
            }

            try {
                String nameMyClub = myClub.getNameClub();
                fileWriter = new FileWriter(nameMyClub + "FootballClubData.csv");
                bufferedWriter = new BufferedWriter(fileWriter);

                String line = LINE_TITTLE_FILE_CLUB_DATA;
                bufferedWriter.write(line);
                bufferedWriter.newLine();

                line = myClub.toWriteToFile();
                bufferedWriter.write(line);
                bufferedWriter.newLine();
                bufferedWriter.newLine();
                bufferedWriter.newLine();

                line = LINE_TITTLE_FILE_PLAYER_DATA;
                bufferedWriter.write(line);

                for (Player player : allPlayersSet) {
                    if (player.getNameClub().equalsIgnoreCase(nameMyClub)) {
                        bufferedWriter.newLine();
                        line = player.toWriteToFile();
                        bufferedWriter.write(line);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                bufferedWriter.close();
                fileWriter.close();
            }
            System.out.println("--> DONE!!!");
            System.out.println("--> Data has been saved successfully");
        }
        else if(answer.equalsIgnoreCase("NO")) {
            System.out.println("--> Continue use application <--");
        } else {
            exportData();
        }
    }
    public static void getMyClubData() {
        System.out.println("Enter name club --> ");
        String nameMyClub = scanner.nextLine();
        myClub = ClubServices.searchClubByName(allClubsSet, nameMyClub);

        if (myClub != null) {
            Set<Player> playerSet = PlayerServices.searchPlayerByNameClub(allPlayersSet, nameMyClub);
            for(Player player: playerSet) {
                myPlayersMap = PlayerServices.addPlayerToMap(myPlayersMap, player);
            }
        } else {
            System.out.println("****VALUE INVALID***");
            System.out.println("--> Please enter the correct name club!!!!");
            getMyClubData();
        }
    }

    public static void runApplication() throws Exception {
        Menu.displayTaskManager();
        Scanner scanner = new Scanner(System.in);
        int task = scanner.nextInt();
        switch (task) {
            case 1 -> {
                displayData();
                runApplication();
            }
            case 2 -> {
                searchData();
                runApplication();
            }
            case 3 -> {
                editData();
                runApplication();
            }
            case 4 -> {
                transferPlayer();
                runApplication();
            }
            case 5 -> {
                exportData();
                runApplication();
            }
            case 0 -> {
                exportData();
                System.out.println("***Thank for your use***");
                System.out.println("--> GOODBYE <--");
            }
        }
    }

    //display data
    public static void displayData() {
        Menu.displayTaskChooseData();
        int task = scanner.nextInt();
        switch (task) {
            case 1 -> {
                System.out.println(myClub.toString());
                System.out.println("Press any button to continue");
                scanner.nextInt();
            }
            case 2 -> displayMyPlayerWithPosition();
            case 0 -> {}
            default -> displayData();
        }
    }
    public static void displayMyPlayerWithPosition() {
        Menu.displayTaskChoosePosition();
        int task = scanner.nextInt();
        Position position = null;
        switch (task) {
            case 1 -> position = Position.Goalkeeper;
            case 2 -> position = Position.Defender;
            case 3 -> position = Position.Midfielder;
            case 4 -> position = Position.Striker;
            case 0 -> displayData();
            default -> displayMyPlayerWithPosition();
        }
        if (position != null) {
            Set<Player> myPlayersSet = new HashSet<>(myPlayersMap.values());
            Set<Player>  byPositionSet = PlayerServices.searchPlayerByPosition(myPlayersSet, position);
            byPositionSet = PlayerServices.sortPlayerByMarketValue(byPositionSet);
            PlayerServices.displayPlayerData(byPositionSet);
            System.out.println("Press 0 to continue choose other position or any button to exit --> ");
            task = scanner.nextInt();
            if(task == 0) {
                displayMyPlayerWithPosition();
            }
        }
    }

    //search player
    public static void searchData() {
        Menu.displayTaskChooseSearch();
        int task = scanner.nextInt();
        switch (task) {
            case 1 -> {
                Set<Player> sortedPlayersSet = PlayerServices.sortPlayerByMarketValue(allPlayersSet);
                PlayerServices.displayPlayerData(sortedPlayersSet);
                System.out.println("Press any button to continue");
                scanner.nextInt();
            }
            case 2 -> searchPlayerByNamePlayer();
            case 3 -> searchPlayerByNameClub();
            case 4 -> searchPlayerByPosition();
            case 5 -> {
                String nameClubSearch = "Freelance player";
                Set<Player> byNameClubSet = PlayerServices.searchPlayerByNameClub(allPlayersSet, nameClubSearch);
                byNameClubSet = PlayerServices.sortPlayerByMarketValue(byNameClubSet);
                PlayerServices.displayPlayerData(byNameClubSet);
                System.out.println("Press any button to continue");
                scanner.nextInt();
            }
            case 0 -> {}
            default -> searchData();
        }
    }
    public static void searchPlayerByNamePlayer() {
        scanner.nextLine();
        System.out.println("Enter name player --> ");
        String namePlayerSearch = scanner.nextLine();

        Set<Player> byNamePlayerSet = PlayerServices.searchPlayerByNamePlayer(allPlayersSet, namePlayerSearch);

        if (byNamePlayerSet.size() != 0) {
            byNamePlayerSet = PlayerServices.sortPlayerByMarketValue(byNamePlayerSet);
            PlayerServices.displayPlayerData(byNamePlayerSet);
        } else {
            System.out.println("***Value invalid***");
        }
        System.out.println("Press 0 to continue search or any button to exit --> ");
        int task = scanner.nextInt();
        if(task == 0) {
            searchPlayerByNamePlayer();
        }
    }
    public static void searchPlayerByNameClub() {
        scanner.nextLine();
        System.out.println("Enter name club --> ");
        String nameClubSearch = scanner.nextLine();

        Set<Player> byClubNameSet = PlayerServices.searchPlayerByNameClub(allPlayersSet, nameClubSearch);

        if (byClubNameSet.size() != 0) {
            byClubNameSet = PlayerServices.sortPlayerByMarketValue(byClubNameSet);
            PlayerServices.displayPlayerData(byClubNameSet);
        } else {
            System.out.println("***Value invalid***");
        }
        System.out.println("Press 0 to continue search or any button to exit --> ");
        int task = scanner.nextInt();
        if(task == 0) {
            searchPlayerByNameClub();
        }
    }
    public static void searchPlayerByPosition(){
        Menu.displayTaskChoosePosition();
        int task = scanner.nextInt();
        Position position = null;
        switch (task) {
            case 1 -> position = Position.Goalkeeper;
            case 2 -> position = Position.Defender;
            case 3 -> position = Position.Midfielder;
            case 4 -> position = Position.Striker;
            case 0 -> {}
            default -> {}
        }
        if (position != null) {
            Set<Player> byPositionSet = PlayerServices.searchPlayerByPosition(allPlayersSet, position);
            byPositionSet = PlayerServices.sortPlayerByMarketValue(byPositionSet);
            PlayerServices.displayPlayerData(byPositionSet);
        }
        System.out.println("Press 0 to continue search or any button to exit --> ");
        task = scanner.nextInt();
        if(task == 0) {
            searchPlayerByPosition();
        }
    }

    //edit data
    public static void editData( ) {
        Menu.displayTaskChooseData();
        int task = scanner.nextInt();
        switch (task) {
            case 1 -> editClubData();
            case 2 -> {
                do {
                    Menu.displayTaskChooseEditPlayerData();
                    task = scanner.nextInt();

                    switch (task) {
                        case 1 -> createNewMyPlayer();
                        case 2 -> editMyPlayerData();
                        case 0 -> {}
                        default -> {}
                    }
                } while (task < 0 || task > 2);
            }
            case 0 -> {}
            default -> editData();
        }
    }
    public static void editClubData() {
        String nameMyClub = myClub.getNameClub();
        Club editClub = ClubServices.searchClubByName(allClubsSet, nameMyClub);
        System.out.println(editClub.toString());
        int task = -1;
        do {
            Menu.displayTaskEditClubData();
            task = scanner.nextInt();
            editClub = ClubServices.eidtDataClub(editClub, task);
        } while (task <0 || task > 5);

        if(task != 0) {
            allClubsSet = ClubServices.editAllClubsSet(allClubsSet, myClub, editClub);

            allPlayersSet = PlayerServices.editClubInPlayerSet(allPlayersSet, myClub.getNameClub(), editClub.getNameClub());

            myPlayersMap = PlayerServices.editClubInPlayerMap(myPlayersMap, editClub.getNameClub());

            myClub = editClub;

            System.out.println("Edit data successfully");
            System.out.println("--> New data of your club");
            System.out.println(myClub.toString());
        }
        System.out.println("Press 0 to continue edit club data or any button to exit --> ");
        task = scanner.nextInt();
        if(task == 0) {
            editClubData();
        }
    }
    public static void createNewMyPlayer() {
        String myNameClub = myClub.getNameClub();
        Set<Player> myPlayersSet = new HashSet<>(myPlayersMap.values());
        Player newPlayer = PlayerServices.createNewPlayer(myPlayersSet, myNameClub);

        myPlayersMap.put(newPlayer.getNumber(), newPlayer);

        int newTotalPlayers = myClub.getTotalPlayers() + 1;
        myClub.setTotalPlayers(newTotalPlayers);

        for (Club club : allClubsSet) {
            if (club.getNameClub().equalsIgnoreCase(myNameClub)) {
                club = myClub;
            }
        }

        allPlayersSet.add(newPlayer);

        System.out.println("--> DONE!");
        System.out.println("Player has been created successfully");
        System.out.println(newPlayer.toString());
    }
    public static void editMyPlayerData() {
        scanner.nextLine();
        System.out.println("Enter name player --> ");
        String namePlayerSearch = scanner.nextLine();

        Set<Player> myPlayersSet = new HashSet<>(myPlayersMap.values());
        Set<Player> byNamePlayerSet = PlayerServices.searchPlayerByNamePlayer(myPlayersSet, namePlayerSearch);
        PlayerServices.displayPlayerData(byNamePlayerSet);

        if (byNamePlayerSet.size() == 0) {
            System.out.println("***This player is not already your team player or value invalid***");
        }

        if(byNamePlayerSet.size() > 1) {
            PlayerServices.displayPlayerData(byNamePlayerSet);
            do {
                System.out.println("Enter the number of player you want to edit data -->");
                int number = scanner.nextInt();
                byNamePlayerSet = PlayerServices.searchPlayerByNumber(byNamePlayerSet, number);
            } while (byNamePlayerSet.size() > 1);
        }

        if(byNamePlayerSet.size() == 1) {
            Player editPlayer = null;
            Player newDataPlayer;
            for (Player player: byNamePlayerSet) {
                editPlayer = player;
            }
            int task;
            do {
                Menu.displayTaskEditPlayerData();
                task = scanner.nextInt();
                newDataPlayer = PlayerServices.editDataPlayer(myPlayersSet, editPlayer, task);
            } while (task < 0 || task > 7);

            if (task != 0) {
                for (Player player: allPlayersSet) {
                    if (player.getNameClub().equalsIgnoreCase(editPlayer.getNameClub()) && player.getNumber() == editPlayer.getNumber()) {
                        player = newDataPlayer;
                    }
                }

                if(editPlayer.getNumber() == newDataPlayer.getNumber()) {
                    myPlayersMap.put(newDataPlayer.getNumber(), newDataPlayer);
                } else {
                    myPlayersMap.put(newDataPlayer.getNumber(), newDataPlayer);
                    myPlayersMap.remove(editPlayer.getNumber());
                }

                System.out.println("--> Edit data successful!!!");
                System.out.println(newDataPlayer.toString());
            }
        }
        System.out.println("Press 0 to continue edit player data or any button to exit --> ");
        int task = scanner.nextInt();
        if(task == 0) {
            editMyPlayerData();
        }
    }


    //transfer
    public static void transferPlayer()  {
        Menu.displayTaskTransferPlayer();
        int task = scanner.nextInt();
        switch (task) {
            case 1 -> buyOtherPlayer();
            case 2 -> sellMyPlayer();
            case 3 -> layyOffMyPlayer();
            case 0 -> {}
            default -> transferPlayer();
        }
    }
    public static void buyOtherPlayer() {
        scanner.nextLine();
        System.out.println("Enter name player you want to buy --> ");
        String namePlayerSearch = scanner.nextLine();

        Set<Player> needBuyPlayerSet = PlayerServices.searchPlayerByNamePlayer(allPlayersSet, namePlayerSearch);

        for(Player player: needBuyPlayerSet) {
            if(player.getNameClub().equalsIgnoreCase(myClub.getNameClub())) {
                needBuyPlayerSet.remove(player);
            } else {
                System.out.println(player.toString());
            }
        }

        if(needBuyPlayerSet.size() == 0) {
            System.out.println("***This player is already your team player or value invalid***");
        }

        Club clubSellPlayer;
        if(needBuyPlayerSet.size() > 1) {
            PlayerServices.displayPlayerData(needBuyPlayerSet);
            do{
                System.out.println("Enter name club you want to buy player --> ");
                String nameClubToBuyPlayer = scanner.nextLine();
                clubSellPlayer = ClubServices.searchClubByName(allClubsSet, nameClubToBuyPlayer);
            } while (clubSellPlayer == null);

            needBuyPlayerSet = PlayerServices.searchPlayerByNamePlayer(needBuyPlayerSet, clubSellPlayer.getNameClub());
            PlayerServices.displayPlayerData(needBuyPlayerSet);
        }

        if(needBuyPlayerSet.size() == 1) {
            Player needBuyPlayer = null;
            for(Player player: needBuyPlayerSet) {
                needBuyPlayer = player;
            }
            clubSellPlayer = ClubServices.searchClubByName(allClubsSet, needBuyPlayer.getNameClub());

            System.out.println("Do you want to buy this player?");

            boolean isCheck = false;
            String answer = "";
            do{
                System.out.println("Yes or No? --> ");
                answer = scanner.nextLine();
                if(answer.equalsIgnoreCase("Yes") || answer.equalsIgnoreCase("No")) {
                    isCheck = true;
                }
            } while (!isCheck);

            if(answer.equalsIgnoreCase("Yes")) {
                Set<Player> myPlayersSet = new HashSet<>(myPlayersMap.values());
                Player newDataPlayer = PlayerServices.editDataPlayer(myPlayersSet, needBuyPlayer, 5);
                newDataPlayer.setNameClub(myClub.getNameClub());

                myPlayersMap.put(newDataPlayer.getNumber(), newDataPlayer);

                for(Player player: allPlayersSet) {
                    if(player.getNamePlayer().equalsIgnoreCase(needBuyPlayer.getNamePlayer()) && player.getNameClub().equalsIgnoreCase(needBuyPlayer.getNameClub())) {
                        player = newDataPlayer;
                    }
                }

                myClub.setTotalPlayers(myClub.getTotalPlayers() + 1);
                myClub.setInvestmentMoney(myClub.getInvestmentMoney() - newDataPlayer.getMarketValue());

                if(clubSellPlayer != null) {
                    clubSellPlayer.setTotalPlayers(clubSellPlayer.getTotalPlayers() - 1);
                    clubSellPlayer.setInvestmentMoney(clubSellPlayer.getInvestmentMoney() + newDataPlayer.getMarketValue());
                }

                for(Club club: allClubsSet) {
                    if(club.getNameClub().equalsIgnoreCase(myClub.getNameClub())) {
                        club = myClub;
                    }
                    if(clubSellPlayer!= null &&
                            club.getNameClub().equalsIgnoreCase(clubSellPlayer.getNameClub())) {
                        club = clubSellPlayer;
                    }
                }

                System.out.println("--> DONE");
                System.out.println("--> Buy player successful!!!");
            }
        }

        System.out.println("Press 0 to continue buy player or any button to exit --> ");
        int task = scanner.nextInt();
        if(task == 0) {
            buyOtherPlayer();
        }
    }
    public static void sellMyPlayer() {
        scanner.nextLine();
        System.out.println("Enter name player you want to sell --> ");
        String namePlayerSearch = scanner.nextLine();

        Set<Player> myPlayersSet = new HashSet<>(myPlayersMap.values());
        Set<Player> byNamePlayerSet = PlayerServices.searchPlayerByNamePlayer(myPlayersSet, namePlayerSearch);

        if(byNamePlayerSet.size() == 0) {
            System.out.println("***This player is not already your team player or value invalid***");
        }

        if(byNamePlayerSet.size() > 1) {
            PlayerServices.displayPlayerData(byNamePlayerSet);
            do {
                System.out.println("Enter the number of player you want to sell --> ");
                int number = scanner.nextInt();
                byNamePlayerSet = PlayerServices.searchPlayerByNumber(byNamePlayerSet, number);
            } while (byNamePlayerSet.size() > 1);
        }

        if(byNamePlayerSet.size() == 1) {
            Player needSellPlayer = null;
            for(Player player: byNamePlayerSet) {
                needSellPlayer = player;
            }
            System.out.println(needSellPlayer.toString());
            System.out.println("Do you want to sell this player?");

            boolean isCheck = false;
            String answer = "";
            do{
                System.out.println("Yes or No? --> ");
                answer = scanner.nextLine();
                if(answer.equalsIgnoreCase("Yes") || answer.equalsIgnoreCase("No")) {
                    isCheck = true;
                }
            } while (!isCheck);


            if(answer.equalsIgnoreCase("Yes")) {
                Club clubBuyPlayer;
                do{
                    System.out.println("Enter name club you want to sell this player --> ");
                    String nameClubToBuyPlayer = scanner.nextLine();
                    clubBuyPlayer = ClubServices.searchClubByName(allClubsSet, nameClubToBuyPlayer);
                } while (clubBuyPlayer == null);

                Player newDataPlayer = needSellPlayer;

                myPlayersMap.remove(needSellPlayer.getNumber());

                newDataPlayer.setNameClub(clubBuyPlayer.getNameClub());
                newDataPlayer.setNumber(99);

                for(Player player: allPlayersSet) {
                    if(player.getNamePlayer().equalsIgnoreCase(needSellPlayer.getNamePlayer())
                            && player.getNameClub().equalsIgnoreCase(needSellPlayer.getNameClub())
                            && player.getNumber() == needSellPlayer.getNumber()) {
                        player = newDataPlayer;
                    }
                }

                myClub.setTotalPlayers(myClub.getTotalPlayers() - 1);
                clubBuyPlayer.setTotalPlayers(clubBuyPlayer.getTotalPlayers() + 1);

                myClub.setInvestmentMoney(myClub.getInvestmentMoney() + newDataPlayer.getMarketValue());
                clubBuyPlayer.setInvestmentMoney(clubBuyPlayer.getInvestmentMoney() - newDataPlayer.getMarketValue());

                for(Club club: allClubsSet) {
                    if(club.getNameClub().equalsIgnoreCase(myClub.getNameClub())) {
                        club = myClub;
                    }
                    if(club.getNameClub().equalsIgnoreCase(clubBuyPlayer.getNameClub())) {
                        club = clubBuyPlayer;
                    }
                }

                System.out.println("--> DONE");
                System.out.println("--> Sell player successful!!!");
            }
        }

        System.out.println("Press 0 to continue sell player or any button to exit --> ");
        int task = scanner.nextInt();
        if(task == 0) {
            sellMyPlayer();
        }
    }
    public static void layyOffMyPlayer() {
        scanner.nextLine();
        System.out.println("Enter name player you want to lay off --> ");
        String namePlayerSearch = scanner.nextLine();

        Set<Player> myPlayersSet = new HashSet<>(myPlayersMap.values());
        Set<Player> byNamePlayerSet = PlayerServices.searchPlayerByNamePlayer(myPlayersSet, namePlayerSearch);

        if(byNamePlayerSet.size() == 0) {
            System.out.println("***This player is not already your team player or value invalid***");
        }

        if(byNamePlayerSet.size() > 1) {
            PlayerServices.displayPlayerData(byNamePlayerSet);
            do {
                System.out.println("Enter the number of player you want to lay off --> ");
                int number = scanner.nextInt();
                byNamePlayerSet = PlayerServices.searchPlayerByNumber(byNamePlayerSet, number);
            } while (byNamePlayerSet.size() > 1);
        }

        if(byNamePlayerSet.size() == 1) {
            Player needLayOffPlayer = null;
            for(Player player: byNamePlayerSet) {
                needLayOffPlayer = player;
            }
            System.out.println(needLayOffPlayer.toString());
            System.out.println("Do you want to lay off this player?");

            boolean isCheck = false;
            String answer;
            do{
                System.out.println("Yes or No? --> ");
                answer = scanner.nextLine();
                if(answer.equalsIgnoreCase("Yes") || answer.equalsIgnoreCase("No")) {
                    isCheck = true;
                }
            } while (!isCheck);


            if(answer.equalsIgnoreCase("Yes")) {
                Player newDataPlayer = needLayOffPlayer;

                myPlayersMap.remove(needLayOffPlayer.getNumber());

                newDataPlayer.setNameClub("Freelance player");

                for(Player player: allPlayersSet) {
                    if(player.getNamePlayer().equalsIgnoreCase(needLayOffPlayer.getNamePlayer())
                            && player.getNameClub().equalsIgnoreCase(needLayOffPlayer.getNameClub())
                            && player.getNumber() == needLayOffPlayer.getNumber()) {
                        player = newDataPlayer;
                    }
                }

                myClub.setTotalPlayers(myClub.getTotalPlayers() - 1);

                for(Club club: allClubsSet) {
                    if(club.getNameClub().equalsIgnoreCase(myClub.getNameClub())) {
                        club = myClub;
                    }
                }

                System.out.println("--> DONE");
                System.out.println("--> Lay off player successful!!!");
            }
        }

        System.out.println("Press 0 to continue lay off player or any button to exit --> ");
        int task = scanner.nextInt();
        if(task == 0) {
            layyOffMyPlayer();
        }
    }
}

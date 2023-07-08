package services;

import player.Player;
import player.PlayerFactory;
import player.Position;

import java.util.*;

public class PlayerServices {
    private static Scanner scanner = new Scanner(System.in);
    public static void displayPlayerData(Set<Player> playerSet) {
        if(playerSet.size() != 0) {
            int index = 0;
            for(Player player: playerSet) {
                index ++;
                System.out.println(index + ".\s" + player.toString());
            }
        }
    }
    public static Set<Player> searchPlayerByNamePlayer(Set<Player> playerSet, String namePlayer) {
        Set<Player> byNamePlayerSet = new HashSet<>();
        for(Player player: playerSet) {
            if(player.getNamePlayer().equalsIgnoreCase(namePlayer)) {
                byNamePlayerSet.add(player);
            }
        }
        return byNamePlayerSet;
    }
    public static Set<Player> searchPlayerByNameClub(Set<Player> playerSet, String nameClub) {
        Set<Player> byNameClubSet = new HashSet<>();
        for(Player player: playerSet) {
            if(player.getNameClub().equalsIgnoreCase(nameClub)) {
                byNameClubSet.add(player);
            }
        }
        return byNameClubSet;
    }
    public static Set<Player> searchPlayerByPosition(Set<Player> playerSet, Position position) {
        Set<Player> byPositionSet = new HashSet<>();
        for(Player player: playerSet) {
            if(player.getPosition() == position) {
                byPositionSet.add(player);
            }
        }
        return byPositionSet;
    }
    public static Set<Player>  searchPlayerByNumber(Set<Player> playerSet, int number) {
        Set<Player> byNumberPlayer = new HashSet<>();
        for(Player player: playerSet) {
            if(player.getNumber() == number) {
                byNumberPlayer.add(player);
            }
        }
        return byNumberPlayer;
    }
    public static Map<Integer, Player> addPlayerToMap(Map<Integer, Player> playerMap, Player newPlayer) {
        for(Integer key: playerMap.keySet()) {
            if(key == newPlayer.getNumber()) {
                System.out.println("--> This player's number was used by another players!!!");
                return playerMap;
            }
        }
        playerMap.put(newPlayer.getNumber(), newPlayer);
        return playerMap;
    }
    public static Set<Player> sortPlayerByMarketValue(Set<Player> playerSet) {
        List<Player> sortedList = new ArrayList<>(playerSet);
        sortedList.sort(new Comparator<>() {
            @Override
            public int compare(Player p1, Player p2) {
                return (int) (p2.getMarketValue() - p1.getMarketValue());
            }
        });
        Set<Player> sortedPlayersSet = new LinkedHashSet<>(sortedList);
        return sortedPlayersSet;
    }
    public static Set<Player> editClubInPlayerSet(Set<Player> playerSet, String oldNameClub, String newNameClub) {
        for(Player player: playerSet) {
            if(player.getNameClub().equalsIgnoreCase(oldNameClub)) {
                player.setNameClub(newNameClub);
            }
        }
        return playerSet;
    }
    public static Map<Integer, Player> editClubInPlayerMap (Map<Integer, Player> playersMap, String newNameClub) {
        for (Integer key: playersMap.keySet()) {
            Player player = playersMap.get(key);
            player.setNameClub(newNameClub);
        }
        return playersMap;
    }
    public static Player createNewPlayer(Set<Player> playerSet, String nameMyClub) {
        System.out.println("Enter namePlayer --> ");
        String namePlayer = scanner.nextLine();

        String nameClub = nameMyClub;

        System.out.println("Enter yearOfBirth --> ");
        int yearOfBirth = scanner.nextInt();

        scanner.nextLine();
        System.out.println("Enter national --> ");
        String national = scanner.nextLine();

        System.out.println("Enter position --> ");
        Position position = Position.valueOf(scanner.nextLine());
        
        int number;
        do {
            System.out.println("Enter number --> ");
            number = scanner.nextInt();
            Set<Player> byNumberPlayer = searchPlayerByNumber(playerSet, number);
            if(byNumberPlayer.size() != 0) {
                System.out.println("--> This number is already in use, please choose another number");
                number = 0;
            }
        } while (number <= 0 || number > 99);

        scanner.nextLine();
        System.out.println("Enter marketValue --> ");
        float marketValue = scanner.nextFloat();

        scanner.nextLine();
        System.out.println("Enter salary --> ");
        float salary = scanner.nextFloat();

        Player newPlayer = PlayerFactory.createPlayer(nameClub, namePlayer, yearOfBirth, national, number, position, marketValue, salary);
        return newPlayer;
    }
    public static Player editDataPlayer(Set<Player> playerSet, Player player, int task) {
        switch (task) {
            case 1 -> {
                scanner.nextLine();
                System.out.println("Enter namePlayer --> ");
                String namePlayer = scanner.nextLine();
                player.setNamePlayer(namePlayer);
            }
            case 2 -> {
                System.out.println("Enter yearOfBirth --> ");
                int yearOfBirth = scanner.nextInt();
                player.setYearOfBirth(yearOfBirth);
            }
            case 3 -> {
                System.out.println("Enter national --> ");
                String national = scanner.nextLine();
                player.setNational(national);
            }
            case 4 -> {
                Position position;
                do{
                    System.out.println("Enter position --> ");
                    position = Position.valueOf(scanner.nextLine());
                    if(position != Position.Goalkeeper && position != Position.Defender && position != Position.Midfielder && position != Position.Striker) {
                        position = null;
                    }
                } while (position == null);

                player.setPosition(position);
                player = PlayerFactory.createPlayer(player.getNameClub(), player.getNamePlayer(), player.getYearOfBirth(), player.getNational(), player.getNumber(),player.getPosition(), player.getMarketValue(), player.getSalary());
            }
            case 5 -> {
                int number;
                do {
                    System.out.println("Enter number --> ");
                    number = scanner.nextInt();
                    Set<Player> byNumberPlayer = searchPlayerByNumber(playerSet, number);
                    if(byNumberPlayer.size() != 0) {
                        System.out.println("--> This number is already in use, please choose another number");
                        number = 0;
                    }
                } while (number <= 0 || number > 99);
                player.setNumber(number);
            }
            case 6 -> {
                System.out.println("Enter marketValue --> ");
                float marketValue = scanner.nextFloat();
                player.setMarketValue(marketValue);
            }
            case 7 -> {
                System.out.println("Enter salary --> ");
                float salary = scanner.nextFloat();
                player.setSalary(salary);
            }
        }
        return player;
    }
}

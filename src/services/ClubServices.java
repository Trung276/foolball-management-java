package services;

import club.Club;

import java.util.Scanner;
import java.util.Set;

public class ClubServices {
    private static final Scanner SCANNER = new Scanner(System.in);
    public static Club searchClubByName(Set<Club> clubSet, String nameClub) {
        Club byNameClub = null;
        for(Club club: clubSet) {
            if(club.getNameClub().equalsIgnoreCase(nameClub)) {
                byNameClub = club;
            }
        }
        return byNameClub;
    }
    public static Set<Club> editAllClubsSet(Set<Club> clubSet, Club oldDataClub, Club newDataClub) {
        for (Club club : clubSet) {
            if (club.getNameClub().equalsIgnoreCase(oldDataClub.getNameClub())) {
                club = newDataClub;
            }
        }
        return clubSet;
    }
    public static Club eidtDataClub(Club editClub, int task) {
        switch (task) {
            case 1 -> {
                System.out.println("Enter nameClub --> ");
                String newNameClub = SCANNER.nextLine();
                editClub.setNameClub(newNameClub);
            }
            case 2 -> {
                System.out.println("Enter yearFounded --> ");
                int newYearFounded = SCANNER.nextInt();
                editClub.setYearFounded(newYearFounded);
            }
            case 3 -> {
                System.out.println("Enter ground --> ");
                String newGround = SCANNER.nextLine();
                editClub.setGround(newGround);
            }
            case 4 -> {
                System.out.println("Enter manager --> ");
                String newManager = SCANNER.nextLine();
                editClub.setManager(newManager);
            }
            case 5 -> {
                System.out.println("Enter investmentMoney ($) --> ");
                float newInvestmentMoney = SCANNER.nextFloat();
                editClub.setInvestmentMoney(newInvestmentMoney);
            }
            case 0 -> {}
            default -> {}
        }
        return editClub;
    }
}

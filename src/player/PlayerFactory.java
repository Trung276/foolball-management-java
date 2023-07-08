package player;

public class PlayerFactory {
    public static Player createPlayer(String nameClub, String namePlayer, int yearOfBirth, String national, int number, Position position, float marketValue, float salary) {
        Player player;
        switch (position) {
            case Goalkeeper -> {
                player = new Goalkeeper.Builder()
                        .nameClub(nameClub)
                        .namePlayer(namePlayer)
                        .yearOfBirth(yearOfBirth)
                        .national(national)
                        .number(number)
                        .position(position)
                        .marketValue(marketValue)
                        .salary(salary)
                        .builder();
            }
            case Defender -> {
                player = new Defender.Builder()
                        .nameClub(nameClub)
                        .namePlayer(namePlayer)
                        .yearOfBirth(yearOfBirth)
                        .national(national)
                        .number(number)
                        .position(position)
                        .marketValue(marketValue)
                        .salary(salary)
                        .builder();
            }
            case Midfielder -> {
                player = new Midfielder.Builder()
                        .nameClub(nameClub)
                        .namePlayer(namePlayer)
                        .yearOfBirth(yearOfBirth)
                        .national(national)
                        .number(number)
                        .position(position)
                        .marketValue(marketValue)
                        .salary(salary)
                        .builder();
            }
            case Striker ->  {
                player = new Striker.Builder()
                        .nameClub(nameClub)
                        .namePlayer(namePlayer)
                        .yearOfBirth(yearOfBirth)
                        .national(national)
                        .number(number)
                        .position(position)
                        .marketValue(marketValue)
                        .salary(salary)
                        .builder();
            }
            default -> player = null;
        }
        return player;
    }
}

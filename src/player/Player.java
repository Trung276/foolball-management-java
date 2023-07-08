package player;

public abstract class Player {
    protected String namePlayer;
    protected String nameClub;
    protected int yearOfBirth;
    protected String national;
    protected Position position;
    protected int number;
    protected float marketValue;
    protected float salary;

    public String getNameClub() {
        return nameClub;
    }

    public void setNameClub(String nameClub) {
        this.nameClub = nameClub;
    }

    public String getNamePlayer() {
        return namePlayer;
    }

    public void setNamePlayer(String namePlayer) {
        this.namePlayer = namePlayer;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getNational() {
        return national;
    }

    public void setNational(String national) {
        this.national = national;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public float getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(float marketValue) {
        this.marketValue = marketValue;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public abstract String toString();
    public String toWriteToFile() {
        return namePlayer +
                ';' + nameClub +
                ';' + yearOfBirth +
                ';' + national + '\'' +
                ';' + number +
                ';' + position +
                ';' + marketValue +
                ';' + salary;
    }
}

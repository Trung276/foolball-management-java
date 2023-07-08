package player;

public class Goalkeeper extends Player{
    private Goalkeeper(Builder builder) {
        this.nameClub = builder.nameClub;
        this.namePlayer = builder.namePlayer;
        this.yearOfBirth = builder.yearOfBirth;
        this.national = builder.national;
        this.number = builder.number;
        this.position = builder.position;
        this.marketValue = builder.marketValue;
        this.salary = builder.salary;
    }
    public static class Builder {
        private String nameClub;
        private String namePlayer;
        private int yearOfBirth;
        private String national;
        private int number;
        private Position position;
        private float marketValue;
        private float salary;
        public Builder(){

        }
        public Builder nameClub(String nameClub) {
            this.nameClub = nameClub;
            return this;
        }
        public Builder namePlayer (String namePlayer) {
            this.namePlayer = namePlayer;
            return this;
        }
        public Builder yearOfBirth(int yearOfBirth) {
            this.yearOfBirth = yearOfBirth;
            return this;
        }
        public Builder national(String national) {
            this.national = national;
            return this;
        }
        public Builder position(Position position) {
            this.position = position;
            return this;
        }
        public Builder number(int number) {
            this.number = number;
            return this;
        }
        public Builder position() {
            return this;
        }
        public Builder marketValue(float marketValue) {
            this.marketValue = marketValue;
            return this;
        }
        public Builder salary(float salary) {
            this.salary = salary;
            return this;
        }
        public Goalkeeper builder(){
            return new Goalkeeper(this);
        }
    }

    @Override
    public String toString() {
        return "Goalkeeper{" +
                "namePlayer='" + namePlayer + '\'' +
                ", nameClub='" + nameClub + '\'' +
                ", yearOfBirth=" + yearOfBirth +
                ", national='" + national + '\'' +
                ", position=" + position +
                ", number=" + number +
                ", marketValue=" + marketValue +
                ", salary=" + salary +
                "$ per year}";    }
}

package club;

public class Club {
    private String nameClub;
    private int yearFounded;
    private String ground;
    private String manager;
    private int totalPlayers;
    private float investmentMoney;

    private Club(Builder builder) {
        this.nameClub = builder.nameClub;
        this.yearFounded = builder.yearFounded;
        this.ground = builder.ground;
        this.manager = builder.manager;
        this.totalPlayers = builder.totalPlayers;
        this.investmentMoney = builder.investmentMoney;
    }

    public static class Builder {
        private String nameClub;
        private int yearFounded;
        private String ground;
        private String manager;
        private int totalPlayers;
        private float investmentMoney;

        public Builder() {
        }
        public Builder nameClub(String nameClub) {
            this.nameClub = nameClub;
            return this;
        }

        public Builder yearFounded(int yearFounded) {
            this.yearFounded = yearFounded;
            return this;
        }

        public Builder ground(String ground) {
            this.ground = ground;
            return this;
        }

        public Builder manager(String manager) {
            this.manager = manager;
            return this;
        }

        public Builder totalPlayers(int totalPlayers) {
            this.totalPlayers = totalPlayers;
            return this;
        }

        public Builder investmentMoney(float investmentMoney) {
            this.investmentMoney = investmentMoney;
            return this;
        }

        public Club builder() {
            return new Club(this);
        }
    }

    public String getNameClub() {
        return nameClub;
    }

    public int getYearFounded() {
        return yearFounded;
    }

    public String getGround() {
        return ground;
    }

    public String getManager() {
        return manager;
    }

    public int getTotalPlayers() {
        return totalPlayers;
    }

    public float getInvestmentMoney() {
        return investmentMoney;
    }

    public void setNameClub(String nameClub) {
        this.nameClub = nameClub;
    }

    public void setYearFounded(int yearFounded) {
        this.yearFounded = yearFounded;
    }

    public void setGround(String ground) {
        this.ground = ground;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public void setTotalPlayers(int totalPlayers) {
        this.totalPlayers = totalPlayers;
    }

    public void setInvestmentMoney(float investmentMoney) {
        this.investmentMoney = investmentMoney;
    }

    @Override
    public String toString() {
        return "Club{" +
                "nameClub='" + nameClub + '\'' +
                ", yearFounded=" + yearFounded +
                ", ground='" + ground + '\'' +
                ", manager='" + manager + '\'' +
                ", totalPlayers=" + totalPlayers +
                ", investmentMoney=" + investmentMoney +
                "$}";
    }
    public String toWriteToFile() {
        return nameClub +
                ';' + yearFounded +
                ';' + ground +
                ';' + manager +
                ';' + totalPlayers +
                ';' + investmentMoney;
    }
}

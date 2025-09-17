package nba.league.demo.player;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name="player_statistics")


public class Player {

    @Id
    @Column(name="player_name",unique=true)
    private String name;
    private String nation;
    private String position;
    private Integer age;
    private Integer np;
    private Integer starts;
    private Double minutes_played;
    private Double goals;
    private Double assists;
    private Double penalties_scored;
    private Double yellow_cards;
    private Double red_cards;
    private Double expected_goals;
    private Double expected_assists;
    private String team_name;

    public Player() {
    }
    @JsonProperty
    @Transient
    private Double price;

    // Getters and setters...

    public Double getPrice() {
        // Example calculation:
        // Base price from minutes played + goals + assists + expected goals/assists
        double base = (minutes_played != null ? minutes_played / 1000 : 0); // normalize minutes
        double performance = (goals != null ? goals : 0) * 5
                + (assists != null ? assists : 0) * 3
                + (expected_goals != null ? expected_goals : 0) * 2
                + (expected_assists != null ? expected_assists : 0) * 2;
        double ageFactor = 1.0;
        if (age != null) {
            if (age < 21) ageFactor = 1.2; // young players more valuable
            else if (age > 30) ageFactor = 0.8; // older players less valuable
        }
        return Math.round((base + performance) * ageFactor * 10) / 10.0; // round to 1 decimal
    }

    public Player(String team, Double xag, Double xg, Double crdr, Double crdy, Double pk, Double ast, Double gls, Double min, Integer starts, Integer np, Integer age, String pos, String nation, String name) {
        this.team_name = team;
        this.expected_assists = xag;
        this.expected_goals = xg;
        this.red_cards = crdr;
        this.yellow_cards = crdy;
        this.penalties_scored = pk;
        this.assists = ast;
        this.goals = gls;
        this.minutes_played = min;
        this.starts = starts;
        this.np = np;
        this.age = age;
        this.position = pos;
        this.nation = nation;
        this.name = name;
    }

    public Player(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getNation() {
        return nation;
    }

    public String getPos() {
        return position;
    }

    public Integer getAge() {
        return age;
    }

    public Integer getNp() {
        return np;
    }

    public Integer getStarts() {
        return starts;
    }

    public Double getMin() {
        return minutes_played;
    }

    public Double getGls() {
        return goals;
    }

    public Double getAssists() {
        return assists;
    }

    public Double getPk() {
        return penalties_scored;
    }

    public Double getCrdy() {
        return yellow_cards;
    }

    public Double getCrdr() {
        return red_cards;
    }

    public Double getXg() {
        return expected_goals;
    }

    public Double getXag() {
        return expected_assists;
    }

    public String getTeam() {
        return team_name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public void setPos(String pos) {
        this.position = pos;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setNp(Integer np) {
        this.np = np;
    }

    public void setStarts(Integer starts) {
        this.starts = starts;
    }

    public void setMin(Double min) {
        this.minutes_played = min;
    }

    public void setGls(Double gls) {
        this.goals = gls;
    }

    public void setAssists(Double assists) {
        this.assists = assists;
    }

    public void setPk(Double pk) {
        this.penalties_scored = pk;
    }

    public void setCrdy(Double crdy) {
        this.yellow_cards = crdy;
    }

    public void setCrdr(Double crdr) {
        this.red_cards = crdr;
    }

    public void setXg(Double xg) {
        this.expected_goals = xg;
    }

    public void setXag(Double xag) {
        this.expected_assists = xag;
    }

    public void setTeam(String team) {
        this.team_name = team;
    }
}

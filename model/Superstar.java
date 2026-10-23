package model;

public class Superstar {
    private String name;
    private Division division;
    private int rating;

    public Superstar(String name, Division division, int rating){
        this.name=name;
        this.division=division;
        this.rating=rating;
    }

    public String getName(){
        return this.name;
    }

    public Division getDivision(){
        return this.division;
    }

    public int getRating(){
        return this.rating;
    }
}

package model;

public enum Division {
    MEN(20), WOMEN(12), TAG(5);

    private int quota;

    Division(int quota){
        this.quota=quota;
    }

    //Quota Setter.
    public void setQuota(int quota){
        this.quota=quota;
    }

    //Quota Getter.
    public int getQuota(){
        return this.quota;
    }
}

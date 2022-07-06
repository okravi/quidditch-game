package src.main.models;

import java.util.Arrays;
import java.util.Objects;

public class Team {

    private static final String POSITION_CHASER = "chaser";
    private static final String POSITION_SEEKER = "seeker";
    private static final String POSITION_KEEPER = "keeper";

    private String house;
    private String keeper;
    private String seeker;
    private String[] chasers;

    public Team (String house, String keeper, String seeker, String[] chasers) 
    throws IllegalArgumentException{

        if (house == null || house.isBlank() || 
        keeper == null || keeper.isBlank() || 
        seeker == null || seeker.isBlank() || 
        chasers == null || chasers.length != 3) 
        throw new IllegalArgumentException 
        ("Parameter can not be null or empty and there must be at least 3 chasers");

        if (hasNull(chasers) || hasBlank(chasers)) 
        throw new IllegalArgumentException
        ("Chasers can not be null or empty");

        this.house = house;
        this.keeper = keeper;
        this.seeker = seeker;
        this.chasers = chasers;
    }

    public Team (Team source) {
        this.house = source.house;
        this.keeper = source.keeper;
        this.seeker = source.seeker;
        this.chasers = Arrays.copyOf(source.chasers, source.chasers.length);
    }

    public String getHouse() {
        return this.house;
    }

    public String getKeeper() {
        return this.keeper;
    }

    public String getSeeker() {
        return this.seeker;
    }

    public String[] getChasers() {
        return Arrays.copyOf(this.chasers, this.chasers.length);
    }

    public void setHouse(String house) throws IllegalArgumentException{
        checkParam(house);
        this.house = house;
    }

    public void setKeeper(String keeper) throws IllegalArgumentException{
        checkParam(keeper);
        this.keeper = keeper;
    }

    public void setSeeker(String seeker) throws IllegalArgumentException{
        checkParam(seeker);
        this.seeker = seeker;
    }

    public void setChasers(String[] chasers) throws IllegalArgumentException{
        if (chasers.length !=3 || hasNull(chasers)) 
        throw new IllegalArgumentException("Chasers can not be null/empty and there mst be 3 of them");
        this.chasers = Arrays.copyOf(chasers, chasers.length);
    }

    public String toString(){
        return "House: " + this.house + "\n" +
        "Keeper: " + this.keeper + "\n" +         
        "Seeker: "  + this.seeker + "\n" +         
        "Chasers: " + Arrays.toString(this.chasers) + "\n"; 
    }

    public static Boolean hasNull(String[] ar) {

        //for (String a : ar) if (a==null) return true;
        //return false;

        return Arrays.stream(ar).anyMatch((a) -> a==null);

    }

    public static Boolean hasBlank(String[] ar) {

        return Arrays.stream(ar).anyMatch((a) -> a.isBlank());

    }

     public static String getPositionChaser() {
         return POSITION_CHASER;
     }

     public static String getPositionSeeker() {
         return POSITION_SEEKER;
     }

     public static String getPositionKeeper() {
         return POSITION_KEEPER;
     }

     public void checkParam(String param) {
        if (param == null || param.isBlank()) {
            throw new IllegalArgumentException(param + " cannot be null or blank");
        }
    }

    @Override public boolean equals(Object obj){
        if (obj == null) return false;
        if (!(obj instanceof Team)) return false;

        Team team = (Team)obj;

        return this.house.equals(team.house) &&
        this.keeper.equals(team.keeper) &&
        this.seeker.equals(team.seeker) &&
        Arrays.toString(this.chasers).equals(Arrays.toString(team.chasers));

    }

    @Override public int hashCode() {
        return Objects.hash(house, keeper, seeker, Arrays.toString(this.chasers));
    }

}

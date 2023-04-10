package fr.odddd.vachesdefrance;

public class Vache {
    private String race;

    // Image name (Without extension)
    private String photo;
    private int niveau;

    public Vache(String race, String photo, int niveau) {
        this.race= race;
        this.photo= photo;
        this.niveau= niveau;
    }

    public int getNiveau() {
        return niveau;
    }

    public String getNiveauString() {
        return  "Niveau " + niveau;
    }

    public void setPopulation(int niveau) {
        this.niveau = niveau;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

}
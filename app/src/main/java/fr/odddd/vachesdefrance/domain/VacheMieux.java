package fr.odddd.vachesdefrance.domain;

import android.graphics.drawable.Drawable;

public class VacheMieux {

    private String race;
    private Drawable photo;
    private Drawable photoCompressed;
    private String niveau;

    public VacheMieux(String race, Drawable photo, Drawable photoCompressed, String niveau) {
        this.race = race;
        this.photo = photo;
        this.photoCompressed = photoCompressed;
        this.niveau = niveau;
    }

    public String getNiveau() {
        return niveau;
    }

    public String getNiveauString() {
        return "Niveau " + niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public Drawable getPhoto() {
        return photo;
    }

    public void setPhoto(Drawable photo) {
        this.photo = photo;
    }

    public Drawable getPhotoCompressed() {
        return photoCompressed;
    }


}

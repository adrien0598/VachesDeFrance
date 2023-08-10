package fr.odddd.vachesdefrance.domain;

import android.graphics.drawable.Drawable;

public class Lvl {

    private String nb;
    private String niveau;
    private Drawable block;
    private boolean isblocked;

    public Lvl(String nb, String niveau, Drawable block, boolean isblocked) {
        this.nb = nb;
        this.niveau = niveau;
        this.block = block;
        this.isblocked = isblocked;
    }

    public Drawable getLock() {
        return block;
    }

    public Boolean getIsBlocked() {
        return isblocked;
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

    public String getNb() {
        return "Vaches : " + nb;
    }

    public void setNb(String nb) {
        this.nb = nb;
    }

}

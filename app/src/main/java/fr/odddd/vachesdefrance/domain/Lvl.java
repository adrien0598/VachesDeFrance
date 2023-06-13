package fr.odddd.vachesdefrance.domain;

import android.graphics.drawable.Drawable;

public class Lvl {

    private String nb;
    private String niveau;
    private boolean block;

    public Lvl(String nb, String niveau, boolean block) {
        this.nb= nb;
        this.niveau= niveau;
        this.block = block;
    }

    public boolean getLock(){return block;}

    public String getNiveau() {
        return niveau;
    }

    public String getNiveauString() {
        return  "Niveau " + niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public String getNb() {
        return "Vaches : " +nb;
    }

    public void setNb(String nb) {
        this.nb = nb;
    }

}

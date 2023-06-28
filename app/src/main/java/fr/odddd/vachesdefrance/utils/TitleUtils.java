package fr.odddd.vachesdefrance.utils;


import androidx.annotation.StringRes;
import androidx.appcompat.app.ActionBar;

public class TitleUtils {

    public void setTitle(
            ActionBar actionBar,
            @StringRes
            Integer idTitle,
            Boolean displayHomeAsUpEnabled
            ) {
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(displayHomeAsUpEnabled);
            actionBar.setTitle(idTitle);
        }
    }
}

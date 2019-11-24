package com.naledi.buzazi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.codemybrainsout.onboarder.AhoyOnboarderActivity;
import com.codemybrainsout.onboarder.AhoyOnboarderCard;

import java.util.ArrayList;
import java.util.List;

public class Presentation extends  AhoyOnboarderActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        SharedPreferences prefs = getApplicationContext().getSharedPreferences(getResources().getString(R.string.application_key),
                Context.MODE_PRIVATE);
        boolean installed = prefs.getBoolean(getResources().getString(R.string.install_app_id), false);
        boolean connected = prefs.getBoolean(getResources().getString(R.string.app_id), true);

        if (installed) {
            finish();
            startActivity(new Intent(getApplicationContext(), Splash.class));
        }
        AhoyOnboarderCard ahoyOnboarderCard1 = new AhoyOnboarderCard("Prévention", "Prévenir les risques sanitaires liés à la grossesse chez les femmes.", R.drawable.bun);
        AhoyOnboarderCard ahoyOnboarderCard2 = new AhoyOnboarderCard("Reduction", "Réduire la mortalité infantile.", R.drawable.btrois);
        AhoyOnboarderCard ahoyOnboarderCard3 = new AhoyOnboarderCard("Contribution", "Contribuer à prévenir le VIH/sida.", R.drawable.bdeux);

        ahoyOnboarderCard1.setBackgroundColor(R.color.black_transparent);
        ahoyOnboarderCard2.setBackgroundColor(R.color.black_transparent);
        ahoyOnboarderCard3.setBackgroundColor(R.color.black_transparent);

        List<AhoyOnboarderCard> pages = new ArrayList<>();

        pages.add(ahoyOnboarderCard1);
        pages.add(ahoyOnboarderCard2);
        pages.add(ahoyOnboarderCard3);

        for (AhoyOnboarderCard page : pages) {
            page.setTitleColor(R.color.white);
            page.setDescriptionColor(R.color.grey_200);
            //page.setTitleTextSize(dpToPixels(12, this));
            //page.setDescriptionTextSize(dpToPixels(8, this));
            //page.setIconLayoutParams(width, height, marginTop, marginLeft, marginRight, marginBottom);
        }

        setFinishButtonTitle("Lancer");
        showNavigationControls(true);
        setGradientBackground();

        //set the button style you created
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            setFinishButtonDrawableStyle(ContextCompat.getDrawable(this, R.drawable.rounded_button));
        }

        //Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
        //setFont(face);

        setOnboardPages(pages);
    }

    @Override
    public void onFinishButtonPressed() {

        //nOT ALLOW INTALLATION AFTER REACH THIS ui FOR A FIRST TIME
        SharedPreferences preferences = getApplicationContext().getSharedPreferences(getResources().getString(R.string.application_key),
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor_install = preferences.edit();
        editor_install.putBoolean(getResources().getString(R.string.install_app_id), true);
        editor_install.apply();
        //Toast.makeText(this, "Finish Pressed", Toast.LENGTH_SHORT).show();
        //MainActivity()
        Intent intent = new Intent(this, Splash.class);
        startActivity(intent);
        finish();
    }
}
package com.sn660016793.weatherapp;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Day2 extends DialogFragment implements View.OnClickListener{

    Button ok;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancesState) {
        View view = inflater.inflate(R.layout.day2,null);
        ok = (Button) view.findViewById(R.id.ok);
        ok.setOnClickListener(this);
        getDialog().setTitle(MainActivity.day2String);

        TextView temp1 = (TextView) view.findViewById(R.id.temp1);
        temp1.setText( MainActivity.convertTemperature((MainActivity.day2temps)[0]) );
        TextView temp2 = (TextView) view.findViewById(R.id.temp2);
        temp2.setText( MainActivity.convertTemperature((MainActivity.day2temps)[1]) );
        TextView temp3 = (TextView) view.findViewById(R.id.temp3);
        temp3.setText( MainActivity.convertTemperature((MainActivity.day2temps)[2]) );
        TextView temp4 = (TextView) view.findViewById(R.id.temp4);
        temp4.setText( MainActivity.convertTemperature((MainActivity.day2temps)[3]) );
        TextView temp5 = (TextView) view.findViewById(R.id.temp5);
        temp5.setText( MainActivity.convertTemperature((MainActivity.day2temps)[4]) );
        TextView temp6 = (TextView) view.findViewById(R.id.temp6);
        temp6.setText( MainActivity.convertTemperature((MainActivity.day2temps)[5]) );
        TextView temp7 = (TextView) view.findViewById(R.id.temp7);
        temp7.setText( MainActivity.convertTemperature((MainActivity.day2temps)[6]) );
        TextView temp8 = (TextView) view.findViewById(R.id.temp8);
        temp8.setText( MainActivity.convertTemperature((MainActivity.day2temps)[7]) );

        ImageView img1 = (ImageView) view.findViewById(R.id.img1);
        changeImg(img1, MainActivity.day2states[0], MainActivity.day2states2[0]);
        ImageView img2 = (ImageView) view.findViewById(R.id.img2);
        changeImg(img2, MainActivity.day2states[1], MainActivity.day2states2[1]);
        ImageView img3 = (ImageView) view.findViewById(R.id.img3);
        changeImg(img3, MainActivity.day2states[2], MainActivity.day2states2[2]);
        ImageView img4 = (ImageView) view.findViewById(R.id.img4);
        changeImg(img4, MainActivity.day2states[3], MainActivity.day2states2[3]);
        ImageView img5 = (ImageView) view.findViewById(R.id.img5);
        changeImg(img5, MainActivity.day2states[4], MainActivity.day2states2[4]);
        ImageView img6 = (ImageView) view.findViewById(R.id.img6);
        changeImg(img6, MainActivity.day2states[5], MainActivity.day2states2[5]);
        ImageView img7 = (ImageView) view.findViewById(R.id.img7);
        changeImg(img7, MainActivity.day2states[6], MainActivity.day2states2[6]);
        ImageView img8 = (ImageView) view.findViewById(R.id.img8);
        changeImg(img8, MainActivity.day2states[7], MainActivity.day2states2[7]);

        return view;
    }


    public void onClick(View view) {
        dismiss();
    }

    public void changeImg(ImageView img, String state, String state2){
        switch (state) {
            case "Rain":
            case "Drizzle":
                img.setImageResource(R.drawable.rain);
                break;
            case "Clear":
                img.setImageResource(R.drawable.sun);
                break;
            case "Clouds":
                if (state2.equals("few clouds")) {
                    img.setImageResource(R.drawable.cloudy);
                } else {
                    img.setImageResource(R.drawable.few_cloud);
                }
                break;
            case "Snow":
                img.setImageResource(R.drawable.snow);
                break;
            case "Thunderstorm":
                img.setImageResource(R.drawable.thunder);
                break;
            default:
                img.setImageResource(R.drawable.few_cloud);
                break;
        }
    }
}

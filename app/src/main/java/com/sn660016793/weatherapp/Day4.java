package com.sn660016793.weatherapp;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.security.spec.ECField;
import java.util.concurrent.ExecutorCompletionService;

public class Day4 extends DialogFragment implements View.OnClickListener{

    Button ok;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancesState) {
        View view = inflater.inflate(R.layout.day4,null);
        ok = (Button) view.findViewById(R.id.ok);
        ok.setOnClickListener(this);
        getDialog().setTitle(MainActivity.day4String);

        TextView temp1 = (TextView) view.findViewById(R.id.temp1);
        temp1.setText( MainActivity.convertTemperature((MainActivity.day4temps)[0]) );
        TextView temp2 = (TextView) view.findViewById(R.id.temp2);
        temp2.setText( MainActivity.convertTemperature((MainActivity.day4temps)[1]) );
        TextView temp3 = (TextView) view.findViewById(R.id.temp3);
        temp3.setText( MainActivity.convertTemperature((MainActivity.day4temps)[2]) );
        TextView temp4 = (TextView) view.findViewById(R.id.temp4);
        temp4.setText( MainActivity.convertTemperature((MainActivity.day4temps)[3]) );
        TextView temp5 = (TextView) view.findViewById(R.id.temp5);
        temp5.setText( MainActivity.convertTemperature((MainActivity.day4temps)[4]) );
        TextView temp6 = (TextView) view.findViewById(R.id.temp6);
        temp6.setText( MainActivity.convertTemperature((MainActivity.day4temps)[5]) );
        TextView temp7 = (TextView) view.findViewById(R.id.temp7);
        temp7.setText( MainActivity.convertTemperature((MainActivity.day4temps)[6]) );
        TextView temp8 = (TextView) view.findViewById(R.id.temp8);
        temp8.setText( MainActivity.convertTemperature((MainActivity.day4temps)[7]) );

        ImageView img1 = (ImageView) view.findViewById(R.id.img1);
        changeImg(img1, MainActivity.day4states[0], MainActivity.day4states2[0]);
        ImageView img2 = (ImageView) view.findViewById(R.id.img2);
        changeImg(img2, MainActivity.day4states[1], MainActivity.day4states2[1]);
        ImageView img3 = (ImageView) view.findViewById(R.id.img3);
        changeImg(img3, MainActivity.day4states[2], MainActivity.day4states2[2]);
        ImageView img4 = (ImageView) view.findViewById(R.id.img4);
        changeImg(img4, MainActivity.day4states[3], MainActivity.day4states2[3]);
        ImageView img5 = (ImageView) view.findViewById(R.id.img5);
        changeImg(img5, MainActivity.day4states[4], MainActivity.day4states2[4]);
        ImageView img6 = (ImageView) view.findViewById(R.id.img6);
        changeImg(img6, MainActivity.day4states[5], MainActivity.day4states2[5]);
        ImageView img7 = (ImageView) view.findViewById(R.id.img7);
        changeImg(img7, MainActivity.day4states[6], MainActivity.day4states2[6]);
        ImageView img8 = (ImageView) view.findViewById(R.id.img8);
        changeImg(img8, MainActivity.day4states[7], MainActivity.day4states2[7]);

        return view;
    }


    public void onClick(View view) {
        dismiss();
    }

    public void changeImg(ImageView img, String state, String state2){
        try{
        if (state.equals("Rain") || state.equals("Drizzle")) {
            img.setImageResource(R.drawable.rain);
        }else if (state.equals("Clear")){
            img.setImageResource(R.drawable.sun);
        }else if (state.equals("Clouds")){
            if(state2.equals("few clouds")){
                img.setImageResource(R.drawable.cloudy);
            } else {
                img.setImageResource(R.drawable.few_cloud);
            }
        }else if (state.equals("Snow")){
            img.setImageResource(R.drawable.snow);
        }else if(state.equals("Thunderstorm")){
            img.setImageResource(R.drawable.thunder);
        } else {
            System.out.println("ELSE");
            img.setImageResource(R.drawable.few_cloud);
        }
    } catch (Exception e) {
            e.printStackTrace();
        }}
}

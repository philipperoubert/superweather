package com.sn660016793.weatherapp;
import android.app.Activity;
import android.app.FragmentManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

// Weather icons by Good Ware

public class MainActivity extends Activity implements Location.Communicator {

    static String locationString;
    static String temperatureString;
    static String windSpeedString;
    static String humidityString;
    static String stateString;
    static String descriptionString;
    static String lastUpdateString;

    static double[] day1temps = new double[8];
    static double[] day2temps = new double[8];
    static double[] day3temps = new double[8];
    static double[] day4temps = new double[8];
    static String[] day1states = new String[8];
    static String[] day2states = new String[8];
    static String[] day3states = new String[8];
    static String[] day4states = new String[8];
    static String[] day1states2 = new String[8];
    static String[] day2states2 = new String[8];
    static String[] day3states2 = new String[8];
    static String[] day4states2 = new String[8];

    static String day1String;
    static String day2String;
    static String day3String;
    static String day4String;

    SQLiteDatabase db;


    /**
     * Default Android onCreate studios that happense when the app is run for the first time
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        new DownloadFilesTask().execute("http://api.openweathermap.org/data/2.5/forecast?q=Exeter,gb&mode=kspm&units=metric&appid=509e2c6a7c0739c1d9875b28044f9203");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         openDatabase();
    }

    /**
     * Opens/Creates the database to store offline information
     */
    private void openDatabase() {
        try {
            db = SQLiteDatabase.openDatabase("data/data/com.sn660016793.weatherapp/weatherDB", null, SQLiteDatabase.CREATE_IF_NECESSARY);
            try{
                db.execSQL("drop table weatherData");
                System.out.println(db.rawQuery("SELECT * FROM weatherData",null));

            } catch (SQLiteException e){
                db.execSQL("create table weatherData ("
                        + " ID integer PRIMARY KEY autoincrement, "
                        + " location text, "
                        + " temperature text, "
                        + " windspeed text, "
                        + " humidity text,"
                        + " state text,"
                        + " description text,"
                        + " lastUpdate text,"
                        + " day1String text,"
                        + " day2String text,"
                        + " day3String text,"
                        + " day4String text,"
                        + " day1temps text,"
                        + " day2temps text,"
                        + " day3temps text,"
                        + " day4temps text,"
                        + " day1states text,"
                        + " day2states text,"
                        + " day3states text,"
                        + " day4states text,"
                        + " day1states2 text,"
                        + " day2states2 text,"
                        + " day3states2 text,"
                        + " day4states2 text); ");
                System.out.println("Made Table");
            }
            System.out.println("Database opened");
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds/Updates the current data state to the database
     */
    public void addDataDB(){
        db = SQLiteDatabase.openDatabase("data/data/com.sn660016793.weatherapp/weatherDB", null, SQLiteDatabase.CREATE_IF_NECESSARY);
        try {
            if (db.rawQuery("select * from weatherData where location = '"+locationString+"';", null).getCount() == 0) {
                db.execSQL("insert into weatherData (location, temperature, windspeed, humidity, state, description, lastUpdate, day1String, day2String, day3String, day4String,"
                +"day1temps, day2temps, day3temps, day4temps, day1states, day2states, day3states, day4states, day1states2, day2states2, day3states2,day4states2) values ('"
                +locationString+"','"
                +temperatureString+"','"
                +windSpeedString +"','"
                +humidityString+"','"
                +stateString+"','"
                +descriptionString+"','"
                +lastUpdateString+"','"
                +day1String+"','"
                +day2String+"','"
                +day3String+"','"
                +day4String+"','"
                +Arrays.toString(day1temps)+"','"
                +Arrays.toString(day2temps)+"','"
                +Arrays.toString(day3temps)+"','"
                +Arrays.toString(day4temps)+"','"
                +Arrays.toString(day1states)+"','"
                +Arrays.toString(day2states)+"','"
                +Arrays.toString(day3states)+"','"
                +Arrays.toString(day4states)+"','"
                +Arrays.toString(day1states2)+"','"
                +Arrays.toString(day2states2)+"','"
                +Arrays.toString(day3states2)+"','"
                +Arrays.toString(day4states2) +"');"); }
            else {

                    db.execSQL("update weatherData set "
                            +"location = '"+locationString+"',"
                            +"temperature = '"+temperatureString+"',"
                            +"windspeed = '"+windSpeedString +"',"
                            +"humidity = '"+humidityString+"',"
                            +"state = '"+stateString+"',"
                            +"description = '"+descriptionString+"',"
                            +"lastUpdate = '"+lastUpdateString+"',"
                            +"day1String= '"+day1String+"',"
                            +"day2String = '"+day2String+"',"
                            +"day3String = '"+day3String+"',"
                            +"day4String = '"+day4String+"',"
                            +"day1temps = '"+Arrays.toString(day1temps)+"',"
                            +"day2temps = '"+Arrays.toString(day2temps)+"',"
                            +"day3temps = '"+Arrays.toString(day3temps)+"',"
                            +"day4temps = '"+Arrays.toString(day4temps)+"',"
                            +"day1states = '"+Arrays.toString(day1states)+"',"
                            +"day2states = '"+Arrays.toString(day2states)+"',"
                            +"day3states = '"+Arrays.toString(day3states)+"',"
                            +"day4states = '"+Arrays.toString(day4states)+"',"
                            +"day1states2 = '"+Arrays.toString(day1states2)+"',"
                            +"day2states2 = '"+Arrays.toString(day2states2)+"',"
                            +"day3states2 = '"+Arrays.toString(day3states2)+"',"
                            +"day4states2 = '"+Arrays.toString(day4states2) +"' where location ='"+locationString+"';");
            }

        } catch (SQLiteException e) {
            e.printStackTrace();
        }

    }

    /**
     * Makes an average from 6am to 6pm of the day temperatures and returns it
     */
    public double makeAvg(double[] doubles){
        double average = 0;
        for (int i=2; i<6; i++){
            average += doubles[i];
        }
        average /= 4;
        return average;
    }

    /**
     * Updates all the views with the current data state
     */
    public void updateValues(){
        TextView location = (TextView) findViewById(R.id.location);
        TextView temperature = (TextView) findViewById(R.id.temperature);
        TextView state = (TextView) findViewById(R.id.state);
        TextView windspeed = (TextView) findViewById(R.id.windspeed);
        TextView humidity = (TextView) findViewById(R.id.humidity);
        TextView lastUpdate = (TextView) findViewById(R.id.lastUpdate);
        TextView day1 = (TextView) findViewById(R.id.day1);
        TextView day2 = (TextView) findViewById(R.id.day2);
        TextView day3 = (TextView) findViewById(R.id.day3);
        TextView day4 = (TextView) findViewById(R.id.day4);
        TextView temp1 = (TextView) findViewById(R.id.temp1);
        TextView temp2 = (TextView) findViewById(R.id.temp2);
        TextView temp3 = (TextView) findViewById(R.id.temp3);
        TextView temp4 = (TextView) findViewById(R.id.temp4);
        ImageView img= (ImageView) findViewById(R.id.stateImg);
        ImageView img1= (ImageView) findViewById(R.id.day1temp);
        ImageView img2= (ImageView) findViewById(R.id.day2temp);
        ImageView img3= (ImageView) findViewById(R.id.day3temp);
        ImageView img4= (ImageView) findViewById(R.id.day4temp);

        location.setText(locationString);
        temperature.setText(temperatureString);
        state.setText(stateString);
        windspeed.setText(windSpeedString);
        humidity.setText(humidityString);
        lastUpdate.setText(lastUpdateString);
        day1.setText(day1String);
        day2.setText(day2String);
        day3.setText(day3String);
        day4.setText(day4String);
        temp1.setText(convertTemperature( makeAvg(day1temps) ));
        temp2.setText(convertTemperature( makeAvg(day2temps) ));
        temp3.setText(convertTemperature( makeAvg(day3temps) ));
        temp4.setText(convertTemperature( makeAvg(day4temps) ));
        changeImg(img, stateString, descriptionString);
        changeImg(img1, day1states[4], day1states2[4]);
        changeImg(img2, day2states[4], day2states2[4]);
        changeImg(img3, day3states[4], day3states2[4]);
        changeImg(img4, day4states[4], day4states2[4]);
    }


    /**
     * Reads the contents of the database and updates the current variable values
     */
    public void readDataDB(){
        db = SQLiteDatabase.openDatabase("data/data/com.sn660016793.weatherapp/weatherDB", null, SQLiteDatabase.CREATE_IF_NECESSARY);
        Cursor c = db.rawQuery("select * from weatherData where location = '"+locationString+"';", null);
        if (c.getCount() != 0) {
            c.moveToFirst();
            temperatureString = c.getString(2);
            windSpeedString = c.getString(3);
            humidityString = c.getString(4);
            stateString = c.getString(5);
            descriptionString = c.getString(6);
            lastUpdateString = c.getString(7);
            day1String = c.getString(8);
            day2String = c.getString(9);
            day3String = c.getString(10);
            day4String = c.getString(11);
            String[] convertingTransition = c.getString(12).replaceAll("\\[", "").replaceAll("\\]","").split(",");
            for (int i = 0; i < day1temps.length; i++) {
                day1temps[i] = Double.parseDouble(convertingTransition[i]);
            }
            convertingTransition = c.getString(13).replaceAll("\\[", "").replaceAll("\\]","").split(",");
            for (int i = 0; i < day1temps.length; i++) {
                day2temps[i] = Double.parseDouble(convertingTransition[i]);
            }
            convertingTransition = c.getString(14).replaceAll("\\[", "").replaceAll("\\]","").split(",");
            for (int i = 0; i < day1temps.length; i++) {
                day3temps[i] = Double.parseDouble(convertingTransition[i]);
            }
            convertingTransition = c.getString(15).replaceAll("\\[", "").replaceAll("\\]","").split(",");
            for (int i = 0; i < day1temps.length; i++) {
                day4temps[i] = Double.parseDouble(convertingTransition[i]);
            }
            day1states = c.getString(16).replaceAll("\\[","").replaceAll("\\]","").replaceAll(" ","").split(",");
            day2states = c.getString(17).replaceAll("\\[","").replaceAll("\\]","").replaceAll(" ","").split(",");
            day3states = c.getString(18).replaceAll("\\[","").replaceAll("\\]","").replaceAll(" ","").split(",");
            day4states = c.getString(19).replaceAll("\\[","").replaceAll("\\]","").replaceAll(" ","").split(",");

            day1states2 = c.getString(20).replaceAll("\\[","").replaceAll("\\]","").replaceAll(" ","").split(",");
            day2states2 = c.getString(21).replaceAll("\\[","").replaceAll("\\]","").replaceAll(" ","").split(",");
            day3states2 = c.getString(22).replaceAll("\\[","").replaceAll("\\]","").replaceAll(" ","").split(",");
            day4states2 = c.getString(23).replaceAll("\\[","").replaceAll("\\]","").replaceAll(" ","").split(",");

        }


    }


    /**
     * Converts a double to a String that represents the temperature
     */
    public static String convertTemperature(double i){

        String temperature = String.format("%.1f",i);
        temperature = temperature + "°C";
        return temperature;
    }

    /**
     * Shows the dialog that allows changing the location
     */
    public void showDialog(View v){
        FragmentManager manager = getFragmentManager();
        Location location = new Location();
        location.show(manager, "Location");
    }

    /**
     * Shows the dialog that shows the forecast for that day
     */
    public void showDay1(View v){
        FragmentManager manager = getFragmentManager();
        Day1 day1 = new Day1();
        day1.show(manager, "Day1");
    }

    /**
     * Shows the dialog that shows the forecast for that day
     */
    public void showDay2(View v){
        FragmentManager manager = getFragmentManager();
        Day2 day2 = new Day2();
        day2.show(manager, "Day2");
    }

    /**
     * Shows the dialog that shows the forecast for that day
     */
    public void showDay3(View v){
        FragmentManager manager = getFragmentManager();
        Day3 day3 = new Day3();
        day3.show(manager, "Day3");
    }

    /**
     * Shows the dialog that shows the forecast for that day
     */
    public void showDay4(View v) {
        FragmentManager manager = getFragmentManager();
        Day4 day4 = new Day4();
        day4.show(manager, "Day4");
    }

    /**
     * Reads the content of an url
     */
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    /**
     * Retrieves the content of an url
     */
    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    /**
     * Updates the new location
     */
    @Override
    public void onDialogMessage(String message) {
        locationString = message;
        try {
            new DownloadFilesTask().execute("http://api.openweathermap.org/data/2.5/forecast?q=" + message + "&mode=kspm&units=metric&appid=509e2c6a7c0739c1d9875b28044f9203");
        } catch (Exception e){
            Toast.makeText(getApplicationContext(), "Couldn't find city " + message, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Changes the image to the appropriate image
     */
    public void changeImg(ImageView img, String state, String state2){
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Downloads all the necessary data from the API using AsyncTask to allow multitasking
     */
    class DownloadFilesTask extends AsyncTask<String, Integer, JSONObject>{


        protected JSONObject doInBackground(String... strings){

            JSONObject json = null;

            try {
                json = readJsonFromUrl(strings[0]);
            } catch (IOException e) {

                runOnUiThread(new Runnable() {
                    public void run() {
                        final Toast toast = Toast.makeText(getApplicationContext(), "Service unavailable or\ncan't find city...", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });

                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return json;
        }


        /**
         * Converts an int representing the day of week to it's week name string
         */
        private String convertDayWeekName(int i){
            switch(i){
                case 1: return "Monday";
                case 2: return "Tuesday";
                case 3: return "Wednesday";
                case 4: return "Thursday";
                case 5: return "Friday";
                case 6: return "Saturday";
                case 0: return "Sunday";
                default: return "Monday";
            }
        }


        /**
         * Once the download has finished
         */
        protected void onPostExecute(JSONObject result) {
            TextView location = (TextView) findViewById(R.id.location);
            TextView temperature = (TextView) findViewById(R.id.temperature);
            TextView state = (TextView) findViewById(R.id.state);
            ImageView img= (ImageView) findViewById(R.id.stateImg);
            TextView windspeed = (TextView) findViewById(R.id.windspeed);
            TextView humidity = (TextView) findViewById(R.id.humidity);
            TextView lastUpdate = (TextView) findViewById(R.id.lastUpdate);
            TextView day1 = (TextView) findViewById(R.id.day1);
            TextView day2 = (TextView) findViewById(R.id.day2);
            TextView day3 = (TextView) findViewById(R.id.day3);
            TextView day4 = (TextView) findViewById(R.id.day4);
            TextView temp1 = (TextView) findViewById(R.id.temp1);
            TextView temp2 = (TextView) findViewById(R.id.temp2);
            TextView temp3 = (TextView) findViewById(R.id.temp3);
            TextView temp4 = (TextView) findViewById(R.id.temp4);
            ImageView img1= (ImageView) findViewById(R.id.day1temp);
            ImageView img2= (ImageView) findViewById(R.id.day2temp);
            ImageView img3= (ImageView) findViewById(R.id.day3temp);
            ImageView img4= (ImageView) findViewById(R.id.day4temp);
            try {
                JSONObject main = (JSONObject) result.get("city");
                String cityName = main.get("name").toString();
                location.setText(cityName);
                locationString = cityName;
                JSONArray list = (JSONArray) result.get("list");
                JSONObject dt = (JSONObject) list.get(0);

                JSONObject weather = (JSONObject) ((JSONArray) dt.get("weather")).get(0);
                descriptionString = weather.get("description").toString();
                state.setText(descriptionString);
                stateString = weather.get("main").toString();
                String weatherState;
                String weatherStateDetail;

                changeImg(img, stateString, descriptionString);


                windSpeedString = "Wind speed: " + ((JSONObject) dt.get("wind")).get("speed").toString() + "mph";
                windspeed.setText(windSpeedString);
                humidityString = "Humidity: " + ((JSONObject) dt.get("main")).get("humidity").toString() + "%";
                humidity.setText(humidityString);

                dt = (JSONObject) dt.get("main");

                double tempNumber = (double) dt.get("temp");
                temperatureString = convertTemperature(tempNumber);
                temperature.setText(temperatureString);

                lastUpdateString = "Last updated: " + new SimpleDateFormat("dd/MM/yyyy - kk:mm").format(Calendar.getInstance().getTime());
                lastUpdate.setText(lastUpdateString);

                int weekDayNumber = Integer.parseInt(new SimpleDateFormat("u").format(Calendar.getInstance().getTime()));
                day1String = convertDayWeekName((weekDayNumber + 1) % 7);
                day1.setText(day1String);
                day2String = convertDayWeekName((weekDayNumber + 2) % 7);
                day2.setText(day2String);
                day3String = convertDayWeekName((weekDayNumber + 3) % 7);
                day3.setText(day3String);
                day4String = convertDayWeekName((weekDayNumber + 4) % 7);
                day4.setText(day4String);

                int iteration = 0;
                String[] date;
                String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());

                while (true) {
                    dt = (JSONObject) list.get(iteration);
                    date = (dt.get("dt_txt")).toString().split(" ");
                    if (!(date[0].equals(currentDate)) && date[1].equals("00:00:00")) {
                        iteration++;
                        break;
                    }
                    iteration++;
                }


                double forecastTemp;
                double average = 0;

                for (int i = 0; i < 4; i++){
                    for (int j = 0; j < 8; j++) {
                        iteration++;
                        dt = (JSONObject) list.get(iteration);
                        dt = (JSONObject) dt.get("main");

                        forecastTemp = Double.parseDouble(dt.get("temp").toString());

                        dt = (JSONObject) list.get(iteration);
                        weather = (JSONObject) ((JSONArray) dt.get("weather")).get(0);
                        weatherState = weather.get("main").toString();
                        weatherStateDetail = weather.get("description").toString();

                        switch(i){
                            case 0: day1temps[j] = forecastTemp; day1states[j] = weatherState; day1states2[j] = weatherStateDetail; break;
                            case 1: day2temps[j] = forecastTemp; day2states[j] = weatherState; day2states2[j] = weatherStateDetail; break;
                            case 2: day3temps[j] = forecastTemp; day3states[j] = weatherState; day3states2[j] = weatherStateDetail; break;
                            case 3: day4temps[j] = forecastTemp; day4states[j] = weatherState; day4states2[j] = weatherStateDetail; break;
                            default: break;
                        }
                        switch (j){
                            case 2: average += forecastTemp; break;
                            case 3: average += forecastTemp; break;
                            case 4: average += forecastTemp; break;
                            case 5: average += forecastTemp; break;
                            default: break;
                        }
                        if (j == 4) {
                            dt = (JSONObject) list.get(iteration);
                            weather = (JSONObject) ((JSONArray) dt.get("weather")).get(0);

                            switch (i){

                                case 0: changeImg(img1, weather.get("main").toString(), weather.get("description").toString() );  break;
                                case 1: changeImg(img2, weather.get("main").toString(), weather.get("description").toString() );  break;
                                case 2: changeImg(img3, weather.get("main").toString(), weather.get("description").toString() );  break;
                                case 3: changeImg(img4, weather.get("main").toString(), weather.get("description").toString() );  break;
                                default: break;
                            }
                        }
                    }
                    average /= 4;

                    switch(i){
                        case 0: temp1.setText(convertTemperature(average)); break;
                        case 1: temp2.setText(convertTemperature(average)); break;
                        case 2: temp3.setText(convertTemperature(average)); break;
                        case 3: temp4.setText(convertTemperature(average)); break;
                        default: break;
                    }
                    average = 0;
                }

                addDataDB();

            } catch (Exception e) {
                e.printStackTrace();
                readDataDB();
                updateValues();
                if (locationString ==null){
                    Toast.makeText(getApplicationContext(), "Couldn't load data for the first time\nNo internet connection", Toast.LENGTH_SHORT).show();
                }
            }
        }

    }





}

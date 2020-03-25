package nl.hsleiden.service;

import com.google.gson.Gson;
import nl.hsleiden.service.model.HighScore;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * In firebase worden alle highscores en archivements bewaard.
 * Dit is de class die deze zaken afhandeld.
 * */
public class FirebaseService {
    /**
     * Deze methode zorgd ervoor dat een highscore bewaard word.
     * @param groupName naam van jullie groep.
     * @param groupToken de token die jullie hebben gekregen.
     * @param score de score die behaald is.
     * @param playerName de naam van de speler die de score gehaald heeft.
     * */
    private String firebaseString = "https://iposechallenge.firebaseio.com";

    public void saveHighScore(String groupName,String groupToken,int score,String playerName) throws Exception {
        Date date = new Date();
        String jsonString = "{ \"playerName\" : \""+playerName+"\" , \"score\" :  "+score+" , \"date\" : \""+date.toString()+"\" }";

        URL urlObject = new URL(firebaseString + "/highscore/" + groupName + ".json?auth="+groupToken);
        HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();

        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type","application/json");

        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(jsonString.getBytes());

        InputStream inputStream = connection.getInputStream();
    }

    /**
     * Een methode om de highscores op te halen.
     * @param groupName de naam van jullie groepje.
     * */
    public HighScore getHighScores(String groupName) throws Exception {
        URL urlObject = new URL(firebaseString + "/highscore/" + groupName + ".json");
        HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();

        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type","application/json");

        InputStream inputStream = connection.getInputStream();
        String inputStreamData = new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.joining());

        String cleanedJson = inputStreamData.substring(inputStreamData.lastIndexOf(":{") + 1, inputStreamData.length()-1);

        Gson gson = new Gson();

        return gson.fromJson(cleanedJson, HighScore.class);
    }

    /**
     * Een methode om een archievement op te slaan.
     * @param groupName naam van jullie groep.
     * @param groupToken de token die jullie hebben gekregen.
     * @param achievementName de archievement die behaald is.
     * */
    public void saveAchievement(String groupName,String groupToken,String achievementName) throws Exception{
        Date date = new Date();
        String jsonString = "{ \"achievementName\" : \""+achievementName+"\" , \"date\" : \""+date.toString()+"\" }";

        URL urlObject = new URL(firebaseString + "/achievements/" + groupName + ".json?auth=" + groupToken);
        HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();

        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type","application/json");

        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(jsonString.getBytes());

        InputStream inputStream = connection.getInputStream();
    }
}

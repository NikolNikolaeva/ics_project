package com.vmwareTalantBoost.ics.Image;

import org.hibernate.annotations.Comment;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;
import org.json.simple.JSONArray;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    public String ImagaTagsList(String url_input){
        try {
            String credentialsToEncode = "acc_f74f5358f104bd8" + ":" + "05d4bb93f20be312c863578502533812";
            String basicAuth = Base64.getEncoder().encodeToString(credentialsToEncode.getBytes(StandardCharsets.UTF_8));

            String endpoint_url = "https://api.imagga.com/v2/tags";
           // String image_url = "https://imagga.com/static/images/tagging/wind-farm-538576_640.jpg";
            String image_url=url_input;

            String url = endpoint_url + "?image_url=" + image_url;
            URL urlObject = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();

            connection.setRequestProperty("Authorization", "Basic " + basicAuth);
            //System.out.println(basicAuth);

            int responseCode = connection.getResponseCode();

            //System.out.println("\nSending 'GET' request to URL : " + url);
            //System.out.println("Response Code : " + responseCode);

            BufferedReader connectionInput = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String jsonResponse = connectionInput.readLine();
            connectionInput.close();

            List<Tag> tags=new ArrayList<>();

            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(jsonResponse);
            return json.get("result").toString();

//            JSONObject jsonObj =  (JSONObject) json.get("result");
//            JSONArray jsonArrObj =  (JSONArray) json.get("tags");
//
//            Iterator<JSONObject> iteratorObj = jsonArrObj.iterator();
//            while (iteratorObj.hasNext()) {
//                String name=iteratorObj.next().get("en").toString();
//                int confidence=(int)iteratorObj.next().get("confidence");
//                tags.add(new Tag(name,confidence));
//            }

//            System.out.println(jsonResponse);
           // return tags;

        }
        catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}

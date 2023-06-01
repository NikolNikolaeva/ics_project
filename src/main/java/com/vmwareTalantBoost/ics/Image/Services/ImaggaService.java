package com.vmwareTalantBoost.ics.Image.Services;

import com.vmwareTalantBoost.ics.Image.Classes.Tag;
import com.vmwareTalantBoost.ics.Image.Repositories.TagRepository;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class ImaggaService {

    private TagService tagService;
    private TagRepository tagRepository;

    public ImaggaService(TagService tagService, TagRepository tagRepository) {
        this.tagService = tagService;
        this.tagRepository = tagRepository;
    }

    public  List<Tag>  getTagsFromImage(String imageURL) {

        List<Tag> tagsImage;

        try {
            String credentialsToEncode = "acc_f74f5358f104bd8" + ":" + "9078b5faa09f51813badb28a0666a75b";
            String basicAuth = Base64.getEncoder().encodeToString(credentialsToEncode.getBytes(StandardCharsets.UTF_8));

            String endpoint_url = "https://api.imagga.com/v2/tags";
            String image_url = "https://imagga.com/static/images/tagging/wind-farm-538576_640.jpg";

            String url = endpoint_url + "?image_url=" + image_url;
            URL urlObject = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();

            connection.setRequestProperty("Authorization", "Basic " + basicAuth);

            int responseCode = connection.getResponseCode();

            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);

            BufferedReader connectionInput = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String jsonResponse = connectionInput.readLine();

            connectionInput.close();

            tagsImage = tagService.createTagListFromJSON(jsonResponse);
            return tagsImage;

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

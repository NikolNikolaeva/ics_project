package com.vmwareTalantBoost.ics.Image.Services;

import com.vmwareTalantBoost.ics.Image.Classes.Image;
import com.vmwareTalantBoost.ics.Image.Classes.Tag;
import com.vmwareTalantBoost.ics.Image.Repositories.ImageRepository;
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
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    final private ImageRepository imageRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public List<Image> getImages() {
            return imageRepository.findAll();
        }

    public void addNewImage(Image image) {

        Optional<Image> imagesByUrl = imageRepository.findImagesByUrl(image.getUrl());
        if(imagesByUrl.isPresent()){
            throw new IllegalStateException("url already exist in data");
        }
        imageRepository.save(image);
    }

    public void deleteImage(Long imageId) {
        boolean exists=imageRepository.existsById(imageId);
        if(!exists)
        {
            throw new IllegalStateException(
                    "Image with id "+imageId+" does not exists"
            );
        }
        imageRepository.deleteById(imageId);
    }
}

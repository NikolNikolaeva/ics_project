package ics.Image.Services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ics.Image.Classes.Tag;
import ics.Image.Repositories.TagRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagService {

    private TagRepository tagRepository;

    public List<Tag> createTagListFromJSON(String jsonString) {

        List<Tag>  tags = new ArrayList<Tag>();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonString);

            JsonNode tagsNode = jsonNode.get("result").get("tags");
            if (tagsNode.isArray()) {
                for (JsonNode tagNode : tagsNode) {
                    JsonNode tag = tagNode.get("tag");

                    Tag tagImage = new Tag();
                    String tagName = tag.get("en").asText();
                    int tagConfidence = tagNode.get("confidence").asInt();
                    if (tagConfidence > 30) {
                        tagImage.setName(tagName);
                        tagImage.setConfidence(tagConfidence);
                        tags.add(tagImage);
                    }
                }
            }

            return tags;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return tags;
    }

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public List<Tag> getTagsWithStr(String str){
        return tagRepository.findTagByStr(str);
    }

    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }
}

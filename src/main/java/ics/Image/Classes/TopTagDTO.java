package ics.Image.Classes;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TopTagDTO {
    private String name;
    private Long count;

    public TopTagDTO(String name, Long count) {
        this.name = name;
        this.count = count;
    }
}
package ics.Image.Classes;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TopActiveUserDTO {
    private String name;
    private Long count;

    public TopActiveUserDTO(String name, Long count) {
        this.name = name;
        this.count = count;
    }
}
package com.alexstark.lombok;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Books {

    private int pages;
    private String isbn;
    private String title;
    private String author;
    @JsonProperty("publish_date")
    private String publishDate;
}

package com.upspoon.common.web;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

/**
 * @author burak.yesildal
 */

public class CustomPage<T> extends PageImpl<T> implements Serializable {

    private static final long serialVersionUID = 385021193918602621L;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public CustomPage(@JsonProperty("content") List<T> content, @JsonProperty("number") int number, @JsonProperty("size") int size,
                      @JsonProperty("totalElements") Long totalElements, @JsonProperty("pageable") JsonNode pageable, @JsonProperty("last") boolean last,
                      @JsonProperty("totalPages") int totalPages, @JsonProperty("sort") JsonNode sort, @JsonProperty("first") boolean first,
                      @JsonProperty("numberOfElements") int numberOfElements) {
        super(content, PageRequest.of(number, size), totalElements);
    }

    public CustomPage(List<T> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }


    public CustomPage(List<T> content) {
        super(content);
    }


}

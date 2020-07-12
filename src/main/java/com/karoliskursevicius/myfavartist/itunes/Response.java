package com.karoliskursevicius.myfavartist.itunes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * @author Karolis Kursevičius
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response {
    private int resultCount;
    private List<Result> results;
}

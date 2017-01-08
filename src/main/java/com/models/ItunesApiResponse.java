package com.models;

/**
 * Created by Hari Rao on 07/01/17.
 */
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "resultCount",
        "results"
})
public class ItunesApiResponse {

    @JsonProperty("resultCount")
    private Integer resultCount;
    @JsonProperty("results")
    private List<Itunes> results = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("resultCount")
    public Integer getResultCount() {
        return resultCount;
    }

    @JsonProperty("resultCount")
    public void setResultCount(Integer resultCount) {
        this.resultCount = resultCount;
    }

    @JsonProperty("results")
    public List<Itunes> getResults() {
        return results;
    }

    @JsonProperty("results")
    public void setResults(List<Itunes> results) {
        this.results = results;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}

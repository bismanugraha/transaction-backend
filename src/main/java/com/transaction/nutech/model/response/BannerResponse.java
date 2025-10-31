package com.transaction.nutech.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BannerResponse {
    @JsonProperty("banner_name")
    private String bannerName;
    @JsonProperty("banner_image")
    private String bannerImage;
    private String description;
}

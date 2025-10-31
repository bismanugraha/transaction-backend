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
public class ServicesResponse {
    @JsonProperty("service_code")
    private String serviceCode;
    @JsonProperty("service_name")
    private String serviceName;
    @JsonProperty("service_icon")
    private String serviceIcon;
    @JsonProperty("service_tariff")
    private Long serviceTariff;
}

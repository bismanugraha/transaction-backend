package com.transaction.nutech.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.transaction.nutech.enums.ServiceCode;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@NoArgsConstructor
public class TransactionRequest {
    @JsonProperty("service_code")
    @NotNull(message = "Top up balance tidak boleh kosong")
    private ServiceCode serviceCode;
}

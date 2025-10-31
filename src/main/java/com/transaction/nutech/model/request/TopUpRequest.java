package com.transaction.nutech.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@NoArgsConstructor
public class TopUpRequest {
    @JsonProperty("top_up_balance")
    @NotNull(message = "Top up balance tidak boleh kosong")
    @Min(value = 1, message = "Paramter amount hanya boleh angka dan tidak boleh lebih kecil dari 1")
    private Long topUpBalance;
}

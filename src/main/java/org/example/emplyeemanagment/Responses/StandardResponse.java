package org.example.emplyeemanagment.Responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonPropertyOrder({"messageCode", "ResponseMessage", "data"})
public class StandardResponse {
    @JsonProperty("messageCode")
    private String code;
    @JsonProperty("ResponseMessage")
    private String status;
    @JsonProperty("data")
    private Object data ;
}

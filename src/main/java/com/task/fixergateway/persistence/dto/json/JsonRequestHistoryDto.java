package com.task.fixergateway.persistence.dto.json;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class JsonRequestHistoryDto extends JsonRequestDto {
    private int period;

}

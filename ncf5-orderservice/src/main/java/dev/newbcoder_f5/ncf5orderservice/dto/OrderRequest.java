package dev.newbcoder_f5.ncf5orderservice.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

    private List<OrderLineItemsDto> orderLineItemsDtoList;
    
}

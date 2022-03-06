package br.com.dias.blurrystoremanager.domain.event;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Builder
@Getter
@Setter
public class ProductEvent {
    private UUID productId;
    private String name;
    private Float price;
}

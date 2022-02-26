package br.com.dias.blurrystoremanager.dto;

import br.com.dias.blurrystoremanager.exception.ProductNotCreatedException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Builder
public class ProductDTO {

    private UUID id;

    @NotNull
    private String name;

    @NotNull
    private Float price;

    public ProductDTO() {
    }

    @JsonCreator
    public ProductDTO(@JsonProperty("id") final UUID id, @JsonProperty("name") final String name,
                      @JsonProperty("price") final Float price) {
        List<String> errors = new ArrayList<>();
        if(name.isBlank()){
            throw new ProductNotCreatedException("name property cannot be null");
        }
        if(price == null){
            throw new ProductNotCreatedException("price property cannot be null");
        }
        this.id = id;
        this.name = Objects.requireNonNull(name);
        this.price = Objects.requireNonNull(price);
    }

}
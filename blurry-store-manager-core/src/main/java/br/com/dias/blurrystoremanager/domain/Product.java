package br.com.dias.blurrystoremanager.domain;

import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import java.util.Objects;
import java.util.UUID;

@Builder
@Getter
@Table(name = "products")
@Entity
public class Product {

    @Id
    @Column(name = "ID", columnDefinition = "uuid")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "ID",
            strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    
    @Column(name = "NAME")
    private String name;

    @Column(name = "PRICE")
    private Float price;

    public Product() {
    }

    public Product(final String name, final Float price) {
        this.name = Objects.requireNonNull(name, "name can not be null");
        this.price = Objects.requireNonNull(price, "price can not be null");
    }

    public Product(UUID id, String name, Float price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}

package istasenko.practicaltask.eshop.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import istasenko.practicaltask.eshop.serializer.MoneySerializer;

import java.math.BigDecimal;

public class ProductDto {

    private Long id;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String title;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonSerialize(using = MoneySerializer.class)
    private BigDecimal price;

    public ProductDto() {
    }

    public ProductDto(Long id) {
        this.id = id;
    }

    public ProductDto(Long id, String title, BigDecimal price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}

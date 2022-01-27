package istasenko.practicaltask.eshop.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NaturalId;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "PRODUCTS")
public class Product {

    @Id
    @GeneratedValue(generator = "ID_GENERATOR")
    @GenericGenerator(name = "ID_GENERATOR",
            strategy = "increment",
            parameters = {
                    @org.hibernate.annotations.Parameter(
                            name = "sequence_name",
                            value = "ESHOP_SEQUENCE"
                    )
            })
    @Column(name= "PRODUCT_ID", nullable = false, updatable = false, unique = true)
    private Long id;

    @NaturalId
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "TITLE", nullable = false, updatable = false)
    private String title;

    @Column(name = "PRICE")
    @NumberFormat(pattern = "0.00$")
    @JsonFormat(locale = "en", pattern = "0.00$")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private BigDecimal price;

    @OneToMany(mappedBy = "product")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<BasketedProduct> basketedProducts = new HashSet<>();

    @OneToMany(mappedBy = "product")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<OrderedProduct> orderedProducts = new HashSet<>();

    public Product() {
    }

    public Product(String title, BigDecimal price) {
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

    public Set<BasketedProduct> getBasketedProducts() {
        return basketedProducts;
    }

    public void setBasketedProducts(Set<BasketedProduct> basketedProducts) {
        this.basketedProducts = basketedProducts;
    }

    public Set<OrderedProduct> getOrderedProducts() {
        return orderedProducts;
    }

    public void setOrderedProducts(Set<OrderedProduct> orderedProducts) {
        this.orderedProducts = orderedProducts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id.equals(product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

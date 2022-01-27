package istasenko.practicaltask.eshop.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "BASKETS")
@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "basket-entity-graph",
                attributeNodes = {
                        @NamedAttributeNode(value = "basketedProducts")
                }
        )
})
public class Basket {
    @Id
    @GeneratedValue(generator = "ID_GENERATOR")
    @Column(name = "BASKET_ID",  unique = true, nullable = false, updatable = false)
    private Long id;

    @OneToOne(mappedBy = "basket", optional = false)
    private User user;

    @Column
    @OneToMany(mappedBy = "basket", cascade = ALL)
    private Set<BasketedProduct> basketedProducts = new HashSet<>();

    public Basket() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<BasketedProduct> getBasketedProducts() {
        return basketedProducts;
    }

    public void setBasketedProducts(Set<BasketedProduct> basketedProducts) {
        this.basketedProducts = basketedProducts;
    }

    public void addBasketedProduct(BasketedProduct basketedProduct) {basketedProducts.add(basketedProduct);}

    public void removeBasketedProduct(BasketedProduct basketedProduct) {basketedProducts.remove(basketedProduct);}
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Basket basket = (Basket) o;
        return Objects.equals(id, basket.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

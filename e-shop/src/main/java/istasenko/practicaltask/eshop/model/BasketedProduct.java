package istasenko.practicaltask.eshop.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "BASKETS_PRODUCTS")
public class BasketedProduct {

    @Embeddable
    public static class BasketProductId implements Serializable {
        @Column(name = "BASKET_ID")
        private Long basketId;

        @Column(name = "PRODUCT_ID")
        private Long productId;

        public BasketProductId() {

        }

        public BasketProductId(Long productId, Long basketId) {
            this.productId = productId;
            this.basketId = basketId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            BasketProductId id = (BasketProductId) o;
            return Objects.equals(basketId, id.basketId) && Objects.equals(productId, id.productId);
        }

        @Override
        public int hashCode() {
            return basketId.hashCode() + productId.hashCode();
        }
    }

    @EmbeddedId
    private BasketProductId id = new BasketProductId();

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID", insertable = false, updatable = false)
    private Product product;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "BASKET_ID", insertable = false, updatable = false)
    private Basket basket;

    @Column(name = "QUANTITY")
    private Long quantity;

    @Column(name = "TOTAL")
    private BigDecimal total;

    public BasketedProduct(
    ) {

    }

    public BasketedProduct(Product product, Basket basket) {
        quantity = 1L;
        total = product.getPrice();
        this.id.productId = product.getId();
        this.id.basketId = basket.getId();
        basket.getBasketedProducts().add(this);
        product.getBasketedProducts().add(this);
    }

    public BasketProductId getId() {
        return id;
    }

    public void setId(BasketProductId id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Long increaseQuantity() {
        quantity++;
        total = product.getPrice().multiply(BigDecimal.valueOf(quantity));
        return quantity;
    }

    public Long decreaseQuantity() {
        quantity--;
        total = product.getPrice().multiply(BigDecimal.valueOf(quantity));
        return quantity;
    }

    public void destruct() {
        basket.getBasketedProducts().remove(this);
        product.getBasketedProducts().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasketedProduct that = (BasketedProduct) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

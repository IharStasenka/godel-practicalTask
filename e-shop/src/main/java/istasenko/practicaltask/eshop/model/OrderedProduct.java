package istasenko.practicaltask.eshop.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
@Entity
@Table(name = "ORDERS_PRODUCTS")
public class OrderedProduct {

    @Embeddable
    public static class OrderProductId implements Serializable {
        @Column(name = "ORDER_ID")
        private Long orderID;

        @Column(name = "PRODUCT_ID")
        private Long productId;

        public OrderProductId() {


        }
        public OrderProductId(Long productId, Long basketId) {
            this.productId = productId;
            this.orderID = basketId;
        }



        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            OrderProductId that = (OrderProductId) o;
            return Objects.equals(orderID, that.orderID) && Objects.equals(productId, that.productId);
        }

        @Override
        public int hashCode() {
            return orderID.hashCode() + productId.hashCode();
        }
    }

    @EmbeddedId
    private OrderProductId id = new OrderProductId();

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID", insertable = false, updatable = false)
    private Product product;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID", insertable = false, updatable = false)
    private Order order;

    @Column(name = "QUANTITY")
    private Long quantity;

    @Column(name = "TOTAL")
    private BigDecimal total;

    public OrderedProduct() {

    }

    public OrderedProduct(Product product, Order order) {
        quantity = 1L;
        total = product.getPrice();
        this.id.productId = product.getId();
        this.id.orderID = order.getId();
        order.getItems().add(this);
        product.getOrderedProducts().add(this);
    }

    public OrderedProduct(BasketedProduct basketedProduct, Order order) {
        quantity = basketedProduct.getQuantity();
        total = basketedProduct.getTotal();
        product = basketedProduct.getProduct();
        this.id.productId = basketedProduct.getProduct().getId();
        this.id.orderID = order.getId();
        order.getItems().add(this);
        basketedProduct.getProduct().getOrderedProducts().add(this);
    }

    public OrderProductId getId() {
        return id;
    }

    public void setId(OrderProductId id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderedProduct that = (OrderedProduct) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

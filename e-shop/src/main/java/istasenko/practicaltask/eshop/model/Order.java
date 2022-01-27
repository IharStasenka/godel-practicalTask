package istasenko.practicaltask.eshop.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name = "ORDERS")

@NamedEntityGraph(
        name = "graph.OrderOrderedProductProduct",
        attributeNodes = @NamedAttributeNode(
                value = "items",
                subgraph = "subgraph.orderedProducts"),
        subgraphs = {
                @NamedSubgraph(
                        name = "subgraph.orderedProducts",
                        attributeNodes = @NamedAttributeNode(value = "product"))
        }

)
public class Order {
    @Id
    @GeneratedValue(generator = "ID_GENERATOR")
    @Column(name = "ORDER_ID", unique = true, nullable = false, updatable = false)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TIMESTAMP", nullable = false, updatable = false)
    @CreationTimestamp
    private Date timestamp;

    @Column(name = "ORDER_PRICE")
    private BigDecimal orderPrice ;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false, updatable = false)
    private User user;

    @OneToMany(mappedBy = "order", cascade = ALL)
    private Set<OrderedProduct> items = new LinkedHashSet<>();

    public Order() {
        orderPrice = BigDecimal.ZERO;
    }

    public Order(User user) {
        this.user = user;
        orderPrice = BigDecimal.ZERO;
        user.getOrders().add(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Set<OrderedProduct> getItems() {
        return items;
    }

    public void setItems(Set<OrderedProduct> items) {
        this.items = items;
        for (OrderedProduct product: items) {
            orderPrice = orderPrice.add(product.getTotal());
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

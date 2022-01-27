package istasenko.practicaltask.eshop.dto;

import istasenko.practicaltask.eshop.dto.processedproductsdto.OrderedProductDto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

public class OrderDto {
    private Long id;

    private BigDecimal orderPrice;

    private Date timestamp;

    private Set<OrderedProductDto> items;

    public OrderDto() {
    }

    public OrderDto(Long id, BigDecimal orderPrice, Date timestamp, Set<OrderedProductDto> items) {
        this.id = id;
        this.orderPrice = orderPrice;
        this.timestamp = timestamp;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Set<OrderedProductDto> getItems() {
        return items;
    }

    public void setItems(Set<OrderedProductDto> items) {
        this.items = items;
    }
}

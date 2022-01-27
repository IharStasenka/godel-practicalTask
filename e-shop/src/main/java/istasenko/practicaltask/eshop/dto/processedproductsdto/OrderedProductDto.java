package istasenko.practicaltask.eshop.dto.processedproductsdto;

import java.math.BigDecimal;

public class OrderedProductDto{
    private Long id;
    private BigDecimal price;
    private String title;
    private Long quantity;
    private BigDecimal total;

    public OrderedProductDto() {
    }

    public OrderedProductDto(Long id, String title, BigDecimal price, Long quantity, BigDecimal total) {
        this.id = id;
        this.price = price;
        this.title = title;
        this.quantity = quantity;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
}

package istasenko.practicaltask.eshop.dto.processedproductsdto;

import java.math.BigDecimal;

public class BasketedProductDto{
    private String title;
    private Long quantity;
    private BigDecimal total;

    public BasketedProductDto() {
    }

    public BasketedProductDto(String title, Long quantity, BigDecimal total) {
        this.title = title;
        this.quantity = quantity;
        this.total = total;
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

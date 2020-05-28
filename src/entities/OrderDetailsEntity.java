package entities;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "order_details", schema = "northwind", catalog = "")
public class OrderDetailsEntity {
    private int id;
    private BigDecimal quantity;
    private BigDecimal unitPrice;
    private double discount;
    private Timestamp dateAllocated;
    private Integer purchaseOrderId;
    private Integer inventoryId;
    private Integer productId;
    private Integer orderId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "quantity", nullable = false, precision = 4)
    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    @Basic
    @Column(name = "unit_price", nullable = true, precision = 4)
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Basic
    @Column(name = "discount", nullable = false, precision = 0)
    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Basic
    @Column(name = "date_allocated", nullable = true)
    public Timestamp getDateAllocated() {
        return dateAllocated;
    }

    public void setDateAllocated(Timestamp dateAllocated) {
        this.dateAllocated = dateAllocated;
    }

    @Basic
    @Column(name = "purchase_order_id", nullable = true)
    public Integer getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public void setPurchaseOrderId(Integer purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

    @Basic
    @Column(name = "inventory_id", nullable = true)
    public Integer getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Integer inventoryId) {
        this.inventoryId = inventoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetailsEntity that = (OrderDetailsEntity) o;
        return id == that.id &&
                Double.compare(that.discount, discount) == 0 &&
                Objects.equals(quantity, that.quantity) &&
                Objects.equals(unitPrice, that.unitPrice) &&
                Objects.equals(dateAllocated, that.dateAllocated) &&
                Objects.equals(purchaseOrderId, that.purchaseOrderId) &&
                Objects.equals(inventoryId, that.inventoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quantity, unitPrice, discount, dateAllocated, purchaseOrderId, inventoryId);
    }

    @Basic
    @Column(name = "product_id")
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Basic
    @Column(name = "order_id")
    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}

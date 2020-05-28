package entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "products", schema = "northwind", catalog = "")
public class ProductsEntity {
    private String supplierIds;
    private int id;
    private String productCode;
    private String productName;
    private String description;
    private BigDecimal standardCost;
    private BigDecimal listPrice;
    private Integer reorderLevel;
    private Integer targetLevel;
    private String quantityPerUnit;
    private byte discontinued;
    private Integer minimumReorderQuantity;
    private String category;
    private byte[] attachments;

    @Basic
    @Column(name = "supplier_ids", nullable = true, length = -1)
    public String getSupplierIds() {
        return supplierIds;
    }

    public void setSupplierIds(String supplierIds) {
        this.supplierIds = supplierIds;
    }

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "product_code", nullable = true, length = 25)
    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    @Basic
    @Column(name = "product_name", nullable = true, length = 50)
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Basic
    @Column(name = "description", nullable = true, length = -1)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "standard_cost", nullable = true, precision = 4)
    public BigDecimal getStandardCost() {
        return standardCost;
    }

    public void setStandardCost(BigDecimal standardCost) {
        this.standardCost = standardCost;
    }

    @Basic
    @Column(name = "list_price", nullable = false, precision = 4)
    public BigDecimal getListPrice() {
        return listPrice;
    }

    public void setListPrice(BigDecimal listPrice) {
        this.listPrice = listPrice;
    }

    @Basic
    @Column(name = "reorder_level", nullable = true)
    public Integer getReorderLevel() {
        return reorderLevel;
    }

    public void setReorderLevel(Integer reorderLevel) {
        this.reorderLevel = reorderLevel;
    }

    @Basic
    @Column(name = "target_level", nullable = true)
    public Integer getTargetLevel() {
        return targetLevel;
    }

    public void setTargetLevel(Integer targetLevel) {
        this.targetLevel = targetLevel;
    }

    @Basic
    @Column(name = "quantity_per_unit", nullable = true, length = 50)
    public String getQuantityPerUnit() {
        return quantityPerUnit;
    }

    public void setQuantityPerUnit(String quantityPerUnit) {
        this.quantityPerUnit = quantityPerUnit;
    }

    @Basic
    @Column(name = "discontinued", nullable = false)
    public byte getDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(byte discontinued) {
        this.discontinued = discontinued;
    }

    @Basic
    @Column(name = "minimum_reorder_quantity", nullable = true)
    public Integer getMinimumReorderQuantity() {
        return minimumReorderQuantity;
    }

    public void setMinimumReorderQuantity(Integer minimumReorderQuantity) {
        this.minimumReorderQuantity = minimumReorderQuantity;
    }

    @Basic
    @Column(name = "category", nullable = true, length = 50)
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Basic
    @Column(name = "attachments", nullable = true)
    public byte[] getAttachments() {
        return attachments;
    }

    public void setAttachments(byte[] attachments) {
        this.attachments = attachments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductsEntity that = (ProductsEntity) o;
        return id == that.id &&
                discontinued == that.discontinued &&
                Objects.equals(supplierIds, that.supplierIds) &&
                Objects.equals(productCode, that.productCode) &&
                Objects.equals(productName, that.productName) &&
                Objects.equals(description, that.description) &&
                Objects.equals(standardCost, that.standardCost) &&
                Objects.equals(listPrice, that.listPrice) &&
                Objects.equals(reorderLevel, that.reorderLevel) &&
                Objects.equals(targetLevel, that.targetLevel) &&
                Objects.equals(quantityPerUnit, that.quantityPerUnit) &&
                Objects.equals(minimumReorderQuantity, that.minimumReorderQuantity) &&
                Objects.equals(category, that.category) &&
                Arrays.equals(attachments, that.attachments);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(supplierIds, id, productCode, productName, description, standardCost, listPrice, reorderLevel, targetLevel, quantityPerUnit, discontinued, minimumReorderQuantity, category);
        result = 31 * result + Arrays.hashCode(attachments);
        return result;
    }
}

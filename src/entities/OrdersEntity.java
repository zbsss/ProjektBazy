package entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "orders", schema = "northwind", catalog = "")
public class OrdersEntity {
    private int id;
    private Timestamp orderDate;
    private Timestamp shippedDate;
    private String shipName;
    private String shipAddress;
    private String shipCity;
    private String shipStateProvince;
    private String shipZipPostalCode;
    private String shipCountryRegion;
    private BigDecimal shippingFee;
    private BigDecimal taxes;
    private String paymentType;
    private Timestamp paidDate;
    private String notes;
    private Double taxRate;
    private EmployeesEntity employeesByEmployeeId;
    private CustomersEntity customersByCustomerId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "order_date", nullable = true)
    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    @Basic
    @Column(name = "shipped_date", nullable = true)
    public Timestamp getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(Timestamp shippedDate) {
        this.shippedDate = shippedDate;
    }

    @Basic
    @Column(name = "ship_name", nullable = true, length = 50)
    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    @Basic
    @Column(name = "ship_address", nullable = true, length = -1)
    public String getShipAddress() {
        return shipAddress;
    }

    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }

    @Basic
    @Column(name = "ship_city", nullable = true, length = 50)
    public String getShipCity() {
        return shipCity;
    }

    public void setShipCity(String shipCity) {
        this.shipCity = shipCity;
    }

    @Basic
    @Column(name = "ship_state_province", nullable = true, length = 50)
    public String getShipStateProvince() {
        return shipStateProvince;
    }

    public void setShipStateProvince(String shipStateProvince) {
        this.shipStateProvince = shipStateProvince;
    }

    @Basic
    @Column(name = "ship_zip_postal_code", nullable = true, length = 50)
    public String getShipZipPostalCode() {
        return shipZipPostalCode;
    }

    public void setShipZipPostalCode(String shipZipPostalCode) {
        this.shipZipPostalCode = shipZipPostalCode;
    }

    @Basic
    @Column(name = "ship_country_region", nullable = true, length = 50)
    public String getShipCountryRegion() {
        return shipCountryRegion;
    }

    public void setShipCountryRegion(String shipCountryRegion) {
        this.shipCountryRegion = shipCountryRegion;
    }

    @Basic
    @Column(name = "shipping_fee", nullable = true, precision = 4)
    public BigDecimal getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(BigDecimal shippingFee) {
        this.shippingFee = shippingFee;
    }

    @Basic
    @Column(name = "taxes", nullable = true, precision = 4)
    public BigDecimal getTaxes() {
        return taxes;
    }

    public void setTaxes(BigDecimal taxes) {
        this.taxes = taxes;
    }

    @Basic
    @Column(name = "payment_type", nullable = true, length = 50)
    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    @Basic
    @Column(name = "paid_date", nullable = true)
    public Timestamp getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(Timestamp paidDate) {
        this.paidDate = paidDate;
    }

    @Basic
    @Column(name = "notes", nullable = true, length = -1)
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Basic
    @Column(name = "tax_rate", nullable = true, precision = 0)
    public Double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrdersEntity that = (OrdersEntity) o;
        return id == that.id &&
                Objects.equals(orderDate, that.orderDate) &&
                Objects.equals(shippedDate, that.shippedDate) &&
                Objects.equals(shipName, that.shipName) &&
                Objects.equals(shipAddress, that.shipAddress) &&
                Objects.equals(shipCity, that.shipCity) &&
                Objects.equals(shipStateProvince, that.shipStateProvince) &&
                Objects.equals(shipZipPostalCode, that.shipZipPostalCode) &&
                Objects.equals(shipCountryRegion, that.shipCountryRegion) &&
                Objects.equals(shippingFee, that.shippingFee) &&
                Objects.equals(taxes, that.taxes) &&
                Objects.equals(paymentType, that.paymentType) &&
                Objects.equals(paidDate, that.paidDate) &&
                Objects.equals(notes, that.notes) &&
                Objects.equals(taxRate, that.taxRate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderDate, shippedDate, shipName, shipAddress, shipCity, shipStateProvince, shipZipPostalCode, shipCountryRegion, shippingFee, taxes, paymentType, paidDate, notes, taxRate);
    }

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    public EmployeesEntity getEmployeesByEmployeeId() {
        return employeesByEmployeeId;
    }

    public void setEmployeesByEmployeeId(EmployeesEntity employeesByEmployeeId) {
        this.employeesByEmployeeId = employeesByEmployeeId;
    }

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    public CustomersEntity getCustomersByCustomerId() {
        return customersByCustomerId;
    }

    public void setCustomersByCustomerId(CustomersEntity customersByCustomerId) {
        this.customersByCustomerId = customersByCustomerId;
    }
}

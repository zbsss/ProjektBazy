import entities.CustomersEntity;
import org.hibernate.Session;

import javax.persistence.Query;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import java.math.BigDecimal;
import java.util.Date;

public class OrderData {
    private int orderID;
    private Date orderDate;
    private int customerID;
    private int productID;
    private String productName;
    private double unitPrice;
    private BigDecimal quantity;
    private double discount;
    private double shippingFee;
    private double totalPrice;


    public OrderData() {
    }

    public OrderData(
            int orderID,
            Date orderDate,
            int customerID,
            int productID,
            String productName,
            BigDecimal unitPrice,
            BigDecimal quantity,
            double discount,
            double totalPrice,
            BigDecimal shippingFee
    ) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.customerID = customerID;
        this.productID = productID;
        this.productName = productName;
        this.unitPrice = unitPrice.doubleValue();
        this.quantity = quantity;
        this.discount = discount;
        this.totalPrice = totalPrice;
        this.shippingFee = shippingFee.doubleValue();
    }


    public static List<OrderData> getOrders(Timestamp startDate, Timestamp endDate, CustomersEntity customer, Session session){
        @SuppressWarnings("rawtypes") Query query = session
                .createQuery(
                        "select new OrderData (\n" +
                                "\t\to.id,\n" +
                                "\t\to.orderDate,\n" +
                                "\t\tc.id,\n" +
                                "\t\tp.id,\n" +
                                "\t\tp.productName,\n" +
                                "\t\tod.unitPrice,\n" +
                                "\t\tod.quantity,\n" +
                                "\t\tod.discount,\n" +
                                "\t\t(od.unitPrice * od.quantity * (1 - od.discount)) + o.shippingFee as totalPrice ,\n" +
                                "\t\to.shippingFee)\n" +
                                "\tfrom  OrdersEntity as o \n" +
                                "\tinner join CustomersEntity as c on c.id = o.customersByCustomerId.id\n" +
                                "\tinner join OrderDetailsEntity as od on od.orderId=o.id\n" +
                                "\tinner join ProductsEntity as p on p.id=od.productId\n" +
                                "\twhere c.id = \'" + customer.getId() + "\'\n" +
                                "\t \tand o.orderDate between  \'" + startDate + "\'\n" +
                                " \t \tand  \'" + endDate + "\'");

        List<OrderData> invoiceResultList = new LinkedList<>();
        for(Object o : query.getResultList()){
            invoiceResultList.add((OrderData)o);
        }
        return invoiceResultList;
    }

    public int getOrderID() {
        return orderID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public int getCustomerID() {
        return customerID;
    }


    public int getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public double getDiscount() {
        return discount;
    }

    public double getShippingFee() {
        return shippingFee;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

}
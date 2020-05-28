import entities.CustomersEntity;
import org.hibernate.Session;

import java.io.File;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class Invoice {
    private String invoiceId;
    private CustomersEntity customer;
    private List<OrderData> orderData;
    private Date startDate;
    private Date endDate;
    private double totalShipping;
    private double totalPrice;

    public Invoice() {
    }

    private Invoice(
            String invoiceId,
            CustomersEntity customer,
            List<OrderData> orderData,
            Date startDate,
            Date endDate,
            double totalShipping,
            double totalPrice
    ) {
        this.invoiceId = invoiceId;
        this.customer = customer;
        this.orderData = orderData;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalShipping = totalShipping;
        this.totalPrice = totalPrice;
    }

    public void create(String invoiceNumber, Timestamp startDate, Timestamp endDate, CustomersEntity customer, Session session) {

        List<OrderData> orders = OrderData.getOrders(startDate, endDate, customer, session);
        double totalShipping = orders.stream().map(OrderData::getShippingFee).reduce(0.0, Double::sum);
        double orderPrice = orders.stream().map(OrderData::getTotalPrice).reduce(0.0, Double::sum);
        Invoice invoiceData = new Invoice(invoiceNumber, customer, orders, startDate, endDate, totalShipping, orderPrice);

        String fileName = title(customer.getCompany());
        File file = new File("invoices/" + fileName + "_invoice.pdf");
        InvoicePDFBuilder.makePDF(file, invoiceData);
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public CustomersEntity getCustomer() {
        return customer;
    }

    public List<OrderData> getOrderData() {
        return orderData;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public double getTotalShipping() {
        return totalShipping;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    private static String title(final String init) {
        if (init==null)
            return null;

        final StringBuilder ret = new StringBuilder(init.length());

        for (final String word : init.split(" ")) {
            if (!word.isEmpty()) {
                ret.append(word.substring(0, 1).toUpperCase());
                ret.append(word.substring(1).toLowerCase());
            }
            if (!(ret.length()==init.length()))
                ret.append("_");
        }

        return ret.toString();
    }
}

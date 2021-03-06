
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.FontSelector;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


public class InvoicePDFBuilder {

    public static void makePDF(File fileName, Invoice data) {
        List<OrderData> ordersList = data.getOrderData();

        Date curDate = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyy");
        String date = format.format(curDate);
        try {

            OutputStream file = new FileOutputStream(fileName);
            Document document = new Document();
            PdfWriter.getInstance(document, file);

            Image image = Image.getInstance("resources/logo.jpg");
            image.scaleAbsolute(540f, 72f);


            PdfPTable irdTable = new PdfPTable(2);
            irdTable.setWidthPercentage(50);
            irdTable.addCell(getIRDCell("Invoice No "));
            irdTable.addCell(getIRDCell(data.getInvoiceId()));
            irdTable.addCell(getIRDCell("Invoice Date"));
            irdTable.addCell(getIRDCell(date));


            PdfPTable irhTable = new PdfPTable(3);
            irhTable.setWidthPercentage(100);

            irhTable.addCell(getIRHCell("", PdfPCell.ALIGN_RIGHT));
            irhTable.addCell(getIRHCell("", PdfPCell.ALIGN_RIGHT));
            irhTable.addCell(getIRHCell("Invoice", PdfPCell.ALIGN_RIGHT));
            irhTable.addCell(getIRHCell("", PdfPCell.ALIGN_RIGHT));
            irhTable.addCell(getIRHCell("", PdfPCell.ALIGN_RIGHT));
            PdfPCell invoiceTable = new PdfPCell(irdTable);
            invoiceTable.setBorder(0);
            irhTable.addCell(invoiceTable);





            FontSelector fs = new FontSelector();
            Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 13, Font.BOLD);
            fs.addFont(font);
            Phrase bill = fs.process("Bill To:");
            Paragraph name = new Paragraph( "Mr.\\ Mrs. " + data.getCustomer().getFirstName() + " " + data.getCustomer().getLastName() + ", " + data.getCustomer().getJobTitle());
            name.setIndentationLeft(20);
            Paragraph companyName = new Paragraph(data.getCustomer().getCompany());
            companyName.setIndentationLeft(20);
            Paragraph address1line = new Paragraph(data.getCustomer().getAddress());
            address1line.setIndentationLeft(20);
            Paragraph address2line = new Paragraph(data.getCustomer().getCity() + ", " + data.getCustomer().getZipPostalCode() + ", " + data.getCustomer().getCountryRegion());
            address2line.setIndentationLeft(20);

            PdfPTable billTable = new PdfPTable(9);
            billTable.setWidthPercentage(100);
            billTable.setWidths(new float[]{1, 1, 2, 3, 1, 1, 1, 1, 1});
            billTable.setSpacingBefore(30.0f);
            billTable.addCell(getBillHeaderCell("Index"));
            billTable.addCell(getBillHeaderCell("OrderID"));
            billTable.addCell(getBillHeaderCell("Order Date"));
            billTable.addCell(getBillHeaderCell("Product Name"));
            billTable.addCell(getBillHeaderCell("Unit Price"));
            billTable.addCell(getBillHeaderCell("Quantity"));
            billTable.addCell(getBillHeaderCell("Discount"));
            billTable.addCell(getBillHeaderCell("Shipping Price"));
            billTable.addCell(getBillHeaderCell("Total Cost"));

            for (int i = 0; i < ordersList.size(); i++) {
                billTable.addCell(getBillRowCell((i + 1) + ""));
                billTable.addCell(getBillRowCell(String.valueOf(ordersList.get(i).getOrderID())));
                billTable.addCell(getBillRowCell(format.format(ordersList.get(i).getOrderDate()) + ""));

                billTable.addCell(getBillRowCell(ordersList.get(i).getProductName() + ""));
                billTable.addCell(getBillRowCell(ordersList.get(i).getUnitPrice() + ""));
                billTable.addCell(getBillRowCell(ordersList.get(i).getQuantity() + ""));
                billTable.addCell(getBillRowCell(ordersList.get(i).getDiscount() + ""));
                billTable.addCell(getBillRowCell((Math.floor(ordersList.get(i).getShippingFee() * 100) / 100) + ""));
                billTable.addCell(getBillRowCell((Math.floor(ordersList.get(i).getTotalPrice() * 100) / 100) + ""));
            }
            for (int j = 0; j < 9; j++) {
                billTable.addCell(getBillRowCell(""));
                billTable.addCell(getBillRowCell(""));
                billTable.addCell(getBillRowCell(""));
                billTable.addCell(getBillRowCell(""));
                billTable.addCell(getBillRowCell(""));
                billTable.addCell(getBillRowCell(""));
            }

            PdfPTable validity = new PdfPTable(1);
            validity.setWidthPercentage(100);
            validity.addCell(getValidityCell(" "));
            validity.addCell(getValidityCell("\t\tAdditional Information:"));
            PdfPCell summaryL = new PdfPCell(validity);
            summaryL.setColspan(4);
            summaryL.setPadding(1.0f);
            billTable.addCell(summaryL);

            PdfPTable accounts = new PdfPTable(2);
            accounts.setWidthPercentage(100);
            accounts.addCell(getAccountsCell("Shipping Total Price:"));
            accounts.addCell(getAccountsCellR("$" + String.valueOf(Math.floor(data.getTotalShipping() * 100) / 100)));
            accounts.addCell(getAccountsCell("Orders Total Price:"));
            accounts.addCell(getAccountsCellR("$" + String.valueOf(Math.floor(data.getTotalPrice() * 100) / 100)));
            accounts.addCell(getAccountsCell("Summary Total Price:"));
            accounts.addCell(getAccountsCellR("$" + String.valueOf(Math.floor((data.getTotalShipping() + data.getTotalPrice()) * 100) / 100)));
            accounts.addCell(getAccountsCell(""));
            accounts.addCell(getAccountsCellR(""));
            accounts.addCell(getAccountsCell("Invoices since:"));
            accounts.addCell(getAccountsCellR(format.format(data.getStartDate())));
            accounts.addCell(getAccountsCell("Due to:"));
            accounts.addCell(getAccountsCellR(format.format(data.getEndDate())));


            PdfPCell summaryR = new PdfPCell(accounts);
            summaryR.setColspan(9);
            billTable.addCell(summaryR);

            PdfPTable describer = new PdfPTable(1);
            describer.setWidthPercentage(100);
            describer.addCell(getdescCell(" "));
            describer.addCell(getdescCell(" \n\n"));

            document.open();

            document.add(image);
            document.add(irhTable);
            document.add(bill);
            document.add(name);
            document.add(companyName);
            document.add(address1line);
            document.add(address2line);
            document.add(billTable);
            document.add(describer);

            document.close();

            file.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public static PdfPCell getIRHCell(String text, int alignment) {
        FontSelector fs = new FontSelector();
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 16);
        font.setColor(BaseColor.GRAY);
        fs.addFont(font);
        Phrase phrase = fs.process(text);
        PdfPCell cell = new PdfPCell(phrase);
        cell.setPadding(5);
        cell.setHorizontalAlignment(alignment);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }

    public static PdfPCell getIRDCell(String text) {
        PdfPCell cell = new PdfPCell(new Paragraph(text));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(5.0f);

        cell.setBorderWidthRight(0);
        cell.setBorderWidthLeft(0);
        cell.setBorderWidthBottom(0);
        cell.setBorderWidthTop(0);
        return cell;
    }

    public static PdfPCell getBillHeaderCell(String text) {
        FontSelector fs = new FontSelector();
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 9);
        font.setColor(BaseColor.GRAY);
        fs.addFont(font);
        Phrase phrase = fs.process(text);
        PdfPCell cell = new PdfPCell(phrase);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(5.0f);
        return cell;
    }

    public static PdfPCell getBillRowCell(String text) {
        FontSelector fs = new FontSelector();
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 10);
        fs.addFont(font);
        Phrase phrase = fs.process(text);
        PdfPCell cell = new PdfPCell(phrase);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(5.0f);
        cell.setBorderWidthBottom(0);
        cell.setBorderWidthTop(0);

        return cell;
    }

    public static PdfPCell getBillFooterCell(String text) {
        PdfPCell cell = new PdfPCell(new Paragraph(text));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(5.0f);
        cell.setBorderWidthBottom(0);
        cell.setBorderWidthTop(0);
        return cell;
    }

    public static PdfPCell getValidityCell(String text) {
        FontSelector fs = new FontSelector();
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 10);
        font.setColor(BaseColor.GRAY);
        fs.addFont(font);
        Phrase phrase = fs.process(text);
        PdfPCell cell = new PdfPCell(phrase);
        cell.setBorder(0);
        return cell;
    }

    public static PdfPCell getAccountsCell(String text) {
        FontSelector fs = new FontSelector();
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 10);
        fs.addFont(font);
        Phrase phrase = fs.process(text);
        PdfPCell cell = new PdfPCell(phrase);
        cell.setBorderWidth(0);
        cell.setPadding(5.0f);
        return cell;
    }

    public static PdfPCell getAccountsCellR(String text) {
        FontSelector fs = new FontSelector();
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 10);
        fs.addFont(font);
        Phrase phrase = fs.process(text);
        PdfPCell cell = new PdfPCell(phrase);
        cell.setBorderWidth(0);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setPadding(5.0f);
        cell.setPaddingRight(20.0f);
        return cell;
    }

    public static PdfPCell getdescCell(String text) {
        FontSelector fs = new FontSelector();
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 10);
        font.setColor(BaseColor.GRAY);
        fs.addFont(font);
        Phrase phrase = fs.process(text);
        PdfPCell cell = new PdfPCell(phrase);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(0);
        return cell;
    }


}
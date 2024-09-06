package com.vn.jewelry_management_system.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.vn.jewelry_management_system.domain.BuybackInvoice;
import com.vn.jewelry_management_system.domain.Product;
import com.vn.jewelry_management_system.domain.SalesInvoice;
import com.vn.jewelry_management_system.domain.SalesInvoiceDetail;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.stream.Stream;
import java.io.IOException;

@Service
public class InvoicePdfService {
        private final ProductService productService;

        public InvoicePdfService(ProductService productService) {
                this.productService = productService;
        }

        public byte[] generateInvoicePdf(SalesInvoice invoice) throws DocumentException {
                Document document = new Document();

                // Thiết lập căn lề cho toàn bộ hóa đơn (trái, phải, trên, dưới)
                document.setMargins(60, 60, 60, 60); // Thay đổi giá trị 36 để điều chỉnh lề (đơn vị tính là points)

                ByteArrayOutputStream out = new ByteArrayOutputStream();
                PdfWriter.getInstance(document, out);

                document.open();

                // Thiết lập font chữ cho toàn bộ tài liệu
                Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, BaseColor.BLACK);
                Font infoFont = FontFactory.getFont(FontFactory.HELVETICA, 16, BaseColor.BLACK);
                Font tableHeaderFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, BaseColor.WHITE);

                // Thêm tiêu đề hóa đơn
                Paragraph title = new Paragraph("SALES INVOICE", titleFont);
                title.setAlignment(Element.ALIGN_CENTER);
                document.add(title);

                // Thêm thông tin cửa hàng
                Paragraph storeInfo = new Paragraph(
                                "Jewelry Store AnhPhanLe\nAddress: 123 XYZ Street, Ho Chi Minh City\nPhone: 0123456789",
                                infoFont);
                storeInfo.setAlignment(Element.ALIGN_CENTER);
                document.add(storeInfo);

                // Thêm khoảng trống
                document.add(new Paragraph("\n"));

                // Thêm thông tin hóa đơn
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Paragraph invoiceInfo = new Paragraph(
                                "Invoice No.: " + invoice.getSalesInvoiceId() + "\n" +
                                                "Created Date: " + dateFormat.format(invoice.getCreatedDate()) + "\n" +
                                                "Customer: " + invoice.getCustomer().getCustomerName() + "\n" +
                                                "Employee: " + invoice.getEmployee().getEmployeeName() + "\n" +
                                                "Stall: " + invoice.getStall().getStallName(),
                                infoFont);
                document.add(invoiceInfo);

                // Thêm bảng chi tiết sản phẩm
                PdfPTable table = new PdfPTable(4); // 4 cột: Product, Quantity, Unit Price, Total
                table.setWidthPercentage(100);
                table.setSpacingBefore(10f);
                table.setSpacingAfter(10f);

                // Thêm header cho bảng
                addTableHeader(table, tableHeaderFont);

                // Định dạng tiền tệ VND
                NumberFormat vndFormat = DecimalFormat.getCurrencyInstance(new Locale("vi", "VN"));

                // Thêm chi tiết sản phẩm
                for (SalesInvoiceDetail detail : invoice.getSalesInvoiceDetails()) {
                        table.addCell(detail.getProduct().getProductName());
                        table.addCell(String.valueOf(detail.getQuantity()));
                        table.addCell(vndFormat.format(detail.getUnitPrice()));
                        table.addCell(vndFormat
                                        .format(detail.getUnitPrice().multiply(new BigDecimal(detail.getQuantity()))));
                }
                document.add(table);

                // Thêm tổng tiền và chiết khấu
                Paragraph totalInfo = new Paragraph(
                                "Total: " + vndFormat.format(invoice.getTotalAmount()) + "\n" +
                                                "Discount: " + vndFormat.format(invoice.getDiscount()) + "\n" +
                                                "Net Amount: "
                                                + vndFormat.format(invoice.getTotalAmount()
                                                                .subtract(invoice.getDiscount())),
                                infoFont);
                totalInfo.setAlignment(Element.ALIGN_RIGHT);
                document.add(totalInfo);

                document.close();
                return out.toByteArray();
        }

        public byte[] generateWarrantyPdf(SalesInvoice invoice) throws DocumentException {
                Document document = new Document();
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                PdfWriter.getInstance(document, out);

                document.open();

                // Thiết lập font chữ cho toàn bộ tài liệu
                Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, BaseColor.BLACK);
                Font infoFont = FontFactory.getFont(FontFactory.HELVETICA, 16, BaseColor.BLACK);

                // Thêm tiêu đề phiếu bảo hành
                Paragraph title = new Paragraph("WARRANTY CARD", titleFont);
                title.setAlignment(Element.ALIGN_CENTER);
                document.add(title);

                // Thêm thông tin cửa hàng
                Paragraph storeInfo = new Paragraph(
                                "Jewelry Store ABC\nAddress: 123 XYZ Street, Ho Chi Minh City\nPhone: 0123456789",
                                infoFont);
                storeInfo.setAlignment(Element.ALIGN_CENTER);
                document.add(storeInfo);

                // Thêm khoảng trống
                document.add(new Paragraph("\n"));

                // Thêm thông tin hóa đơn và sản phẩm
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                for (SalesInvoiceDetail detail : invoice.getSalesInvoiceDetails()) {
                        Paragraph productInfo = new Paragraph(
                                        "Invoice No.: " + invoice.getSalesInvoiceId() + "\n" +
                                                        "Purchased Date: " + dateFormat.format(invoice.getCreatedDate())
                                                        + "\n" +
                                                        "Product: " + detail.getProduct().getProductName(),
                                        infoFont);
                        document.add(productInfo);

                        // Thêm khoảng trống giữa các sản phẩm
                        document.add(new Paragraph("\n"));
                }

                // Thêm điều khoản bảo hành (bạn có thể tùy chỉnh nội dung)
                Paragraph warrantyTerms = new Paragraph(
                                "Warranty terms:\n" +
                                                "- This warranty card is valid for 1 year from the date of purchase.\n"
                                                +
                                                "- The warranty covers manufacturing defects only.\n" +
                                                "- This warranty does not cover damage caused by misuse, accidents, or unauthorized repairs.",
                                infoFont);
                document.add(warrantyTerms);

                document.close();
                return out.toByteArray();
        }

        private void addTableHeader(PdfPTable table, Font font) {
                Stream.of("Product", "Quantity", "Unit Price", "Total")
                                .forEach(columnTitle -> {
                                        PdfPCell header = new PdfPCell();
                                        header.setBackgroundColor(BaseColor.GRAY); // Màu header
                                        header.setBorderWidth(2);
                                        header.setHorizontalAlignment(Element.ALIGN_CENTER); // Căn giữa nội dung header
                                        header.setPhrase(new Phrase(columnTitle, font));
                                        table.addCell(header);
                                });
        }

        public byte[] generateBuybackInvoicePdf(BuybackInvoice buybackInvoice) throws DocumentException, IOException {
                Document document = new Document();
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                PdfWriter.getInstance(document, out);

                document.open();

                // Load font chữ tiếng Việt
                BaseFont bf = BaseFont.createFont("fonts/VNI-Times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                Font titleFont = new Font(bf, 20, Font.BOLD);
                Font infoFont = new Font(bf, 16);
                Font tableHeaderFont = new Font(bf, 16, Font.BOLD, BaseColor.WHITE);

                // Thêm tiêu đề hóa đơn mua lại
                Paragraph title = new Paragraph("BUYBACK INVOICE", titleFont);
                title.setAlignment(Element.ALIGN_CENTER);
                document.add(title);

                // Thêm thông tin cửa hàng
                Paragraph storeInfo = new Paragraph(
                                "Jewelry Store ABC\nAddress: 123 XYZ Street, Ho Chi Minh City\nPhone: 0123456789",
                                infoFont);
                storeInfo.setAlignment(Element.ALIGN_CENTER);
                document.add(storeInfo);

                // Thêm khoảng trống
                document.add(new Paragraph("\n"));

                // Thêm thông tin hóa đơn mua lại
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Paragraph invoiceInfo = new Paragraph(
                                "Buyback Invoice No.: " + buybackInvoice.getBuybackInvoiceId() + "\n" +
                                                "Created Date: " + dateFormat.format(buybackInvoice.getCreatedDate())
                                                + "\n" +
                                                "Customer: " + buybackInvoice.getCustomer().getCustomerName() + "\n" +
                                                "Employee: " + buybackInvoice.getEmployee().getEmployeeName() + "\n" +
                                                "Stall: " + buybackInvoice.getStall().getStallName() + "\n" +
                                                "Sales Invoice No.: "
                                                + buybackInvoice.getSalesInvoice().getSalesInvoiceId(), // Thêm Sales
                                                                                                        // Invoice ID
                                infoFont);
                document.add(invoiceInfo);

                // Thêm bảng chi tiết sản phẩm
                PdfPTable table = new PdfPTable(4); // 4 cột: Product, Quantity, Unit Price, Total
                table.setWidthPercentage(100);
                table.setSpacingBefore(10f);
                table.setSpacingAfter(10f);

                // Thêm header cho bảng
                addTableHeader(table, tableHeaderFont);

                // Định dạng tiền tệ VND
                NumberFormat vndFormat = DecimalFormat.getCurrencyInstance(new Locale("vi", "VN"));

                // Thêm chi tiết sản phẩm từ hóa đơn bán
                for (SalesInvoiceDetail detail : buybackInvoice.getSalesInvoice().getSalesInvoiceDetails()) {
                        Product product = detail.getProduct();
                        BigDecimal buybackPrice = productService.calculateBuybackPrice(product);
                        table.addCell(product.getProductName());
                        table.addCell(String.valueOf(detail.getQuantity()));
                        table.addCell(vndFormat.format(buybackPrice));
                        table.addCell(vndFormat.format(buybackPrice.multiply(new BigDecimal(detail.getQuantity()))));
                }
                document.add(table);

                // Thêm tổng tiền mua lại
                Paragraph totalInfo = new Paragraph(
                                "Total Amount: " + vndFormat.format(buybackInvoice.getTotalAmount()),
                                infoFont);
                totalInfo.setAlignment(Element.ALIGN_RIGHT);
                document.add(totalInfo);

                document.close();
                return out.toByteArray();
        }

        // private void addTableHeader(PdfPTable table, Font font) {
        // Stream.of("Product", "Quantity", "Unit Price", "Total")
        // .forEach(columnTitle -> {
        // PdfPCell header = new PdfPCell();
        // header.setBackgroundColor(BaseColor.GRAY);
        // header.setBorderWidth(2);
        // header.setHorizontalAlignment(Element.ALIGN_CENTER);
        // header.setPhrase(new Phrase(columnTitle, font));
        // table.addCell(header);
        // });
        // }
}
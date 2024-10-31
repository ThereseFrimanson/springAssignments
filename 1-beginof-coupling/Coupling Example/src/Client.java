
public class Client {
	public static void main(String[] args){
		InvoiceService invoices = new InvoiceService();
		Invoice newInvoice = new Invoice("11075", "Bill McLarane");

		InvoicingDAO dao = new InvoicingDAOJdbcImplementation();
		invoices.setDao(dao);
		invoices.raiseInvoice(newInvoice);
	}
}

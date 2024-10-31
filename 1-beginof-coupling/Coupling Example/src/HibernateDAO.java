public class HibernateDAO implements InvoicingDAO{

    @Override
    public void save(Invoice newInvoice) {
        System.out.println("Saving an invoice using Hibernate");
    }

    @Override
    public void delete(Invoice oldInvoice) {
        //TODO auto-generated method sub
    }

    @Override
    public void update(Invoice invoiceToCancel) {
        //TODO auto-generated method sub
    }
}

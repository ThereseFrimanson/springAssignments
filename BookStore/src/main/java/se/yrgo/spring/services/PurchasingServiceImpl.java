package se.yrgo.spring.services;

import se.yrgo.spring.domain.Book;

public class PurchasingServiceImpl implements PurchasingService {
    private AccountsService accounts;
    private BookService books;


    public PurchasingServiceImpl(AccountsService accounts, BookService books){
        this.accounts = accounts;
        this.books = books;
    }
    @Override
    public void buyBook(String isbn) {
        //To find the book
        Book book = books.getBookByIsbn(isbn);
        //Raise invoice
        accounts.raiseInvoice(book);

    }

    //public void setAccountsService (AccountsService accounts) {
      //  this.accounts = accounts;
    //}

    //public void setBookService (BookService books) {
      //  this.books= books;
    //}
}

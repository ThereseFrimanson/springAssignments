package se.yrgo.spring.client;

import java.util.List;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import se.yrgo.spring.domain.Book;
import se.yrgo.spring.services.BookService;
import se.yrgo.spring.services.PurchasingService;

public class Client {
	public static void main(String[] args) {
		System.out.println("Testing buying a book...");
		String isbn = "ISBN1"; //we know that this isbn exists in the mock

		ClassPathXmlApplicationContext container = new
				ClassPathXmlApplicationContext("application.xml");

		PurchasingService purchasing = container.getBean(PurchasingService.class);

		purchasing.buyBook(isbn);
		container.close();
	}
}
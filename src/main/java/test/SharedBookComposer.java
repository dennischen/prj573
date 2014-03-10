package test;

import java.io.IOException;
import java.util.*;

import org.zkoss.zk.ui.*;
import org.zkoss.zss.api.*;
import org.zkoss.zss.api.model.Book;

public class SharedBookComposer extends UserActionComposer {
	 
    private static final long serialVersionUID = 1L;
     
    static private final Map<String,Book> sharedBook = new HashMap<String,Book>();
 
    public void doAfterCompose (Component comp) throws Exception {
    	super.doAfterCompose(comp);
    	String bookName = "blank.xlsx";
        Book book = loadBookFromAvailable(bookName);
        System.out.println("book " + book);
        ss.setBook(book);
        
        allCells = new AreaRef(0,0, ss.getMaxrows()-1, ss.getMaxcolumns()-1).asString();
    }
     
    private Book loadBookFromAvailable(String bookname){
        Book book;
        synchronized (sharedBook){
            book = sharedBook.get(bookname);
            if(book==null){
                book = importBook(bookname);
                book.setShareScope("application");
                sharedBook.put(bookname, book);
            }
        }
        return book;
    }
     
    private Book importBook(String bookname){
        Importer imp = Importers.getImporter();
        try {
            Book book = imp.imports(
                    WebApps.getCurrent().getResource("/WEB-INF/books/" + bookname),
                    bookname);
            return book;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(),e);
        }
    }
    
    
}
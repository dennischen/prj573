package test;

import java.io.IOException;
import java.util.*;

import org.zkoss.zk.ui.*;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.*;
import org.zkoss.zss.api.*;
import org.zkoss.zss.api.model.*;
import org.zkoss.zss.ui.Spreadsheet;

public class TestComposer extends SelectorComposer<Component> {
	 
    private static final long serialVersionUID = 1L;
     
    @Wire
    private Spreadsheet ss;
     
    static private final Map<String,Book> sharedBook = new HashMap<String,Book>();
 
    static private String allCells= "A1:Y400";
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
    
    @Listen("onClick = #blue")
    public void setBlue(){
    	Sheet sheet = ss.getBook().getSheetAt(0);
    	CellOperationUtil.applyBackgroundColor(Ranges.range(sheet, allCells), "#0000ff");
    }
    
    @Listen("onClick = #red")
    public void setRed(){
    	Sheet sheet = ss.getBook().getSheetAt(0);
    	CellOperationUtil.applyBackgroundColor(Ranges.range(sheet, allCells), "#ff0000");
    }
    
    @Listen("onClick = #green")
    public void setGreen(){
    	Sheet sheet = ss.getBook().getSheetAt(0);
    	CellOperationUtil.applyBackgroundColor(Ranges.range(sheet, allCells), "#00ff00");
    }
    
    
    @Listen("onClick = #filla")
    public void filla(){
    	Sheet sheet = ss.getBook().getSheetAt(0);
    	Ranges.range(sheet, allCells).setCellEditText("a");
    }
    
    @Listen("onClick = #fillb")
    public void fillb(){
    	Sheet sheet = ss.getBook().getSheetAt(0);
    	Ranges.range(sheet, allCells).setCellEditText("b");
    }
    
    @Listen("onClick = #fillc")
    public void fillc(){
    	Sheet sheet = ss.getBook().getSheetAt(0);
    	Ranges.range(sheet, allCells).setCellEditText("c");
    }
    
    @Listen("onClick = #formulaA")
    public void formulaA(){
    	Sheet sheet = ss.getBook().getSheetAt(0);
    	Ranges.range(sheet, allCells).setCellEditText("=A1");
    	Ranges.range(sheet, "A1").setCellEditText("A1");
    }
    
    @Listen("onClick = #formulaB")
    public void formulaB(){
    	Sheet sheet = ss.getBook().getSheetAt(0);
    	Ranges.range(sheet, allCells).setCellEditText("=B1");
    	Ranges.range(sheet, "B1").setCellEditText("B1");
    }
    
    @Listen("onClick = #formulaC")
    public void formulaC(){
    	Sheet sheet = ss.getBook().getSheetAt(0);
    	Ranges.range(sheet, allCells).setCellEditText("=C1");
    	Ranges.range(sheet, "C1").setCellEditText("C1");
    }
}
package test;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.*;
import org.zkoss.zss.api.*;
import org.zkoss.zss.api.model.Sheet;
import org.zkoss.zss.ui.Spreadsheet;

public class UserActionComposer extends SelectorComposer<Component> {
	
	private static final long serialVersionUID = 6348072845470791964L;
	
	@Wire
    protected Spreadsheet ss;
	protected String allCells= "A1:Y400";

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

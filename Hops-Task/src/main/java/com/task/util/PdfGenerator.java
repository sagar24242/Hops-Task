package com.task.util;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.task.model.Employee;

public class PdfGenerator {
	
	public void generate(List<Employee>  employeeList, HttpServletResponse response) throws DocumentException, IOException {
	    // Creating the Object of Document
	    Document document = new Document(PageSize.A4);
	    // Getting instance of PdfWriter
	    PdfWriter.getInstance(document, response.getOutputStream());
	    // Opening the created document to change it
	    document.open();
	    // Creating font
	    // Setting font style and size
	    Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
	    fontTiltle.setSize(20);
	    
	    
	    // Creating paragraph
	    Paragraph paragraph1 = new Paragraph("List of the Employees", fontTiltle);
	    
	    
	    
	    // Aligning the paragraph in the document
	    paragraph1.setAlignment(Paragraph.ALIGN_CENTER);
	    
	    
	    // Adding the created paragraph in the document
	    document.add(paragraph1);
	    
	    
	    // Creating a table of the 4 columns
	    PdfPTable table = new PdfPTable(5);
	    
	    
	    
	    // Setting width of the table, its columns and spacing
	    table.setWidthPercentage(100 );
	    table.setWidths(new int[] {3,3,3,3,3});
	    table.setSpacingBefore(5);
	    
	    
	    
	    
	    // Create Table Cells for the table header
	    PdfPCell cell = new PdfPCell();
	    
	    
	    
	    // Setting the background color and padding of the table cell
	    cell.setBackgroundColor(CMYKColor.BLUE);
	    cell.setPadding(5);
	    
	    
	    
	    // Creating font
	    // Setting font style and size
	    Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
	    font.setColor(CMYKColor.WHITE);
	    
	    
	    
	    
	    // Adding headings in the created table cell or  header
	    // Adding Cell to table
	    cell.setPhrase(new Phrase("ID", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("name", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("active", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("image", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("code", font));
	    table.addCell(cell);
	    
	    
	    
	    // Iterating the list of students
	    for (Employee employee: employeeList) {
	      // Adding student id
	      table.addCell(String.valueOf(employee.getId()));
	      // Adding student name
	      table.addCell(employee.getName());
	      // Adding student email
	      table.addCell(employee.getActive());
	      // Adding student mobile
	      table.addCell(employee.getImage());
	      //add to code
	      table.addCell(String.valueOf(employee.getCode()));
	    }
	    // Adding the created table to the document
	    document.add(table);
	    // Closing the document
	    document.close();
	  }

}

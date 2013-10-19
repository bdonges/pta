package bill.pta;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class TestFileWrite 
{

	public static void main(String[] args)
	{
		try
		{
			TestFileWrite tfw = new TestFileWrite();
			tfw.write();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void write() throws IOException
	{
		// instantiate workbook and sheet
		Workbook wb = new HSSFWorkbook();
	    CreationHelper createHelper = wb.getCreationHelper();
	    Sheet sheet = wb.createSheet("formatted");

	    // get entries
	    Hashtable <String,Entry> entries = getEntries();
	    
	    int rowCnt = 0;
	    
		// loop through entries and create new sheet
		Enumeration<String> en = entries.keys();
		while (en.hasMoreElements())
		{
			String key = (String)en.nextElement();
			Entry e = (Entry)entries.get(key);
			
		    // Create a row and put some cells in it. Rows are 0 based.
		    Row row = sheet.createRow((short)rowCnt);

		    // Create a cell and put a value in it.
		    row.createCell(0).setCellValue(createHelper.createRichTextString(e.getLastName()));
		    row.createCell(1).setCellValue(createHelper.createRichTextString("Students: " + e.getStudents().size()));
		    row.createCell(2).setCellValue(createHelper.createRichTextString("Guardians: " + e.getGuardians().size()));
		    row.createCell(3).setCellValue(createHelper.createRichTextString(e.getPriority()));
		    row.createCell(4).setCellValue(createHelper.createRichTextString(e.getComplex()));
		    row.createCell(5).setCellValue(createHelper.createRichTextString(e.getStreetNum()));
		    row.createCell(6).setCellValue(createHelper.createRichTextString(e.getStreetName()));
		    row.createCell(7).setCellValue(createHelper.createRichTextString(e.getStreetType()));
		    row.createCell(8).setCellValue(createHelper.createRichTextString(e.getApt()));
		    row.createCell(9).setCellValue(createHelper.createRichTextString(e.getCityStateZip()));
		    row.createCell(10).setCellValue(createHelper.createRichTextString(e.getPhone()));

		    rowCnt++;
		}
		
	    FileOutputStream fileOut = new FileOutputStream("C:\\WWW\\directory_formatted_test.xlsx");
	    wb.write(fileOut);
	    fileOut.close();
	}
	
	private Hashtable<String,Entry> getEntries()
	{
		Hashtable<String,Entry> entries = new Hashtable<String,Entry>();
		for (int i = 0; i < 100; i++)
		{
			entries.put(String.valueOf(i), new Entry("last"+i, 
					String.valueOf(i), 
					"complex"+i, 
					"snum"+i, 
					"sname"+i, 
					"stype"+i, 
					"apt"+i, 
					"roswell ga 30076", 
					i+"-111-2222"));
		}
		return entries;
	}
	
	
}

package bill.pta;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DirectoryReader 
{

	public static void main(String[] args)
	{
		try
		{
			System.out.println("started reading file at " + new java.util.Date());
			System.out.println("Working Directory = " + System.getProperty("user.dir"));			
			
			String dir = System.getProperty("user.dir") + "\\files\\2014\\";
			String file = "orig_13_14_directory_combined_columns_version.xlsx";

			//..
			FileInputStream inFile = new FileInputStream(new File(dir + file));
			             
			//Get the workbook instance for XLS file 
			XSSFWorkbook inWorkbook = new XSSFWorkbook (inFile);
			 
			//Get first sheet from the workbook
			XSSFSheet inSheet = inWorkbook.getSheetAt(0);
			 
			//Get iterator to all the rows in current sheet
			Iterator<Row> inRowIterator = inSheet.iterator();
			 
			int rowCnt = 0;
			
			Hashtable<String,Entry> entries = new Hashtable<String,Entry>();
			
			while (inRowIterator.hasNext())
			{
				Row row = inRowIterator.next();

				Entry e = new Entry();
				Student s = new Student();
				Guardian g = new Guardian();
				
				//Get iterator to all cells of current row
				Iterator<Cell> cellIterator = row.cellIterator();
				
				int cellCnt = 0;
				
				while (cellIterator.hasNext())
				{
					Cell cell = cellIterator.next();

					if (cellCnt == 0)
					{
						e.setLastName(cell.toString());
					}
					else if (cellCnt == 1)
					{
						s.setFirstName(cell.toString());
					}
					else if (cellCnt == 2)
					{
						s.setGrade(cell.toString());
					}
					else if (cellCnt == 3)
					{
						s.setTeacher(cell.toString());
					}
					else if (cellCnt == 4)
					{
						e.setComplex(cell.toString());
					}
					else if (cellCnt == 5)
					{
						e.setStreetNum(cell.toString());
					}
					else if (cellCnt == 6)
					{
						e.setStreetName(cell.toString());
					}
					else if (cellCnt == 7)
					{
						e.setStreetType(cell.toString());
					}
					else if (cellCnt == 8)
					{
						e.setApt(cell.toString());
					}
					else if (cellCnt == 9)
					{
						e.setCityStateZip(cell.toString());
					}					
					else if (cellCnt == 10)
					{
						e.setPhone(cell.toString());
					}
					else if (cellCnt == 11)
					{
						e.setPriority(cell.toString());
						g.setPriority(cell.toString());
					}
					else if (cellCnt == 12)
					{
						g.setLastName(cell.toString());
					}
					else if (cellCnt == 13)
					{
						g.setFirstName(cell.toString());
					}
					else if (cellCnt == 14)
					{
						g.setEmail(cell.toString());
					}					

					cellCnt++;
				}
				
				String key = e.getLastName() + "_" + 
							 s.getFirstName() + "_" + 
							 e.getStreetNum() + "_" + 
							 e.getStreetName() + "_" + 
							 e.getStreetType() + "_" +
							 s.getGrade();
				
				if (entries.containsKey(key))
				{
					Entry entry = (Entry)entries.get(key);
					entries.remove(key);
					
					entry.getStudents().add(s);
					entry.getGuardians().add(g);
					entries.put(key, entry);
				}
				else
				{
					e.getStudents().add(s);
					e.getGuardians().add(g);
					entries.put(key, e);
				}
				
				cellCnt = 0;
				rowCnt++;
			}

			System.out.println("done reading file and creating entries, now creating workbook at " + new java.util.Date());
			
			// instantiate workbook and sheet
			Workbook wb = new HSSFWorkbook();
		    CreationHelper createHelper = wb.getCreationHelper();
		    Sheet sheet = wb.createSheet("formatted");

		    rowCnt = 0;
		    
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

			    /*
			    System.out.println(e.getLastName() + " " + "Students: " + e.getStudents().size() + " " +
			    		"Guardians: " + e.getGuardians().size() + " " + e.getPriority() + " " +
			    		e.getComplex() + " " + e.getStreetNum() + " " +
			    		e.getStreetName() + " " + e.getStreetType() + " " +
			    		e.getApt() + " " + e.getCityStateZip() + " " + e.getPhone()); 
			    */
			    rowCnt++;
			}
			
		    FileOutputStream fileOut = new FileOutputStream(dir + "directory_formatted.xls");
		    wb.write(fileOut);
		    fileOut.close();

		    System.out.println("done at " + new java.util.Date());
			
		}
		catch (Exception e)
		{
			System.err.println("exception - message: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	
}

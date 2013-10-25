package bill.pta;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
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
			
			String os = System.getProperty("os.name").toLowerCase(); 
			String div = "/";
			if (os.indexOf("windows") > -1)
				div = "\\";
			
			String dir = System.getProperty("user.dir") + div + "files" + div + "2014" + div;
			String file = "orig_13_14_directory_combined_columns_version_all.xlsx";

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
						g.setPhone(cell.toString());
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
							 e.getStreetNum() + "_" + 
							 e.getStreetName() + "_" + 
							 e.getStreetType();
				
				if (entries.containsKey(key))
				{
					Entry entry = (Entry)entries.get(key);
					entries.remove(key);

					if (!entry.containsStudent(s.getFirstName()))
						entry.getStudents().add(s);
					
					if (!entry.containsGuardian(g.getFirstName()))
						entry.getGuardians().add(g);
					
					entries.put(key, entry);
				}
				else
				{
					e.getStudents().add(s);
					e.getGuardians().add(g);
					entries.put(key, e);
				}
				
				//Thread.sleep(1000);
				
				cellCnt = 0;
				rowCnt++;
			}

			System.out.println("done reading file and creating entries, now creating workbook at " + new java.util.Date());
			
			// instantiate workbook and sheet
			Workbook wb = new HSSFWorkbook();
		    Sheet sheet = wb.createSheet("formatted");

	        String[] keys = (String[]) entries.keySet().toArray(new String[0]);  
	        Arrays.sort(keys);  

	        rowCnt = 0;
			for(String key : keys) 
			{
				Entry e = (Entry)entries.get(key);
			    for (Student student : e.getStudents())
			    {
			    	Row row1 = sheet.createRow((short)rowCnt);	
			    	row1.createCell(0).setCellValue(e.getLastName() + ", " + student.getFirstName());
			    	rowCnt++;
			    	
			    	Row row2 = sheet.createRow((short)rowCnt);
			    	row2.createCell(0).setCellValue("Grade:");
			    	row2.createCell(1).setCellValue(student.getDisplayGrade());
			    	rowCnt++;
			    	
			    	Row row3 = sheet.createRow((short)rowCnt);
			    	row3.createCell(0).setCellValue("Teacher:");
			    	row3.createCell(1).setCellValue(student.getTeacher());
			    	rowCnt++;
			    }
			    
		    	Row row4 = sheet.createRow((short)rowCnt);
		    	row4.createCell(0).setCellValue("Address:");
		    	row4.createCell(1).setCellValue(e.getStreetNum() + " " + e.getStreetName() + " " + e.getStreetType());
		    	rowCnt++;

		    	if (e.getApt() != null && !e.getApt().equals(""))
		    	{
		    		Row row45 = sheet.createRow((short)rowCnt);
		    		row45.createCell(0).setCellValue(" ");
		    		row45.createCell(1).setCellValue(e.getApt());
		    		rowCnt++;
		    	}
		    	
		    	Row row5 = sheet.createRow((short)rowCnt);
	    		row5.createCell(0).setCellValue("Phone:");
		    	row5.createCell(1).setCellValue(e.getFirstPriority().getPhone());
		    	rowCnt++;		    		
		    	
		    	if (e.getSecondPriority() != null && e.getSecondPriority().getPhone() != null)
		    	{
		    		if (!e.getFirstPriority().getPhone().equals(e.getSecondPriority().getPhone()))
		    		{
			    		Row row55 = sheet.createRow((short)rowCnt);
			    		row55.createCell(0).setCellValue(" ");
				    	row55.createCell(1).setCellValue(e.getSecondPriority().getPhone());
				    	rowCnt++;
		    		}
		    	}		    	
		    	
		    	Row row6 = sheet.createRow((short)rowCnt);
	    		row6.createCell(0).setCellValue("Parents:");
		    	row6.createCell(1).setCellValue(e.getFirstPriority().getLastName() + ", " + e.getFirstPriority().getFirstName());
		    	rowCnt++;		    		
		    	
		    	if (e.getSecondPriority() != null && e.getSecondPriority().getFirstName() != null)
		    	{
		    		Row row65 = sheet.createRow((short)rowCnt);
		    		row65.createCell(0).setCellValue(" ");
			    	row65.createCell(1).setCellValue(e.getSecondPriority().getLastName() + ", " + e.getSecondPriority().getFirstName());
			    	rowCnt++;		    		
		    	}
		    	
		    	if (e.getFirstPriority().getEmail() != null && e.getFirstPriority().getEmail().length() > 0)
		    	{
			    	Row row7 = sheet.createRow((short)rowCnt);
		    		row7.createCell(0).setCellValue("email:");
			    	row7.createCell(1).setCellValue(e.getFirstPriority().getEmail());
			    	rowCnt++;
		    	}
		    	
		    	if (e.getSecondPriority() != null && 
		    			e.getSecondPriority().getEmail() != null && 
		    			e.getFirstPriority().getEmail() != null && 
		    			e.getFirstPriority().getEmail().length() > 0)
		    	{
		    		Row row75 = sheet.createRow((short)rowCnt);
		    		row75.createCell(0).setCellValue(" ");
			    	row75.createCell(1).setCellValue(e.getSecondPriority().getEmail());
			    	rowCnt++;		    		
		    	}
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
	
	public void work()
	{
		/*
		// Create a row and put some cells in it. Rows are 0 based.
	    Row row = sheet.createRow((short)rowCnt);

	    // Create a cell and put a value in it.
	    row.createCell(0).setCellValue(createHelper.createRichTextString(e.getLastName()));
	    
	    String students = "";
	    for (Student student : e.getStudents())
	    	students = students + student.getFirstName() + " " + student.getDisplayGrade() + ",";
	    //students = students.substring(0, students.length()-1);
	    
	    String guardians = "";
	    for (Guardian guardian : e.getGuardians())
	    	guardians = guardians + guardian.getFirstName() + " " + guardian.getLastName() + ",";
	    //guardians = guardians.substring(0, students.length()-1);			    
	    
	    row.createCell(1).setCellValue(createHelper.createRichTextString(students));
	    row.createCell(2).setCellValue(createHelper.createRichTextString(guardians));
	    row.createCell(3).setCellValue(createHelper.createRichTextString(e.getPriority()));
	    row.createCell(4).setCellValue(createHelper.createRichTextString(e.getComplex()));
	    row.createCell(5).setCellValue(createHelper.createRichTextString(e.getStreetNum()));
	    row.createCell(6).setCellValue(createHelper.createRichTextString(e.getStreetName()));
	    row.createCell(7).setCellValue(createHelper.createRichTextString(e.getStreetType()));
	    row.createCell(8).setCellValue(createHelper.createRichTextString(e.getApt()));
	    row.createCell(9).setCellValue(createHelper.createRichTextString(e.getCityStateZip()));
	    row.createCell(10).setCellValue(createHelper.createRichTextString(e.getPhone()));
	    
	    
	    System.out.println(e.getLastName() + " " + "Students: " + students + " " +
	    		"Guardians: " + guardians + " " + e.getPriority() + " " +
	    		e.getComplex() + " " + e.getStreetNum() + " " +
	    		e.getStreetName() + " " + e.getStreetType() + " " +
	    		e.getApt() + " " + e.getCityStateZip() + " " + e.getPhone()); 
	    
	    rowCnt++;
	    */
	}

}

package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Utils {

	public static Date validFecha(String msg) {
		  Scanner sc = new Scanner (System.in);
		  Date result = null;
		  boolean valid = false;
		  SimpleDateFormat sdfrmt = new SimpleDateFormat("dd-MM-yyyy");
		  sdfrmt.setLenient(false);
		  do {
			 
			try {
				 System.out.print(msg);
				 String fechaInicio = sc.nextLine();
				 result = sdfrmt.parse(fechaInicio);
				 valid = true;
			} catch (ParseException e) {
				 System.out.println("Error no has introducido la fecha correctamente");
			} catch (Exception e) {
				System.out.println("Error. No has introducido una cadena de caracteres");
				sc.nextLine();
			}
		} while (!valid);
		  return result;
	  }
}

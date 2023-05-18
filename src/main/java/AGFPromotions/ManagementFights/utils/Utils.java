package AGFPromotions.ManagementFights.utils;

import java.security.MessageDigest;
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
	
	public static String encryptSHA256 (String s){
        String result = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA256");
            md.update(s.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte aByte : md.digest()) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            result = sb.toString();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
}

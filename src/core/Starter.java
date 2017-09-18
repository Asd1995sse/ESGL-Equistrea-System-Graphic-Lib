package core;

public class Starter {
	 static double disk = 1.44; //мб
	 static double v3disk = 24; //кубмм
	 static double mdisk = 4; //граммы	 
	 public static void main(String []args) {
		 System.out.println("Equestria System Graphic Lib_v1.0... Init");
		 calcul();
		 
			 
		 }
		 
	 public static Object calcul(){
		 double fz = 60;
		 double mbfz = fz*1024;
		 double xernia = mbfz / disk;
		 System.out.println(xernia + " - Количество дискет");
		 double v3all = v3disk*xernia;
		 System.out.println(v3all + " - Объем дискет");
		 double massa = xernia*mdisk;
		 System.out.println(massa + " - Вес дискет");
		return null;		 
		 
		
	 }	 
		 
	      
	     // pony();
	   
	/*** public static Object multivibrator(){
		 return pony();
	 }
	 public static Object pony(){
		 System.out.println("Пони для девочек! азаза!");
		return multivibrator();
		 
	 }
	 ***/
}


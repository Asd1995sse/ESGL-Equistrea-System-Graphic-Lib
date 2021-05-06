package ru.asd1995sse.main;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Core {
	
static //матрица 1
int P[][];
static Core instanse = new Core();	

//самый важный функция
public static void main(String[] args) {
	parsePng(instanse.inputfolder);	
}
//Вход и выход
private static File inputfolder;
private static File ouputfolder;
public Core() {
	inputfolder = new File(System.getProperty("user.dir") + File.separator + "input" );
	inputfolder.mkdir();
	ouputfolder = new File(System.getProperty("user.dir") + File.separator + "output");
	ouputfolder.mkdir();	
}

static int height = 0;
static int width = 0;
static String strFinalC = "";
static String strFinalH = "";
static private void parsePng(File in) {
	String[] filepatch = in.list();
	 File outputfileC = new File(ouputfolder + File.separator + "matrix.c" );
	 File outputfileH = new File(ouputfolder + File.separator + "matrix.h" );
	    System.out.println(outputfileC);	
	    System.out.println(outputfileH);
	   try {
		    outputfileH.delete();
			outputfileH.createNewFile();
	    	outputfileC.delete();
			outputfileC.createNewFile();
		} catch (IOException e1) {
			System.out.println("Все внезапно перестало работать, что вызвало у меня большие негативные эмоции!" + e1);
			e1.printStackTrace();
		}
	   strFinalC = MatrixContent.matrixCheader;
	   strFinalH = MatrixContent.matrixHheader;
	for(String path:filepatch) {
    	File images = new File(in + File.separator + path);
    	try {
    		BufferedImage bi = ImageIO.read(images);
    		height = bi.getHeight();
    		width = bi.getWidth();
    		P = new int[height][width];
    		String str = "";
    		strFinalC = strFinalC + "const int " + images.getName().replaceFirst("[.][^.]+$", "") + "["+height+"]"+"["+width+"]"+" = "+"{" +"\n";
    		strFinalH = strFinalH + "const int " + images.getName().replaceFirst("[.][^.]+$", "") + "["+height+"]"+"["+width+"];\n";
    		//циклы чтения файла попиксельно
    		for (int x=0; x<height; x++) {
    			
				for(int y=0; y<width; y++) {
    				Color pixels = new Color(bi.getRGB(y, x));
    				if(pixels.getRed()>1 && pixels.getBlue()>1 && pixels.getGreen()>1) {
    					P[x][y] = 0;
    				}else {
    					P[x][y] = 1;
    				}
    				if(y==0) {
    					str = "{"+P[x][y]+",";
    				} else if(y<(width-1)) {
    					str = str+P[x][y]+",";
    				} else if(y==(width-1)) {
    					str = str+P[x][y]+"}";
    				}
    			}
    			if(x<(height-1)){
    				str = str+","+"\n";
    			}else if(x==(height-1)) {
    				str = str+"\n";
    			} 
    			strFinalC = strFinalC+str ;
    			str = "";
    		}
    		strFinalC = strFinalC+"};\n\r";    		
    	} catch (IOException e) {		
    		e.printStackTrace();
    	}
    	
    	//strFinalH = strFinalH+MatrixContent.matrixHheaderEnd;
    	FileOutputStream fos;
		try {
			fos = new FileOutputStream(outputfileC);
			fos.write(strFinalC.getBytes());
			fos.flush();
			fos = new FileOutputStream(outputfileH);
			fos.write(strFinalH.getBytes());
			fos.flush();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	
        
	}	
}
}
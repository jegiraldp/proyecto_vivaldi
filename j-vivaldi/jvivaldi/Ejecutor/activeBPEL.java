package Ejecutor;

import java.io.*;
import javax.swing.JOptionPane;

/**
 * Esta clase configura el ambiente web para ActiveBpel
 * @author Universidad Nacional de Colombia, Escuela de Sistemas - GIDIA
 */
public class activeBPEL {
	
	    FileWriter fwBAT;
		
	    public void inicio() throws IOException{
		
		try{
		
		fwBAT = new FileWriter (".\\activebpel\\config.bat");
		PrintWriter pw = new PrintWriter (fwBAT);
		pw.println("jar cf sw.bpr wsdl bpel *.pdd META-INF");
		pw.println("copy sw.bpr "+"\""+"%CATALINA_HOME%/bpr"+"\"");
		pw.flush();	
		pw.close();
		
		Process p= Runtime.getRuntime().exec ("cmd /c .\\activebpel\\config.bat");
		System.out.println("Ejecutado config de ActiveBPEL");
		
		JOptionPane.showMessageDialog(null, "Acceda al WSDL del servicio escribiendo lo siguiente en su navegador web:"+"\n"+
				"http://localhost:8080/active-bpel/services/swServiceService?wsdl"
					,"Ejecución servicio compuesto = Atencion", JOptionPane.WARNING_MESSAGE);
		System.out.println("http://localhost:8080/active-bpel/services/swServiceService?wsdl");
		System.out.println("I'LL BE BACK !");
		}catch(IOException ioex)
		{System.out.println("Error construyento BAT");}
		 
		
		}//finalizaBAT()
		
		}//clase
	



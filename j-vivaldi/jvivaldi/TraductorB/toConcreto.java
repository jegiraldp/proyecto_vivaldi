
package TraductorB;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Hashtable;
import javax.swing.JOptionPane;
import TraductorB.literales;
import TraductorB.literalFinal;
import TraductorB.bpel;



/**
 * Ayuda a generar el archivo BPEL final-concreto
 * @author Universidad Nacional de Colombia, Escuela de Sistemas - GIDIA
 */
public class toConcreto {
	BufferedReader entrada;
	String servicio,linea;
	public String nombre="nombre";
	public String persona="persona";
	FileWriter fw;
	String nombreServicio1,nombreServicio2,nombreServicio3,nombreServicio4;
	String ayudanombreServicio1,ayudanombreServicio2,ayudanombreServicio3,ayudanombreServicio4;
	String operacionServicio1,operacionServicio2,operacionServicio3,operacionServicio4;
	String entradaServicio1,entradaServicio2,entradaServicio3,entradaServicio4;
	String ValorentradaServicio1,ValorentradaServicio2,ValorentradaServicio3,ValorentradaServicio4;
	String literal1,literal2,literal3;
	public bpel objeto_bpel = new bpel();
	public literales objeto_literales = new literales();
	public literalFinal objeto_literalFinal = new literalFinal();

	/**
	 * @param sw1 Plantilla servicio
	 * @param sw2 Plantilla servicio
	 * @param sw3 Plantilla servicio
	 * @param sw4 Plantilla servicio
	 * @throws IOException
	 */
	public void recorrerSecuencia(Hashtable sw1,Hashtable sw2,Hashtable sw3,Hashtable sw4)throws IOException{
		
		ayudanombreServicio1=sw1.get("nombre").toString();
		ayudanombreServicio2=sw2.get("nombre").toString();
		ayudanombreServicio3=sw3.get("nombre").toString();
		nombreServicio1=ayudanombreServicio1.trim();
		nombreServicio2=ayudanombreServicio2.trim();
		nombreServicio3=ayudanombreServicio3.trim();
		operacionServicio1=sw1.get("operaciones").toString();
		operacionServicio2=sw2.get("operaciones").toString();
		operacionServicio3=sw3.get("operaciones").toString();
		entradaServicio1=sw1.get("entradas").toString();
		entradaServicio2=sw2.get("entradas").toString();
		entradaServicio3=sw3.get("entradas").toString();
				
		entrada = new BufferedReader(new FileReader (".\\plan.txt"));
		entrada.readLine();
		linea=entrada.readLine();
		if(linea.equals(nombreServicio1)){
			ValorentradaServicio1=JOptionPane.showInputDialog ("Digite el valor de "+entradaServicio1);
			objeto_literales.capturarLiteral(nombreServicio1,operacionServicio1,entradaServicio1,ValorentradaServicio1);
			literal1=objeto_literales.getSalida();
			ValorentradaServicio2=JOptionPane.showInputDialog ("Digite el valor de "+entradaServicio2);
			objeto_literales.capturarLiteral(nombreServicio2,operacionServicio2,entradaServicio2,ValorentradaServicio2);
			literal2=objeto_literales.getSalida();
			objeto_literalFinal.capturarLiteral(nombreServicio3,operacionServicio3,nombre,persona,literal1,literal2);
			literal3=objeto_literalFinal.getSalida();
			//System.out.println(literal3);
			}else System.out.println("No corresponde a la secuencia planteada");
	
		try{
		objeto_bpel.construirBPELActive(objeto_bpel.contenido(literal3),".\\activebpel\\bpel\\sw.bpel");
		}catch(Exception e){e.printStackTrace();}//fin catch
	
	}

}

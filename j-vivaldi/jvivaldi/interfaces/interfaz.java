package interfaces;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.Hashtable;

import Compositor.pddlProblema;
import Compositor.Planificar;
import TraductorA.pddlDominio;
import TraductorA.RecolectorOWLS;

/**
 * Esta clase construye y controla la interfaz
 * @author Universidad Nacional de Colombia, Escuela de Sistemas - GIDIA
 */
public class interfaz  extends JFrame{
	
	 static interfaz ven = new interfaz();
	 public String[] serviciosThis,serviciosThis2; 
	 public pddlProblema obj_pddlproblema = new pddlProblema();
	 public pddlDominio obj_pddlDominio = new pddlDominio();
	 public RecolectorOWLS objeto_recolector;
	 public Planificar objeto_planificar = new Planificar();
	 //public interfaz obj;
	 
	 public Hashtable objetivosThis = new Hashtable();
	 public Hashtable condicionesThis = new Hashtable();
     public Hashtable operadoresThis = new Hashtable();
	 	 
	 public int numObjetivos = 1, numCondiciones = 1;
	 public int numObjetivosBorrar = 1;
	 public int numCondicionesBorrar = 1;
	 
	 public JLabel EtiquetaObj2;
	 public JLabel EtiquetaObj3;
	 public JLabel EtiquetaObj4;
	 public JLabel EtiquetaObj5;
	 public JLabel EtiquetaCondi1;
	 public JLabel EtiquetaCondi2;
	 public JLabel EtiquetaCondi3;
	 public JLabel EtiquetaCondi4;
	 
	 
	 public JTextField CampoTextoObj1;
	 public JTextField CampoTextoObj2;
	 public JTextField CampoTextoObj3;
	 public JTextField CampoTextoObj4;
	 public JTextField CampoTextoObj5;
	 public JTextField CampoTextoCond1;
	 public JTextField CampoTextoCond2;
	 public JTextField CampoTextoCond3;
	 public JTextField CampoTextoCond4;
	 
	 public String operadores[] = { "-","and", "or", "not"};
	 public JComboBox CmbBoxOperadores1;
	 public JComboBox CmbBoxOperadores2;
	 public JComboBox CmbBoxOperadores3;
	 public JComboBox CmbBoxOperadores4;
	 
	 public FileWriter fw, fwLimpiar,fwLimpiar2;
	 public String objetivos2[];
	 boolean nuevaLinea = true;
	 public static String hola;
	 public String operador1,operador2,operador3,operador4;
	 public String textoCampoTextoCond1,textoCampoTextoCond2,textoCampoTextoCond3,textoCampoTextoCond4;
	 public String textoCmbBoxOperadores1,textoCmbBoxOperadores2,textoCmbBoxOperadores3,textoCmbBoxOperadores4;
	 public String textoCampoTextoObj1,textoCampoTextoObj2,textoCampoTextoObj3,textoCampoTextoObj4,textoCampoTextoObj5;
	 
	 /**
	  * Constructor de la clase
	 */
	
	public interfaz()
	{
		super("CompositorSW-UN");
		
		//CompoBox Operadores
		CmbBoxOperadores1 = new JComboBox (operadores);
		CmbBoxOperadores1.setBounds(120,105,60,20);
		CmbBoxOperadores1.setVisible(false);
		CmbBoxOperadores1.setEnabled(false);
		
		CmbBoxOperadores2 = new JComboBox (operadores);
		CmbBoxOperadores2.setBounds(120,155,60,20);
		CmbBoxOperadores2.setVisible(false);

		CmbBoxOperadores3 = new JComboBox (operadores);
		CmbBoxOperadores3.setBounds(120,205,60,20);
		CmbBoxOperadores3.setVisible(false);
		
		CmbBoxOperadores4 = new JComboBox (operadores);
		CmbBoxOperadores4.setBounds(120,255,60,20);
		CmbBoxOperadores4.setVisible(false);
		
		
		
		//Etiquetas
		JLabel Etiqueta = new JLabel ("SISTEMA DE COMPOSICIÓN AUTOMÁTICA");
		Etiqueta.setBounds(140,10,300,30);
		
		JLabel Etiqueta2 = new JLabel ("Definición del problema:");
		Etiqueta2.setBounds(40,50,200,30);
		
		JLabel EtiquetaCondiciones = new JLabel ("Condiciones Iniciales:");
		EtiquetaCondiciones.setBounds(300,50,200,30);
					
		JLabel EtiquetaObj1 = new JLabel ("OBJETIVO :");
		EtiquetaObj1.setBounds(40,80,80,20);
		
		EtiquetaObj2 = new JLabel ("OBJETIVO :");
		EtiquetaObj2.setBounds(40,130,80,20);
		EtiquetaObj2.setVisible(false);
		
		EtiquetaObj3 = new JLabel ("OBJETIVO :");
		EtiquetaObj3.setBounds(40,180,80,20);
		EtiquetaObj3.setVisible(false);
		
		EtiquetaObj4 = new JLabel ("OBJETIVO :");
		EtiquetaObj4.setBounds(40,230,80,20);
		EtiquetaObj4.setVisible(false);
		
		EtiquetaObj5 = new JLabel ("OBJETIVO :");
		EtiquetaObj5.setBounds(40,280,80,20);
		EtiquetaObj5.setVisible(false);
		
		EtiquetaCondi1 = new JLabel ("CONDICIÓN :");
		EtiquetaCondi1.setBounds(300,80,80,20);
		EtiquetaCondi1.setVisible(false);
		
		EtiquetaCondi2 = new JLabel ("CONDICIÓN :");
		EtiquetaCondi2.setBounds(300,130,80,20);
		EtiquetaCondi2.setVisible(false);
		
		EtiquetaCondi3 = new JLabel ("CONDICIÓN :");
		EtiquetaCondi3.setBounds(300,180,80,20);
		EtiquetaCondi3.setVisible(false);
		
		EtiquetaCondi4 = new JLabel ("CONDICIÓN :");
		EtiquetaCondi4.setBounds(300,230,80,20);
		EtiquetaCondi4.setVisible(false);
			
		
		//campos de texto
		CampoTextoObj1 = new JTextField(50);
		CampoTextoObj1.setBounds(120,80,150,20);
		CampoTextoObj1.setText("");
		
		CampoTextoObj2 = new JTextField(50);
		CampoTextoObj2.setBounds(120,130,150,20);
		CampoTextoObj2.setText("");
		CampoTextoObj2.setVisible(false);
		
		CampoTextoObj3 = new JTextField(50);
		CampoTextoObj3.setBounds(120,180,150,20);
		CampoTextoObj3.setText("");
		CampoTextoObj3.setVisible(false);
		
		CampoTextoObj4 = new JTextField(50);
		CampoTextoObj4.setBounds(120,230,150,20);
		CampoTextoObj4.setText("");
		CampoTextoObj4.setVisible(false);
		
		CampoTextoObj5 = new JTextField(50);
		CampoTextoObj5.setBounds(120,280,150,20);
		CampoTextoObj5.setText("");
		CampoTextoObj5.setVisible(false);
		
		CampoTextoCond1 = new JTextField(50);
		CampoTextoCond1.setBounds(300,105,150,20);
		CampoTextoCond1.setVisible(false);
		
		CampoTextoCond2 = new JTextField(50);
		CampoTextoCond2.setBounds(300,155,150,20);
		CampoTextoCond2.setVisible(false);
		
		CampoTextoCond3 = new JTextField(50);
		CampoTextoCond3.setBounds(300,205,150,20);
		CampoTextoCond3.setVisible(false);
		
		CampoTextoCond4 = new JTextField(50);
		CampoTextoCond4.setBounds(300,255,150,20);
		CampoTextoCond4.setVisible(false);
		
		
		//Botones
		//Boton Nuevo Objetivo
		JButton BotonNuevoObj = new JButton ("Nuevo Objetivo",  new ImageIcon(".\\imagenes\\ok1.gif"));
		BotonNuevoObj.setBounds(100,320,160,25);
		BotonNuevoObj.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent aevent){
				
				numObjetivos++;
				//System.out.println(numObjetivos);
				if(numObjetivos==2){EtiquetaObj2.setVisible(true);CampoTextoObj2.setVisible(true);CmbBoxOperadores1.setVisible(true);CmbBoxOperadores1.setEnabled(true);}
				if(numObjetivos==3){EtiquetaObj3.setVisible(true);CampoTextoObj3.setVisible(true);CmbBoxOperadores2.setVisible(true);}
				if(numObjetivos==4){EtiquetaObj4.setVisible(true);CampoTextoObj4.setVisible(true);CmbBoxOperadores3.setVisible(true);}
				if(numObjetivos==5){EtiquetaObj5.setVisible(true);CampoTextoObj5.setVisible(true);CmbBoxOperadores4.setVisible(true);}
				if(numObjetivos>5)System.out.println("Máximo cinco objetivos");
					
			}});
		
		//Boton Eliminar Objetivo
		JButton BotonEliminarObj = new JButton ("Eliminar Objetivo", new ImageIcon(".\\imagenes\\del.gif"));
		BotonEliminarObj.setBounds(100,360,160,25);
		BotonEliminarObj.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent aevent){
				
				numObjetivosBorrar=numObjetivos;
				if (numObjetivosBorrar>1){				
				numObjetivos--;
				if(numObjetivosBorrar==2){EtiquetaObj2.setVisible(false);CampoTextoObj2.setVisible(false);CmbBoxOperadores1.setVisible(false);}
				if(numObjetivosBorrar==3){EtiquetaObj3.setVisible(false);CampoTextoObj3.setVisible(false);CmbBoxOperadores2.setVisible(false);}
				if(numObjetivosBorrar==4){EtiquetaObj4.setVisible(false);CampoTextoObj4.setVisible(false);CmbBoxOperadores3.setVisible(false);}
				if(numObjetivosBorrar==5){EtiquetaObj5.setVisible(false);CampoTextoObj5.setVisible(false);CmbBoxOperadores4.setVisible(false);}
				}
				else{System.out.println("Un objetivo como mínimo");}
				}});
		
		//Boton Nueva condición Inicial
		JButton BotonNuevoCondicionI = new JButton ("Nueva Condición",  new ImageIcon(".\\imagenes\\ok1.gif"));
		BotonNuevoCondicionI.setBounds(300,320,160,25);
		BotonNuevoCondicionI.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent aevent){
				
				numCondiciones++;
				//System.out.println(numCondiciones);
				if(numCondiciones==2){EtiquetaCondi1.setVisible(true);CampoTextoCond1.setVisible(true);}
				if(numCondiciones==3){EtiquetaCondi2.setVisible(true);CampoTextoCond2.setVisible(true);}
				if(numCondiciones==4){EtiquetaCondi3.setVisible(true);CampoTextoCond3.setVisible(true);}
				if(numCondiciones==5){EtiquetaCondi4.setVisible(true);CampoTextoCond4.setVisible(true);}
				if(numCondiciones>5)System.out.println("Máximo cuatro condiciones iniciales");
							
		}});
		//Boton Eliminar Condición Inicial
		JButton BotonEliminarCondicionI = new JButton ("Eliminar Condición", new ImageIcon(".\\imagenes\\del.gif"));
		BotonEliminarCondicionI.setBounds(300,360,160,25);
		BotonEliminarCondicionI.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent aevent){
				numCondicionesBorrar=numCondiciones;
				//System.out.println(numObjetivosBorrar);
				if (numCondicionesBorrar>1){				
					numCondiciones--;
				if(numCondicionesBorrar==2){EtiquetaCondi1.setVisible(false);CampoTextoCond1.setVisible(false);}
				if(numCondicionesBorrar==3){EtiquetaCondi2.setVisible(false);CampoTextoCond2.setVisible(false);}
				if(numCondicionesBorrar==4){EtiquetaCondi3.setVisible(false);CampoTextoCond3.setVisible(false);}
				if(numCondicionesBorrar==5){EtiquetaCondi4.setVisible(false);CampoTextoCond4.setVisible(false);}
				}
				else{System.out.println("Condiciones igual a cero");}
		}}	
		);
		
		//Boton componer
		JButton BotonComponer = new JButton ("Componer", new ImageIcon(".\\imagenes\\componer.gif"));
		BotonComponer.setBounds(200,400,160,25);
		BotonComponer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent aevent){
				
				textoCampoTextoObj1 = CampoTextoObj1.getText();
				textoCampoTextoObj2 = CampoTextoObj2.getText();
				textoCampoTextoObj3 = CampoTextoObj3.getText();
				textoCampoTextoObj4 = CampoTextoObj4.getText();
				textoCampoTextoObj5 = CampoTextoObj5.getText();
				
				textoCampoTextoCond1 = CampoTextoCond1.getText();
				textoCampoTextoCond2 = CampoTextoCond2.getText();
				textoCampoTextoCond3 = CampoTextoCond3.getText();
				textoCampoTextoCond4 = CampoTextoCond4.getText();
				
				textoCmbBoxOperadores1 = CmbBoxOperadores1.getSelectedItem().toString();
				textoCmbBoxOperadores2 = CmbBoxOperadores2.getSelectedItem().toString();
				textoCmbBoxOperadores3 = CmbBoxOperadores3.getSelectedItem().toString();
				textoCmbBoxOperadores4 = CmbBoxOperadores4.getSelectedItem().toString();
							
				try{
				escribir("!--CompositorSW-UN-Derechos Reservados");
				escribir("objetivos");
				escribir(textoCampoTextoObj1);
				escribir(textoCmbBoxOperadores1);
				escribir(textoCampoTextoObj2);
				escribir(textoCmbBoxOperadores2);
				escribir(textoCampoTextoObj3);
				escribir(textoCmbBoxOperadores3);
				escribir(textoCampoTextoObj4);
				escribir(textoCmbBoxOperadores4);
				escribir(textoCampoTextoObj5);
				escribir("condiciones");
				escribir(textoCampoTextoCond1);
				escribir(textoCampoTextoCond2);
				escribir(textoCampoTextoCond3);
				escribir(textoCampoTextoCond4);
					
				}catch(IOException ioex)
				{System.out.println("No se puede escribir en TemporalProblema");}
				try{
				obj_pddlproblema.RecuperarDatosInterfaz(numObjetivos,numCondiciones);
				obj_pddlproblema.contruir_archivo(numObjetivos,numCondiciones);
				obj_pddlDominio.filtro();
				serviciosThis=obj_pddlDominio.getServicios();
				serviciosThis2=obj_pddlDominio.getServicios2();
				objetivosThis = obj_pddlproblema.getObjetivos();
				operadoresThis = obj_pddlproblema.getOperadores();
				condicionesThis = obj_pddlproblema.getCondiciones();
				for (int i=0; i<serviciosThis.length;i++){
				objeto_recolector = new RecolectorOWLS(".\\Repositorio\\"+serviciosThis[i]);
				}
				obj_pddlDominio.construir_Dominio(numCondiciones);
				//limpiarTemporales();
				objeto_planificar.ejecutarBAT();
				objeto_planificar.recolectarInformacionProceso(numObjetivos,numCondiciones,serviciosThis2,objetivosThis);
				limpiar();
				
				}catch(IOException ioex)
				{System.out.println("No se puede escribir en TemporalProblema");}
		}});
		
		add(BotonNuevoObj);
		add(BotonEliminarObj);
		add(BotonNuevoCondicionI);
		add(BotonEliminarCondicionI);
		add(BotonComponer);
		add(Etiqueta);
		add(EtiquetaCondiciones);
		add(EtiquetaCondi1);
		add(EtiquetaCondi2);
		add(EtiquetaCondi3);
		add(EtiquetaCondi4);
		add(Etiqueta2);
		add(EtiquetaObj1);
		add(EtiquetaObj2);
		add(EtiquetaObj3);
		add(EtiquetaObj4);
		add(EtiquetaObj5);
		add(CampoTextoObj1);
		add(CampoTextoObj2);
		add(CampoTextoObj3);
		add(CampoTextoObj4);
		add(CampoTextoObj5);
		add(CampoTextoCond1);
		add(CampoTextoCond2);
		add(CampoTextoCond3);
		add(CampoTextoCond4);
		add(CmbBoxOperadores1);
		add(CmbBoxOperadores2);
		add(CmbBoxOperadores3);
		add(CmbBoxOperadores4);
		
					
		setLayout(null);
		setSize(550,570);
		setVisible (true);
		
	}
	
	/**
	 * El main
	 */
	public static void main (String arg[])
	{
		ven.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		ven.setLocation(300,200);
		
	}
	
	//escribe 
	/**
	 * @param algo String a escribir
	 */
	public void escribir(String algo)throws IOException{
		
		fw = new FileWriter (".\\temporales\\TemporalProblema.txt",nuevaLinea);
		PrintWriter pw = new PrintWriter (fw);
		
		if(algo.equals("0"))algo="---";
		if(algo.equals("1"))algo="and";
		if(algo.equals("2"))algo="or";
		if(algo.equals("3"))algo="not";	
		pw.println(algo);
		pw.close();		
	}
	/**
	 * Limpia archivos temporales
	 * @throws IOException
	 */
	public void limpiar()throws IOException{
		try{
        	
        	fwLimpiar = new FileWriter (".\\temporales\\TemporalDominio.txt");
			fwLimpiar2 = new FileWriter (".\\temporales\\TemporalPredicados.txt");
		}catch(IOException ioex){};
	}
	
}

	
	
	



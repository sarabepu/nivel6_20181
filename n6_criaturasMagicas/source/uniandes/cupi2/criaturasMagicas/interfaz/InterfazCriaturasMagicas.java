package uniandes.cupi2.criaturasMagicas.interfaz;

import java.awt.*; 
import java.io.File;
import java.util.ArrayList;

import javax.swing.*;
import uniandes.cupi2.criaturasMagicas.mundo.CriaturasMagicas;

public class InterfazCriaturasMagicas extends JFrame {


	private CriaturasMagicas principal;
	private PanelBanner panelBanner;
	private PanelInformacionJuego panelInfo;
	private PanelMapa panelMapa;
	private PanelEnciclopedia panelEnciclopedia;
	private PanelConsultas panelConsultas;
	private PanelOpciones panelOpciones;
	private CriaturasMagicas mundo;
	private File tablero;

	public InterfazCriaturasMagicas()
	{

		try 
		{
			mundo = new CriaturasMagicas();
			System.out.println("normal: " +mundo.darPuntaje());
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		setTitle("Criaturas Magicas");
		getContentPane( ).setBackground( new Color(48, 41, 84) );
		setLayout(new BorderLayout());
		setSize(784, 750);
		setResizable(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		JPanel panelNorte = new JPanel();
		panelNorte.setLayout(new BorderLayout());

		panelBanner = new PanelBanner();
		panelInfo = new PanelInformacionJuego(this);

		panelBanner.setOpaque( false );
		panelInfo.setOpaque( false );

		panelNorte.add(panelBanner, BorderLayout.NORTH);
		panelNorte.add(panelInfo, BorderLayout.SOUTH);

		panelNorte.setOpaque(false);
		add(panelNorte, BorderLayout.NORTH);

		panelMapa = new PanelMapa(this);
		panelMapa.setOpaque(false);
		panelMapa.setPreferredSize(new Dimension(552, 450));
		add(panelMapa, BorderLayout.WEST);

		panelEnciclopedia = new PanelEnciclopedia(this, mundo.darActual());
		panelEnciclopedia.setOpaque(false);
		add(panelEnciclopedia, BorderLayout.EAST);

		JPanel panelSur = new JPanel();
		panelSur.setLayout(new BorderLayout());
		panelConsultas = new PanelConsultas(this);
		panelConsultas.setOpaque(false);

		panelOpciones = new PanelOpciones(this);
		panelOpciones.setOpaque(false);

		panelSur.add(panelConsultas, BorderLayout.NORTH);
		panelSur.add(panelOpciones, BorderLayout.SOUTH);

		panelSur.setOpaque(false);
		add(panelSur, BorderLayout.SOUTH);

	}
	public void pedirTablero(){  

		JFileChooser fc = new JFileChooser( "./data" );
		fc.setDialogTitle( "Seleccionar mapa" );
		int resultado = fc.showOpenDialog( this );
		if( resultado == JFileChooser.APPROVE_OPTION )
		{
			tablero = fc.getSelectedFile( );
			System.out.println(tablero);
			try
			{	        

				mundo.tablero(tablero);
			}
			catch (Exception e) 
			{
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, "Formato inv�lido", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}


	public void cambiarSiguiente() 
	{
		try {
			panelEnciclopedia.refrescar(mundo.darSiguiente());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog( this, e.getMessage(), "cambiar siguiente", JOptionPane.INFORMATION_MESSAGE );
		}
	}

	public void cambiarAnterior() 
	{
		try 
		{
			panelEnciclopedia.refrescar(mundo.darAnterior());
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog( this, e.getMessage(), "cambiar anterior", JOptionPane.INFORMATION_MESSAGE );
		}
	}

	public void cargar() 
	{
		
		pedirTablero();
		panelMapa.cargar(mundo.darTablero());
		panelInfo.cargar(mundo.darPuntaje()+"", mundo.darMovimientosRestantes()+"");
	}

	public void reiniciar()
	{
		try {
			mundo.cargarTablero(tablero);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		panelMapa.cargar(mundo.darTablero());;
		panelInfo.cargar(mundo.darPuntaje()+"", mundo.darMovimientosRestantes()+"");
	}

	public void criaturasFila() 
	{
		String input = JOptionPane.showInputDialog( this, "Ingresa la fila", "Dar criaturas por fila", JOptionPane.QUESTION_MESSAGE );
		try
		{
			int fila = Integer.parseInt( input );
			String mensaje= "En la fila indicada hay "+mundo.darCantidadCriaturasPorFila(fila)+ " criaturas";
			JOptionPane.showMessageDialog( this, mensaje, "Dar criaturas por fila", JOptionPane.INFORMATION_MESSAGE );
		}
		catch( Exception e )
		{
			JOptionPane.showMessageDialog( this, "Debe ingresar valor numerico", "Dar criaturas por fila", JOptionPane.ERROR_MESSAGE );
		}
	}


	public void criaturasColumna() 
	{
		String input = JOptionPane.showInputDialog( this, "Ingresa la columna", "Dar criaturas por columna", JOptionPane.QUESTION_MESSAGE );
		try
		{
			int columna = Integer.parseInt( input );
			String mensaje= "En la columna indicada hay "+mundo.darCantidadCriaturasPorColumna(columna)+ " columna";
			JOptionPane.showMessageDialog( this, mensaje, "Dar criaturas por columna", JOptionPane.INFORMATION_MESSAGE );
		}
		catch( Exception e )
		{
			JOptionPane.showMessageDialog( this, "Debe ingresar valor numerico", "Dar criaturas por columna", JOptionPane.ERROR_MESSAGE );
		}
	}

	public void puntajeCuadrante() 
	{
		String input = JOptionPane.showInputDialog( this, "Ingresa el cuadrante", "Dar puntaje por cuadrante", JOptionPane.QUESTION_MESSAGE );
		try
		{
			int cuadrante = Integer.parseInt( input );
			if (cuadrante < 1 || cuadrante > 4)
				throw new Exception();

			String mensaje= "En el cuadrante indicado hay "+mundo.calcularPuntajePorCuadrante(cuadrante)+ " puntos por obtener";
			JOptionPane.showMessageDialog( this, mensaje, "Dar puntaje por cuadrante", JOptionPane.INFORMATION_MESSAGE );
		}
		catch( Exception e )
		{
			JOptionPane.showMessageDialog( this, "El cuadrante especificado no es valido", "Dar puntaje por cuadrante", JOptionPane.ERROR_MESSAGE );
		}
	}


	public void criaturaMayorPuntaje()
	{
		String mensaje= "La criatura encontrada con mayor puntaje es "+mundo.darCriaturaMayorPuntaje().darNombre();
		JOptionPane.showMessageDialog( this, mensaje, "Dar criatura con mayor puntaje", JOptionPane.INFORMATION_MESSAGE );
	}




	public static void main(String[] args) {

		InterfazCriaturasMagicas ventana = new InterfazCriaturasMagicas();
		ventana.setVisible(true);

		// TODO Auto-generated method stub

	}
	public void click(int fila, int col) 
	{
		// TODO Auto-generated method stub
		try {
			mundo.click(fila, col);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ImageIcon icono = new ImageIcon( new ImageIcon( mundo.buscarCriatura(e.getMessage()).darRutaImagen() ).getImage( ).getScaledInstance( 150, 150, Image.SCALE_DEFAULT ) );
			JOptionPane.showMessageDialog( this,"Encontraste 1 "+ e.getMessage(), "Hacer una jugada", JOptionPane.INFORMATION_MESSAGE, icono);
		}
		
		panelMapa.actualizar(mundo.darTablero());
		panelInfo.cargar(mundo.darPuntaje()+"", mundo.darMovimientosRestantes()+"");
		
		
	}


}

package uniandes.cupi2.criaturasMagicas.interfaz;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;


public class PanelConsultas extends JPanel implements ActionListener
{
	public final static String CRIATURAFILA ="fila";
	public final static String CRIATURACOLUMNA ="columna";
	public final static String PUNTAJECUADRANTE= "cuadrante";
	public final static String MAYORPUNTAJE = "mayor";
	
	private InterfazCriaturasMagicas principal;
	private JButton fila;
	private JButton col;
	private JButton cuadrante;
	private JButton mayorPuntaje;
	
	public PanelConsultas(InterfazCriaturasMagicas v)
	{
		TitledBorder border = BorderFactory.createTitledBorder( "Acciones" );
		border.setTitleColor( Color.white );
		setBorder( border );
		setLayout(new GridLayout(1	, 4));
		principal=v;
		fila= new JButton("criaturas por fila");
		fila.setActionCommand(CRIATURAFILA);
		fila.addActionListener(this);
		
		col= new JButton("criaturas por columna");
		col.setActionCommand(CRIATURACOLUMNA);
		col.addActionListener(this);
		
		cuadrante= new JButton("puntaje por cuadrante");
		cuadrante.setActionCommand(PUNTAJECUADRANTE);
		cuadrante.addActionListener(this);
		
		mayorPuntaje= new JButton("criatura mayor puntaje");
		mayorPuntaje.setActionCommand(MAYORPUNTAJE);
		mayorPuntaje.addActionListener(this);
		
		add(fila);
		add(col);
		add(cuadrante);
		add(mayorPuntaje);
		setOpaque(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String comando = e.getActionCommand();
		if (comando.equals(CRIATURAFILA))
		{
			principal.criaturasFila();
		}
		else if (comando.equals(CRIATURACOLUMNA))
		{
			principal.criaturasColumna();
		}
		else if (comando.equals(PUNTAJECUADRANTE))
		{
			principal.puntajeCuadrante();
		}
		else if (comando.equals(MAYORPUNTAJE))
		{
			principal.criaturaMayorPuntaje();
		}
		
	}
}

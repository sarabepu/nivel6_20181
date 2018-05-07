package uniandes.cupi2.criaturasMagicas.interfaz;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class PanelInformacionJuego extends JPanel
{
	private JLabel puntaje;
	private JLabel mov;
	private JTextField txtpuntaje;
	private JTextField txtMov;
	private InterfazCriaturasMagicas principal;
	public PanelInformacionJuego(InterfazCriaturasMagicas v)
	{
		principal=v;
		TitledBorder border = BorderFactory.createTitledBorder( "Estado de juego" );
		border.setTitleColor( Color.white );
		setBorder( border );
		setLayout(new GridLayout(1, 4));
		puntaje= new JLabel("Puntaje: ");
		puntaje.setForeground( Color.WHITE );
		
		mov= new JLabel("Movimientos restantes: ");
		mov.setForeground( Color.WHITE );
		txtpuntaje= new JTextField();
		txtpuntaje.setOpaque(false);
		txtpuntaje.setEditable(false);
		txtpuntaje.setForeground(Color.WHITE);
		
		txtMov= new JTextField();
		txtMov.setOpaque(false);
		txtMov.setEditable(false);
		txtMov.setForeground(Color.WHITE);
		
		add(puntaje);
		add(txtpuntaje);
		add(mov);
		add( txtMov);
	}
	
	public void cargar(String pPuntaje, String pMovimientos)
	{
		txtpuntaje.setText(pPuntaje);
		txtMov.setText(pMovimientos);
		validate();
	}
	
	public void reiniciar()
	{
		txtpuntaje.setText("");
		txtMov.setText("");
		validate();
	}
}


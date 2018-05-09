package uniandes.cupi2.criaturasMagicas.interfaz;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import uniandes.cupi2.criaturasMagicas.mundo.Casilla;

public class PanelMapa extends JPanel implements ActionListener{
	private JButton[][] mapa;
	InterfazCriaturasMagicas principal;
	public PanelMapa(InterfazCriaturasMagicas v)
	
	{ 
		setOpaque(false);
		principal=v;
	}
	
	public void cargar(Casilla[][] tablero)
	{
		removeAll();
		setLayout(new GridLayout(tablero.length, tablero[0].length));
		
		mapa=new JButton[tablero.length][tablero[0].length];
		for(int i = 0; i < tablero.length; i++)
		{
			for (int j = 0; j < tablero[0].length; j++)
			{
				setBackground(new Color(237, 221,123));
				mapa[i][j]= new JButton();
				ImageIcon icono = new ImageIcon( new ImageIcon( tablero[i][j].darTipo()).getImage( ).getScaledInstance( 50, 50, Image.SCALE_DEFAULT ) );
				
				mapa[i][j].setIcon(icono);
				mapa[i][j].addActionListener(this);
				mapa[i][j].setActionCommand(i+","+j);
				mapa[i][j].setBorderPainted(false);
				mapa[i][j].setOpaque(false);
				mapa[i][j].setContentAreaFilled(false);
				add(mapa[i][j]);
				
			}
		}
		validate();
		repaint();
	}
	
	public void actualizar(Casilla[][] tablero)
	{
		removeAll();
		setOpaque(true);
		for(int i = 0; i < tablero.length; i++)
		{
			for (int j = 0; j < tablero[0].length; j++)
			{
				ImageIcon icono = new ImageIcon( new ImageIcon( tablero[i][j].darTipo()).getImage( ).getScaledInstance( 50, 50, Image.SCALE_DEFAULT ) );
				mapa[i][j].setIcon(icono);
				add(mapa[i][j]);
				
			}
		}
		validate();
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String comando= arg0.getActionCommand();
		String[] coordenadas= comando.split(",");
		
		int fila= Integer.parseInt(coordenadas[0]);
		int col= Integer.parseInt(coordenadas[1]);
		
		principal.click(fila, col);
	}
}

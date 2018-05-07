package uniandes.cupi2.criaturasMagicas.interfaz;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import uniandes.cupi2.criaturasMagicas.mundo.Casilla;

public class PanelMapa extends JPanel implements ActionListener{
	private JButton[][] mapa;
	public PanelMapa()
	{ 
		
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
				mapa[i][j]= new JButton();
				ImageIcon icono= new ImageIcon("./data/imagenes/"+tablero[i][j].darTipo());
				mapa[i][j].setIcon(icono);
				mapa[i][j].addActionListener(this);
				mapa[i][j].setActionCommand(i+""+j);
				mapa[i][j].setBorderPainted(false);
				mapa[i][j].setOpaque(false);
				mapa[i][j].setContentAreaFilled(false);
				add(mapa[i][j]);
				
			}
		}
		validate();
		repaint();
	}
	
	public void reiniciar()
	{
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}

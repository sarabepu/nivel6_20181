package uniandes.cupi2.criaturasMagicas.interfaz;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelBanner extends JPanel {
	private JLabel lblImagen;
	public PanelBanner()
	{
		lblImagen = new JLabel();
		ImageIcon icono = new ImageIcon( "data/imagenes/banner.png" );
		lblImagen.setIcon(icono);
		add(lblImagen);
	}

}


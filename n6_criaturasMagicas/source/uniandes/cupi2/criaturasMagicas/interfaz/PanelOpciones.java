package uniandes.cupi2.criaturasMagicas.interfaz;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class PanelOpciones extends JPanel implements ActionListener{
	
	public final static String CARGAR= "cargar";
	public final static String REINICIAR = "reiniciar";
	public final static String OPCION1 = "opcion1";
	public final static String OPCION2 = "opcion2";
	
	private InterfazCriaturasMagicas principal;
	private JButton cargar;
	private JButton reiniciar;
	private JButton opcion1;
	private JButton opcion2;
	
	public PanelOpciones(InterfazCriaturasMagicas v)
	{
		TitledBorder border = BorderFactory.createTitledBorder( "Opciones" );
		border.setTitleColor( Color.white );
		setBorder( border );
		setLayout(new GridLayout(1	, 4));
		principal=v;
		cargar= new JButton("Cargar");
		cargar.setActionCommand(CARGAR);
		cargar.addActionListener(this);
		
		reiniciar= new JButton("Reiniciar");
		reiniciar.setActionCommand(REINICIAR);
		reiniciar.addActionListener(this);
		
		opcion1= new JButton("Opción 1");
		opcion2= new JButton("Opción 2");
		opcion1.addActionListener(this);
		opcion1.setActionCommand(OPCION1);
		opcion2.addActionListener(this);
		opcion2.setActionCommand(OPCION2);
		
		add(cargar);
		add(reiniciar);
		add(opcion1);
		add(opcion2);
		setOpaque(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String comando= e.getActionCommand();
		if (comando.equals(CARGAR))
		{
			principal.cargar();
		}
		else if (comando.equals(REINICIAR))
		{
			principal.reiniciar();
		}
		else if (comando.equals(OPCION1))
		{
			principal.opcion1();;
		}
		else if (comando.equals(OPCION2))
		{
			principal.opcion2();;
		}
		
	}

}

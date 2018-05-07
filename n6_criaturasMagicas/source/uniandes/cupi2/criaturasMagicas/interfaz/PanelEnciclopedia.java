package uniandes.cupi2.criaturasMagicas.interfaz;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.Principal;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import uniandes.cupi2.criaturasMagicas.mundo.Criatura;


public class PanelEnciclopedia extends JPanel implements ActionListener {
	
	public final static String SIGUIENTE= "siguiente";
	public final static String ANTERIOR= "anterior";

	private JLabel imagen;
	private JLabel puntos;
	private JLabel nombre;
	private JLabel gustos;
	private JLabel miedos;
	private JTextField txtmiedos;
	private JTextField txtgustos;
	private JCheckBox luz;
	private JButton next;
	private JButton prev;
	private InterfazCriaturasMagicas principal;
	
	public PanelEnciclopedia(InterfazCriaturasMagicas v, Criatura pCriatura)
	{
		principal=  v;
		TitledBorder border = BorderFactory.createTitledBorder( "Enciclopedia" );
		border.setTitleColor( Color.white);
		setBorder( border );
		setLayout(new BorderLayout());	
		// Crea los subpaneles
		imagen = new JLabel();
		ImageIcon icono = new ImageIcon( new ImageIcon(
				"data/criaturas/bruja.png" ).getImage( ).getScaledInstance( 200, 200,
				Image.SCALE_DEFAULT ) );
		imagen.setIcon(icono);
		imagen.setOpaque(false);
		add(imagen, BorderLayout.CENTER);
		
		
		JPanel panelN= new JPanel();
		panelN.setLayout(new GridLayout(2, 1));
		nombre = new JLabel("NOMBRE");
		nombre.setFont( new Font("Helvetica", Font.BOLD, 22) );
		nombre.setForeground(new Color(255,252,222));
		nombre .setHorizontalAlignment( JLabel.CENTER );
		
		
		puntos= new JLabel("PUNTOS");
		puntos.setFont( new Font("Helvetica", Font.BOLD, 18) );
		puntos.setForeground( Color.WHITE );
		puntos.setHorizontalAlignment( JLabel.CENTER );
		
		panelN.add(nombre);
		panelN.add(puntos);
		panelN.setOpaque(false);
		add(panelN, BorderLayout.NORTH);
		
		
		JPanel panelS= new JPanel();
		panelS.setLayout(new GridLayout(6,1));
		gustos= new JLabel("Gustos: ");
		gustos.setForeground( new Color(255,252,222));
		miedos= new JLabel("Miedos: ");
		miedos.setForeground( new Color(255,252,222) );
		
		
		txtgustos= new JTextField();
		txtgustos.setForeground( Color.WHITE );
		txtgustos.setEditable(false);
		txtgustos.setOpaque(false);
		
		
		txtmiedos= new JTextField();
		txtmiedos.setForeground( Color.WHITE );
		txtmiedos.setEditable(false);
		txtmiedos.setOpaque(false);
		luz= new JCheckBox("Ser de luz");
		luz.setForeground( Color.WHITE );
		luz.setOpaque(false);
		luz.setEnabled(false);
		panelS.setOpaque(false);
		panelS.add(gustos);
		panelS.add(txtgustos);
		panelS.add(miedos);
		panelS.add(txtmiedos);
		panelS.add(luz);
		JPanel sub= new JPanel();
		sub.setLayout(new GridLayout(1, 2));
		prev= new JButton("<");
		prev.setActionCommand(ANTERIOR);
		prev.addActionListener(this);
		
		next= new JButton(">");
		next.setActionCommand(SIGUIENTE);
		next.addActionListener(this);
		
		sub.add(prev);
		sub.add(next);
		sub.setOpaque(false);
		panelS.add(sub);
		add(panelS, BorderLayout.SOUTH);
		
		
		refrescar(pCriatura);
		
		}
		public void refrescar(Criatura pCriatura)
		{
			txtgustos.setText(pCriatura.darGustos());
			txtmiedos.setText(pCriatura.darMiedos());
			nombre.setText(pCriatura.darNombre());
			puntos.setText(pCriatura.darPuntos()+"");
			luz.setSelected(pCriatura.esSerDeLuz());
			imagen.setIcon(new ImageIcon(new ImageIcon(
				pCriatura.darRutaImagen() ).getImage( ).getScaledInstance( 200, 200,
				Image.SCALE_DEFAULT )));
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String comando= e.getActionCommand();
			if(comando.equals(SIGUIENTE))
			{
				principal.cambiarSiguiente();
			}
			else if (comando.equals(ANTERIOR))
			{
				principal.cambiarAnterior();
			}
		}
	}

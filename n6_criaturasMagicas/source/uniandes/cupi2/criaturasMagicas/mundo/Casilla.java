package uniandes.cupi2.criaturasMagicas.mundo;

public class Casilla {

	
	public final static String  PRADERA = "./data/imagenes/pradera.png";
	public final static String BOSQUE = "./data/imagenes/bosque.png";
	public final static String CUEVA = "./data/imagenes/cueva.png";
	public final static String OCEANO = "./data/imagenes/oceano.png";
	public final static String VISITADA = "./data/imagenes/visitada.png";

	
	
	private Criatura criatura;
	private boolean visitada;
	private String tipo;
	public Casilla( int pTipo)
	{
		if(pTipo==0)
		{
			tipo=PRADERA;
		}
		else if(pTipo==1)
		{
			tipo=BOSQUE;
		}
		else if(pTipo==2)
		{
			tipo=OCEANO;
		}
		else if(pTipo==3)
		{
			tipo=CUEVA;
		}
	}
	public void cambiarCriatura(Criatura pCriatura)
	{
		criatura= pCriatura;
	}
	public String darTipo()
	{
		return tipo;
	}
	public void cambiarTipo(String pTipo)
	{
		tipo=pTipo;
	}
	public Criatura darCriatura()
	{
		return criatura;
	}
	public boolean esVisitada() {
		return visitada;
	}
	public void visitar(boolean pVisitada) {
		visitada = pVisitada;
	}
}

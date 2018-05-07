package uniandes.cupi2.criaturasMagicas.mundo;

public class Casilla {

	
	public final static String  PRADERA = "pradera.png";
	public final static String BOSQUE = "bosque.png";
	public final static String CUEVA = "cueva.png";
	public final static String OCEANO = "oceano.png";
	public final static String BRUJA = ".png";
	public final static String ENANO = "oceano.png";
	public final static String NINFA = "oceano.png";
	
	
	private Criatura criatura;
	
	
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
}

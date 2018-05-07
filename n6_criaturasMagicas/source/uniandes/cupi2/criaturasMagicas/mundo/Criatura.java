/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n5_criaturasMagicas
 * Autor: Equipo Cupi2 2018
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.criaturasMagicas.mundo;

/**
 * Criatura que puede habitar en un terreno.
 */
public class Criatura
{

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Nombre de la criatura.
	 */
	private String nombre;

	/**
	 * Gustos de la criatura.
	 */
	private String gustos;

	/**
	 * Miedos de la criatura.
	 */
	private String miedos;

	/**
	 * Indica si la criatura es un ser de luz.
	 */
	private boolean serDeLuz;

	/**
	 * Los puntos que obtiene el jugador al encontrar la criatura.
	 */
	private int puntos;

	/**
	 * Ruta de la imagen de la criatura.
	 */
	private String rutaImagen;

	// -----------------------------------------------------------------
	// Constructor
	// -----------------------------------------------------------------

	/**
	 * Crea la criatura con las características especificadas por parámetro. <br>
	 * <b>post: </b> Los atributos fueron inicializados con los valores recibidos por parámetro.
	 * @param pNombre Nombre de la criatura. pNombre != null && pNombre != "".
	 * @param pGustos Gustos de la criatura. pGustos != null && pGustos != "".
	 * @param pMiedos Miedos de la criatura. pMiedos != null && pMiedos != "".
	 * @param pSerDeLuz True si la criatura es un ser de luz, false en caso contrario.
	 * @param pPuntos Puntos que recibe el jugador al encontrar la criatura. pPuntos > 0.
	 * @param pRutaImagen Ruta de la imagen. pRutaImagen != null && pRutaImagen != "".
	 */
	public Criatura( String pNombre, String pGustos, String pMiedos, boolean pSerDeLuz, int pPuntos, String pRutaImagen )
	{
		nombre = pNombre;
		gustos = pGustos;
		miedos = pMiedos;
		serDeLuz = pSerDeLuz;
		puntos = pPuntos;
		rutaImagen = pRutaImagen;
		
	}

	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------

	/**
	 * Retorna el nombre de la criatura.
	 * @return Nombre de la criatura.
	 */
	public String darNombre( )
	{
		return nombre;
	}

	/**
	 * Retorna los gustos de la criatura.
	 * @return Gustos de la criatura.
	 */
	public String darGustos( )
	{
		return gustos;
	}

	/**
	 * Retorna los miedos de la criatura.
	 * @return Los miedos de la criatura.
	 */
	public String darMiedos( )
	{
		return miedos;
	}

	/**
	 * Indica si la criatura es un ser de luz.
	 * @return True si la criatura es un ser de luz, false en caso contrario.
	 */
	public boolean esSerDeLuz( )
	{
		return serDeLuz;
	}

	/**
	 * Retorna la cantidad de puntos que recibe el jugador al encontrar la criatura.
	 * @return Cantidad de puntos que recibe el jugador al encontrar la criatura.
	 */
	public int darPuntos( )
	{
		if(serDeLuz)
			return puntos;
		else
			return puntos*-1;
	}

	/**
	 * Retorna la ruta de la imagen.
	 * @return Ruta de la imagen.
	 */
	public String darRutaImagen( )
	{
		return rutaImagen;
	}
}

/* Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n5_criaturasMagicas
 * Autor: Equipo Cupi2 2018
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.criaturasMagicas.mundo;

import java.io.File; 
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


/**
 * Juego de la búsqueda de criaturas mágicas.
 */
public class CriaturasMagicas
{




	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Enciclopedia de criaturas que se pueden encontrar.
	 */
	private Criatura[] enciclopedia;

	/**
	 * Los últimos datos cargados del juego.
	 */
	private Properties datos;
	private Properties datos2;
	private int puntos;
	private int movimientosRestantes;

	/**
	 * Criatura actual en la navegación.
	 */
	private int criaturaActual;
	private int filas;
	private int columnas;

	private Casilla[][] tablero;
	// -----------------------------------------------------------------
	// Constructor
	// -----------------------------------------------------------------

	/**
	 * Crea la instancia principal de la aplicación y carga la enciclopedia de criaturas. <br>
	 * <b>post: </b> Se ha cargado la información del arreglo de criaturas.
	 * @throws Exception Si hubo error al cargar el archivo.
	 *                   Si hubo error al leer el formato del archivo.
	 */
	public CriaturasMagicas() throws Exception
	{
		try
		{
			cargar( );
			inicializarCriaturas( );

		}
		catch( Exception e )
		{
			throw e;
		}
	}

	public void tablero (File arch) throws Exception{
		cargarTablero(arch);

		inicializarTablero();

	}

	public void cargarTablero( File arch)throws Exception{

		datos2= new Properties( );
		FileInputStream in = new FileInputStream( arch );

		try {
			datos2.load( in );
			in.close( );
		}
		catch( Exception e ) {
			throw new Exception( "Formato invAlido" );
		}
	}


	public void inicializarTablero(){
		Properties datos= datos2;

		String numeroMov = datos.getProperty("tablero.cantidadMovimientos");
		movimientosRestantes = Integer.parseInt(numeroMov); 

		String numeroFilas = datos.getProperty("tablero.cantidadFilas");
		filas = Integer.parseInt(numeroFilas);  

		String numeroColumnas= datos.getProperty("tablero.cantidadColumnas");
		columnas = Integer.parseInt(numeroColumnas);  


		tablero = new Casilla [filas][columnas];

		for (int i = 0; i<filas; i++)
		{
			for(int j = 0; j<columnas; j++)
			{
				String[] filaActual = datos.getProperty("tablero.fila"+(i+1)).split(",");
				int estado = Integer.parseInt(filaActual[j]);
				tablero[i][j]= new Casilla (estado);
			}
		}

		int criaturas= Integer.parseInt(datos.getProperty("tablero.cantidadCriaturas"));
		for (int i = 1; i <= criaturas; i++)
		{
			String[] filaActual = datos.getProperty("tablero.criatura"+i).split(",");
			String criatura= filaActual[0];
			int filaC = Integer.parseInt(filaActual[1]);
			int colC = Integer.parseInt(filaActual[2]);

			tablero[filaC][colC].cambiarCriatura(buscarCriatura(criatura));
		}

	} 

	public Casilla[][] darTablero()
	{
		return tablero;
	}



	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------

	/**
	 * Retorna la criatura actual de la enciclopedia.
	 * @return La criatura actual de la enciclopedia.
	 */
	public Criatura darActual( )
	{
		return enciclopedia[ criaturaActual ];
	}

	/**
	 * Retorna la siguiente criatura a la criatura actual. <br>
	 * <b>post: </b> Se ha modificado la criatura actual a la siguiente criatura.
	 * @return La siguiente criatura.
	 * @throws Exception Si ya se encuentra en la última criatura de la enciclopedia.
	 */
	public Criatura darSiguiente( ) throws Exception
	{
		if( criaturaActual == enciclopedia.length - 1 )
		{
			throw new Exception( "Ya se encuentra en la última criatura." );
		}

		criaturaActual++;
		return enciclopedia[ criaturaActual ];
	}

	/**
	 * Retorna la anterior criatura a la criatura actual. <br>
	 * <b>post: </b> Se ha modificado la criatura actual a la anterior criatura.
	 * @return La anterior criatura.
	 * @throws Exception Si ya se encuentra en la primera criatura de la enciclopedia.
	 */
	public Criatura darAnterior( ) throws Exception
	{
		if( criaturaActual == 0 )
		{
			throw new Exception( "Ya se encuentra en la primera criatura." );
		}

		criaturaActual--;
		return enciclopedia[ criaturaActual ];
	}

	/**
	 * Retorna el puntaje del jugador. Este valor siempre es 0.
	 * @return El  puntaje del jugador.
	 */
	public int darPuntaje( )
	{
		return puntos;
	}

	public void click(int i, int j) throws Exception
	{
		
		System.out.println("fila "+i+"col"+j);
		if(tablero[i][j].esVisitada())
		{
			movimientosRestantes--;
		}
		else
		{
			Criatura criatura= tablero[i][j].darCriatura();
			tablero[i][j].visitar(true);
			movimientosRestantes--;
			if(criatura!=null)
			{
				puntos+=criatura.darPuntos();
				tablero[i][j].cambiarTipo(criatura.darRutaImagen());
				throw new Exception (criatura.darNombre());
			}
			else
			{
				tablero[i][j].cambiarTipo(Casilla.VISITADA);
			}
		}
	}
	/**
	 * Retorna la cantidad de movimientos restantes del jugador. Este valor siempre es 10.
	 * @return La cantidad de movimientos restantes del jugador.
	 */
	public int darMovimientosRestantes( )
	{
		return movimientosRestantes;
	}

	/**
	 * Busca una criatura por su nombre en la enciclopedia de criaturas.
	 * @param pNombre Nombre de la criatura. pNombre != null && pNombre != "".
	 * @return La criatura con el nombre especificado. Si no la encuentra retorna null.
	 */
	public Criatura buscarCriatura( String pNombre )
	{
		Criatura buscada = null;

		for( int i = 0; i < enciclopedia.length && buscada == null; i++ )
		{
			if( enciclopedia[ i ].darNombre( ).equalsIgnoreCase( pNombre ) )
			{
				buscada = enciclopedia[ i ];
			}
		}

		return buscada;
	}

	/**
	 * Carga el archivo especificado por parámetro para procesarlo.
	 * @throws Exception Si se encuentra algún problema al cargar el archivo.
	 */
	private void cargar() throws Exception
	{
		datos = new Properties( );
		FileInputStream in = new FileInputStream( "./data/criaturas.properties" );


		try
		{
			datos.load( in );

			in.close( );

		}
		catch( IOException e )
		{
			throw new Exception( "Error al cargar el archivo, archivo no válido." );
		}
	}

	/**
	 * Inicializa el arreglo de criaturas a partir del archivo de configuración. <br>
	 * <b>post: </b> Ha sido inicializada la enciclopedia de criaturas.
	 * @throws Exception Lanza excepción si hay algún error al inicializar las criaturas.
	 */
	private void inicializarCriaturas( ) throws Exception
	{
		try
		{
			int cantidadCriaturas = Integer.parseInt( datos.getProperty( "criaturas.cantidad" ) );

			enciclopedia = new Criatura[cantidadCriaturas];
			for( int i = 0; i < cantidadCriaturas; i++ )
			{
				String nombre = datos.getProperty( "criaturas.criatura" + ( i + 1 ) + ".nombre" );
				String gustos = datos.getProperty( "criaturas.criatura" + ( i + 1 ) + ".gustos" );
				String miedos = datos.getProperty( "criaturas.criatura" + ( i + 1 ) + ".miedos" );
				boolean serDeLuz = true;
				int puntos = Integer.parseInt( datos.getProperty( "criaturas.criatura" + ( i + 1 ) + ".puntos" ) );

				if( datos.getProperty( "criaturas.criatura" + ( i + 1 ) + ".serDeLuz" ).equals( "false" ) )
				{
					serDeLuz = false;
				}
				String rutaImagen = datos.getProperty( "criaturas.criatura" + ( i + 1 ) + ".ruta" );

				enciclopedia[ i ] = new Criatura( nombre, gustos, miedos, serDeLuz, puntos, rutaImagen );
			}

			criaturaActual = 0;
		}
		catch(Exception e)
		{
			throw new Exception("Error al leer el formato del archivo.");
		}
	}

	/**
	 * Retorna la cantidad de criaturas en la fila especificada. Esta cantidad se genera aleatoriamente.
	 * @param pFila Fila que se desea consultar. 
	 * @return Cantidad de criaturas en la fila especificada.
	 */
	public int darCantidadCriaturasPorFila( int pFila )
	{
		int cantidad=0;

		for(int i=0; i< tablero[0].length; i++)
		{
			if(tablero[pFila][i].darCriatura()!=null)
				cantidad++;
		}


		return cantidad;
	}

	/**
	 * Retorna la cantidad de criaturas en la columna especificada. Esta cantidad se genera aleatoriamente.
	 * @param pColumna Columna que se desea consultar. 
	 * @return Cantidad de criaturas en la columna especificada.
	 */
	public int darCantidadCriaturasPorColumna( int pColumna )
	{
		int cantidad=0;
		for(int i=0; i< tablero.length; i++)
		{
			if(tablero[i][pColumna].darCriatura()!=null)
				cantidad++;
		}


		return cantidad;
	}

	/**
	 * Retorna el puntaje total que se puede obtener si se encuentran todas las criaturas el cuadrante especificado. Esta cantidad se genera aleatoriamente.
	 * @param pCuadrante Cuadrante que se desea consultar. pCuadrante > 0 && pCuadrante <= 4
	 * @return El puntaje que se puede obtener en cuadrante especificado.
	 * @throws Exception 
	 */
	public int calcularPuntajePorCuadrante( int pCuadrante ) throws Exception
	{
		int cantidad=0;
		int filaI=0;
		int columnaI=0;
		int filaF=0;
		int columnaF=0;
		if(pCuadrante==1)
		{
			filaF=filas/2;
			columnaF=columnas/2;
		}
		else if(pCuadrante==2)
		{
			filaI=0;
			filaF=filas/2;
			columnaI=columnas/2;
			columnaF=columnas;
		}
		else if(pCuadrante==3)
		{
			filaI=filas/2;
			filaF=filas;
			columnaI=0;
			columnaF=columnas/2;
		}
		else if(pCuadrante==4)
		{
			filaI=filas/2;
			filaF=filas;
			columnaI=columnas;
			columnaF=columnas/2;
		}
		else
		{
			throw new Exception ("Numero de cuadrante invalido");
		}

		for(int i= filaI; i<filaF; i++)
		{
			for(int j= columnaI; j<columnaF; j++)
			{
				Criatura criatura=tablero[i][j].darCriatura();
				if(criatura!=null&&!tablero[i][j].esVisitada())
				{
					cantidad+=criatura.darPuntos();
				}
			}
		}

		return cantidad;

	}

	/**
	 * Retorna la criatura de luz no encontrada que tiene el mayor puntaje. 
	 * @return Criatura con el mayor puntaje.
	 */
	public Criatura darCriaturaMayorPuntaje( )
	{
		Criatura ans= null;
		int maxPuntos=0;
		for (int i = 0; i < tablero.length; i++) 
		{
			for (int j = 0; j < tablero[0].length; j++) 
			{
				Criatura criatura = tablero[i][j].darCriatura();
				if(criatura!=null&&criatura.darPuntos()>maxPuntos&&!tablero[i][j].esVisitada())
				{
					ans=criatura;
					maxPuntos= criatura.darPuntos();
				}
			}
		}

		return ans;
	}

	// ----------------------------------------------------------------
	// Métodos de Extensión
	// ----------------------------------------------------------------

	
	public void reiniciarPuntajes()
	{
		puntos = 0;
	}
	
	/**
	 * Método para la extensión 1.
	 * @return Respuesta 1.
	 */
	public String metodo1( )
	{
		return "Respuesta 1";
	}

	/**
	 * Método para la extensión 2.
	 * @return Respuesta 2.
	 */
	public String metodo2( )
	{
		return "Respuesta 2";
	}

	

}

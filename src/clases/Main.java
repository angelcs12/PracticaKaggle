package clases;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		/*
		 * Almacenamos los jugadores en un arraylist en la clase Main
		 */
		ArrayList<Jugador> jugadores = Jugador.CreaJugadores();
		
		/*
		 * Bucle que muestra el contenido del arraylist jugadores
		 */
		
//		for(Jugador jugador : jugadores) {
//			System.out.println(jugador.toString());
//		}
		
		/*
		 * Creamos todos los equipos, con un bucle for comprobamos todos los
		 * jugadores y su equipo, para almacenarlos en el que corresponda
		 */
		Equipo loud = new Equipo("LOUD");
		Equipo optc = new Equipo("OPTC");
		Equipo drx = new Equipo("DRX");
		Equipo fpx = new Equipo("FPX");
		Equipo xset = new Equipo("XSET");
		Equipo fnc = new Equipo("FNC");
		Equipo tl = new Equipo("TL");
		Equipo lev = new Equipo("LEV");
		
		for(Jugador jugador : jugadores) {
			if(jugador.getEquipo().equals("LOUD")) {
				loud.agregaJugadores(jugador);
			}else if(jugador.getEquipo().equals("OPTC")) {
				optc.agregaJugadores(jugador);
			}else if(jugador.getEquipo().equals("DRX")) {
				drx.agregaJugadores(jugador);
			}else if(jugador.getEquipo().equals("FPX")) {
				fpx.agregaJugadores(jugador);
			}else if(jugador.getEquipo().equals("XSET")) {
				xset.agregaJugadores(jugador);
			}else if(jugador.getEquipo().equals("FNC")) {
				fnc.agregaJugadores(jugador);
			}else if(jugador.getEquipo().equals("TL")) {
				tl.agregaJugadores(jugador);
			}else if(jugador.getEquipo().equals("LEV")) {
				lev.agregaJugadores(jugador);
			}
		}
		
		/*
		 * Creamos un arraylist de equipos y añadimos todos.
		 */
		ArrayList<Equipo> equipos = new ArrayList<>();
		
		equipos.add(lev);
		equipos.add(xset);
		equipos.add(tl);
		equipos.add(fnc);
		equipos.add(fpx);
		equipos.add(drx);
		equipos.add(optc);
		equipos.add(loud);
		
		/*
		 * Comprobaciones de los métodos
		 */
		
//		fpx.mostrarJugadores();
		
//		creaDocumento(jugadores);
		
//		ordenaPorKills(jugadores);
		
//		ordenaEquipos(equipos);
		
//		porRoles(jugadores);
		
//		mejorJugador(equipos);
	}
	
	/*
	 * Método que crea un documento con la cuantía total repartida en premios,
	 * ratio medio de kills/deaths de los jugadores, nacionalidad con más victorias y
	 * equipo con más dinero obtenido en premios
	 */
	public static void creaDocumento(ArrayList<Jugador> jugadores) throws IOException {
		/*
		 * Creamos la variable cuantiaTotal, que nos sirve para calcular la cuantía total repartida
		 * en premios, la variable ratioMedio, para calcular el ratio medio de kills/deaths de los jugadores,
		 * un hashmap de nacionalidades, para calcular la nacionalidad con más victorias,
		 * y un hasmap de equipos, que nos sirve para calcular qué equipo ha obtenido más dinero en premios
		 */
		double cuantiaTotal = 0;
		
		double ratioMedio = 0;
		
		HashMap<String, Integer> nacionalidades = new HashMap<>();
		int victorias;
		
		HashMap<String, Double> equipos = new HashMap<>();
		double cuantia;
		
		FileWriter flujoEscritura = null;
		PrintWriter filtroEscritura = null;
		
		/*
		 * Recorremos todos los jugadores, sumando sus ganancias para la cuantia total y su ratio
		 * medio para calcular el ratio de todos los jugadores. Luego, comprobamos si su nacionalidad se
		 * encuentra dentro del hashmap, si no se encuentra, lo añadimos como primera nacionalidad junto con
		 * sus rondas ganadas. Si no, se lo sumamos a las rondas ganadas que ya tenía dicha nacionalidad.
		 * Igual para calcular el equipo con más dinero
		 */
		for(Jugador jugador : jugadores) {
			cuantiaTotal += jugador.getGanancias();
			
			ratioMedio += jugador.getKillsDeaths();
			
			if(nacionalidades.containsKey(jugador.getNacionalidad())) {
				victorias = nacionalidades.get(jugador.getNacionalidad());
				nacionalidades.put(jugador.getNacionalidad(), victorias + jugador.getRondasGanadas());
			}else {
				nacionalidades.put(jugador.getNacionalidad(), jugador.getRondasGanadas());
			}
			
			if(equipos.containsKey(jugador.getEquipo())) {
				cuantia = equipos.get(jugador.getEquipo());
				equipos.put(jugador.getEquipo(), cuantia + jugador.getGanancias());
			}else {
				equipos.put(jugador.getEquipo(), jugador.getGanancias());
			}
		}
		
		/*
		 * Declaramos la variable nacionalidad con más victorias, donde vamos a almacenar
		 * la nacionalidad que tras comprobar todas contenga el mayor número de victorias.
		 * También declaramos la variable victoriasMaximas que nos servirá como auxiliar para
		 * almacenar en todo momento cual es la nacionalidad que momentaneamente tiene el
		 * mayor número de victorias
		 */
		String nacionalidadConMasVictorias = "";
		int victoriasMaximas = 0;
		
		/*
		 * Recorremos el hashmap de nacionalidades. Guardamos en una variable nacionalidad
		 * el nombre de la nacionalidad y en la variable victoriasActuales la victoria del país.
		 * Comprobamos si el país que acabamos de recorrer tiene el mayor número de victorias o no
		 */
		for(Map.Entry<String, Integer> entry : nacionalidades.entrySet()) {
			String nacionalidad = entry.getKey();
			int victoriasActuales = entry.getValue();
			
			if(victoriasActuales > victoriasMaximas) {
				victoriasMaximas = victoriasActuales;
				nacionalidadConMasVictorias = nacionalidad;
			}
		}
		
		/*
		 * Igual que con las nacionalidades
		 */
		String equipoConMasGanancias = "";
		double cuantiaMaxima = 0;
		
		for(Map.Entry<String, Double> entry : equipos.entrySet()) {
			String equipo = entry.getKey();
			double cuantiaActual = entry.getValue();
			
			if(cuantiaActual > cuantiaMaxima) {
				cuantiaMaxima = cuantiaActual;
				equipoConMasGanancias = equipo;
			}
		}
		
		/*
		 * Terminamos de calcular el ratio medio, dividiendolo entre la cantidad
		 * de jugadores. Finalmente terminamos por imprimir la información en el
		 * nuevo archivo.
		 */
		ratioMedio = ratioMedio / jugadores.size();
		
		try {
			flujoEscritura = new FileWriter("C:\\informacionValorant.txt");
			filtroEscritura = new PrintWriter(flujoEscritura);
			
			filtroEscritura.println("Cuantía total repartida en premios: " + cuantiaTotal + " mil dolares");
			filtroEscritura.println("Ratio medio de Kills/Deaths en los jugadores: " + ratioMedio);
			filtroEscritura.println("Nacionalidad con más victorias: " + nacionalidadConMasVictorias);
			filtroEscritura.println("Equipo con más dinero obtenido en premios: " + equipoConMasGanancias);
			
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}finally {
			if(flujoEscritura != null) {
				flujoEscritura.close();
			}
			if(filtroEscritura != null) {
				filtroEscritura.close();
			}
		}
		
	}
	
	/*
	 * Método que ordena por el número de kills de cada jugador
	 */
	public static void ordenaPorKills(ArrayList<Jugador> jugadores) throws IOException {
		FileWriter flujoEscritura = null;
		PrintWriter filtroEscritura = null;
		
		/*
		 * Usamos collections.sort para ordenar el arraylist que hemos pasado por
		 * parámetro, luego imprimimos en un archivo de texto
		 */
		Collections.sort(jugadores, (a, b) -> {
			return (int) (a.getKills() - b.getKills());
		});
		
		try {
			flujoEscritura = new FileWriter("C:\\informacionPorKills.txt");
			filtroEscritura = new PrintWriter(flujoEscritura);
			
			for(Jugador jugador : jugadores) {
				filtroEscritura.println("Nombre: " + jugador.getNombre() + ", Nacionalidad: " + jugador.getNacionalidad() + ", Kills: " + jugador.getKills());
			}
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}finally {
			if(flujoEscritura != null) {
				flujoEscritura.close();
			}
			if(filtroEscritura != null) {
				filtroEscritura.close();
			}
		}
	}
	
	/*
	 * Método que ordena los equipos según el número de jugadores que tengan.
	 */
	public static void ordenaEquipos(ArrayList<Equipo> equipos) throws IOException {
		FileWriter flujoEscritura = null;
		PrintWriter filtroEscritura = null;
		
		/*
		 * Declaramos las variables ratioKD y dineroTotal, que nos servirán para imprimir
		 * dichos datos de cada equipo
		 */
		int ratioKD = 0;
		double dineroTotal = 0;
		
		/*
		 * Collections.sort para ordenar los equipos según el número de jugadore que tienen
		 */
		Collections.sort(equipos, (a, b) -> {
			return (int) (a.getJugadores().size() - b.getJugadores().size());
		});
		
		try {
			flujoEscritura = new FileWriter("C:\\equiposOrdenados.txt");
			filtroEscritura = new PrintWriter(flujoEscritura);
			
			for(Equipo equipo : equipos) {
				for(Jugador jugador : equipo.getJugadores()) {
					dineroTotal += jugador.getGanancias();
					ratioKD += jugador.getKillsDeaths();
				}
				
				ratioKD = ratioKD / equipo.getJugadores().size();
				
				filtroEscritura.println("Nombre: " + equipo.getNombre() + ", Dinero total: " + dineroTotal + " mil, Ratio Kills/Deaths: " + ratioKD);
			}
			
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}finally {
			if(flujoEscritura != null) {
				flujoEscritura.close();
			}
			if(filtroEscritura != null) {
				filtroEscritura.close();
			}
		}
	}
	
	/*
	 * Método que devuelve la media de kills y muertes de cada rol.
	 */
	public static void porRoles(ArrayList<Jugador> jugadores) throws IOException {
		FileWriter flujoEscritura = null;
		PrintWriter filtroEscritura = null;
		
		/*
		 * Declaramos las variables mediaKills y mediaDeaths, que nos sirven para almacenar
		 * las medias de cada rol. Creamos también tres HashMap, una para las kills, otras para
		 * las muertes y otra para la cantidad de jugadores de cada rol. Esto nos servirá para poder
		 * almacenar de una forma más fácil los datos distinguiendo por rol.
		 */
		int mediaKills;
		int mediaDeaths;
		
		HashMap<String, Integer> killsRol = new HashMap<>();
		HashMap<String, Integer> deathsRol = new HashMap<>();
		HashMap<String, Integer> jugadoresRol = new HashMap<>();
		
		try {
			flujoEscritura = new FileWriter("C:\\porRoles.txt");
			filtroEscritura = new PrintWriter(flujoEscritura);
			
			/*
			 * Recorremos todos los jugadores, comprobando su rol. Si el rol ya se encuentra en los HashMaps
			 * (solo tenemos que comprobar uno porque al añadir un rol ya lo tenemos almacenado en los tres)
			 * sumamos a los datos ya almacenados los datos del nuevo jugador, si no, almacenamos los primeros
			 * datos para el nuevo rol.
			 */ 
			for(Jugador jugador : jugadores) {
				if(killsRol.containsKey(jugador.getRol())) {
					killsRol.put(jugador.getRol(), killsRol.get(jugador.getRol()) + jugador.getKills());
					deathsRol.put(jugador.getRol(), deathsRol.get(jugador.getRol()) + jugador.getDeaths());
					jugadoresRol.put(jugador.getRol(), jugadoresRol.get(jugador.getRol()) + 1);
				}else {
					killsRol.put(jugador.getRol(), jugador.getKills());
					deathsRol.put(jugador.getRol(), jugador.getDeaths());
					jugadoresRol.put(jugador.getRol(), 1);
				}
			}
			
			/*
			 * Recorremos todos los roles, calculando sus respectivas medias, para después imprimir los
			 * resultados de cada rol.
			 */
			for(String rol : jugadoresRol.keySet()) {
				mediaKills = killsRol.get(rol) / jugadoresRol.get(rol);
				mediaDeaths = deathsRol.get(rol) / jugadoresRol.get(rol);
				
				filtroEscritura.println("Rol: " + rol + ", Kills medias: " + mediaKills + ", Deaths medias: " + mediaDeaths);
			}
			
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}finally {
			if(flujoEscritura != null) {
				flujoEscritura.close();
			}
			if(filtroEscritura != null) {
				filtroEscritura.close();
			}
		}
	}
	
	/*
	 * Método que devuelve el mejor jugador (jugador con mejor ratio k/d de cada equipo)
	 */
	public static void mejorJugador(ArrayList<Equipo> equipos) throws IOException {
		FileWriter flujoEscritura = null;
		PrintWriter filtroEscritura = null;
		
		try {
			flujoEscritura = new FileWriter("C:\\mejorJugador.txt");
			filtroEscritura = new PrintWriter(flujoEscritura);
			
			/*
			 * Declaramos las variables jugador j y kdMax antes de empezar a recorrer todos
			 * los jugadores de cada equipo. Esto nos sirve para que se reinicie cada vez que
			 * cambiamos de equipo.
			 */
			
			for(Equipo equipo : equipos) {
				Jugador j = null;
				double kdMax = 0;
				
				/*
				 * Declaramos la variable kdAct, que nos sirve para almacenar el ratio k/d de cada
				 * jugador que estamos recorriendo y compararlo con el ratio k/d máximo que tenemos
				 * almacenado en este momento.
				 */
				for(Jugador jugador : equipo.getJugadores()) {
					double kdAct;
					
					kdAct = jugador.getKillsDeaths();
					
					if(kdAct > kdMax) {
						kdMax = kdAct;
						j = jugador;
					}
				}
				
				filtroEscritura.println("Equipo: " + equipo.getNombre() + ", Jugador: " + j.getNombre() + ", K/D: " + j.getKillsDeaths());
			}
			
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}finally {
			if(flujoEscritura != null) {
				flujoEscritura.close();
			}
			if(filtroEscritura != null) {
				filtroEscritura.close();
			}
		}
	}
	
}

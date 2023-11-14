package clases;

import java.io.*;
import java.util.*;

public class Jugador {
	private String nombre;
	private String equipo;
	private String nacionalidad;
	private int kills;
	private int deaths;
	private double killsDeaths;
	private String kast;
	private double ganancias;
	private String rol;
	private double headshot;
	private int rondasJugadas;
	private int rondasGanadas;
	private int rondasPerdidas;


	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getEquipo() {
		return equipo;
	}



	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}



	public String getNacionalidad() {
		return nacionalidad;
	}



	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}



	public int getKills() {
		return kills;
	}



	public void setKills(int kills) {
		this.kills = kills;
	}



	public int getDeaths() {
		return deaths;
	}



	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}



	public double getKillsDeaths() {
		return killsDeaths;
	}



	public void setKillsDeaths(double killsDeaths) {
		this.killsDeaths = killsDeaths;
	}



	public String getKast() {
		return kast;
	}



	public void setKast(String kast) {
		this.kast = kast;
	}



	public double getGanancias() {
		return ganancias;
	}



	public void setGanancias(double ganancias) {
		this.ganancias = ganancias;
	}



	public String getRol() {
		return rol;
	}



	public void setRol(String rol) {
		this.rol = rol;
	}



	public double getHeadshot() {
		return headshot;
	}



	public void setHeadshot(double headshot) {
		this.headshot = headshot;
	}



	public int getRondasJugadas() {
		return rondasJugadas;
	}



	public void setRondasJugadas(int rondasJugadas) {
		this.rondasJugadas = rondasJugadas;
	}



	public int getRondasGanadas() {
		return rondasGanadas;
	}



	public void setRondasGanadas(int rondasGanadas) {
		this.rondasGanadas = rondasGanadas;
	}



	public int getRondasPerdidas() {
		return rondasPerdidas;
	}



	public void setRondasPerdidas(int rondasPerdidas) {
		this.rondasPerdidas = rondasPerdidas;
	}

	
	public Jugador(String nombre, String equipo, String nacionalidad, int kills, int deaths, double killsDeaths,
			String kast, double ganancias, String rol, double headshot, int rondasJugadas, int rondasGanadas,
			int rondasPerdidas) {
		super();
		this.nombre = nombre;
		this.equipo = equipo;
		this.nacionalidad = nacionalidad;
		this.kills = kills;
		this.deaths = deaths;
		this.killsDeaths = killsDeaths;
		this.kast = kast;
		this.ganancias = ganancias;
		this.rol = rol;
		this.headshot = headshot;
		this.rondasJugadas = rondasJugadas;
		this.rondasGanadas = rondasGanadas;
		this.rondasPerdidas = rondasPerdidas;
	}


	public String toString() {
		return "Jugador [nombre=" + nombre + ", equipo=" + equipo + ", nacionalidad=" + nacionalidad + ", kills="
				+ kills + ", deaths=" + deaths + ", killsDeaths=" + killsDeaths + ", kast=" + kast + ", ganancias="
				+ ganancias + ", rol=" + rol + ", headshot=" + headshot + ", rondasJugadas=" + rondasJugadas
				+ ", rondasGanadas=" + rondasGanadas + ", rondasPerdidas=" + rondasPerdidas + "]";
	}


	/*
	 * Método que crea todos los jugadores que se encuentran en el archivo de texto.
	 * Usamos las expresiones split y trim para separar la información y almacenarla en
	 * su su respectiva variable.
	 */
	public static ArrayList CreaJugadores() throws IOException {
		FileReader flujoLectura = null;
		BufferedReader filtroLectura = null;
		String linea;
		ArrayList<Jugador> jugadores = new ArrayList<>();
		try {
			flujoLectura = new FileReader("C://valorantChampionship.txt");
			filtroLectura = new BufferedReader(flujoLectura);
			
			filtroLectura.readLine();
			
			while((linea = filtroLectura.readLine()) != null) {
				String [] informacion = linea.split(",");
				
				Jugador jugador = new Jugador(informacion[0].trim(), informacion[1].trim(), 
							informacion[2].trim(), Integer.parseInt(informacion[3].trim()), Integer.parseInt(informacion[4].trim()),
							Double.parseDouble(informacion[5].trim()), informacion[6].trim(),
							Double.parseDouble(informacion[7].trim().replaceAll("[^\\d.]", "")), informacion[9].trim(),
							Double.parseDouble(informacion[10].trim()), Integer.parseInt(informacion[11].trim()),
							Integer.parseInt(informacion[12].trim()), Integer.parseInt(informacion[13].trim()));
				
				jugadores.add(jugador);
			}
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}finally {
			if(flujoLectura!=null) {
				flujoLectura.close();
			}
			if(filtroLectura != null) {
				filtroLectura.close();
			}
		}
	
		return jugadores;
	}
}

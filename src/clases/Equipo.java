package clases;

import java.util.*;
import java.io.*;

public class Equipo {
	private String nombre;
	private ArrayList<Jugador> jugadores;
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public ArrayList<Jugador> getJugadores() {
		return jugadores;
	}
	public void setJugadores(ArrayList<Jugador> jugadores) {
		this.jugadores = jugadores;
	}
	
	
	public Equipo(String nombre) {
		super();
		this.nombre = nombre;
		this.jugadores = new ArrayList<>();
	}
	
	
	public String toString() {
		return "Equipo [nombre=" + nombre + ", jugadores=" + jugadores + "]";
	}
	
	/*
	 * Método necesario para poder almacenar los jugadores en su equipo desde la clase Main
	 */
	public void agregaJugadores(Jugador jugador) {
		jugadores.add(jugador);
	}
	
	/*
	 * Método que nos permite comprobar que los jugadores se han almacenado de forma correcta
	 */
	public void mostrarJugadores() {
		for(Jugador jugador : jugadores) {
			System.out.println(jugador.toString());
		}
	}
	
}

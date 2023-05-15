package model.domain;

import java.sql.SQLException;
import java.util.List;

import model.enums.Background;
import model.enums.Genero;

public class Peleador extends Persona{
	
	private int edad;
	private Genero genero;
	private int peso;
	private int altura;
	private String record;
	private String pais;
	private Background background;
	private List<Evento> Eventos = null; 
	
	
	public Peleador() {
		super();
		this.edad=0;
		this.genero=null;
		this.peso=0;
		this.altura=0;
		this.record="";
		this.pais="";
		this.background=null;

	}

	public Peleador(String dni,String nombre, String apellidos, int edad, 
			Genero genero, int peso, int altura, String record, String pais, Background background) {
		super(dni,nombre,apellidos);
		this.edad = edad;
		this.genero = genero;
		this.peso = peso;
		this.altura = altura;
		this.record = record;
		this.pais = pais;
		this.background = background;
	}
	
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	public int getAltura() {
		return altura;
	}
	public void setAltura(int altura) {
		this.altura = altura;
	}
	public String getRecord() {
		return record;
	}
	public void setRecord(String record) {
		this.record = record;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public Background getBackground() {
		return background;
	}

	public void setBackground(Background background) {
		this.background = background;
	}

	public List<Evento> getEventos() {
		return Eventos;
	}

	public void setEventos(List<Evento> eventos) {
		Eventos = eventos;
	}

	@Override
	public String toString() {
		return "Peleador ["+super.toString()+"edad=" + edad + ", genero=" + genero + ", peso=" + peso + ", altura=" + altura + ", record="
				+ record + ", pais=" + pais + ", backgroud=" + background + "]";
	}
	
	
}


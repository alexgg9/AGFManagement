package model.domain;

public class Matchmaker extends Persona{

	private String promotora;


	
	public Matchmaker() {
		super();
		this.promotora="";
	}

	public Matchmaker(String dni, String nombre, String apellidos, String promotora) {
		super(dni, nombre, apellidos);
		this.promotora=promotora;
	}

	public String getPromotora() {
		return promotora;
	}

	public void setPromotora(String promotora) {
		this.promotora = promotora;
	}

	@Override
	public String toString() {
		return "Matchmaker ["+super.toString()+"promotora=" + promotora + "]";
	}

	
}


package AGFPromotions.ManagementFights.model.domain;

public class Matchmaker extends Persona{

	private String promotora;
	private String usuario;
	private String password;


	
	public Matchmaker() {
		super();
		this.promotora="";
		this.usuario="";
		this.password="";
	}
	public Matchmaker(String dni,String usuario) {
		super(dni,usuario,"");
	}

	public Matchmaker(String dni, String nombre, String apellidos, String promotora, String usuario, String password) {
		super(dni, nombre, apellidos);
		this.promotora=promotora;
		this.usuario=usuario;
		this.password=password;
	}

	public String getPromotora() {
		return promotora;
	}

	public void setPromotora(String promotora) {
		this.promotora = promotora;
	}
	
	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Matchmaker ["+super.toString()+"promotora=" + promotora + "usuario="+ usuario;
	}

	
}


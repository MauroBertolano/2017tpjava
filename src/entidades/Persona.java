package entidades;

public class Persona {
	private String nombre;
	private String apellido;
	private String dni;
	private Boolean habilitado;
	
	public Persona(String nombre, String apellido, String dni, Boolean habilitado) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.habilitado = habilitado;
	}
	public Persona() {
		super();
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public Boolean getHabilitado() {
		return habilitado;
	}
	public void setHabilitado(Boolean habilitado) {
		this.habilitado = habilitado;
	}
	
}

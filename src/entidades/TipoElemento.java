package entidades;

public class TipoElemento {
	private int id;
	private String nombre;
	private int cantMax;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getCantMax() {
		return cantMax;
	}
	public void setCantMax(int cantMax) {
		this.cantMax = cantMax;
	}
	@Override
	public String toString(){
		return (this.getId()+" - "+this.getNombre());
	}
	
	@Override
	public boolean equals(Object o){
		return (o instanceof TipoElemento && ((TipoElemento)o).getId()==this.getId());
	}
		
}

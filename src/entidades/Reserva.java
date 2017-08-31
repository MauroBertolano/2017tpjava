package entidades;

import java.sql.Date;

public class Reserva {
	private int id;
	private Date fecha;
	private int horaDesde;
	private int horaHasta;
	private Elemento elemento;
	private String Detalle;
	
	public String getDetalle() {
		return Detalle;
	}
	public void setDetalle(String detalle) {
		Detalle = detalle;
	}
	public Elemento getElemento() {
		return elemento;
	}
	public void setElemento(Elemento elemento) {
		this.elemento = elemento;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public int getHoraDesde() {
		return horaDesde;
	}
	public void setHoraDesde(int horaDesde) {
		this.horaDesde = horaDesde;
	}
	public int getHoraHasta() {
		return horaHasta;
	}
	public void setHoraHasta(int horaHasta) {
		this.horaHasta = horaHasta;
	}
	
	
	

}

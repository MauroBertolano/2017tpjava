package logica;

import java.util.ArrayList;

import data.DataTipoElemento;
import entidades.TipoElemento;
import util.PersonaInvalida;

public class ControladorTipoElemento {
	
	DataTipoElemento dataTipo= new DataTipoElemento();

	public TipoElemento getByNombre(TipoElemento tp) throws Exception {
		
		return dataTipo.getByNombre(tp);
	}

	public void add(TipoElemento tp)throws Exception {
		if(tp.getNombre().equals("") | tp.getCantMax()==0){
			throw new PersonaInvalida("Valores invalidos");
		}
		this.dataTipo.add(tp);
		}

	public void actualiza(TipoElemento tp)throws Exception{
		if(tp.getNombre().equals("") | tp.getCantMax()==0){
			throw new PersonaInvalida("Valores invalidos");}
		this.dataTipo.modificar(tp);
	}
	public void borrar(TipoElemento tp)throws Exception {
		dataTipo.remove(this.dataTipo.getByNombre(tp));
	}

	public ArrayList<TipoElemento> getTipos() throws Exception {
		return dataTipo.getAll();
	}
	
}

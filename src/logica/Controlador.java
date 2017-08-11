package logica;

import java.util.ArrayList;

import data.DataPersona;
import entidades.Persona;

public class Controlador {
	
	DataPersona dataPer= new DataPersona();
	ArrayList<Persona> pers=new ArrayList<Persona>();
	
	public void add(Persona p) {         //aumenta el id si aunque no se agregue nadie 
		try {
			this.dataPer.add(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Persona getByDni(Persona p) {
		return dataPer.getByDni(p);
	}

	public void borrar(Persona p) {
		pers.remove(this.getByDni(p));
	}

	public void actualiza(Persona p) {
		this.borrar(p);
		this.add(p);
	}
	public ArrayList<Persona> getPersonas(){
		return pers;
	}

	public Persona getByNombreApellido(Persona p) {
		for(int i=0;i<pers.size();i++){
			if(pers.get(i).getNombre().equalsIgnoreCase(p.getNombre()) && pers.get(i).getApellido().equalsIgnoreCase(p.getApellido())){
				return pers.get(i);
			}
		}
		return null;
	}
	public ArrayList<Persona> getAll(){
		return dataPer.getAll();
}
	
}
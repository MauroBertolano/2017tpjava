package logica;

import java.util.ArrayList;

import data.DataPersona;
import entidades.Persona;
import util.PersonaExistente;
import util.PersonaInvalida;

public class Controlador {
	
	DataPersona dataPer= new DataPersona();
	ArrayList<Persona> pers=new ArrayList<Persona>();
	
	public void add(Persona p) throws Exception  {
		if(p.getDni().equals("") | p.getApellido().equals("") | p.getNombre().equals("") ){
			throw new PersonaInvalida("Valores invalidos");}
		if(this.dataPer.getByDni(p)==null){
		this.dataPer.add(p);}
		else{
			throw new PersonaExistente("Ya existe la persona");}
		}

	public Persona getByDni(Persona p)throws Exception{
				return dataPer.getByDni(p);
	}

	public void borrar(Persona p)throws Exception {
		dataPer.remove(this.dataPer.getByDni(p));
	}

	public void actualiza(Persona p)throws Exception {
		this.borrar(p);
		try {
			this.add(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
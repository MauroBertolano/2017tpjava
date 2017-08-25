package logica;

import java.util.ArrayList;

import data.DataPersona;
import entidades.Persona;
import util.PersonaInvalida;

public class Controlador {
	
	DataPersona dataPer= new DataPersona();
	ArrayList<Persona> pers=new ArrayList<Persona>();
	
	public void add(Persona p) throws Exception  {
		if(p.getDni().equals("") | p.getApellido().equals("") | p.getNombre().equals("") ){
			throw new PersonaInvalida("Valores invalidos");
		}
		this.dataPer.add(p);
		}

	public Persona getByDni(Persona p)throws Exception{
				return dataPer.getByDni(p);
	}

	public void borrar(Persona p)throws Exception {
		dataPer.remove(this.dataPer.getByDni(p));
	}

	public void actualiza(Persona p)throws Exception {
		if(p.getDni().equals("") | p.getApellido().equals("") | p.getNombre().equals("") ){
			throw new PersonaInvalida("Valores invalidos");}
		this.dataPer.modificar(p);
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

	public Boolean validarUsuario(Persona p) throws Exception {
		String psw = p.getPsw();
		return (dataPer.getByUsuario(p).getPsw().equals(psw));
	}
	
}
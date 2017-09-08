
package data;

import entidades.Persona;

public class CuentaLogeada {
	private static Persona per;
	
	public static void asignarUsuario(Persona p){
		per = p;
	}
	public static Persona getUsuario(){
		return per;
	}
}
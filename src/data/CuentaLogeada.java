package data;

import entidades.Persona;

public class CuentaLogeada {
	private static Persona per;
	
	private CuentaLogeada() {
	}
	public static Persona getPer(Persona p){
		if(CuentaLogeada.per==null){
			CuentaLogeada.per = p;
		}
		return CuentaLogeada.per;
		
	}

}

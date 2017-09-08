package logica;

import java.util.ArrayList;

import data.DataElemento;
import entidades.Elemento;
import entidades.TipoElemento;
import util.ValorInvalido;

public class ControladorElemento {
	
	DataElemento dataEle= new DataElemento();

	public Elemento getByNombre(Elemento ele) throws Exception {		
		return dataEle.getByNombre(ele);
	}

	public void add(Elemento ele)throws Exception {
		if(ele.getNombre().equals("") ){
			throw new ValorInvalido("Valores invalidos");
		}
		this.dataEle.add(ele);
		}

	public void actualiza(Elemento ele)throws Exception{
		if(ele.getNombre().equals("")){
			throw new ValorInvalido("Valores invalidos");}
		this.dataEle.modificar(ele);
	}
	public void borrar(Elemento ele)throws Exception {
		dataEle.remove(ele);
	}

	public ArrayList<Elemento> getAll()throws Exception{		
		return this.dataEle.getAll();
	}
}

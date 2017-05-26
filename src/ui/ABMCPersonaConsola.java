package ui;

import java.util.ArrayList;
import java.util.Scanner;

import entidades.Persona;
import logica.Controlador;

public class ABMCPersonaConsola {
	private Scanner s;
	private Controlador ctrl;

	public ABMCPersonaConsola() {
		s = new Scanner(System.in);
		ctrl = new Controlador();
	}
	
	public void inicio(){
		Boolean seguir=true;
		String rta="";
		do {
			System.out.println("\n\n ###############\n");
			System.out.println("Ingrese una opcion");
			System.out.println("1- Alta de persona");
			System.out.println("2- Baja de persona");
			System.out.println("3- Modificacion de persona");
			System.out.println("4- Consulta de persona");
			System.out.println("5- salir");
			rta=s.nextLine();
			switch (rta) {
			case "1":
				this.alta();
				break;
			case "2":
				this.baja();
				break;
			case "3":
				this.modificacion();
				break;
			case "4":
				this.consulta();
				break;
			case "5":
				seguir=false;
				break;
			}
		} while (seguir);
		s.close();
	}

	public void alta() {
		String rta = "";
		System.out.println("Nueva Persona");
		Persona p = new Persona();
		System.out.println("Ingrese Nombre");
		p.setNombre(s.nextLine());
		System.out.println("Ingrese Apellido");
		p.setApellido(s.nextLine());
		System.out.println("Ingrese DNI");
		p.setDni(s.nextLine());
		System.out.println("Esta habilitado s/n");
		rta = s.nextLine();
		if (rta.equalsIgnoreCase("s")) {
			p.setHabilitado(true);
		} else {
			p.setHabilitado(false);
		}
		ctrl.add(p);
	}

	public void baja() {
		System.out.println("Baja Persona");
		Persona p = new Persona();
		System.out.println("Ingrese DNI de la persona que quiere dar de baja");
		p.setDni(s.nextLine());
		ctrl.borrar(ctrl.getByDni(p));

	}

	public void modificacion() {
		String rta;
		System.out.println("Modificar Persona");
		Persona p = new Persona();
		System.out.println("Ingrese DNI de la persona a modificar");
		p.setDni(s.nextLine());
		this.mostrar(ctrl.getByDni(p));
		System.out.println("Ingrese nuevo nombre");
		p.setNombre(s.nextLine());
		System.out.println("Ingrese nuevo apellido");
		p.setApellido(s.nextLine());
		System.out.println("Esta habilitado? s/n");
		rta = s.nextLine();
		if (rta.equalsIgnoreCase("s")) {
			p.setHabilitado(true);
		} else {
			p.setHabilitado(false);
		}
		ctrl.actualiza(p);
	}

	public void consulta() {
		Persona p = new Persona();
		System.out.println("Consulta de persona");
		System.out.println("Elija una opcion");
		System.out.println("1- Listar todas las personas");
		System.out.println("2- Por dni");
		System.out.println("3- Por nombre y apellido");
		String rta = s.nextLine();
		switch (rta) {
		case "1":
			this.mostrar(ctrl.getPersonas());

			break;
		case "2":
			System.out.println("Ingrese dni de la persona");
			p.setDni(s.nextLine());
			this.mostrar(ctrl.getByDni(p));
			break;
		case "3":
			System.out.println("Ingrese nombre");
			p.setNombre(s.nextLine());
			System.out.println("Ingrese apellido");
			p.setApellido(s.nextLine());
			this.mostrar(ctrl.getByNombreApellido(p));
			break;

		default:
			break;
		}

	}

	private void mostrar(Persona p) {
		String h = "";
		if (p.getHabilitado()) {
			h = "Habilitado";
		} else {
			h = "Deshabilitado";
		}
		System.out.println("Nombre: " + p.getNombre() + " Apellido: " + p.getApellido() + " dni: " + p.getDni() +" "+ h);
	}
	private void mostrar(ArrayList<Persona> p) {
		for (Persona persona : p) {
			this.mostrar(persona);
		}
	}
}

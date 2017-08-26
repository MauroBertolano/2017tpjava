package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JDesktopPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;

public class ABMCDesktopWindow extends JFrame {

	private JPanel contentPane;
	private JDesktopPane desktopPane;

	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ABMCDesktopWindow frame = new ABMCDesktopWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public ABMCDesktopWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 440);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnMenu = new JMenu("Menu");
		menuBar.add(mnMenu);
		
		JMenuItem mntmAbmc = new JMenuItem("ABMCPersona");
		mntmAbmc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showABMC();
			}
		});
		mnMenu.add(mntmAbmc);
		
		JMenuItem mntmAbmctipoelemento = new JMenuItem("ABMCTipoElemento");
		mntmAbmctipoelemento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showABMCTipo();
			}
		});
		mnMenu.add(mntmAbmctipoelemento);
		
		JMenuItem mntmAbmcelemento = new JMenuItem("ABMCElemento");
		mntmAbmcelemento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showABMCEle();
			}
		});
		mnMenu.add(mntmAbmcelemento);
		
		JMenuItem mntmListado = new JMenuItem("Listado");
		mntmListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showListado();
			}
		});
		
		JMenuItem mntmAbmcreserva = new JMenuItem("Reservar");
		mntmAbmcreserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showReserva();
			}
		});
		mnMenu.add(mntmAbmcreserva);
		
		JMenuItem mntmReser = new JMenuItem("Consultar Reservas");
		mntmReser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showConsultaReserva();
			}
		});
		mnMenu.add(mntmReser);
		mnMenu.add(mntmListado);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		desktopPane = new JDesktopPane();
		contentPane.add(desktopPane, BorderLayout.CENTER);
		desktopPane.setLayout(new BorderLayout(0, 0));
	}
protected void showConsultaReserva() {
	ListadoReservas res = new ListadoReservas();
	desktopPane.add(res);
	res.setVisible(true);
	}
protected void showReserva() {
	ABMCReserva res = new ABMCReserva();
	desktopPane.add(res);
	res.setVisible(true);
	}
protected void showListado() {
	ListadoElementos lista = new ListadoElementos();
	desktopPane.add(lista);
	lista.setVisible(true);
	}

protected void showABMCEle() {
		ABMCElemento abmcEle = new ABMCElemento();
		desktopPane.add(abmcEle);
		abmcEle.setVisible(true);
		
	}

protected void showABMCTipo() {
	ABMCTipoElemento abmcTipo = new ABMCTipoElemento();
	desktopPane.add(abmcTipo);
	abmcTipo.setVisible(true);
	}

protected void showABMC(){
	ABMCPersona abmcPersona = new ABMCPersona();
	desktopPane.add(abmcPersona);
	abmcPersona.setVisible(true);
}
}
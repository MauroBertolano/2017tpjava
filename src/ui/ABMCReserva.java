package ui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JScrollBar;

public class ABMCReserva extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ABMCReserva frame = new ABMCReserva();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ABMCReserva() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JList list = new JList();
		list.setBounds(97, 112, 159, -31);
		getContentPane().add(list);
		

	}
}

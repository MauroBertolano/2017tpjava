package ui;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import entidades.Reserva;
import logica.ControladorReserva;

import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;
import java.sql.Date;

public class ListadoReservas extends JInternalFrame {
	private JTable table;
	private ArrayList<Reserva> res = new ArrayList<Reserva>();
	private ControladorReserva ctrlRes= new ControladorReserva();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListadoReservas frame = new ListadoReservas();
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
	public ListadoReservas() {
		setClosable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 631, 362);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 595, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 285, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(36, Short.MAX_VALUE))
		);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		getContentPane().setLayout(groupLayout);
		
		try {
			this.res = ctrlRes.getAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		initDataBindings();
	}
	protected void initDataBindings() {
		JTableBinding<Reserva, List<Reserva>, JTable> jTableBinding = SwingBindings.createJTableBinding(UpdateStrategy.READ, res, table);
		//
		BeanProperty<Reserva, Integer> reservaBeanProperty = BeanProperty.create("id");
		jTableBinding.addColumnBinding(reservaBeanProperty).setColumnName("IDReserva");
		//
		BeanProperty<Reserva, Date> reservaBeanProperty_1 = BeanProperty.create("fecha");
		jTableBinding.addColumnBinding(reservaBeanProperty_1).setColumnName("FechaReserva");
		//
		BeanProperty<Reserva, Integer> reservaBeanProperty_2 = BeanProperty.create("hora");
		jTableBinding.addColumnBinding(reservaBeanProperty_2).setColumnName("HoraReserva");
		//
		BeanProperty<Reserva, Integer> reservaBeanProperty_3 = BeanProperty.create("elemento.id");
		jTableBinding.addColumnBinding(reservaBeanProperty_3).setColumnName("IDElemento");
		//
		BeanProperty<Reserva, String> reservaBeanProperty_4 = BeanProperty.create("elemento.nombre");
		jTableBinding.addColumnBinding(reservaBeanProperty_4).setColumnName("NombreElemento");
		//
		BeanProperty<Reserva, String> reservaBeanProperty_5 = BeanProperty.create("elemento.tipo.nombre");
		jTableBinding.addColumnBinding(reservaBeanProperty_5).setColumnName("TipoElemento");
		//
		BeanProperty<Reserva, String> reservaBeanProperty_6 = BeanProperty.create("detalle");
		jTableBinding.addColumnBinding(reservaBeanProperty_6).setColumnName("Detalle");
		//
		jTableBinding.bind();
	}
}

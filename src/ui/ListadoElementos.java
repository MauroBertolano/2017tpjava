package ui;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;

import entidades.Elemento;
import logica.ControladorElemento;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.ObjectProperty;
import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.Bindings;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;

public class ListadoElementos extends JInternalFrame {

	private JTable table;
	private ArrayList<Elemento> elem;
	ControladorElemento ctrl = new ControladorElemento();

	/**
	 * Create the frame.
	 */
	public ListadoElementos() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setTitle("Listado de Elementos");
		setBounds(100, 100, 556, 456);

		JScrollPane scrollPane = new JScrollPane();

		JButton btnEditar = new JButton("Editar");
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				editarClick();
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout
				.setHorizontalGroup(
						groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup().addGap(24)
										.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 491,
												GroupLayout.PREFERRED_SIZE)
										.addContainerGap(25, Short.MAX_VALUE))
								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
										.addContainerGap(411, Short.MAX_VALUE).addComponent(btnEditar).addGap(40)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(19)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 337, GroupLayout.PREFERRED_SIZE)
						.addGap(18).addComponent(btnEditar).addContainerGap(29, Short.MAX_VALUE)));

		table = new JTable();
		scrollPane.setViewportView(table);
		getContentPane().setLayout(groupLayout);
		try {
			this.elem = ctrl.getAll();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

		}
		initDataBindings();

	}

	protected void editarClick() {
		int indexPersona = table.convertRowIndexToModel(table.getSelectedRow());

		ABMCElemento pd = new ABMCElemento();
		pd.showElemento(this.elem.get(indexPersona));

		this.getDesktopPane().add(pd);
		pd.setVisible(true);
	}
	protected void initDataBindings() {
		JTableBinding<Elemento, List<Elemento>, JTable> jTableBinding = SwingBindings.createJTableBinding(UpdateStrategy.READ, elem, table);
		//
		BeanProperty<Elemento, Integer> elementoBeanProperty = BeanProperty.create("id");
		jTableBinding.addColumnBinding(elementoBeanProperty).setColumnName("IdElemento").setEditable(false);
		//
		BeanProperty<Elemento, String> elementoBeanProperty_1 = BeanProperty.create("nombre");
		jTableBinding.addColumnBinding(elementoBeanProperty_1).setColumnName("NombreElemento").setEditable(false);
		//
		BeanProperty<Elemento, Integer> elementoBeanProperty_2 = BeanProperty.create("tipo.id");
		jTableBinding.addColumnBinding(elementoBeanProperty_2).setColumnName("IDTipoElemento").setEditable(false);
		//
		BeanProperty<Elemento, String> elementoBeanProperty_3 = BeanProperty.create("tipo.nombre");
		jTableBinding.addColumnBinding(elementoBeanProperty_3).setColumnName("NombreTipoElemento").setEditable(false);
		//
		BeanProperty<Elemento, Integer> elementoBeanProperty_4 = BeanProperty.create("tipo.cantMax");
		jTableBinding.addColumnBinding(elementoBeanProperty_4).setColumnName("CantidadMaxima").setEditable(false);
		//
		jTableBinding.setEditable(false);
		jTableBinding.bind();
	}
}

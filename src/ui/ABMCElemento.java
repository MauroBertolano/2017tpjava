package ui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import entidades.Elemento;
import entidades.TipoElemento;
import logica.ControladorElemento;
import logica.ControladorTipoElemento;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ABMCElemento extends JInternalFrame {
	
	private JTextField txtNombre;
	private ControladorElemento ctrl= new ControladorElemento();
	private ControladorTipoElemento ctrlTipo= new ControladorTipoElemento();
	private JComboBox cboTipo;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ABMCElemento frame = new ABMCElemento();
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
	public ABMCElemento() {
		setTitle("ABMCElementos");
		setBounds(100, 100, 300, 244);
		
		JLabel lblId = new JLabel("ID");
		
		JLabel lblNombre = new JLabel("Nombre");
		
		JLabel lblTipo = new JLabel("Tipo");
		
		JLabel lblIdOculta = new JLabel("");
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		
		cboTipo = new JComboBox();
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				agregarClick();
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(27)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblId)
									.addGap(48)
									.addComponent(lblIdOculta))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNombre)
										.addComponent(lblTipo))
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(cboTipo, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(txtNombre)))))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnAgregar)))
					.addContainerGap(116, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblId)
						.addComponent(lblIdOculta))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNombre)
						.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTipo)
						.addComponent(cboTipo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(43)
					.addComponent(btnAgregar)
					.addContainerGap(46, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
		cargarListas();
	}

	protected void agregarClick() {
		try {
			this.ctrl.add(this.mapearDeForm());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void cargarListas() {
		try {
			this.cboTipo.setModel(new DefaultComboBoxModel(ctrlTipo.getTipos().toArray()));
			this.cboTipo.setSelectedIndex(-1);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());}
	}
	private Elemento mapearDeForm(){
		Elemento ele = new Elemento();
		ele.setNombre(this.txtNombre.getText());
		if (cboTipo.getSelectedIndex() != -1){
			ele.setTipo((TipoElemento)this.cboTipo.getSelectedItem());
		}
		return ele;
}
}
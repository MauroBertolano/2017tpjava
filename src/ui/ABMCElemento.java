package ui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import logica.ControladorElemento;
import logica.ControladorTipoElemento;

import javax.swing.JTextField;
import javax.swing.JComboBox;

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
		setBounds(100, 100, 450, 300);
		
		JLabel lblId = new JLabel("ID");
		
		JLabel lblNombre = new JLabel("Nombre");
		
		JLabel lblTipo = new JLabel("Tipo");
		
		JLabel lblIdOculta = new JLabel("");
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		
		cboTipo = new JComboBox();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
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
								.addComponent(txtNombre))))
					.addContainerGap(266, Short.MAX_VALUE))
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
					.addContainerGap(180, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
		cargarListas();
	}

	private void cargarListas() {
		try {
			this.cboTipo.setModel(new DefaultComboBoxModel(ctrlTipo.getTipos().toArray()));
			this.cboTipo.setSelectedIndex(-1);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
}
	}
}

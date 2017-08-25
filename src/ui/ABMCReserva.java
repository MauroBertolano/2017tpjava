package ui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JLabel;
import javax.swing.JTextField;

import entidades.Elemento;
import entidades.Reserva;
import entidades.TipoElemento;
import logica.ControladorReserva;
import logica.ControladorTipoElemento;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;

public class ABMCReserva extends JInternalFrame {
	private JTextField txtFecha;
	private JTextField txtHora;
	private JTextField txtDetalle;
	private JTextField txtNombre;
	private ControladorTipoElemento ctrlTipo= new ControladorTipoElemento();
	private ControladorReserva ctrlRes= new ControladorReserva();
	private ArrayList<Elemento> elemDisp = new ArrayList<Elemento>();
	private JComboBox cboTipos;
	private JLabel lblIdOculta;
	private JLabel lblIdOculta2;
	private JTable table;
	
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

	public ABMCReserva() {
		setClosable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setTitle("ABMCReserva");
		setBounds(100, 100, 892, 430);
		
		JLabel lblIdReserva = new JLabel("ID Reserva");
		
		JLabel lblFecha = new JLabel("Fecha");
		
		JLabel lblHora = new JLabel("Hora");
		
		JLabel lblDetalle = new JLabel("Detalle de la reserva");
		
		JLabel lblReserva = new JLabel("RESERVA");
		
		JLabel lblElemento = new JLabel("ELEMENTO");
		
		JLabel lblId = new JLabel("ID Elemento");
		
		JLabel lblNombre = new JLabel("Nombre");
		
		JLabel lblTipoelemento = new JLabel("TipoElemento");
		
		lblIdOculta = new JLabel("");
		
		txtFecha = new JTextField();
		txtFecha.setColumns(10);
		
		txtHora = new JTextField();
		txtHora.setColumns(10);
		
		txtDetalle = new JTextField();
		txtDetalle.setColumns(10);
		
		lblIdOculta2 = new JLabel("");
		
		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		txtNombre.setColumns(10);
		
		JButton btnBuscarElementos = new JButton("Buscar Elementos");
		btnBuscarElementos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				buscarDisponibles();
			}
		});
		btnBuscarElementos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		cboTipos = new JComboBox();
		
		JButton btnReservar = new JButton("Reservar");
		btnReservar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				reservarClick();
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				seleccionarClick();
			}
		});
		
		JLabel lblAaaammdd = new JLabel("aaaa/mm/dd");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(28)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnSeleccionar)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnReservar)
								.addComponent(lblElemento, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblReserva, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblIdReserva, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
									.addGap(23)
									.addComponent(lblIdOculta, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblFecha, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
									.addGap(23)
									.addComponent(txtFecha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblAaaammdd))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblDetalle)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(txtDetalle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblId, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
											.addGap(18)
											.addComponent(lblIdOculta2, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblNombre, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
											.addGap(18)
											.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblTipoelemento, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
											.addGap(23)
											.addComponent(cboTipos, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblHora, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
											.addGap(23)
											.addComponent(txtHora, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnBuscarElementos, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
									.addGap(8)))
							.addGap(34)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 462, GroupLayout.PREFERRED_SIZE)))
					.addGap(34))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(31)
							.addComponent(lblReserva)
							.addGap(11)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblIdReserva)
								.addComponent(lblIdOculta, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
							.addGap(8)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(3)
									.addComponent(lblFecha))
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
									.addComponent(txtFecha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblAaaammdd)))
							.addGap(5)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(3)
									.addComponent(lblHora))
								.addComponent(txtHora, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(4)
									.addComponent(lblTipoelemento))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(1)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnBuscarElementos)
										.addComponent(cboTipos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
							.addGap(12)
							.addComponent(lblElemento)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblId)
								.addComponent(lblIdOculta2, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
							.addGap(11)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNombre)
								.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(44)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblDetalle, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtDetalle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(39)
							.addComponent(btnReservar))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(44)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 305, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnSeleccionar)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		getContentPane().setLayout(groupLayout);
		
		cargarListas();

	}

	protected void seleccionarClick() {
		int indexElemento = table.convertRowIndexToModel(table.getSelectedRow());
		this.mapearAForm(this.elemDisp.get(indexElemento));
		
	}

	protected void reservarClick() {
		try {
			this.ctrlRes.addReserva(this.mapearDeForm());
			JOptionPane.showMessageDialog(this, "Reserva realizada");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void buscarDisponibles() {
		try {
			this.elemDisp = this.ctrlRes.getDisponibles(this.mapearDeForm());
			initDataBindings();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void cargarListas() {
		try {
			this.cboTipos.setModel(new DefaultComboBoxModel(ctrlTipo.getTipos().toArray()));
			this.cboTipos.setSelectedIndex(-1);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	private Reserva mapearDeForm(){
		Reserva res = new Reserva();
		Elemento el = new Elemento();
		res.setElemento(el);
		if(!this.lblIdOculta.getText().isEmpty()){
			res.setId(Integer.parseInt(this.lblIdOculta.getText()));
			}
		res.setFecha(Date.valueOf(this.txtFecha.getText()));
		res.setHora(Integer.parseInt(this.txtHora.getText()));
		if (cboTipos.getSelectedIndex() != -1){
			res.getElemento().setTipo((TipoElemento)this.cboTipos.getSelectedItem());}
		if(!this.lblIdOculta2.getText().isEmpty()){
			el.setId(Integer.parseInt(this.lblIdOculta2.getText()));
			}
		if(!this.txtNombre.getText().isEmpty()){
			el.setNombre(this.txtNombre.getText());
			}
		if(!this.txtDetalle.getText().isEmpty()){
			res.setDetalle(this.txtDetalle.getText());
			}
		return res;
	}
	private void mapearAForm(Elemento e){
		lblIdOculta2.setText(Integer.toString(e.getId()));
		txtNombre.setText(e.getNombre());
	}
	protected void initDataBindings() {
		JTableBinding<Elemento, List<Elemento>, JTable> jTableBinding = SwingBindings.createJTableBinding(UpdateStrategy.READ, elemDisp, table);
		//
		BeanProperty<Elemento, Integer> elementoBeanProperty = BeanProperty.create("id");
		jTableBinding.addColumnBinding(elementoBeanProperty).setColumnName("IDelemento");
		//
		BeanProperty<Elemento, String> elementoBeanProperty_1 = BeanProperty.create("nombre");
		jTableBinding.addColumnBinding(elementoBeanProperty_1).setColumnName("NombreElemento");
		//
		BeanProperty<Elemento, Integer> elementoBeanProperty_2 = BeanProperty.create("tipo.cantMax");
		jTableBinding.addColumnBinding(elementoBeanProperty_2).setColumnName("CantidadMax");
		//
		jTableBinding.setEditable(false);
		jTableBinding.bind();
	}
}

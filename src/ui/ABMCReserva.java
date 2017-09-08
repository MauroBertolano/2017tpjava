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
import util.AppDataException;

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
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Date;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;
import com.toedter.calendar.JDateChooser;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class ABMCReserva extends JInternalFrame {
	private JTextField txtHoraDesde;
	private JTextField txtDetalle;
	private JTextField txtNombre;
	private ControladorTipoElemento ctrlTipo= new ControladorTipoElemento();
	private ControladorReserva ctrlRes= new ControladorReserva();
	private ArrayList<Elemento> elemDisp = new ArrayList<Elemento>();
	private JComboBox cboTipos;
	private JLabel lblIdOculta;
	private JLabel lblIdOculta2;
	private JTable table;
	private JDateChooser dateCh;
	private JTextField txtMinutoDesde;
	private JTextField txtHoraHasta;
	private JTextField txtMinutoHasta;
	
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
		setTitle("Reservar");
		setBounds(100, 100, 947, 552);
		
		JButton btnBuscarElementos = new JButton("Buscar Elementos");
		btnBuscarElementos.setBounds(302, 192, 122, 23);
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
		
		JButton btnReservar = new JButton("Reservar");
		btnReservar.setBounds(10, 373, 86, 23);
		btnReservar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				reservarClick();
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(430, 54, 462, 305);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 54, 286, 161);
		panel.setBorder(new TitledBorder(null, "RESERVA", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 226, 286, 102);
		panel_1.setBorder(new TitledBorder(null, "ELEMENTO", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JButton btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.setBounds(777, 370, 120, 23);
		btnSeleccionar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				seleccionarClick();
			}
		});
		
		JLabel lblDetalle = new JLabel("Detalle de la reserva");
		lblDetalle.setBounds(10, 339, 115, 28);
		
		txtDetalle = new JTextField();
		txtDetalle.setBounds(129, 343, 86, 20);
		txtDetalle.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		
		JLabel lblId = new JLabel("ID Elemento");
		
		lblIdOculta2 = new JLabel("");
		
		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		txtNombre.setColumns(10);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblId, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblIdOculta2, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblNombre, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(40, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(20)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblId)
						.addComponent(lblIdOculta2, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNombre)
						.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(14, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		txtHoraHasta = new JTextField();
		txtHoraHasta.setColumns(10);
		
		JLabel label_1 = new JLabel(":");
		
		txtMinutoHasta = new JTextField();
		txtMinutoHasta.setColumns(10);
		
		JLabel lblIdReserva = new JLabel("ID Reserva");
		
		lblIdOculta = new JLabel("");
		
		JLabel lblHoraDesde = new JLabel("HoraDesde");
		
		JLabel lblFecha = new JLabel("Fecha");
		
		JLabel lblHorahasta = new JLabel("HoraHasta");
		
		txtHoraDesde = new JTextField();
		txtHoraDesde.setColumns(10);
		
		JLabel label = new JLabel(":");
		
		txtMinutoDesde = new JTextField();
		txtMinutoDesde.setColumns(10);
		
		dateCh = new JDateChooser();
		dateCh.setDateFormatString("yyyy-MM-dd");
		
		JLabel lblTipoelemento = new JLabel("TipoElemento");
		
		cboTipos = new JComboBox();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblIdReserva, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
							.addGap(23)
							.addComponent(lblIdOculta, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblFecha, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
							.addGap(23)
							.addComponent(dateCh, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblHoraDesde, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
							.addGap(23)
							.addComponent(txtHoraDesde, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(label)
							.addGap(4)
							.addComponent(txtMinutoDesde, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblHorahasta, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
							.addGap(23)
							.addComponent(txtHoraHasta, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(label_1)
							.addGap(4)
							.addComponent(txtMinutoHasta, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblTipoelemento, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
							.addGap(23)
							.addComponent(cboTipos, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(26, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGap(1)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblIdReserva)
						.addComponent(lblIdOculta, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(5)
							.addComponent(lblFecha))
						.addComponent(dateCh, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(3)
							.addComponent(lblHoraDesde))
						.addComponent(txtHoraDesde, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(3)
							.addComponent(label))
						.addComponent(txtMinutoDesde, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblHorahasta)
						.addComponent(txtHoraHasta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_1)
						.addComponent(txtMinutoHasta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(3)
							.addComponent(lblTipoelemento))
						.addComponent(cboTipos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(25, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		getContentPane().setLayout(null);
		getContentPane().add(btnReservar);
		getContentPane().add(lblDetalle);
		getContentPane().add(txtDetalle);
		getContentPane().add(panel);
		getContentPane().add(panel_1);
		getContentPane().add(btnBuscarElementos);
		getContentPane().add(scrollPane);
		getContentPane().add(btnSeleccionar);
		
		cargarListas();

	}

	protected void seleccionarClick() {
		if (cboTipos.getSelectedIndex() != -1){
			this.txtNombre.setText(elemDisp.get(table.getSelectedRow()).getNombre());
			this.lblIdOculta2.setText(Integer.toString(elemDisp.get(table.getSelectedRow()).getId()));
						
		}
	}

	protected void reservarClick() {
		try {
			if(!this.txtNombre.getText().isEmpty()){
			this.ctrlRes.addReserva(this.mapearDeForm());
			JOptionPane.showMessageDialog(this, "Reserva realizada");}
			else{JOptionPane.showMessageDialog(this, "Seleccione un objeto");}
		}	catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Error al realizar reserva");
		}
	}

	protected void buscarDisponibles() {
		SimpleDateFormat d = new SimpleDateFormat("yy-MM-dd");
		Date date = new Date();
		String hoy = d.format(date);
		try {
			if(!this.txtHoraDesde.getText().isEmpty() && !this.txtMinutoDesde.getText().isEmpty() && !this.txtHoraHasta.getText().isEmpty() && !this.txtMinutoHasta.getText().isEmpty()){
				if (Integer.parseInt(this.txtHoraDesde.getText()+this.txtMinutoDesde.getText())<Integer.parseInt(this.txtHoraHasta.getText()+this.txtMinutoHasta.getText())) {
					if (d.format(this.dateCh.getDate()).equals(hoy) || this.dateCh.getDate().compareTo(date)>=0){
						this.elemDisp = this.ctrlRes.getDisponibles(this.mapearDeForm());
						initDataBindings();
					}else{JOptionPane.showMessageDialog(this, "La fecha seleccionada es anterior a la fecha actual");}
				} else {
					JOptionPane.showMessageDialog(this, "La hora desde es mayor o igual que la hora hasta");
				}}
			else {
				JOptionPane.showMessageDialog(this, "Rellene todos los campos");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Datos ingresados incorrectos");
		}
		catch (AppDataException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
		catch (Exception e) {
		JOptionPane.showMessageDialog(this, "Rellene todos los campos");
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
		Date fecha = dateCh.getDate();
		long d = fecha.getTime();
		java.sql.Date fechaa = new java.sql.Date(d);
		res.setElemento(el);
		if(!this.lblIdOculta.getText().isEmpty()){
			res.setId(Integer.parseInt(this.lblIdOculta.getText()));
			}
		res.setFecha(fechaa);
		if(!this.txtHoraDesde.getText().isEmpty() && !this.txtMinutoDesde.getText().isEmpty()){
		res.setHoraDesde(Integer.parseInt(this.txtHoraDesde.getText()+this.txtMinutoDesde.getText()));}
		if(!this.txtHoraHasta.getText().isEmpty() && !this.txtMinutoHasta.getText().isEmpty()){
		res.setHoraHasta(Integer.parseInt(this.txtHoraHasta.getText()+this.txtMinutoHasta.getText()));}
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

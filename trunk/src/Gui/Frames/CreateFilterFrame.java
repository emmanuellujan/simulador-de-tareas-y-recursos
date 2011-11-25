
/*
 * CreateFilterFrame.java
 *
 * Created on 15/11/2011, 11:33:15 AM
 */
package Gui.Frames;

import java.util.Vector;

import Controller.FilterSystem.AndFilter;
import Controller.FilterSystem.EqualFilter;
import Controller.FilterSystem.Filter;
import Controller.FilterSystem.JobPositionFilter;
import Controller.FilterSystem.PropertyListFilter;
import Controller.SchedulingSystem.Resource;

/**
 * 
 * @author F.Rossi
 */
public class CreateFilterFrame extends javax.swing.JFrame {

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed"
		// desc=" Look and feel setting code (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the
		 * default look and feel. For details see
		 * http://download.oracle.com/javase
		 * /tutorial/uiswing/lookandfeel/plaf.html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
					.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(
					CreateFilterFrame.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(
					CreateFilterFrame.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(
					CreateFilterFrame.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(
					CreateFilterFrame.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		}
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {

			public void run() {
				if (this == null)
					new CreateFilterFrame();
			}
		});
	}

	private String backFrameId;

	private String filterType;// Tipo del filtro a crear en este panel

	private CreateTaskFrame taskFrame;// Panel de vuelta, una vez creado el
										// filtro

	// private CreateFilterFrame filterFrame;//Variable que representa esta
	// ventana, para que otros puedan trabajar en ella
	private CreateFilterFrame filterBackFrame;// Panel de vuelta, una vez creado
												// el filtro

	private UpdaterFrame updaterFrame;// Panel de vuelta, una vez creado el
										// filtro

	private Vector resourcesMainList;// Lista total de recursos, artefactos y
										// actores

	private Vector<Resource> resourcesList; // Lista de artefactos

	private Filter filterFrameI;// Frame para crear el 1er filtro del and

	private Filter filterFrameII;// Frame para crear el 2do filtro del and

	private boolean filterFrameStateI;

	private boolean filterFrameStateII;

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton jButton1;

	private javax.swing.JButton jButton2;

	private javax.swing.JButton jButton3;

	private javax.swing.JButton jButton4;

	private javax.swing.JComboBox jComboBox1;

	private javax.swing.JComboBox jComboBox2;

	private javax.swing.JComboBox jComboBox3;

	private javax.swing.JComboBox jComboBox4;

	private javax.swing.JLabel jLabel1;

	private javax.swing.JLabel jLabel2;

	private javax.swing.JLabel jLabel3;

	private javax.swing.JLabel jLabel4;

	private javax.swing.JLabel jLabel5;

	private javax.swing.JLabel jLabel6;

	private javax.swing.JLabel jLabel7;

	private javax.swing.JLabel jLabel8;

	private javax.swing.JLabel jLabel9;

	private javax.swing.JPanel jPanel1;

	private javax.swing.JPanel jPanel2;

	private javax.swing.JTextField jTextField1;

	private javax.swing.JTextField jTextField2;

	private javax.swing.JTextField jTextField3;

	// End of variables declaration//GEN-END:variables

	/** Creates new form CreateFilterFrame */
	public CreateFilterFrame() {
		initComponents();
	}

	public void fillObjectCombobox() {
		this.jComboBox1.removeAllItems();
		for (int i = 0; i < this.getMainResourcesList().size(); i++) {
			this.jComboBox1.addItem(((Resource) this.getMainResourcesList()
					.elementAt(i)).getResId());
		}
	}

	public void fillResourceCombobox() {
		this.jComboBox3.removeAllItems();
		for (int i = 0; i < this.getResourcesList().size(); i++) {
			this.jComboBox3.addItem(((Resource) this.getResourcesList()
					.elementAt(i)).getResId());
		}
	}

	public Object getBackFrame() {
		if (this.taskFrame != null)
			return this.taskFrame;
		else {
			if (this.filterBackFrame != null)
				return this.filterBackFrame;
			else
				return this.updaterFrame;
		}
	}

	public String getBackFrameId() {
		return this.backFrameId;
	}

	private Resource getElement(String id) {
		for (int i = 0; i < this.getMainResourcesList().size(); i++) {
			if (((Resource) this.getMainResourcesList().elementAt(i))
					.getResId().equals(this.jComboBox1.getSelectedItem())) {
				return (Resource) this.getMainResourcesList().elementAt(i);
			}
		}
		return null;
	}

	public Filter getFilterFrameI() {
		return this.filterFrameI;
	}

	public Filter getFilterFrameII() {
		return this.filterFrameII;
	}

	public boolean getFilterFrameStateI() {
		return this.filterFrameStateI;
	}

	public boolean getFilterFrameStateII() {
		return this.filterFrameStateII;
	}

	public String getFilterType() {
		return this.filterType;
	}

	public Vector getMainResourcesList() {
		return this.resourcesMainList;
	}

	private Resource getResource(String id) {
		for (int i = 0; i < this.getResourcesList().size(); i++) {
			if (this.getResourcesList().elementAt(i).getResId()
					.equals(this.jComboBox3.getSelectedItem())) {
				return this.getResourcesList().elementAt(i);
			}
		}
		return null;
	}

	public Vector<Resource> getResourcesList() {
		return resourcesList;
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed"
	// desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		jButton1 = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jButton3 = new javax.swing.JButton();
		jButton4 = new javax.swing.JButton();
		jPanel2 = new javax.swing.JPanel();
		jLabel3 = new javax.swing.JLabel();
		jComboBox1 = new javax.swing.JComboBox();
		jLabel4 = new javax.swing.JLabel();
		jTextField1 = new javax.swing.JTextField();
		jLabel6 = new javax.swing.JLabel();
		jTextField2 = new javax.swing.JTextField();
		jLabel7 = new javax.swing.JLabel();
		jTextField3 = new javax.swing.JTextField();
		jLabel8 = new javax.swing.JLabel();
		jComboBox3 = new javax.swing.JComboBox();
		jLabel5 = new javax.swing.JLabel();
		jComboBox2 = new javax.swing.JComboBox();
		jLabel9 = new javax.swing.JLabel();
		jComboBox4 = new javax.swing.JComboBox();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
				"Filter configuration",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Verdana", 1, 11))); // NOI18N

		jButton1.setText("Cancel");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		jButton2.setText("Create");
		jButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton2ActionPerformed(evt);
			}
		});

		jLabel1.setText("Composition Filter (1)");

		jLabel2.setText("Composition Filter (2)");

		jButton3.setText("Create Filter");
		jButton3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton3ActionPerformed(evt);
			}
		});

		jButton4.setText("Create Filter");
		jButton4.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton4ActionPerformed(evt);
			}
		});

		jPanel2.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Simple Filter"));

		jLabel3.setText("Select object to compare ");

		jLabel4.setText("String/Value to compare");

		jLabel6.setText("Property Filter key");

		jLabel7.setText("Property Filter value");

		jLabel8.setText("Resource to compare");

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(
				jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout
				.setHorizontalGroup(jPanel2Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel2Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel2Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(jLabel4)
														.addComponent(jLabel3)
														.addComponent(jLabel6)
														.addComponent(jLabel7)
														.addComponent(jLabel8))
										.addGap(70, 70, 70)
										.addGroup(
												jPanel2Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																jPanel2Layout
																		.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING,
																				false)
																		.addComponent(
																				jComboBox1,
																				0,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addComponent(
																				jTextField1,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				134,
																				Short.MAX_VALUE))
														.addGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																jPanel2Layout
																		.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																		.addComponent(
																				jComboBox3,
																				0,
																				134,
																				Short.MAX_VALUE)
																		.addComponent(
																				jTextField3,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				134,
																				Short.MAX_VALUE)
																		.addComponent(
																				jTextField2,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				134,
																				Short.MAX_VALUE)))
										.addContainerGap()));
		jPanel2Layout
				.setVerticalGroup(jPanel2Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel2Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel2Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel3)
														.addComponent(
																jComboBox1,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel2Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel4)
														.addComponent(
																jTextField1,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel2Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel6)
														.addComponent(
																jTextField2,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel2Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel7)
														.addComponent(
																jTextField3,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel2Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel8)
														.addComponent(
																jComboBox3,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));

		jLabel5.setText("Composition Filter type (1)");

		jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
				"Element Equal Filter", "Job Position Filter",
				"List Property Filter" }));
		jComboBox2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jComboBox2ActionPerformed(evt);
			}
		});

		jLabel9.setText("Composition Filter type (2)");

		jComboBox4.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
				"Element Equal Filter", "Job Position Filter",
				"List Property Filter" }));

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(
				jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout
				.setHorizontalGroup(jPanel1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel1Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addComponent(
																				jPanel2,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addContainerGap())
														.addGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																jPanel1Layout
																		.createSequentialGroup()
																		.addComponent(
																				jButton2)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jButton1))
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel1)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																				173,
																				Short.MAX_VALUE)
																		.addComponent(
																				jButton3))
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel2)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																				173,
																				Short.MAX_VALUE)
																		.addComponent(
																				jButton4))
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel5)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																				122,
																				Short.MAX_VALUE)
																		.addComponent(
																				jComboBox2,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																jPanel1Layout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel9)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																				122,
																				Short.MAX_VALUE)
																		.addComponent(
																				jComboBox4,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE)))));
		jPanel1Layout
				.setVerticalGroup(jPanel1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								jPanel1Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel1)
														.addComponent(jButton3))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel5)
														.addComponent(
																jComboBox2,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel2)
														.addComponent(jButton4))
										.addGap(8, 8, 8)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jComboBox4,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel9))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(
												jPanel2,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jButton1)
														.addComponent(jButton2))));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(jPanel1,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE).addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup()
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(jPanel1,
								javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE)));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
		if (this.taskFrame != null)
			this.taskFrame.setVisible(true);
		else {
			if (this.filterBackFrame != null)
				this.filterBackFrame.setVisible(true);
			else
				this.updaterFrame.setVisible(true);
		}
		this.setVisible(false);
	}// GEN-LAST:event_jButton1ActionPerformed

	/*
	 * Se chequea que filtro se está creado, para posteriormente realizar su
	 * creación e incorporarlo a la tarea o a su filtro compuesto.
	 */
	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton2ActionPerformed
		Filter filter = null;
		if ((this.getFilterType().equals("ListPropertyFilter"))
				|| (this.getFilterType().equals("List Property Filter"))) {
			if ((!this.jTextField2.getText().equals(""))
					&& (!this.jTextField3.getText().equals(""))
					&& (this.jComboBox3.getSelectedItem() != null)) {
				filter = new PropertyListFilter(this.jTextField2.getText(),
						this.jTextField3.getText(),
						this.getResource((String) this.jComboBox3
								.getSelectedItem()));
			}
		} else {
			if ((this.getFilterType().equals("ValueFilter"))
					|| (this.getFilterType().equals("Job Position Filter"))) {
				if (!this.jTextField1.getText().equals("")) {
					filter = new JobPositionFilter(this.jTextField1.getText());
				}
			} else {
				if (this.getFilterType().equals("AndFilter")) {
					if ((this.getFilterFrameI() != null)
							&& (this.getFilterFrameII() != null))
						filter = new AndFilter(this.getFilterFrameI(),
								this.getFilterFrameII());
				} else {
					if (this.jComboBox1.getSelectedItem() != null)
						filter = new EqualFilter(
								this.getElement((String) this.jComboBox1
										.getSelectedItem()));
				}
			}
		}
		if (filter == null) {
			ErrorFrame.getInstance().setLabel(
					"Empty values. Cannot create filter.");
			ErrorFrame.getInstance().setBackFrame("FilterFrame");
			ErrorFrame.getInstance().setFilterBackFrame(this);
			this.setVisible(false);
			ErrorFrame.getInstance().setLocationRelativeTo(null);
			ErrorFrame.getInstance().setVisible(true);
		} else {
			if (this.getBackFrameId().equals("TaskFrame")) {
				((CreateTaskFrame) this.getBackFrame()).setFilter(filter);
				this.successExit();
			} else {
				if (this.getBackFrameId().equals("FilterFrame")) {
					if (this.getFilterFrameStateI()) {
						((CreateFilterFrame) this.getBackFrame())
								.setFilterFrameI(filter);
						this.successExit();
					} else {
						((CreateFilterFrame) this.getBackFrame())
								.setFilterFrameII(filter);
						this.successExit();
					}
				} else {
					((UpdaterFrame) this.getBackFrame()).setFilter(filter);
					this.successExit();
				}
			}
		}
	}// GEN-LAST:event_jButton2ActionPerformed

	/*
	 * Se setean el frame a donde se configurara el filtro componente del filtro
	 * compuesto a crear(con sus listas). Con ello, tambien se seteara el tipo
	 * de filtro que se incluira, y tambien setear este panel como panel de
	 * regreso.
	 */
	private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton3ActionPerformed
		CreateFilterFrame filterFrame = new CreateFilterFrame();
		filterFrame.setFilterType((String) this.jComboBox2.getSelectedItem());
		filterFrame.setBackFrame("FilterFrame", this);
		filterFrame.setResourcesList(this.getResourcesList());
		filterFrame.setMainResourcesList(this.getMainResourcesList());
		filterFrame.setFilterFrameStateI(true);
		filterFrame.setFilterFrameStateII(false);
		this.setVisible(false);
		filterFrame.setLocationRelativeTo(null);
		filterFrame.main(null);
		filterFrame.setVisible(true);
	}// GEN-LAST:event_jButton3ActionPerformed

	private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton4ActionPerformed
		CreateFilterFrame filterFrame = new CreateFilterFrame();
		filterFrame.setFilterType((String) this.jComboBox4.getSelectedItem());
		filterFrame.setBackFrame("FilterFrame", this);
		filterFrame.setResourcesList(this.getResourcesList());
		filterFrame.setMainResourcesList(this.getMainResourcesList());
		filterFrame.setFilterFrameStateI(false);
		filterFrame.setFilterFrameStateII(true);
		this.setVisible(false);
		filterFrame.setLocationRelativeTo(null);
		filterFrame.main(null);
		filterFrame.setVisible(true);
	}// GEN-LAST:event_jButton4ActionPerformed

	private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jComboBox2ActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jComboBox2ActionPerformed

	private void makeFilterGUI() {
		if ((this.getFilterType().equals("ListPropertyFilter"))
				|| (this.getFilterType().equals("List Property Filter")))
			this.setListPropertyElements();
		else {
			if (this.getFilterType().equals("AndFilter"))
				this.setAndElements();
			else {
				if ((this.getFilterType().equals("ValueFilter"))
						|| (this.getFilterType().equals("Job Position Filter")))
					this.setValueElements();
				else
					this.setObjectElements();
			}
		}

	}

	private void setAndElements() {
		this.jComboBox3.setEnabled(false);
		this.jTextField3.setEnabled(false);
		this.jTextField2.setEnabled(false);
		this.jButton3.setEnabled(true);
		this.jButton4.setEnabled(true);
		this.jComboBox1.setEnabled(false);
		this.jTextField1.setEnabled(false);
		this.jComboBox1.setEnabled(false);
		this.jComboBox2.setEnabled(true);
		this.jComboBox4.setEnabled(true);

		this.jLabel4.setEnabled(false);
		this.jLabel6.setEnabled(false);
		this.jLabel7.setEnabled(false);
		this.jLabel8.setEnabled(false);
		this.jLabel3.setEnabled(false);
		this.jLabel1.setEnabled(true);
		this.jLabel2.setEnabled(true);
		this.jLabel5.setEnabled(true);
		this.jLabel9.setEnabled(true);
	}

	/*
	 * private CreateFilterFrame getFilterFrame(){ return this.filterFrame; }
	 * 
	 * private void setFilterFrame(CreateFilterFrame frame){ this.filterFrame =
	 * frame; } /*
	 */
	public void setBackFrame(String frameName, Object backFrame) {
		if (frameName.equals("TaskFrame")) {
			this.taskFrame = (CreateTaskFrame) backFrame;
			this.setBackFrameId("TaskFrame");
		} else {
			if (frameName.equals("FilterFrame")) {
				this.filterBackFrame = (CreateFilterFrame) backFrame;
				this.setBackFrameId("FilterFrame");
			} else {
				this.updaterFrame = (UpdaterFrame) backFrame;
				this.setBackFrameId("UpdaterFrame");
			}
		}
	}

	public void setBackFrameId(String id) {
		this.backFrameId = id;
	}

	public void setFilterFrameI(Filter frame) {
		this.filterFrameI = frame;
	}

	public void setFilterFrameII(Filter frame) {
		this.filterFrameII = frame;
	}

	public void setFilterFrameStateI(boolean frameState) {
		this.filterFrameStateI = frameState;
	}

	public void setFilterFrameStateII(boolean frameState) {
		this.filterFrameStateII = frameState;
	}

	public void setFilterType(String ftype) {
		this.filterType = ftype;
		this.makeFilterGUI();
	}

	private void setListPropertyElements() {
		this.jComboBox3.setEnabled(true);
		this.jTextField3.setEnabled(true);
		this.jTextField2.setEnabled(true);
		this.jButton3.setEnabled(false);
		this.jButton4.setEnabled(false);
		this.jComboBox1.setEnabled(false);
		this.jTextField1.setEnabled(false);
		this.jComboBox1.setEnabled(false);
		this.jComboBox2.setEnabled(false);
		this.jComboBox4.setEnabled(false);

		this.jLabel4.setEnabled(false);
		this.jLabel6.setEnabled(true);
		this.jLabel7.setEnabled(true);
		this.jLabel8.setEnabled(true);
		this.jLabel3.setEnabled(false);
		this.jLabel1.setEnabled(false);
		this.jLabel2.setEnabled(false);
		this.jLabel5.setEnabled(false);
		this.jLabel9.setEnabled(false);
	}

	public void setMainResourcesList(Vector resourcesPrincipalList) {
		this.resourcesMainList = resourcesPrincipalList;
		if (this.resourcesMainList != null)
			this.fillObjectCombobox();
	}

	private void setObjectElements() {
		this.jComboBox3.setEnabled(false);
		this.jTextField3.setEnabled(false);
		this.jTextField2.setEnabled(false);
		this.jButton3.setEnabled(false);
		this.jButton4.setEnabled(false);
		this.jComboBox1.setEnabled(false);
		this.jTextField1.setEnabled(false);
		this.jComboBox1.setEnabled(true);
		this.jComboBox2.setEnabled(false);
		this.jComboBox4.setEnabled(false);

		this.jLabel3.setEnabled(true);
		this.jLabel4.setEnabled(false);
		this.jLabel6.setEnabled(false);
		this.jLabel7.setEnabled(false);
		this.jLabel8.setEnabled(false);
		this.jLabel1.setEnabled(false);
		this.jLabel2.setEnabled(false);
		this.jLabel5.setEnabled(false);
		this.jLabel9.setEnabled(false);
	}

	public void setResourcesList(Vector<Resource> resourcesList) {
		this.resourcesList = resourcesList;
		if (this.resourcesList != null)
			this.fillResourceCombobox();
	}

	private void setValueElements() {
		this.jComboBox3.setEnabled(false);
		this.jTextField3.setEnabled(false);
		this.jTextField2.setEnabled(false);
		this.jButton3.setEnabled(false);
		this.jButton4.setEnabled(false);
		this.jComboBox1.setEnabled(false);
		this.jTextField1.setEnabled(true);
		this.jComboBox1.setEnabled(false);
		this.jComboBox2.setEnabled(false);
		this.jComboBox4.setEnabled(false);

		this.jLabel4.setEnabled(true);
		this.jLabel6.setEnabled(false);
		this.jLabel7.setEnabled(false);
		this.jLabel8.setEnabled(false);
		this.jLabel3.setEnabled(false);
		this.jLabel1.setEnabled(false);
		this.jLabel2.setEnabled(false);
		this.jLabel5.setEnabled(false);
		this.jLabel9.setEnabled(false);
	}

	private void successExit() {
		NewsFrame.getInstance().setLabel("Filter created successfully.");
		if (this.getBackFrameId().equals("TaskFrame")) {
			NewsFrame.getInstance().setBackFrame("CreateTaskFrame");
		} else {
			if (this.getBackFrameId().equals("FilterFrame"))
				NewsFrame.getInstance().setBackFrame("CreateFilterFrame");
			else {
				NewsFrame.getInstance().setBackFrame("UpdaterFrame");
				NewsFrame.getInstance().setUpdaterBackFrame(
						(UpdaterFrame) this.getBackFrame());
			}
		}
		this.setVisible(false);
		NewsFrame.getInstance().setLocationRelativeTo(null);
		NewsFrame.getInstance().setVisible(true);
	}
}

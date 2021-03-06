/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SimulatorFrame.java
 *
 * Created on 10/11/2011, 01:49:37 PM
 */
package presentationLayer.frames;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import logicLayer.schedulingSystem.Actor;
import logicLayer.schedulingSystem.Resource;
import logicLayer.schedulingSystem.SchedulingSystem;
import logicLayer.schedulingSystem.SystemServices;
import logicLayer.schedulingSystem.Task;
import persistenceLayer.logginSystem.FileLogginSystem;

import com.birosoft.liquid.LiquidLookAndFeel;

/**
 * 
 * @author F.Rossi
 */
public class SimulatorFrame extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;

	public static SimulatorFrame getInstance() {
		if (SIMULATORFRAME_INSTANCE == null)
			SIMULATORFRAME_INSTANCE = new SimulatorFrame();
		return SIMULATORFRAME_INSTANCE;
	}

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
			javax.swing.UIManager
					.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
			LiquidLookAndFeel.setLiquidDecorations(true, "panther");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Error in Look and Feel" + e.getMessage());
		}
		/*
		 * try { for (javax.swing.UIManager.LookAndFeelInfo info :
		 * javax.swing.UIManager.getInstalledLookAndFeels()) { if
		 * ("Nimbus".equals(info.getName())) {
		 * javax.swing.UIManager.setLookAndFeel(info.getClassName()); break; } }
		 * } catch (ClassNotFoundException ex) {
		 * java.util.logging.Logger.getLogger
		 * (SimulatorFrame.class.getName()).log(java.util.logging.Level.SEVERE,
		 * null, ex); } catch (InstantiationException ex) {
		 * java.util.logging.Logger
		 * .getLogger(SimulatorFrame.class.getName()).log
		 * (java.util.logging.Level.SEVERE, null, ex); } catch
		 * (IllegalAccessException ex) {
		 * java.util.logging.Logger.getLogger(SimulatorFrame
		 * .class.getName()).log(java.util.logging.Level.SEVERE, null, ex); }
		 * catch (javax.swing.UnsupportedLookAndFeelException ex) {
		 * java.util.logging
		 * .Logger.getLogger(SimulatorFrame.class.getName()).log
		 * (java.util.logging.Level.SEVERE, null, ex); } //</editor-fold>
		 */
		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				SimulatorFrame.getInstance().setVisible(true);
			}
		});
	}

	private CreateTaskFrame taskCreatePanel;

	private CreateActorFrame actorCreatePanel;

	private CreateArtifactFrame artifactCreatePanel;

	private RelationFrame relationPanel;

	private DeleteTaskFrame taskDeletePanel;

	private DeleteActorFrame actorDeletePanel;

	private DeleteArtifactFrame artifactDeletePanel;
        
        private Vector<Task> tasks;

	private Vector<Task> newsList;

	private Vector<Actor> actorsList;

	private Vector<Resource> resourcesList;

	private Vector<Resource> resourcesMainList;

	private SchedulingSystem mainSchedulingSystem;

	private SystemServices services;

	private boolean loadState;

	private String projectName;

	private static SimulatorFrame SIMULATORFRAME_INSTANCE;

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton10;
	private javax.swing.JButton jButton11;
	private javax.swing.JButton jButton12;
	private javax.swing.JButton jButton13;
	private javax.swing.JButton jButton14;
	private javax.swing.JButton jButton15;
	private javax.swing.JButton jButton16;
	private javax.swing.JButton jButton2;
	private javax.swing.JButton jButton3;
	private javax.swing.JButton jButton4;
	private javax.swing.JButton jButton5;
	private javax.swing.JButton jButton6;
	private javax.swing.JButton jButton7;
	private javax.swing.JButton jButton8;
	private javax.swing.JButton jButton9;
	private javax.swing.JComboBox jComboBox1;
	private javax.swing.JComboBox jComboBox3;
	private javax.swing.JComboBox jComboBox4;
	private javax.swing.JComboBox jComboBox5;
	private javax.swing.JComboBox jComboBox6;
	private javax.swing.JFileChooser jFileChooser1;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel10;
	private javax.swing.JLabel jLabel11;
	private javax.swing.JLabel jLabel12;
	private javax.swing.JLabel jLabel13;
	private javax.swing.JLabel jLabel14;
	private javax.swing.JLabel jLabel15;
	private javax.swing.JLabel jLabel16;
	private javax.swing.JLabel jLabel17;
	private javax.swing.JLabel jLabel18;
	private javax.swing.JLabel jLabel19;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel20;
	private javax.swing.JLabel jLabel21;
	private javax.swing.JLabel jLabel22;
	private javax.swing.JLabel jLabel23;
	private javax.swing.JLabel jLabel24;
	private javax.swing.JLabel jLabel25;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JLabel jLabel9;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel10;
	private javax.swing.JPanel jPanel11;
	private javax.swing.JPanel jPanel12;
	private javax.swing.JPanel jPanel13;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JPanel jPanel4;
	private javax.swing.JPanel jPanel5;
	private javax.swing.JPanel jPanel6;
	private javax.swing.JPanel jPanel7;
	private javax.swing.JPanel jPanel8;
	private javax.swing.JProgressBar jProgressBar1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane3;
	private javax.swing.JScrollPane jScrollPane4;
	private javax.swing.JScrollPane jScrollPane5;
	private javax.swing.JTabbedPane jTabbedPane1;
	private javax.swing.JTextArea jTextArea1;
	private javax.swing.JTextField jTextField1;
	private javax.swing.JTextField jTextField2;
	private javax.swing.JTextField jTextField3;
	private javax.swing.JTextPane jTextPane1;
	private javax.swing.JTextPane jTextPane2;
	private javax.swing.JTextPane jTextPane3;

	// End of variables declaration//GEN-END:variables
	/** Creates new form SimulatorFrame */
	private SimulatorFrame() {
		this.actorCreatePanel = CreateActorFrame.getInstance();
		this.taskCreatePanel = CreateTaskFrame.getInstance();
		this.artifactCreatePanel = CreateArtifactFrame.getInstance();
		this.taskDeletePanel = DeleteTaskFrame.getInstance();
		this.actorDeletePanel = DeleteActorFrame.getInstance();
		this.artifactDeletePanel = DeleteArtifactFrame.getInstance();
		this.relationPanel = RelationFrame.getInstance();
		this.mainSchedulingSystem = new SchedulingSystem();

		initComponents();
		this.setNewsList(new Vector<Task>());
		this.setActorsList(new Vector<Actor>());
		this.setResourcesList(new Vector<Resource>());
		this.setSchedulingSystem(new SchedulingSystem());
		this.setMainResourcesList();
                this.setTasksList(new Vector<Task>());

		this.services = new SystemServices();
		this.jTextArea1.setLineWrap(true);
		this.jTextArea1.setWrapStyleWord(true);
		this.setLocationRelativeTo(null);
		this.setLoadState(false);

	}

	public void fillResourcesComboboxes() {
		this.jComboBox6.removeAllItems();
		this.jComboBox5.removeAllItems();
		this.jComboBox4.removeAllItems();
		for (int i = 0; i < this.getMainResourcesList().size(); i++) {
			this.jComboBox6.addItem(this.getMainResourcesList().elementAt(i)
					.getResId());
			this.jComboBox5.addItem(this.getMainResourcesList().elementAt(i)
					.getResId());
			this.jComboBox4.addItem(this.getMainResourcesList().elementAt(i)
					.getResId());
		}
	}

	public Vector<Actor> getActorsList() {
		return actorsList;
	}
        
        public Vector<Task> getTasksList(){
            return this.tasks;
        }

	public javax.swing.JFileChooser getjFileChooser1() {
		return jFileChooser1;
	}

	private boolean getLoadState() {
		return this.loadState;
	}

	public Vector<Resource> getMainResourcesList() {
		return resourcesMainList;
	}

	public Vector<Task> getNewsList() {
		return newsList;
	}

	private String getProjectName() {
		return this.projectName;
	}

	public Vector<Resource> getResourcesList() {
		return resourcesList;
	}

	public SchedulingSystem getSchedulingSystem() {
		return this.mainSchedulingSystem;
	}

	public SystemServices getSystemServices() {
		return services;
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */

	// <editor-fold defaultstate="collapsed"
	// <editor-fold defaultstate="collapsed"
	// <editor-fold defaultstate="collapsed"
	// desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jLabel12 = new javax.swing.JLabel();
		jFileChooser1 = new javax.swing.JFileChooser();
		jTabbedPane1 = new javax.swing.JTabbedPane();
		jPanel1 = new javax.swing.JPanel();
		jLabel2 = new javax.swing.JLabel();
		jLabel22 = new javax.swing.JLabel();
		jButton10 = new javax.swing.JButton();
		jLabel23 = new javax.swing.JLabel();
		jLabel24 = new javax.swing.JLabel();
		jButton12 = new javax.swing.JButton();
		jButton13 = new javax.swing.JButton();
		jButton15 = new javax.swing.JButton();
		jLabel15 = new javax.swing.JLabel();
		jPanel2 = new javax.swing.JPanel();
		jPanel5 = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		jButton1 = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();
		jButton3 = new javax.swing.JButton();
		jButton5 = new javax.swing.JButton();
		jButton6 = new javax.swing.JButton();
		jButton7 = new javax.swing.JButton();
		jLabel21 = new javax.swing.JLabel();
		jButton11 = new javax.swing.JButton();
		jLabel14 = new javax.swing.JLabel();
		jTextField1 = new javax.swing.JTextField();
		jPanel6 = new javax.swing.JPanel();
		jLabel5 = new javax.swing.JLabel();
		jLabel6 = new javax.swing.JLabel();
		jLabel7 = new javax.swing.JLabel();
		jLabel8 = new javax.swing.JLabel();
		jLabel9 = new javax.swing.JLabel();
		jLabel10 = new javax.swing.JLabel();
		jScrollPane3 = new javax.swing.JScrollPane();
		jTextPane1 = new javax.swing.JTextPane();
		jScrollPane4 = new javax.swing.JScrollPane();
		jTextPane2 = new javax.swing.JTextPane();
		jScrollPane5 = new javax.swing.JScrollPane();
		jTextPane3 = new javax.swing.JTextPane();
		jProgressBar1 = new javax.swing.JProgressBar();
		jButton4 = new javax.swing.JButton();
		jPanel3 = new javax.swing.JPanel();
		jPanel7 = new javax.swing.JPanel();
		jLabel11 = new javax.swing.JLabel();
		jComboBox3 = new javax.swing.JComboBox();
		jLabel13 = new javax.swing.JLabel();
		jButton14 = new javax.swing.JButton();
		jComboBox1 = new javax.swing.JComboBox();
		jPanel10 = new javax.swing.JPanel();
		jPanel11 = new javax.swing.JPanel();
		jPanel12 = new javax.swing.JPanel();
		jLabel17 = new javax.swing.JLabel();
		jLabel18 = new javax.swing.JLabel();
		jLabel19 = new javax.swing.JLabel();
		jComboBox6 = new javax.swing.JComboBox();
		jButton8 = new javax.swing.JButton();
		jTextField2 = new javax.swing.JTextField();
		jTextField3 = new javax.swing.JTextField();
		jPanel13 = new javax.swing.JPanel();
		jLabel16 = new javax.swing.JLabel();
		jLabel20 = new javax.swing.JLabel();
		jComboBox4 = new javax.swing.JComboBox();
		jComboBox5 = new javax.swing.JComboBox();
		jButton9 = new javax.swing.JButton();
		jPanel4 = new javax.swing.JPanel();
		jPanel8 = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTextArea1 = new javax.swing.JTextArea();
		jButton16 = new javax.swing.JButton();
		jLabel25 = new javax.swing.JLabel();

		jLabel12.setText("jLabel12");

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setResizable(false);

		jTabbedPane1.setPreferredSize(new java.awt.Dimension(357, 427));

		jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/presentationLayer/media/logoII.png"))); 

		jLabel22.setFont(new java.awt.Font("Verdana", 1, 11));
		jLabel22.setText("Notaciones de ayuda");

		jButton10.setText("Descargar");
		jButton10.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton10ActionPerformed(evt);
			}
		});

		jLabel23.setFont(new java.awt.Font("Verdana", 1, 11));
		jLabel23.setText("Entrada de datos");

		jLabel24.setFont(new java.awt.Font("Verdana", 1, 11));
		jLabel24.setText("Salida de datos");

		jButton12.setText("Guardar");
		jButton12.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton12ActionPerformed(evt);
			}
		});

		jButton13.setText("Cargar");
		jButton13.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton13ActionPerformed(evt);
			}
		});

		jButton15.setText("Nuevo");
		jButton15.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton15ActionPerformed(evt);
			}
		});

		jLabel15.setFont(new java.awt.Font("Verdana", 1, 11));
		jLabel15.setText("Nuevo proyecto");

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
														.addComponent(
																jLabel2,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																352,
																Short.MAX_VALUE)
														.addGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																jPanel1Layout
																		.createSequentialGroup()
																		.addGroup(
																				jPanel1Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jLabel22)
																						.addComponent(
																								jLabel23)
																						.addComponent(
																								jLabel24)
																						.addComponent(
																								jLabel15))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																				135,
																				Short.MAX_VALUE)
																		.addGroup(
																				jPanel1Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING,
																								false)
																						.addComponent(
																								jButton12,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.addComponent(
																								jButton13,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.addComponent(
																								jButton10,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.addComponent(
																								jButton15,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE))))
										.addContainerGap()));
		jPanel1Layout
				.setVerticalGroup(jPanel1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel1Layout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(jLabel2)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												105, Short.MAX_VALUE)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jButton15)
														.addComponent(jLabel15))
										.addGap(18, 18, 18)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel23)
														.addComponent(jButton13))
										.addGap(18, 18, 18)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel24)
														.addComponent(jButton12))
										.addGap(18, 18, 18)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jButton10)
														.addComponent(jLabel22))
										.addContainerGap()));

		jTabbedPane1.addTab("Main", jPanel1);

		jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
				"Simulation settings",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Verdana", 1, 11))); // NOI18N

		jLabel1.setText("Task");

		jLabel3.setText("Actor");

		jLabel4.setText("Artifact");

		jButton1.setText("Create");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		jButton2.setText("Create");
		jButton2.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton2ActionPerformed(evt);
			}
		});

		jButton3.setText("Create");
		jButton3.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton3ActionPerformed(evt);
			}
		});

		jButton5.setText("Delete");
		jButton5.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton5ActionPerformed(evt);
			}
		});

		jButton6.setText("Delete");
		jButton6.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton6ActionPerformed(evt);
			}
		});

		jButton7.setText("Delete");
		jButton7.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton7ActionPerformed(evt);
			}
		});

		jLabel21.setText("Set Relations");

		jButton11.setText("Set");
		jButton11.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton11ActionPerformed(evt);
			}
		});

		jLabel14.setText("Project name");

		javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(
				jPanel5);
		jPanel5.setLayout(jPanel5Layout);
		jPanel5Layout
				.setHorizontalGroup(jPanel5Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel5Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel5Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel5Layout
																		.createSequentialGroup()
																		.addGroup(
																				jPanel5Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jLabel21)
																						.addComponent(
																								jLabel1)
																						.addComponent(
																								jLabel3)
																						.addComponent(
																								jLabel4))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																				84,
																				Short.MAX_VALUE)
																		.addGroup(
																				jPanel5Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addGroup(
																								javax.swing.GroupLayout.Alignment.TRAILING,
																								jPanel5Layout
																										.createSequentialGroup()
																										.addGroup(
																												jPanel5Layout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.LEADING)
																														.addComponent(
																																jButton3)
																														.addComponent(
																																jButton1)
																														.addComponent(
																																jButton2))
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addGroup(
																												jPanel5Layout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.TRAILING)
																														.addComponent(
																																jButton5)
																														.addComponent(
																																jButton6)
																														.addComponent(
																																jButton7))
																										.addGap(49,
																												49,
																												49))
																						.addGroup(
																								javax.swing.GroupLayout.Alignment.TRAILING,
																								jPanel5Layout
																										.createSequentialGroup()
																										.addComponent(
																												jButton11)
																										.addGap(96,
																												96,
																												96))))
														.addGroup(
																jPanel5Layout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel14)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																				43,
																				Short.MAX_VALUE)
																		.addComponent(
																				jTextField1,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				214,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addContainerGap()))));
		jPanel5Layout
				.setVerticalGroup(jPanel5Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel5Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel5Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel5Layout
																		.createSequentialGroup()
																		.addComponent(
																				jButton5)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jButton6)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jButton7))
														.addGroup(
																jPanel5Layout
																		.createSequentialGroup()
																		.addGroup(
																				jPanel5Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								jButton1)
																						.addComponent(
																								jLabel1))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				jPanel5Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								jButton2)
																						.addComponent(
																								jLabel3))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				jPanel5Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								jButton3)
																						.addComponent(
																								jLabel4))))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel5Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel21)
														.addComponent(jButton11))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel5Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel14)
														.addComponent(
																jTextField1,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));

		jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
				"Current configuration",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Verdana", 1, 11))); // NOI18N

		jLabel5.setText("Created Tasks");

		jLabel6.setText("Created Actors");

		jLabel7.setText("Created Artifacts");

		jTextPane1.setEditable(false);
		jScrollPane3.setViewportView(jTextPane1);

		jTextPane2.setEditable(false);
		jScrollPane4.setViewportView(jTextPane2);

		jTextPane3.setEditable(false);
		jScrollPane5.setViewportView(jTextPane3);

		javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(
				jPanel6);
		jPanel6.setLayout(jPanel6Layout);
		jPanel6Layout
				.setHorizontalGroup(jPanel6Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel6Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel6Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel6Layout
																		.createSequentialGroup()
																		.addGap(87,
																				87,
																				87)
																		.addGroup(
																				jPanel6Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jLabel10)
																						.addComponent(
																								jLabel8)))
														.addGroup(
																jPanel6Layout
																		.createSequentialGroup()
																		.addGap(127,
																				127,
																				127)
																		.addComponent(
																				jLabel9))
														.addGroup(
																jPanel6Layout
																		.createSequentialGroup()
																		.addGroup(
																				jPanel6Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jLabel6)
																						.addComponent(
																								jLabel7))
																		.addGap(18,
																				18,
																				18)
																		.addGroup(
																				jPanel6Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addGroup(
																								jPanel6Layout
																										.createSequentialGroup()
																										.addComponent(
																												jScrollPane4,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												22,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addGap(18,
																												18,
																												18)
																										.addComponent(
																												jLabel5)
																										.addGap(26,
																												26,
																												26)
																										.addComponent(
																												jScrollPane3,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												22,
																												javax.swing.GroupLayout.PREFERRED_SIZE))
																						.addComponent(
																								jScrollPane5,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								22,
																								javax.swing.GroupLayout.PREFERRED_SIZE))))
										.addContainerGap(72, Short.MAX_VALUE)));
		jPanel6Layout
				.setVerticalGroup(jPanel6Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel6Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel6Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addComponent(
																jScrollPane3,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel5)
														.addComponent(
																jScrollPane4,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel6))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel6Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addComponent(
																jScrollPane5,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel7))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(jLabel8)
										.addGap(62, 62, 62)
										.addComponent(jLabel10)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jLabel9)));

		jButton4.setText("Simulate");
		jButton4.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton4ActionPerformed(evt);
			}
		});

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
										.addGroup(
												jPanel2Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel2Layout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				jPanel5,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE))
														.addGroup(
																jPanel2Layout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				jPanel6,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE))
														.addGroup(
																jPanel2Layout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				jProgressBar1,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				352,
																				Short.MAX_VALUE))
														.addGroup(
																jPanel2Layout
																		.createSequentialGroup()
																		.addGap(133,
																				133,
																				133)
																		.addComponent(
																				jButton4)))
										.addContainerGap()));
		jPanel2Layout
				.setVerticalGroup(jPanel2Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel2Layout
										.createSequentialGroup()
										.addGap(16, 16, 16)
										.addComponent(
												jPanel5,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(1, 1, 1)
										.addComponent(
												jPanel6,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												111,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jProgressBar1,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jButton4)
										.addContainerGap(14, Short.MAX_VALUE)));

		jTabbedPane1.addTab("Set", jPanel2);

		jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
				"Contingency tasks configuration",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Verdana", 1, 11))); // NOI18N

		jLabel11.setText("Main task");

		jComboBox3.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jComboBox3ActionPerformed(evt);
			}
		});

		jLabel13.setText("Contingency task");

		jButton14.setText("Save");
		jButton14.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton14ActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(
				jPanel7);
		jPanel7.setLayout(jPanel7Layout);
		jPanel7Layout
				.setHorizontalGroup(jPanel7Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel7Layout
										.createSequentialGroup()
										.addGroup(
												jPanel7Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel7Layout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addGroup(
																				jPanel7Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jLabel11,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								55,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								jLabel13))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																				111,
																				Short.MAX_VALUE)
																		.addGroup(
																				jPanel7Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING,
																								false)
																						.addComponent(
																								jComboBox1,
																								0,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.addComponent(
																								jComboBox3,
																								0,
																								126,
																								Short.MAX_VALUE)))
														.addGroup(
																jPanel7Layout
																		.createSequentialGroup()
																		.addGap(115,
																				115,
																				115)
																		.addComponent(
																				jButton14)))
										.addContainerGap()));
		jPanel7Layout
				.setVerticalGroup(jPanel7Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel7Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel7Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel11)
														.addComponent(
																jComboBox3,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel7Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel13)
														.addComponent(
																jComboBox1,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(jButton14)
										.addContainerGap()));

		javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(
				jPanel3);
		jPanel3.setLayout(jPanel3Layout);
		jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel3Layout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(jPanel7,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE).addContainerGap()));
		jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel3Layout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(jPanel7,
								javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(241, Short.MAX_VALUE)));

		jTabbedPane1.addTab("Set contingencia", jPanel3);

		jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
				"Modification settings",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Verdana", 1, 11))); // NOI18N

		jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
				"Set resource property",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Tahoma", 1, 11))); // NOI18N

		jLabel17.setText("Set resource to modify");

		jLabel18.setText("Set property to modify");

		jLabel19.setText("Set value to add");

		jButton8.setText("Make modification");
		jButton8.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton8ActionPerformed(evt);
			}
		});

		jTextField2.setMinimumSize(new java.awt.Dimension(6, 30));
		jTextField2.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTextField2ActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(
				jPanel12);
		jPanel12.setLayout(jPanel12Layout);
		jPanel12Layout
				.setHorizontalGroup(jPanel12Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel12Layout
										.createSequentialGroup()
										.addGroup(
												jPanel12Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel12Layout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addGroup(
																				jPanel12Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jLabel18)
																						.addComponent(
																								jLabel19)
																						.addComponent(
																								jLabel17))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				jPanel12Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.TRAILING)
																						.addComponent(
																								jComboBox6,
																								0,
																								175,
																								Short.MAX_VALUE)
																						.addComponent(
																								jTextField2,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								175,
																								Short.MAX_VALUE)
																						.addComponent(
																								jTextField3,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								175,
																								Short.MAX_VALUE)))
														.addGroup(
																jPanel12Layout
																		.createSequentialGroup()
																		.addGap(93,
																				93,
																				93)
																		.addComponent(
																				jButton8)))
										.addContainerGap()));
		jPanel12Layout
				.setVerticalGroup(jPanel12Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel12Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel12Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel17)
														.addComponent(
																jComboBox6,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel12Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel18)
														.addComponent(
																jTextField2,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel12Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel19)
														.addComponent(
																jTextField3,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(jButton8)));

		jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
				"Delete Relation",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Verdana", 1, 11))); // NOI18N

		jLabel16.setText("Delete relation between this resource");

		jLabel20.setText("And this one");

		jButton9.setText("Delete relation");
		jButton9.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton9ActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(
				jPanel13);
		jPanel13.setLayout(jPanel13Layout);
		jPanel13Layout
				.setHorizontalGroup(jPanel13Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel13Layout
										.createSequentialGroup()
										.addGroup(
												jPanel13Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel13Layout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addGroup(
																				jPanel13Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jLabel16)
																						.addComponent(
																								jLabel20))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				jPanel13Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jComboBox5,
																								0,
																								104,
																								Short.MAX_VALUE)
																						.addComponent(
																								jComboBox4,
																								0,
																								104,
																								Short.MAX_VALUE)))
														.addGroup(
																jPanel13Layout
																		.createSequentialGroup()
																		.addGap(103,
																				103,
																				103)
																		.addComponent(
																				jButton9)))
										.addContainerGap()));
		jPanel13Layout
				.setVerticalGroup(jPanel13Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel13Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel13Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel16)
														.addComponent(
																jComboBox4,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(18, 18, 18)
										.addGroup(
												jPanel13Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel20)
														.addComponent(
																jComboBox5,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												40, Short.MAX_VALUE)
										.addComponent(jButton9)
										.addContainerGap()));

		javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(
				jPanel11);
		jPanel11.setLayout(jPanel11Layout);
		jPanel11Layout
				.setHorizontalGroup(jPanel11Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								jPanel11Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel11Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addComponent(
																jPanel13,
																javax.swing.GroupLayout.Alignment.LEADING,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																jPanel12,
																javax.swing.GroupLayout.Alignment.LEADING,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE))
										.addContainerGap()));
		jPanel11Layout
				.setVerticalGroup(jPanel11Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel11Layout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(
												jPanel12,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jPanel13,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));

		javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(
				jPanel10);
		jPanel10.setLayout(jPanel10Layout);
		jPanel10Layout.setHorizontalGroup(jPanel10Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel10Layout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(jPanel11,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE).addContainerGap()));
		jPanel10Layout.setVerticalGroup(jPanel10Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel10Layout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(jPanel11,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE).addContainerGap()));

		jTabbedPane1.addTab("Modification", jPanel10);

		jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
				"Outcome data",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Verdana", 1, 11))); // NOI18N

		jTextArea1.setColumns(20);
		jTextArea1.setEditable(false);
		jTextArea1.setRows(5);
		jScrollPane1.setViewportView(jTextArea1);

		jButton16.setText("View");
		jButton16.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton16ActionPerformed(evt);
			}
		});

		jLabel25.setText("View Log Data");

		javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(
				jPanel8);
		jPanel8.setLayout(jPanel8Layout);
		jPanel8Layout
				.setHorizontalGroup(jPanel8Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel8Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel8Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addGroup(
																jPanel8Layout
																		.createSequentialGroup()
																		.addComponent(
																				jScrollPane1,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				320,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addContainerGap())
														.addGroup(
																jPanel8Layout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel25)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addComponent(
																				jButton16)
																		.addGap(21,
																				21,
																				21)))));
		jPanel8Layout
				.setVerticalGroup(jPanel8Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel8Layout
										.createSequentialGroup()
										.addComponent(
												jScrollPane1,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												287,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel8Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel25)
														.addComponent(jButton16))
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));

		javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(
				jPanel4);
		jPanel4.setLayout(jPanel4Layout);
		jPanel4Layout.setHorizontalGroup(jPanel4Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel4Layout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(jPanel8,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE).addContainerGap()));
		jPanel4Layout.setVerticalGroup(jPanel4Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel4Layout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(jPanel8,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE).addContainerGap()));

		jTabbedPane1.addTab("Result", jPanel4);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				layout.createSequentialGroup()
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(jTabbedPane1,
								javax.swing.GroupLayout.PREFERRED_SIZE, 377,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(jTabbedPane1,
								javax.swing.GroupLayout.DEFAULT_SIZE, 405,
								Short.MAX_VALUE)));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private boolean isComboboxesValuesValid() {
		if ((this.jComboBox3.getSelectedItem() != null)
				&& (this.jComboBox1.getSelectedItem() != null))
			return true;
		return false;
	}

	private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton10ActionPerformed
		File archivo = new File("media\\Manual de usuario - lrSimulator.pdf");
		if (archivo.exists()) {
			java.awt.Desktop d = java.awt.Desktop.getDesktop();
			try {
				d.open(archivo);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}// GEN-LAST:event_jButton10ActionPerformed

	private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton11ActionPerformed
		RelationFrame.getInstance().setResourcesList(this.getResourcesList());
		RelationFrame.getInstance().setActorsList(this.getActorsList());
		this.relationPanel.setLocationRelativeTo(null);
		this.relationPanel.writeTextArea();
		this.relationPanel.setVisible(true);
		this.setVisible(false);
	}// GEN-LAST:event_jButton11ActionPerformed

	private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton12ActionPerformed
		Vector<Task> newsList = this.getNewsList();
		Vector<Task> tasks = new Vector<Task>();
		tasks.addAll(newsList);
		this.getSchedulingSystem().setTasks(tasks);

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Guardar");
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fileChooser.setApproveButtonText("Guardar");
		int seleccion = fileChooser.showOpenDialog(null);
		if (seleccion == JFileChooser.APPROVE_OPTION) {
			if ((this.jTextField1.getText().equals(""))&&(!this.getLoadState())){                         					
				NewsFrame.getInstance().setLabel(
						"Project title is empty, please complete it.");
				NewsFrame.getInstance().setBackFrame("SimulatorFrame");
				this.setVisible(false);
				NewsFrame.getInstance().setLocationRelativeTo(null);
				NewsFrame.getInstance().setVisible(true);
			} else {
				this.getSchedulingSystem().setActorsList(this.getActorsList());
				this.getSchedulingSystem().setNewsList(this.getNewsList());
                                this.getSchedulingSystem().setTasks(this.getTasksList());
				this.getSchedulingSystem().setResourcesList(
						this.getResourcesList());
				this.setProjectName(this.jTextField1.getText());
				if (!this.getLoadState())
					this.getSchedulingSystem().saveData(this.getProjectName(),
							fileChooser.getSelectedFile().getAbsolutePath());
				else
					this.getSchedulingSystem().saveData(
							fileChooser.getSelectedFile().getAbsolutePath());
			}
		}
	}// GEN-LAST:event_jButton12ActionPerformed

	private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton13ActionPerformed
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Cargar");
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int seleccion = fileChooser.showOpenDialog(null);
		if (seleccion == JFileChooser.APPROVE_OPTION) {
			this.getSchedulingSystem().loadData(
					fileChooser.getSelectedFile().getAbsolutePath());
			this.setLoadState(true);
			this.setActorsList(this.getSchedulingSystem().getActorsList());
			this.setResourcesList(this.getSchedulingSystem().getResourcesList());
			this.setNewsList(this.getSchedulingSystem().getNewsList());
                        this.setTasksList(this.getSchedulingSystem().getTasks());
			this.jTextPane1.setText(String.valueOf(this.getNewsList().size()));
			this.jTextPane2
					.setText(String.valueOf(this.getActorsList().size()));
			this.jTextPane3.setText(String.valueOf(this.getResourcesList()
					.size()));
		}
	}// GEN-LAST:event_jButton13ActionPerformed

	private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton14ActionPerformed
		if (this.isComboboxesValuesValid()) {
			if (this.jComboBox3.getSelectedItem().equals(
					this.jComboBox1.getSelectedItem())) {
				ErrorFrame.getInstance().setLabel(
						"Selected values cannot be equals");
				ErrorFrame.getInstance().setBackFrame("SimulatorFrame");
				ErrorFrame.getInstance().setLocationRelativeTo(null);
				ErrorFrame.getInstance().setVisible(true);
				this.setVisible(false);
			} else {
				for (int r = 0; r < this.getNewsList().size(); r++) {
					if (this.getNewsList().elementAt(r).getTaskId()
							.equals(this.jComboBox3.getSelectedItem())) {
						Task contingencyTask = this
								.searchTaskOnList((String) this.jComboBox1
										.getSelectedItem());
						this.getNewsList().elementAt(r)
								.setContingencyTask(contingencyTask);
						this.getNewsList().elementAt(r)
								.setContTaskId(contingencyTask.getTaskId());
                                                this.removeNewsListContingencyTasks(contingencyTask);
                                                        
                                                        
						NewsFrame.getInstance().setLabel(
								"Contingency task successfully added");
						NewsFrame.getInstance().setBackFrame("SimulatorFrame");
						NewsFrame.getInstance().setLocationRelativeTo(null);
						NewsFrame.getInstance().setVisible(true);
						this.setVisible(false);
					}
				}
			}
		} else {
			ErrorFrame.getInstance().setLabel("Selected values are empty");
			ErrorFrame.getInstance().setBackFrame("SimulatorFrame");
			ErrorFrame.getInstance().setLocationRelativeTo(null);
			ErrorFrame.getInstance().setVisible(true);
			this.setVisible(false);
		}
	}// GEN-LAST:event_jButton14ActionPerformed

	private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton15ActionPerformed
		this.setNewsList(new Vector<Task>());
                this.setTasksList(new Vector<Task>());
		this.setActorsList(new Vector<Actor>());
		this.setResourcesList(new Vector<Resource>());
		this.setSchedulingSystem(new SchedulingSystem());
		this.setMainResourcesList();

		this.services = new SystemServices();
		this.jTextArea1.setLineWrap(true);
		this.jTextArea1.setWrapStyleWord(true);
		this.setLocationRelativeTo(null);
		this.setLoadState(false);
                this.relationPanel.clearTextArea();
		this.jTextPane1.setText(String.valueOf(this.getTasksList().size()));
		this.jTextPane2.setText(String.valueOf(this.getActorsList().size()));
		this.jTextPane3.setText(String.valueOf(this.getResourcesList().size()));
	}// GEN-LAST:event_jButton15ActionPerformed

	private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton16ActionPerformed
		String content = ((FileLogginSystem) this.getSchedulingSystem()
				.getLogger().getLogginSystems().elementAt(1)).getLog();
		this.setVisible(false);
		LogFrame currentLog = new LogFrame(content);
		currentLog.setLocationRelativeTo(null);
		currentLog.setVisible(true);
		currentLog.setBackFrame(this);
	}// GEN-LAST:event_jButton16ActionPerformed

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
		this.taskCreatePanel.setActorsList(this.getActorsList());
		this.taskCreatePanel.setResourcesList(this.getResourcesList());
		this.taskCreatePanel.setMainResourcesList(this.getMainResourcesList());
		this.taskCreatePanel.setSchedulingSystem(this.getSchedulingSystem());
		this.taskCreatePanel.setNewsList(this.getNewsList());
                this.taskCreatePanel.setTasksList(this.getTasksList());
		this.taskCreatePanel.setLocationRelativeTo(null);
		this.taskCreatePanel.setVisible(true);
		this.setVisible(false);
	}// GEN-LAST:event_jButton1ActionPerformed

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton2ActionPerformed
		this.actorCreatePanel.setMainResourcesList(this.getMainResourcesList());
		this.actorCreatePanel.setResourcesList(this.getResourcesList());
		this.actorCreatePanel.setActorsList(this.getActorsList());
		this.actorCreatePanel.setSchedulingSystem(this.getSchedulingSystem());
		this.actorCreatePanel.setLocationRelativeTo(null);
		this.actorCreatePanel.setVisible(true);
		this.setVisible(false);
	}// GEN-LAST:event_jButton2ActionPerformed

	private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton3ActionPerformed
		this.artifactCreatePanel.setResourcesList(this.getResourcesList());
		this.artifactCreatePanel.setLocationRelativeTo(null);
		this.artifactCreatePanel.setVisible(true);
		this.setVisible(false);
	}// GEN-LAST:event_jButton3ActionPerformed

	private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton4ActionPerformed
		if ((this.getActorsList().size() != 0)
				|| (this.getActorsList().size() != 0)
				|| (this.getActorsList().size() != 0)) {
			this.jProgressBar1.setValue(0);
			this.jProgressBar1.setStringPainted(true);
			this.jTextArea1.removeAll();

			int deadline = 80;
			Vector<Task> newsList = this.getNewsList();
                        Vector<Task> tasksList = this.getTasksList();
			Vector<Actor> actorsList = this.getActorsList();
			Vector<Resource> resourcesList = this.getResourcesList();
			Vector<Task> failedFinishedList = new Vector<Task>();
			Vector<Task> finishedList = new Vector<Task>();

			this.getSchedulingSystem().getDealerActor().setReadyList(newsList);
			this.getSchedulingSystem().setDeadline(deadline);
			this.getSchedulingSystem().setNewsList(newsList);
			this.getSchedulingSystem().setTasks(tasksList);
			this.getSchedulingSystem().setActorsList(actorsList);
			this.getSchedulingSystem().setResourcesList(resourcesList);
			this.getSchedulingSystem().getLogger()
					.setFailedFinishedTasks(failedFinishedList);
			this.getSchedulingSystem().getLogger()
					.setSuccessfulFinishedTasks(finishedList);

			this.jProgressBar1.setValue(30);
			this.jProgressBar1.setValue(50);
			this.getSchedulingSystem().simulateAndLog();
			this.jProgressBar1.setValue(75);
			this.getSchedulingSystem().getResultsAnalyzer().analyze();
			this.jTextArea1.setText(this.getSchedulingSystem()
					.getResultsAnalyzer().getAnalysis());
			// this.setSchedulingSystem(new SchedulingSystem());
			this.jProgressBar1.setValue(100);
			NewsFrame.getInstance().setLabel("Simulation completed");
			this.jButton16.setEnabled(true);
			NewsFrame.getInstance().setBackFrame("SimulatorFrame");
			this.setVisible(false);
			NewsFrame.getInstance().setLocationRelativeTo(null);
			NewsFrame.getInstance().setVisible(true);
			this.jProgressBar1.setValue(0);
		}
	}// GEN-LAST:event_jButton4ActionPerformed

	private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton5ActionPerformed
		DeleteTaskFrame.getInstance().setTaskList(this.getNewsList());
		this.taskDeletePanel.setLocationRelativeTo(null);
		this.taskDeletePanel.setVisible(true);
		this.setVisible(false);
	}// GEN-LAST:event_jButton5ActionPerformed

	private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton6ActionPerformed
		DeleteActorFrame.getInstance().setActorsList(this.getActorsList());
		this.actorDeletePanel.setLocationRelativeTo(null);
		this.actorDeletePanel.setVisible(true);
		this.setVisible(false);
	}// GEN-LAST:event_jButton6ActionPerformed

	private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton7ActionPerformed
		DeleteArtifactFrame.getInstance().setResourcesList(
				this.getResourcesList());
		this.artifactDeletePanel.setLocationRelativeTo(null);
		this.artifactDeletePanel.setVisible(true);
		this.setVisible(false);
	}// GEN-LAST:event_jButton7ActionPerformed

	private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton8ActionPerformed
		if ((this.jComboBox6.getSelectedItem() != null)
				&& (!this.jTextField2.getText().equals(""))
				&& (!this.jTextField3.getText().equals(""))) {
			this.services.setResourceProperty(
					(String) this.jComboBox6.getSelectedItem(),
					this.jTextField2.getText(), this.jTextField3.getText());
			this.jTextField2.setText("");
			this.jTextField3.setText("");
		} else {
			SimulatorFrame.getInstance().setVisible(false);
			ErrorFrame.getInstance().setBackFrame("SimulatorFrame");
			ErrorFrame.getInstance().setLabel(
					"There is any empty value, please complete it");
			ErrorFrame.getInstance().setLocationRelativeTo(null);
			ErrorFrame.getInstance().setVisible(true);
		}
	}// GEN-LAST:event_jButton8ActionPerformed

	private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton9ActionPerformed
		if ((this.jComboBox4.getSelectedItem() != null)
				&& (this.jComboBox5.getSelectedItem() != null))
			this.services.deleteResourceRelation(this.jComboBox4
					.getSelectedItem().toString(), this.jComboBox5
					.getSelectedItem().toString());
		else {
			SimulatorFrame.getInstance().setVisible(false);
			ErrorFrame.getInstance().setBackFrame("SimulatorFrame");
			ErrorFrame.getInstance().setLabel(
					"There is any empty value, please complete it");
			ErrorFrame.getInstance().setLocationRelativeTo(null);
			ErrorFrame.getInstance().setVisible(true);
		}
	}// GEN-LAST:event_jButton9ActionPerformed

	private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jComboBox3ActionPerformed
	}// GEN-LAST:event_jComboBox3ActionPerformed

	private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField2ActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jTextField2ActionPerformed

        private void removeNewsListContingencyTasks(Task cTask){
            this.getNewsList().remove(cTask);            
        }
        
	private Task searchTaskOnList(String id) {
		for (int r = 0; r < this.getNewsList().size(); r++) {
			if (this.getNewsList().elementAt(r).getTaskId().equals(id)) {
				return this.getNewsList().elementAt(r);
			}
		}
		return null;
	}

	public void setActorsList(Vector<Actor> actorsList) {
		this.actorsList = actorsList;
		this.setMainResourcesList();
		this.jTextPane2.setText(String.valueOf(this.actorsList.size()));
	}

	private void setContingencyTaskCombobox1() {
		String element = "";
		this.jComboBox1.removeAllItems();
		for (int t = 0; t < this.getTasksList().size(); t++) {
			element = this.getTasksList().elementAt(t).getTaskId();
			this.jComboBox1.addItem(element);
		}
	}

	private void setContingencyTaskCombobox3() {
		String element = "";
		this.jComboBox3.removeAllItems();
		for (int i = 0; i < this.getTasksList().size(); i++) {
			element = this.getTasksList().elementAt(i).getTaskId();
			this.jComboBox3.addItem(element);
		}
	}

	public void setjFileChooser1(javax.swing.JFileChooser jFileChooser1) {
		this.jFileChooser1 = jFileChooser1;
	}

	private void setLoadState(boolean state) {
		this.loadState = state;
		if (this.loadState) {
			this.jTextField1.setBackground(Color.LIGHT_GRAY);
			this.jTextField1.setText("");
			this.jTextField1.setEditable(false);
		} else {
			this.jTextField1.setEditable(true);
			this.jTextField1.setText("");
			this.jTextField1.setBackground(Color.WHITE);
		}
	}

	public void setMainResourcesList() {
		Vector<Resource> resourcesCurrentList = (Vector<Resource>) this.actorsList
				.clone();
		if (this.resourcesList != null) {
			for (int i = 0; i < this.resourcesList.size(); i++) {
				if (!resourcesCurrentList.contains(this.resourcesList
						.elementAt(i)))
					resourcesCurrentList.add(this.resourcesList.elementAt(i));
			}
		}
		this.setMainResourcesList(resourcesCurrentList);
		this.fillResourcesComboboxes();
	}

	public void setMainResourcesList(Vector<Resource> resourcesPrincipalList) {
		this.resourcesMainList = resourcesPrincipalList;
		RelationFrame.getInstance()
				.setMainResourcesList(resourcesPrincipalList);
	}

	public void setNewsList(Vector<Task> newsList) {
		this.newsList = newsList;		               
		this.jTextPane1.setText(String.valueOf(this.newsList.size()));
	}      
        
        
        public void setTasksList(Vector<Task> currentTasks){
                this.tasks = currentTasks;
                if (this.tasks != null) {
			this.setContingencyTaskCombobox1();
			this.setContingencyTaskCombobox3();
		} 
        }

	private void setProjectName(String name) {
		this.projectName = name;
	}

	public void setResourcesList(Vector<Resource> resourcesList) {
		this.resourcesList = resourcesList;
		this.setMainResourcesList();
		this.jTextPane3.setText(String.valueOf(this.resourcesList.size()));
	}

	public void setSchedulingSystem(SchedulingSystem scheduleSystem) {
		this.mainSchedulingSystem = scheduleSystem;
	}

	public void setSystemServices(SystemServices newServices) {
		this.services = newServices;
	}
}

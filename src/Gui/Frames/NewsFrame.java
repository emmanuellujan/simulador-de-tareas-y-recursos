/*
 * NewsFrame.java
 *
 * Created on 13/11/2011, 08:00:51 PM
 */
package Gui.Frames;

/**
 * 
 * @author F.Rossi
 */
public class NewsFrame extends javax.swing.JFrame {

	public static NewsFrame getInstance() {
		if (ERROR_INSTANCE == null)
			ERROR_INSTANCE = new NewsFrame();
		return ERROR_INSTANCE;
	}

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
			java.util.logging.Logger.getLogger(NewsFrame.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(NewsFrame.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(NewsFrame.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(NewsFrame.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		}
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {

			public void run() {
				new NewsFrame().setVisible(true);
			}
		});
	}

	private String backFrame;

	private UpdaterFrame updaterBackFrame;

	private CreateFilterFrame filterBackFrame;

	private static NewsFrame ERROR_INSTANCE;

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton jButton1;

	private javax.swing.JLabel jLabel1;

	private javax.swing.JLabel jLabel2;

	// End of variables declaration//GEN-END:variables

	/** Creates new form NewsFrame */
	public NewsFrame() {
		initComponents();
	}

	public CreateFilterFrame getFilterBackFrame() {
		return this.filterBackFrame;
	}

	public UpdaterFrame getUpdaterBackFrame() {
		return this.updaterBackFrame;
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

		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jButton1 = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12));
		jLabel1.setText("News!");

		jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/Gui/Media/warning.png"))); // NOI18N

		jButton1.setText("Ok");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(jLabel2)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jLabel1)
								.addContainerGap(404, Short.MAX_VALUE))
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addContainerGap(226, Short.MAX_VALUE)
								.addComponent(jButton1).addGap(222, 222, 222)));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(
														jLabel2,
														javax.swing.GroupLayout.Alignment.TRAILING)
												.addGroup(
														javax.swing.GroupLayout.Alignment.TRAILING,
														layout.createSequentialGroup()
																.addComponent(
																		jLabel1)
																.addGap(9, 9, 9)))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE).addComponent(jButton1)
								.addContainerGap()));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
		this.setVisible(false);
		this.setVisibleBackFrame();
	}// GEN-LAST:event_jButton1ActionPerformed

	public void setBackFrame(String currentBackFrame) {
		this.backFrame = currentBackFrame;
	}

	public void setFilterBackFrame(CreateFilterFrame currentBackFrame) {
		this.filterBackFrame = currentBackFrame;
	}

	public void setLabel(String message) {
		this.jLabel1.setText(message);
	}

	public void setUpdaterBackFrame(UpdaterFrame currentBackFrame) {
		this.updaterBackFrame = currentBackFrame;
	}

	private void setVisibleBackFrame() {
		if (this.backFrame.equals("CreateActorFrame"))
			CreateActorFrame.getInstance().setVisible(true);
		else {
			if (this.backFrame.equals("CreateArtifactFrame"))
				CreateArtifactFrame.getInstance().setVisible(true);
			else {
				if (this.backFrame.equals("RelationFrame"))
					RelationFrame.getInstance().setVisible(true);
				else {
					if (this.backFrame.equals("SimulatorFrame"))
						SimulatorFrame.getInstance().setVisible(true);
					else {
						if (this.backFrame.equals("UpdaterFrame"))
							this.getUpdaterBackFrame().setVisible(true);
						else {
							if (this.backFrame.equals("CreateFilterFrame"))
								this.getFilterBackFrame().setVisible(true);
							else
								CreateTaskFrame.getInstance().setVisible(true);
						}
					}
				}
			}
		}
	}
}

package org.unibl.etf.bp.hospitalis.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.unibl.etf.bp.hospitalis.entity.Pacijent;
import org.unibl.etf.bp.hospitalis.entity.PacijentNijeZadrzan;
import org.unibl.etf.bp.hospitalis.util.Utilities;

import java.sql.Date;

@SuppressWarnings("serial")
public class PacijentNijeZadrzanDialog extends JDialog{
	
	private PacijentNijeZadrzanDialog ovaj;
	private boolean izmena;
	private String dialogResult = "Cancel";

	private final JPanel contentPanel = new JPanel();
	
	private JTextField tfDatumDolaska;
	@SuppressWarnings("rawtypes")
	private JComboBox cbPacijenti;

	/**
	 * Create the dialog.
	 */
	public PacijentNijeZadrzanDialog() {
		ovaj = this;
		izmena = false;

		initialize();
	}

	public PacijentNijeZadrzanDialog(PacijentNijeZadrzan nezadrzaniPacijent) {
		ovaj = this;
		izmena = true;

		initialize();
		
		cbPacijenti.setSelectedItem(nezadrzaniPacijent.getPacijent());
		cbPacijenti.setEnabled(false);
		Date utilDate = nezadrzaniPacijent.getDatumDolaska();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = sdf.format(utilDate);
		tfDatumDolaska.setText(dateString);
	}

	public String getDialogResult() {
		return dialogResult;
	}

	private boolean proveriValidnostPolja() {
	
		 if(cbPacijenti.getSelectedIndex() == -1){
			JOptionPane.showMessageDialog(ovaj,
					"Pacijent nije odabran!", "Greška",
					JOptionPane.ERROR_MESSAGE);
		 } else if (!(Utilities.isTextValid(tfDatumDolaska.getText()))) {
				JOptionPane.showMessageDialog(ovaj,
					"Datum dolaska nije pravilno popunjen!", "Greška",
					JOptionPane.ERROR_MESSAGE);
		}
		else
			return true;
		return false;
	}

	//za dodavanje i azuriranje 
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {
		setResizable(false);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setTitle("Nezadržani pacijent");
		setBounds(100, 100, 355, 200);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(this.contentPanel, BorderLayout.CENTER);	
		{
			JLabel lblPacijenti = new JLabel("Svi pacijenti:");
			lblPacijenti.setBounds(10, 11, 300, 14);
			contentPanel.add(lblPacijenti);
		}
		{
			this.cbPacijenti = new JComboBox(Utilities
					.getDataAccessFactory().getPacijentDataAccess().pacijenti("*")
					.toArray(new Pacijent[] {}));
			this.cbPacijenti.setBounds(10, 25, 300, 20);
			contentPanel.add(this.cbPacijenti);
		}
		{
			JLabel lblDatumDolaska = new JLabel("Datum dolaska (format GGGG-MM-DD):");
			lblDatumDolaska.setBounds(10, 59, 300, 14);
			contentPanel.add(lblDatumDolaska);
		}
		{
			this.tfDatumDolaska = new JTextField();
			this.tfDatumDolaska.setColumns(10);
			this.tfDatumDolaska.setBounds(10, 73, 300, 20);
			contentPanel.add(this.tfDatumDolaska);
		}
			
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Sačuvati");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (proveriValidnostPolja()) {
							String dateString = tfDatumDolaska.getText();
							java.sql.Date sqlDate = java.sql.Date.valueOf(dateString);
							PacijentNijeZadrzan nezadrzaniPacijent = new PacijentNijeZadrzan(									 
									 (Pacijent) cbPacijenti.getSelectedItem(), sqlDate);
							boolean rezultat;
							if (izmena) {
								rezultat = Utilities.getDataAccessFactory()
										.getPacijentNijeZadrzanDataAccess().azurirajNezadrzanogPacijenta(nezadrzaniPacijent);
								if (!rezultat)
									JOptionPane.showMessageDialog(ovaj,
											"Nezadržani pacijent uspješno ažuriran!",
											"Poruka",
											JOptionPane.INFORMATION_MESSAGE);
							} else {
								rezultat = Utilities.getDataAccessFactory()
										.getPacijentNijeZadrzanDataAccess().dodajNezadrzanogPacijenta(nezadrzaniPacijent);
								if (!rezultat)
									JOptionPane.showMessageDialog(ovaj,
											"Nezadržani pacijent nije uspješno dodan!",
											"Poruka",
											JOptionPane.INFORMATION_MESSAGE);
							}
							if (rezultat) {
								dialogResult = e.getActionCommand();
								ovaj.getToolkit()
										.getSystemEventQueue()
										.postEvent(
												new WindowEvent(
														ovaj,
														WindowEvent.WINDOW_CLOSING));
							}

						}
					}
				});
				okButton.setIcon(new ImageIcon(PacijentNijeZadrzanDialog.class
						.getResource(Utilities.IMAGE_RESOURCES_PATH + "Check_14.png")));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Otkazati");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dialogResult = e.getActionCommand();
						ovaj.getToolkit()
								.getSystemEventQueue()
								.postEvent(
										new WindowEvent(ovaj,
												WindowEvent.WINDOW_CLOSING));
					}
				});
				cancelButton
						.setIcon(new ImageIcon(
								PacijentNijeZadrzanDialog.class
										.getResource(Utilities.IMAGE_RESOURCES_PATH + "Cancel_14.png")));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}

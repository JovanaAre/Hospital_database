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

import org.unibl.etf.bp.hospitalis.entity.Odjel;
import org.unibl.etf.bp.hospitalis.entity.TelefonOdjela;
import org.unibl.etf.bp.hospitalis.util.Utilities;

@SuppressWarnings("serial")
public class TelefonOdjelaDialog extends JDialog{
	
	private TelefonOdjelaDialog ovaj;
	private boolean izmena;
	private String dialogResult = "Cancel";

	private final JPanel contentPanel = new JPanel();
	
	private JTextField tfTelefon;
	@SuppressWarnings("rawtypes")
	private JComboBox cbOdjeli;

	/**
	 * Create the dialog.
	 */
	public TelefonOdjelaDialog() {
		ovaj = this;
		izmena = false;

		initialize();
	}

	public TelefonOdjelaDialog(TelefonOdjela telefonOdjela) {
		ovaj = this;
		izmena = true;

		initialize();
		
		cbOdjeli.setSelectedItem(telefonOdjela.getOdjel());
		cbOdjeli.setEnabled(false);
		tfTelefon.setText(telefonOdjela.getTelefon());

		
	}

	public String getDialogResult() {
		return dialogResult;
	}

	private boolean proveriValidnostPolja() {
	
		 if(cbOdjeli.getSelectedIndex() == -1){
			JOptionPane.showMessageDialog(ovaj,
					"Odjel nije odabran!", "Greška",
					JOptionPane.ERROR_MESSAGE);		
		} else if (tfTelefon.getText().length() == 0) {
			JOptionPane.showMessageDialog(ovaj,
					"Telefon nije popunjen!", "Greška",
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
		setTitle("Telefon odjela");
		setBounds(100, 100, 355, 200);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(this.contentPanel, BorderLayout.CENTER);	
		{
			JLabel lblOdjeli = new JLabel("Odjeli:");
			lblOdjeli.setBounds(10, 11, 300, 14);
			contentPanel.add(lblOdjeli);
		}
		{
			this.cbOdjeli = new JComboBox(Utilities
					.getDataAccessFactory().getOdjelDataAccess().odjeli("*")
					.toArray(new Odjel[] {}));
			this.cbOdjeli.setBounds(10, 25, 300, 20);
			contentPanel.add(this.cbOdjeli);
		}
		{
			JLabel lblTelefon = new JLabel("Telefon:");
			lblTelefon.setBounds(10, 59, 300, 14);
			contentPanel.add(lblTelefon);
		}
		{
			this.tfTelefon = new JTextField();
			this.tfTelefon.setColumns(10);
			this.tfTelefon.setBounds(10, 73, 300, 20);
			contentPanel.add(this.tfTelefon);
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
							TelefonOdjela telefonOdjela = new TelefonOdjela(									 
									 (Odjel) cbOdjeli.getSelectedItem(),tfTelefon.getText());								
							boolean rezultat;
							if (izmena) {
								rezultat = Utilities.getDataAccessFactory()
										.getTelefonOdjelaDataAccess().azurirajTelefonOdjela(telefonOdjela);
								if (!rezultat)
									JOptionPane.showMessageDialog(ovaj,
											"Telefon nije uspješno ažuriran!",
											"Poruka",
											JOptionPane.INFORMATION_MESSAGE);
							} else {
								rezultat = Utilities.getDataAccessFactory()
										.getTelefonOdjelaDataAccess().dodajTelefonOdjela(telefonOdjela);
								if (!rezultat)
									JOptionPane.showMessageDialog(ovaj,
											"Telefon nije uspješno dodan!",
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
				okButton.setIcon(new ImageIcon(TelefonOdjelaDialog.class
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
								TelefonOdjelaDialog.class
										.getResource(Utilities.IMAGE_RESOURCES_PATH + "Cancel_14.png")));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}

package org.unibl.etf.bp.hospitalis.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Dialog.ModalityType;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.unibl.etf.bp.hospitalis.entity.KontaktOsoba;
import org.unibl.etf.bp.hospitalis.util.Utilities;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

@SuppressWarnings("serial")
public class KontaktOsobaDialog extends JDialog {
	
	private KontaktOsobaDialog ovaj;
	private boolean izmena;
	private String dialogResult = "Cancel";

	private final JPanel contentPanel = new JPanel();
	private JTextField tfJmbKontaktOsobe;
	private JTextField tfIme;
	private JTextField tfPrezime;
	private JTextField tfTelefon;

	/**
	 * Create the dialog.
	 */
	public KontaktOsobaDialog() {
		ovaj = this;
		izmena = false;

		initialize();
	}

	public KontaktOsobaDialog(KontaktOsoba kontaktOsoba) {
		ovaj = this;
		izmena = true;

		initialize();

		tfJmbKontaktOsobe.setText(kontaktOsoba.getJmbKontaktOsobe());
		tfJmbKontaktOsobe.setEditable(false);
		tfIme.setText(kontaktOsoba.getIme());
		tfPrezime.setText(kontaktOsoba.getPrezime());
		tfTelefon.setText(kontaktOsoba.getTelefon());
	}

	public String getDialogResult() {
		return dialogResult;
	}

	private boolean proveriValidnostPolja() {
		if (tfJmbKontaktOsobe.getText().length() == 0) {
			JOptionPane.showMessageDialog(ovaj,
					"JMB nije popunjen!", "Greška",
					JOptionPane.ERROR_MESSAGE);
		} else if (!(Utilities.isTextValid(tfJmbKontaktOsobe.getText()))) {
			JOptionPane.showMessageDialog(ovaj,
					"JMB nije pravilno popunjen!", "Greška",
					JOptionPane.ERROR_MESSAGE);
		} else if (tfIme.getText().length() == 0) {
			JOptionPane.showMessageDialog(ovaj,
					"Ime nije popunjeno!", "Greška",
					JOptionPane.ERROR_MESSAGE);
		} else if (!(Utilities.isTextValid(tfIme.getText()))) {
			JOptionPane.showMessageDialog(ovaj,
					"Ime nije pravilno popunjeno!", "Greška",
					JOptionPane.ERROR_MESSAGE);
		} else if (tfPrezime.getText().length() == 0) {
			JOptionPane.showMessageDialog(ovaj,
					"Prezime nije popunjeno!", "Greška",
					JOptionPane.ERROR_MESSAGE);
		} else if (!(Utilities.isTextValid(tfPrezime.getText()))) {
			JOptionPane.showMessageDialog(ovaj,
					"Prezime nije pravilno popunjeno!", "Greška",
					JOptionPane.ERROR_MESSAGE);
		} else if (tfTelefon.getText().length() == 0) {
			JOptionPane.showMessageDialog(ovaj,
					"Telefon nije popunjen!", "Greška",
					JOptionPane.ERROR_MESSAGE);
		} else
			return true;
		return false;
	}

	private void initialize() {
		setResizable(false);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setTitle("Kontakt osoba");
		setBounds(100, 100, 355, 300);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(this.contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblJmb = new JLabel("JMB:");
			lblJmb.setBounds(10, 11, 327, 14);
			contentPanel.add(lblJmb);
		}
		{
			this.tfJmbKontaktOsobe = new JTextField();
			this.tfJmbKontaktOsobe.setColumns(10);
			this.tfJmbKontaktOsobe.setBounds(10, 25, 327, 20);
			contentPanel.add(this.tfJmbKontaktOsobe);
		}
		{
			JLabel lblIme = new JLabel("Ime:");
			lblIme.setBounds(10, 59, 327, 14);
			contentPanel.add(lblIme);
		}
		{
			this.tfIme = new JTextField();
			this.tfIme .setColumns(10);
			this.tfIme .setBounds(10, 73, 327, 20);
			contentPanel.add(this.tfIme );
		}
		{
			JLabel lblPrezime = new JLabel("Prezime:");
			lblPrezime.setBounds(10, 104, 327, 14);
			contentPanel.add(lblPrezime);
		}
		{
			this.tfPrezime = new JTextField();
			this.tfPrezime.setColumns(10);
			this.tfPrezime.setBounds(10, 118, 327, 20);
			contentPanel.add(this.tfPrezime);
		}
		{
			JLabel lblTelefon = new JLabel("Telefon:");
			lblTelefon.setBounds(10, 149, 327, 14);
			contentPanel.add(lblTelefon);
		}
		{
			this.tfTelefon = new JTextField();
			this.tfTelefon.setColumns(10);
			this.tfTelefon.setBounds(10, 163, 327, 20);
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
							KontaktOsoba kontaktOsoba = new KontaktOsoba(tfJmbKontaktOsobe.getText(),
									tfIme.getText(), tfPrezime
											.getText(), tfTelefon.getText());
							boolean rezultat;
							if (izmena) {
								rezultat = Utilities.getDataAccessFactory()
										.getKontaktOsobaDataAccess()
										.azurirajKontaktOsobu(kontaktOsoba);
								if (!rezultat)
									JOptionPane.showMessageDialog(ovaj,
											"Osoba nije uspješno ažurirana!",
											"Poruka",
											JOptionPane.INFORMATION_MESSAGE);
							} else {
								rezultat = Utilities.getDataAccessFactory()
										.getKontaktOsobaDataAccess()
										.dodajKontaktOsobu(kontaktOsoba);
								if (!rezultat)
									JOptionPane.showMessageDialog(ovaj,
											"Osoba nije uspješno dodana!",
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
				okButton.setIcon(new ImageIcon(KontaktOsobaDialog.class
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
								KontaktOsobaDialog.class
										.getResource(Utilities.IMAGE_RESOURCES_PATH + "Cancel_14.png")));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
}
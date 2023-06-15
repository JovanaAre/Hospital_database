package org.unibl.etf.bp.hospitalis.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.unibl.etf.bp.hospitalis.entity.ZdravstvenoOsiguranje;
import org.unibl.etf.bp.hospitalis.util.Utilities;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

@SuppressWarnings("serial")
public class ZdravstvenoOsiguranjeDialog extends JDialog {
	
	private ZdravstvenoOsiguranjeDialog ovaj;
	private boolean izmena;
	private String dialogResult = "Cancel";

	private final JPanel contentPanel = new JPanel();
	private JTextField tfDavalacOsiguranja;
	private JTextField tfAdresa;
	private JTextField tfTelefon;

	/**
	 * Create the dialog.
	 */
	public ZdravstvenoOsiguranjeDialog() {
		ovaj = this;
		izmena = false;

		initialize();
	}

	public ZdravstvenoOsiguranjeDialog(ZdravstvenoOsiguranje zdravstvenoOsiguranje) {
		ovaj = this;
		izmena = true;

		initialize();

		tfDavalacOsiguranja.setText(zdravstvenoOsiguranje.getDavalacOsiguranja());
		tfDavalacOsiguranja.setEditable(false);
		tfAdresa.setText(zdravstvenoOsiguranje.getAdresa());
		tfTelefon.setText(zdravstvenoOsiguranje.getTelefon());
	}

	public String getDialogResult() {
		return dialogResult;
	}

	private boolean proveriValidnostPolja() {
		
		if (tfDavalacOsiguranja.getText().length() == 0) {
			JOptionPane.showMessageDialog(ovaj,
					"Davalac osiguranja nije popunjen!", "Greška",
					JOptionPane.ERROR_MESSAGE);
		} else if (!(Utilities.isTextValid(tfDavalacOsiguranja.getText()))) {
			JOptionPane.showMessageDialog(ovaj,
					"Davalac osiguranja nije pravilno popunjen!", "Greška",
					JOptionPane.ERROR_MESSAGE);
		} else if (tfAdresa.getText().length() == 0) {
			JOptionPane.showMessageDialog(ovaj,
					"Adresa nije popunjena!", "Greška",
					JOptionPane.ERROR_MESSAGE);
		} else if (!(Utilities.isTextValid(tfAdresa.getText()))) {
			JOptionPane.showMessageDialog(ovaj,
					"Adresa nije pravilno popunjena!", "Greška",
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
		setTitle("Zdravstveno osiguranje");
		setBounds(100, 100, 355, 300);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(this.contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		{
			JLabel lblDavalacOsiguranja = new JLabel("Davalac osiguranja:");
			lblDavalacOsiguranja.setBounds(10, 11, 327, 14);
			contentPanel.add(lblDavalacOsiguranja);
		}
		{
			this.tfDavalacOsiguranja = new JTextField();
			this.tfDavalacOsiguranja.setColumns(10);
			this.tfDavalacOsiguranja.setBounds(10, 25, 327, 20);
			contentPanel.add(this.tfDavalacOsiguranja);
		}
		{
			JLabel lblAdresa = new JLabel("Adresa:");
			lblAdresa.setBounds(10, 59, 327, 14);
			contentPanel.add(lblAdresa);
		}
		{
			this.tfAdresa = new JTextField();
			this.tfAdresa.setColumns(10);
			this.tfAdresa.setBounds(10, 73, 327, 20);
			contentPanel.add(this.tfAdresa);
		}
		{
			JLabel lblTelefon = new JLabel("Telefon:");
			lblTelefon.setBounds(10, 104, 327, 14);
			contentPanel.add(lblTelefon);
		}
		{
			this.tfTelefon = new JTextField();
			this.tfTelefon.setColumns(10);
			this.tfTelefon.setBounds(10, 118, 327, 20);
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
							ZdravstvenoOsiguranje zdravstvenoOsiguranje = new ZdravstvenoOsiguranje(
									tfDavalacOsiguranja.getText(), tfAdresa
											.getText(), tfTelefon.getText());
							boolean rezultat;
							if (izmena) {
								rezultat = Utilities.getDataAccessFactory()
										.getZdravstvenoOsiguranjeDataAccess()
										.azurirajZdravstvenoOsiguranje(zdravstvenoOsiguranje);
								if (!rezultat)
									JOptionPane.showMessageDialog(ovaj,
											"Osiguranje nije uspješno ažurirano!",
											"Poruka",
											JOptionPane.INFORMATION_MESSAGE);
							} else {
								rezultat = Utilities.getDataAccessFactory()
										.getZdravstvenoOsiguranjeDataAccess()
										.dodajZdravstvenoOsiguranje(zdravstvenoOsiguranje);
								if (!rezultat)
									JOptionPane.showMessageDialog(ovaj,
											"Osiguranje nije uspješno dodano!",
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
				okButton.setIcon(new ImageIcon(ZdravstvenoOsiguranjeDialog.class
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
								ZdravstvenoOsiguranjeDialog.class
										.getResource(Utilities.IMAGE_RESOURCES_PATH + "Cancel_14.png")));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
}


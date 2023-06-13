package org.unibl.etf.bp.hospitalis.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.unibl.etf.bp.hospitalis.entity.Lijek;
import org.unibl.etf.bp.hospitalis.util.Utilities;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

@SuppressWarnings("serial")
public class LijekDialog extends JDialog {
	
	private LijekDialog ovaj;
	private boolean izmena;
	private String dialogResult = "Cancel";

	private final JPanel contentPanel = new JPanel();
	private JTextField tfIdLijeka;
	private JTextField tfNazivLijeka;
	private JTextField tfTipLijeka;
	private JTextField tfCijenaLijeka;

	/**
	 * Create the dialog.
	 */
	public LijekDialog() {
		ovaj = this;
		izmena = false;

		initialize();
	}

	public LijekDialog(Lijek lijek) {
		ovaj = this;
		izmena = true;

		initialize();

		tfIdLijeka.setText(Integer.toString(lijek.getIdLijeka()));
		tfIdLijeka.setEditable(false);
		tfNazivLijeka.setText(lijek.getNazivLijeka());
		tfTipLijeka.setText(lijek.getTipLijeka());
		tfCijenaLijeka.setText(Double.toString(lijek.getCijenaLijeka()));
	}

	public String getDialogResult() {
		return dialogResult;
	}

	private boolean proveriValidnostPolja() {
		if (tfIdLijeka.getText().length() == 0) {
			JOptionPane.showMessageDialog(ovaj,
					"Identifikator lijeka nije popunjen!", "Greška",
					JOptionPane.ERROR_MESSAGE);
		} else if (!(Utilities.tryParseInt(tfIdLijeka.getText()))
				|| Integer.valueOf(tfIdLijeka.getText()) < 1) {
			JOptionPane.showMessageDialog(ovaj,
					"Identifikator lijeka nije pravilno popunjen!", "Greška",
					JOptionPane.ERROR_MESSAGE);
		} else if (tfNazivLijeka.getText().length() == 0) {
			JOptionPane.showMessageDialog(ovaj,
					"Naziv lijeka nije popunjen!", "Greška",
					JOptionPane.ERROR_MESSAGE);
		} else if (!(Utilities.isTextValid(tfNazivLijeka.getText()))) {
			JOptionPane.showMessageDialog(ovaj,
					"Naziv lijeka nije pravilno popunjen!", "Greška",
					JOptionPane.ERROR_MESSAGE);
		} else if (tfTipLijeka.getText().length() == 0) {
			JOptionPane.showMessageDialog(ovaj,
					"Tip lijeka nije popunjen!", "Greška",
					JOptionPane.ERROR_MESSAGE);
		} else if (!(Utilities.isTextValid(tfTipLijeka.getText()))) {
			JOptionPane.showMessageDialog(ovaj,
					"Tip lijeka nije pravilno popunjen!", "Greška",
					JOptionPane.ERROR_MESSAGE);
		} else if (tfCijenaLijeka.getText().length() == 0) {
			JOptionPane.showMessageDialog(ovaj,
					"Cijena lijeka nije popunjena!", "Greška",
					JOptionPane.ERROR_MESSAGE);
		} else
			return true;
		return false;
	}

	private void initialize() {
		setResizable(false);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setTitle("Lijek");
		setBounds(100, 100, 355, 300);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(this.contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblIdentifikator = new JLabel("Identifikator:");
			lblIdentifikator.setBounds(10, 11, 327, 14);
			contentPanel.add(lblIdentifikator);
		}
		{
			this.tfIdLijeka = new JTextField();
			this.tfIdLijeka.setColumns(10);
			this.tfIdLijeka.setBounds(10, 25, 327, 20);
			contentPanel.add(this.tfIdLijeka);
		}
		{
			JLabel lblNazivLijeka = new JLabel("Naziv lijeka:");
			lblNazivLijeka.setBounds(10, 59, 327, 14);
			contentPanel.add(lblNazivLijeka);
		}
		{
			this.tfNazivLijeka = new JTextField();
			this.tfNazivLijeka.setColumns(10);
			this.tfNazivLijeka.setBounds(10, 73, 327, 20);
			contentPanel.add(this.tfNazivLijeka);
		}
		{
			JLabel lblTipLijeka = new JLabel("Tip:");
			lblTipLijeka.setBounds(10, 104, 327, 14);
			contentPanel.add(lblTipLijeka);
		}
		{
			this.tfTipLijeka = new JTextField();
			this.tfTipLijeka.setColumns(10);
			this.tfTipLijeka.setBounds(10, 118, 327, 20);
			contentPanel.add(this.tfTipLijeka);
		}
		{
			JLabel lblCijenaLijeka = new JLabel("Cijena:");
			lblCijenaLijeka.setBounds(10, 149, 327, 14);
			contentPanel.add(lblCijenaLijeka);
		}
		{
			this.tfCijenaLijeka = new JTextField();
			this.tfCijenaLijeka.setColumns(10);
			this.tfCijenaLijeka.setBounds(10, 163, 327, 20);
			contentPanel.add(this.tfCijenaLijeka);
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
							Lijek lijek = new Lijek(Integer
									.valueOf(tfIdLijeka.getText()),
									tfNazivLijeka.getText(), tfTipLijeka
											.getText(), Double.valueOf(tfCijenaLijeka.getText()));
							boolean rezultat;
							if (izmena) {
								rezultat = Utilities.getDataAccessFactory()
										.getLijekDataAccess()
										.azurirajLijek(lijek);
								if (!rezultat)
									JOptionPane.showMessageDialog(ovaj,
											"Lijek nije uspješno ažuriran!",
											"Poruka",
											JOptionPane.INFORMATION_MESSAGE);
							} else {
								rezultat = Utilities.getDataAccessFactory()
										.getLijekDataAccess()
										.dodajLijek(lijek);
								if (!rezultat)
									JOptionPane.showMessageDialog(ovaj,
											"Lijek nije uspješno dodan!",
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
				okButton.setIcon(new ImageIcon(LijekDialog.class
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
								LijekDialog.class
										.getResource(Utilities.IMAGE_RESOURCES_PATH + "Cancel_14.png")));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
}

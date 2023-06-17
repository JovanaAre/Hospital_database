package org.unibl.etf.bp.hospitalis.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.unibl.etf.bp.hospitalis.entity.DijagnostickiPregled;
import org.unibl.etf.bp.hospitalis.util.Utilities;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

@SuppressWarnings("serial")
public class DijagnostickiPregledDialog extends JDialog {
	
	private DijagnostickiPregledDialog ovaj;
	private boolean izmena;
	private String dialogResult = "Cancel";

	private final JPanel contentPanel = new JPanel();
	private JTextField tfIdPregleda;
	private JTextField tfNazivPregleda;
	private JTextField tfCijenaPregleda;

	/**
	 * Create the dialog.
	 */
	public DijagnostickiPregledDialog() {
		ovaj = this;
		izmena = false;

		initialize();
	}

	public DijagnostickiPregledDialog(DijagnostickiPregled dijagnostickiPregled) {
		ovaj = this;
		izmena = true;

		initialize();

		tfIdPregleda.setText(Integer.toString(dijagnostickiPregled.getIdPregleda()));
		tfIdPregleda.setEditable(false);
		tfNazivPregleda.setText(dijagnostickiPregled.getNazivPregleda());
		tfCijenaPregleda.setText(Double.toString(dijagnostickiPregled.getCijenaPregleda()));
	}

	public String getDialogResult() {
		return dialogResult;
	}

	private boolean proveriValidnostPolja() {
		if (tfIdPregleda.getText().length() == 0) {
			JOptionPane.showMessageDialog(ovaj,
					"Identifikator pregleda nije popunjen!", "Greška",
					JOptionPane.ERROR_MESSAGE);
		} else if (!(Utilities.tryParseInt(tfIdPregleda.getText()))
				|| Integer.valueOf(tfIdPregleda.getText()) < 1) {
			JOptionPane.showMessageDialog(ovaj,
					"Identifikator pregleda nije pravilno popunjen!", "Greška",
					JOptionPane.ERROR_MESSAGE);
		} else if (tfNazivPregleda.getText().length() == 0) {
			JOptionPane.showMessageDialog(ovaj,
					"Naziv pregleda nije popunjen!", "Greška",
					JOptionPane.ERROR_MESSAGE);
		} else if (!(Utilities.isTextValid(tfNazivPregleda.getText()))) {
			JOptionPane.showMessageDialog(ovaj,
					"Naziv pregleda nije pravilno popunjen!", "Greška",
					JOptionPane.ERROR_MESSAGE);
		} else if (tfCijenaPregleda.getText().length() == 0) {
			JOptionPane.showMessageDialog(ovaj,
					"Cijena pregleda nije popunjena!", "Greška",
					JOptionPane.ERROR_MESSAGE);
		} else
			return true;
		return false;
	}

	private void initialize() {
		setResizable(false);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setTitle("Dijagnostički pregled");
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
			this.tfIdPregleda = new JTextField();
			this.tfIdPregleda.setColumns(10);
			this.tfIdPregleda.setBounds(10, 25, 327, 20);
			contentPanel.add(this.tfIdPregleda);
		}
		{
			JLabel lblNazivPregleda = new JLabel("Naziv pregleda:");
			lblNazivPregleda.setBounds(10, 59, 327, 14);
			contentPanel.add(lblNazivPregleda);
		}
		{
			this.tfNazivPregleda = new JTextField();
			this.tfNazivPregleda.setColumns(10);
			this.tfNazivPregleda.setBounds(10, 73, 327, 20);
			contentPanel.add(this.tfNazivPregleda);
		}
		{
			JLabel lblCijenaPregleda = new JLabel("Cijena:");
			lblCijenaPregleda.setBounds(10, 104, 327, 14);
			contentPanel.add(lblCijenaPregleda);
		}
		{
			this.tfCijenaPregleda = new JTextField();
			this.tfCijenaPregleda.setColumns(10);
			this.tfCijenaPregleda.setBounds(10, 118, 327, 20);
			contentPanel.add(this.tfCijenaPregleda);
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
							DijagnostickiPregled dijagnostickiPregled = new DijagnostickiPregled(Integer
									.valueOf(tfIdPregleda.getText()),
									tfNazivPregleda.getText(), Double.valueOf(tfCijenaPregleda.getText()));
							boolean rezultat;
							if (izmena) {
								rezultat = Utilities.getDataAccessFactory()
										.getDijagnostickiPregledDataAccess()
										.azurirajDijagnostickiPregled(dijagnostickiPregled);
								if (!rezultat)
									JOptionPane.showMessageDialog(ovaj,
											"Pregled nije uspješno ažuriran!",
											"Poruka",
											JOptionPane.INFORMATION_MESSAGE);
							} else {
								rezultat = Utilities.getDataAccessFactory()
										.getDijagnostickiPregledDataAccess()
										.dodajDijagnostickiPregled(dijagnostickiPregled);
								if (!rezultat)
									JOptionPane.showMessageDialog(ovaj,
											"Pregled nije uspješno dodan!",
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
				okButton.setIcon(new ImageIcon(DijagnostickiPregledDialog.class
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
								DijagnostickiPregledDialog.class
										.getResource(Utilities.IMAGE_RESOURCES_PATH + "Cancel_14.png")));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
}

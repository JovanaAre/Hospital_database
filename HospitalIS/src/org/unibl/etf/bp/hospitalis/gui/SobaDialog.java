package org.unibl.etf.bp.hospitalis.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.unibl.etf.bp.hospitalis.entity.Soba;
import org.unibl.etf.bp.hospitalis.util.Utilities;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

@SuppressWarnings("serial")
public class SobaDialog extends JDialog {
	
	private SobaDialog ovaj;
	private boolean izmena;
	private String dialogResult = "Cancel";

	private final JPanel contentPanel = new JPanel();
	private JTextField tfBrojSobe;
	private JTextField tfCijenaSobe;
	private JTextField tfBrojKreveta;

	/**
	 * Create the dialog.
	 */
	public SobaDialog() {
		ovaj = this;
		izmena = false;

		initialize();
	}

	public SobaDialog(Soba soba) {
		ovaj = this;
		izmena = true;

		initialize();

		tfBrojSobe.setText(Integer.toString(soba.getBrojSobe()));
		tfBrojSobe.setEditable(false);
		tfCijenaSobe.setText(Double.toString(soba.getCijenaSobe()));
		tfBrojKreveta.setText(Integer.toString(soba.getBrojKreveta()));
	}

	public String getDialogResult() {
		return dialogResult;
	}

	private boolean proveriValidnostPolja() {
		if (tfBrojSobe.getText().length() == 0) {
			JOptionPane.showMessageDialog(ovaj,
					"Broj sobe nije popunjen!", "Greška",
					JOptionPane.ERROR_MESSAGE);
		} else if (!(Utilities.tryParseInt(tfBrojSobe.getText()))
				|| Integer.valueOf(tfBrojSobe.getText()) < 1) {
			JOptionPane.showMessageDialog(ovaj,
					"Broj sobe nije pravilno popunjen!", "Greška",
					JOptionPane.ERROR_MESSAGE);
		} else if (tfCijenaSobe.getText().length() == 0) {
			JOptionPane.showMessageDialog(ovaj,
					"Cijena sobe nije popunjena!", "Greška",
					JOptionPane.ERROR_MESSAGE);
		} else if (tfBrojKreveta.getText().length() == 0) {
				JOptionPane.showMessageDialog(ovaj,
					"Broj kreveta nije popunjen!", "Greška",
						JOptionPane.ERROR_MESSAGE);
		} else if (!(Utilities.tryParseInt(tfBrojKreveta.getText()))
					|| Integer.valueOf(tfBrojKreveta.getText()) < 1) {
				JOptionPane.showMessageDialog(ovaj,
					"Broj kreveta nije pravilno popunjen!", "Greška",
						JOptionPane.ERROR_MESSAGE);	 
		} else
			return true;
		return false;
	}

	private void initialize() {
		setResizable(false);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setTitle("Soba");
		setBounds(100, 100, 355, 300);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(this.contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblBrojSobe = new JLabel("Broj sobe:");
			lblBrojSobe.setBounds(10, 11, 327, 14);
			contentPanel.add(lblBrojSobe);
		}
		{
			this.tfBrojSobe = new JTextField();
			this.tfBrojSobe.setColumns(10);
			this.tfBrojSobe.setBounds(10, 25, 327, 20);
			contentPanel.add(this.tfBrojSobe);
		}
		{
			JLabel lblCijenaSobe = new JLabel("Cijena:");
			lblCijenaSobe .setBounds(10, 59, 327, 14);
			contentPanel.add(lblCijenaSobe );
		}
		{
			this.tfCijenaSobe  = new JTextField();
			this.tfCijenaSobe .setColumns(10);
			this.tfCijenaSobe .setBounds(10, 73, 327, 20);
			contentPanel.add(this.tfCijenaSobe);
		}
		{
			JLabel lblBrojKreveta = new JLabel("Broj kreveta:");
			lblBrojKreveta.setBounds(10, 104, 327, 14);
			contentPanel.add(lblBrojKreveta);
		}
		{
			this.tfBrojKreveta = new JTextField();
			this.tfBrojKreveta.setColumns(10);
			this.tfBrojKreveta.setBounds(10, 118, 327, 20);
			contentPanel.add(this.tfBrojKreveta);
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
							Soba soba = new Soba(Integer
									.valueOf(tfBrojSobe.getText()), Double.valueOf(tfCijenaSobe.getText()),Integer
									.valueOf(tfBrojKreveta.getText()));
							boolean rezultat;
							if (izmena) {
								rezultat = Utilities.getDataAccessFactory()
										.getSobaDataAccess()
										.azurirajSobu(soba);
								if (!rezultat)
									JOptionPane.showMessageDialog(ovaj,
											"Soba nije uspješno ažurirana!",
											"Poruka",
											JOptionPane.INFORMATION_MESSAGE);
							} else {
								rezultat = Utilities.getDataAccessFactory()
										.getSobaDataAccess()
										.dodajSobu(soba);
								if (!rezultat)
									JOptionPane.showMessageDialog(ovaj,
											"Soba nije uspješno dodana!",
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
				okButton.setIcon(new ImageIcon(SobaDialog.class
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
								SobaDialog.class
										.getResource(Utilities.IMAGE_RESOURCES_PATH + "Cancel_14.png")));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
}

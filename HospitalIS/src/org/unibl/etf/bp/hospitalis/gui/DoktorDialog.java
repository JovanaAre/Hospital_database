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

import org.unibl.etf.bp.hospitalis.entity.Zaposleni;
import org.unibl.etf.bp.hospitalis.entity.Doktor;
import org.unibl.etf.bp.hospitalis.util.Utilities;

import java.sql.Date;

@SuppressWarnings("serial")
public class DoktorDialog extends JDialog{
	
	private DoktorDialog ovaj;
	private boolean izmena;
	private String dialogResult = "Cancel";

	private final JPanel contentPanel = new JPanel();
	
	private JTextField tfSpecijalizacija;
	@SuppressWarnings("rawtypes")
	private JComboBox cbZaposleni;

	/**
	 * Create the dialog.
	 */
	public DoktorDialog() {
		ovaj = this;
		izmena = false;

		initialize();
	}

	public DoktorDialog(Doktor doktor) {
		ovaj = this;
		izmena = true;

		initialize();
		
		cbZaposleni.setSelectedItem(doktor.getZaposleni());
		cbZaposleni.setEnabled(false);
		tfSpecijalizacija.setText(doktor.getSpecijalizacija());

		
	}

	public String getDialogResult() {
		return dialogResult;
	}

	private boolean proveriValidnostPolja() {
	
		 if(cbZaposleni.getSelectedIndex() == -1){
			JOptionPane.showMessageDialog(ovaj,
					"Zaposleni nije odabran!", "Greška",
					JOptionPane.ERROR_MESSAGE);		
		} else if (tfSpecijalizacija.getText().length() == 0) {
			JOptionPane.showMessageDialog(ovaj,
					"Specijalizacija nije popunjena!", "Greška",
	 			JOptionPane.ERROR_MESSAGE);
		}
		else
			return true;
		return false;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {
		setResizable(false);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setTitle("Doktor");
		setBounds(100, 100, 355, 200);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(this.contentPanel, BorderLayout.CENTER);	
		{
			JLabel lblZaposleni = new JLabel("Zaposleni:");
			lblZaposleni.setBounds(10, 11, 300, 14);
			contentPanel.add(lblZaposleni);
		}
		{
			this.cbZaposleni = new JComboBox(Utilities
					.getDataAccessFactory().getZaposleniDataAccess().zaposleni("*")
					.toArray(new Zaposleni[] {}));
			this.cbZaposleni.setBounds(10, 25, 300, 20);
			contentPanel.add(this.cbZaposleni);
		}
		{
			JLabel lblSpecijalizacija = new JLabel("Specijalizacija:");
			lblSpecijalizacija.setBounds(10, 59, 300, 14);
			contentPanel.add(lblSpecijalizacija);
		}
		{
			this.tfSpecijalizacija = new JTextField();
			this.tfSpecijalizacija.setColumns(10);
			this.tfSpecijalizacija.setBounds(10, 73, 300, 20);
			contentPanel.add(this.tfSpecijalizacija);
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
							Doktor doktor = new Doktor(									 
									 (Zaposleni) cbZaposleni.getSelectedItem(),tfSpecijalizacija.getText());								
							boolean rezultat;
							if (izmena) {
								rezultat = Utilities.getDataAccessFactory()
										.getDoktorDataAccess().azurirajDoktora(doktor);
								if (!rezultat)
									JOptionPane.showMessageDialog(ovaj,
											"Doktor nije uspješno ažuriran!",
											"Poruka",
											JOptionPane.INFORMATION_MESSAGE);
							} else {
								rezultat = Utilities.getDataAccessFactory()
										.getDoktorDataAccess().dodajDoktora(doktor);
								if (!rezultat)
									JOptionPane.showMessageDialog(ovaj,
											"Doktor nije uspješno dodan!",
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
				okButton.setIcon(new ImageIcon(DoktorDialog.class
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
								DoktorDialog.class
										.getResource(Utilities.IMAGE_RESOURCES_PATH + "Cancel_14.png")));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
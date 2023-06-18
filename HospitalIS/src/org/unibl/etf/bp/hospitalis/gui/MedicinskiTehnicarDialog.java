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
import org.unibl.etf.bp.hospitalis.entity.MedicinskiTehnicar;
import org.unibl.etf.bp.hospitalis.util.Utilities;

import java.sql.Date;

@SuppressWarnings("serial")
public class MedicinskiTehnicarDialog extends JDialog{
	
	private MedicinskiTehnicarDialog ovaj;
	private boolean izmena;
	private String dialogResult = "Cancel";

	private final JPanel contentPanel = new JPanel();
	
	private JTextField tfStrucnaSprema;
	@SuppressWarnings("rawtypes")
	private JComboBox cbZaposleni;
	@SuppressWarnings("rawtypes")
	private JComboBox cbStrucnaSprema;

	/**
	 * Create the dialog.
	 */
	public MedicinskiTehnicarDialog() {
		ovaj = this;
		izmena = false;

		initialize();
	}

	public MedicinskiTehnicarDialog(MedicinskiTehnicar medicinskiTehnicar) {
		ovaj = this;
		izmena = true;

		initialize();
		
		cbZaposleni.setSelectedItem(medicinskiTehnicar.getZaposleni());
		cbZaposleni.setEnabled(false);
		cbStrucnaSprema.setSelectedItem(medicinskiTehnicar.getStrucnaSprema());
	
	}

	public String getDialogResult() {
		return dialogResult;
	}

	private boolean proveriValidnostPolja() {
	
		 if(cbZaposleni.getSelectedIndex() == -1){
			JOptionPane.showMessageDialog(ovaj,
					"Zaposleni nije odabran!", "Greška",
					JOptionPane.ERROR_MESSAGE);		
		} else if (cbStrucnaSprema.getSelectedIndex() == -1) {
				JOptionPane.showMessageDialog(ovaj, "Stručna sprema nije odabrana!",
						"Greška", JOptionPane.ERROR_MESSAGE);
		}
		else
			return true;
		return false;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {
		setResizable(false);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setTitle("Medicinski tehničar");
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
			JLabel lblStrucnaSprema = new JLabel("Stručna sprema:");
			lblStrucnaSprema.setBounds(10, 59, 300, 14);
			contentPanel.add(lblStrucnaSprema);
		}
		{
			cbStrucnaSprema = new JComboBox();
			cbStrucnaSprema.setModel(new DefaultComboBoxModel(new String[] {"medicinska sestra/tehničar", "viša medicinska sestra/tehničar", "diplomirana medicinska sestra/tehničar"}));
			cbStrucnaSprema.setBounds(10, 73, 300, 20);
			contentPanel.add(cbStrucnaSprema);
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
							MedicinskiTehnicar medicinskiTehnicar = new MedicinskiTehnicar(									 
									 (Zaposleni) cbZaposleni.getSelectedItem(),(String) cbStrucnaSprema
										.getSelectedItem());
									
							boolean rezultat;
							if (izmena) {
								rezultat = Utilities.getDataAccessFactory()
										.getMedicinskiTehnicarDataAccess().azurirajMedicinskogTehnicara(medicinskiTehnicar);
								if (!rezultat)
									JOptionPane.showMessageDialog(ovaj,
											"Medicinski tehničar nije uspješno ažuriran!",
											"Poruka",
											JOptionPane.INFORMATION_MESSAGE);
							} else {
								rezultat = Utilities.getDataAccessFactory()
										.getMedicinskiTehnicarDataAccess().dodajMedicinskogTehnicara(medicinskiTehnicar);
								if (!rezultat)
									JOptionPane.showMessageDialog(ovaj,
											"Medicinski tehničar nije uspješno dodan!",
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
				okButton.setIcon(new ImageIcon(MedicinskiTehnicarDialog.class
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
								MedicinskiTehnicarDialog.class
										.getResource(Utilities.IMAGE_RESOURCES_PATH + "Cancel_14.png")));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}

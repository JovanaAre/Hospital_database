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
import org.unibl.etf.bp.hospitalis.entity.MedicinskiTehnicar;
import org.unibl.etf.bp.hospitalis.entity.Odjel;
import org.unibl.etf.bp.hospitalis.util.Utilities;


import java.sql.Date;

@SuppressWarnings("serial")
public class OdjelDialog extends JDialog{
	
	private OdjelDialog ovaj;
	private boolean izmena;
	private String dialogResult = "Cancel";

	private final JPanel contentPanel = new JPanel();
	
	private JTextField tfIdOdjela;
	private JTextField tfNazivOdjela;
	private JTextField tfAdresa;
	@SuppressWarnings("rawtypes")
	private JComboBox cbDoktori;
	@SuppressWarnings("rawtypes")
	private JComboBox cbMedicinskiTehnicari;

	/**
	 * Create the dialog.
	 */
	public OdjelDialog() {
		ovaj = this;
		izmena = false;

		initialize();
	}

	public OdjelDialog(Odjel odjel) {
		ovaj = this;
		izmena = true;

		initialize();
		
		tfIdOdjela.setText(Integer.toString(odjel.getIdOdjela()));
		tfIdOdjela.setEditable(false);
		tfNazivOdjela.setText(odjel.getNazivOdjela());
		tfAdresa.setText(odjel.getAdresa());
		cbDoktori.setSelectedItem(odjel.getDoktor());
		cbMedicinskiTehnicari.setSelectedItem(odjel.getMedicinskiTehnicar());
		// cbDoktori.setEnabled(false);		
	}

	public String getDialogResult() {
		return dialogResult;
	}

	private boolean proveriValidnostPolja() {
		
	
		if (tfIdOdjela.getText().length() == 0) {
			JOptionPane.showMessageDialog(ovaj,
					"Identifikator odjela nije popunjen!", "Greška",
					JOptionPane.ERROR_MESSAGE);
		} else if (!(Utilities.tryParseInt(tfIdOdjela.getText()))
				|| Integer.valueOf(tfIdOdjela.getText()) < 1) {
			JOptionPane.showMessageDialog(ovaj,
					"Identifikator odjela nije pravilno popunjen!", "Greška",
					JOptionPane.ERROR_MESSAGE);
		} else if (tfNazivOdjela.getText().length() == 0) {
			JOptionPane.showMessageDialog(ovaj,
					"Naziv odjela nije popunjen!", "Greška",
					JOptionPane.ERROR_MESSAGE);
		} else if (!(Utilities.isTextValid(tfNazivOdjela.getText()))) {
			JOptionPane.showMessageDialog(ovaj,
					"Naziv odjela nije pravilno popunjen!", "Greška",
					JOptionPane.ERROR_MESSAGE);
		} else if (tfAdresa.getText().length() == 0) {
			JOptionPane.showMessageDialog(ovaj, "Adresa nije popunjena!",
					"Greška", JOptionPane.ERROR_MESSAGE);
		} else if (!(Utilities.isTextValid(tfAdresa.getText()))) {
			JOptionPane.showMessageDialog(ovaj,
					"Adresa nije pravilno popunjena!", "Greška",
					JOptionPane.ERROR_MESSAGE);
		} else if(cbDoktori.getSelectedIndex() == -1){
			JOptionPane.showMessageDialog(ovaj,
					"Šef odjela nije odabran!", "Greška",
					JOptionPane.ERROR_MESSAGE);		
		} else if(cbMedicinskiTehnicari.getSelectedIndex() == -1){
			JOptionPane.showMessageDialog(ovaj,
					"Glavna medicinska sestra/tehničar nije odabrana!", "Greška",
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
		setTitle("Odjel");
		setBounds(100, 100, 355, 300);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(this.contentPanel, BorderLayout.CENTER);	
		{
			JLabel lblIdentifikator = new JLabel("Identifikator:");
			lblIdentifikator.setBounds(10, 11, 327, 14);
			contentPanel.add(lblIdentifikator);
		}
		{
			this.tfIdOdjela = new JTextField();
			this.tfIdOdjela.setColumns(10);
			this.tfIdOdjela.setBounds(10, 25, 327, 20);
			contentPanel.add(this.tfIdOdjela);
		}
		{
			JLabel lblNazivOdjela = new JLabel("Naziv odjela:");
			lblNazivOdjela.setBounds(10, 59, 327, 14);
			contentPanel.add(lblNazivOdjela);
		}
		{
			this.tfNazivOdjela = new JTextField();
			this.tfNazivOdjela.setColumns(10);
			this.tfNazivOdjela.setBounds(10, 73, 327, 20);
			contentPanel.add(this.tfNazivOdjela);
		}
		{
			JLabel lblAdresa = new JLabel("Adresa:");
			lblAdresa.setBounds(10, 104, 327, 14);
			contentPanel.add(lblAdresa);
		}
		{
			this.tfAdresa = new JTextField();
			this.tfAdresa.setColumns(10);
			this.tfAdresa.setBounds(10, 118, 327, 20);
			contentPanel.add(this.tfAdresa);
		}
		{
			JLabel lblDoktori = new JLabel("Šef odjela:");
			lblDoktori.setBounds(10, 149, 327, 14);
			contentPanel.add(lblDoktori);
		}
		{
			this.cbDoktori = new JComboBox(Utilities
					.getDataAccessFactory().getDoktorDataAccess().sviDoktori()
					.toArray(new Doktor[] {}));
			this.cbDoktori.setBounds(10, 163, 327, 20);
			contentPanel.add(this.cbDoktori);
		}
		{
			JLabel lblMedicinskiTehnicari = new JLabel("Glavna medicinska sestra/tehničar:");
			lblMedicinskiTehnicari.setBounds(10, 194, 327, 14);
			contentPanel.add(lblMedicinskiTehnicari);
		}
		{
			this.cbMedicinskiTehnicari = new JComboBox(Utilities
					.getDataAccessFactory().getMedicinskiTehnicarDataAccess().sviMedicinskiTehnicari()
					.toArray(new MedicinskiTehnicar[] {}));
			this.cbMedicinskiTehnicari.setBounds(10, 208, 327, 20);
			contentPanel.add(this.cbMedicinskiTehnicari);
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
							Odjel odjel = new Odjel(Integer
									.valueOf(tfIdOdjela.getText()),
									tfNazivOdjela.getText(), tfAdresa.getText(),
									(Doktor) cbDoktori.getSelectedItem(), (MedicinskiTehnicar) cbMedicinskiTehnicari.getSelectedItem());								
							boolean rezultat;
							if (izmena) {
								rezultat = Utilities.getDataAccessFactory()
										.getOdjelDataAccess().azurirajOdjel(odjel);
								if (!rezultat)
									JOptionPane.showMessageDialog(ovaj,
											"Odjel nije uspješno ažuriran!",
											"Poruka",
											JOptionPane.INFORMATION_MESSAGE);
							} else {
								rezultat = Utilities.getDataAccessFactory()
										.getOdjelDataAccess().dodajOdjel(odjel);
								if (!rezultat)
									JOptionPane.showMessageDialog(ovaj,
											"Odjel nije uspješno dodan!",
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
				okButton.setIcon(new ImageIcon(OdjelDialog.class
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
								OdjelDialog.class
										.getResource(Utilities.IMAGE_RESOURCES_PATH + "Cancel_14.png")));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
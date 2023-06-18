package org.unibl.etf.bp.hospitalis.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.unibl.etf.bp.hospitalis.entity.Doktor;
import org.unibl.etf.bp.hospitalis.entity.Pacijent;
import org.unibl.etf.bp.hospitalis.entity.ZdravstvenoOsiguranje;
import org.unibl.etf.bp.hospitalis.util.Utilities;

import java.sql.Date;

@SuppressWarnings("serial")
public class PacijentDialog extends JDialog{
	
	private PacijentDialog ovaj;
	private boolean izmena;
	private String dialogResult = "Cancel";

	private final JPanel contentPanel = new JPanel();
	private JTextField tfJMBPacijenta;
	private JTextField tfIme;
	private JTextField tfPrezime;
	private JTextField tfDatumRodjenja;
	private JTextField tfAdresa;
	private JTextField tfKrvnaGrupa;
	private JTextField tfPol;
	private JTextField tfTelefon;
	@SuppressWarnings("rawtypes")
	private JComboBox cbZdravstvenaOsiguranja;

	/**
	 * Create the dialog.
	 */
	public PacijentDialog() {
		ovaj = this;
		izmena = false;

		initialize();
	}

	public PacijentDialog(Pacijent pacijent) {
		ovaj = this;
		izmena = true;

		initialize();

		tfJMBPacijenta.setText(pacijent.getJmbPacijenta());
		tfJMBPacijenta.setEditable(false);
		tfIme.setText(pacijent.getIme());
		tfIme.setEditable(false);
		tfPrezime.setText(pacijent.getPrezime());
		tfPrezime.setEditable(false);
		Date utilDate = pacijent.getDatumRodjenja();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = sdf.format(utilDate);
		tfDatumRodjenja.setText(dateString);
		tfDatumRodjenja.setEditable(false);
		tfAdresa.setText(pacijent.getAdresa());
		tfPol.setText(pacijent.getPol());
		tfPol.setEditable(false);
		tfTelefon.setText(pacijent.getTelefon());
		tfKrvnaGrupa.setText(pacijent.getKrvnaGrupa());
		cbZdravstvenaOsiguranja.setSelectedItem(pacijent.getZdravstvenoOsiguranje());
	
	}

	public String getDialogResult() {
		return dialogResult;
	}

	private boolean proveriValidnostPolja() {
		if (tfJMBPacijenta.getText().length() == 0) {
			JOptionPane.showMessageDialog(ovaj,
					"JMB nije popunjen!", "Greška",
					JOptionPane.ERROR_MESSAGE);
		} else if (!(Utilities.isTextValid(tfJMBPacijenta.getText()))) {
			JOptionPane.showMessageDialog(ovaj,
					"JMB nije pravilno popunjen!", "Greška",
					JOptionPane.ERROR_MESSAGE);
		} else if (tfIme.getText().length() == 0) {
			JOptionPane.showMessageDialog(ovaj, "Ime nije popunjeno!",
					"Greška", JOptionPane.ERROR_MESSAGE);
		} else if (!(Utilities.isTextValid(tfIme.getText()))) {
			JOptionPane.showMessageDialog(ovaj,
					"Ime nije pravilno popunjeno!", "Greška",
					JOptionPane.ERROR_MESSAGE);
		} else if (tfPrezime.getText().length() == 0) {
			JOptionPane.showMessageDialog(ovaj, "Prezime nije popunjeno!",
					"Greška", JOptionPane.ERROR_MESSAGE);
		} else if (!(Utilities.isTextValid(tfPrezime.getText()))) {
			JOptionPane.showMessageDialog(ovaj,
					"Prezime nije pravilno popunjeno!", "Greška",
					JOptionPane.ERROR_MESSAGE);
		} else if (tfKrvnaGrupa.getText().length() == 0) {
			JOptionPane.showMessageDialog(ovaj, "Krvna grupa nije popunjena!",
					"Greška", JOptionPane.ERROR_MESSAGE);
		} else if (!(Utilities.isTextValid(tfDatumRodjenja.getText()))) {
				JOptionPane.showMessageDialog(ovaj,
					"Datum rodjenja nije pravilno popunjena!", "Greška",
					JOptionPane.ERROR_MESSAGE);
		 } else if (!(Utilities.isTextValid(tfAdresa.getText()))) {
				JOptionPane.showMessageDialog(ovaj,
				  "Adresa nije pravilno popunjena!", "Greška",
				  JOptionPane.ERROR_MESSAGE);
		} else if (tfTelefon.getText().length() == 0) {
			JOptionPane.showMessageDialog(ovaj, "Telefon nije popunjen!",
					"Greška", JOptionPane.ERROR_MESSAGE);
		}
		else if(cbZdravstvenaOsiguranja.getSelectedIndex() == -1){
			JOptionPane.showMessageDialog(ovaj,
					"Zdravstveno osiguranje nije odabrano!", "Greška",
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
		setTitle("Pacijenti");
		setBounds(100, 100, 355, 500);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(this.contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lbljmb = new JLabel("JMB:");
			lbljmb.setBounds(10, 11, 300, 14);
			contentPanel.add(lbljmb);
		}
		{
			this.tfJMBPacijenta = new JTextField();
			this.tfJMBPacijenta.setColumns(10);
			this.tfJMBPacijenta.setBounds(10, 25, 300, 20);
			contentPanel.add(this.tfJMBPacijenta);
		}
		{
			JLabel lblIme = new JLabel("Ime:");
			lblIme.setBounds(10, 59, 300, 14);
			contentPanel.add(lblIme);
		}
		{
			this.tfIme = new JTextField();
			this.tfIme.setColumns(10);
			this.tfIme.setBounds(10, 73, 300, 20);
			contentPanel.add(this.tfIme);
		}
		{
			JLabel lblPrezime = new JLabel("Prezime:");
			lblPrezime.setBounds(10, 107, 300, 14);
			contentPanel.add(lblPrezime);
		}
		{
			this.tfPrezime = new JTextField();
			this.tfPrezime.setColumns(10);
			this.tfPrezime.setBounds(10, 121, 300, 20);
			contentPanel.add(this.tfPrezime);
		}		
		{
			JLabel lblDatumRodjenja = new JLabel("Datum rođenja (format GGGG-MM-DD):");
			lblDatumRodjenja.setBounds(10, 155, 300, 14);
			contentPanel.add(lblDatumRodjenja);
		}
		{
			this.tfDatumRodjenja = new JTextField();
			this.tfDatumRodjenja.setColumns(10);
			this.tfDatumRodjenja.setBounds(10, 169, 300, 20);
			contentPanel.add(this.tfDatumRodjenja);
		}
		{
			JLabel lblAdresa = new JLabel("Adresa:");
			lblAdresa.setBounds(10, 203, 300, 14);
			contentPanel.add(lblAdresa);
		}
		{
			this.tfAdresa = new JTextField();
			this.tfAdresa.setColumns(10);
			this.tfAdresa.setBounds(10, 217, 300, 20);
			contentPanel.add(this.tfAdresa);
		}
		{
			JLabel lblTelefon = new JLabel("Telefon:");
			lblTelefon.setBounds(10, 251, 300, 14);
			contentPanel.add(lblTelefon);
		}
		{
			this.tfTelefon= new JTextField();
			this.tfTelefon.setColumns(10);
			this.tfTelefon.setBounds(10, 265, 300, 20);
			contentPanel.add(this.tfTelefon);
		}
		
		{
			JLabel lblPol = new JLabel("Pol:");
			lblPol.setBounds(10, 299, 300, 14);
			contentPanel.add(lblPol);
		}
		{
			this.tfPol = new JTextField();
			this.tfPol.setColumns(10);
			this.tfPol.setBounds(10, 313, 300, 20);
			contentPanel.add(this.tfPol);
		}
		{
			JLabel lblKrvnaGrupa = new JLabel("Krvna grupa:");
			lblKrvnaGrupa.setBounds(10, 347, 300, 14);
			contentPanel.add(lblKrvnaGrupa);
		}
		{
			this.tfKrvnaGrupa = new JTextField();
			this.tfKrvnaGrupa.setColumns(10);
			this.tfKrvnaGrupa.setBounds(10, 361, 300, 20);
			contentPanel.add(this.tfKrvnaGrupa);
		}
		{
			JLabel lblZdravstvenaOsiguranja = new JLabel("Osiguranje:");
			lblZdravstvenaOsiguranja.setBounds(10, 392, 327, 14);
			contentPanel.add(lblZdravstvenaOsiguranja);
		}
		{
			this.cbZdravstvenaOsiguranja = new JComboBox(Utilities
					.getDataAccessFactory().getZdravstvenoOsiguranjeDataAccess().zdravstvenaOsiguranja("*")
					.toArray(new ZdravstvenoOsiguranje[] {}));
			this.cbZdravstvenaOsiguranja.setBounds(10, 409, 327, 20);
			contentPanel.add(this.cbZdravstvenaOsiguranja);
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
							String dateString = tfDatumRodjenja.getText();
							java.sql.Date sqlDate = java.sql.Date.valueOf(dateString);
							Pacijent pacijenti = new Pacijent(tfJMBPacijenta.getText(),
									tfIme.getText(), tfPrezime.getText(), sqlDate,
									tfAdresa.getText(), tfTelefon.getText(),
									tfPol.getText(),tfKrvnaGrupa.getText( ),
									(ZdravstvenoOsiguranje) cbZdravstvenaOsiguranja.getSelectedItem());
							
							boolean rezultat;
							if (izmena) {
								rezultat = Utilities.getDataAccessFactory()
										.getPacijentDataAccess().azurirajPacijenta(pacijenti);
								if (!rezultat)
									JOptionPane.showMessageDialog(ovaj,
											"Pacijent nije uspješno ažuriran!",
											"Poruka",
											JOptionPane.INFORMATION_MESSAGE);
							} else {
								rezultat = Utilities.getDataAccessFactory()
										.getPacijentDataAccess().dodajPacijenta(pacijenti);
								if (!rezultat)
									JOptionPane.showMessageDialog(ovaj,
											"Pacijent nije uspješno dodan!",
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
				okButton.setIcon(new ImageIcon(PacijentDialog.class
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
								PacijentDialog.class
										.getResource(Utilities.IMAGE_RESOURCES_PATH + "Cancel_14.png")));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}	
}

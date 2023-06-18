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
import org.unibl.etf.bp.hospitalis.entity.Pacijent;
import org.unibl.etf.bp.hospitalis.entity.Prepisivanje;
import org.unibl.etf.bp.hospitalis.entity.Lijek;
import org.unibl.etf.bp.hospitalis.util.Utilities;


import java.sql.Date;

@SuppressWarnings("serial")
public class PrepisivanjeDialog extends JDialog{
	
	private PrepisivanjeDialog ovaj;
	private boolean izmena;
	private String dialogResult = "Cancel";

	private final JPanel contentPanel = new JPanel();
	
	private JTextField tfDatumPrepisivanja;
	private JTextField tfKolicinaLijeka;
	@SuppressWarnings("rawtypes")
	private JComboBox cbDoktori;
	@SuppressWarnings("rawtypes")
	private JComboBox cbPacijenti;
	@SuppressWarnings("rawtypes")
	private JComboBox cbLijekovi;

	/**
	 * Create the dialog.
	 */
	public PrepisivanjeDialog() {
		ovaj = this;
		izmena = false;

		initialize();
	}

	public PrepisivanjeDialog(Prepisivanje prepisivanje) {
		ovaj = this;
		izmena = true;

		initialize();
		
		Date utilDate = prepisivanje.getDatumPrepisivanja();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = sdf.format(utilDate);
		tfDatumPrepisivanja.setText(dateString);
		tfDatumPrepisivanja.setEditable(false);
		cbDoktori.setSelectedItem(prepisivanje.getDoktor());
		cbDoktori.setEnabled(false);
		cbPacijenti.setSelectedItem(prepisivanje.getPacijent());
		cbPacijenti.setEnabled(false);
		cbLijekovi.setSelectedItem(prepisivanje.getLijek());
		cbLijekovi.setEnabled(false);
		tfKolicinaLijeka.setText(Integer.toString(prepisivanje.getKolicinaLijeka()));	
	}

	public String getDialogResult() {
		return dialogResult;
	}

	private boolean proveriValidnostPolja() {
		
	
		if (!(Utilities.isTextValid(tfDatumPrepisivanja.getText()))) {
			JOptionPane.showMessageDialog(ovaj,
				"Datum prepisivanja nije pravilno popunjen!", "Greška",
				JOptionPane.ERROR_MESSAGE);
		} else if (tfKolicinaLijeka.getText().length() == 0) {
			JOptionPane.showMessageDialog(ovaj,
				"Količina lijeka nije popunjena!", "Greška",
				JOptionPane.ERROR_MESSAGE);
		} else if (!(Utilities.tryParseInt(tfKolicinaLijeka.getText()))
					|| Integer.valueOf(tfKolicinaLijeka.getText()) < 1) {
			JOptionPane.showMessageDialog(ovaj,
				"Količina lijeka nije pravilno popunjena!", "Greška",
				JOptionPane.ERROR_MESSAGE);		
		} else if(cbDoktori.getSelectedIndex() == -1){
			JOptionPane.showMessageDialog(ovaj,
				"Doktor nije odabran!", "Greška",
				JOptionPane.ERROR_MESSAGE);		
		} else if(cbPacijenti.getSelectedIndex() == -1){
			JOptionPane.showMessageDialog(ovaj,
				"Pacijent nije odabran!", "Greška",
					JOptionPane.ERROR_MESSAGE);	
		} 
		else if(cbLijekovi.getSelectedIndex() == -1){
			JOptionPane.showMessageDialog(ovaj,
				"Lijek nije odabran!", "Greška",
					JOptionPane.ERROR_MESSAGE);	
		} else
			return true;
		return false;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {
		setResizable(false);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setTitle("Prepisivanje");
		setBounds(100, 100, 355, 300);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(this.contentPanel, BorderLayout.CENTER);	
		{
			JLabel lblDatumPrepisivanja = new JLabel("Datum prepisivanja (format GGGG-MM-DD):");
			lblDatumPrepisivanja.setBounds(10, 11, 327, 14);
			contentPanel.add(lblDatumPrepisivanja);
		}
		{
			this.tfDatumPrepisivanja = new JTextField();
			this.tfDatumPrepisivanja.setColumns(10);
			this.tfDatumPrepisivanja.setBounds(10, 25, 327, 20);
			contentPanel.add(this.tfDatumPrepisivanja);
		}
		{
			JLabel lblDoktori = new JLabel("Doktor:");
			lblDoktori.setBounds(10, 59, 327, 14);
			contentPanel.add(lblDoktori);
		}
		{
			this.cbDoktori = new JComboBox(Utilities
					.getDataAccessFactory().getDoktorDataAccess().sviDoktori()
					.toArray(new Doktor[] {}));
			this.cbDoktori.setBounds(10, 73, 327, 20);
			contentPanel.add(this.cbDoktori);
		}
		{
			JLabel lblPacijenti = new JLabel("Pacijent:");
			lblPacijenti.setBounds(10, 104, 327, 14);
			contentPanel.add(lblPacijenti);
		}
		{
			this.cbPacijenti = new JComboBox(Utilities
					.getDataAccessFactory().getPacijentDataAccess().pacijenti("*")
					.toArray(new Pacijent[] {}));
			this.cbPacijenti.setBounds(10, 118, 327, 20);
			contentPanel.add(this.cbPacijenti);
		}
		{
			JLabel lblLijekovi = new JLabel("Lijek:");
			lblLijekovi.setBounds(10, 149, 327, 14);
			contentPanel.add(lblLijekovi);
		}
		{
			this.cbLijekovi = new JComboBox(Utilities
					.getDataAccessFactory().getLijekDataAccess().lijekovi("*")
					.toArray(new Lijek[] {}));
			this.cbLijekovi.setBounds(10, 163, 327, 20);
			contentPanel.add(this.cbLijekovi);
		}
		{
			JLabel lblKolicinaLijeka = new JLabel("Količina lijeka:");
			lblKolicinaLijeka.setBounds(50, 194, 327, 14);
			contentPanel.add(lblKolicinaLijeka);
		}
		{
			this.tfKolicinaLijeka = new JTextField();
			this.tfKolicinaLijeka.setColumns(10);
			this.tfKolicinaLijeka.setBounds(50, 208, 327, 20);
			contentPanel.add(this.tfKolicinaLijeka);
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
							String dateString = tfDatumPrepisivanja.getText();
							java.sql.Date sqlDate = java.sql.Date.valueOf(dateString);
							Prepisivanje prepisivanje = new Prepisivanje(sqlDate, 
									(Doktor) cbDoktori.getSelectedItem(), 
									(Pacijent) cbPacijenti.getSelectedItem(),
									(Lijek) cbLijekovi.getSelectedItem(),
									Integer.valueOf(tfKolicinaLijeka.getText()));								
							boolean rezultat;
							if (izmena) {
								rezultat = Utilities.getDataAccessFactory()
										.getPrepisivanjeDataAccess().azurirajPrepisivanje(prepisivanje);
								if (!rezultat)
									JOptionPane.showMessageDialog(ovaj,
											"Prepisivanje nije uspješno ažurirano!",
											"Poruka",
											JOptionPane.INFORMATION_MESSAGE);
							} else {
								rezultat = Utilities.getDataAccessFactory()
										.getPrepisivanjeDataAccess().dodajPrepisivanje(prepisivanje);
								if (!rezultat)
									JOptionPane.showMessageDialog(ovaj,
											"Prepisivanje nije uspješno dodano!",
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
				okButton.setIcon(new ImageIcon(PrepisivanjeDialog.class
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
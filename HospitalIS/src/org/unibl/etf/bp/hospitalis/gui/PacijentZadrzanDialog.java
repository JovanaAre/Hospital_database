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

import org.unibl.etf.bp.hospitalis.entity.Pacijent;
import org.unibl.etf.bp.hospitalis.entity.PacijentZadrzan;
import org.unibl.etf.bp.hospitalis.entity.Soba;
import org.unibl.etf.bp.hospitalis.entity.KontaktOsoba;
import org.unibl.etf.bp.hospitalis.util.Utilities;

import java.sql.Date;

@SuppressWarnings("serial")
public class PacijentZadrzanDialog extends JDialog{
	
	private PacijentZadrzanDialog ovaj;
	private boolean izmena;
	private String dialogResult = "Cancel";

	private final JPanel contentPanel = new JPanel();
	
	private JTextField tfDatumPrijema;
	private JTextField tfDatumOtpustanja;
	@SuppressWarnings("rawtypes")
	private JComboBox cbPacijenti;
	@SuppressWarnings("rawtypes")
	private JComboBox cbSobe;
	@SuppressWarnings("rawtypes")
	private JComboBox cbKontaktOsobe;

	/**
	 * Create the dialog.
	 */
	public PacijentZadrzanDialog() {
		ovaj = this;
		izmena = false;

		initialize();
	}

	public PacijentZadrzanDialog(PacijentZadrzan zadrzaniPacijent) {
		ovaj = this;
		izmena = true;

		initialize();
		
		cbPacijenti.setSelectedItem(zadrzaniPacijent.getPacijent());
		cbPacijenti.setEnabled(false);
		Date utilDateDP = zadrzaniPacijent.getDatumPrijema();
		SimpleDateFormat sdfDP = new SimpleDateFormat("yyyy-MM-dd");
		String dateStringDP = sdfDP.format(utilDateDP);
		tfDatumPrijema.setText(dateStringDP);
		tfDatumPrijema.setEditable(false);
		Date utilDateDO = zadrzaniPacijent.getDatumOtpustanja();
		SimpleDateFormat sdfDO = new SimpleDateFormat("yyyy-MM-dd");
		String dateStringDO = sdfDO.format(utilDateDO);
		tfDatumOtpustanja.setText(dateStringDO);
		cbSobe.setSelectedItem(zadrzaniPacijent.getSoba());
		cbSobe.setEnabled(false);
		cbKontaktOsobe.setSelectedItem(zadrzaniPacijent.getKontaktOsoba());
		cbKontaktOsobe.setEnabled(false);
	}

	public String getDialogResult() {
		return dialogResult;
	}

	private boolean proveriValidnostPolja() {
	
		 if(cbPacijenti.getSelectedIndex() == -1){
			JOptionPane.showMessageDialog(ovaj,
					"Pacijent nije odabran!", "Greška",
					JOptionPane.ERROR_MESSAGE);
		 } else if (!(Utilities.isTextValid(tfDatumPrijema.getText()))) {
			JOptionPane.showMessageDialog(ovaj,
					"Datum prijema nije pravilno popunjen!", "Greška",
					JOptionPane.ERROR_MESSAGE);
		 } 	else if(cbSobe.getSelectedIndex() == -1){
				JOptionPane.showMessageDialog(ovaj,
						"Soba nije odabrana!", "Greška",
						JOptionPane.ERROR_MESSAGE);
		 }	else if(cbKontaktOsobe.getSelectedIndex() == -1){
				JOptionPane.showMessageDialog(ovaj,
						"Kontakt osoba nije odabrana!", "Greška",
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
		setTitle("Zadržani pacijent");
		setBounds(100, 100, 355, 200);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(this.contentPanel, BorderLayout.CENTER);	
		{
			JLabel lblPacijenti = new JLabel("Svi pacijenti:");
			lblPacijenti.setBounds(10, 11, 300, 14);
			contentPanel.add(lblPacijenti);
		}
		{
			this.cbPacijenti = new JComboBox(Utilities
					.getDataAccessFactory().getPacijentDataAccess().pacijenti("*")
					.toArray(new Pacijent[] {}));
			this.cbPacijenti.setBounds(10, 25, 300, 20);
			contentPanel.add(this.cbPacijenti);
		}
		{
			JLabel lblDatumPrijema = new JLabel("Datum prijema (format GGGG-MM-DD):");
			lblDatumPrijema.setBounds(10, 59, 300, 14);
			contentPanel.add(lblDatumPrijema);
		}
		{
			this.tfDatumPrijema = new JTextField();
			this.tfDatumPrijema.setColumns(10);
			this.tfDatumPrijema.setBounds(10, 73, 300, 20);
			contentPanel.add(this.tfDatumPrijema);
		}
		{
			JLabel lblDatumOtpustanja = new JLabel("Datum otpuštanja (format GGGG-MM-DD):");
			lblDatumOtpustanja.setBounds(10, 104, 300, 14);
			contentPanel.add(lblDatumOtpustanja);
		}
		{
			this.tfDatumOtpustanja = new JTextField();
			this.tfDatumOtpustanja.setColumns(10);
			this.tfDatumOtpustanja.setBounds(10, 118, 300, 20);
			contentPanel.add(this.tfDatumOtpustanja);
		}
		{
			JLabel lblSobe = new JLabel("Sobe:");
			lblSobe.setBounds(10, 149, 300, 14);
			contentPanel.add(lblSobe);
		}
		{
			this.cbSobe = new JComboBox(Utilities
					.getDataAccessFactory().getSobaDataAccess().sveSobe()
					.toArray(new Soba[] {}));
			this.cbSobe.setBounds(10, 163, 300, 20);
			contentPanel.add(this.cbSobe);
		}
		{
			JLabel lblKontaktOsobe = new JLabel("Kontakt osobe:");
			lblKontaktOsobe.setBounds(10, 194, 300, 14);
			contentPanel.add(lblKontaktOsobe);
		}
		{
			this.cbKontaktOsobe = new JComboBox(Utilities
					.getDataAccessFactory().getKontaktOsobaDataAccess().kontaktOsobe("*")
					.toArray(new KontaktOsoba[] {}));
			this.cbKontaktOsobe.setBounds(10, 208, 300, 20);
			contentPanel.add(this.cbKontaktOsobe);
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
							String dateStringDP = tfDatumPrijema.getText();
							java.sql.Date sqlDateDP = java.sql.Date.valueOf(dateStringDP);
							String dateStringDO = tfDatumOtpustanja.getText();
							java.sql.Date sqlDateDO = java.sql.Date.valueOf(dateStringDO);
							PacijentZadrzan zadrzaniPacijent = new PacijentZadrzan(									 
									 (Pacijent) cbPacijenti.getSelectedItem(), sqlDateDP,
									 sqlDateDO,(Soba) cbSobe.getSelectedItem(),
									 (KontaktOsoba) cbKontaktOsobe.getSelectedItem());
							boolean rezultat;
							if (izmena) {
								rezultat = Utilities.getDataAccessFactory()
										.getPacijentZadrzanDataAccess().azurirajZadrzanogPacijenta(zadrzaniPacijent);
								if (!rezultat)
									JOptionPane.showMessageDialog(ovaj,
											"Zadržani pacijent uspješno ažuriran!",
											"Poruka",
											JOptionPane.INFORMATION_MESSAGE);
							} else {
								rezultat = Utilities.getDataAccessFactory()
										.getPacijentZadrzanDataAccess().dodajZadrzanogPacijenta(zadrzaniPacijent);
								if (!rezultat)
									JOptionPane.showMessageDialog(ovaj,
											"Zadržani pacijent nije uspješno dodan!",
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
				okButton.setIcon(new ImageIcon(PacijentZadrzanDialog.class
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
								PacijentZadrzanDialog.class
										.getResource(Utilities.IMAGE_RESOURCES_PATH + "Cancel_14.png")));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}

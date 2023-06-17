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
import org.unibl.etf.bp.hospitalis.entity.Pregledanje;
import org.unibl.etf.bp.hospitalis.entity.DijagnostickiPregled;
import org.unibl.etf.bp.hospitalis.util.Utilities;


import java.sql.Date;

@SuppressWarnings("serial")
public class PregledanjeDialog extends JDialog{
	
	private PregledanjeDialog ovaj;
	private boolean izmena;
	private String dialogResult = "Cancel";

	private final JPanel contentPanel = new JPanel();
	
	private JTextField tfDatumPregleda;
	private JTextField tfDijagnoza;
	private JTextField tfMisljenje;
	@SuppressWarnings("rawtypes")
	private JComboBox cbDoktori;
	@SuppressWarnings("rawtypes")
	private JComboBox cbPacijenti;
	@SuppressWarnings("rawtypes")
	private JComboBox cbDijagnostickiPregledi;

	/**
	 * Create the dialog.
	 */
	public PregledanjeDialog() {
		ovaj = this;
		izmena = false;

		initialize();
	}

	public PregledanjeDialog(Pregledanje pregledanje) {
		ovaj = this;
		izmena = true;

		initialize();
		
		Date utilDate = pregledanje.getDatumPregleda();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = sdf.format(utilDate);
		tfDatumPregleda.setText(dateString);
		tfDatumPregleda.setEditable(false);
		cbDoktori.setSelectedItem(pregledanje.getDoktor());
		cbDoktori.setEnabled(false);
		cbPacijenti.setSelectedItem(pregledanje.getPacijent());
		cbPacijenti.setEnabled(false);
		cbDijagnostickiPregledi.setSelectedItem(pregledanje.getDijagnostickiPregled());
		cbDijagnostickiPregledi.setEnabled(false);
		tfDijagnoza.setText(pregledanje.getDijagnoza());
		tfMisljenje.setText(pregledanje.getMisljenje());
	}

	public String getDialogResult() {
		return dialogResult;
	}

	private boolean proveriValidnostPolja() {
		
	
		if (!(Utilities.isTextValid(tfDatumPregleda.getText()))) {
			JOptionPane.showMessageDialog(ovaj,
				"Datum pregleda nije pravilno popunjen!", "Greška",
				JOptionPane.ERROR_MESSAGE);
		} else if (tfDijagnoza.getText().length() == 0) {
			JOptionPane.showMessageDialog(ovaj,
					"Dijagnoza nije popunjena!", "Greška",
					JOptionPane.ERROR_MESSAGE);
		} else if (!(Utilities.isTextValid(tfDijagnoza.getText()))) {
			JOptionPane.showMessageDialog(ovaj,
					"Dijagnoza nije pravilno popunjena!", "Greška",
					JOptionPane.ERROR_MESSAGE);	
		} else if(cbDoktori.getSelectedIndex() == -1){
			JOptionPane.showMessageDialog(ovaj,
				"Doktor nije odabran!", "Greška",
				JOptionPane.ERROR_MESSAGE);		
		} else if(cbPacijenti.getSelectedIndex() == -1){
			JOptionPane.showMessageDialog(ovaj,
				"Pacijent nije odabran!", "Greška",
					JOptionPane.ERROR_MESSAGE);	
		} else if(cbDijagnostickiPregledi.getSelectedIndex() == -1){
			JOptionPane.showMessageDialog(ovaj,
				"Dijagnostički pregled nije odabran!", "Greška",
					JOptionPane.ERROR_MESSAGE);	
		} else
			return true;
		return false;
	}

	//za dodavanje i azuriranje 
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {
		setResizable(false);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setTitle("Pregledanje");
		setBounds(100, 100, 355, 300);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(this.contentPanel, BorderLayout.CENTER);	
		{
			JLabel lblDatumPregleda = new JLabel("Datum pregleda (format GGGG-MM-DD):");
			lblDatumPregleda.setBounds(10, 11, 327, 14);
			contentPanel.add(lblDatumPregleda);
		}
		{
			this.tfDatumPregleda = new JTextField();
			this.tfDatumPregleda.setColumns(10);
			this.tfDatumPregleda.setBounds(10, 25, 327, 20);
			contentPanel.add(this.tfDatumPregleda);
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
			JLabel lblDijagnostickiPregledi = new JLabel("Dijagnostički pregled:");
			lblDijagnostickiPregledi.setBounds(10, 149, 327, 14);
			contentPanel.add(lblDijagnostickiPregledi);
		}
		{
			this.cbDijagnostickiPregledi = new JComboBox(Utilities
					.getDataAccessFactory().getDijagnostickiPregledDataAccess().dijagnostickiPregledi("*")
					.toArray(new DijagnostickiPregled[] {}));
			this.cbDijagnostickiPregledi.setBounds(10, 163, 327, 20);
			contentPanel.add(this.cbDijagnostickiPregledi);
		}
		{
			JLabel lblDijagnoza = new JLabel("Dijagnoza:");
			lblDijagnoza.setBounds(10, 194, 327, 14);
			contentPanel.add(lblDijagnoza);
		}
		{
			this.tfDijagnoza = new JTextField();
			this.tfDijagnoza.setColumns(10);
			this.tfDijagnoza.setBounds(10, 208, 327, 20);
			contentPanel.add(this.tfDijagnoza);
		}
		{
			JLabel lblMisljenje = new JLabel("Mišljenje:");
			lblMisljenje.setBounds(10, 239, 327, 14);
			contentPanel.add(lblMisljenje);
		}
		{
			this.tfMisljenje = new JTextField();
			this.tfMisljenje.setColumns(10);
			this.tfMisljenje.setBounds(10, 253, 327, 20);
			contentPanel.add(this.tfMisljenje);
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
							String dateString = tfDatumPregleda.getText();
							java.sql.Date sqlDate = java.sql.Date.valueOf(dateString);
							Pregledanje pregledanje = new Pregledanje(sqlDate, 
									(Doktor) cbDoktori.getSelectedItem(), 
									(Pacijent) cbPacijenti.getSelectedItem(),
									(DijagnostickiPregled) cbDijagnostickiPregledi.getSelectedItem(),
									tfDijagnoza.getText(), tfMisljenje.getText());								
							boolean rezultat;
							if (izmena) {
								rezultat = Utilities.getDataAccessFactory()
										.getPregledanjeDataAccess().azurirajPregledanje(pregledanje);
								if (!rezultat)
									JOptionPane.showMessageDialog(ovaj,
											"Pregledanje nije uspješno ažurirano!",
											"Poruka",
											JOptionPane.INFORMATION_MESSAGE);
							} else {
								rezultat = Utilities.getDataAccessFactory()
										.getPregledanjeDataAccess().dodajPregledanje(pregledanje);
								if (!rezultat)
									JOptionPane.showMessageDialog(ovaj,
											"Pregledanje nije uspješno dodano!",
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
				okButton.setIcon(new ImageIcon(PregledanjeDialog.class
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
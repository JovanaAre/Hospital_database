package org.unibl.etf.bp.hospitalis.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.unibl.etf.bp.hospitalis.entity.Zaposleni;
import org.unibl.etf.bp.hospitalis.entity.ZaposleniNaOdjelima;
import org.unibl.etf.bp.hospitalis.entity.Odjel;
import org.unibl.etf.bp.hospitalis.util.Utilities;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.sql.Date;
import java.text.SimpleDateFormat;

@SuppressWarnings("serial")
public class ZaposleniNaOdjelimaDialog extends JDialog {
	
	private ZaposleniNaOdjelimaDialog ovaj;
	private boolean izmena;
	private String dialogResult = "Cancel";
	
	private Date datumZaposlenja;
	private Odjel odjel;
	private Zaposleni zaposleni;

	private final JPanel contentPanel = new JPanel();
	private JTextField tfDatumZaposlenja;
	private JTextField tfJmbZaposlenog;
	private JButton btnOdaberiZaposlenog;
	private JTextField tfZaposleni;

	/**
	 * Create the dialog.
	 * 
	 * @wbp.parser.constructor
	 */
	public ZaposleniNaOdjelimaDialog(Odjel odjel) {
		ovaj = this;
		izmena = false;
		this.odjel = odjel;

		initialize();

		this.setTitle("Zaposleni na odjelu '" + odjel
				+ "'");
	}

	public ZaposleniNaOdjelimaDialog(ZaposleniNaOdjelima zaposleniNaOdjelu) {
		ovaj = this;
		izmena = true;
		
		this.datumZaposlenja= zaposleniNaOdjelu.getDatumZaposlenja();
		this.zaposleni = zaposleniNaOdjelu.getZaposleni();
		this.odjel = zaposleniNaOdjelu.getOdjel();

		initialize();

		this.setTitle("Zaposleni na odjelu'" + odjel
				+ "'");
		Date utilDate = zaposleniNaOdjelu.getDatumZaposlenja();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = sdf.format(utilDate);
		tfDatumZaposlenja.setText(dateString);
		tfJmbZaposlenog.setText(zaposleni.getJmb());
		tfJmbZaposlenog.setEditable(false);
		tfZaposleni.setText(zaposleni.toString());
		btnOdaberiZaposlenog.setEnabled(false);
	}

	public String getDialogResult() {
		return dialogResult;
	}

	private boolean proveriValidnostPolja() {
		if (zaposleni == null) {
			JOptionPane.showMessageDialog(ovaj, "Zaposleni nije odabran!",
					"Greška", JOptionPane.ERROR_MESSAGE);
		} else if (!(Utilities.isTextValid(tfDatumZaposlenja.getText()))) {
				JOptionPane.showMessageDialog(ovaj,
					"Datum zaposlenja nije pravilno popunjen!", "Greška",
					JOptionPane.ERROR_MESSAGE);
		} else
			return true;
		return false;
	}

	private void pronadjiZaposlenog(boolean tiho) {
		tfZaposleni.setText("");
		zaposleni = null;
		if (Utilities.tryParseInt(tfJmbZaposlenog.getText())) {
			zaposleni = Utilities.getDataAccessFactory().getZaposleniDataAccess()
					.zaposlen(tfJmbZaposlenog.getText());
			if (zaposleni != null)
				tfZaposleni.setText(zaposleni.toString());
		} else if (!tiho)
			JOptionPane.showMessageDialog(ovaj,
					"JMB zaposlenog nije pravilno popunjen!", "Greška",
					JOptionPane.ERROR_MESSAGE);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize() {
		setResizable(false);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 500, 175);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(this.contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblZaposleni = new JLabel("Zaposleni:");
			lblZaposleni.setBounds(10, 11, 125, 14);
			contentPanel.add(lblZaposleni);
		}
		{
			this.tfJmbZaposlenog = new JTextField();
			this.tfJmbZaposlenog.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent arg0) {
					pronadjiZaposlenog(true);
				}
			});
			this.tfJmbZaposlenog.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent arg0) {
					if (arg0.getKeyCode() == KeyEvent.VK_ENTER)
						pronadjiZaposlenog(false);
				}
			});
			this.tfJmbZaposlenog.setColumns(10);
			this.tfJmbZaposlenog.setBounds(10, 28, 125, 20);
			contentPanel.add(this.tfJmbZaposlenog);
		}
		{
			btnOdaberiZaposlenog = new JButton("");
			this.btnOdaberiZaposlenog.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					ovaj.setVisible(false);
					final ZaposleniFrame zf = new ZaposleniFrame(true);
					zf.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosing(WindowEvent e) {
							if (zf.getOdabraniZaposleni() != null) {
								zaposleni = zf.getOdabraniZaposleni();
								tfJmbZaposlenog.setText(zaposleni
										.getJmb());
								tfZaposleni.setText(zaposleni.toString());
							}
							zf.dispose();
							ovaj.setVisible(true);
							ovaj.toFront();
						}
					});
					zf.setVisible(true);
				}
			});
			this.btnOdaberiZaposlenog
					.setIcon(new ImageIcon(
							ZaposleniNaOdjelimaDialog.class
									.getResource(Utilities.IMAGE_RESOURCES_PATH + "Lookup_14.png")));
			this.btnOdaberiZaposlenog.setBounds(145, 27, 30, 23);
			contentPanel.add(this.btnOdaberiZaposlenog);
		}
		{
			this.tfZaposleni = new JTextField();
			this.tfZaposleni.setEditable(false);
			this.tfZaposleni.setColumns(10);
			this.tfZaposleni.setBounds(185, 28, 300, 20);
			contentPanel.add(this.tfZaposleni);
		}
		{
			JLabel lblDatumZaposlenja = new JLabel("Datum zaposlenja (format GGGG-MM-DD):");
			lblDatumZaposlenja.setBounds(10, 59, 125, 14);
			contentPanel.add(lblDatumZaposlenja);
		}
		{
			this.tfDatumZaposlenja = new JTextField();
			this.tfDatumZaposlenja.setColumns(10);
			this.tfDatumZaposlenja.setBounds(10, 73, 125, 20);
			contentPanel.add(this.tfDatumZaposlenja);
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
							String dateString = tfDatumZaposlenja.getText();
							java.sql.Date sqlDate = java.sql.Date.valueOf(dateString);
							ZaposleniNaOdjelima zaposleniNaOdjelu = new ZaposleniNaOdjelima(sqlDate,
									zaposleni, odjel);
							boolean rezultat;
							/*if (izmena) {
								rezultat = Utilities.getDataAccessFactory()
										.getZaposleniNaOdjelimaDataAccess()
										.azurirajZaposlenogNaOdjelu(zaposleniNaOdjelu);
								if (!rezultat)
									JOptionPane
											.showMessageDialog(
													ovaj,
													"Zaposleni na odjelu nije uspješno ažuriran!",
													"Poruka",
													JOptionPane.INFORMATION_MESSAGE);
							} else {*/
								rezultat = Utilities.getDataAccessFactory()
										.getZaposleniNaOdjelimaDataAccess()
										.dodajZaposlenogNaOdjelu(zaposleniNaOdjelu);
								if (!rezultat)
									JOptionPane
											.showMessageDialog(
													ovaj,
													"Zaposleni nije uspješno dodan na odjel!",
													"Poruka",
													JOptionPane.INFORMATION_MESSAGE);
							//}
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
				okButton.setIcon(new ImageIcon(ZaposleniNaOdjelimaDialog.class
						.getResource(Utilities.IMAGE_RESOURCES_PATH + "Check_14.png")));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				// getRootPane().setDefaultButton(okButton);
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
								ZaposleniNaOdjelimaDialog.class
										.getResource(Utilities.IMAGE_RESOURCES_PATH + "Cancel_14.png")));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
}

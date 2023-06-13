package org.unibl.etf.bp.hospitalis.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;

import org.unibl.etf.bp.hospitalis.entity.KontaktOsoba;
import org.unibl.etf.bp.hospitalis.util.Utilities;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.util.List;

@SuppressWarnings("serial")
public class KontaktOsobeFrame extends JFrame {
	
	private KontaktOsobeFrame ovaj;
	private List<KontaktOsoba> kontaktOsobe;

	private KontaktOsoba odabranaOsoba;

	private JPanel contentPane;
	private JPanel panel;
	private JPanel panelPodaci;
	private JPanel panelOpcije;
	private JPanel panelPretraga;
	private JButton btnDodati;
	private JButton btnIzmeniti;
	private JButton btnObrisati;
	private JButton btnPrihvatiti;
	private JLabel lblJmbKontaktOsobe;
	private JTextField tfJmbKontaktOsobe;
	private JButton btnPretraziti;
	private JButton btnPrikazatiSve;
	private JScrollPane scrollPane;
	private JTable table;

	/**
	 * Create the frame.
	 */
	public KontaktOsobeFrame(boolean odabirOsobe) {
		ovaj = this;
		kontaktOsobe = Utilities.getDataAccessFactory().getKontaktOsobaDataAccess()
				.kontaktOsobe("*");

		initialize();

		if (!odabirOsobe)
			btnPrihvatiti.setVisible(false);
	}

	public KontaktOsoba getOdabranaOsoba() {
		return odabranaOsoba;
	}

	private void osveziTabelu() {
		if (Utilities.isSearchPatternValid(tfJmbKontaktOsobe.getText())) {
			kontaktOsobe = Utilities.getDataAccessFactory().getKontaktOsobaDataAccess()
					.kontaktOsobe(tfJmbKontaktOsobe.getText());

			KontaktOsobaTableModel ftm = (KontaktOsobaTableModel) table.getModel();
			ftm.setPodaci(kontaktOsobe);
			ftm.fireTableDataChanged();
		}
	}

	private void initialize() {
		setTitle("Kontakt osobe");
		setBounds(100, 100, 815, 420);
		setLocationRelativeTo(null);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(this.contentPane);
		this.contentPane.add(getPanel(), BorderLayout.NORTH);
		this.contentPane.add(getPanelPodaci(), BorderLayout.CENTER);
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new BorderLayout(0, 0));
			panel.add(getPanelOpcije(), BorderLayout.NORTH);
			panel.add(getPanelPretraga(), BorderLayout.SOUTH);
		}
		return panel;
	}

	private JPanel getPanelPodaci() {
		if (panelPodaci == null) {
			panelPodaci = new JPanel();
			panelPodaci.setLayout(new BorderLayout(0, 0));
			panelPodaci.add(getScrollPane(), BorderLayout.CENTER);
		}
		return panelPodaci;
	}

	private JPanel getPanelOpcije() {
		if (panelOpcije == null) {
			panelOpcije = new JPanel();
			panelOpcije.setLayout(null);
			panelOpcije.setPreferredSize(new Dimension(200, 68));
			panelOpcije.add(getBtnDodati());
			panelOpcije.add(getBtnIzmeniti());
			panelOpcije.add(getBtnObrisati());
			panelOpcije.add(getBtnPrihvatiti());
		}
		return panelOpcije;
	}

	private JPanel getPanelPretraga() {
		if (panelPretraga == null) {
			panelPretraga = new JPanel();
			panelPretraga.setBorder(new TitledBorder(null, "Pretraga",
					TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelPretraga.setLayout(null);
			panelPretraga.setPreferredSize(new Dimension(200, 70));
			panelPretraga.add(getLblJmbKontaktOsobe());
			panelPretraga.add(getTfJmbKontaktOsobe());
			panelPretraga.add(getBtnPretraziti());
			panelPretraga.add(getBtnPrikazatiSve());
		}
		return panelPretraga;
	}

	private JButton getBtnDodati() {
		if (btnDodati == null) {
			btnDodati = new JButton("");
			btnDodati.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					KontaktOsobaDialog kod = new KontaktOsobaDialog();
					kod.setVisible(true);
					if (kod.getDialogResult().equalsIgnoreCase("OK")) {
						osveziTabelu();
						JOptionPane.showMessageDialog(ovaj,
								"Nova osoba je uspje�no dodana!", "Poruka",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			});
			btnDodati.setIcon(new ImageIcon(KontaktOsobeFrame.class
					.getResource(Utilities.IMAGE_RESOURCES_PATH + "Add_32.png")));
			btnDodati.setToolTipText("Dodati novu kontakt osobu");
			btnDodati.setBounds(0, 0, 58, 58);
		}
		return btnDodati;
	}

	private JButton getBtnIzmeniti() {
		if (btnIzmeniti == null) {
			btnIzmeniti = new JButton("");
			btnIzmeniti.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (table.getSelectedRow() == -1) {
						JOptionPane.showMessageDialog(ovaj,
								"Osoba nije odabrana!", "Gre�ka",
								JOptionPane.ERROR_MESSAGE);
					} else {
						KontaktOsoba odabranaOsoba = ((KontaktOsobaTableModel) table
								.getModel()).getOsobuAtRow(table
								.getSelectedRow());
						KontaktOsobaDialog zod = new KontaktOsobaDialog(odabranaOsoba);
						zod.setVisible(true);
						if (zod.getDialogResult().equalsIgnoreCase("OK")) {
							osveziTabelu();
							JOptionPane.showMessageDialog(ovaj,
									"Osoba je uspje�no a�urirana!", "Poruka",
									JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
			});
			btnIzmeniti.setIcon(new ImageIcon(KontaktOsobeFrame.class
					.getResource(Utilities.IMAGE_RESOURCES_PATH + "Edit_32.png")));
			btnIzmeniti.setToolTipText("Izmijeniti odabranu osobu");
			btnIzmeniti.setBounds(68, 0, 58, 58);
		}
		return btnIzmeniti;
	}

	private JButton getBtnObrisati() {
		if (btnObrisati == null) {
			btnObrisati = new JButton("");
			btnObrisati.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (table.getSelectedRow() == -1) {
						JOptionPane.showMessageDialog(ovaj,
								"Osoba nije odabrana!", "Gre�ka",
								JOptionPane.ERROR_MESSAGE);
					} else {
						KontaktOsoba odabranaOsoba = ((KontaktOsobaTableModel) table
								.getModel()).getOsobuAtRow(table
								.getSelectedRow());
						int odabir = JOptionPane
								.showOptionDialog(
										ovaj,
										"Da li ste sigurni da �elite obrisati odabranu osobu?",
										"Potvrda brisanja",
										JOptionPane.YES_NO_OPTION,
										JOptionPane.QUESTION_MESSAGE, null,
										Utilities.YES_NO_OPTIONS,
										Utilities.YES_NO_OPTIONS[1]);
						if (odabir == JOptionPane.YES_OPTION) {
							if (Utilities
									.getDataAccessFactory()
									.getKontaktOsobaDataAccess()
									.obrisiKontaktOsobu(
											odabranaOsoba.getJmbKontaktOsobe())) {
								osveziTabelu();
								JOptionPane.showMessageDialog(ovaj,
										"Osoba je uspje�no obrisana!",
										"Poruka",
										JOptionPane.INFORMATION_MESSAGE);
							} else
								JOptionPane.showMessageDialog(ovaj,
										"Osoba nije uspje�no obrisana!",
										"Poruka",
										JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
			});
			btnObrisati.setIcon(new ImageIcon(KontaktOsobeFrame.class
					.getResource(Utilities.IMAGE_RESOURCES_PATH + "Delete_32.png")));
			btnObrisati.setToolTipText("Obrisati odabranu osobu");
			btnObrisati.setBounds(136, 0, 58, 58);
		}
		return btnObrisati;
	}

	private JButton getBtnPrihvatiti() {
		if (btnPrihvatiti == null) {
			btnPrihvatiti = new JButton("");
			btnPrihvatiti.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (table.getSelectedRow() == -1) {
						JOptionPane.showMessageDialog(ovaj,
								"Osoba nije odabrana!", "Gre�ka",
								JOptionPane.ERROR_MESSAGE);
					} else {
						odabranaOsoba = ((KontaktOsobaTableModel) table.getModel())
								.getOsobuAtRow(table.getSelectedRow());
						ovaj.getToolkit()
								.getSystemEventQueue()
								.postEvent(
										new WindowEvent(ovaj,
												WindowEvent.WINDOW_CLOSING));
					}
				}
			});
			btnPrihvatiti.setIcon(new ImageIcon(KontaktOsobeFrame.class
					.getResource(Utilities.IMAGE_RESOURCES_PATH + "Check_32.png")));
			btnPrihvatiti.setToolTipText("Prihvatiti odabranu osobu");
			btnPrihvatiti.setBounds(204, 0, 58, 58);
		}
		return btnPrihvatiti;
	}

	private JLabel getLblJmbKontaktOsobe() {
		if (lblJmbKontaktOsobe == null) {
			lblJmbKontaktOsobe = new JLabel("JMB:");
			lblJmbKontaktOsobe.setBounds(10, 21, 254, 14);
		}
		return lblJmbKontaktOsobe;
	}

	private JTextField getTfJmbKontaktOsobe() {
		if (tfJmbKontaktOsobe == null) {
			tfJmbKontaktOsobe = new JTextField();
			tfJmbKontaktOsobe.setText("*");
			tfJmbKontaktOsobe.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent arg0) {
					if (arg0.getKeyCode() == KeyEvent.VK_ENTER)
						btnPretraziti.doClick();
				}
			});
			tfJmbKontaktOsobe.setBounds(10, 38, 254, 20);
			tfJmbKontaktOsobe.setColumns(10);
		}
		return tfJmbKontaktOsobe;
	}

	private JButton getBtnPretraziti() {
		if (btnPretraziti == null) {
			btnPretraziti = new JButton("Pretražiti");
			btnPretraziti.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (Utilities.isSearchPatternValid(tfJmbKontaktOsobe
							.getText()))
						osveziTabelu();
					else
						JOptionPane.showMessageDialog(ovaj,
								"JMB kontakt osobe nije pravilno popunjen!",
								"Gre�ka", JOptionPane.ERROR_MESSAGE);
				}
			});
			btnPretraziti.setBounds(274, 37, 100, 23);
		}
		return btnPretraziti;
	}

	private JButton getBtnPrikazatiSve() {
		if (btnPrikazatiSve == null) {
			btnPrikazatiSve = new JButton("Prikazati sve");
			btnPrikazatiSve.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					tfJmbKontaktOsobe.setText("*");
					osveziTabelu();
				}
			});
			btnPrikazatiSve.setBounds(384, 37, 100, 23);
		}
		return btnPrikazatiSve;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane
					.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPane.setViewportView(getTable());
		}
		return scrollPane;
	}

	private JTable getTable() {
		if (table == null) {
			table = new JTable(new KontaktOsobaTableModel(kontaktOsobe));
			table.setFillsViewportHeight(true);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.getColumnModel().getColumn(0).setPreferredWidth(300);
			table.getColumnModel().getColumn(1).setPreferredWidth(450);
		}
		return table;
	}
	
}
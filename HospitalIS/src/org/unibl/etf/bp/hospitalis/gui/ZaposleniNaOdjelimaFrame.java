package org.unibl.etf.bp.hospitalis.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import javax.swing.border.TitledBorder;

import org.unibl.etf.bp.hospitalis.entity.ZaposleniNaOdjelima;
import org.unibl.etf.bp.hospitalis.entity.Odjel;
import org.unibl.etf.bp.hospitalis.util.Utilities;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class ZaposleniNaOdjelimaFrame extends JFrame {
	
	private ZaposleniNaOdjelimaFrame ovaj;
	private List<ZaposleniNaOdjelima> zaposleniNaOdjelima;

	private Odjel odjel;

	private JPanel contentPane;
	private JPanel panel;
	private JPanel panelPodaci;
	private JPanel panelOpcije;
	private JPanel panelPretraga;
	private JButton btnDodati;
	private JButton btnIzmeniti;
	private JButton btnObrisati;
	private JLabel lblOdjel;
	private JTextField tfIdOdjela;
	private JButton btnOdaberiOdjel;
	private JTextField tfOdjel;
	private JButton btnPretraziti;
	private JScrollPane scrollPane;
	private JTable table;

	/**
	 * Create the frame.
	 */
	public ZaposleniNaOdjelimaFrame() {
		ovaj = this;
		zaposleniNaOdjelima = new ArrayList<ZaposleniNaOdjelima>();

		initialize();
	}

	private void pronadjiOdjel(boolean tiho) {
		tfOdjel.setText("");
		odjel = null;
		ocistiTabelu();

		if (Utilities.tryParseInt(tfIdOdjela.getText())) {
			odjel = Utilities.getDataAccessFactory()
					.getOdjelDataAccess()
					.odjel(Integer.valueOf(tfIdOdjela.getText()));
			if (odjel != null)
				tfOdjel.setText(odjel.toString());
		} else if (!tiho)
			JOptionPane
					.showMessageDialog(
							ovaj,
							"Identifikator odjela nije pravilno popunjen!",
							"Greška", JOptionPane.ERROR_MESSAGE);
	}

	private void osveziTabelu() {
		if (odjel != null) {
			zaposleniNaOdjelima = Utilities.getDataAccessFactory()
					.getZaposleniNaOdjelimaDataAccess()
					.zaposleniNaOdjelima(odjel.getIdOdjela());

			ZaposleniNaOdjelimaTableModel ftm = (ZaposleniNaOdjelimaTableModel) table
					.getModel();
			ftm.setPodaci(zaposleniNaOdjelima);
			ftm.fireTableDataChanged();
		}
	}

	private void ocistiTabelu() {
		zaposleniNaOdjelima = new ArrayList<ZaposleniNaOdjelima>();

		ZaposleniNaOdjelimaTableModel ftm = (ZaposleniNaOdjelimaTableModel) table.getModel();
		ftm.setPodaci(zaposleniNaOdjelima);
		ftm.fireTableDataChanged();
	}

	private void initialize() {
		setTitle("Zaposleni na odjelima");
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
			panelOpcije.setPreferredSize(new Dimension(200, 68));
			panelOpcije.setLayout(null);
			panelOpcije.add(getBtnDodati());
			//panelOpcije.add(getBtnIzmeniti());
			panelOpcije.add(getBtnObrisati());
		}
		return panelOpcije;
	}

	private JPanel getPanelPretraga() {
		if (panelPretraga == null) {
			panelPretraga = new JPanel();
			panelPretraga.setBorder(new TitledBorder(null, "Pretraga",
					TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelPretraga.setPreferredSize(new Dimension(200, 70));
			panelPretraga.setLayout(null);
			panelPretraga.add(getLblOdjel());
			panelPretraga.add(getTfIdOdjela());
			panelPretraga.add(getBtnOdaberiOdjel());
			panelPretraga.add(getTfOdjel());
			panelPretraga.add(getBtnPretraziti());
		}
		return panelPretraga;
	}

	private JButton getBtnDodati() {
		if (btnDodati == null) {
			btnDodati = new JButton("");
			btnDodati.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (odjel == null) {
						JOptionPane.showMessageDialog(ovaj,
								"Odjel nije odabran!", "Greška",
								JOptionPane.ERROR_MESSAGE);
					} else {
						final ZaposleniNaOdjelimaDialog znod = new ZaposleniNaOdjelimaDialog(
								odjel);
						znod.addWindowListener(new WindowAdapter() {
							@Override
							public void windowClosing(WindowEvent e) {
								if (znod.getDialogResult()
										.equalsIgnoreCase("OK")) {
									osveziTabelu();
									JOptionPane
											.showMessageDialog(
													ovaj,
													"Zaposleni je uspješno dodan na odjel!",
													"Poruka",
													JOptionPane.INFORMATION_MESSAGE);
								}
							}
						});
						znod.setVisible(true);
					}
				}
			});
			btnDodati.setIcon(new ImageIcon(ZaposleniNaOdjelimaFrame.class
					.getResource(Utilities.IMAGE_RESOURCES_PATH + "Add_32.png")));
			btnDodati
					.setToolTipText("Dodati novog zaposlenog na odabrani odjel");
			btnDodati.setBounds(0, 0, 58, 58);
		}
		return btnDodati;
	}

	/*private JButton getBtnIzmeniti() {
		if (btnIzmeniti == null) {
			btnIzmeniti = new JButton("");
			btnIzmeniti.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (table.getSelectedRow() == -1) {
						JOptionPane.showMessageDialog(ovaj,
								"Zaposleni nije odabran!", "Greška",
								JOptionPane.ERROR_MESSAGE);
					} else {
						PredmetNaStudijskomProgramu odabraniPredmetNaSP = ((PlanIProgramTableModel) table
								.getModel())
								.getPredmetNaStudijskomProgramuAtRow(table
										.getSelectedRow());
						final PlanIProgramDialog ppd = new PlanIProgramDialog(
								odabraniPredmetNaSP);
						ppd.addWindowListener(new WindowAdapter() {
							@Override
							public void windowClosing(WindowEvent e) {
								if (ppd.getDialogResult()
										.equalsIgnoreCase("OK")) {
									osveziTabelu();
									JOptionPane.showMessageDialog(ovaj,
											"Predmet je uspešno ažuriran!",
											"Poruka",
											JOptionPane.INFORMATION_MESSAGE);
								}
							}
						});
						ppd.setVisible(true);
					}
				}
			});
			btnIzmeniti.setIcon(new ImageIcon(PlanIProgramFrame.class
					.getResource(Utilities.IMAGE_RESOURCES_PATH + "Edit_32.png")));
			btnIzmeniti
					.setToolTipText("Izmeniti odabrani predmet na studijskom programu");
			btnIzmeniti.setBounds(68, 0, 58, 58);
		}
		return btnIzmeniti;
	}*/

	private JButton getBtnObrisati() {
		if (btnObrisati == null) {
			btnObrisati = new JButton("");
			btnObrisati.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (table.getSelectedRow() == -1) {
						JOptionPane.showMessageDialog(ovaj,
								"Zaposleni nije odabran!", "Greška",
								JOptionPane.ERROR_MESSAGE);
					} else {
						ZaposleniNaOdjelima odabraniZaposleniNaOdjelu = ((ZaposleniNaOdjelimaTableModel) table
								.getModel())
								.getZaposleniNaOdjelimaAtRow(table
										.getSelectedRow());
						int odabir = JOptionPane
								.showOptionDialog(
										ovaj,
										"Da li ste sigurni da želite obrisati odabranog zaposlenog sa odjela?",
										"Potvrda brisanja",
										JOptionPane.YES_NO_OPTION,
										JOptionPane.QUESTION_MESSAGE, null,
										Utilities.YES_NO_OPTIONS,
										Utilities.YES_NO_OPTIONS[1]);
						if (odabir == JOptionPane.YES_OPTION) {
							if (Utilities
									.getDataAccessFactory()
									.getZaposleniNaOdjelimaDataAccess()
									.obrisiZaposlenogNaOdjelu(
											odabraniZaposleniNaOdjelu.getDatumZaposlenja(),
											odabraniZaposleniNaOdjelu
													.getZaposleni().getJmb(),odabraniZaposleniNaOdjelu
													.getOdjel().getIdOdjela() )) {
								osveziTabelu();
								JOptionPane
										.showMessageDialog(
												ovaj,
												"Zaposleni je uspješno obrisan sa odjela!",
												"Poruka",
												JOptionPane.INFORMATION_MESSAGE);
							} else
								JOptionPane
										.showMessageDialog(
												ovaj,
												"Zaposleni nije uspješno obrisan sa odjela!",
												"Poruka",
												JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
			});
			btnObrisati.setIcon(new ImageIcon(ZaposleniNaOdjelimaFrame.class
					.getResource(Utilities.IMAGE_RESOURCES_PATH + "Delete_32.png")));
			btnObrisati
					.setToolTipText("Obrisati odabranog zaposlenog sa odjela");
			//btnObrisati.setBounds(136, 0, 58, 58);
			btnObrisati.setBounds(68, 0, 58, 58);
		}
		return btnObrisati;
	}

	private JLabel getLblOdjel() {
		if (lblOdjel == null) {
			lblOdjel = new JLabel("Odjel:");
			lblOdjel.setBounds(10, 22, 125, 14);
		}
		return lblOdjel;
	}

	private JTextField getTfIdOdjela() {
		if (tfIdOdjela == null) {
			tfIdOdjela = new JTextField();
			tfIdOdjela.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent arg0) {
					pronadjiOdjel(true);
				}
			});
			tfIdOdjela.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent arg0) {
					if (arg0.getKeyCode() == KeyEvent.VK_ENTER)
						pronadjiOdjel(false);
				}
			});
			tfIdOdjela.setColumns(10);
			tfIdOdjela.setBounds(10, 39, 125, 20);
		}
		return tfIdOdjela;
	}

	private JButton getBtnOdaberiOdjel() {
		if (btnOdaberiOdjel == null) {
			btnOdaberiOdjel = new JButton("");
			btnOdaberiOdjel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ovaj.setVisible(false);
					final OdjeliFrame of = new OdjeliFrame(
							true);
					of.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosing(WindowEvent e) {
							if (of.getOdabraniOdjel() != null) {
								ocistiTabelu();

								odjel = of
										.getOdabraniOdjel();
								tfIdOdjela.setText(Integer
										.toString(odjel.getIdOdjela()));
								tfOdjel.setText(odjel
										.toString());
							}
							of.dispose();
							ovaj.setVisible(true);
							ovaj.toFront();
						}
					});
					of.setVisible(true);
				}
			});
			btnOdaberiOdjel.setIcon(new ImageIcon(ZaposleniNaOdjelimaFrame.class
					.getResource(Utilities.IMAGE_RESOURCES_PATH + "Lookup_14.png")));
			btnOdaberiOdjel.setBounds(145, 38, 30, 23);
		}
		return btnOdaberiOdjel;
	}

	private JTextField getTfOdjel() {
		if (tfOdjel == null) {
			tfOdjel = new JTextField();
			tfOdjel.setEditable(false);
			tfOdjel.setBounds(185, 39, 300, 20);
			tfOdjel.setColumns(10);
		}
		return tfOdjel;
	}

	private JButton getBtnPretraziti() {
		if (btnPretraziti == null) {
			btnPretraziti = new JButton("Pretražiti");
			btnPretraziti.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (odjel == null) {
						JOptionPane.showMessageDialog(ovaj,
								"Odjel nije odabran!", "Greška",
								JOptionPane.ERROR_MESSAGE);
					} else
						osveziTabelu();
				}
			});
			btnPretraziti.setBounds(495, 36, 100, 23);
		}
		return btnPretraziti;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTable());
		}
		return scrollPane;
	}

	private JTable getTable() {
		if (table == null) {
			table = new JTable(new ZaposleniNaOdjelimaTableModel(zaposleniNaOdjelima));
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.setFillsViewportHeight(true);
			table.getColumnModel().getColumn(0).setPreferredWidth(250);
			table.getColumnModel().getColumn(1).setPreferredWidth(200);
			table.getColumnModel().getColumn(2).setPreferredWidth(250);
			table.getColumnModel().getColumn(3).setPreferredWidth(250);
		}
		return table;
	}
	
}


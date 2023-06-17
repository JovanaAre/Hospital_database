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

import org.unibl.etf.bp.hospitalis.entity.DijagnostickiPregled;
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
public class DijagnostickiPreglediFrame extends JFrame {
	
	private DijagnostickiPreglediFrame ovaj;
	private List<DijagnostickiPregled> dijagnostickiPregledi;

	private DijagnostickiPregled odabraniDijagnostickiPregled;

	private JPanel contentPane;
	private JPanel panel;
	private JPanel panelPodaci;
	private JPanel panelOpcije;
	private JPanel panelPretraga;
	private JButton btnDodati;
	private JButton btnIzmeniti;
	private JButton btnObrisati;
	private JButton btnPrihvatiti;
	private JLabel lblNazivPregleda;
	private JTextField tfNazivPregleda;
	private JButton btnPretraziti;
	private JButton btnPrikazatiSve;
	private JScrollPane scrollPane;
	private JTable table;

	/**
	 * Create the frame.
	 */
	public DijagnostickiPreglediFrame(boolean odabirDijagnostickogPregleda) {
		ovaj = this;
		dijagnostickiPregledi = Utilities.getDataAccessFactory().getDijagnostickiPregledDataAccess()
				.dijagnostickiPregledi("*");

		initialize();

		if (!odabirDijagnostickogPregleda)
			btnPrihvatiti.setVisible(false);
	}

	public DijagnostickiPregled getOdabraniDijagnostickiPregled() {
		return odabraniDijagnostickiPregled;
	}

	private void osveziTabelu() {
		if (Utilities.isSearchPatternValid(tfNazivPregleda.getText())) {
			dijagnostickiPregledi = Utilities.getDataAccessFactory().getDijagnostickiPregledDataAccess()
					.dijagnostickiPregledi(tfNazivPregleda.getText());

			DijagnostickiPregledTableModel ftm = (DijagnostickiPregledTableModel) table.getModel();
			ftm.setPodaci(dijagnostickiPregledi);
			ftm.fireTableDataChanged();
		}
	}

	private void initialize() {
		setTitle("Dijagnostički pregledi");
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
			panelPretraga.add(getLblNazivPregleda());
			panelPretraga.add(getTfNazivPregleda());
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
					DijagnostickiPregledDialog dpd = new DijagnostickiPregledDialog();
					dpd.setVisible(true);
					if (dpd.getDialogResult().equalsIgnoreCase("OK")) {
						osveziTabelu();
						JOptionPane.showMessageDialog(ovaj,
								"Novi pregled je uspješno dodan!", "Poruka",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			});
			btnDodati.setIcon(new ImageIcon(DijagnostickiPreglediFrame.class
					.getResource(Utilities.IMAGE_RESOURCES_PATH + "Add_32.png")));
			btnDodati.setToolTipText("Dodati novi pregled");
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
								"Pregled nije odabran!", "Greška",
								JOptionPane.ERROR_MESSAGE);
					} else {
						DijagnostickiPregled odabraniDijagnostickiPregled = ((DijagnostickiPregledTableModel) table
								.getModel()).getDijagnostickiPregledAtRow(table
								.getSelectedRow());
						DijagnostickiPregledDialog dpd = new DijagnostickiPregledDialog(odabraniDijagnostickiPregled);
						dpd.setVisible(true);
						if (dpd.getDialogResult().equalsIgnoreCase("OK")) {
							osveziTabelu();
							JOptionPane.showMessageDialog(ovaj,
									"Pregled je uspješno ažuriran!", "Poruka",
									JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
			});
			btnIzmeniti.setIcon(new ImageIcon(DijagnostickiPreglediFrame.class
					.getResource(Utilities.IMAGE_RESOURCES_PATH + "Edit_32.png")));
			btnIzmeniti.setToolTipText("Izmeniti odabrani pregled");
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
								"Pregled nije odabran!", "Greška",
								JOptionPane.ERROR_MESSAGE);
					} else {
						DijagnostickiPregled odabraniDijagnostickiPregled = ((DijagnostickiPregledTableModel) table
								.getModel()).getDijagnostickiPregledAtRow(table
								.getSelectedRow());
						int odabir = JOptionPane
								.showOptionDialog(
										ovaj,
										"Da li ste sigurni da želite obrisati odabrani pregled?",
										"Potvrda brisanja",
										JOptionPane.YES_NO_OPTION,
										JOptionPane.QUESTION_MESSAGE, null,
										Utilities.YES_NO_OPTIONS,
										Utilities.YES_NO_OPTIONS[1]);
						if (odabir == JOptionPane.YES_OPTION) {
							if (Utilities
									.getDataAccessFactory()
									.getDijagnostickiPregledDataAccess()
									.obrisiDijagnostickiPregled(
											odabraniDijagnostickiPregled.getIdPregleda())) {
										osveziTabelu();
								JOptionPane.showMessageDialog(ovaj,
										"Pregled je uspješno obrisan!",
										"Poruka",
										JOptionPane.INFORMATION_MESSAGE);
							} else
								JOptionPane.showMessageDialog(ovaj,
										"Pregled nije uspješno obrisan!",
										"Poruka",
										JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
			});
			btnObrisati.setIcon(new ImageIcon(DijagnostickiPreglediFrame.class
					.getResource(Utilities.IMAGE_RESOURCES_PATH + "Delete_32.png")));
			btnObrisati.setToolTipText("Obrisati odabrani pregled");
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
								"Pregled nije odabran!", "Greška",
								JOptionPane.ERROR_MESSAGE);
					} else {
						odabraniDijagnostickiPregled = ((DijagnostickiPregledTableModel) table.getModel())
								.getDijagnostickiPregledAtRow(table.getSelectedRow());
						ovaj.getToolkit()
								.getSystemEventQueue()
								.postEvent(
										new WindowEvent(ovaj,
												WindowEvent.WINDOW_CLOSING));
					}
				}
			});
			btnPrihvatiti.setIcon(new ImageIcon(DijagnostickiPreglediFrame.class
					.getResource(Utilities.IMAGE_RESOURCES_PATH + "Check_32.png")));
			btnPrihvatiti.setToolTipText("Prihvatiti odabrani pregled");
			btnPrihvatiti.setBounds(204, 0, 58, 58);
		}
		return btnPrihvatiti;
	}

	private JLabel getLblNazivPregleda() {
		if (lblNazivPregleda == null) {
			lblNazivPregleda = new JLabel("Naziv pregleda:");
			lblNazivPregleda.setBounds(10, 21, 254, 14);
		}
		return lblNazivPregleda;
	}

	private JTextField getTfNazivPregleda() {
		if (tfNazivPregleda == null) {
			tfNazivPregleda = new JTextField();
			tfNazivPregleda.setText("*");
			tfNazivPregleda.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent arg0) {
					if (arg0.getKeyCode() == KeyEvent.VK_ENTER)
						btnPretraziti.doClick();
				}
			});
			tfNazivPregleda.setBounds(10, 38, 254, 20);
			tfNazivPregleda.setColumns(10);
		}
		return tfNazivPregleda;
	}

	private JButton getBtnPretraziti() {
		if (btnPretraziti == null) {
			btnPretraziti = new JButton("Pretražiti");
			btnPretraziti.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (Utilities.isSearchPatternValid(tfNazivPregleda
							.getText()))
						osveziTabelu();
					else
						JOptionPane.showMessageDialog(ovaj,
								"Naziv pregleda nije pravilno popunjen!",
								"Greška", JOptionPane.ERROR_MESSAGE);
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
					tfNazivPregleda.setText("*");
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
			table = new JTable(new DijagnostickiPregledTableModel(dijagnostickiPregledi));
			table.setFillsViewportHeight(true);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.getColumnModel().getColumn(0).setPreferredWidth(100);
			table.getColumnModel().getColumn(1).setPreferredWidth(100);
			table.getColumnModel().getColumn(0).setPreferredWidth(100);
		}
		return table;
	}	
}
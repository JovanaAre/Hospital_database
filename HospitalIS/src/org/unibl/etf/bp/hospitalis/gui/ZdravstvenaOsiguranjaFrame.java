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

import org.unibl.etf.bp.hospitalis.entity.ZdravstvenoOsiguranje;
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
public class ZdravstvenaOsiguranjaFrame extends JFrame {
	
	private ZdravstvenaOsiguranjaFrame ovaj;
	private List<ZdravstvenoOsiguranje> zdravstvenaOsiguranja;

	private ZdravstvenoOsiguranje odabranoOsiguranje;

	private JPanel contentPane;
	private JPanel panel;
	private JPanel panelPodaci;
	private JPanel panelOpcije;
	private JPanel panelPretraga;
	private JButton btnDodati;
	private JButton btnIzmeniti;
	private JButton btnObrisati;
	private JButton btnPrihvatiti;
	private JLabel lblDavalacOsiguranja;
	private JTextField tfDavalacOsiguranja;
	private JButton btnPretraziti;
	private JButton btnPrikazatiSve;
	private JScrollPane scrollPane;
	private JTable table;

	/**
	 * Create the frame.
	 */
	public ZdravstvenaOsiguranjaFrame(boolean odabirOsiguranja) {
		ovaj = this;
		zdravstvenaOsiguranja = Utilities.getDataAccessFactory().getZdravstvenoOsiguranjeDataAccess()
				.zdravstvenaOsiguranja("*");

		initialize();

		if (!odabirOsiguranja)
			btnPrihvatiti.setVisible(false);
	}

	public ZdravstvenoOsiguranje getOdabranoOsiguranje() {
		return odabranoOsiguranje;
	}

	private void osveziTabelu() {
		if (Utilities.isSearchPatternValid(tfDavalacOsiguranja.getText())) {
			zdravstvenaOsiguranja = Utilities.getDataAccessFactory().getZdravstvenoOsiguranjeDataAccess()
					.zdravstvenaOsiguranja(tfDavalacOsiguranja.getText());

			ZdravstvenoOsiguranjeTableModel ftm = (ZdravstvenoOsiguranjeTableModel) table.getModel();
			ftm.setPodaci(zdravstvenaOsiguranja);
			ftm.fireTableDataChanged();
		}
	}

	private void initialize() {
		setTitle("Zdravstvena osiguranja");
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
			panelPretraga.add(getLblDavalacOsiguranja());
			panelPretraga.add(getTfDavalacOsiguranja());
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
					ZdravstvenoOsiguranjeDialog zod = new ZdravstvenoOsiguranjeDialog();
					zod.setVisible(true);
					if (zod.getDialogResult().equalsIgnoreCase("OK")) {
						osveziTabelu();
						JOptionPane.showMessageDialog(ovaj,
								"Novo osiguranje je uspješno dodano!", "Poruka",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			});
			btnDodati.setIcon(new ImageIcon(ZdravstvenaOsiguranjaFrame.class
					.getResource(Utilities.IMAGE_RESOURCES_PATH + "Add_32.png")));
			btnDodati.setToolTipText("Dodati novo osiguranje");
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
								"Osiguranje nije odabrano!", "Gre�ka",
								JOptionPane.ERROR_MESSAGE);
					} else {
						ZdravstvenoOsiguranje odabranoOsiguranje = ((ZdravstvenoOsiguranjeTableModel) table
								.getModel()).getOsiguranjeAtRow(table
								.getSelectedRow());
						ZdravstvenoOsiguranjeDialog zod = new ZdravstvenoOsiguranjeDialog(odabranoOsiguranje);
						zod.setVisible(true);
						if (zod.getDialogResult().equalsIgnoreCase("OK")) {
							osveziTabelu();
							JOptionPane.showMessageDialog(ovaj,
									"Osiguranje je uspješno ažurirano!", "Poruka",
									JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
			});
			btnIzmeniti.setIcon(new ImageIcon(ZdravstvenaOsiguranjaFrame.class
					.getResource(Utilities.IMAGE_RESOURCES_PATH + "Edit_32.png")));
			btnIzmeniti.setToolTipText("Izmeniti odabrano osiguranje");
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
								"Osiguranje nije odabrano!", "Greška",
								JOptionPane.ERROR_MESSAGE);
					} else {
						ZdravstvenoOsiguranje odabranoOsiguranje = ((ZdravstvenoOsiguranjeTableModel) table
								.getModel()).getOsiguranjeAtRow(table
								.getSelectedRow());
						int odabir = JOptionPane
								.showOptionDialog(
										ovaj,
										"Da li ste sigurni da želite obrisati odabrano osiguranje?",
										"Potvrda brisanja",
										JOptionPane.YES_NO_OPTION,
										JOptionPane.QUESTION_MESSAGE, null,
										Utilities.YES_NO_OPTIONS,
										Utilities.YES_NO_OPTIONS[1]);
						if (odabir == JOptionPane.YES_OPTION) {
							if (Utilities
									.getDataAccessFactory()
									.getZdravstvenoOsiguranjeDataAccess()
									.obrisiZdravstvenoOsiguranje(
											odabranoOsiguranje.getDavalacOsiguranja())) {
								osveziTabelu();
								JOptionPane.showMessageDialog(ovaj,
										"Osiguranje je uspe�no obrisano!",
										"Poruka",
										JOptionPane.INFORMATION_MESSAGE);
							} else
								JOptionPane.showMessageDialog(ovaj,
										"Osiguranje nije uspe�no obrisano!",
										"Poruka",
										JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
			});
			btnObrisati.setIcon(new ImageIcon(ZdravstvenaOsiguranjaFrame.class
					.getResource(Utilities.IMAGE_RESOURCES_PATH + "Delete_32.png")));
			btnObrisati.setToolTipText("Obrisati odabrano osiguranje");
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
								"Osiguranje nije odabrano!", "Gre�ka",
								JOptionPane.ERROR_MESSAGE);
					} else {
						odabranoOsiguranje = ((ZdravstvenoOsiguranjeTableModel) table.getModel())
								.getOsiguranjeAtRow(table.getSelectedRow());
						ovaj.getToolkit()
								.getSystemEventQueue()
								.postEvent(
										new WindowEvent(ovaj,
												WindowEvent.WINDOW_CLOSING));
					}
				}
			});
			btnPrihvatiti.setIcon(new ImageIcon(ZdravstvenaOsiguranjaFrame.class
					.getResource(Utilities.IMAGE_RESOURCES_PATH + "Check_32.png")));
			btnPrihvatiti.setToolTipText("Prihvatiti odabrano osiguranje");
			btnPrihvatiti.setBounds(204, 0, 58, 58);
		}
		return btnPrihvatiti;
	}

	private JLabel getLblDavalacOsiguranja() {
		if (lblDavalacOsiguranja == null) {
			lblDavalacOsiguranja = new JLabel("Davalac osiguranja:");
			lblDavalacOsiguranja.setBounds(10, 21, 254, 14);
		}
		return lblDavalacOsiguranja;
	}

	private JTextField getTfDavalacOsiguranja() {
		if (tfDavalacOsiguranja == null) {
			tfDavalacOsiguranja = new JTextField();
			tfDavalacOsiguranja.setText("*");
			tfDavalacOsiguranja.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent arg0) {
					if (arg0.getKeyCode() == KeyEvent.VK_ENTER)
						btnPretraziti.doClick();
				}
			});
			tfDavalacOsiguranja.setBounds(10, 38, 254, 20);
			tfDavalacOsiguranja.setColumns(10);
		}
		return tfDavalacOsiguranja;
	}

	private JButton getBtnPretraziti() {
		if (btnPretraziti == null) {
			btnPretraziti = new JButton("Pretra�iti");
			btnPretraziti.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (Utilities.isSearchPatternValid(tfDavalacOsiguranja
							.getText()))
						osveziTabelu();
					else
						JOptionPane.showMessageDialog(ovaj,
								"Davalac osiguranja nije pravilno popunjen!",
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
					tfDavalacOsiguranja.setText("*");
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
			table = new JTable(new ZdravstvenoOsiguranjeTableModel(zdravstvenaOsiguranja));
			table.setFillsViewportHeight(true);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.getColumnModel().getColumn(0).setPreferredWidth(300);
			table.getColumnModel().getColumn(1).setPreferredWidth(450);
			table.getColumnModel().getColumn(1).setPreferredWidth(450);
		}
		return table;
	}
	
}
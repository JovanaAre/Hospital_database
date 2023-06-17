package org.unibl.etf.bp.hospitalis.gui;

import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.unibl.etf.bp.hospitalis.util.Utilities;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import javax.swing.ScrollPaneConstants;

import java.sql.Date;

@SuppressWarnings("serial")
public class IzvjestajiRacuniFrame extends JFrame {

	private Vector<Object> kolone;
	private Vector<Vector<Object>> podaci;

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable table;

	/**
	 * Create the frame.
	 */
	public IzvjestajiRacuniFrame() {
		kolone = new Vector<Object>();
		kolone.add("Datum računa");
		kolone.add("JMB pacijenta");
		kolone.add("Prezime i ime");
		kolone.add("Cijena pregleda");
		kolone.add("Cijena lijekova");
		kolone.add("Cijena sobe");
		kolone.add("Ukupan iznos");

		setTitle("Računi pacijenata");

		podaci = Utilities.getDataAccessFactory().getIzvjestajiDataAccess()
				.racuniPacijenata();

		initialize();
	}

	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 815, 420);
		setLocationRelativeTo(null);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(this.contentPane);
		this.contentPane.add(getScrollPane(), BorderLayout.CENTER);
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
			table = new JTable(new DefaultTableModel(podaci, kolone) {
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			});
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.setFillsViewportHeight(true);
			table.getColumnModel().getColumn(0).setPreferredWidth(150);
			table.getColumnModel().getColumn(1).setPreferredWidth(200);
			table.getColumnModel().getColumn(2).setPreferredWidth(350);
			table.getColumnModel().getColumn(3).setPreferredWidth(150);
			table.getColumnModel().getColumn(4).setPreferredWidth(150);
			table.getColumnModel().getColumn(5).setPreferredWidth(150);
			table.getColumnModel().getColumn(6).setPreferredWidth(150);
		}
		return table;
	}
	
}

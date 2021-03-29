package dao;

import domain.EmpruntArchive;
import domain.PingJdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import control.InterfaceEmpruntArchiveDao;

public class EmpruntArchiveDao implements InterfaceEmpruntArchiveDao {
	
	PingJdbc jdbc = null;
	
	public EmpruntArchiveDao(PingJdbc jdbc) {
		this.jdbc = jdbc;
		}

	
	@Override
	public ArrayList<EmpruntArchive> findByKey(int idU) {
		ArrayList<EmpruntArchive> listarchives = new ArrayList<>();
		PreparedStatement pstm;
		EmpruntArchive eac = null;
		Integer idArchive = 0;
		Integer idEx = 0;
		Integer idUtil=0;
		Date dateEmprunt;
		Date dateRestitutionEff;

		try {
			pstm = jdbc.getConnection("bibliothecaire.properties")
					.prepareStatement("SELECT * FROM EMPRUNTENCOURS, EMPRUNTARCHIVE WHERE EMPRUNTENCOURS.IDUTILISATEUR = EMPRUNTARCHIVE.IDUTILISATEUR (+) AND EMPRUNTENCOURS.idutilisateur=?");
			pstm.setInt(1, idU);
			ResultSet result = pstm.executeQuery();
			while (result.next()) {
				idArchive = result.getInt(4);
				dateEmprunt = result.getDate(3);
				dateRestitutionEff = result.getDate(6);
				idEx = result.getInt(7);
				idUtil = result.getInt(8);
				
				eac = new EmpruntArchive(idArchive, dateEmprunt, dateRestitutionEff, idUtil, idEx);
				listarchives.add(eac);
				}
			pstm.close();
				return listarchives;
				
			} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public ArrayList<EmpruntArchive> findAll() {
		ArrayList<EmpruntArchive> listarchives = new ArrayList<>();
		PreparedStatement pstm;
		EmpruntArchive eac = null;
		Integer idArchive = 0;
		Integer idEx = 0;
		Integer idUtil=0;
		Date dateEmprunt;
		Date dateRestitutionEff;

		try {
			pstm = jdbc.getConnection("bibliothecaire.properties")
					.prepareStatement("SELECT * FROM EMPRUNTARCHIVE");

			ResultSet result = pstm.executeQuery();
			while (result.next()) {
				idArchive = result.getInt(1);
				dateEmprunt = result.getDate(2);
				dateRestitutionEff = result.getDate(3);
				idEx = result.getInt(4);
				idUtil = result.getInt(5);
				
				eac = new EmpruntArchive(idArchive, dateEmprunt, dateRestitutionEff, idUtil, idEx);
				listarchives.add(eac);
				}
			pstm.close();
				return listarchives;
				
			} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}


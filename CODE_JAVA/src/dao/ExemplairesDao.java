package dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import control.InterfaceExemplaireDao;
import domain.EnumStatusExemplaire;
import domain.Exemplaire;
import domain.PingJdbc;


public class ExemplairesDao implements InterfaceExemplaireDao {

	PingJdbc jdbc = null;
	
/*
	public ExemplairesDao(Connection connection) {
		this.connection = connection;
	}*/

	public ExemplairesDao(PingJdbc jdbc) {
		this.jdbc = jdbc;
	}

	
	@Override
	public Exemplaire findByKey	(int idExemplaire) throws SQLException {
		PreparedStatement pstm;
		
		Exemplaire exemplaire = null;
		Integer idExemp = 0;
		Date dateAchat = null;
		String status = "";
		String isbn = "";
		try {
			//pstm = jdbc.getConnection("biblioFinale.properties")
			pstm = jdbc.getConnection("bibliothecaire.properties")
					.prepareStatement("Select * from exemplaire where idExemplaire = ?");
			pstm.setInt(1, idExemplaire);
			ResultSet result = pstm.executeQuery();
	           	 
				while(result.next()) {
					idExemp = result.getInt(1);
					dateAchat = result.getDate(2);
					status = result.getString(3);
					isbn = result.getString(4);
					EnumStatusExemplaire stat2 = EnumStatusExemplaire.valueOf(status.toUpperCase());
					exemplaire = new Exemplaire(idExemp, dateAchat, stat2, isbn);
				}
				
				pstm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return exemplaire;
	}
	
	
	@Override
	public ArrayList<Exemplaire> findAll() {
		ArrayList<Exemplaire> listExemplaires = new ArrayList<Exemplaire>();
		
		Exemplaire exemplaire = null;
		Integer idExemp = 0;
		Date dateAchat = null;
		String status = "";
		String isbn = "";
		try {
			Statement stm = jdbc.getConnection("bibliothecaire.properties").createStatement();
			ResultSet result = stm
				.executeQuery("Select * from exemplaire ");
	           	 
				while(result.next()) {
					idExemp = result.getInt(1);
					dateAchat = result.getDate(2);
					status = result.getString(3);
					isbn = result.getString(4);
					EnumStatusExemplaire stat2 = EnumStatusExemplaire.valueOf(status.toUpperCase());
					exemplaire = new Exemplaire(idExemp, dateAchat, stat2, isbn);
					listExemplaires.add(exemplaire);
	                }

				stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listExemplaires;
		
	}
	
	
	
	
	
}

package dao;


import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

import control.InterfaceEmpruntEnCoursDao;
import domain.BiblioException;
import domain.EmpruntEnCours;
import domain.PingJdbc;
import domain.Utilisateur;

public class EmpruntEnCoursDao implements InterfaceEmpruntEnCoursDao {
	PingJdbc jdbc = null;
	SimpleDateFormat formater = null;

	public EmpruntEnCoursDao(PingJdbc jdbc) {
		this.jdbc = jdbc;
	}
	
	@Override
	@SuppressWarnings("null")
	public boolean insertEmpruntEnCours (EmpruntEnCours emprunt) throws SQLException {
		

		Statement stmt = jdbc.getConnection("bibliothecaire.properties").createStatement();
		
		ResultSet result = stmt.executeQuery("select status from exemplaire where idexemplaire = " + emprunt.getEx().getIdExemplaire());
		
		result.next();
		if (result.getString(1).equalsIgnoreCase("DISPONIBLE")) {

			ResultSet result1 = stmt.executeQuery("SELECT dateemprunt FROM EMPRUNTENCOURS, utilisateur"
					+ " WHERE UTILISATEUR.idutilisateur = " + emprunt.getU().getIdUtilisateur() + 
					" and EMPRUNTENCOURS.idutilisateur = UTILISATEUR.idutilisateur" + 
					" and UTILISATEUR.CATEGORIEUTILISATEUR = 'ADHERENT'");
			if (result1!=null) {
			int x=0;
			int y=0;
			while(result1.next()) {
				long dateDif = ChronoUnit.DAYS.between(LocalDate.parse(result1.getDate(1).toString()), LocalDate.now());
				if( (int) dateDif > 15 && (int) dateDif!=730485) x++;
				y++;
			}
			if( y<3 ) {							
						if ( x==0 ) {
								formater = new SimpleDateFormat("dd-MM-yyyy");
								
								PreparedStatement pstmt = jdbc.getConnection("bibliothecaire.properties").prepareStatement("INSERT INTO EMPRUNTENCOURS VALUES (?, ?, TO_DATE(?, 'DD-MM-YYYY'))");
								pstmt.setInt(1,emprunt.getEx().getIdExemplaire());
								pstmt.setInt(2,emprunt.getU().getIdUtilisateur());
								pstmt.setString(3,formater.format(emprunt.getDateEmprunt()));
								pstmt.executeUpdate();
								pstmt.close();
								PreparedStatement pstmt2 = jdbc.getConnection("bibliothecaire.properties").prepareStatement("UPDATE EXEMPLAIRE SET STATUS='PRETE' WHERE IDEXEMPLAIRE = ?");
								pstmt2.setInt(1,emprunt.getEx().getIdExemplaire());
								pstmt2.executeUpdate();
								pstmt2.close();									
								result.close();
								result1.close();
								stmt.close();									
								return true;
						
						}else {
							
							JOptionPane.showMessageDialog(null, "Désolé mais vous avez "+ x +" retard(s)", "Retard emprunt(s)", JOptionPane.WARNING_MESSAGE);
							result.close();
							result1.close();
							stmt.close();
							jdbc.getConnection("bibliothecaire.properties").close();
							return false;
						}
						
									
			}else {
									
				JOptionPane.showMessageDialog(null, "Nombre maximal d'emprunts autorisé atteint", "Maximum nombre emprunts atteint", JOptionPane.WARNING_MESSAGE);
				result.close();
				result1.close();
				stmt.close();
				jdbc.getConnection("bibliothecaire.properties").close();
				return false;
				}
			
			} else {
			
				/*result1 = stmt.executeQuery("SELECT dateemprunt FROM EMPRUNTENCOURS, utilisateur"
					+ " WHERE UTILISATEUR.idutilisateur = " + emprunt.getU().getIdUtilisateur() + 
					" and EMPRUNTENCOURS.idutilisateur = UTILISATEUR.idutilisateur" + 
					" and UTILISATEUR.CATEGORIEUTILISATEUR = 'EMPLOYE'");
			
				while(result1.next()) {
				}*/
				PreparedStatement pstmt = jdbc.getConnection("bibliothecaire.properties").prepareStatement("INSERT INTO EMPRUNTENCOURS VALUES (?, ?, TO_DATE(?, 'DD-MM-YYYY'))");
				pstmt.setInt(1,emprunt.getEx().getIdExemplaire());
				pstmt.setInt(2,emprunt.getU().getIdUtilisateur());
				pstmt.setDate(3,(java.sql.Date) emprunt.getDateEmprunt());
				pstmt.executeUpdate();
				pstmt.close();
				PreparedStatement pstmt2 = jdbc.getConnection("bibliothecaire.properties").prepareStatement("UPDATE EXEMPLAIRE SET STATUS='PRETE' WHERE IDEXEMPLAIRE = ?");
				pstmt2.setInt(1,emprunt.getEx().getIdExemplaire());
				pstmt2.executeUpdate();
				pstmt2.close();									
				result.close();
				result1.close();
				stmt.close();									
				return true;
				}
			
		
		}else {
			JOptionPane.showMessageDialog(null, "L'exemplaire demandé est déja : " + result.getString(1), "Exemplaire indisponible", JOptionPane.WARNING_MESSAGE);
			
			result.close();
			stmt.close();
			jdbc.getConnection("bibliothecaire.properties").close();
			return false;
		}
		
	}
		
		
	

	@Override
	public EmpruntEnCoursDb findByKey (int idExemplaire) throws BiblioException {
		
		PreparedStatement pstm;
		
		EmpruntEnCoursDb empruntEncoursDb = null;
		//Exemplaire exemplaire = null;

		
		try {
			//pstm = jdbc.getConnection("biblioFinale.properties")
			pstm = jdbc.getConnection("bibliothecaire.properties")
					.prepareStatement("SELECT IDEXEMPLAIRE FROM EXEMPLAIRE WHERE IDEXEMPLAIRE = " + idExemplaire);
			pstm.setInt(1, idExemplaire);
			ResultSet result = pstm.executeQuery();
	           
			boolean next = result.next();
			
			
			if( next ) {
				int idEx = result.getInt("IDEXEMPLAIRE"); 
				int idU = result.getInt("IDUTILISATEUR"); 			
				empruntEncoursDb = new EmpruntEnCoursDb(idEx,idU); 
			}
			else {
				empruntEncoursDb = null;
			}
			
			
			/*
			
				while(result.next()) {
					idExemplaire = result.getInt(1);
				}
				*/
				pstm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return empruntEncoursDb;
	}
	
	
	@Override
	public List<EmpruntEnCoursDb> findByUtilisateur(Utilisateur u) throws SQLException, BiblioException {
		Statement stmt2 = jdbc.getConnection("bibliothecaire.properties").createStatement();
		List<EmpruntEnCoursDb> listeEmpruntEnCoursDb = new ArrayList<EmpruntEnCoursDb>();
		ResultSet result2 = stmt2.executeQuery("SELECT * FROM EMPRUNTENCOURS WHERE IDUTILISATEUR = " + u.getIdUtilisateur());			
		while( result2.next()){
			
			int idEx = result2.getInt(1);
			int idU = result2.getInt(2);

			EmpruntEnCoursDb ex2 = new EmpruntEnCoursDb(idEx,idU);
			listeEmpruntEnCoursDb.add(ex2);
			
		}
		
		return listeEmpruntEnCoursDb;
	}

	
	@Override
	public void removeEmpruntEnCours(int idExemplaire) throws SQLException {
		
		Statement stmt = jdbc.getConnection("bibliothecaire.properties").createStatement();
		
		ResultSet result3 = stmt.executeQuery("SELECT * FROM EMPRUNTENCOURS WHERE IDEXEMPLAIRE ="+ idExemplaire);
		if(result3.next()) {
		
			Statement stmt2 = jdbc.getConnection("bibliothecaire.properties").createStatement();
			
			ResultSet result4 = stmt2.executeQuery("SELECT IDEMPRUNTARCHIVE+1 FROM EMPRUNTARCHIVE ORDER BY IDEMPRUNTARCHIVE DESC FETCH FIRST 1 ROWS ONLY");
			result4.next();
			
			
			PreparedStatement pstmt2 = jdbc.getConnection("bibliothecaire.properties").prepareStatement("INSERT INTO EMPRUNTARCHIVE VALUES (?, TO_DATE(?, 'DD-MM-YYYY'), TO_DATE(?, 'DD-MM-YYYY'),?,?)");
			pstmt2.setInt(1,(result4.getInt(1)));
			pstmt2.setDate(2,result3.getDate(3));
			pstmt2.setDate(3,Date.valueOf(LocalDate.now()));
			pstmt2.setInt(4,result3.getInt(1));
			pstmt2.setInt(5,result3.getInt(2));
			pstmt2.executeUpdate();
			pstmt2.close();
			result3.close();
			result4.close();
			stmt.close();
			stmt2.close();
			
			PreparedStatement pstmt3 = jdbc.getConnection("bibliothecaire.properties").prepareStatement("DELETE FROM EMPRUNTENCOURS WHERE IDEXEMPLAIRE = ?");
			pstmt3.setInt(1,idExemplaire);
			pstmt3.execute();
			pstmt3.close();
			PreparedStatement pstmt4 = jdbc.getConnection("bibliothecaire.properties").prepareStatement("UPDATE EXEMPLAIRE SET STATUS='DISPONIBLE' WHERE IDEXEMPLAIRE = ?");
			pstmt4.setInt(1,idExemplaire);
			pstmt4.executeUpdate();
			pstmt4.close();	
			}
		jdbc.getConnection("bibliothecaire.properties").close();
	}
	
	
}

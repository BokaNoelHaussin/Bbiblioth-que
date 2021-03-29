package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import control.InterfaceUtilisateursDao;
import domain.Adherent;
import domain.Employe;
import domain.EnumCategorieEmploye;
import domain.PingJdbc;
import domain.Utilisateur;

public class UtilisateursDao implements InterfaceUtilisateursDao {

	PingJdbc jdbc = null;

	public UtilisateursDao(PingJdbc jdbc) {
		this.jdbc = jdbc;
	}

	@Override
	public Utilisateur findByKey(int idUser) {
		PreparedStatement pstm;
		Utilisateur user = null;
		Integer id = 0;
		String pwd = "";
		String nom = "";
		String prenom = "";
		String cat = "";
		String tel = "";
		String code = "";
		String cat_employe = "";
		try {
			//pstm = jdbc.getConnection("biblioFinale.properties")
			pstm = jdbc.getConnection("bibliothecaire.properties")
					.prepareStatement("select utilisateur.idutilisateur, utilisateur.pwd, utilisateur.nom, utilisateur.prenom, categorieutilisateur, telephone, codematricule, categorieemploye "
							+ "from utilisateur, adherent, employe "
							+ "where utilisateur.idutilisateur=adherent.idutilisateur (+) "
							+ "and utilisateur.idutilisateur=employe.idutilisateur (+) "
							+ "and utilisateur.idutilisateur=?");
			pstm.setInt(1, idUser);
			ResultSet result = pstm.executeQuery();
			while (result.next()) {
				id = result.getInt(1);
				pwd = result.getString(2);
				nom = result.getString(3);
				prenom = result.getString(4);
				cat = result.getString(5);
				if (cat.equals("ADHERENT")) {
					tel = result.getString(6);
					user = new Adherent(nom, prenom, id, pwd, tel);
				}
				if (cat.equals("EMPLOYE")) {
					code = result.getString(7);
					cat_employe = result.getString(8);
					EnumCategorieEmploye cat2 = EnumCategorieEmploye.valueOf(cat_employe.toUpperCase());
					user = new Employe(nom, prenom, id, pwd, code, cat2);
				}

			}

			pstm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

	@Override
	public ArrayList<Utilisateur> findAll() {
		ArrayList<Utilisateur> listUtilisateur = new ArrayList<Utilisateur>();
		Utilisateur user = null;
		Integer id = 0;
		String pwd = "";
		String nom = "";
		String prenom = "";
		String cat = "";
		String tel = "";
		String code = "";
		String cat_employe = "";
		try {
			Statement stm = jdbc.getConnection("bibliothecaire.properties").createStatement();
			ResultSet result = stm
					.executeQuery("select utilisateur.idutilisateur, utilisateur.pwd, utilisateur.nom, utilisateur.prenom, categorie_utilisateur, telephone, codematricule, categorieemploye "
							+ "from utilisateur, adherent, employe "
							+ "where utilisateur.idutilisateur=adherent.idutilisateur (+) "
							+ "and utilisateur.idutilisateur=employe.idutilisateur (+)");
			while (result.next()) {
				id = result.getInt(1);
				pwd = result.getString(2);
				nom = result.getString(3);
				prenom = result.getString(4);
				cat = result.getString(5);
				if (cat.equals("ADHERENT")) {
					tel = result.getString(6);
					user = new Adherent(nom, prenom, id, pwd, tel);
				}
				if (cat.equals("EMPLOYE")) {
					code = result.getString(7);
					cat_employe = result.getString(8);
					EnumCategorieEmploye cat2 = EnumCategorieEmploye.valueOf(cat_employe.toLowerCase());
					user = new Employe(nom, prenom, id, pwd, code, cat2);
				}

				listUtilisateur.add(user);
			}
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listUtilisateur;
	}

}

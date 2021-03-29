package control;

import java.sql.SQLException;
import java.util.List;

import dao.EmpruntEnCoursDb;
import domain.BiblioException;
import domain.EmpruntEnCours;
import domain.Utilisateur;

public interface InterfaceEmpruntEnCoursDao {

	boolean insertEmpruntEnCours(EmpruntEnCours emprunt) throws SQLException;

	EmpruntEnCoursDb findByKey(int idExemplaire) throws BiblioException;

	List<EmpruntEnCoursDb> findByUtilisateur(Utilisateur u) throws SQLException, BiblioException;

	void removeEmpruntEnCours(int idExemplaire) throws SQLException;



}
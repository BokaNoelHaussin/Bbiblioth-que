package control;

import java.sql.SQLException;
import java.util.ArrayList;

import domain.Exemplaire;

public interface InterfaceExemplaireDao {

	Exemplaire findByKey(int idExemplaire) throws SQLException;

	ArrayList<Exemplaire> findAll();

}
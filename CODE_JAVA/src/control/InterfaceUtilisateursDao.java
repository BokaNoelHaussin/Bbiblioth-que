package control;

import java.util.ArrayList;

import domain.Utilisateur;

public interface InterfaceUtilisateursDao {

	Utilisateur findByKey(int idUser);

	ArrayList<Utilisateur> findAll();

}
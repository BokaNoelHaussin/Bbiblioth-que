package test;


import java.sql.SQLException;
import java.text.ParseException;

import dao.ExemplairesDao;
import dao.UtilisateursDao;
import domain.BiblioException;
import domain.Exemplaire;
import domain.PingJdbc;
import domain.Utilisateur;

public class TestDeBase {

	public static void main(String[] args) throws ParseException, BiblioException, SQLException {
		PingJdbc pjdbc = new PingJdbc();
		pjdbc.getConnection("bibliothecaire.properties");
		//pjdbc.getConnection("biblioFinale.properties");
		System.out.println("Test de base : emprunt adhérent et employé");
		UtilisateursDao udao = new UtilisateursDao(pjdbc);
		Utilisateur u1 = udao.findByKey(1);
		System.out.println(" Adhérent instancié via classe Dao " + u1);
		
		Utilisateur u2 = udao.findByKey(2);
		System.out.println(" Adhérent instancié via classe Dao " + u2);
		
		ExemplairesDao edao = new ExemplairesDao(pjdbc);
		Exemplaire ex1 = edao.findByKey(1);
		System.out.println(" Exemplaire instancié via classe Dao " + ex1);
		
		Exemplaire ex2 = edao.findByKey(2);
		System.out.println(" Exemplaire instancié via classe Dao " + ex2);
		

	}

}

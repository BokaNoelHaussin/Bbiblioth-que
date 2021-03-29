package control;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import dao.ExemplairesDao;
import dao.UtilisateursDao;
import domain.Exemplaire;
import domain.PingJdbc;
import domain.Utilisateur;

public class ConsulterCtl {

	public static void main(String[] args) throws SQLException {
		
		PingJdbc pjdbc = new PingJdbc();
		pjdbc.getConnection("bibliothecaire.properties");
		
		//pjdbc.getConnection("biblioFinale.properties");
		System.out.println("Test de base : utilisateurs et exemplaires");
		String idUtil1 = JOptionPane.showInputDialog(null, "Entrez l'identifiant de l'adherent (1, 4, 5, 7 ou 8) : ","Identification utilisateur", JOptionPane.INFORMATION_MESSAGE);
		UtilisateursDao udao = new UtilisateursDao(pjdbc);
		Utilisateur u1 = udao.findByKey(Integer.parseInt(idUtil1));
		System.out.println(" Utilisateur instancié via classe Dao " + u1);
		
		String idUtil2 = JOptionPane.showInputDialog(null, "Entrez l'identifiant de l'employé (2, 3 ou 6) : ","Identification utilisateur", JOptionPane.INFORMATION_MESSAGE);
		Utilisateur u2 = udao.findByKey(Integer.parseInt(idUtil2));
		System.out.println(" Utilisateur instancié via classe Dao " + u2);
		
		String idExemp1 = JOptionPane.showInputDialog(null, "Entrez l'identifiant de l'exemplaire emprunté entre 1 et 8 : ","Identification exemplaire", JOptionPane.INFORMATION_MESSAGE);
		ExemplairesDao edao = new ExemplairesDao(pjdbc);
		Exemplaire ex1 = edao.findByKey(Integer.parseInt(idExemp1));
		System.out.println(" Exemplaire instancié via classe Dao " + ex1);
		
		String idExemp2 = JOptionPane.showInputDialog(null, "Entrez l'identifiant de l'exemplaire emprunté entre 1 et 8 : ","Identification exemplaire", JOptionPane.INFORMATION_MESSAGE);
		Exemplaire ex2 = edao.findByKey(Integer.parseInt(idExemp2));
		System.out.println(" Exemplaire instancié via classe Dao " + ex2);
		

	}

}

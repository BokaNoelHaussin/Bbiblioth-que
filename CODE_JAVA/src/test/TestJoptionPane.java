package test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import control.InterfaceEmpruntEnCoursDao;
import control.InterfaceExemplaireDao;
import dao.EmpruntArchiveDao;
import dao.EmpruntEnCoursDao;

import dao.ExemplairesDao;
import dao.UtilisateursDao;
import domain.BiblioException;
import domain.EmpruntArchive;
import domain.EmpruntEnCours;
import domain.Exemplaire;
import domain.PingJdbc;


public class TestJoptionPane {
	
	public static void main(String[] args) throws SQLException, FileNotFoundException, IOException, NumberFormatException, BiblioException {
		
		PingJdbc pjdbc = new PingJdbc();
		pjdbc.getConnection("bibliothecaire.properties");
		Integer choixFinal;
		do {
		String[] choix = {"Consulter un livre", "Consulter un utilisateur", "Emprunter un livre", "Disponibilit� d'un exemplaire","Rendre un livre","Emprunts Archiv�s"};
		int option = JOptionPane.showOptionDialog(null, "Choisissez parmi les options suivantes", "Biblioth�que_JDBC", 0, JOptionPane.QUESTION_MESSAGE, null, choix, "Applicatif Biblioth�que");
		
		
		if(option == 0 ) {
			ExemplairesDao exemplaireJOption = new ExemplairesDao(pjdbc);
			String c = JOptionPane.showInputDialog(null, "Entrez l'identifiant de l'exemplaire (entre 1 et 8) : ","Consultation d'un exemplaire", JOptionPane.INFORMATION_MESSAGE);
			System.out.println(exemplaireJOption.findByKey(Integer.parseInt(c)));
		} else if(option == 1) {
			UtilisateursDao utilisateurJOption = new UtilisateursDao(pjdbc);
			String d = JOptionPane.showInputDialog(null, "Entrez l'identifiant d'un utilisateur ( Employ� = 2, 3 et 6 - Adh�rent = 1, 4, 5, 7 et 8) : ","Consultation d'un utilisateur", JOptionPane.INFORMATION_MESSAGE);
			System.out.println(utilisateurJOption.findByKey(Integer.parseInt(d)));
		} else if (option == 2) {
			UtilisateursDao utilisateurJOption = new UtilisateursDao(pjdbc);
			ExemplairesDao exemplaireJOption = new ExemplairesDao(pjdbc);
			String x = JOptionPane.showInputDialog(null, "Entrez l'identifiant de l'emprunteur ( Employ� = 2, 3 et 6 et  Adh�rent = 1, 4, 5, 7 et 8) : ","R�alisation d'un emprunt", JOptionPane.INFORMATION_MESSAGE);
			String y = JOptionPane.showInputDialog(null, "Entrez l'identifiant de l'exemplaire � emprunter : ","R�alisation d'un emprunt", JOptionPane.INFORMATION_MESSAGE);
			EmpruntEnCoursDao empruntEnCoursDao = new EmpruntEnCoursDao(pjdbc);
			System.out.println(empruntEnCoursDao.insertEmpruntEnCours(new EmpruntEnCours(utilisateurJOption.findByKey(Integer.parseInt(x)), exemplaireJOption.findByKey(Integer.parseInt(y)))));
		} else if (option == 3) {
			ExemplairesDao exemplaireJOption = new ExemplairesDao(pjdbc);
			String e = JOptionPane.showInputDialog(null, "Entrez l'identifiant de l'exemplaire ( de 1 � 8 ): ","Disponinibilit� exemplaire", JOptionPane.INFORMATION_MESSAGE);
			System.out.println(" L'exemplaire demand� est : " + exemplaireJOption.findByKey(Integer.parseInt(e)).getEnumStatusExemplaire());
			//EmpruntArchiveDao empruntArchive = new EmpruntArchiveDao(PingJdbc.getConnectionByProperties());
		} else if (option == 4) {
			String retourEx = JOptionPane.showInputDialog(null, "Entrez l'identifiant � restituer ( de 1 � 8 ): ","Retour d'emprunt", JOptionPane.INFORMATION_MESSAGE);
			InterfaceEmpruntEnCoursDao eecd = new EmpruntEnCoursDao(pjdbc);
			eecd.removeEmpruntEnCours(Integer.parseInt(retourEx));
			System.out.println("\nL'exemplaire rendu  est : " + Integer.parseInt(retourEx) + "\n");
			InterfaceExemplaireDao eecd2 = new ExemplairesDao(pjdbc);
			System.out.println("\nListe des exemplaires disponibles : \n");
			for(Exemplaire ex1 : eecd2.findAll()) {
				if ( ex1.getEnumStatusExemplaire().toString().equalsIgnoreCase("DISPONIBLE")) {
					System.out.println("Exemplaire id : " + ex1 +"\n");
				}
			}
		} else if (option == 5) {
			EmpruntArchiveDao empruntArchive = new EmpruntArchiveDao(pjdbc);
			for(EmpruntArchive emp : empruntArchive.findAll())
			System.out.println(emp);
	
			

		}
		
		  choixFinal = JOptionPane.showConfirmDialog(null, "Voulez-vous continuer ?"); 
		} while (choixFinal==0);
		
	}

}

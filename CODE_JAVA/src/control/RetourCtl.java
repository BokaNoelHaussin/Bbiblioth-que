package control;


import java.sql.SQLException;

import javax.swing.JOptionPane;

import dao.EmpruntArchiveDao;
import dao.EmpruntEnCoursDao;
import dao.ExemplairesDao;
import domain.EmpruntArchive;
import domain.Exemplaire;
import domain.PingJdbc;

public class RetourCtl {

	public static void main(String[] args) throws NumberFormatException, SQLException {

		PingJdbc pjdbc = new PingJdbc();
		pjdbc.getConnection("bibliothecaire.properties");
		
		new ExemplairesDao(pjdbc);
		
		String retourEx = JOptionPane.showInputDialog(null, "Entrez l'identifiant à restituer ( de 1 à 8 ): ","Retour d'emprunt", JOptionPane.INFORMATION_MESSAGE);
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
		
		
		JOptionPane.showMessageDialog(null, "Emprunt rendu", "Fin du retour de l'exemplaire", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static String retourExemplaire (String retourEx) throws NumberFormatException, SQLException {
		
		PingJdbc pjdbc = new PingJdbc();
		pjdbc.getConnection("bibliothecaire.properties");
		
		
		new ExemplairesDao(pjdbc);
		InterfaceEmpruntEnCoursDao eecd3 = new EmpruntEnCoursDao(pjdbc);
		eecd3.removeEmpruntEnCours(Integer.parseInt(retourEx));

		InterfaceExemplaireDao eecd4 = new ExemplairesDao(pjdbc);
		String result = "\nListe des exemplaires disponibles : \n\n";
		for(Exemplaire ex2 : eecd4.findAll()) {
			if ( ex2.getEnumStatusExemplaire().toString().equalsIgnoreCase("DISPONIBLE")) {
				result = result + "Exemplaire id : " + ex2 + "\n";
			}
		}
		
		InterfaceEmpruntArchiveDao eecd5 = new EmpruntArchiveDao(pjdbc);
		String result2 = "\nListe des exemplaires archivés de cet utilisateur : \n\n";
		for(EmpruntArchive eac1 : eecd5.findAll()) {
			result2 = result2 + "Exemplaire archivé : " + eac1 + "\n";
		}
		result = result + result2;
		return result;
	



	}

}

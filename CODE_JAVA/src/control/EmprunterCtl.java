package control;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import dao.EmpruntEnCoursDao;
import dao.EmpruntEnCoursDb;
import dao.ExemplairesDao;
import dao.UtilisateursDao;
import domain.BiblioException;
import domain.EmpruntEnCours;
import domain.PingJdbc;

public class EmprunterCtl {
	
	public static void main(String[] args) throws NumberFormatException, SQLException, BiblioException {
		
		PingJdbc pjdbc = new PingJdbc();
		pjdbc.getConnection("bibliothecaire.properties");
		
		InterfaceUtilisateursDao util1 = new UtilisateursDao(pjdbc);
		InterfaceExemplaireDao exemp1 = new ExemplairesDao(pjdbc);
		String idUtil = JOptionPane.showInputDialog(null, "Entrez l'identifiant de l'utilisateur (si Employe : 2, 3 et 6 ou si adhérent : 1, 4, 5, 7 et 8) : ","Identification utilisateur", JOptionPane.INFORMATION_MESSAGE);
		String idExemp = JOptionPane.showInputDialog(null, "Entrez l'identifiant de l'exemplaire emprunté entre 1 et 8 : ","Identification exemplaire", JOptionPane.INFORMATION_MESSAGE);
		InterfaceEmpruntEnCoursDao eecd = new EmpruntEnCoursDao(pjdbc);
		eecd.insertEmpruntEnCours(new EmpruntEnCours(util1.findByKey(Integer.parseInt(idUtil)),exemp1.findByKey(Integer.parseInt(idExemp))));
		System.out.println("Liste des emprunts en cours dans la base de l'emprunteur dont l'identifiant est : " + idUtil);
	
		String find = "SELECT * FROM EMPRUNTENCOURS WHERE IDUTILISATEUR = " + idUtil;
            
				PreparedStatement pstmt = pjdbc.getConnection("bibliothecaire.properties").prepareStatement(find);
				try (ResultSet result = pstmt.executeQuery(find)){
               	 
                    while(result.next()) {
                        System.out.println("IDEXEMPLAIRE : " + result.getInt("IDEXEMPLAIRE") + " , IDUTILISATEUR : " + result.getInt("IDUTILISATEUR") + " , DATEEMPRUNT : " + result.getString("DATEEMPRUNT"));
                    }
		
				} catch (SQLException e) {
					System.out.println("Erreur sur fermeture result SQLException: " + e.getMessage());
				}
				
				
				System.out.println("\nListe des emprunts en cours récupérée via mapping relationnel objet :");
				for(EmpruntEnCoursDb eecdb : eecd.findByUtilisateur(util1.findByKey(Integer.parseInt(idUtil)))) {
					System.out.println("l'exemplaire dont l'identifiant : "+ eecdb.getIdExemp() + ", est emprunté par utilisateur dont l'identifiant est : "+ eecdb.getIdUtil()+"\n");
				}
				
		
		
		InterfaceExemplaireDao eecd3 = new ExemplairesDao(pjdbc);

		System.out.println("Etat de l'exemplaire faisant l'objet d'une demande de prêt : " + eecd3.findByKey(Integer.parseInt(idExemp)).toString());
					
		
		
		
		
		JOptionPane.showMessageDialog(null, "Fin de la saisie de l'emprunt","Fin emprunt", JOptionPane.INFORMATION_MESSAGE);
		

	}

}

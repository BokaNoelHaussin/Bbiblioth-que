package domain;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import control.InterfaceUtilisateursDao;
import dao.UtilisateursDao;

public class EmpruntArchive {
	/** Attribut qui tient la date d'emprunt */
	private Date dateEmprunt;
	/** Attribut qui tient la date de retour */
	private Date dateRestitutionEff;
	/** format de la date d'emprunt */
	// Penser à utiliser des LocalDate pour fiabliliser le rendu des date quelque
	// soit le type de classe !!!!
	public static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	/** Attribut qui récupère l'Utlisateur*/
	private Utilisateur u;
	/** Attribut qui récupère l'exemplaire*/
	private Exemplaire ex;
	/** Attribut qui récupère la connection*/
	PingJdbc jdbc = null;

	public EmpruntArchive(Date dateEmprunt, Date dateRestitutionEff) {
		this.dateEmprunt = dateEmprunt;
		this.dateRestitutionEff = dateRestitutionEff;
	}

	
	public EmpruntArchive(Integer idArchive, Date dateEmprunt, Date dateRestitutionEff, Integer u, Integer ex) {
		PingJdbc pjdbc = new PingJdbc();
		pjdbc.getConnection("bibliothecaire.properties");
		this.dateEmprunt = dateEmprunt;
		this.dateRestitutionEff = dateRestitutionEff;
		InterfaceUtilisateursDao udao = new UtilisateursDao(pjdbc);
		setU(udao.findByKey(u));
		InterfaceUtilisateursDao edao = new UtilisateursDao(pjdbc);
		setU(edao.findByKey(ex));
	}


	public Utilisateur getU() {
		return u;
	}

	public void setU(Utilisateur u) {
		this.u = u;
	}

	public Exemplaire getEx() {
		return ex;
	}

	public void setEx(Exemplaire ex) {
		this.ex = ex;
	}

	public Date getDateEmprunt() {
		return dateEmprunt;
	}

	public void setDateEmprunt(Date dateEmprunt) {
		this.dateEmprunt = dateEmprunt;
	}

	public Date getDateRestitutionEff() {
		return dateRestitutionEff;
	}

	public void setDateRestitutionEff(Date dateRestitutionEff) {
		this.dateRestitutionEff = dateRestitutionEff;
	}

	@Override
	public String toString() {
		return "EmpruntArchive [dateEmprunt = " + dateEmprunt + ", dateRestitutionEff = " + dateRestitutionEff + ", utilisateur = " + u + ", Exemplaire = " + ex + " ]";
	}

	public static void main(String[] args) throws ParseException {

	}

}

package domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EmpruntEnCours {
	/** Attribut de la date d'emprunt d'un exemplaire */
	private Date dateEmprunt;
	/** format de la date d'emprunt */
	@SuppressWarnings("unused")
	// Penser à utiliser des LocalDate pour fiabliliser le rendu des date quelque
	// soit le type de classe !!!!
	public static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	/** Attribut qui retourne l'utilisateur qui emprunte */
	Utilisateur u;
	/** Attribut qui retourne l'exemplaire emprunté */
	Exemplaire ex;
	/** Attribut de type énuméré pour la disponibilite du livre */
	private EnumStatusExemplaire EnumStatusExemplaire;

	public EmpruntEnCours(Date dateEmprunt, Utilisateur u, Exemplaire ex) {
		/*if (ex.getEnumStatusExemplaire() == domain.EnumStatusExemplaire.PRETE
				|| ex.getEnumStatusExemplaire() == domain.EnumStatusExemplaire.SUPPRIME) {
			System.out.println("Erreur : Livre non disponible");
		} else {*/
			ex.setEnumStatusExemplaire(domain.EnumStatusExemplaire.PRETE);
			this.ex = ex;
			this.u = u;
			this.dateEmprunt = dateEmprunt;
			try {
				u.addEmpruntEnCours(this);
			} catch (BiblioException e) {
				System.out.println(e);
			//}
		}
	}

	public EmpruntEnCours(Date dateEmprunt, Exemplaire ex) throws BiblioException {
		/*if (ex.getEnumStatusExemplaire() == domain.EnumStatusExemplaire.PRETE
				|| ex.getEnumStatusExemplaire() == domain.EnumStatusExemplaire.SUPPRIME) {
			System.out.println("Erreur : Livre non disponible");
		} else {*/
			ex.setEnumStatusExemplaire(domain.EnumStatusExemplaire.PRETE);
			this.ex = ex;
			this.dateEmprunt = dateEmprunt;

		//}
	}
	
	public EmpruntEnCours(Utilisateur u, Exemplaire ex) throws BiblioException {
		/*if (ex.getEnumStatusExemplaire() == domain.EnumStatusExemplaire.PRETE
				|| ex.getEnumStatusExemplaire() == domain.EnumStatusExemplaire.SUPPRIME) {
			System.out.println("Erreur : Livre non disponible");
		} else {*/
			ex.setEnumStatusExemplaire(domain.EnumStatusExemplaire.PRETE);
			 setU(u);
			 setEx(ex);
			
			//this.ex = ex;
			//this.u = u;
			setDateEmprunt(new Date());
		//}
	}

	public EmpruntEnCours() {
		
	}

	public EmpruntEnCours(java.sql.Date dateEmprunt, Integer idUtil, Integer idEx) {
		this.dateEmprunt = dateEmprunt;
	}

	public EnumStatusExemplaire getEnumStatusExemplaire() {
		return EnumStatusExemplaire;
	}

	public void setEnumStatusExemplaire(EnumStatusExemplaire enumStatusExemplaire) {
		EnumStatusExemplaire = enumStatusExemplaire;
	}

	public Utilisateur getU() {
		return u;
	}

	public void setU(Utilisateur u) throws BiblioException {
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
		this.dateEmprunt = new Date();
	}

	@Override
	public String toString() {
		return "EmpruntEnCours [ dateEmprunt = " + dateEmprunt + /* ", utilisateur = " + u + */ ", exemplaire = " + ex
				+ " ]\n";
	}

	public static void main(String[] args) throws ParseException {

	}

}

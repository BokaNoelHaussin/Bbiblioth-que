package domain;

import java.util.Date;

public class Adherent extends Utilisateur {
	/** Attribut du numéro de téléphone de l'adhérent */
	private String telephone;
	/** Attribut de la durée max d'emprunt d'un livre */
	private Integer dureeMaxPret = 15;
	/** Attribut du nombre de prêt maximal autorisé */
	private static Integer nbMaxPrets = 3;
	/** Attribut du nombre de retard de retour */
	private Integer nbRetards = 0;
	/** Attribut dérivé du nombre de livre empruntés et s'il y a un retard */
	private boolean isConditionsPretAcceptees = true;
	/** Variable du nombre d'emprunt */
	private Integer nbEmpruntsEnCours = 0;

	public Adherent(String nom, String prenom, Date dateNaissance, String sexe, Integer idUtilisateur, String pwd,
			String pseudonyme, String telephone) {
		super(nom, prenom, dateNaissance, sexe, idUtilisateur, pwd, pseudonyme);
		this.telephone = telephone;
	}

	public Adherent(String nom, String prenom, Integer idUtilisateur, String pwd,  String telephone) {
		super(nom, prenom, idUtilisateur, pwd);
		this.telephone = telephone;
	}
	
	public Adherent(String nom, String prenom, Integer idUtilisateur, String pwd, String pseudonyme, String telephone) {
		super(nom, prenom, idUtilisateur, pwd, pseudonyme);
		this.telephone = telephone;
	}

	public Adherent(String nom, String prenom, String telephone) {
		super(nom, prenom);
		this.telephone = telephone;
	}

	public Adherent(String nom, String prenom) {
		super(nom, prenom);
	}

	public void setNbRetards(Integer nbRetards) {
		this.nbRetards = nbRetards;
	}

	public Integer getNbEmpruntsEnCours() {
		return nbEmpruntsEnCours;
	}

	public void setNbEmpruntsEnCours(Integer nbEmpruntsEnCours) {
		this.nbEmpruntsEnCours = nbEmpruntsEnCours;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Integer getDureeMaxPret() {
		return dureeMaxPret;
	}

	public void setDureeMaxPret(Integer dureeMaxPret) {
		this.dureeMaxPret = dureeMaxPret;
	}

	public static Integer getNbMaxPrets() {
		return nbMaxPrets;
	}

	public static void setNbMaxPrets(Integer nbMaxPrets) {
		Adherent.nbMaxPrets = nbMaxPrets;
	}

	public boolean isPretEnRetard(EmpruntEnCours ep) {
		Date dateEmprunt = ep.getDateEmprunt();
		int days = (int) (((new Date()).getTime() - dateEmprunt.getTime()) / (1000 * 60 * 60 * 24));
		if (days > dureeMaxPret) {
			nbRetards++;
			return true;
		} else {
			return false;
		}
	}

	public int getNbRetards() {
		for (EmpruntEnCours eep : emprunts) {
			eep.getDateEmprunt();
			this.isPretEnRetard(eep);
		}
		return nbRetards;
	}

	public boolean isConditionsPretAcceptees() {
		for (EmpruntEnCours eep : emprunts) {
			if (this.isPretEnRetard(eep) || emprunts.size() >= 3) {
				isConditionsPretAcceptees = false;
			} else {
				isConditionsPretAcceptees = true;
			}
		}
		return isConditionsPretAcceptees;
	}

	public void addEmpruntEnCours(EmpruntEnCours ep) throws BiblioException {
		if (isConditionsPretAcceptees == false) {
			throw new BiblioException();
		} else if (ep.getEnumStatusExemplaire() == EnumStatusExemplaire.PRETE
				|| ep.getEnumStatusExemplaire() == EnumStatusExemplaire.SUPPRIME) {
			throw new BiblioException();
		} else {
			emprunts.add(ep);
			ep.setEnumStatusExemplaire(EnumStatusExemplaire.PRETE);
			nbEmpruntsEnCours++;
		}
	}

	@Override
	public String toString() {
		if (getIdUtilisateur() == null) {
			return "Adherent [nom = " + getNom() + ", prenom = " + getPrenom() + ", telephone = " + telephone + "]";
		} else {
			return "Adherent [" + super.toString() + ", telephone = " + telephone + "]";
		}
	}

	public static void main(String[] args) {
		Adherent a1 = new Adherent("Nom", "Prénom");
		Adherent a2 = new Adherent("Nom2", "Prénom2", "00.00.00.00.00");
		System.out.println(a1 + "\n" + a2);
		Adherent a3 = new Adherent("Nom2", "Prénom2", 100, "mdp", "pseudo", "00.00.00.00.01");
		System.out.println(a3);

	}

}

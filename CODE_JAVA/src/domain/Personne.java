package domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Personne implements NommablePersonne {
	/** Attribut nom de la personne */
	private String nom;
	/** Attribut de prénom de la personne */
	private String prenom;
	/** format de la date d'emprunt */
	// Penser à utiliser des LocalDate pour fiabliliser le rendu des date quelque
	// soit le type de classe !!!!
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	/** Attribut de la date de naissance */
	private Date dateNaissance;
	/** Attribut du genre de la personne */
	private String sexe;

	public Personne(String nom, String prenom, Date dateNaissance, String sexe) {
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.sexe = sexe;
	}

	public Personne(String nom, String prenom) {
		this.nom = nom;
		this.prenom = prenom;

	}

	public Personne() {

	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	@Override
	public String toString() {
		return "Personne [ nom = " + nom + ", prenom = " + prenom + ", dateNaissance = " + dateNaissance + ", sexe = "
				+ sexe + " ]";
	}

	public static void main(String[] args) throws BiblioException, ParseException {
		Personne p1 = new Personne();
		Personne p2 = new Personne("Nom", "Prénom", Personne.sdf.parse("01/01/1900"), "Homme");
		System.out.println(p1 + "\n" + p2);

	}

}

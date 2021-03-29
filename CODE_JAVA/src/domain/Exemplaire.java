package domain;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Exemplaire {
	/** Attribut identifiant de l'exemplaire */
	private Integer idExemplaire;
	/** Attribut de la date d'achat */
	private Date dateAchat;
	/** Attribut de type énuméré pour la disponibilite du livre */
	private EnumStatusExemplaire EnumStatusExemplaire;
	/** Formattage de la date */
	// Penser à utiliser des LocalDate pour fiabliliser le rendu des date quelque
	// soit le type de classe !!!!
	public static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	/** Attribut du code ISBN */
	private String isbn;

	/** propriété qui correspond à la date d'emprunt du livre */
	// private Date dateEmprunt;

	public Exemplaire(Integer idExemplaire, Date dateAchat, EnumStatusExemplaire enumStatusExemplaire, String isbn) {
		this.idExemplaire = idExemplaire;
		this.dateAchat = dateAchat;
		EnumStatusExemplaire = enumStatusExemplaire;
		this.isbn = isbn;
	}

	public Exemplaire(Integer idExemplaire, EnumStatusExemplaire enumStatusExemplaire, String isbn) {
		this.idExemplaire = idExemplaire;
		EnumStatusExemplaire = enumStatusExemplaire;
		this.isbn = isbn;
	}

	public Exemplaire() {

	}


	public Integer getIdExemplaire() {
		return idExemplaire;
	}

	public void setIdExemplaire(Integer idExemplaire) {
		this.idExemplaire = idExemplaire;
	}

	public Date getDateAchat() {
		return dateAchat;
	}

	public void setDateAchat(Date dateAchat) {
		this.dateAchat = dateAchat;
	}

	public EnumStatusExemplaire getEnumStatusExemplaire() {
		return EnumStatusExemplaire;
	}

	public void setEnumStatusExemplaire(EnumStatusExemplaire enumStatusExemplaire) {
		this.EnumStatusExemplaire = enumStatusExemplaire;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	@Override
	public String toString() {
		return "Exemplaire [ idExemplaire = " + idExemplaire + ", dateAchat = " + dateAchat
				+ ", EnumStatusExemplaire = " + EnumStatusExemplaire + ", isbn = " + isbn + " ]";
	}

	public static void main(String[] args) {

	}

}

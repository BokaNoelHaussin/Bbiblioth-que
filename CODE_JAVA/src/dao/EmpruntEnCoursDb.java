package dao;


import control.InterfaceUtilisateursDao;
import domain.BiblioException;
import domain.EmpruntEnCours;
import domain.PingJdbc;



public class EmpruntEnCoursDb extends EmpruntEnCours{
	private Integer idExemp;
	private Integer idUtil;
	
	

	
	public EmpruntEnCoursDb(Integer idEx, Integer idU) throws BiblioException {
		super();
		PingJdbc pjdbc = new PingJdbc();
		pjdbc.getConnection("bibliothecaire.properties");
		InterfaceUtilisateursDao udao = new UtilisateursDao(pjdbc);
		setU(udao.findByKey(idU));
		InterfaceUtilisateursDao edao = new UtilisateursDao(pjdbc);
		setU(edao.findByKey(idEx));
		//setIdExemp(idEx);
		//setIdUtil(idU);		
	}

	public Integer getIdExemp() {
		return idExemp;
	}

	public void setIdExemp(Integer idExemp) {
		this.idExemp = idExemp;
	}

	public Integer getIdUtil() {
		return idUtil;
	}

	public void setIdUtil(Integer idUtil) {
		this.idUtil = idUtil;
	}
	

}

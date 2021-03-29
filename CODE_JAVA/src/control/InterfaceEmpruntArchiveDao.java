package control;

import java.util.ArrayList;

import domain.EmpruntArchive;

public interface InterfaceEmpruntArchiveDao {

	ArrayList<EmpruntArchive> findByKey(int idU);

	ArrayList<EmpruntArchive> findAll();

}
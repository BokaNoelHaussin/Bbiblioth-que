/***************************************************************
Attribution des privil�ges sur les objets.Cr�ation des synonymes
MAJ: XH
***************************************************************/
--Granter le privil�ge syst�me "create public synonym" � biblio
--Le propri�taire du sch�ma (biblio) donne des droits aux utilisateurs de la BD
--Cr�ez un user bibliothecaire


--Les tables
grant select on Utilisateur to bibliothecaire;
grant select on Employe to bibliothecaire;
grant select on Adherent to bibliothecaire;
grant select, update on Exemplaire to bibliothecaire;
grant select, insert, delete on EmpruntEncours to bibliothecaire;
grant insert on EmpruntArchive  to bibliothecaire;
grant select on AdherentGeneral to bibliothecaire;

--Les s�quences
grant select on seq_archive to bibliothecaire;

--Le propri�taire cre�e des bibliothecaire SYNONYM sur les tables
CREATE OR REPLACE PUBLIC SYNONYM Utilisateur FOR biblio.Utilisateur;
CREATE OR REPLACE PUBLIC SYNONYM Employe FOR biblio.Employe;
CREATE OR REPLACE PUBLIC SYNONYM Adherent FOR biblio.Adherent;
CREATE OR REPLACE PUBLIC SYNONYM Exemplaire FOR biblio.Exemplaire;
CREATE OR REPLACE PUBLIC SYNONYM EmpruntEncours FOR biblio.EmpruntEncours;
CREATE OR REPLACE PUBLIC SYNONYM EmpruntArchive FOR biblio.EmpruntArchive;
CREATE OR REPLACE PUBLIC SYNONYM AdherentGeneral FOR biblio.AdherentGeneral;

--Le propri�taire cre�e des PUBLIC SYNONYM sur les sequences
CREATE OR REPLACE PUBLIC SYNONYM seq_archive FOR biblio.seq_archive;



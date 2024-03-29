

-----------------------------------------------------------------REIMBURSEMENT-------------------------------------------------------------------------------------------------

CREATE TABLE ers_reimbursement (

	reimb_id serial PRIMARY KEY,
	reimb_amount int,
	reimb_submitted timestamp,
	reimb_resolved timestamp,
	reimb_receipt TEXT,
	reimb_author_fk int REFERENCES ers_users (ers_user_id),
	reimb_resolver_fk int REFERENCES ers_users (ers_user_id),
	reimb_status_id_fk int REFERENCES ers_reimbursement_status (reimb_status_id),
	reimb_type TEXT

)



-------------------------------------=============================== REIMB STATUS ===================================------------------------------------------------------------


CREATE TABLE ers_reimbursement_status (

	reimb_status_id serial PRIMARY KEY,
	reimb_status TEXT

)

INSERT  INTO ers_reimbursement_status (reimb_status)
VALUES ('Approved'), ('Pending'), ('Denied')

-------------------------------------================================ USER ROLE ========================================-----------------------------------------------------


CREATE TABLE ers_user_roles	 (

	ers_user_role_id serial PRIMARY KEY,
	user_role TEXT

)

INSERT INTO ers_user_roles (user_role)
VALUES ('Finance Manager'), ('Compliance Specialist')
------------------------------------------------------------------------USERS--------------------------------------------------------------------------------------------------


CREATE TABLE ers_users (

	ers_user_id serial PRIMARY KEY,
	ers_username TEXT,
	ers_password TEXT,
	user_first_name TEXT,
	user_last_name TEXT,
	user_email TEXT,
	user_role_id_fk int REFERENCES ers_user_roles (ers_user_role_id),
	UNIQUE(ers_username, user_email)

)

INSERT INTO ers_users (ers_username, ers_password, user_first_name, user_last_name, user_email, user_role_id_fk)
VALUES ('jackenglo1', 'passwordheaven', 'Jack', 'Englo', 'jackenglo1@oscarcorp.com', 2), 
		('gordorasmuth', 'grownup7', 'Gordo', 'Rasmuth', 'gordoras@oscarcorp.com', 1),
		('peterhangslin', 'methodb3ast', 'Peter', 'Hangslin', 'peterhangslin@oscarcorp.com', 2),
		('medinacraig', 'comeflowerspl33z', 'Medina', 'Craig', 'medinacraig@oscarcorp.com', 2)

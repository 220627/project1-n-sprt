

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
	reimb_type_id_fk int REFERENCES ers_reimbursement_type (reimb_type_id)

)

DROP TABLE ers_reimbursement;



-------------------------------------=============================== REIMB STATUS ===================================------------------------------------------------------------


CREATE TABLE ers_reimbursement_status (

	reimb_status_id serial PRIMARY KEY,
	reimb_status TEXT

)


---------------------------------------------------------------------REIMB TYPE--------------------------------------------------------------------------------------------------


CREATE TABLE ers_reimbursement_type (

	reimb_type_id serial PRIMARY KEY,
	reimb_type TEXT

)


-------------------------------------================================ USER ROLE ========================================-----------------------------------------------------


CREATE TABLE ers_user_roles	 (

	ers_user_role_id serial PRIMARY KEY,
	user_role TEXT

)


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
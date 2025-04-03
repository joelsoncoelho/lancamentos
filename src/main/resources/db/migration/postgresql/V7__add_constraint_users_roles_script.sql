ALTER TABLE users_roles add CONSTRAINT FK_role_id FOREIGN KEY (role_id) REFERENCES roles (id);

ALTER TABLE users_roles add CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES users (id);

INSERT INTO public.users_roles (user_id, role_id) VALUES(1, 1);
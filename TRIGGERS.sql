CREATE OR REPLACE FUNCTION bloquear_usuario() RETURNS TRIGGER AS $bloquear$
BEGIN
	IF OLD.tentativas >= 3 THEN 
		IF NEW.bloqueado = false THEN
			NEW.tentativas = 0;
			NEW.editado = now();
			RETURN NEW;
		END IF;
	RETURN NEW;
	ELSIF NEW.tentativas >= 3 THEN
		NEW.bloqueado = true;
		NEW.editado = now();
  	END IF;
  	RETURN NEW;
  	END;
	$bloquear$ LANGUAGE plpgsql;

CREATE TRIGGER bloqueia_user 
	BEFORE UPDATE ON usuarios 
		FOR EACH ROW EXECUTE PROCEDURE bloquear_usuario();

CREATE OR REPLACE FUNCTION get_level(points INT)
    RETURNS INT AS $$
DECLARE current_level INT;
BEGIN
    SELECT level INTO current_level FROM level_threshold
    WHERE threshold_roof = (SELECT MIN(threshold_roof)
                       FROM level_threshold
                       WHERE threshold_roof > $1);
    RETURN current_level;
END;
$$  LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION level_update()
    RETURNS trigger AS
$BODY$
DECLARE new_level INT;
BEGIN
    SELECT  get_level(NEW.points) INTO new_level;
    IF new_level <> OLD.level  THEN
        UPDATE user_gamification_detail set level = new_level
        WHERE user_id = NEW.user_id;
    END IF;
    RETURN NEW;
END;
$BODY$
LANGUAGE plpgsql;

CREATE TRIGGER tr_level_update
    AFTER UPDATE OF points ON user_gamification_detail
    FOR EACH ROW
EXECUTE PROCEDURE level_update();

CREATE OR REPLACE FUNCTION user_init()
    RETURNS trigger AS
$BODY$
BEGIN
    INSERT INTO user_gamification_detail (user_id)
    values (NEW.id);
    RETURN NEW;
END;
$BODY$
LANGUAGE plpgsql;

CREATE TRIGGER tr_user_init
    AFTER INSERT ON users
    FOR EACH ROW
EXECUTE PROCEDURE user_init();
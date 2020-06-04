package pl.agh.edu.master_diet.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

@Slf4j
@Repository
public class DbInfoRepository
        extends MasterDietPersistence implements InfoRepository {

    @Override
    public ZonedDateTime getSysdate() {
        log.info("Próba pobrania daty systemowej bazy danych");

        final String sysDateQuery = "SELECT CURRENT_TIMESTAMP";

        final Date date = (Date) getSession(entityManager)
                .createSQLQuery(sysDateQuery)
                .uniqueResult();

        final Instant instant = Instant.ofEpochMilli(date.getTime());
        return ZonedDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

}

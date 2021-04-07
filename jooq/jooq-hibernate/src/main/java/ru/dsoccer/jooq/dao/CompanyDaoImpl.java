package ru.dsoccer.jooq.dao;

import static ru.dsoccer.jooq.entities.Tables.PERSON;
import static ru.dsoccer.jooq.entities.tables.Company.COMPANY;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectJoinStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class CompanyDaoImpl implements CompanyDao {

  @PersistenceContext
  private EntityManager em;

  @Autowired
  private DSLContext ctx;

  private ru.dsoccer.jooq.entities.tables.Company c = COMPANY.as("c");
  private ru.dsoccer.jooq.entities.tables.Person p = PERSON.as("p");

  @Override
  public List<ru.dsoccer.jooq.domain.Company> getCompaniesWithPersonsUsingJOOQ() {
    List<ru.dsoccer.jooq.domain.Company> list = ctx.select()
        .from(c)
        .leftJoin(p).on(c.ID.eq(p.COMPANY_ID))
        .fetch().into(ru.dsoccer.jooq.domain.Company.class);
    list.forEach(company -> System.out.println(company + " " + company.getPersons()));
    return list;
  }

  @Override
  public List<ru.dsoccer.jooq.domain.Company> getCompaniesWithPersonsUsingJOOQ2() {
    SelectJoinStep<Record> jooqQuery = ctx.select()
        .from(c)
        .leftJoin(p).on(c.ID.eq(p.COMPANY_ID));

    Query query = em.createNativeQuery(jooqQuery.getSQL(), ru.dsoccer.jooq.domain.Company.class);
    setBindParameterValues(query, jooqQuery);
    List<ru.dsoccer.jooq.domain.Company> list = query.getResultList();
    list.forEach(System.out::println);
    return list;
  }

  private void setBindParameterValues(Query hibernateQuery, org.jooq.Query jooqQuery) {
    List<Object> values = jooqQuery.getBindValues();
    for (int i = 0; i < values.size(); i++) {
      hibernateQuery.setParameter(i + 1, values.get(i));
    }
  }

}

package com.khsa.javaee.init;

import com.khsa.javaee.dao.AddressDao;
import com.khsa.javaee.dao.ContactDao;
import com.khsa.javaee.model.Address;
import com.khsa.javaee.model.Contact;
import com.khsa.javaee.utils.MockUtils;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import liquibase.resource.ResourceAccessor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.*;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Startup
@Singleton
@TransactionManagement(TransactionManagementType.BEAN)
@Slf4j
public class InitializerBean {

    private static final String STAGE = "development";
    private static final String CHANGELOG_FILE = "liquibase/db.changelog.xml";

    @Resource(lookup = "java:jboss/datasources/MySqlDS")
    private DataSource ds;

    @EJB
    private ContactDao contactDao;

    @EJB
    public AddressDao addressDao;

    @PostConstruct
    protected void bootstrap() {
        log.info("Preparing datasource: {} ", ds);
        ResourceAccessor resourceAccessor = new ClassLoaderResourceAccessor(getClass().getClassLoader());
        try (Connection connection = ds.getConnection()) {
            JdbcConnection jdbcConnection = new JdbcConnection(connection);
            Database db = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(jdbcConnection);

            Liquibase liquiBase = new Liquibase(CHANGELOG_FILE, resourceAccessor, db);
            liquiBase.update(STAGE);
        } catch (SQLException | LiquibaseException e) {
            log.error(StringUtils.EMPTY, e);
        }

        createMockData();
    }

    private static final int MOCK_CONTACTS_COUNT = 100;
    private static final int MOCK_CONTACTS_ADDRESSES_COUNT = 3;
    private void createMockData() {
        if (contactDao.findAll().size() == 0) {
            log.info("Generating mock data");
            List<Address> addressList = new ArrayList<>();
            for (int i = 0; i < MOCK_CONTACTS_COUNT; i++) {
                Address currentAddress = MockUtils.generateRandomAddress();
                addressList.add(currentAddress);
                addressDao.create(currentAddress);
            }

            for (int i = 0; i < MOCK_CONTACTS_COUNT; i++) {
                Contact randomContact = MockUtils.generateRandomContact(
                        MockUtils.filterRandomAddressList(addressList, MOCK_CONTACTS_ADDRESSES_COUNT));
                contactDao.create(randomContact);
            }

        }
    }
}

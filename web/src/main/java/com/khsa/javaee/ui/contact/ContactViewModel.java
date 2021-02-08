package com.khsa.javaee.ui.contact;

import com.khsa.javaee.dao.ContactDao;
import com.khsa.javaee.model.Contact;
import lombok.extern.slf4j.Slf4j;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.List;

import static java.util.Objects.isNull;

@Slf4j
public class ContactViewModel {

    @EJB
    private ContactDao contactDao;

    private static final String footerMessage = "A Total of %d Contact Items";
    private List<Contact> currentContactList;

    public ContactViewModel() {
        if (isNull(contactDao)) {
            try {
                InitialContext context = new InitialContext();
                contactDao = (ContactDao) context.lookup("java:global/sampleWildfly/com.khsa.javaee-ejb-1.0-SNAPSHOT/ContactDaoBean");
            } catch (NamingException e) {
                log.error("Context of DAO not found :", e);
            }
        }
    }

    public ListModel<Contact> getModel() {
        currentContactList = contactDao.findAll();
        return new ListModelList<>(currentContactList);
    }

    public String getFooter() {
        return String.format(footerMessage, currentContactList.size());
    }
}

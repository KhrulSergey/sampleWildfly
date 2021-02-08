package com.khsa.javaee.ui.address;

import com.khsa.javaee.dao.AddressDao;
import com.khsa.javaee.model.Address;
import lombok.extern.slf4j.Slf4j;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServlet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static org.apache.commons.lang.StringUtils.isBlank;

@Slf4j
public class AddressFilterViewModel extends HttpServlet {

    @EJB
    private AddressDao addressDao;

    private static final String footerMessage = "A Total of %d Address Items";
    private final AddressFilter filter = new AddressFilter();
    private List<Address> currentAddressList;

    public AddressFilterViewModel() {
        if (isNull(addressDao)) {
            try {
                InitialContext context = new InitialContext();
                this.addressDao = (AddressDao) context.lookup("java:global/sampleWildfly/com.khsa.javaee-ejb-1.0-SNAPSHOT/AddressDaoBean");
            } catch (NamingException e) {
                log.error("Context of DAO not found :", e);
            }
        }
    }

    public AddressFilter getFilter() {
        return filter;
    }

    public ListModel<Address> getModel() {
        return new ListModelList<>(currentAddressList);
    }

    public String getFooter() {
        return String.format(footerMessage, currentAddressList.size());
    }

    @Command
    @NotifyChange({"model", "footer"})
    public void changeFilter() {
        currentAddressList = addressDao.findAll();
        if (!isBlank(filter.getCity())) {
            currentAddressList = currentAddressList.stream()
                    .filter(a -> a.getCity().equals(filter.getCity())).collect(Collectors.toList());
        }
        if (!isBlank(filter.getContactId())) {
            currentAddressList = currentAddressList.stream()
                    .filter(a -> a.getContacts().stream().anyMatch(c -> Objects.equals(c.getId(), filter.getContactId())))
                    .collect(Collectors.toList());
        }
    }
}

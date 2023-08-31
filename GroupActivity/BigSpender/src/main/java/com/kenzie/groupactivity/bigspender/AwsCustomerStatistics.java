package com.kenzie.groupactivity.bigspender;

import com.kenzie.groupactivity.bigspender.compare.CustomerTotalSpendBySpend;
import com.kenzie.groupactivity.bigspender.types.ServiceSpendByService;
import com.kenzie.groupactivity.bigspender.dao.AwsServiceInvoiceDao;
import com.kenzie.groupactivity.bigspender.types.Customer;
import com.kenzie.groupactivity.bigspender.types.CustomerServiceSpend;
import com.kenzie.groupactivity.bigspender.types.CustomerTotalSpend;
import com.kenzie.groupactivity.bigspender.types.ServiceSpend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A class that uses the AWS customer spending data provided by an
 * AwsServiceInvoiceDao to calculate information about the
 * customers.
 */
public class AwsCustomerStatistics {
    private AwsServiceInvoiceDao awsServiceInvoiceDao;

    /**
     * Creates a statistics instance with the provided DAO.
     * @param awsServiceInvoiceDao The AwsServiceInvoiceDao to use
     */
    public AwsCustomerStatistics(AwsServiceInvoiceDao awsServiceInvoiceDao) {
        this.awsServiceInvoiceDao = awsServiceInvoiceDao;
    }

    /**
     * Produces a list of the single service each AWS customer spent the most on,
     * sorted by customer name (ascending).
     * @return A list of CustomerServiceSpend representing the highest service
     *         spend for each customer, sorted by customer name
     */
    public List<CustomerServiceSpend> getTopServiceSpendForEachCustomer() {
        // PARTICIPANTS: Which list needs to be sorted and returned?
        List<CustomerServiceSpend> spends = awsServiceInvoiceDao.getHighestServiceSpendsForEachCustomer();
        Collections.sort(spends);

        return spends;
    }

    /**
     * Produces a list of *all* service spends of each AWS customer,
     * sorted by total spend (descending), then by the service spend (individual
     * service spend (descending), then service name).
     *
     * @return A list of CustomerServiceSpend, sorted by AWS customer
     * total spend and individual service spend.
     */
    public List<CustomerServiceSpend> getTopItemizedSpends() {
        // PARTICIPANTS: Implement according to Javadoc and README
        List<CustomerServiceSpend> reportResults = new ArrayList<>();
        List<CustomerTotalSpend> totalSpends = awsServiceInvoiceDao.getAllServiceSpends();
        Collections.sort(totalSpends,new CustomerTotalSpendBySpend().reversed());
        for (CustomerTotalSpend totalSpend : totalSpends){
            Customer currentCustomer = totalSpend.getCustomer();
            List<ServiceSpend> currentServiceSpends = totalSpend.getServiceSpends();
            Collections.sort(currentServiceSpends, new ServiceSpendByService().reversed());
            for (ServiceSpend spend: currentServiceSpends){
                CustomerServiceSpend customerServiceSpend = new CustomerServiceSpend(currentCustomer,spend);
                reportResults.add(customerServiceSpend);
            }
        }
        return reportResults;

    }
}
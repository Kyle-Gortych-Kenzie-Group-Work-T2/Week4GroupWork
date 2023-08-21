package com.kenzie.groupactivity.bigspender;

import com.kenzie.groupactivity.bigspender.dao.AwsServiceInvoiceDao;
import com.kenzie.groupactivity.bigspender.types.Customer;
import com.kenzie.groupactivity.bigspender.types.CustomerServiceSpend;
import com.kenzie.groupactivity.bigspender.types.CustomerTotalSpend;
import com.kenzie.groupactivity.bigspender.types.ServiceSpend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
        List<CustomerServiceSpend> topCustomerServiceSpends =
                awsServiceInvoiceDao.getHighestServiceSpendsForEachCustomer();

        Collections.sort(topCustomerServiceSpends, new Comparator<CustomerServiceSpend>() {
            @Override
            public int compare(CustomerServiceSpend o1, CustomerServiceSpend o2) {
                int nameComparison = o1.getCustomer().getName().compareTo(o2.getCustomer().getName());
                if (nameComparison != 0) {
                    return nameComparison;
                }
                return o1.getServiceSpend().getServiceName().compareTo(o2.getServiceSpend().getServiceName());
            }
        });

        return topCustomerServiceSpends;
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
        List<CustomerServiceSpend> report = new ArrayList<>();
        List<CustomerTotalSpend> totalSpends = awsServiceInvoiceDao.getAllServiceSpends();

        // Sort totalSpends by total spend (descending)
        Collections.sort(totalSpends, new Comparator<CustomerTotalSpend>() {
            @Override
            public int compare(CustomerTotalSpend o1, CustomerTotalSpend o2) {
                return Long.compare(o2.getTotalSpend(), o1.getTotalSpend());
            }
        });

        for (CustomerTotalSpend currentSpend : totalSpends) {
            // Get the current customer
            Customer currCustomer = currentSpend.getCustomer();
            // Get all the services the customer used
            List<ServiceSpend> serviceSpends = currentSpend.getServiceSpends();
            // Sort the serviceSpends (high to low spend, then by service name)
            Collections.sort(serviceSpends, new Comparator<ServiceSpend>() {
                @Override
                public int compare(ServiceSpend o1, ServiceSpend o2) {
                    int spendComparison = Long.compare(o2.getSpend(), o1.getSpend());
                    if (spendComparison != 0) {
                        return spendComparison;
                    }
                    return o1.getServiceName().compareTo(o2.getServiceName());
                }
            });
            // For each service, add the customer and the service spend to the CustomerServiceSpend report
            for (ServiceSpend serviceSpend : serviceSpends) {
                report.add(new CustomerServiceSpend(currCustomer, serviceSpend));
            }
        }

        return report;
    }
}

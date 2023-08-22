# Week4GroupWork

<details>
<summary><h2>Refactored or Created Files</h2></summary>

<details>
  <summary>Customer.java</summary>
  
``` java
package com.kenzie.groupactivity.bigspender.types;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents a customer who uses AWS.
 */
public class Customer implements Comparable<Customer> {
    private String name;
    private LocalDate joinDate;

    /**
     * Constructor creating an AWS customer.
     * @param name The unique name of the customer.
     * @param joinDate The Data that the customer joined.
     */
    public Customer(String name, LocalDate joinDate) {
        this.name = name;
        this.joinDate = joinDate;
    }

    public String getName() {
        return name;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", joinDate=" + joinDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (getClass() != o.getClass()) {
            return false;
        }

        if (this == o) {
            return true;
        }

        Customer that = (Customer) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(joinDate, that.joinDate);
    }
    @Override
    public int compareTo(Customer other){
        int nameComparison = this.name.compareTo(other.getName());
        if (nameComparison==0){
            return  this.joinDate.compareTo(other.getJoinDate());
        }
        return nameComparison;
    }


    @Override
    public int hashCode() {
        return Objects.hash(name, joinDate);
    }
}
```
</details>

<details>
  <summary>CustomerTest.java</summary>
  
``` java
package com.kenzie.groupactivity.bigspender.types;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class CustomerTest {
    private Customer nedflixCustomer;
    private LocalDate nedflixJoinDate;
    private LocalDate laterJoinDate;
    private LocalDate earlierJoinDate;

    @BeforeEach
    void setUp() throws Exception {
        nedflixJoinDate = LocalDate.of(2020, 10, 11);
        laterJoinDate = LocalDate.of(2021, 1, 1);
        earlierJoinDate = LocalDate.of(2019, 1, 1);
        nedflixCustomer = new Customer("nedflix", nedflixJoinDate);
    }

    // PARTICIPANTS: add implementations to these tests of compareTo:

    @Test
    void compareTo_withAnEqualCustomer_returnsZero() {
        // GIVEN
        Customer equalCustomer = new Customer("nedflix", nedflixJoinDate);

        // WHEN
        int result = nedflixCustomer.compareTo(equalCustomer);

        // THEN
        //fail("Test not implemented yet!");
        assertTrue(result == 0, "compareTo should return zero for equalCustomers");
    }

    @Test
    void compareTo_withLaterCustomerName_returnsNegative() {
        // GIVEN
        Customer laterCustomer = new Customer("zedflix", nedflixJoinDate);

        // WHEN
        int result = nedflixCustomer.compareTo(laterCustomer);

        // THEN
        //fail("Test not implemented yet!");
        assertTrue(result < 0, "compareTo should return a negative value for laterCustomer");
    }

    @Test
    void compareTo_withEarlierCustomerName_returnsPositive() {
        // GIVEN
        Customer earlyCustomer = new Customer("bobflix", nedflixJoinDate);

        // WHEN
        int result = nedflixCustomer.compareTo(earlyCustomer);

        // THEN
        //fail("Test not implemented yet!");
        assertTrue(result > 0, "compareTo should return a positive value for laterCustomer");
    }

    @Test
    void compareTo_withSameCustomerNameLaterJoinDate_returnsNegative() {
        // GIVEN
        Customer sameCustomerLater = new Customer("nedflix", laterJoinDate);

        // WHEN
        int result = nedflixCustomer.compareTo(sameCustomerLater);

        // THEN
        //fail("Test not implemented yet!");
        assertTrue(result < 0, "compareTo should return a negative value for laterCustomer");
    }

    @Test
    void compareTo_withSameCustomerNameEarlierJoinDate_returnsPositive() {
        // GIVEN
        Customer sameCustomerEarly = new Customer("nedflix", earlierJoinDate);

        // WHEN
        int result = nedflixCustomer.compareTo(sameCustomerEarly);

        // THEN
        //fail("Test not implemented yet!");
        assertTrue(result > 0, "compareTo should return a positive value for laterCustomer");
    }

    // PARTICIPANTS: leave these tests below alone

    @Test
    void equals_inputObjectNull_returnsFalse() throws Exception {
        // WHEN
        boolean result = nedflixCustomer.equals(null);

        // THEN
        assertFalse(result, "Customer should not be equal to null!");
    }

    @Test
    void equals_inputObjectDifferentType_returnsFalse() {
        // WHEN
        boolean result = nedflixCustomer.equals("nedflix");

        // THEN
        assertFalse(result, "Customer should not be equal to a different type!");
    }

    @Test
    void equals_sameObject_returnsTrue() {
        // WHEN
        boolean result = nedflixCustomer.equals(nedflixCustomer);

        // THEN
        assertTrue(result, "Customer should be equal to a itself!");
    }

    @Test
    void equals_equalAttributes_returnsTrue() throws Exception {
        // GIVEN
        Customer same = new Customer("nedflix", nedflixJoinDate);

        // WHEN
        boolean result = nedflixCustomer.equals(same);

        // THEN
        assertTrue(result, "Customer should be equal to one with same attributes!");
    }

    @Test
    void equals_notEqualName_returnsFalse() {
        // GIVEN
        Customer other = new Customer("reddthem", nedflixJoinDate);

        // WHEN
        boolean result = nedflixCustomer.equals(other);

        // THEN
        assertFalse(result, "Customer should not be equal to one with a different name!");
    }

    @Test
    void equals_notEqualDate_returnsFalse() {
        // GIVEN
        Customer other = new Customer("nedflix", laterJoinDate);

        // WHEN
        boolean result = nedflixCustomer.equals(other);

        // THEN
        assertFalse(result, "Customer should not be equal to one with a different join date!");
    }
}
```
</details>
<details>
  <summary>ServiceSpendByService.java</summary>
  
``` java
package com.kenzie.groupactivity.bigspender.types;

import java.util.Comparator;

public class ServiceSpendByService implements Comparator<ServiceSpend> {
    @Override
    public int compare(ServiceSpend o1, ServiceSpend o2) {
        int result = Long.compare(o1.getSpend(),o2.getSpend());
        if(result == 0) {
            return o1.getServiceName().compareTo(o2.getServiceName());
        }
        return result;
    }
}
```
</details>
<details>
  <summary>CustomerTotalSpendBySpend.java</summary>
  
``` java
package com.kenzie.groupactivity.bigspender.compare;

import com.kenzie.groupactivity.bigspender.types.CustomerTotalSpend;

import java.util.Comparator;

public class CustomerTotalSpendBySpend implements Comparator<CustomerTotalSpend> {
    @Override
    public int compare(CustomerTotalSpend left, CustomerTotalSpend right) {
        return Long.compare(left.getTotalSpend(),right.getTotalSpend());
    }
}
```
</details>
<details>
  <summary>CustomerServicieSpend.java</summary>
  
``` java
package com.kenzie.groupactivity.bigspender.types;

import java.util.Objects;

/**
 * Represents both a Customer and the details of one AWS service spend.
 */
public class CustomerServiceSpend implements Comparable<CustomerServiceSpend> {
    private Customer customer;
    private ServiceSpend serviceSpend;

    /**
     * Constructor taking a customer and a service spend.
     * @param customer the customer.
     * @param serviceSpend The service spend.
     */
    public CustomerServiceSpend(Customer customer, ServiceSpend serviceSpend) {
        this.customer = customer;
        this.serviceSpend = serviceSpend;
    }

    public Customer getCustomer() {
        return customer;
    }

    public ServiceSpend getServiceSpend() {
        return serviceSpend;
    }

    @Override
    public String toString() {
        return "CustomerServiceSpend{" +
                "customer=" + customer +
                ", serviceSpend=" + serviceSpend +
                '}';
    }

    @Override
    public int compareTo(CustomerServiceSpend other) {
        int comparison = this.customer.compareTo(other.customer);
        if (comparison!=0){
            return comparison;
        }
        comparison = this.serviceSpend.getServiceName().compareTo(other.getServiceSpend().getServiceName());
        if (comparison !=0){
            return comparison;
        }
        return Long.compare(this.serviceSpend.getSpend(),other.serviceSpend.getSpend());
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerServiceSpend that = (CustomerServiceSpend) o;
        return Objects.equals(customer, that.customer)&&
                Objects.equals(serviceSpend,that.serviceSpend);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer,serviceSpend);
    }
}
```
</details>
<details>
  <summary>ServiceSpend.java</summary>
  
``` java
package com.kenzie.groupactivity.bigspender.types;

import java.util.Objects;

/**
 * Represents the amount spent on a single AWS service.
 * Must be associated with a Customer who needs to pay the bill.
 */
public class ServiceSpend {
    private ServiceType serviceName;
    private long spend;

    /**
     * Constructor taking all parameters.
     * @param serviceName The service the customer used.
     * @param spend The amount, in cents, that the customer consumed.
     */
    public ServiceSpend(ServiceType serviceName, long spend) {
        this.serviceName = serviceName;
        this.spend = spend;
    }

    public ServiceType getServiceName() {
        return serviceName;
    }

    public long getSpend() {
        return spend;
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceName,spend);
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if (o == null||getClass() != o.getClass()) return false;
        ServiceSpend sSpend = (ServiceSpend) o;
        return spend == sSpend.spend && serviceName == sSpend.serviceName;
    }

    @Override
    public String toString() {
        return "ServiceSpend{" +
                "serviceName=" + serviceName +
                ", spend=" + spend +
                '}';
    }
}
```
</details>
<details>
  <summary>AwsCustomerStatistics.java</summary>
  
``` java
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
```
</details>
<details>
  <summary>CustomerTotalSpendTest.java</summary>
  
``` java
package com.kenzie.groupactivity.bigspender.types;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CustomerTotalSpendTest {
    private CustomerTotalSpend nedflixTotalSpend;
    private Customer nedflix;
    private Customer reddthem;
    private LocalDate nedflixJoinDate;
    private LocalDate reddthemJoinDate;
    private long nedflixSpend = 500_00;
    private long reddthemSpend = 400_00;
    private List<ServiceSpend> nedflixSpends = Arrays.asList(
        new ServiceSpend(ServiceType.S3, 350_00),
        new ServiceSpend(ServiceType.DYNAMODB, 75_00),
        new ServiceSpend(ServiceType.EC2, 75_00));
    private List<ServiceSpend> reddthemSpends = Arrays.asList(
        new ServiceSpend(ServiceType.LAMBDA, 230_00),
        new ServiceSpend(ServiceType.EC2, 40_00),
        new ServiceSpend(ServiceType.DYNAMODB, 20_00),
        new ServiceSpend(ServiceType.SQS, 10_00));

    @BeforeEach
    void setUp() throws Exception {
        nedflixJoinDate = LocalDate.of(2008, 10, 11);
        nedflix = new Customer("nedflix", nedflixJoinDate);
        nedflixTotalSpend = new CustomerTotalSpend(nedflix, nedflixSpends);

        reddthemJoinDate = LocalDate.of(2009, 1, 1);
        reddthem = new Customer("reddthem", reddthemJoinDate);
    }

    @Test
    void getTotalSpend_withMultipleServiceSpends_returnsCorrectTotalSpend() {
        // GIVEN - nedflix and its multiple spends

        // WHEN
        long result = nedflixTotalSpend.getTotalSpend();

        // THEN
        assertEquals(nedflixSpend,
                     result,
                     String.format("Expected total spend for %s to be %d", nedflixTotalSpend, nedflixSpend)
        );
    }

    @Test
    void equals_inputObjectNull_returnsFalse() {
        // WHEN
        boolean result = nedflixTotalSpend.equals(null);

        // THEN
        assertFalse(result, "CustomerTotalSpend should not be equal to null!");
    }

    @Test
    void equals_inputObjectDifferentType_returnsFalse() {
        // WHEN
        boolean result = nedflixTotalSpend.equals("nedflixTotalSpend");

        // THEN
        assertFalse(result, "CustomerTotalSpend should not be equal to a different type!");
    }

    @Test
    void equals_sameObject_returnsTrue() {
        // WHEN
        boolean result = nedflixTotalSpend.equals(nedflixTotalSpend);

        // THEN
        assertTrue(result, "CustomerTotalSpend should be equal to a itself!");
    }

    @Test
    void equals_equalAttributes_returnsTrue() {
        // GIVEN
        CustomerTotalSpend same = new CustomerTotalSpend(nedflix, nedflixSpends);

        // WHEN
        boolean result = nedflixTotalSpend.equals(same);

        // THEN
        assertTrue(result, "CustomerTotalSpend should be equal to one with same attributes!");
    }

    @Test
    void equals_notEqualCustomer_returnsFalse() {
        // GIVEN
        CustomerTotalSpend other = new CustomerTotalSpend(reddthem, nedflixSpends);

        // WHEN
        boolean result = nedflixTotalSpend.equals(other);

        // THEN
        assertFalse(result, "CustomerTotalSpend should not be equal to one with a different customer!");
    }

    @Test
    void equals_notEqualSpend_returnsFalse() {
        // GIVEN
        CustomerTotalSpend other = new CustomerTotalSpend(nedflix, reddthemSpends);

        // WHEN
        boolean result = nedflixTotalSpend.equals(other);

        // THEN
        assertFalse(result, "CustomerTotalSpend should not be equal to one with a different spend!");
    }
}
```
</details>

</details>

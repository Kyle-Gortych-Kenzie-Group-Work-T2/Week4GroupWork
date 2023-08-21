package com.kenzie.groupactivity.bigspender.types;

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
        // Compare by customer name first
        int nameComparison = customer.getName().compareTo(other.getCustomer().getName());
        if (nameComparison != 0) {
            return nameComparison;
        }

        // If customer names are the same, compare by service spend
        return Long.compare(serviceSpend.getSpend(), other.getServiceSpend().getSpend());
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CustomerServiceSpend that = (CustomerServiceSpend) o;

        if (!customer.equals(that.customer)) {
            return false;
        }
        return serviceSpend.equals(that.serviceSpend);
    }

    @Override
    public int hashCode() {
        int result = customer.hashCode();
        result = 31 * result + serviceSpend.hashCode();
        return result;
    }
}

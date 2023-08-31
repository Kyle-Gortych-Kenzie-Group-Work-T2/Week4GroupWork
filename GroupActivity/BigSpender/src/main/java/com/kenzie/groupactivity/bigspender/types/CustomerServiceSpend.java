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
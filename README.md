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
public class Customer implements Comparable{
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
    public int hashCode() {
        return Objects.hash(name, joinDate);
    }

    @Override
    public int compareTo(Object o) {
        return 0;
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

        // WHEN

        // THEN
        fail("Test not implemented yet!");
    }

    @Test
    void compareTo_withLaterCustomerName_returnsNegative() {
        // GIVEN

        // WHEN

        // THEN
        fail("Test not implemented yet!");
    }

    @Test
    void compareTo_withEarlierCustomerName_returnsPositive() {
        // GIVEN

        // WHEN

        // THEN
        fail("Test not implemented yet!");
    }

    @Test
    void compareTo_withSameCustomerNameLaterJoinDate_returnsNegative() {
        // GIVEN

        // WHEN

        // THEN
        fail("Test not implemented yet!");
    }

    @Test
    void compareTo_withSameCustomerNameEarlierJoinDate_returnsPositive() {
        // GIVEN

        // WHEN

        // THEN
        fail("Test not implemented yet!");
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

</details>

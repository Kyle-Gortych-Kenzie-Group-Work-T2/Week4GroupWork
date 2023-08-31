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
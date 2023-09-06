<div align="right">
 
![week 2](https://img.shields.io/github/actions/workflow/status/Kyle-Gortych-Kenzie-Group-Work-T2/Week4GroupWork/main.yml?label=main) ![week 2](https://img.shields.io/github/actions/workflow/status/Kyle-Gortych-Kenzie-Group-Work-T2/Week4GroupWork/original.yml?label=original)

</div>

# Week 4 Group Project 

descript

## Changes
<details>
<summary>Diff with original branch</summary>

<details>
<summary>AwsCustomerStatistics.java</summary>
 
```diff
diff --git a/GroupActivity/BigSpender/src/main/java/com/kenzie/groupactivity/bigspender/AwsCustomerStatistics.java b/GroupActivity/BigSpender/src/main/java/com/kenzie/groupactivity/bigspender/AwsCustomerStatistics.java
index 69d85c5..5f746d6 100644
--- a/GroupActivity/BigSpender/src/main/java/com/kenzie/groupactivity/bigspender/AwsCustomerStatistics.java
+++ b/GroupActivity/BigSpender/src/main/java/com/kenzie/groupactivity/bigspender/AwsCustomerStatistics.java
@@ -1,12 +1,8 @@
 package com.kenzie.groupactivity.bigspender;
 
-import com.kenzie.groupactivity.bigspender.compare.CustomerTotalSpendBySpend;
-import com.kenzie.groupactivity.bigspender.types.ServiceSpendByService;
 import com.kenzie.groupactivity.bigspender.dao.AwsServiceInvoiceDao;
-import com.kenzie.groupactivity.bigspender.types.Customer;
 import com.kenzie.groupactivity.bigspender.types.CustomerServiceSpend;
 import com.kenzie.groupactivity.bigspender.types.CustomerTotalSpend;
-import com.kenzie.groupactivity.bigspender.types.ServiceSpend;
 
 import java.util.ArrayList;
 import java.util.Collections;
@@ -36,10 +32,11 @@ public class AwsCustomerStatistics {
      */
     public List<CustomerServiceSpend> getTopServiceSpendForEachCustomer() {
         // PARTICIPANTS: Which list needs to be sorted and returned?
-        List<CustomerServiceSpend> spends = awsServiceInvoiceDao.getHighestServiceSpendsForEachCustomer();
-        Collections.sort(spends);
+        List<CustomerServiceSpend> topCustomerServiceSpends =
+                awsServiceInvoiceDao.getHighestServiceSpendsForEachCustomer();
+        Collections.sort(null);
 
-        return spends;
+        return null;
     }
 
     /**
@@ -52,19 +49,10 @@ public class AwsCustomerStatistics {
      */
     public List<CustomerServiceSpend> getTopItemizedSpends() {
         // PARTICIPANTS: Implement according to Javadoc and README
-        List<CustomerServiceSpend> reportResults = new ArrayList<>();
+        List<CustomerServiceSpend> report = new ArrayList<>();
         List<CustomerTotalSpend> totalSpends = awsServiceInvoiceDao.getAllServiceSpends();
-        Collections.sort(totalSpends,new CustomerTotalSpendBySpend().reversed());
-        for (CustomerTotalSpend totalSpend : totalSpends){
-            Customer currentCustomer = totalSpend.getCustomer();
-            List<ServiceSpend> currentServiceSpends = totalSpend.getServiceSpends();
-            Collections.sort(currentServiceSpends, new ServiceSpendByService().reversed());
-            for (ServiceSpend spend: currentServiceSpends){
-                CustomerServiceSpend customerServiceSpend = new CustomerServiceSpend(currentCustomer,spend);
-                reportResults.add(customerServiceSpend);
-            }
-        }
-        return reportResults;
+
+        return report;
 
     }
-}
\ No newline at end of file
+}
```
</details>

<details>
<summary>CustomerTotalSpendBySpend.java</summary>
 
```diff
diff --git a/GroupActivity/BigSpender/src/main/java/com/kenzie/groupactivity/bigspender/compare/CustomerTotalSpendBySpend.java b/GroupActivity/BigSpender/src/main/java/com/kenzie/groupactivity/bigspender/compare/CustomerTotalSpendBySpend.java
deleted file mode 100644
index 83f1a1a..0000000
--- a/GroupActivity/BigSpender/src/main/java/com/kenzie/groupactivity/bigspender/compare/CustomerTotalSpendBySpend.java
+++ /dev/null
@@ -1,12 +0,0 @@
-package com.kenzie.groupactivity.bigspender.compare;
-
-import com.kenzie.groupactivity.bigspender.types.CustomerTotalSpend;
-
-import java.util.Comparator;
-
-public class CustomerTotalSpendBySpend implements Comparator<CustomerTotalSpend> {
-    @Override
-    public int compare(CustomerTotalSpend left, CustomerTotalSpend right) {
-        return Long.compare(left.getTotalSpend(),right.getTotalSpend());
-    }
-}
\ No newline at end of file
```
</details>

<details>
<summary>Customer.java</summary>
 
```diff
diff --git a/GroupActivity/BigSpender/src/main/java/com/kenzie/groupactivity/bigspender/types/Customer.java b/GroupActivity/BigSpender/src/main/java/com/kenzie/groupactivity/bigspender/types/Customer.java
index 89b11bd..6169820 100644
--- a/GroupActivity/BigSpender/src/main/java/com/kenzie/groupactivity/bigspender/types/Customer.java
+++ b/GroupActivity/BigSpender/src/main/java/com/kenzie/groupactivity/bigspender/types/Customer.java
@@ -6,7 +6,7 @@ import java.util.Objects;
 /**
  * Represents a customer who uses AWS.
  */
-public class Customer implements Comparable<Customer> {
+public class Customer {
     private String name;
     private LocalDate joinDate;
 
@@ -31,9 +31,9 @@ public class Customer implements Comparable<Customer> {
     @Override
     public String toString() {
         return "Customer{" +
-                "name='" + name + '\'' +
-                ", joinDate=" + joinDate +
-                '}';
+            "name='" + name + '\'' +
+            ", joinDate=" + joinDate +
+            '}';
     }
 
     @Override
@@ -52,20 +52,11 @@ public class Customer implements Comparable<Customer> {
 
         Customer that = (Customer) o;
         return Objects.equals(name, that.name) &&
-                Objects.equals(joinDate, that.joinDate);
+            Objects.equals(joinDate, that.joinDate);
     }
-    @Override
-    public int compareTo(Customer other){
-        int nameComparison = this.name.compareTo(other.getName());
-        if (nameComparison==0){
-            return  this.joinDate.compareTo(other.getJoinDate());
-        }
-        return nameComparison;
-    }
-
 
     @Override
     public int hashCode() {
         return Objects.hash(name, joinDate);
     }
-}
\ No newline at end of file
+}
```
</details>

<details>
<summary>CustomerServiceSpend.java</summary>
 
```diff
diff --git a/GroupActivity/BigSpender/src/main/java/com/kenzie/groupactivity/bigspender/types/CustomerServiceSpend.java b/GroupActivity/BigSpender/src/main/java/com/kenzie/groupactivity/bigspender/types/CustomerServiceSpend.java
index fef6e1c..3c10577 100644
--- a/GroupActivity/BigSpender/src/main/java/com/kenzie/groupactivity/bigspender/types/CustomerServiceSpend.java
+++ b/GroupActivity/BigSpender/src/main/java/com/kenzie/groupactivity/bigspender/types/CustomerServiceSpend.java
@@ -1,11 +1,9 @@
 package com.kenzie.groupactivity.bigspender.types;
 
-import java.util.Objects;
-
 /**
  * Represents both a Customer and the details of one AWS service spend.
  */
-public class CustomerServiceSpend implements Comparable<CustomerServiceSpend> {
+public class CustomerServiceSpend {
     private Customer customer;
     private ServiceSpend serviceSpend;
 
@@ -30,35 +28,8 @@ public class CustomerServiceSpend implements Comparable<CustomerServiceSpend> {
     @Override
     public String toString() {
         return "CustomerServiceSpend{" +
-                "customer=" + customer +
-                ", serviceSpend=" + serviceSpend +
-                '}';
-    }
-
-    @Override
-    public int compareTo(CustomerServiceSpend other) {
-        int comparison = this.customer.compareTo(other.customer);
-        if (comparison!=0){
-            return comparison;
-        }
-        comparison = this.serviceSpend.getServiceName().compareTo(other.getServiceSpend().getServiceName());
-        if (comparison !=0){
-            return comparison;
-        }
-        return Long.compare(this.serviceSpend.getSpend(),other.serviceSpend.getSpend());
-    }
-
-    @Override
-    public boolean equals(Object o) {
-        if(this == o) return true;
-        if (o == null || getClass() != o.getClass()) return false;
-        CustomerServiceSpend that = (CustomerServiceSpend) o;
-        return Objects.equals(customer, that.customer)&&
-                Objects.equals(serviceSpend,that.serviceSpend);
-    }
-
-    @Override
-    public int hashCode() {
-        return Objects.hash(customer,serviceSpend);
+               "customer=" + customer +
+               ", serviceSpend=" + serviceSpend +
+               '}';
     }
-}
\ No newline at end of file
+}
```
</details>

<details>
<summary>ServiceSpend.java</summary>
 
```diff
diff --git a/GroupActivity/BigSpender/src/main/java/com/kenzie/groupactivity/bigspender/types/ServiceSpend.java b/GroupActivity/BigSpender/src/main/java/com/kenzie/groupactivity/bigspender/types/ServiceSpend.java
index 1493bdb..5713ae2 100644
--- a/GroupActivity/BigSpender/src/main/java/com/kenzie/groupactivity/bigspender/types/ServiceSpend.java
+++ b/GroupActivity/BigSpender/src/main/java/com/kenzie/groupactivity/bigspender/types/ServiceSpend.java
@@ -1,7 +1,5 @@
 package com.kenzie.groupactivity.bigspender.types;
 
-import java.util.Objects;
-
 /**
  * Represents the amount spent on a single AWS service.
  * Must be associated with a Customer who needs to pay the bill.
@@ -28,24 +26,11 @@ public class ServiceSpend {
         return spend;
     }
 
-    @Override
-    public int hashCode() {
-        return Objects.hash(serviceName,spend);
-    }
-
-    @Override
-    public boolean equals(Object o) {
-        if(this == o) return true;
-        if (o == null||getClass() != o.getClass()) return false;
-        ServiceSpend sSpend = (ServiceSpend) o;
-        return spend == sSpend.spend && serviceName == sSpend.serviceName;
-    }
-
     @Override
     public String toString() {
         return "ServiceSpend{" +
-                "serviceName=" + serviceName +
-                ", spend=" + spend +
-                '}';
+            "serviceName=" + serviceName +
+            ", spend=" + spend +
+            '}';
     }
-}
\ No newline at end of file
+}
```
</details>

<details>
<summary>ServiceSpendByService.java</summary>
 
```diff
diff --git a/GroupActivity/BigSpender/src/main/java/com/kenzie/groupactivity/bigspender/types/ServiceSpendByService.java b/GroupActivity/BigSpender/src/main/java/com/kenzie/groupactivity/bigspender/types/ServiceSpendByService.java
deleted file mode 100644
index 9513776..0000000
--- a/GroupActivity/BigSpender/src/main/java/com/kenzie/groupactivity/bigspender/types/ServiceSpendByService.java
+++ /dev/null
@@ -1,14 +0,0 @@
-package com.kenzie.groupactivity.bigspender.types;
-
-import java.util.Comparator;
-
-public class ServiceSpendByService implements Comparator<ServiceSpend> {
-    @Override
-    public int compare(ServiceSpend o1, ServiceSpend o2) {
-        int result = Long.compare(o1.getSpend(),o2.getSpend());
-        if(result == 0) {
-            return o1.getServiceName().compareTo(o2.getServiceName());
-        }
-        return result;
-    }
-}
```
</details>

<details>
<summary>CustomerServiceSpendTest.java</summary>
 
```diff
diff --git a/GroupActivity/BigSpender/src/test/java/com/kenzie/groupactivity/bigspender/types/CustomerServiceSpendTest.java b/GroupActivity/BigSpender/src/test/java/com/kenzie/groupactivity/bigspender/types/CustomerServiceSpendTest.java
index 102715d..17bbe83 100644
--- a/GroupActivity/BigSpender/src/test/java/com/kenzie/groupactivity/bigspender/types/CustomerServiceSpendTest.java
+++ b/GroupActivity/BigSpender/src/test/java/com/kenzie/groupactivity/bigspender/types/CustomerServiceSpendTest.java
@@ -30,11 +30,10 @@ public class CustomerServiceSpendTest {
         CustomerServiceSpend equalCustomerServiceSpend = new CustomerServiceSpend(equalNedflix, nedflixSagemakerSpend);
 
         // WHEN
-        int result = equalCustomerServiceSpend.compareTo(nedflixCustomerServiceSpend);
+
 
         // THEN
-        //fail("Not implemented");
-        assertTrue(result == 0, "compareTo should return zero for nedflixCustomerServiceSpend");
+        fail("Not implemented");
     }
 
     @Test
@@ -44,11 +43,10 @@ public class CustomerServiceSpendTest {
         CustomerServiceSpend laterCustomerServiceSpend = new CustomerServiceSpend(laterCustomer, nedflixSagemakerSpend);
 
         // WHEN
-        int result = nedflixCustomerServiceSpend.compareTo(laterCustomerServiceSpend);
+
 
         // THEN
-        //fail("Not implemented");
-        assertTrue(result < 0, "compareTo should return negative for laterCustomerServiceSpend");
+        fail("Not implemented");
     }
 
     @Test
@@ -58,11 +56,10 @@ public class CustomerServiceSpendTest {
         CustomerServiceSpend earlierCustomerServiceSpend = new CustomerServiceSpend(earlierCustomer, nedflixSagemakerSpend);
 
         // WHEN
-        int result = nedflixCustomerServiceSpend.compareTo(earlierCustomerServiceSpend);
+
 
         // THEN
-        //fail("Needs implemented");
-        assertTrue(result > 0, "compareTo should return positive for earlierCustomerServiceSpend");
+        fail("Needs implemented");
     }
 
     @Test
```
</details>

<details>
<summary>CustomerTest.java</summary>
 
```diff
diff --git a/GroupActivity/BigSpender/src/test/java/com/kenzie/groupactivity/bigspender/types/CustomerTest.java b/GroupActivity/BigSpender/src/test/java/com/kenzie/groupactivity/bigspender/types/CustomerTest.java
index e6c92e0..a6b9b02 100644
--- a/GroupActivity/BigSpender/src/test/java/com/kenzie/groupactivity/bigspender/types/CustomerTest.java
+++ b/GroupActivity/BigSpender/src/test/java/com/kenzie/groupactivity/bigspender/types/CustomerTest.java
@@ -28,66 +28,51 @@ class CustomerTest {
     @Test
     void compareTo_withAnEqualCustomer_returnsZero() {
         // GIVEN
-        Customer equalCustomer = new Customer("nedflix", nedflixJoinDate);
 
         // WHEN
-        int result = nedflixCustomer.compareTo(equalCustomer);
 
         // THEN
-        //fail("Test not implemented yet!");
-        assertTrue(result == 0, "compareTo should return zero for equalCustomers");
+        fail("Test not implemented yet!");
     }
 
     @Test
     void compareTo_withLaterCustomerName_returnsNegative() {
         // GIVEN
-        Customer laterCustomer = new Customer("zedflix", nedflixJoinDate);
 
         // WHEN
-        int result = nedflixCustomer.compareTo(laterCustomer);
 
         // THEN
-        //fail("Test not implemented yet!");
-        assertTrue(result < 0, "compareTo should return a negative value for laterCustomer");
+        fail("Test not implemented yet!");
     }
 
     @Test
     void compareTo_withEarlierCustomerName_returnsPositive() {
         // GIVEN
-        Customer earlyCustomer = new Customer("bobflix", nedflixJoinDate);
 
         // WHEN
-        int result = nedflixCustomer.compareTo(earlyCustomer);
 
         // THEN
-        //fail("Test not implemented yet!");
-        assertTrue(result > 0, "compareTo should return a positive value for laterCustomer");
+        fail("Test not implemented yet!");
     }
 
     @Test
     void compareTo_withSameCustomerNameLaterJoinDate_returnsNegative() {
         // GIVEN
-        Customer sameCustomerLater = new Customer("nedflix", laterJoinDate);
 
         // WHEN
-        int result = nedflixCustomer.compareTo(sameCustomerLater);
 
         // THEN
-        //fail("Test not implemented yet!");
-        assertTrue(result < 0, "compareTo should return a negative value for laterCustomer");
+        fail("Test not implemented yet!");
     }
 
     @Test
     void compareTo_withSameCustomerNameEarlierJoinDate_returnsPositive() {
         // GIVEN
-        Customer sameCustomerEarly = new Customer("nedflix", earlierJoinDate);
 
         // WHEN
-        int result = nedflixCustomer.compareTo(sameCustomerEarly);
 
         // THEN
-        //fail("Test not implemented yet!");
-        assertTrue(result > 0, "compareTo should return a positive value for laterCustomer");
+        fail("Test not implemented yet!");
     }
 
     // PARTICIPANTS: leave these tests below alone
```
</details>

</details>

<div align="center">
 
### :hammer_and_wrench: Tools :

| Version Control | Build System | Languages |
| --------------- | ------------ | --------- |
| <img src="https://img.shields.io/badge/Git-white?style=plastic&logo=git&logoColor=red" title="Git" alt="Git" height="30"/> | <img src="https://img.shields.io/badge/Gradle-white?style=plastic&logo=gradle&logoColor=black" title="gradle" alt="gradle" height="30"/> | <img src="https://custom-icon-badges.demolab.com/badge/Java-white.svg?&sytle=plastic&logo=java" title="Java" alt="Java" height="30"/> |
</div>
<br>

## Gradle Commands
```console
./gradlew groupactivity-bigspender-test
```
<br>

<a href="your-gmail-link?">:mailbox:</a> How to reach the maintainer

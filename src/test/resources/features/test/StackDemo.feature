Feature: [NDC] Regression Suite Test Set

@TEST_CRDP-16813 @TESTSET_CRDP-16836 @TESTSET_CRDP-16946 @TESTSET_CRDP-18573 @NDC_BookingWithNTP @NDC_Bookings @NDC_Payments @REGRESSION @data=CRDP-16813
Scenario Outline: CRDP-16813 Verify RoundTrip Booking with 2ADT2CHD1INF1INS Business with NTP on Economy (Cash Payment)
Given User Set the required data maps for test
When User updates Soap request payload with dynamic attribute
 | Field | Value |
 | SchemaType | NDC |
 | SellerId | ac.cndc.qa.v1:ac.gndc.qa.v1:ac.gndc.qa.v1 |
 | AS_LEG1_DATE | FutureDate+11 |
Then I should have <left> cucumbers
Examples:
  | start | eat | left |
  |    12 |   5 |    7 |
  |    20 |   5 |   15 |

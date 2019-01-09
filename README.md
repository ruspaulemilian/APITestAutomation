# APITestAutomation

Given the user story and API above:
1.	What clarification or additional information might you need in order to write test for this feature?

It was nice that the application was running

2.	How would this API test be automated? Please provide an implementation example using a test tool or framework of your choice.
3.	What parts of the response you would test and why?  How would you validate the response?

The first check should be on the status code
The next check on the returned response (check that the details that I want to modify are successfully retrieved)
You will see in the step (Response includes the following) used the method equalTo which means that my input data should match with the output data

4.	Please explain how you could optimise a test suite that contains 100's of tests similar to the one above so that it gives its results quicker?

Indeed here we can have a lot of tests on updating a user
There are 10 fields that compose the user, so we can do a lot of combinations:
- update just one field for each request
- update 2 fields for each request
- update 3 fields for each request
.
.
.
.
.

But, it doesn`t make sense to have 100 of tests, so we could  do it like that

We got 10 fields that form a user
Test 1   perform an update without toke NOK
Test 2   perform an update without user NOK
Test 3   perform an update with invalid or expired token NOK 
Test 4-  update 1 field
Test 5 - update 2 fields
Test 6 - update 9 fields
Test 7-  update all the fields (this is less probably because some data specific to a user is static like, first_name, last_name)
Test 8 - inject a new field just from test and verify that the server retunrs a bad request error - NOK

 ......0..1..2.....................9.10__11

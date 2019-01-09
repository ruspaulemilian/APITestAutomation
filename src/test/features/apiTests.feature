Feature: The Jira User Story

  Background:
    Given The application is up and running

  Scenario: Successfully modify details on the user
    When I want to modify the user details using details from the json file 'jsonInputData.json'
    Then The status code is 200
    And Response includes the following
      | token           | b4444924ec456699e23024f2881282dd72419d30 |
      | account_id      | 2032                                     |
      | mobile_number   | 004455555777                             |
      | home_tel_number | 004455555777                             |
      | last_name       | Jones                                    |
      | username        | one.two@homeserve.com                    |
      | sms_allowed     | 1                                        |
      | email_allowed   | 1                                        |
      | mail_allowed    | 0                                        |
      | first_name      | Anne                                     |


  Scenario: Verify that updating user details with an invalid token is not possible
    When I want to modify the user details using details from the json file 'jsonInputDataInvalidToken.json'
    Then The status code is 403
    And Response includes the following
      | error       | 52                 |
      | description | TOKEN is not valid |
      | ts          | 1526401668636      |
      | ms          | 66                 |

  Scenario: Verify that updating user details with no token is not possible
    When I want to modify the user details using details from the json file 'jsonInputDataNoToken.json'
    Then The status code is 403
    And Response includes the following
      | error       | 53               |
      | description | TOKEN is missing |
      | ts          | 1526401668636    |
      | ms          | 66               |







@ServiceDesk
Feature: End to end service desk work flow


  Scenario Outline: Login to the service desk,create a ticket and complete the workflow
   Given User is logged in to the Service Desk Dashboard
    And User should click on the the Create button


    And User should access the service desk ticket
    And User should see the status of the ticket as "<>"
    And User should be able to comment on the ticket "<>"
    And User should be able to logout from Service Desk









    Examples:
      |  |
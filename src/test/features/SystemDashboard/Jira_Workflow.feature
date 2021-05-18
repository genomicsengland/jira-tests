@Jira
Feature: End to end Jira flow

  @JiraWorkflow1
  Scenario Outline: Login to the System Dashboard and create a ticket for issue type "Test" and complete the workflow 1

    Given User is logged in to the Jira System Dashboard
    And  User should click on the the Create button
    And User should be able to select the project "<projectName>"
    And User should be able to select the Issue Type "<issueType>"
    And User should be able to provide the Summary "<summaryText>"
    And User should be able to provide the ticket description "<description>"
    And User should be able to select the priority "<priority>"
    And User should be able to assign the ticket to the assignee "<assignee>"
    And User should be able to submit the ticket
    And User should be able to see the ticket is created successfully and store the ticketID
    And User should be able to see ticket type as "<issueType>"
    And User should be able to see ticket priority as "<priority>"
    And User should be able to see ticket description as "<description>"
    And User should see the ticket status as "BACKLOG"
    And User should be able to update the workflow as "In Progress"
    And User should see the ticket status as "In Progress"
    And User should be able to update the workflow as "Done"
    And User should see the ticket status as "Done"
    And User should be able to update the workflow as "Selected for Development"
    And User should see the ticket status as "Selected for Development"
    Then User should logout from the Jira

    Examples:
      | projectName | issueType | summaryText | description                 | priority | assignee |
      | Test (TEST) | Test      | Test        | This is for testing purpose | 4. Low   | autobot  |

  @JiraWorkflow2
  Scenario Outline: Login to the System Dashboard and create a ticket for issue type "Task" and complete the workflow 1

    Given User is logged in to the Jira System Dashboard
    And  User should click on the the Create button
    And User should be able to select the project "<projectName>"
    And User should be able to select the Issue Type "<issueType>"
    And User should be able to provide the Summary "<summaryText>"
    And User should be able to provide the ticket description "<description>"
    And User should be able to select the priority "<priority>"
    And User should be able to assign the ticket to the assignee "<assignee>"
    And User should be able to submit the ticket
    And User should be able to see the ticket is created successfully and store the ticketID
    And User should be able to see ticket type as "<issueType>"
    And User should be able to see ticket priority as "<priority>"
    And User should be able to see ticket description as "<description>"
    And User should see the ticket status as "BACKLOG"
    And User should be able to update the workflow as "In Progress"
    And User should see the ticket status as "In Progress"
    And User should be able to update the workflow as "Done"
    And User should see the ticket status as "Done"
    And User should be able to update the workflow as "Selected for Development"
    And User should see the ticket status as "Selected for Development"
    Then User should logout from the Jira

    Examples:
      | projectName | issueType | summaryText | description                 | priority | assignee |
      | Test (TEST) | Task      | Test        | This is for testing purpose | 4. Low   | autobot  |
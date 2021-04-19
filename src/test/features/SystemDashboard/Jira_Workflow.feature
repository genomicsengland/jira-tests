@Jira
Feature: End to end Jira flow

@JiraWorkflow1
  Scenario Outline: Login to the System Dashboard and create a ticket and complete the workflow 1

  Given User is logged in to the Jira System Dashboard
  And  User should click on the the Create button
  And User should be able to select the project "<projectName>"
  And User should be able to select the Issue Type "<issueType>"
  And User should be able to select the Application "<application>"
  And User should be able to provide the Summary "<summaryText>"
  And User should be able to select the Workstream "<workStream>"
  And User should be able to provide the ticket description "<description>"
  And User should be able to select the priority "<priority>"
  And User should be able to assign the ticket to the assignee "<assignee>"
  And User should be able to submit the ticket
  And User should be able to see the ticket is created successfully and store the ticketID
  And User should see the ticket status as "Open"
  And User should be able to able to update the workflow as "Scenario in progress"
  And User should see the ticket status as "Scenario in progress"
  And User should be able to able to update the workflow as "Scenario Review"
  And User should see the ticket status as "Scenario Review"
  And User should be able to able to update the workflow as "Manual test"
  And User should see the ticket status as "Manual execution"
  And User should be able to able to update the workflow as "Scenario change required"
  And User should see the ticket status as "Scenario change required"
  Then User should logout from the Jira

  Examples:
    | projectName          | issueType | summaryText | application | workStream | description                 | priority | assignee          |
    | Testing Project (TP) | Test      | Test        | MIS         | General    | This is for testing purpose | 4. Low   | Sailaja  Avirneni |



  @JiraWorkflow2
  Scenario Outline: Login to the System Dashboard and create a ticket and complete the workflow 2

    Given User is logged in to the Jira System Dashboard
    And  User should click on the the Create button
    And User should be able to select the project "<projectName>"
    And User should be able to select the Issue Type "<issueType>"
    And User should be able to select the Application "<application>"
    And User should be able to provide the Summary "<summaryText>"
    And User should be able to select the Workstream "<workStream>"
    And User should be able to provide the ticket description "<description>"
    And User should be able to select the priority "<priority>"
    And User should be able to assign the ticket to the assignee "<assignee>"
    And User should be able to submit the ticket
    And User should be able to see the ticket is created successfully and store the ticketID
    And User should see the ticket status as "Open"
    And User should be able to able to update the workflow as "Scenario in progress"
    And User should see the ticket status as "Scenario in progress"
    And User should be able to able to update the workflow as "Scenario Review"
    And User should see the ticket status as "Scenario Review"
    And User should be able to able to update the workflow as "Manual test"
    And User should see the ticket status as "Manual execution"
    And User should be able to able to update the workflow as "Deprecated"
    And User should see the ticket status as "Deprecated"
    Then User should logout from the Jira

    Examples:
      | projectName          | issueType | summaryText | application | workStream | description                 | priority | assignee          |
      | Testing Project (TP) | Test      | Test        | MIS         | General    | This is for testing purpose | 4. Low   | Sailaja  Avirneni |



  @JiraWorkflow3
  Scenario Outline: Login to the System Dashboard and create a ticket and complete the workflow 3

    Given User is logged in to the Jira System Dashboard
    And  User should click on the the Create button
    And User should be able to select the project "<projectName>"
    And User should be able to select the Issue Type "<issueType>"
    And User should be able to select the Application "<application>"
    And User should be able to provide the Summary "<summaryText>"
    And User should be able to select the Workstream "<workStream>"
    And User should be able to provide the ticket description "<description>"
    And User should be able to select the priority "<priority>"
    And User should be able to assign the ticket to the assignee "<assignee>"
    And User should be able to submit the ticket
    And User should be able to see the ticket is created successfully and store the ticketID
    And User should see the ticket status as "Open"
    And User should be able to able to update the workflow as "Scenario in progress"
    And User should see the ticket status as "Scenario in progress"
    And User should be able to able to update the workflow as "Scenario Review"
    And User should see the ticket status as "Scenario Review"
    And User should be able to able to update the workflow as "Automated test"
    And User should see the ticket status as "Ready for automation"
    And User should be able to able to update the workflow as "Automation in progress"
    And User should see the ticket status as "Automation in progress"
    And User should be able to able to update the workflow as "Automation review"
    And User should see the ticket status as "Automation review"
    And User should be able to able to update the workflow as "Automated execution"
    And User should see the ticket status as "Automated execution"
    Then User should logout from the Jira

    Examples:
      | projectName          | issueType | summaryText | application | workStream | description                 | priority | assignee          |
      | Testing Project (TP) | Test      | Test        | MIS         | General    | This is for testing purpose | 4. Low   | Sailaja  Avirneni |




  @JiraWorkflow4
  Scenario Outline: Login to the System Dashboard and create a ticket and complete the workflow 4

    Given User is logged in to the Jira System Dashboard
    And  User should click on the the Create button
    And User should be able to select the project "<projectName>"
    And User should be able to select the Issue Type "<issueType>"
    And User should be able to select the Application "<application>"
    And User should be able to provide the Summary "<summaryText>"
    And User should be able to select the Workstream "<workStream>"
    And User should be able to provide the ticket description "<description>"
    And User should be able to select the priority "<priority>"
    And User should be able to assign the ticket to the assignee "<assignee>"
    And User should be able to submit the ticket
    And User should be able to see the ticket is created successfully and store the ticketID
    And User should see the ticket status as "Open"
    And User should be able to able to update the workflow as "Scenario in progress"
    And User should see the ticket status as "Scenario in progress"
    And User should be able to able to update the workflow as "Scenario Review"
    And User should see the ticket status as "Scenario Review"
    And User should be able to able to update the workflow as "Automated test"
    And User should see the ticket status as "Ready for automation"
    And User should be able to able to update the workflow as "Automation in progress"
    And User should see the ticket status as "Automation in progress"
    And User should be able to able to update the workflow as "Automation review"
    And User should see the ticket status as "Automation review"
    And User should be able to able to update the workflow as "Automation change requ..."
    And User should see the ticket status as "Automation change required"
    Then User should logout from the Jira

    Examples:
      | projectName          | issueType | summaryText | application | workStream | description                 | priority | assignee          |
      | Testing Project (TP) | Test      | Test        | MIS         | General    | This is for testing purpose | 4. Low   | Sailaja  Avirneni |


  @JiraWorkflow5
  Scenario Outline: Login to the System Dashboard and create a ticket and complete the workflow 5

    Given User is logged in to the Jira System Dashboard
    And  User should click on the the Create button
    And User should be able to select the project "<projectName>"
    And User should be able to select the Issue Type "<issueType>"
    And User should be able to select the Application "<application>"
    And User should be able to provide the Summary "<summaryText>"
    And User should be able to select the Workstream "<workStream>"
    And User should be able to provide the ticket description "<description>"
    And User should be able to select the priority "<priority>"
    And User should be able to assign the ticket to the assignee "<assignee>"
    And User should be able to submit the ticket
    And User should be able to see the ticket is created successfully and store the ticketID
    And User should see the ticket status as "Open"
    And User should be able to able to update the workflow as "Scenario in progress"
    And User should see the ticket status as "Scenario in progress"
    And User should be able to able to update the workflow as "Scenario Review"
    And User should see the ticket status as "Scenario Review"
    And User should be able to able to update the workflow as "Automated test"
    And User should see the ticket status as "Ready for automation"
    And User should be able to able to update the workflow as "Automation in progress"
    And User should see the ticket status as "Automation in progress"
    And User should be able to able to update the workflow as "Automation review"
    And User should see the ticket status as "Automation review"
    And User should be able to able to update the workflow as "Automated execution"
    And User should see the ticket status as "Automated execution"
    And User should be able to able to update the workflow as "Deprecated"
    And User should see the ticket status as "Deprecated"
    Then User should logout from the Jira

    Examples:
      | projectName          | issueType | summaryText | application | workStream | description                 | priority | assignee          |
      | Testing Project (TP) | Test      | Test        | MIS         | General    | This is for testing purpose | 4. Low   | Sailaja  Avirneni |


  @JiraWorkflow6
  Scenario Outline: Login to the System Dashboard and create a ticket and complete the workflow 6

    Given User is logged in to the Jira System Dashboard
    And  User should click on the the Create button
    And User should be able to select the project "<projectName>"
    And User should be able to select the Issue Type "<issueType>"
    And User should be able to select the Application "<application>"
    And User should be able to provide the Summary "<summaryText>"
    And User should be able to select the Workstream "<workStream>"
    And User should be able to provide the ticket description "<description>"
    And User should be able to select the priority "<priority>"
    And User should be able to assign the ticket to the assignee "<assignee>"
    And User should be able to submit the ticket
    And User should be able to see the ticket is created successfully and store the ticketID
    And User should see the ticket status as "Open"
    And User should be able to able to update the workflow as "Scenario in progress"
    And User should see the ticket status as "Scenario in progress"
    And User should be able to able to update the workflow as "Scenario Review"
    And User should see the ticket status as "Scenario Review"
    And User should be able to able to update the workflow as "Automated test"
    And User should see the ticket status as "Ready for automation"
    And User should be able to able to update the workflow as "Automation in progress"
    And User should see the ticket status as "Automation in progress"
    And User should be able to able to update the workflow as "Automation review"
    And User should see the ticket status as "Automation review"
    And User should be able to able to update the workflow as "Automated execution"
    And User should see the ticket status as "Automated execution"
    And User should be able to able to update the workflow as "Scenario change required"
    And User should see the ticket status as "Scenario change required"
    Then User should logout from the Jira


    Examples:
      | projectName          | issueType | summaryText | application | workStream | description                 | priority | assignee          |
      | Testing Project (TP) | Test      | Test        | MIS         | General    | This is for testing purpose | 4. Low   | Sailaja  Avirneni |

  @JiraWorkflow7
  Scenario Outline: Login to the System Dashboard and create a ticket and complete the workflow 7

    Given User is logged in to the Jira System Dashboard
    And  User should click on the the Create button
    And User should be able to select the project "<projectName>"
    And User should be able to select the Issue Type "<issueType>"
    And User should be able to select the Application "<application>"
    And User should be able to provide the Summary "<summaryText>"
    And User should be able to select the Workstream "<workStream>"
    And User should be able to provide the ticket description "<description>"
    And User should be able to select the priority "<priority>"
    And User should be able to assign the ticket to the assignee "<assignee>"
    And User should be able to submit the ticket
    And User should be able to see the ticket is created successfully and store the ticketID
    And User should see the ticket status as "Open"
    And User should be able to able to update the workflow as "Scenario in progress"
    And User should see the ticket status as "Scenario in progress"
    And User should be able to able to update the workflow as "Scenario Review"
    And User should see the ticket status as "Scenario Review"
    And User should be able to able to update the workflow as "Manual test"
    And User should see the ticket status as "Manual execution"
    And User should be able to able to update the workflow as "Ready for automation"
    And User should see the ticket status as "Ready for automation"
    And User should be able to able to update the workflow as "Automation in progress"
    And User should see the ticket status as "Automation in progress"
    And User should be able to able to update the workflow as "Automation review"
    And User should see the ticket status as "Automation review"
    And User should be able to able to update the workflow as "Automated execution"
    And User should see the ticket status as "Automated execution"
    Then User should logout from the Jira



    Examples:
      | projectName          | issueType | summaryText | application | workStream | description                 | priority | assignee          |
      | Testing Project (TP) | Test      | Test        | MIS         | General    | This is for testing purpose | 4. Low   | Sailaja  Avirneni |


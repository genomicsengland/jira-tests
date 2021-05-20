@ServiceDesk
Feature: End to end service desk work flow

  @WorkFlow1
  Scenario Outline: Login to the service desk,create a service request ticket and workflow transition is In progress, Pending, Canceled and Closed
    Given User is logged in to the Service Desk portal Home page
    And User should be able to see all projects
    And User should click on the the Create button
    And User should be able to select the project "<projectName>"
    And User should be able to select the Issue Type "<issueType>"
    And User should be able to provide the Summary "<summaryText>"
    And User should be able to provide the description "<description>"
    And User should be able to select the priority "<priority>"
    And User should be able to assign the ticket to the assignee "<assignee>"
    And User should be able to submit the ticket
    And User should be able to see the ticket is created successfully and store the ticketID
    And User should be able to see ticket type as "<issueType>"
    And User should be able to see ticket priority as "<priority>"
    And User should be able to see ticket description as "<description>"
    And User should see the ticket status as "WAITING FOR SUPPORT"
    And User should be able to click on "In progress" workflow
    And User should be able to add comment "This is in progress" for "In progress" workflow
    And User should be able to click workFlow Transition Button
    And User should see the ticket status as "In progress"
    And User should be able to click on "Pending" workflow
    And User should be able to select the linked issue as "duplicates"
    And User should be able to select the pending reason "More info required"
    And User should be able to add comment "This is in pending" for "Pending" workflow
    And User should be able to click workFlow Transition Button
    And User should see the ticket status as "PENDING"
    And User should be able to click on "In progress" workflow
    And User should be able to add comment "This is again in progress" for "In progress" workflow
    And User should be able to click workFlow Transition Button
    And User should see the ticket status as "In progress"
    And User should be able to click on "Cancel request" workflow
    And User should be able to select the resolution as "Resolved"
    And User should be able to add comment "Cancelling the ticket" for "Cancel request" workflow
    And User should be able to click workFlow Transition Button
    And User should see the ticket status as "CANCELED"
    And User should be able to click on "Close" workflow
    And User should see the ticket status as "CLOSED"
    And User should be able to comment on the ticket "Ticket resolved and closed"
    And User should be able to logout from Service Desk
    Examples:
      | projectName        | issueType       | summaryText | description                 | priority | assignee |
      | TestProject (TEST) | Service Request | Test        | This is for testing purpose | Sev 4    | autobot  |

  @WorkFlow2
  Scenario Outline: Login to the service desk,create a service request ticket and workflow transition is In progress, Pending, Resolved and Closed
    Given User is logged in to the Service Desk portal Home page
    And User should be able to see all projects
    And User should click on the the Create button
    And User should be able to select the project "<projectName>"
    And User should be able to select the Issue Type "<issueType>"
    And User should be able to provide the Summary "<summaryText>"
    And User should be able to provide the description "<description>"
    And User should be able to select the priority "<priority>"
    And User should be able to assign the ticket to the assignee "<assignee>"
    And User should be able to submit the ticket
    And User should be able to see the ticket is created successfully and store the ticketID
    And User should be able to see ticket type as "<issueType>"
    And User should be able to see ticket priority as "<priority>"
    And User should be able to see ticket description as "<description>"
    And User should see the ticket status as "WAITING FOR SUPPORT"
    And User should be able to click on "In progress" workflow
    And User should be able to add comment "This is in progress" for "In progress" workflow
    And User should be able to click workFlow Transition Button
    And User should see the ticket status as "In progress"
    And User should be able to click on "Pending" workflow
    And User should be able to select the linked issue as "duplicates"
    And User should be able to select the pending reason "More info required"
    And User should be able to add comment "This is in Pending" for "Pending" workflow
    And User should be able to click workFlow Transition Button
    And User should see the ticket status as "PENDING"
    And User should be able to click on "In progress" workflow
    And User should be able to add comment "This is again in progress" for "In progress" workflow
    And User should be able to click workFlow Transition Button
    And User should see the ticket status as "In progress"
    And User should be able to click on "Resolve this issue" workflow
    And User should be able to select the resolution as "Resolved"
    And User should be able to add comment "Resolving the ticket" for "Resolve this issue" workflow
    And User should be able to click workFlow Transition Button
    And User should see the ticket status as "Resolved"
    And User should be able to click on "Close" workflow
    And User should see the ticket status as "CLOSED"
    And User should be able to comment on the ticket "Ticket resolved and closed"
    And User should be able to logout from Service Desk
    Examples:
      | projectName        | issueType       | summaryText | description                 | priority | assignee |
      | TestProject (TEST) | Service Request | Test        | This is for testing purpose | Sev 4    | autobot  |

  @WorkFlow3
  Scenario Outline: Login to the service desk,create a service request ticket and workflow transition is Escalate, In progress, Pending, Respond to customer, Canceled and Closed
    Given User is logged in to the Service Desk portal Home page
    And User should be able to see all projects
    And User should click on the the Create button
    And User should be able to select the project "<projectName>"
    And User should be able to select the Issue Type "<issueType>"
    And User should be able to provide the Summary "<summaryText>"
    And User should be able to provide the description "<description>"
    And User should be able to select the priority "<priority>"
    And User should be able to assign the ticket to the assignee "<assignee>"
    And User should be able to submit the ticket
    And User should be able to see the ticket is created successfully and store the ticketID
    And User should be able to see ticket type as "<issueType>"
    And User should be able to see ticket priority as "<priority>"
    And User should be able to see ticket description as "<description>"
    And User should see the ticket status as "WAITING FOR SUPPORT"
    And User should be able to select "Escalate" from workflow dropdown
    And User should be able to add comment "This is Escalated" for "Escalate" workflow
    And User should be able to click workFlow Transition Button
    And User should see the ticket status as "ESCALATED"
    And User should be able to click on "In progress" workflow
    And User should be able to add comment "This is in Progress" for "In progress" workflow
    And User should be able to click workFlow Transition Button
    And User should see the ticket status as "In progress"
    And User should be able to click on "Pending" workflow
    And User should be able to select the linked issue as "duplicates"
    And User should be able to select the pending reason "More info required"
    And User should be able to add comment "This is Pending" for "Pending" workflow
    And User should be able to click workFlow Transition Button
    And User should see the ticket status as "PENDING"
    And User should be able to click on "Respond to customer" workflow
    And User should be able to add comment "Responding to customer" for "Respond to customer" workflow
    And User should be able to click workFlow Transition Button
    And User should see the ticket status as "WAITING FOR CUSTOMER"
    And User should be able to click on "Respond to support" workflow
    And User should see the ticket status as "WAITING FOR SUPPORT"
    And User should be able to select "Cancel request" from workflow dropdown
    And User should be able to select the resolution as "Resolved"
    And User should be able to add comment "Cancelling the ticket" for "Cancel request" workflow
    And User should be able to click workFlow Transition Button
    And User should see the ticket status as "CANCELED"
    And User should be able to click on "Close" workflow
    And User should see the ticket status as "CLOSED"
    And User should be able to comment on the ticket "Ticket resolved and closed"
    And User should be able to logout from Service Desk
    Examples:
      | projectName        | issueType       | summaryText | description                 | priority | assignee |
      | TestProject (TEST) | Service Request | Test        | This is for testing purpose | Sev 4    | autobot  |

  @WorkFlow4
  Scenario Outline: Login to the service desk,create a Incident ticket and workflow transition is Pending, Investigate, Pending, Canceled and Closed
    Given User is logged in to the Service Desk portal Home page
    And User should be able to see all projects
    And User should click on the the Create button
    And User should be able to select the project "<projectName>"
    And User should be able to select the Issue Type "<issueType>"
    And User should be able to provide the Summary "<summaryText>"
    And User should be able to provide the description "<description>"
    And User should be able to select the priority "<priority>"
    And User should be able to assign the ticket to the assignee "<assignee>"
    And User should be able to submit the ticket
    And User should be able to see the ticket is created successfully and store the ticketID
    And User should be able to see ticket type as "<issueType>"
    And User should be able to see ticket priority as "<priority>"
    And User should be able to see ticket description as "<description>"
    And User should see the ticket status as "OPEN"
    And User should be able to click on "Pending" workflow
    And User should be able to select the linked issue as "duplicates"
    And User should be able to select the pending reason "More info required"
    And User should be able to add comment "This is Pending" for "Pending" workflow
    And User should be able to click workFlow Transition Button
    And User should see the ticket status as "PENDING"
    And User should be able to click on "Investigate" workflow
    And User should be able to add comment "Need to investigate" for "Invetigate" workflow
    And User should be able to click workFlow Transition Button
    And User should see the ticket status as "WORK IN PROGRESS"
    And User should be able to click on "Pending" workflow
    And User should be able to select the linked issue as "duplicates"
    And User should be able to select the pending reason "More info required"
    And User should be able to add comment "Investigated and in pending" for "Pending" workflow
    And User should be able to click workFlow Transition Button
    And User should see the ticket status as "PENDING"
    And User should be able to click on "Cancel" workflow
    And User should be able to select the resolution as "Resolved"
    And User should be able to add comment "Cancelling the ticket" for "Cancel" workflow
    And User should be able to click workFlow Transition Button
    And User should see the ticket status as "CANCELED"
    And User should be able to click on "Close" workflow
    And User should see the ticket status as "CLOSED"
    And User should be able to comment on the ticket "Ticket resolved and closed"
    And User should be able to logout from Service Desk
    Examples:
      | projectName        | issueType | summaryText | description                 | priority | assignee |
      | TestProject (TEST) | Incident  | Test        | This is for testing purpose | Sev 4    | autobot  |

  @WorkFlow5
  Scenario Outline: Login to the service desk,create a Incident ticket and workflow transition is Pending, Investigate, Resolve, Back to work in progress, Resolve and Closed
    Given User is logged in to the Service Desk portal Home page
    And User should be able to see all projects
    And User should click on the the Create button
    And User should be able to select the project "<projectName>"
    And User should be able to select the Issue Type "<issueType>"
    And User should be able to provide the Summary "<summaryText>"
    And User should be able to provide the description "<description>"
    And User should be able to select the priority "<priority>"
    And User should be able to assign the ticket to the assignee "<assignee>"
    And User should be able to submit the ticket
    And User should be able to see the ticket is created successfully and store the ticketID
    And User should be able to see ticket type as "<issueType>"
    And User should be able to see ticket priority as "<priority>"
    And User should be able to see ticket description as "<description>"
    And User should see the ticket status as "OPEN"
    And User should be able to click on "Pending" workflow
    And User should be able to select the linked issue as "duplicates"
    And User should be able to select the pending reason "More info required"
    And User should be able to add comment "This is Pending" for "Pending" workflow
    And User should be able to click workFlow Transition Button
    And User should see the ticket status as "PENDING"
    And User should be able to click on "Investigate" workflow
    And User should be able to add comment "Need to investigate" for "Invetigate" workflow
    And User should be able to click workFlow Transition Button
    And User should see the ticket status as "WORK IN PROGRESS"
    And User should be able to click on "Resolve" workflow
    And User should be able to select the resolution as "Resolved"
    And User should be able to add comment "Resolving the ticket" for "Resolve" workflow
    And User should be able to click workFlow Transition Button
    And User should see the ticket status as "Completed"
    And User should be able to click on "Back to work in progress" workflow
    And User should be able to add comment "Again moving to work in progress" for "Back to work in progress" workflow
    And User should be able to click workFlow Transition Button
    And User should see the ticket status as "WORK IN PROGRESS"
    And User should be able to click on "Resolve" workflow
    And User should be able to select the resolution as "Resolved"
    And User should be able to add comment "Again resolving the ticket" for "Resolve" workflow
    And User should be able to click workFlow Transition Button
    And User should see the ticket status as "COMPLETED"
    And User should be able to click on "Close" workflow
    And User should see the ticket status as "CLOSED"
    And User should be able to comment on the ticket "Ticket resolved and closed"
    And User should be able to logout from Service Desk
    Examples:
      | projectName        | issueType | summaryText | description                 | priority | assignee |
      | TestProject (TEST) | Incident  | Test        | This is for testing purpose | Sev 4    | autobot  |

  @WorkFlow6
  Scenario Outline: Login to the service desk,create a Incident ticket and workflow transition is Open, Cancel and Closed
    Given User is logged in to the Service Desk portal Home page
    And User should be able to see all projects
    And User should click on the the Create button
    And User should be able to select the project "<projectName>"
    And User should be able to select the Issue Type "<issueType>"
    And User should be able to provide the Summary "<summaryText>"
    And User should be able to provide the description "<description>"
    And User should be able to select the priority "<priority>"
    And User should be able to assign the ticket to the assignee "<assignee>"
    And User should be able to submit the ticket
    And User should be able to see the ticket is created successfully and store the ticketID
    And User should be able to see ticket type as "<issueType>"
    And User should be able to see ticket priority as "<priority>"
    And User should be able to see ticket description as "<description>"
    And User should see the ticket status as "OPEN"
    And User should be able to select "Cancel" from workflow dropdown
    And User should be able to select the resolution as "Resolved"
    And User should be able to add comment "Cancelling the ticket" for "Cancel" workflow
    And User should be able to click workFlow Transition Button
    And User should see the ticket status as "CANCELED"
    And User should be able to click on "Close" workflow
    And User should see the ticket status as "CLOSED"
    And User should be able to comment on the ticket "Ticket resolved and closed"
    And User should be able to logout from Service Desk
    Examples:
      | projectName        | issueType | summaryText | description                 | priority | assignee |
      | TestProject (TEST) | Incident  | Test        | This is for testing purpose | Sev 4    | autobot  |

  @WorkFlow7
  Scenario Outline: Login to the service desk,create a Incident ticket and workflow transition is Open, Resolve and Closed
    Given User is logged in to the Service Desk portal Home page
    And User should be able to see all projects
    And User should click on the the Create button
    And User should be able to select the project "<projectName>"
    And User should be able to select the Issue Type "<issueType>"
    And User should be able to provide the Summary "<summaryText>"
    And User should be able to provide the description "<description>"
    And User should be able to select the priority "<priority>"
    And User should be able to assign the ticket to the assignee "<assignee>"
    And User should be able to submit the ticket
    And User should be able to see the ticket is created successfully and store the ticketID
    And User should be able to see ticket type as "<issueType>"
    And User should be able to see ticket priority as "<priority>"
    And User should be able to see ticket description as "<description>"
    And User should see the ticket status as "OPEN"
    And User should be able to select "Resolve" from workflow dropdown
    And User should be able to select the resolution as "Resolved"
    And User should be able to add comment "Resolving the ticket" for "Resolve" workflow
    And User should be able to click workFlow Transition Button
    And User should see the ticket status as "Completed"
    And User should be able to click on "Close" workflow
    And User should see the ticket status as "CLOSED"
    And User should be able to comment on the ticket "Ticket resolved and closed"
    And User should be able to logout from Service Desk
    Examples:
      | projectName        | issueType | summaryText | description                 | priority | assignee |
      | TestProject (TEST) | Incident  | Test        | This is for testing purpose | Sev 4    | autobot  |

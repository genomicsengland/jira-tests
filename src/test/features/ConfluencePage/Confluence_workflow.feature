@confluence

  Feature: End to end confluence page work flow

    Scenario: Login to the confluence page,access the space directory and create a new page

      Given User is logged in to the confluence page
      And User is able to view all the spaces
      And User should click on the space directory "Quality Assurance"
      And User should be able to select the required page tree "Confluence Testing"
      And User should click on the Create a blank page button
      Then User should be able to provide a page title "Test2"
      And User should be able to provide the page description"This is for testing purpose"
      And User should be able to save the page successfully
      And User should select the required sub page "Test2"
      And User should click on the edit page link
      And User should be able to insert a table
      And User should be able to save the page successfully





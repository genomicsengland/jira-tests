@confluence

Feature: End to end confluence page work flow

  @CreatePage
  Scenario: Login to the confluence page,access the space directory and create a new page

    Given User is logged in to the confluence page
    And User is able to view all the spaces
    And User should click on the space directory "Quality Assurance"
    And User should be able to select the required page tree "Confluence Testing"
    And User should click on the Create a blank page button
    Then User should be able to provide a page title "Test"
    And User should be able to provide the page description"This is for testing purpose"
    And User should be able to save the page successfully


  @EditPage
  Scenario: Create a page,add a table and edit the page by deleting the table

    Given User is logged in to the confluence page
    And User is able to view all the spaces
    And User should click on the space directory "Quality Assurance"
    And User should be able to select the required page tree "Confluence Testing"
    And User should click on the Create a blank page button
    Then User should be able to provide a page title "Test"
    And User should be able to provide the page description"This is for testing purpose"
    And User should be able to insert a table
    And User should be able to save the page successfully
    And User should click on the edit page link
    And User should be able to delete the table
    And User should be able to save the page successfully


  @ExportPage
  Scenario: Access a page from the space directory and export the page to PDF and Word
    Given User is logged in to the confluence page
    And User is able to view all the spaces
    And User should click on the space directory "Quality Assurance"
    And User should be able to select the required page tree "Confluence Testing"
    And User should select the required sub page "Test2"
    And User should be able to click on the Page options displayed on the right corner
    Then User should be able to export the page into PDF
    And User should be able to click on the Page options displayed on the right corner
    Then User should be able to export the page into a Word document





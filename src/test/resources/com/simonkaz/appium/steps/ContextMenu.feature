Feature: Long Press


  Scenario: Opening the ContextMenu with a long press
    Given I am on the main screen
    When I open Context Menu Activity
    And I long press on the element
    Then the context menu is displayed
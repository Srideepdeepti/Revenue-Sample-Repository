

Feature: Check Motor Vehicle Stamp Duty with differents sets of data

  Scenario Outline: Open Service NSW and check online stamp duty page is visble and all details in popup are correct
    Given I open the Service NSW check motor vehicle stamp duty page
    When I click the Check Online button
    Then The Check Online page should be displayed
    And  on entering the <purchasePrice> in page
    And click on Calculate button
    Then All the details like  "<registration>" "<expected_Duty>" are correct
    
      Examples:
      |  purchasePrice       |  registration | expected_Duty|
      | 44999     | Yes    |$1,350.00|
      | 45000  | Yes |$1,350.00|
      | 45500   | Yes |$1,375.00|
    
   


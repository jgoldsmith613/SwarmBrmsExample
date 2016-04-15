Feature: Routing Scenarios

  Scenario: US routing under 100 with good account
    Given the following transaction:
      | Country | Amount | Account |
      | US      |  12.12 |    1234 |
    When I execute approval and routing rules
    Then it is routed to US NY Rep
    And it is approved

  Scenario: US routing  over 100 with good account
    Given the following transaction:
      | Country | Amount | Account |
      | US      | 121.12 |    1234 |
    When I execute approval and routing rules
    Then it is routed to US NY Manager
    And it is approved

  Scenario: US routing equal to 100 with good account
    Given the following transaction:
      | Country | Amount | Account |
      | US      | 100.00 |    1234 |
    When I execute approval and routing rules
    Then it is routed to US NY Manager
    And it is approved

  Scenario: Germany routing under 50 with good account
    Given the following transaction:
      | Country | Amount | Account |
      | DE      |  12.12 |    1234 |
    When I execute approval and routing rules
    Then it is routed to DE MU Rep
    And it is approved

  Scenario: Germany routing over 50 with good account
    Given the following transaction:
      | Country | Amount | Account |
      | DE      | 121.12 |    1234 |
    When I execute approval and routing rules
    Then it is routed to DE MU Manager
    And it is approved

  Scenario: Germany routing equal to 50 with good account
    Given the following transaction:
      | Country | Amount | Account |
      | DE      |  50.00 |    1234 |
    When I execute approval and routing rules
    Then it is routed to DE MU Manager
    And it is approved

  Scenario: Account in bad standing so requst is not approved
    Given the following transaction:
      | Country | Amount | Account |
      | DE      |  50.00 |    7890 |
    When I execute approval and routing rules
    Then it is regected

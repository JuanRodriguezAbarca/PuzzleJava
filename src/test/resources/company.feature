@TestThePuzzle
Feature: As a customer, I want to select the type of car I want to sell, so that I can receive an offer from Company that I can receive, an offer from company

  Scenario: Verifying there are no repeated codes
    Given I have a list of manufacturers
    When I compare manufacturer codes
    Then there are no repeated manufacturer codes

  Scenario Outline: Testing the different combinations of models and dates for each manufacturer
    Given I choose a manufacturer '<manufacturer>'
    When I select the different vehicles
    Then I verify there are no duplicated values

    Examples: Manufacturers expected
      | manufacturer    |
      | Abarth          |
      | Alfa Romeo      |
      | Alpina          |
      | Aston Martin    |
      | Audi            |
      | BMW             |
      | Barkas          |
      | Bentley         |
      | Brilliance      |
      | Buick           |
      | Cadillac        |
      | Caterham        |
      | Chevrolet       |
      | Chrysler        |
      | Citroen         |
      | Corvette        |
      | Dacia           |
      | Daewoo          |
      | Daihatsu        |
      | Dodge           |
      | Ferrari         |
      | Fiat            |
      | Ford            |
      | Honda           |
      | Hummer          |
      | Hyundai         |
      | Infiniti        |
      | Isuzu           |
      | Iveco           |
      | Jaguar          |
      | Jeep            |
      | Kia             |
      | Lada            |
      | Lamborghini     |
      | Lancia          |
      | Land Rover      |
      | Lexus           |
      | Lotus           |
      | MAN             |
      | MG Rover        |
      | MINI            |
      | Maserati        |
      | Maybach         |
      | Mazda           |
      | Mercedes-Benz   |
      | Mitsubishi      |
      | Nissan          |
      | Opel            |
      | Peugeot         |
      | Piaggio (Vespa) |
      | Pontiac         |
      | Porsche         |
      | Proton          |
      | Renault         |
      | Rolls Royce     |
      | Saab            |
      | Seat            |
      | Skoda           |
      | Smart           |
      | Ssangyong       |
      | Subaru          |
      | Suzuki          |
      | Tata            |
      | Tesla           |
      | Toyota          |
      | Trabant         |
      | Volkswagen      |
      | Volvo           |
      | Wartburg        |
      | Westfield       |
      | Zastava         |


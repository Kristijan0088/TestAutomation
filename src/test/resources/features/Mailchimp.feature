Feature: Sign up for Mailchimp test

  Scenario Outline: Mailchimp SignUp page test
    Given I have chosen a "<Browser>"
    When I have entered a valid "<Email-adress>", "<Username>" and "<Password>"
    And I press SignUp button
    Then I should know my "<RegistrationStatus>"

  # User is registered ore User is not registered & New User, LongUn, Username taken, Email missing

    Examples:
      | Browser | Email-adress  | Username      | Password     | RegistrationStatus |
      | Chrome  | OverDown      | Dark#Horse*   | Maven99*     | AccountCreated     |
      | Edge    | ChrisMaster   | Lord*OfNight% | Rick34?#     | AccountCreated                |
      | Chrome  |               | Mister*#      | Mister34*%   | MissingEmail       |
      | Edge    |               | YaleGrave19   | Thomas99#    | MissingEmail       |
      | Chrome  | MasterBlaster | LongUserName  | Mike101**    | LongUN             |
      | Edge    | MasterBlaster | longUserName  | Tyson451**   | LongUN             |
      | Chrome  | HavenAbove    | DarkHorse     | Maverick12*# | Occupied           |
      | Edge    | HavenAbove    | DarkHorse     | Maverick12*# | Occupied           |
      | Chrome  | Conan         | Barbarian     |              | MissingPass        |
      | Edge    | Mandalorien   | DeadStar91    |              | MissingPass        |


  #Create user - everything works as expected OK
  #
  #Create user - long username (more than 100 characters) OK
  #
  #Create user - user already occupied OK
  #
  #Create user - e-mail missing OK
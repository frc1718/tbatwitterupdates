# TBA Twitter Updates
Automatically sends Twitter status updates after a match result is posted.
## Usage
### Configuration
TBATwitterUpdates uses a fairly straight-forward Java properties file to handle configuration values.

When running the program for the first time, a configuration file will be generated; the location of which will be printed to the terminal.  
Edit the file and update all of the values. When entering the team name, ensure that you add the "frc" prefix (e.g. frc1718).  
An example configuration complete with value explanations can be found below:  
```properties
team=frc1718
port=3141
accessToken=TOKEN
accessTokenSecret=TOKEN_SECRET
consumerApiKey=API_KEY
consumerApiKeySecret=API_KEY_SECRET
```
`team` - The team whose events you want to listen for (be sure to prefix with "frc")  
`port` - The port that the TBA Webhook listens on (more info in the webhook setup section)  
`accessToken` - Your Twitter access token (more info in the Twitter setup section)  
`accessTokenSecret` - Your Twitter access token secret  (more info in the Twitter setup section)  
`consumerApiKey` - Your Twitter consumer API key (more info in the Twitter setup section)   
`consumerApiKeySecret` - Your Twitter consumer API key secret (more info in the Twitter setup section) 
## Credits
Made by Sandro Petrovski - [Team 1718](http://fightingpi.org/) The Fighting Pi  
Match data obtained via [The Blue Alliance](https://www.thebluealliance.com/)
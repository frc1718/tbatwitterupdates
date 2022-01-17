# TBA Twitter Updates
Automatically sends Twitter status updates after a match result is posted.
## Usage
### Configuration
TBATwitterUpdates uses a fairly straight-forward Java properties file to handle configuration values.

When running the program for the first time, a configuration file will be generated; edit it with your team number and tokens/keys.  
An example configuration complete with value explanations can be found below:  
```properties
team=frc1718
port=3141
accessToken=TOKEN
accessTokenSecret=TOKEN_SECRET
consumerApiKey=API_KEY
consumerApiKeySecret=API_KEY_SECRET
secret=SECRET
```
`team` - The team whose events you want to listen for (be sure to include the "frc" prefix)  
`port` - The port that the TBA Webhook listens on (more info in the webhook setup section)  
`accessToken` - Your Twitter access token (more info in the Twitter setup section)  
`accessTokenSecret` - Your Twitter access token secret  (more info in the Twitter setup section)  
`consumerApiKey` - Your Twitter consumer API key (more info in the Twitter setup section)   
`consumerApiKeySecret` - Your Twitter consumer API key secret (more info in the Twitter setup section)  
`secret` - TBA Webhook secret (more info in the webhook setup section)
### Twitter Setup
In order to be able to send tweets programmatically, you must setup a [Twitter Developer Account](https://developer.twitter.com/en/apply-for-access.html).  
Once you've done that, setup an [app](https://developer.twitter.com/en/apps) and the Account Activity API [dev environment](https://developer.twitter.com/en/account/environments).  

To get the various keys and tokens, go to the [apps](https://developer.twitter.com/en/apps) menu, "Details" -> "Keys and Tokens".
### TBA Setup
You need to create a webhook in order for TBA Twitter Updates to receive real-time match information, you can set one up from your [TBA Account](https://www.thebluealliance.com/account).  
You must also add a team in [myTBA](https://www.thebluealliance.com/account/mytba#my-teams).  

After creating a webhook, there will be a "Secret" listed under the ID column, set the `secret` value in the configuration file to match this.
## Credits
Made by Sandro Petrovski - [Team 1718](http://fightingpi.org/) The Fighting Pi  
Match data obtained via [The Blue Alliance](https://www.thebluealliance.com/)

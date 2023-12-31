<div align="center">

# Ocean Gromits II
Interactive video link between two "Gromit" sculptures with interface utilising open data sources.

</div>

----

## Background

As part of the ‘Gromit Unleashed 2’ sculpture trail, the University created
four interactive sculptures. Two of these - ‘Oceans 1: Deep Blue’ and 
‘Oceans 2: Yellow Sub’ - used a video link, which allowed visitors to 'We the
Curious' and 'MShed' to view each other across the harbour. The University 
reacquired these sculptures at the close of the trail, and wishes to place 
one in the Merchant Venturers Building, and the other at the new Temple Quarter 
precinct of the University. 

The sculptures should show the feed from the others camera. 
This will be done in the most robust, and lowest maintenance way possible;
ensuring that, upon activation, the sculptures "just work" and require no 
external prompting to function.

The first team's repository can be found here: https://github.com/spe-uob/2020-GromitsRepo

----

## Firmware

Our firmware is a local web server based on the Java Spring framework.
This server handles real-time signalling for the WebRTC clients as well
as authentication for these channels. It uses a small embedded database
to record admin authenitcation infomation. 

## Frontend

The frontend is located within `firmware/frontend` and is a self-contained
client side JavaScript application. It connects to the signalling server
specified in the `REACT_APP_SIGNAL_URL` environment variable to negotiate
authentication and video.

### Dependencies

* JDK 11+
* Node 14.x (Only required for frontend development)
* Yarn (Only for frontend)

#### Getting started

Clone the repository
```
git clone https://github.com/emiljino/Gromit2
```

Pull dependencies and build
**(This will take a long time, but should only have to be done once)**
```
./gradlew build (macOS/Linux/Anything really)
```

To build the frontend (If you're doing frontend development)
```
cd frontend
yarn install
```

Make sure IntelliJ is set to use JDK 11 otherwise you'll get some nasty errors. Building outside of IntelliJ for the first time is usually quicker.

Production builds can then be ran by running
```
java -jar build/libs/<exe name>.jar
```

##### Development

To run in development mode with hot reloading and everything nice:

```
./gradlew bootRun # Run the spring server

# In a seperate terminal:
cd frontend && yarn start # Run the frontend development
```

The frontend can then be accessed under `localhost:3000`

--- 

## Deploying

Both the front and backend need to be deployed seperately.

### Frontend:
Make sure you're in the frontend folder: `firmware/frontend`

First, set the required environment variables and then build the frontend application
```
REACT_APP_SIGNAL_URL=<URL of signalling server>
yarn build 
```
The compiled assets will then be available in `build/`

These can be hosted on any HTTP server or static file host - such as Netlify. If hosting internally
you can also host within the Spring server by placing the build artifacts in Springs static folder.


### Backend:

The backend is a Java Spring Boot applicaion. It needs a random secret key set to make
sure the application is secure. This secret key is whats used to sign and authenticate access
keys for the admin api endpoints as well as the video signaller.

```
SECRET_KEY=<Secret Key> ./gradlew build`
```

The JAR file can then be run similar to
```
java -jar build/libs/firmware.jar
```

----

## Additional Notes:

Any known issues in the system can be found in the 'docs' folder alongside all other documentation
around the system; including the favourable ethics review from the Faculty of Engineering.

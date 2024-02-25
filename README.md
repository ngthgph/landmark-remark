<h1>Landmark Remark Project</h1>
<h2>Description</h2>
<p>The Android native application written in Kotlin, utilizing the Jetpack Compose toolkit, allows users to save location-based notes on the map.</p>
<p><b>Tech stack: </b>  Android Studio, Kotlin, Jetpack Compose, Maps Compose, Coroutines, Firebase, Hilt </p>
<p></p>
<h2>User Stories</h2>
<table>
  <tr>
    <th width="5%">No</th>
    <th width="15%">Backlog</th>
    <th width="20%">Requirement</th>
    <th width="35%">Step</th>
    <th width="15%">Problem</th>
    <th width="10%">Tech Stack</th>
  </tr>
  
  <tr>
    <td>1</td>
    <td>As a user (of the application) I can see my current location on a map</td>
    <td>Add Google Map screen to the UI with camera position at the current location</td>
    <td>- Get Map API key
      <br> - Add Manifest permissions
      <br> - Add dependencies
      <br> - Get current location
      <br> - Add ViewModel, UiState
      <br> - Add GoogleMap, Button composable (button to launch permission request if needed)</td>
    <td></td>
    <td>Maps Compose</td>
  </tr>
  
  <tr>
    <td>2</td>
    <td>As a user I can save a short note at my current location</td>
    <td>- Create a database (Room/Firebase) for saving the notes.
      <br> - Create Marker where users can save note</td>
    <td>- Set up Firebase console, add dependencies, service…
      <br> - Setup to log in as an anonymous user
      <br> - Create Marker
      <br> - Create a Bottom Sheet to add/edit note
      <br> - Save the note linking with its Latlng</td>
    <td>- Cannot handle onClick on Marker, have to add otherwhere button and separate Sheet</td>
    <td>Maps Compose, Room/Firebase</td>
  </tr>
  
  <tr>
    <td>3</td>
    <td>As a user I can see notes that I have saved at the location they were saved on the map</td>
    <td>Show all Markers have been saved in the Google Map at the responding location</td>
    <td>- Show Markers in Google Map for the list of saved note</td>
    <td></td>
    <td>Maps Compose</td>
  </tr>
  
  <tr>
    <td>4</td>
    <td>As a user I can see the location, text, and user name of notes other users have saved</td>
    <td>- Handle user account
      <br> - Retrieve data of all notes from the database and show them in Markers</td>
    <td>- Build UI for creating accounts: LoginScreen, SignUpScreen, AccountScreen
      <br> - Add Firebase Account Service
      <br> - Show Markers in Google Map for the list of saved notes of all users</td>
    <td>- (Firebase) The document cannot be added to the collection (can only create an anonymous account)</td>
    <td>Firebase, Hilt</td>
  </tr>
  
  <tr>
    <td>5</td>
    <td>As a user I have the ability to search for a note based on contained text or user-name</td>
    <td>Have a Search Bar to query notes based on text/user name</td>
    <td>- Add Search Bar to UI,
      <br> - Use query to get remarkId from inputed text (note/ user name)</td>
    <td>Not yet build</td>
    <td></td>
  </tr>
  
</table>

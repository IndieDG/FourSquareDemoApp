# FourSquareDemoApp
A demo app to demonstrate basic Android API integrations

Developed by João Quintas for WhitBread pratical test.

Used External Libraries:
	- GSON: Used to parse json responses.

App behaviour:
	- First search is done using a default location defined in strings.xml
	- Clicking a search result will open Google Maps with navigation from your local position to the selected destination
	- Pressing search button located at the bottom will open a search fragment
		- Type desired location to obtain the venues near the searched location
		
App Limitations:
	- Using simple ListView to display search result. Not the most optimal solution. (Implemented like so due to time constraints)
	- No pagination (Implemented like so due to time constraints)
	
Run App
	- Open project using Android Studio
	- Go to Run -> Run App

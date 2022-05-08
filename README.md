# Meteorite assignment

>> Check the screenshots of the app at the bottom of the page

## [Download APK](https://github.com/sibaprasad12/MeteoriteApp_assignment/blob/main/app/apk/MeteoriteApp.apk)
## Assignment details

## Browser screen
* The browser screen contains a list of beers with at least the following information:
- Image
- Name
- ABV
- When tapping on a beer cell a Beer Details Screen is presented.
## Beer Details screen
- The beer screen is a  guide for the brewer and contains:
- Image
- Name
- ABV
- Description
- Display all Hops in a list with quantity
- Display all Malts in a list with quantity
- Display all Methods in a list
- Ingredients and methods lists could be very large. Each ingredient should have a Weigh Button, opening the Weigh Screen with the target weight set.
## Weigh Screen
- The current weight should be displayed on a progress bar, or any other widget that can display the current value and target weight, visually appealing for the users.
- The component  producing the stream of events is not yet ready, so you need to mock the stream to start implementing this part of the app.
- In the Resources section there is an example of the stream and animation.
- Here is a possible layout of the screen just as an example, but creativity will be really appreciated when designing this screen.

##Nice to have
- It will be very convenient for the user if the weighted ingredient is marked as done.
- User might want to navigate back to browser screen and back, so would be nice if all marked ingredients will not lose marked state
## Resources
- Weight Stream Example, this is the stream coming from the BLE scale, with timestamps and weight
- UI Update Animation Example, shows how the UI get animated to display the new weight while the events are received from the BLE scale
- [Beer List  API](https://punkapi.com/documentation/v2)
- Weigh Screen Animation Example

## Feature Implemented
- Lazy Loading
- Pull to refresh
- DIFF Utils to ease loading
- Unit test cases

## Architecture and lib used
- MVVM
- Data Binding
- Coroutines
- Clean Architecture
- Dagger Hilt for Dependency Injection

## Libraries used
- Coroutines - for multi threading
- Data Binding and view model for MVVM
- Junit and mockito for Unit testing
- DIffUtils to make recyclerview adapter load items efficiently

## Here is the screen shot for the application
<table>
<tr>
<td>
  <img src="https://github.com/sibaprasad12/MeteoriteApp_assignment/blob/main/app/images/ss1.png" width="250" height="400" />
 </td>
<td>
 <img src="https://github.com/sibaprasad12/MeteoriteApp_assignment/blob/main/app/images/ss2.png" width="250" height="400"/>
</td>
  <td>
  <img src="https://github.com/sibaprasad12/MeteoriteApp_assignment/blob/main/app/images/ss3.png" width="250" height="400" />
 </td>
<td>
 <img src="https://github.com/sibaprasad12/MeteoriteApp_assignment/blob/main/app/images/ss4.png" width="250" height="400"/>
</td>
</tr>
</table>
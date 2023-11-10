# MovieFinder
## Tested on
 - Tested using MacOS, with Pixel 5 emulators running 30 and 31
## Installation Instructions
- Download the apk labeled: app-debug.apk
- Open Android Studio and open an empty project
- Set up the emulator and run it
- Once the emulator has fully booted up, go to home screen
- Drag and drop the apk on the emulator
- If Drag and drop doesn't work, you can manually install it using terminal:
- Open a terminal.
- Navigate to the directory where the Android SDK is installed. This is usually in a directory called Android/sdk in your home directory.
- Go to the platform-tools directory by typing cd platform-tools and pressing Enter.
- Connect to the emulator by typing adb connect localhost:<PORTNUMBER> and pressing Enter. 
- Install the APK by typing adb install <path_to_apk> and pressing Enter. Replace <path_to_apk> with the path to your APK file
## Libraries Used
- For parsing local json file that I initially used to set up the UI
    implementation 'com.google.code.gson:gson:2.10.1'

- For working with viewmodels 
    implementation 'androidx.lifecycle:lifecycle-livedata-ktx:2.6.2'
  
- To process images and show pass them to the view
    implementation 'com.github.bumptech.glide:glide:4.16.0'

- retrofit and okhittp were used to consume APIs
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
    implementation 'com.squareup.okhttp3:okhttp:4.9.0'
    implementation 'com.squareup.okhttp3:logging-interceptor:4.9.0'

## Thought Process

I went with an MVVM architecuture for its scalability and separation of concerns. It is also my preferred architecture when it comes to Mobile Apps. I will break them down into how I utilized each of them;

### Models
I have Movie and Genre as the main models that make. Each containing the required params in their constructors. MovieRepository is responsible for fetching and handling the data, so I put that in the models as well.
There are other data classes in the package too, which were used to absctract out the types of data since the return value was an object and not an array, it made sense to make extra helper data classes to make it more readable.
The interfaces were used to contain the get request information for both movies and genres. I decided to put them in the Model's package as well.


### ViewModel
I had one ViewModel that was reponsible for creating LivaData of the movies and genres and it since it was taking in an argument, I had to make use of ViewModeFactory to instantiate it. 
#Views#
I used a Fragment (HomeFrament) as the main screen, because I intended to use a Nav bar for a favorites tab if I had time left at the end. It contained a recycle view that was populated by an adapter. The adapter was also used for starting the Detail Activity and passing data to it.
Since that one didn't require much logic, I didn't create a ViewModel for it, and populated the UI using view binding.


### DataHandling and how i tackled the requirements

I initially used a local JSON file to create the layouts and then switched to APIs later. I utlized retrofit and okhttp to fetch the data. Movies were pretty straightforward, and it wasn't hard to replace the mock data. 
I just switched the function that was called by the viewmodel and it worked right away. For Genres, I used a hashmap to map each genre to the id, and then created a helper function to convert an array of genre ids into one string. 
I passed that function to the adapter to display the values on the screen. 
For displaying more than one page (ie more than 20 movies) I looped over the function that fetched the data so that it fetched the next page each time. I then added that to the existing list and passed it forward.
I used gradle.properties to keep the api keys hidden. Although not the best approach, I think it was still better than hard coding it in. 

### What I would do differently if I had more itme
- I would probably start with unit tests, I really wanted to add them but I knew I didn't have enough time for that. 
- Use jetpack compose because it makes view binding much easier.
- Add a nav bar and a favorite section
- Improve upon the UI with better fonts and themes
- Use some sort keystore or cipher for hiding the api key

### Commits- How I use Github
- I make the first commit after I get a basic setup running, with project structure and a genral basic layout set up. In this case, getting a basic MVVM project running with dummy data.
- Then each time I make progress and or I am ending my session, I would commit. In this instant, connecting to the API, adding a display screen were both progress
- I use branches for new features or for collaboration
- If I have a working version of the project, and i want to add a new feature (ie Navbar in this instance) i would create a bracnh

 Thank you for your consideration and taking the time








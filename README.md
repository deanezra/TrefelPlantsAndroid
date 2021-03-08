# A sample Android app testing out the Trefle.io plants API.
# Written by Dean Ezra

https://docs.trefle.io/reference#operation/listPlants

# Tech used

- Platform: Android
- Language: Kotlin and coroutines
- Architecture: MVVM (Model View View Model)
- Networking: Retrofit 2
- Dependency Injection: Dagger2
- Unit tests: Junit and Mockk
- UI: Fragments, RecyclerView and BottomSheetDialogFragement

# What does the app do?

- Downloads a list of plants via the trefle.io plants API.
- Shows them in a RecyclerView list
- If user clicks on one of the plants, a slide up bottom sheet appears showing details of the plant.

# Want to see what it looks like?

## Screen 1: List of plants (pulled via Trefle plant api):
<img src="https://github.com/deanezra/blob/master/appdetail/screens/screen1_list.jpg" height="600" width="300">

## Screen 2: Clicking on a plant in list shows its details via slide up sheet/panel:
<img src="https://github.com/deanezra/blob/master/appdetail/screens/screen2_details.jpg" height="600" width="300">

## Video of the app running on a Samsung S9+ Android 10:
<a href="https://github.com/deanezra/blob/master/appdetail/video/trefleplantapp_video.mp4">

# Important: How to build and run the app:

Before you compile the app in Android Studio, you must set your Trefle.io token in the 'local.properties' file which can be found in the root of this repository.

The current value of the apiToken is set to a placeholder SECRET_TOKEN like so:

```
# Replace SECRET_TOKEN with your Trefle API token:
# NOTE: Do not wrap the token value in quotes.
apiToken = SECRET_TOKEN
```

You need to replace SECRET_TOKEN with your trefle.io token value WITHOUT quotes:

```
# Replace SECRET_TOKEN with your Trefle API token:
# NOTE: Do not wrap the token value in quotes.
apiToken = abcDEF123
```

If you do not have a trefle.io token, sign up for a free account and get a token [here link](https://trefle.io/users/sign_up).



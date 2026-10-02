# Future Game Booster

A futuristic Android game-booster dashboard built with Kotlin + Jetpack Compose.

## Included
- Animated futuristic HUD
- Rotating scanner/radar graphics
- Turbo Boost UI state
- FPS/RAM/temperature dashboard
- RAM/GPU/network module cards
- Game launcher UI
- Dark neon/cyber visual style

## Build
Open this folder in Android Studio (Ladybug or newer recommended), let Gradle sync, then:
Build > Build APK(s)

The generated debug APK will normally be under:
app/build/outputs/apk/debug/app-debug.apk

## Important
This starter app provides a polished booster dashboard and safe UI-level performance mode.
Android does not allow a normal third-party app to arbitrarily overclock the CPU/GPU or force another game's FPS.
Real device optimization should use APIs and permissions supported by the target Android version.


## Build APK from an Android phone with GitHub Actions

1. Create a GitHub repository.
2. Upload the contents of this project (including `.github/workflows/build-apk.yml`).
3. Open the repository's **Actions** tab.
4. Select **Build Android APK**.
5. Tap **Run workflow** if it has not started automatically.
6. When the workflow finishes with a green check, open the workflow run.
7. Under **Artifacts**, download `FutureGameBooster-debug`.
8. Extract the downloaded artifact and install `app-debug.apk` on your Android phone.

For a public GitHub repository, the artifact is available from the workflow run according to GitHub's artifact retention settings. Do not upload signing keys or passwords to the repository.

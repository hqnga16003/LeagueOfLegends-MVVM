
A modern Android application built with MVVM architecture, Jetpack Compose, and Hilt. It provides the latest news, allowing users to read details and bookmark their favorite articles.

## How to Build and Run
1. **Clone the repository:**
   ```bash
   git clone https://github.com/hqnga16003/LeagueOfLegends-MVVM/tree/test_home
   ```
2. **Setup API Key:**
   - Obtain an API key from [NewsAPI.org](https://newsapi.org/).
   - Open the `local.properties` file in the root directory.
   - Add the following line:
     ```properties
     NEWS_API_KEY=your_api_key_here
     ```
3. **Open and Run:**
   - Open the project in **Android Studio**.
   - Sync Gradle.

## Architectural Decisions
- **MVVM Architecture:** Separation of concerns between the UI (Compose), business logic (ViewModel), and data source (Repository).
- **Jetpack Compose:** Fully declarative UI for a modern development experience.
- **Hilt (Dependency Injection):** Used for managing dependencies across the app, including ViewModels and Repositories.
- **Room Database:** Local storage to support bookmarking functionality and offline access to saved articles.
- **WorkManager:** Background sync implementation to periodically check for new articles.
- **Retrofit:** High-performance networking for fetching news data.

## Current Limitations & Future Improvements
While the core functionality is implemented, there are several areas that I haven't had the chance to complete yet. If I had more time, I would focus on:

- **Search & Pagination:** Currently, the app displays a static list of articles. I would implement a search bar and use the Paging 3 library for infinite scrolling to handle large data sets efficiently.
- **Clean Architecture Refactoring:** Move beyond basic MVVM to a full Clean Architecture with Domain layers and Use Cases to ensure the code is even more testable and scalable.
- **Robust Error & Exception Handling:** Create a centralized error handling system to manage API failures, network issues, and edge cases, providing a smoother experience for the user.
- **Notification Permissions (Android 13+):** Implement a more sophisticated permission request flow that explains the value of notifications before asking the user for access.
- **Advanced UI/UX:**
    - **Empty States:** Create better visual feedback when lists are empty (e.g., no bookmarks or search results).
    - **Loading States:** Implement Shimmer/Skeleton screens instead of basic progress bars.
    - **Animations:** Add smooth transitions between screens and list item interactions.
- **Comprehensive Testing:** Write Unit Tests for ViewModels and Repositories using MockK/Turbine, and UI Tests using Compose Test Rule to ensure long-term stability.

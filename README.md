# Brief intro about the CurrencyConverter

Exchange rates are fetching from https://currencylayer.com/documentation

User able to select a currency from a list of currencies provided by the API(some floating-point errors are occuring as of now... Fixing those for next iterations)

User able to enter desired amount for selected currency

User able to see a list of exchange rates for the selected currency

Currently Rates are persisted locally and refreshed no more frequently than every 30 minutes (to limit bandwidth usage)

Unit testing

# Android Architecture and its components

This project written in Kotlin... Clean Architecture using SOLID priniciples and consists of DaggerHilt, Viewbinding, Viewmodels, Stateflows, Room database, extension dependencies and unit testing

